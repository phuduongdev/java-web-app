/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.jshm.controller;

import io.github.phuduongdev.jshm.service.UserService;
import io.github.phuduongdev.jshm.service.UserServiceImplement;
import java.io.IOException;
import java.io.PrintWriter;
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
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImplement();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String action = request.getServletPath();
            System.out.println("+++++++++++++++++++ request path" + action);
            switch (action) {
                case "/create":
                    userService.goToCreateUserView(request, response);
                    break;
                case "/do-create":
                    userService.doCreateUser(request, response);
                    break;
                case "/update":
                    userService.goToUpdateUserView(request, response);
                    break;
                case "/do-update":
                    userService.doUpdateUserView(request, response);
                    break;
                case "/do-delete":
                    userService.doDeleteUser(request, response);
                    break;
//                case "":
//                    // some statements
//                    break;
//                case "":
//                    // some statements
//                    break;
//                case "":
//                    // some statements
//                    break;
//                case "":
//                    // some statements
//                    break;
//                case "":
//                    // some statements
//                    break;
                default:
                    userService.goToUserList(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    // </editor-fold>

}
