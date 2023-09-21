package edu.escuelaing.arep.app.types;

import java.io.IOException;
import java.io.*;
import java.net.*;
import java.util.*;


public class text extends file {

    public text(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        try {
            this.filePath = new URI("." + filePath);
        }catch (URISyntaxException e){
            this.filePath = filePath;
        }
    }

    @Override
    public void sendfile() throws IOException {
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        String outputLine;

        outputLine = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: " + getMimeType() + " \r\n" +
                "\r\n";
        outputLine += readFile(filePath.getPath());

        out.println(outputLine);

        out.close();
        clientSocket.close();
    }

    public String readFile(String path) {
        StringBuilder outputLine = new StringBuilder();
        try {
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                outputLine.append(data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return outputLine.toString();
    }
    public String getMimeType(){
        switch (fileType){
            case "js":
                return "text/javascript";
            case "css":
                return "text/css";
            case "html":
                return "text/html";
        }
        return "";
    }

}
