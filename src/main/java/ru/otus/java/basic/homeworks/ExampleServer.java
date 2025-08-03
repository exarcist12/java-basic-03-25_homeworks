package ru.otus.java.basic.homeworks;

import java.io.*;

public class ExampleServer implements AutoCloseable{
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ExampleServer(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new DataInputStream(inputStream);
        this.outputStream = new DataOutputStream(outputStream);
    }

    public String getMessage() throws IOException {
        return inputStream.readUTF();
    }

    public void send(String message) throws IOException {
        outputStream.writeUTF(message);
        outputStream.flush();
    }

    @Override
    public void close() throws Exception {
        inputStream.close();
        outputStream.close();
    }
}
