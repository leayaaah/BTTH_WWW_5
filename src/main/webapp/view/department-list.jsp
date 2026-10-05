<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Departments List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%" class="mb-4 rounded">

    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Departments List</h2>
        <a href="${pageContext.request.contextPath}/employees" class="btn btn-secondary">Back to Employees</a>
    </div>

    <form action="${pageContext.request.contextPath}/departments" method="get" class="row g-2 mb-3">
        <input type="hidden" name="action" value="list"/>
        <div class="col-md-8">
            <input type="text" name="keyword" class="form-control" placeholder="Search by department name"
                   value="${keyword}">
        </div>
        <div class="col-md-4 d-flex gap-2">
            <button type="submit" class="btn btn-primary">Search</button>
            <a href="${pageContext.request.contextPath}/departments" class="btn btn-outline-secondary">Reset</a>
        </div>
    </form>

    <table class="table table-bordered table-hover">
        <thead class="table-primary text-center">
        <tr>
            <th>ID</th>
            <th>Department Name</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dep" items="${departments}">
            <tr class="align-middle">
                <td class="text-center">${dep.id}</td>
                <td>${dep.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
