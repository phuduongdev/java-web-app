<%--
    Document   : form
    Created on : Feb 27, 2020, 5:21:44 PM
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
              crossorigin="anonymous" />

    </head>
    <body>
        <jsp:include page="../layout/_nav.jsp"></jsp:include>
            <br/>
            <!--        <div class="container test__zone">
                        <p>Todo item: </p>
                    </div>-->
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <!--insert case-->
                    <c:if test="${todoItem == null}">
                        <form action="<%=request.getContextPath()%>/do-insert" method="post">
                            <caption><h2>Add New Todo</h2></caption>
                        </c:if>
                        <!--end case-->

                        <!--update case-->
                        <c:if test="${todoItem != null}">
                            <form action="<%=request.getContextPath()%>/do-update" method="post">
                                <caption><h2>Edit Todo</h2></caption>
                                <input type="hidden" name="todoId" value="<c:out value='${todoItem.id}' />" />
                            </c:if>
                            <!--end case-->

                            <fieldset class="form-group">
                                <label>Title: </label>
                                <input type="text" name="todoTitle"
                                       value="<c:out value='${todoItem.title}' />"
                                       class="form-control" required="required" minlength="5" />
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Description: </label>
                                <input type="text" name="todoDescript"
                                       value="<c:out value='${todoItem.description}' />"
                                       class="form-control" minlength="5" />
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Todo Status</label>
                                <select class="form-control" name="todoIsDone">
                                    <option value="false">In Progress</option>
                                    <option value="true">Complete</option>
                                </select>
                            </fieldset>

                            <fieldset class="form-group">
                                <label>Todo Target Date</label>
                                <jsp:useBean id="dateObject" class="java.util.Date" />
                                <jsp:setProperty name="dateObject" property="time" value="${todoItem.targetDate}" />

                                <input type="date" name="todoTargetDate"
                                       value="<fmt:formatDate value="${dateObject}" pattern="dd/MM/yyyy HH:mm:ss"/>"
                                       class="form-control" required="required" />
                            </fieldset>

                            <button type="submit" class="btn btn-success">Save</button>
                        </form>
                </div>
            </div>
        </div>
        <jsp:include page="../layout/footer.jsp"></jsp:include>
    </body>
</html>
