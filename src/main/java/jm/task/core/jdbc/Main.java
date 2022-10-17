package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
       UserService service = new UserServiceImpl();

       //создать таблицу User
       service.createUsersTable();

       //добавить пользователей в бд
       service.saveUser("Igor", "Medvedev", (byte) 37);
       service.saveUser("Natalya", "Semenova", (byte) 24);
       service.saveUser("Vladimir", "Petrov", (byte) 45);
       service.saveUser("Alexandr", "Smirnov", (byte) 26);

       //очистить таблицу User
       service.cleanUsersTable();

       //удалить таблицу User
       service.dropUsersTable();

    }
}
