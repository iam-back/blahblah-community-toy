<%@ page contentType="text/html; charset=utf-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BlahBlah</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/user/authentication" method="post">
        <label for="email">EMAIL : </label>
        <input type="email" name="email" id="email">
        <label for="password">PASSWORD : </label>
        <input type="password" name="password" id="password">
        <input type="submit" value="로그인">
    </form>
</body>
</html>