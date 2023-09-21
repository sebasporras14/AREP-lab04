package edu.escuelaing.arep.app.types;

import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.Socket;
import java.net.URI;

public class img extends file{

    public String header;

    public img(Socket clientSocket, String fileType, URI filePath){
        this.clientSocket = clientSocket;
        this.fileType = fileType;
        this.filePath = filePath;
        this.header = "HTTP/1.1 200 OK \r\n" +
                "Content-Type: image/" + fileType + " \r\n" +
                "\r\n";
    }

    @Override
    public void sendfile() throws IOException {
        OutputStream out = clientSocket.getOutputStream();
        System.out.println(filePath);
        BufferedImage img = ImageIO.read(new File(System.getProperty("user.dir") + filePath));

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(img, fileType, byteArrayOutputStream);
        out.write(header.getBytes());
        out.write(byteArrayOutputStream.toByteArray());

        out.close();
        clientSocket.close();
    }

}
