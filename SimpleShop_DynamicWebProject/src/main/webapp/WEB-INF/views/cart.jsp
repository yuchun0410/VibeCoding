<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.util.Map" %>
<%@ page import="com.codebyx.shop.entity.CartItem" %>
<%
    Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
    BigDecimal total = BigDecimal.ZERO;
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>購物車</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card">
        <h1>購物車</h1>
        <% if (cart == null || cart.isEmpty()) { %>
            <div class="alert alert-info">購物車目前沒有商品。</div>
            <a class="btn" href="<%= request.getContextPath() %>/product/list">前往商品列表</a>
        <% } else { %>
            <table class="table">
                <thead>
                <tr>
                    <th>商品</th>
                    <th>單價</th>
                    <th>數量</th>
                    <th>小計</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <% for (CartItem item : cart.values()) {
                    total = total.add(item.getSubtotal());
                %>
                    <tr>
                        <td><%= item.getProduct().getName() %></td>
                        <td>NT$ <%= item.getProduct().getPrice() %></td>
                        <td>
                            <form class="actions" action="<%= request.getContextPath() %>/cart/update" method="post">
                                <input type="hidden" name="productId" value="<%= item.getProduct().getId() %>">
                                <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1">
                                <button class="btn btn-warning" type="submit">更新</button>
                            </form>
                        </td>
                        <td>NT$ <%= item.getSubtotal() %></td>
                        <td>
                            <a class="btn btn-danger" href="<%= request.getContextPath() %>/cart/remove?productId=<%= item.getProduct().getId() %>">移除</a>
                        </td>
                    </tr>
                <% } %>
                </tbody>
            </table>
            <h2>總金額：NT$ <%= total %></h2>
            <div class="actions">
                <a class="btn btn-secondary" href="<%= request.getContextPath() %>/product/list">繼續購物</a>
                <a class="btn" href="<%= request.getContextPath() %>/cart/checkout">結帳</a>
            </div>
        <% } %>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
