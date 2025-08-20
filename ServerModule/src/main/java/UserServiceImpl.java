import entity.Role;
import entity.User;

import java.sql.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UserServiceImpl {
    private static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/otus-db";
    private static final String DATABASE_USER = "admin";
    private static final String DATABASE_PASSWORD = "password";
    private static final String ALL_USERS = "select users.login, users.password_user, users.username, roles.role_name from users join roles on users.role_id =roles.id";
    private static final String USER_ROLES_QUERY = "select roles.role_name from users join roles on users.role_id =roles.id where users.username = ?;";
    private static final String IS_ADMIN_QUERY = "select count(1) from roles join users on users.role_id =roles.id  where users.username = ? and roles.role_name = 'admin';";
    private static final String NEW_USER = "insert into users (login, password_user, username, role_id) values (?, ?, ?, 1);";

    private final Connection connection;

    public UserServiceImpl() {
        try{
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Role getRole(User user) {
        Role currentRole = null;
        try (PreparedStatement ps = connection.prepareStatement(USER_ROLES_QUERY)) {
            ps.setString(1, user.getUsername());
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    String roleName = resultSet.getString(1);
                    currentRole = Role.builder().roleName(roleName).build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return currentRole;
    }


    public boolean isAdmin(User user) {
        int flag = 0;
        try (PreparedStatement ps = connection.prepareStatement(IS_ADMIN_QUERY)) {
            ps.setString(1, user.getUsername());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    flag = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flag == 1;
    }


    public void addUser(User user) {
        try (PreparedStatement ps = connection.prepareStatement(NEW_USER)) {
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPasswordUser());
            ps.setString(3, user.getUsername());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<User> getAllUsers() {
        List<User> result = new CopyOnWriteArrayList<>();
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(ALL_USERS)) {
                while (rs.next()) {
                    User currentUser = UserMapper.getUser(rs);
                    result.add(currentUser);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
}