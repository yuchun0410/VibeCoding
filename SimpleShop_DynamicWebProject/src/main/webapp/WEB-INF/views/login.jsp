<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>會員登入</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card" style="max-width: 480px; margin: 40px auto;">
        <h1>會員登入</h1>
        <p>測試帳號：admin / admin123，或 test / test123</p>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="<%= request.getContextPath() %>/login" method="post">
            <div class="form-row">
                <label>帳號</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-row">
                <label>密碼</label>
                <input type="password" name="password" required>
            </div>
            <button class="btn" type="submit">登入</button>
            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/product/list">先逛商品</a>
        </form>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
