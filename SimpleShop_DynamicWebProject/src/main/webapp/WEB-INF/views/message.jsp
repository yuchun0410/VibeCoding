<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title><%= request.getAttribute("title") %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card">
        <h1><%= request.getAttribute("title") %></h1>
        <p><%= request.getAttribute("message") %></p>
        <a class="btn" href="<%= request.getContextPath() %>/product/list">返回商品列表</a>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
