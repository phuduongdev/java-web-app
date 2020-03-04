/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.todoapp.controller;

import io.github.phuduongdev.todoapp.dao.LoginDao;
import io.github.phuduongdev.todoapp.model.Login;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author phudev
 */
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private LoginDao loginDao;

    @Override
    public void init() throws ServletException {
        super.init(); //To change body of generated methods, choose Tools | Templates.
        loginDao = new LoginDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        authenticate(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String loginErrorMessage = "";

        Login login = new Login();
        login.setUsername(username);
        login.setPassword(password);

        try {
            if (loginDao.validate(login)) {
                RequestDispatcher rd = request.getRequestDispatcher("list");
                rd.forward(request, response);
            } else {
                loginErrorMessage = "Email or Password is not valid...";
                HttpSession session = request.getSession(true);
                session.setAttribute("loginErrorMessage", loginErrorMessage);
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
