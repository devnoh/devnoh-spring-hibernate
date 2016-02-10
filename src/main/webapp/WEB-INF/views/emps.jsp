<%@include file="/WEB-INF/layout/header.jsp" %>

<style>
    table {
        border-collapse: collapse;
    }

    td, th {
        padding: 5px;
        border: 1px solid #333;
    }
</style>

<h2>Employees <c:if test="${deptNo gt 0}">(Dept No: ${deptNo})</c:if></h2>

<table>
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Job</th>
        <th>Hired</th>
        <th>Salary</th>
        <th>Comm.</th>
        <th>Dept.</th>
    </tr>
    <c:forEach items="${emps}" var="emp">
        <tr>
            <td>${emp.empNo}</td>
            <td>${emp.name}</td>
            <td>${emp.job}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${emp.hireDate}"/></td>
            <td style="text-align: right"><fmt:formatNumber value="${emp.salary}" type="currency" currencySymbol="$" /></td>
            <td style="text-align: right"><fmt:formatNumber value="${emp.commission}" type="currency" currencySymbol="$" /></td>
            <td>${emp.department.name}</td>
        </tr>
    </c:forEach>
</table>

<%@include file="/WEB-INF/layout/footer.jsp" %>
