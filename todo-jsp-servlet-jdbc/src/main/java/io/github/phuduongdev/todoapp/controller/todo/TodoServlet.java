/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.controller.todo;

import io.github.phuduongdev.todoapp.dao.todo.TodoDao;
import io.github.phuduongdev.todoapp.dao.todo.TodoDaoImpl;
import io.github.phuduongdev.todoapp.model.Todo;
import io.github.phuduongdev.todoapp.utils.DateTimeManager;
import io.github.phuduongdev.todoapp.utils.ExceptionManager;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phudev
 */
@WebServlet("/")
public class TodoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    TodoDao todoDao;

    @Override
    public void init() throws ServletException {
        todoDao = new TodoDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String actionURL = request.getServletPath();
            System.out.println("+++++++++++++++++++++" + actionURL);
            switch (actionURL) {
                case "/insert":
                    showInsertTodoView(request, response);
                    break;
                case "/do-insert":
                    doInsertTodo(request, response);
                    break;
                case "/do-delete":
                    doDeleteTodo(request, response);
                    break;
                case "/update":
                    showUpdateTodoView(request, response);
                    break;
                case "/do-update":
                    doUpdateTodo(request, response);
                    break;
                case "/list":
                    showAllTodosView(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(TodoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    private void showInsertTodoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("todoItem", new Todo());
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/form.jsp");
        dispatcher.forward(request, response);
    }

    private void doInsertTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ParseException {
        String todoTitle = request.getParameter("todoTitle");
        String todoUsername = request.getParameter("todoUsername");
        String todoDescript = request.getParameter("todoDescript");
        boolean todoIsDone = Boolean.valueOf(request.getParameter("todoIsDone"));

        String todoTargetDateString = request.getParameter("todoTargetDate");
        Date convertToDate = new SimpleDateFormat("yyyy-MM-dd").parse(todoTargetDateString);
        long todoTargetDate = DateTimeManager.convertDateToMiliseconds(convertToDate);

        Todo newTodo = new Todo(todoTitle, todoUsername, todoDescript, todoTargetDate, todoIsDone);

        todoDao.insertTodo(newTodo);

        response.sendRedirect("list");
    }

    private void doDeleteTodo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String todoId = request.getParameter("todoId");
        todoDao.deleteTodoById(todoId);

        response.sendRedirect("list");
    }

    private void showUpdateTodoView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String todoId = request.getParameter("todoId");
        Todo existingTodo = todoDao.selectTodoById(todoId);
        request.setAttribute("todoItem", existingTodo);

        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/form.jsp");
        dispatcher.forward(request, response);
    }

    private void doUpdateTodo(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String todoId = request.getParameter("todoId");
        String todoTitle = request.getParameter("todoTitle");
        String todoUsername = request.getParameter("todoUsername");
        String todoDescript = request.getParameter("todoDescript");
        boolean todoIsDone = Boolean.valueOf(request.getParameter("todoIsDone"));
// target date process
        String todoTargetDateString = request.getParameter("todoTargetDate");
        // ATTENTION TO DATETIME STRING FORMAT
        Date convertToDate = new SimpleDateFormat("yyyy-MM-dd").parse(todoTargetDateString);
        long todoTargetDate = DateTimeManager.convertDateToMiliseconds(convertToDate);
//////////////////////

        Todo updateTodo = new Todo(todoId, todoTitle, todoUsername, todoDescript, todoTargetDate, todoIsDone);
        try {
            todoDao.updateTodoById(updateTodo);
        } catch (SQLException ex) {
            ExceptionManager.printSQLException(ex);
        }
        response.sendRedirect("list");
    }

    private void showAllTodosView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Todo> todoList = todoDao.selectAllTodos();
        request.setAttribute("todoList", todoList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("todo/list.jsp");
        dispatcher.forward(request, response);
    }
    // </editor-fold>
}
