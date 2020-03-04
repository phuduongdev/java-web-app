/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.phuduongdev.jshm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author phudev
 */
public interface UserService {

    void doCreateUser(HttpServletRequest request, HttpServletResponse response);

    void doDeleteUser(HttpServletRequest request, HttpServletResponse response);

    void doUpdateUserView(HttpServletRequest request, HttpServletResponse response);

    void goToCreateUserView(HttpServletRequest request, HttpServletResponse response);

    void goToUpdateUserView(HttpServletRequest request, HttpServletResponse response);

    void goToUserList(HttpServletRequest request, HttpServletResponse response);

}
