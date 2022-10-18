package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao = new UserDaoHibernateImpl();
    public UserServiceImpl(){
    }

    public void createUsersTable() throws SQLException, ClassNotFoundException {
        try{
            userDao.createUsersTable();
            System.out.println("Таблица User успешно создана.");
        } catch (HibernateException he) {
            System.out.println("Ошибка создания таблицы User.");
        }

    }

    public void dropUsersTable() throws SQLException, ClassNotFoundException {
        try{
            userDao.dropUsersTable();
            System.out.println("Таблица User успешно удалена.");
        } catch (HibernateException he) {
            System.out.println("Ошибка удаления таблицы.");
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException, ClassNotFoundException {
        try{
            userDao.saveUser(name, lastName, age);
            System.out.println("Пользователь " + name + " успешно добавлен.");
        } catch (HibernateException he) {
            System.out.println("Ошибка добавления пользователя.");
        }
    }

    public void removeUserById(long id) throws SQLException {
        try{
            userDao.removeUserById(id);
            System.out.println("Пользователь с id = " + id + " удалён.");
        } catch (HibernateException he) {
            System.out.println("Ошибка удаления по id");
        }
    }

    public List<User> getAllUsers() throws SQLException, ClassNotFoundException {
        try{
            System.out.println("Список пользователей успешно получен.");
        } catch (HibernateException he) {
            System.out.println("Ошибка получения списка пользователей");
        }
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        try{
            userDao.cleanUsersTable();
            System.out.println("Таблица успешно очищена.");
        } catch (HibernateException he) {
            System.out.println("Ошибка очистки таблицы.");
        }
    }
}
