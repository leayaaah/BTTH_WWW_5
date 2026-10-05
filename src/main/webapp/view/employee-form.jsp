<%--
  Created by IntelliJ IDEA.
  User: pc
  Date: 10/6/2026
  Time: 3:58 AM
  To change this template use File | Settings | File Templates.
--%>
<<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Employee Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4 mb-5">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-4 rounded">

    <h2>${employee != null ? 'Edit Employee' : 'Add New Employee'}</h2>

    <form action="${pageContext.request.contextPath}/employees" method="post" class="mt-4 p-4 border rounded shadow-sm bg-light">
        <!-- Input ẩn chứa ID, cần thiết cho việc Update -->
        <input type="hidden" name="id" value="${employee != null ? employee.id : ''}"/>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label fw-bold">Name:</label>
                <input type="text" class="form-control" name="name" value="${employee != null ? employee.name : ''}" required/>
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Role (Phân quyền):</label>
                <input type="text" class="form-control" name="role" value="${employee != null ? employee.role : ''}" required/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-6">
                <label class="form-label fw-bold">Email:</label>
                <input type="email" class="form-control" name="email" value="${employee != null ? employee.email : ''}"/>
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Phone Number:</label>
                <input type="text" class="form-control" name="phone" value="${employee != null ? employee.phone : ''}"/>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col-md-4">
                <label class="form-label fw-bold">Hire Date:</label>
                <input type="date" class="form-control" name="hireDate" value="${employee != null ? employee.hireDate : ''}"/>
            </div>
            <div class="col-md-4">
                <label class="form-label fw-bold">Salary:</label>
                <input type="number" step="0.01" class="form-control" name="salary" value="${employee != null ? employee.salary : ''}" required/>
            </div>
            <div class="col-md-4">
                <label class="form-label fw-bold">Status:</label>
                <select name="status" class="form-select">
                    <option value="Active" ${employee != null && employee.status == 'Active' ? 'selected' : ''}>Active</option>
                    <option value="Inactive" ${employee != null && employee.status == 'Inactive' ? 'selected' : ''}>Inactive</option>
                    <option value="On Leave" ${employee != null && employee.status == 'On Leave' ? 'selected' : ''}>On Leave</option>
                </select>
            </div>
        </div>

        <div class="row mb-4">
            <div class="col-md-6">
                <label class="form-label fw-bold">Department:</label>
                <select name="departmentId" class="form-select">
                    <option value="0">-- Select Department --</option>
                    <c:forEach var="dep" items="${departments}">
                        <option value="${dep.id}" ${employee != null && employee.departmentId == dep.id ? 'selected' : ''}>${dep.name}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="col-md-6">
                <label class="form-label fw-bold">Position (Chức vụ):</label>
                <select name="positionId" class="form-select">
                    <option value="0">-- Select Position --</option>
                    <c:forEach var="pos" items="${positions}">
                        <option value="${pos.id}" ${employee != null && employee.positionId == pos.id ? 'selected' : ''}>${pos.title}</option>
                    </c:forEach>
                </select>
            </div>
        </div>

        <div class="d-flex gap-2">
            <button type="submit" class="btn btn-primary px-4">Save</button>
            <a href="${pageContext.request.contextPath}/employees" class="btn btn-secondary px-4">Cancel</a>
        </div>
    </form>
</div>
</body>
</html>
