<%--
  Created by IntelliJ IDEA.
  User: Developer
  Date: 3/28/2023
  Time: 12:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<table>
    <thead>
    <tr>
        <th>All Payments</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="document" items="${documents}">
        <tr>
            <td>${document}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</html>
