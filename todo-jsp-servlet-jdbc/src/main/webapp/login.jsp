<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="ISO-8859-1">
        <title>Todo App - Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>

    <body>
        <jsp:include page="layout/header.jsp"></jsp:include>

            <div class="container col-md-4 col-md-offset-8" style="overflow: auto">
                <h1>Login Form</h1>

            <c:if test="<%= session.getAttribute("loginErrorMessage") != null%>">
                <p class="text-danger">
                    <c:out value="<%= session.getAttribute("loginErrorMessage").toString()%>" />
                </p>
            </c:if>

            <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                <div class="form-group">
                    <label for="uname">User Name:</label> <input type="text" class="form-control" id="username" placeholder="User Name" name="username" required>
                </div>
                <div class="form-group">
                    <label for="uname">Password:</label> <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <hr/>
            <div class="form-group">
                Are you new? <a href="register.jsp">Click here to Register</a>

            </div>

        </div>
        <jsp:include page="layout/footer.jsp"></jsp:include>
    </body>

</html>