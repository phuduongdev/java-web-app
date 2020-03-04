/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.dao.todo;

import io.github.phuduongdev.todoapp.model.Todo;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author phudev
 */
public interface TodoDao {

    void insertTodo(Todo todo) throws SQLException;

    Todo selectTodoById(String id);

    List<Todo> selectAllTodos();

    boolean deleteTodoById(String id) throws SQLException;

    boolean updateTodoById(Todo todo) throws SQLException;
}
