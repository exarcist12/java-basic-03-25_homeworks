import java.io.IOException;

public interface AuthenticatedProvider {
    void initialize();
    boolean authenticate(ServerConnection serverConnection, String login, String password) throws IOException;
    boolean registration(ServerConnection serverConnection, String login, String password, String username) throws IOException;
}

