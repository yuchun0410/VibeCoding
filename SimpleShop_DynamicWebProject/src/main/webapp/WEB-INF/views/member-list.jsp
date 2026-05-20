<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.codebyx.shop.entity.Member" %>
<%
    List<Member> members = (List<Member>) request.getAttribute("members");
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title>會員管理</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card">
        <div class="actions" style="justify-content: space-between;">
            <h1>會員管理 CRUD</h1>
            <a class="btn" href="<%= request.getContextPath() %>/member/new">新增會員</a>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>帳號</th>
                <th>姓名</th>
                <th>Email</th>
                <th>角色</th>
                <th>建立時間</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody>
            <% if (members != null) { for (Member m : members) { %>
                <tr>
                    <td><%= m.getId() %></td>
                    <td><%= m.getUsername() %></td>
                    <td><%= m.getName() %></td>
                    <td><%= m.getEmail() %></td>
                    <td><%= m.getRole() %></td>
                    <td><%= m.getCreatedAt() %></td>
                    <td class="actions">
                        <a class="btn btn-warning" href="<%= request.getContextPath() %>/member/edit?id=<%= m.getId() %>">修改</a>
                        <a class="btn btn-danger" href="<%= request.getContextPath() %>/member/delete?id=<%= m.getId() %>" onclick="return confirm('確定刪除此會員？')">刪除</a>
                    </td>
                </tr>
            <% }} %>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
