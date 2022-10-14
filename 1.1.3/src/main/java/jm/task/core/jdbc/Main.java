package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserService service = new UserServiceImpl();

        //создать таблицу User
        service.createUsersTable();

        //добавить пользователей в бд
        List<User> list = new ArrayList<>();
        list.add(new User("Igor", "Medvedev", (byte) 37));
        list.add(new User("Natalya", "Semenova", (byte) 24));
        list.add(new User("Vladimir", "Petrov", (byte) 45));
        list.add(new User("Alexandr", "Smirnov", (byte) 26));

        for (User user : list){
            service.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User с именем " + user.getName() + " добавлен в базу данных.");
        }

        //очистить таблицу User
        service.cleanUsersTable();

        //удалить таблицу User
        service.dropUsersTable();
    }
}
