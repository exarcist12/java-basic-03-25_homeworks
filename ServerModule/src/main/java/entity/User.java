package entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    String login;
    String passwordUser;
    String username;
    Role role;
}
