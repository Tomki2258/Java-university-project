package org.example.repositories.Jdbc;

import org.example.User;
import org.example.UserType;
import org.example.db.JdbcConnectionManager;
import org.example.repositories.IUserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcRepository implements IUserRepository {
    private List<User> users = new ArrayList<>();
    @Override
    public User getUser(int index) {
        return null;
    }
    public UserJdbcRepository(){
        String sql = "SELECT * FROM users";

        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                User user = User.builder()
                        .id(rs.getString("id"))
                        .login(rs.getString("login"))
                        .password(rs.getString("password"))
                        .role(UserType.valueOf(rs.getString("role")))
                        .build();

                users.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
    }
    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void save() {
        String sql = "SELECT * FROM users";

        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
    }

    @Override
    public void add(User user) {
        String sql = "INSERT INTO users (id, login, password, role) VALUES (?, ?, ?, ?)";
        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, user.getId());
            stmt.setString(2, user.getLogin());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getRole().toString());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while saving user", e);
        }
    }

    @Override
    public boolean userExist(String nick) {
        return false;
    }

    @Override
    public void saveJson() {

    }
}
