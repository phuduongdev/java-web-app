/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.dao.todo;

import io.github.phuduongdev.todoapp.model.Todo;
import io.github.phuduongdev.todoapp.utils.DBConnectionManager;
import io.github.phuduongdev.todoapp.utils.ExceptionManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *
 * @author phudev
 */
public class TodoDaoImpl implements TodoDao {

    private final String INSERT_TODO = "INSERT INTO todo "
            + "(title, username, description, targetDate, isDone, id) "
            + "VALUES (?,?,?,?,?,?);";
    private final String SELECT_ALL_TODOS = "SELECT * FROM todo;";
    private final String SELECT_TODO_BY_ID = "SELECT * FROM todo WHERE id=?;";
    private final String DELETE_TODO_BY_ID = "DELETE FROM todo WHERE id=?;";
    private final String UPDATE_TODO_BY_ID = "UPDATE todo "
            + "SET title=?, username=?, description=?, targetDate=?, isDone=? "
            + "WHERE id=?;";

    public TodoDaoImpl() {
    }

    @Override
    public void insertTodo(Todo todo) throws SQLException {
        System.out.println(INSERT_TODO);
        PreparedStatement ps = queryToDemoDB(INSERT_TODO);
        ps.setString(1, todo.getTitle());
        ps.setString(2, todo.getUsername());
        ps.setString(3, todo.getDescription());
        ps.setLong(4, todo.getTargetDate());
        ps.setBoolean(5, todo.getStatus());
        ps.setString(6, UUID.randomUUID().toString());
        System.out.println(ps);
        ps.executeUpdate();
    }

    @Override
    public Todo selectTodoById(String todoId) {
        Todo todo = null;
        try {
            PreparedStatement ps = queryToDemoDB(SELECT_TODO_BY_ID);
            ps.setString(1, todoId);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                todo = getTodoData(rs);
            }
        } catch (SQLException ex) {
            ExceptionManager.printSQLException(ex);
        }
        return todo;
    }

    @Override
    public List<Todo> selectAllTodos() {
        List<Todo> allTodos = new ArrayList<>();
        try {
            PreparedStatement ps = queryToDemoDB(SELECT_ALL_TODOS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Todo item = getTodoData(rs);
                allTodos.add(item);
            }
        } catch (SQLException ex) {
            ExceptionManager.printSQLException(ex);
        }
        return allTodos;
    }

    @Override
    public boolean deleteTodoById(String id) throws SQLException {
        PreparedStatement ps = queryToDemoDB(DELETE_TODO_BY_ID);
        ps.setString(1, id);
        return ps.executeUpdate() > 0;
    }

    @Override
    public boolean updateTodoById(Todo todo) throws SQLException {
        PreparedStatement ps = queryToDemoDB(UPDATE_TODO_BY_ID);
        ps.setString(1, todo.getTitle());
        ps.setString(2, todo.getUsername());
        ps.setString(3, todo.getDescription());
        ps.setLong(4, todo.getTargetDate());
        ps.setBoolean(5, todo.getStatus());
        ps.setString(6, todo.getId());
        return ps.executeUpdate() > 0;
    }

    private Todo getTodoData(ResultSet rs) throws SQLException {
        return new Todo(
                rs.getString("id"),
                rs.getString("title"),
                rs.getString("username"),
                rs.getString("description"),
                rs.getLong("targetDate"),
                rs.getBoolean("isDone"));
    }

    private PreparedStatement queryToDemoDB(String sqlQuery) throws SQLException {
        Connection connection = DBConnectionManager.getConnection();
        return connection.prepareStatement(sqlQuery);
    }

}
