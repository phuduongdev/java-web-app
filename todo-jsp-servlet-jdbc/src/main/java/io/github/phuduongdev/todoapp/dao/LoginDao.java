/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.dao;

import io.github.phuduongdev.todoapp.model.Login;
import io.github.phuduongdev.todoapp.utils.DBConnectionManager;
import io.github.phuduongdev.todoapp.utils.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author phudev
 */
public class LoginDao {

    public boolean validate(Login login) throws ClassNotFoundException {
        boolean status = false;

        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = DBConnectionManager.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee where username=? and password=?")) {
            preparedStatement.setString(1, login.getUsername());
            preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
        } catch (SQLException e) {
            ExceptionManager.printSQLException(e);
        }
        return status;
    }
}
