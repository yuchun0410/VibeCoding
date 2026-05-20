package com.codebyx.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.codebyx.shop.dao.ProductDAO;
import com.codebyx.shop.entity.Product;
import com.codebyx.shop.util.DBUtil;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public List<Product> findAll() throws Exception {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT id, name, description, price, stock, image_url FROM products ORDER BY id";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                products.add(mapRow(rs));
            }
        }
        return products;
    }

    @Override
    public Product findById(int id) throws Exception {
        String sql = "SELECT id, name, description, price, stock, image_url FROM products WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    private Product mapRow(ResultSet rs) throws Exception {
        return new Product(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getBigDecimal("price"),
            rs.getInt("stock"),
            rs.getString("image_url")
        );
    }
}
