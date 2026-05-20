package com.codebyx.shop.controller;

import com.codebyx.shop.dao.ProductDAO;
import com.codebyx.shop.dao.impl.ProductDAOImpl;
import com.codebyx.shop.entity.CartItem;
import com.codebyx.shop.entity.Member;
import com.codebyx.shop.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class CartController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = getAction(request);
        try {
            switch (action) {
                case "/view" -> viewCart(request, response);
                case "/remove" -> removeItem(request, response);
                case "/checkout" -> checkout(request, response);
                default -> response.sendRedirect(request.getContextPath() + "/cart/view");
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
                case "/add" -> addItem(request, response);
                case "/update" -> updateItem(request, response);
                default -> response.sendRedirect(request.getContextPath() + "/cart/view");
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        return pathInfo == null ? "/view" : pathInfo;
    }

    @SuppressWarnings("unchecked")
    private Map<Integer, CartItem> getCart(HttpSession session) {
        Map<Integer, CartItem> cart = (Map<Integer, CartItem>) session.getAttribute("cart");
        if (cart == null) {
            cart = new LinkedHashMap<>();
            session.setAttribute("cart", cart);
        }
        return cart;
    }

    private void addItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = parseQuantity(request.getParameter("quantity"), 1);

        Product product = productDAO.findById(productId);
        if (product == null) {
            response.sendRedirect(request.getContextPath() + "/product/list");
            return;
        }

        Map<Integer, CartItem> cart = getCart(request.getSession());
        CartItem item = cart.get(productId);
        if (item == null) {
            cart.put(productId, new CartItem(product, quantity));
        } else {
            item.setQuantity(item.getQuantity() + quantity);
        }
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }

    private void viewCart(HttpServletRequest request, HttpServletResponse response) throws Exception {
        getCart(request.getSession());
        request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
    }

    private void updateItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        int quantity = parseQuantity(request.getParameter("quantity"), 1);
        Map<Integer, CartItem> cart = getCart(request.getSession());

        if (cart.containsKey(productId)) {
            if (quantity <= 0) {
                cart.remove(productId);
            } else {
                cart.get(productId).setQuantity(quantity);
            }
        }
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }

    private void removeItem(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int productId = Integer.parseInt(request.getParameter("productId"));
        Map<Integer, CartItem> cart = getCart(request.getSession());
        cart.remove(productId);
        response.sendRedirect(request.getContextPath() + "/cart/view");
    }

    private void checkout(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginMember = (Member) request.getSession().getAttribute("loginMember");
        if (loginMember == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        request.getSession().removeAttribute("cart");
        request.setAttribute("title", "結帳完成");
        request.setAttribute("message", "本範例未串接金流，已示範完成購物車結帳流程並清空購物車。");
        request.getRequestDispatcher("/WEB-INF/views/message.jsp").forward(request, response);
    }

    private int parseQuantity(String value, int defaultValue) {
        try {
            int quantity = Integer.parseInt(value);
            return quantity < 1 ? defaultValue : quantity;
        } catch (Exception e) {
            return defaultValue;
        }
    }
}
