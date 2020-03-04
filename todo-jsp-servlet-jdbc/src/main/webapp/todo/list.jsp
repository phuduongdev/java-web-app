<%--
    Document   : todo-list
    Created on : Feb 27, 2020, 9:05:00 AM
    Author     : phudev
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>To Do Management</title>

        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="../layout/_nav.jsp"></jsp:include>
            <!--content-->
            <div class="row">
                <div class="container">
                    <h3 class="text-center">List of Todos</h3>
                    <hr />
                    <div class="container text-left">
                        <a href="<%=request.getContextPath()%>/insert" class="btn btn-success">Add Todo</a>
                </div>
                <br />
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Target Date</th>
                            <th>Todo Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="todoItem" items="${todoList}">
                            <tr>
                                <td><c:out value="${todoItem.title}"/></td>
                                <td>
                                    <jsp:useBean id="dateObject" class="java.util.Date" />
                                    <jsp:setProperty name="dateObject" property="time" value="${todoItem.targetDate}" />
                                    <fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy HH:mm:ss"/>
                                </td>
                                <td>
                                    <c:if test="${todoItem.status}">
                                        <c:out value="Completed"/>
                                    </c:if>
                                    <c:if test="${!(todoItem.status)}">
                                        <c:out value="In Progress"/>
                                    </c:if>

                                </td>
                                <td>
                                    <a href="update?todoId=<c:out value='${todoItem.id}'/>" >Edit</a>
                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                    <a href="do-delete?todoId=<c:out value='${todoItem.id}'/>">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <!--end content-->

        <jsp:include page="../layout/footer.jsp"></jsp:include>
    </body>
</html>
