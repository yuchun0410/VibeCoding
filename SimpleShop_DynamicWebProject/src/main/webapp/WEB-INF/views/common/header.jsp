<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.codebyx.shop.entity.Member" %>
<%
    String ctx = request.getContextPath();
    Member headerMember = (Member) session.getAttribute("loginMember");
%>
<div class="navbar">
    <div>
        <a class="brand" href="<%= ctx %>/product/list">SimpleShop</a>
        <a href="<%= ctx %>/product/list">商品列表</a>
        <a href="<%= ctx %>/cart/view">購物車</a>
        <a href="<%= ctx %>/member/list">會員管理</a>
    </div>
    <div>
        <% if (headerMember == null) { %>
            <a href="<%= ctx %>/login">登入</a>
        <% } else { %>
            <span>Hi, <%= headerMember.getName() %>（<%= headerMember.getRole() %>）</span>
            <a href="<%= ctx %>/logout">登出</a>
        <% } %>
    </div>
</div>
