import java.io.*;

public class ClientBase {


    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;

    public ClientBase(InputStream inputStream, OutputStream outputStream) {
        this.inputStream = new DataInputStream(inputStream);
        this.outputStream = new DataOutputStream(outputStream);
    }

    public void send(String message) throws IOException {

        try{
            outputStream.writeUTF(message);
            outputStream.flush();
        } catch (EOFException e){
            System.out.println("Сервер закрыл соединение.");
        }
    }

    public String getMessage() throws IOException {
        try {
            return inputStream.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() throws Exception {
        inputStream.close();
        outputStream.close();
    }
}
