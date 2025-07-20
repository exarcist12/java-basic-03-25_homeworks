package ru.otus.java.basic.homeworks;

public class Main {
    public static void main(String[] args) {

        User[] users = new User[11];

        users[0] = new User("Ivanov", "Ivan", "Ivanovich", 1999, "Ivan@example.com");
        users[1] = new User("Petrov", "Petr", "Petrovich", 1989, "Petr@example.com");
        users[2] = new User("Sidorov", "Sidor", "Sidorovich", 1979, "Sidor@example.com");
        users[3] = new User("Vasilev", "Vasiliy", "Vasilevich", 1969, "Vasiliy@example.com");
        users[4] = new User("Fedorov", "Fedor", "Fedorovich", 1998, "Fedor@example.com");
        users[5] = new User("Stepanov", "Stepan", "Stepanovich", 1988, "Stepan@example.com");
        users[6] = new User("Chizhikov", "Chizhik", "Chizhikovich", 1978, "Chizhik@example.com");
        users[7] = new User("Chesterov", "Chester", "Chesterovich", 1968, "Chester@example.com");
        users[8] = new User("Sharikov", "Sharik", "Sharikovich", 1997, "Sharik@example.com");
        users[9] = new User("Robertov", "Robert", "Robertovich", 1997, "Robert@example.com");
        users[10] = new User("Baranov", "Baran", "Baranovich", 1997, "Baran@example.com");

        for (int i = 0; i < users.length; i++) {
            if (2025 - users[i].getYearOfBirth() >= 40) {
                users[i].getInfo();
            }
        }
    }

}
