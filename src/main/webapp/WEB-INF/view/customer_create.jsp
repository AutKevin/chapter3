<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>客户管理-创建客户</title>
</head>
<body>

<h1>创建客户界面</h1>
${msg}
<table>
<tr>
    <th>客户名称</th>
    <th>电话号码</th>
    <th>联系人</th>
</tr>
    <tr>
    <c:forEach var="c" items="${customer}">
            <td>${c.value}</td>
    </c:forEach>
    </tr>
</table>
</body>
</html>
