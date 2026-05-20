package com.codebyx.shop.controller;

import com.codebyx.shop.dao.MemberDAO;
import com.codebyx.shop.dao.impl.MemberDAOImpl;
import com.codebyx.shop.entity.Member;
import com.codebyx.shop.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            Member member = memberDAO.login(username, PasswordUtil.sha256(password));
            if (member == null) {
                request.setAttribute("error", "帳號或密碼錯誤");
                request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            response.sendRedirect(request.getContextPath() + "/product/list");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
