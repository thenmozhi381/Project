<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="jquery/jquery-3.2.1.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/header1.jsp"></jsp:include>
<br>
<div class="container">
<h1>Category List</h1>
<table class="table table-hover" id="category" class="display" border="2" width="80" align="center">
<tr>
<th>Sno.</th>
<th>CID</th>
<th>Category Name</th>
<th class="span2">Action</th>


<c:if test="${empty catList }">
<tr><td colspan="9" align="center">No Record Exists!!!</td></tr>
</c:if>

<c:forEach var="c" varStatus="st" items="${catList}">
<tr>
<td><c:out value="${st.count}"/></td>
<td><c:out value="${c.cid}"/></td>
<td><c:out value="${c.cname}"/></td>

<td class="span4">
<c:set var="contextRoot" value="${pageContext.request.contextPath}"></c:set>
<a class="btn btn-info" role="button" href="<c:url value="/admin/deleteCat/${c.cid}"/>">Delete</a>
<a class="btn btn-info" role="button" href="<c:url value="/admin/updateCategory?cid=${c.cid}"/>">Update</a>
</td>
</tr>
</c:forEach>
</table>
</div>

</body>
</html>