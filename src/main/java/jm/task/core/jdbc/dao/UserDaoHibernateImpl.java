package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.ArrayList;
import java.util.List;
import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                    "(id INTEGER not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(45) not NULL, " +
                    " lastname VARCHAR(45) not NULL, " +
                    " age INTEGER(3) not NULL, " +
                    " PRIMARY KEY (id))").addEntity(User.class).executeUpdate();
            transaction.commit();
            System.out.println("Таблица User успешно создана.");
        }catch (HibernateException he){
            System.out.println("Ошибка создания таблицы User.");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS user").executeUpdate();
            transaction.commit();
            System.out.println("Таблица User успешно удалена.");
        }catch (HibernateException eh){
            System.out.println("Ошибка удаления таблицы.");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("Пользователь " + user.getName() + " успешно добавлен");
        }catch (HibernateException eh){
            System.out.println("Ошибка добавления пользователя");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.delete(String.valueOf(id), session.load(User.class, id));
            transaction.commit();
            System.out.println("Пользователь с id = " + id + " удалён.");
        }catch (HibernateException eh){
            System.out.println("Ошибка удаления по id");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            userList = session.createQuery("from User").getResultList();
            transaction.commit();
            System.out.println("Список пользователей успешно получен.");
        }catch (HibernateException eh){
            System.out.println("Ошибка получения списка пользователей");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try(Session session = getSessionFactory().openSession()){
            transaction = session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
            transaction.commit();
            System.out.println("Таблица успешно очищена.");
        }catch (HibernateException eh){
            System.out.println("Ошибка очистки таблицы.");
            if(transaction != null){
                System.out.println("Откат транзакции.");
                transaction.rollback();
            }
        }
    }
}
