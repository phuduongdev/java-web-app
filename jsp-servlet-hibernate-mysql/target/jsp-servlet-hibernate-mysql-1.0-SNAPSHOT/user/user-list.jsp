<%--
    Document   : user-list
    Created on : Mar 3, 2020, 8:48:04 AM
    Author     : phudev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Manager Application - List</title>
    </head>
    <body>
        <jsp:include page="../layout/header.jsp"></jsp:include>

            <div align="center">
                <table border="1" cellpadding="5">
                    <caption>
                        <h2>List of Users</h2>
                    </caption>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Email</th>
                            <th>Country</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="user" items="${userList}">
                        <tr>
                            <td><c:out value="${user.id}" /></td>
                            <td><c:out value="${user.name}" /></td>
                            <td><c:out value="${user.email}" /></td>
                            <td><c:out value="${user.country}" /></td>
                            <td>
                                <a href="update?userId=<c:out value='${user.id}' />">Edit</a>
                                &nbsp;|||&nbsp;
                                <a href="do-delete?userId=<c:out value='${user.id}' />">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>

        </div>
    </body>
</html>
