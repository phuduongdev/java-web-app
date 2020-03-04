/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.dao;

import io.github.phuduongdev.todoapp.model.User;
import io.github.phuduongdev.todoapp.utils.DBConnectionManager;
import io.github.phuduongdev.todoapp.utils.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author phudev
 */
public class UserDao {

    public int registerEmployee(User employee) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO employee"
                + " (id, first_name, last_name, username, password) VALUES "
                + " (?, ?, ?, ?, ?);";

        int result = 0;
        try (Connection connection = DBConnectionManager.getConnection();
                // Step 2:Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, UUID.randomUUID().toString());
            preparedStatement.setString(2, employee.getFirstName());
            preparedStatement.setString(3, employee.getLastName());
            preparedStatement.setString(4, employee.getUsername());
            preparedStatement.setString(5, employee.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // process sql exception
            ExceptionManager.printSQLException(e);
        }
        return result;
    }
}
