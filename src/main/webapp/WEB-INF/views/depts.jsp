<%@include file="/WEB-INF/layout/header.jsp" %>

<h2>Departments</h2>

<ul>
    <c:forEach items="${depts}" var="dept">
        <li><a href="<c:url value='/emps?deptNo=${dept.deptNo}'/>">${dept.name}</a> (${fn:length(dept.employees)})</li>
    </c:forEach>
</ul>

<%@include file="/WEB-INF/layout/footer.jsp" %>
