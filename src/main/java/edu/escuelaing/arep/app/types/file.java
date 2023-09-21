package edu.escuelaing.arep.app.types;

import java.io.IOException;
import java.net.*;

public abstract class file {
    public Socket clientSocket;
    public String fileType;
    public URI filePath;

    public abstract void sendfile() throws IOException;

}
