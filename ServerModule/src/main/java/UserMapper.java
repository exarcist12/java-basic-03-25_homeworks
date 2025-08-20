import entity.Role;
import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public static User getUser(ResultSet rs) throws SQLException {
            String login = rs.getString("login");
            String password = rs.getString("password_user");
            String username = rs.getString("username");
            String roleName = rs.getString("role_name");
        User currentUser = User.builder()
                    .login(login)
                    .passwordUser(password)
                    .username(username)
                    .role(Role.builder()
                            .roleName(roleName)
                            .build())
                    .build();

        return currentUser;
    }
}
