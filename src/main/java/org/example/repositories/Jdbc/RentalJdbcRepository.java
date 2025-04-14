package org.example.repositories.Jdbc;

import org.example.User;
import org.example.UserType;
import org.example.db.JdbcConnectionManager;
import org.example.models.Rental;
import org.example.repositories.IRentalRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentalJdbcRepository implements IRentalRepository {
    @Override
    public void fn() {

    }

    @Override
    public List<Rental> getRentals() {
        List<Rental> list = new ArrayList<>();
        String sql = "SELECT * FROM rental";

        try (Connection connection = JdbcConnectionManager.getInstance().getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Rental rental = Rental.builder()
                        .id(rs.getString("id"))
                        .userID(rs.getString("user_id"))
                        .vehicleID(rs.getString("vehicle_id"))
                        .rentDate(rs.getString("rent_date"))
                        .returnDate(rs.getString("return_date"))
                        .build();

                list.add(rental);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error occurred while reading vehicles", e);
        }
        return list;
    }
}
