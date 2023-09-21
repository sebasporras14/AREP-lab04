package edu.escuelaing.arep.app;

import java.lang.reflect.Method;
import java.net.*;
import edu.escuelaing.arep.app.types.file;
import edu.escuelaing.arep.app.types.img;
import edu.escuelaing.arep.app.types.text;

import java.io.*;



public class HttpServer {
    private static file fileController;


    public static void main(String[] args) throws Exception {
        ComponentLoader.loadComponents();
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(35000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 35000.");
            System.exit(1);
        }
        while (true) {
            Socket clientSocket = null;
            try {
                System.out.println("Listo para recibir ...");
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
                break;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String Request = "";
            Request += in.readLine() + "\n";
            while (in.ready()) {
                Request += (char) in.read();
            }
            System.out.println("Received: " + Request.split("\n")[0]);
            String method = Request.split(" ")[0];
            String path = Request.split(" ")[1];
            URI functPath = new URI(path);
            URI filePath = new URI("/target/classes/public" + path);
            Method s = ComponentLoader.search(functPath.getPath(), method);
            try {
                if(s != null){
                    String query = path.split("=")[1];
                    String response = ComponentLoader.ejecutar(s,query);
                    sendResponce(clientSocket, response);
                } else {
                    manageFile(filePath, clientSocket);
                }
            }catch (Exception e){}

            in.close();
        }
        serverSocket.close();
    }

    private static void manageFile(URI filePath, Socket clientSocket) throws Exception {
        File file = new File(System.getProperty("user.dir") + filePath);
        String fileType = getFileType(filePath);
        if (!file.exists()) {
            fileController = new text(clientSocket, "html", new URI("/target/classes/public" + "/NotFound.html"));
        } else if (filePath.getPath().endsWith(".jpg") || filePath.getPath().endsWith(".png") || filePath.getPath().endsWith(".jpeg") || filePath.getPath().endsWith(".gif")){
            fileController = new img(clientSocket, fileType, filePath);
        } else if (filePath.getPath().endsWith(".html") || filePath.getPath().endsWith(".css") || filePath.getPath().endsWith(".js")) {
            fileController = new text(clientSocket, fileType, filePath);
        }else {
            fileController = new text(clientSocket, "html", new URI("/target/classes/public" + "/NotFound.html"));
        }
        fileController.sendfile();
    }

    private static String getFileType(URI path){
        String format = "";
        try {
            format = path.getPath().split("\\.")[1];
        } catch (ArrayIndexOutOfBoundsException e){}
        return format;
    }

    public static void sendResponce(Socket clientSocket, String responce) throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;
        outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: text/html" + " \r\n" +
                "\r\n";
        outputLine += responce;
        out.println(outputLine);
        out.close();
        clientSocket.close();
    }


}
