<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta  content="text/html; charset=UTF-8"  http-equiv="content-type">
    <title>Paradise</title>
    <link rel="stylesheet" href="css/paradise.css">
</head>
<body>
<div class="smile">
    <div class="titleContainer">
        <div class="title">
            Paradise
        </div>
    </div>
</div>
<div class="main">
    <div class="mainGrid">
        <div class="gridLeft" style="padding-left:5%">
            <jsp:include page="/WEB-INF/listField.jsp">
                <jsp:param name="side" value="${side}"/>
            </jsp:include>
        </div>
        <div class="gridRight">
            <jsp:include page="/WEB-INF/formField.jsp">
                <jsp:param name="side" value="${side}"/>
            </jsp:include>
        </div>
    </div>
</div>
</body>
<script src="scripts/index.js"></script>
</html>