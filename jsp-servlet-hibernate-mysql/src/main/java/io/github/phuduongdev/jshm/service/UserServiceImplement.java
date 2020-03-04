package io.github.phuduongdev.jshm.service;

import io.github.phuduongdev.jshm.dao.UserDao;
import io.github.phuduongdev.jshm.model.User;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phudev
 */
public class UserServiceImplement implements UserService {

    private UserDao userDao;

    public UserServiceImplement() {
        userDao = new UserDao();
    }

    @Override
    public void goToCreateUserView(HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher rd = request.getRequestDispatcher("user/user-form.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doCreateUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("userName");
            String email = request.getParameter("userEmail");
            String country = request.getParameter("userCountry");

            User newUser = new User();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setCountry(country);

            userDao.createUser(newUser);

            response.sendRedirect("list");
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void goToUpdateUserView(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            User existingUser = userDao.readUser(id);

            RequestDispatcher rd = request.getRequestDispatcher("user/user-form.jsp");
            request.setAttribute("user", existingUser);
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doUpdateUserView(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            String name = request.getParameter("userName");
            String email = request.getParameter("userEmail");
            String country = request.getParameter("userCountry");

            User editedUser = new User();
            editedUser.setId(id);
            editedUser.setName(name);
            editedUser.setEmail(email);
            editedUser.setCountry(country);

            userDao.updateUser(editedUser);

            response.sendRedirect("list");
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void doDeleteUser(HttpServletRequest request, HttpServletResponse response) {
        try {
            int id = Integer.parseInt(request.getParameter("userId"));
            userDao.deleteUser(id);

            response.sendRedirect("list");
        } catch (IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void goToUserList(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> userList = userDao.readAllUser();

            request.setAttribute("userList", userList);
            RequestDispatcher rd = request.getRequestDispatcher("user/user-list.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UserServiceImplement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
