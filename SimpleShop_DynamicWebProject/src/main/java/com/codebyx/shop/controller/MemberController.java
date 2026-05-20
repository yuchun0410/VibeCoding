package com.codebyx.shop.controller;

import com.codebyx.shop.dao.MemberDAO;
import com.codebyx.shop.dao.impl.MemberDAOImpl;
import com.codebyx.shop.entity.Member;
import com.codebyx.shop.util.PasswordUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final MemberDAO memberDAO = new MemberDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = getAction(request);

        try {
            switch (action) {
                case "/new" -> showCreateForm(request, response);
                case "/edit" -> showEditForm(request, response);
                case "/delete" -> delete(request, response);
                case "/list" -> list(request, response);
                default -> response.sendRedirect(request.getContextPath() + "/member/list");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = getAction(request);

        try {
            switch (action) {
                case "/create" -> create(request, response);
                case "/update" -> update(request, response);
                default -> response.sendRedirect(request.getContextPath() + "/member/list");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        return pathInfo == null ? "/list" : pathInfo;
    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("members", memberDAO.findAll());
        request.getRequestDispatcher("/WEB-INF/views/member-list.jsp").forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("mode", "create");
        request.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        request.setAttribute("mode", "edit");
        request.setAttribute("member", memberDAO.findById(id));
        request.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (memberDAO.existsByUsername(username)) {
            request.setAttribute("mode", "create");
            request.setAttribute("error", "帳號已存在，請更換帳號");
            request.getRequestDispatcher("/WEB-INF/views/member-form.jsp").forward(request, response);
            return;
        }

        Member member = new Member();
        member.setUsername(username);
        member.setPasswordHash(PasswordUtil.sha256(password));
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setRole(defaultIfBlank(request.getParameter("role"), "USER"));
        memberDAO.create(member);

        response.sendRedirect(request.getContextPath() + "/member/list");
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member member = new Member();
        member.setId(Integer.parseInt(request.getParameter("id")));
        member.setUsername(request.getParameter("username"));
        member.setName(request.getParameter("name"));
        member.setEmail(request.getParameter("email"));
        member.setRole(defaultIfBlank(request.getParameter("role"), "USER"));

        String password = request.getParameter("password");
        if (password == null || password.isBlank()) {
            memberDAO.updateWithoutPassword(member);
        } else {
            member.setPasswordHash(PasswordUtil.sha256(password));
            memberDAO.update(member);
        }

        response.sendRedirect(request.getContextPath() + "/member/list");
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        memberDAO.delete(id);
        response.sendRedirect(request.getContextPath() + "/member/list");
    }

    private String defaultIfBlank(String value, String defaultValue) {
        return value == null || value.isBlank() ? defaultValue : value;
    }
}
