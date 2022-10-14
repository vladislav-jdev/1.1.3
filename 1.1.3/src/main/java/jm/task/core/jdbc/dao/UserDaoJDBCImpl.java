package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() throws SQLException, ClassNotFoundException {

    }

    public void createUsersTable(){
        String createUserTable = "CREATE TABLE IF NOT EXISTS user" +
                "(id INTEGER not NULL AUTO_INCREMENT, " +
                " name VARCHAR(45) not NULL, " +
                " lastname VARCHAR(45) not NULL, " +
                " age INTEGER(3) not NULL, " +
                " PRIMARY KEY (id))";
        try(Statement statement = connection.createStatement()){
            statement.execute(createUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable(){
        String deleteUserTable = "DROP TABLE IF EXISTS user";
        try(Statement statement = connection.createStatement()){
            statement.execute(deleteUserTable);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age){
        String saveUser = "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?)";
        try(PreparedStatement prepareStatement = connection.prepareStatement(saveUser)){
                prepareStatement.setString(1, name);
                prepareStatement.setString(2, lastName);
                prepareStatement.setByte(3, age);
                prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String deleteUserById = "DELETE FROM user WHERE id = ?";
        try(PreparedStatement prepareStatement = connection.prepareStatement(deleteUserById)){
            prepareStatement.setLong(1, id);
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> getAllUsers() {
        String getUserList = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();
        try(Connection connection = Util.getConnection();
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(getUserList)){
                while (rs.next()){
                    User user = new User();
                    user.setId(rs.getLong("id"));
                    user.setName(rs.getString("name"));
                    user.setLastName(rs.getString("lastname"));
                    user.setAge(rs.getByte("age"));
                    userList.add(user);
                }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return userList;
    }

    public void cleanUsersTable() {
        String deleteAllUsers = "TRUNCATE TABLE user";
        try(PreparedStatement prepareStatement = connection.prepareStatement(deleteAllUsers)){
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
