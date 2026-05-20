<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.codebyx.shop.entity.Member" %>
<%
    String mode = (String) request.getAttribute("mode");
    boolean isEdit = "edit".equals(mode);
    Member member = (Member) request.getAttribute("member");
    if (member == null) {
        member = new Member();
    }
    String action = isEdit ? "/member/update" : "/member/create";
%>
<!DOCTYPE html>
<html lang="zh-Hant">
<head>
    <meta charset="UTF-8">
    <title><%= isEdit ? "修改會員" : "新增會員" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/css/style.css">
</head>
<body>
<jsp:include page="common/header.jsp" />
<div class="container">
    <div class="card" style="max-width: 680px;">
        <h1><%= isEdit ? "修改會員" : "新增會員" %></h1>
        <% if (request.getAttribute("error") != null) { %>
            <div class="alert alert-error"><%= request.getAttribute("error") %></div>
        <% } %>
        <form action="<%= request.getContextPath() %><%= action %>" method="post">
            <% if (isEdit) { %>
                <input type="hidden" name="id" value="<%= member.getId() %>">
            <% } %>
            <div class="form-row">
                <label>帳號</label>
                <input type="text" name="username" value="<%= member.getUsername() == null ? "" : member.getUsername() %>" required>
            </div>
            <div class="form-row">
                <label>密碼 <%= isEdit ? "（不修改請留空）" : "" %></label>
                <input type="password" name="password" <%= isEdit ? "" : "required" %>>
            </div>
            <div class="form-row">
                <label>姓名</label>
                <input type="text" name="name" value="<%= member.getName() == null ? "" : member.getName() %>" required>
            </div>
            <div class="form-row">
                <label>Email</label>
                <input type="email" name="email" value="<%= member.getEmail() == null ? "" : member.getEmail() %>" required>
            </div>
            <div class="form-row">
                <label>角色</label>
                <select name="role">
                    <option value="USER" <%= "USER".equals(member.getRole()) ? "selected" : "" %>>USER</option>
                    <option value="ADMIN" <%= "ADMIN".equals(member.getRole()) ? "selected" : "" %>>ADMIN</option>
                </select>
            </div>
            <button class="btn" type="submit">儲存</button>
            <a class="btn btn-secondary" href="<%= request.getContextPath() %>/member/list">返回</a>
        </form>
    </div>
</div>
<jsp:include page="common/footer.jsp" />
</body>
</html>
