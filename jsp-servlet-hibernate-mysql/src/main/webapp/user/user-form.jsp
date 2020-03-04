<%--
    Document   : user-form
    Created on : Mar 3, 2020, 4:50:26 PM
    Author     : phudev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manager Application - Form</title>
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"></jsp:include>
            <div align="center">
                <form action="<c:out value="${user != null ? 'do-update': 'do-create'}" />">
                <table border="1" cellpadding="5">
                    <caption>
                        <c:out value="${user != null ? 'Edit User': 'Add New User'}" />
                            <c:if test="${user != null}">
                                <input type="hidden" name="userId" value="<c:out value="${user.id}" />" />
                            </c:if>
                    </caption>
                    <tbody>
                        <tr>
                            <th>User Name:  </th>
                            <td>
                                <input type="text" name="userName" size="45"
                                       value="<c:out value="${user.name}" />" />
                            </td>
                        </tr>
                        <tr>
                            <th>User Email: </th>
                            <td>
                                <input type="text" name="userEmail" size="45"
                                       value="<c:out value="${user.email}" />" />
                            </td>
                        </tr>
                        <tr>
                            <td>User Country: </td>
                            <td>
                                <input type="text" name="userCountry" size="45"
                                       value="<c:out value="${user.country}" />" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align="center">
                                <input type="submit" value="SAVE" />
                            </td>
                        </tr>
                    </tbody>
                </table>


                </form>
            </div>
        </body>
    </html>
