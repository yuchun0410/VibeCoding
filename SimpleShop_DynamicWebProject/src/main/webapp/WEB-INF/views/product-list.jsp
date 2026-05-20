<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codebyx.shop.entity.Product" %>
<%
    List<Product> products = (List<Product>) request.getAttribute("products");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>商品列表</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card">
        <h1>商品列表</h1>
        <p>此頁透過 ProductController → ProductDAO → MySQL 讀取商品資料，再由 JSP 顯示。</p>
    </div>
    <div class="grid">
        <% if (products != null) { for (Product p : products) {
            String imageSrc = p.getImageUrl();
            if (imageSrc != null && !imageSrc.startsWith("http")) {
                imageSrc = request.getContextPath() + "/" + imageSrc;
            }
        %>
            <div class="card product-card">
                <img src="<%= imageSrc %>" alt="<%= p.getName() %>">
                <div class="product-title"><%= p.getName() %></div>
                <p><%= p.getDescription() %></p>
                <p class="price">NT$ <%= p.getPrice() %></p>
                <p>庫存：<%= p.getStock() %></p>
                <form class="actions" action="<%= request.getContextPath() %>/cart/add" method="post">
                    <input type="hidden" name="productId" value="<%= p.getId() %>">
                    <input type="number" name="quantity" value="1" min="1" max="<%= p.getStock() %>">
                    <button class="btn" type="submit">加入購物車</button>
                </form>
            </div>
        <% }} %>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
