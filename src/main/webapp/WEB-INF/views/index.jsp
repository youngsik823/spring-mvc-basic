<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<%@ include file="include/static-head.jsp" %>

<style>
    #main-title {
        margin-top: 200px auto;
        font-size: 40px;
        font-weight: 700;
        color: orange;
    }
</style>
</head>
<body>

    <%@ include file="include/header.jsp" %>

    <c:if test="${flag}">
        로그인 하셨군요?
    </c:if>

    <script>
        console.log('flag: ${flag}');
    </script>

    <h1 id="main-title">초보자님 안녕하세요~~</h1>
</body>
</html>