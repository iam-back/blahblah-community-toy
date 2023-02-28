<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>BlahBlah</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="x-ua-compatible" content="ie=edge"/>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"/>
</head>
<body>
<div class="container">
    <div class="row blog-navbar">
        <div class="col-md-7"></div>
        <div class="col-md-5 text-end">
            <c:choose>
                <c:when test="${session==null}">
                    <a href="${pageContext.request.contextPath}/user/authentication">Sign In</a>
                    <a href="${pageContext.request.contextPath}/user/registration">Sign Up</a>
                </c:when>
                <c:otherwise>
                    ${session.name}님 안녕하세요!
                    <a href="${pageContext.request.contextPath}/blog/edit">글 작성하기</a>
                    <a href="${pageContext.request.contextPath}/user/info/${session.id}">내 정보</a>
                    <a href="${pageContext.request.contextPath}/user/logout">Sign Out</a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>