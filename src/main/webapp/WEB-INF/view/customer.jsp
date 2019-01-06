<%@ page pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="BASE" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>客户管理</title>
</head>
<body>
<a href="${BASE}/customer_add_jsp">添加</a>
<h1>客户界面</h1>
<table>
    <tr>
        <th>客户名称</th>
        <th>电话号码</th>
        <th>操作</th>
    </tr>
    <c:forEach var="customer" items="${customerList}">
    <tr>
        <td>${customer.name}</td>
        <td>${customer.telephone}</td>
        <td><a href="${BASE}/customer_show?id=${customer.id}" />查看详情</td>
        <td><a href="${BASE}/customer_show_json?id=${customer.id}" />查看JSON接口返回数据</td>
        <td><a href="${BASE}/customer_delete?id=${customer.id}" />删除</td>
    </tr>
    </c:forEach>
</table>
</body>
</html>
