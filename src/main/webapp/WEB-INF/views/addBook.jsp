<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
  <title>FormHeader</title>
</head>
<body>
<h2>Add Book</h2>
<form:form method="post" modelAttribute="book">
    <div><label for="title">Title<form:input id="title" path="title"/></label></div>
    <div><label for="rating">Rating<form:input id="rating" path="rating"/></label></div>
    <div><label for="description">Description<form:textarea id="description" path="description"/></label></div>
    <div><label for="publisher">Publisher<form:select id="publisher" path="publisher.id" items="${publishers}" itemValue="id" itemLabel="name"/></label></div>
    <div><label for="authors">Authors<form:select id="authors" path="authors" items="${authors}" itemValue="id" itemLabel="name" multiple="true"/></label></div>
    <div><input type="submit"></div>
</form:form>
</body>
</html>
