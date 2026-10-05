<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 10/6/2026
  Time: 3:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Employees List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-4 rounded">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Employees List</h2>
        <a href="${pageContext.request.contextPath}/employees?action=new" class="btn btn-success">Add Employee</a>
    </div>

    <table class="table table-bordered table-hover">
        <thead class="table-primary text-center">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Role</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Hire Date</th>
            <th>Status</th>
            <th>Salary</th>
            <th>Dept ID</th>
            <th>Pos ID</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="emp" items="${employees}">
            <tr class="align-middle">
                <td class="text-center">${emp.id}</td>
                <td>${emp.name}</td>
                <td>${emp.role}</td>
                <td>${emp.email}</td>
                <td>${emp.phone}</td>
                <td class="text-center">${emp.hireDate}</td>
                <td class="text-center">
                    <span class="badge ${emp.status == 'Active' ? 'bg-success' : (emp.status == 'Inactive' ? 'bg-danger' : 'bg-warning')}">
                            ${emp.status}
                    </span>
                </td>
                <td class="text-end">${emp.salary}</td>
                <td class="text-center">${emp.departmentId}</td>
                <td class="text-center">${emp.positionId}</td>
                <td class="text-center">
                    <a href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}" class="btn btn-sm btn-primary">Edit</a>
                    <a href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/departments" class="btn btn-secondary">Manage Departments</a>
</div>
</body>
</html>
