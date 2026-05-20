package com.codebyx.shop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.codebyx.shop.dao.MemberDAO;
import com.codebyx.shop.entity.Member;
import com.codebyx.shop.util.DBUtil;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public Member login(String username, String passwordHash) throws Exception {
        String sql = "SELECT id, username, password_hash, name, email, role, created_at FROM members WHERE username = ? AND password_hash = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, passwordHash);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Member> findAll() throws Exception {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT id, username, password_hash, name, email, role, created_at FROM members ORDER BY id DESC";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                members.add(mapRow(rs));
            }
        }
        return members;
    }

    @Override
    public Member findById(int id) throws Exception {
        String sql = "SELECT id, username, password_hash, name, email, role, created_at FROM members WHERE id = ?";
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

    @Override
    public boolean existsByUsername(String username) throws Exception {
        String sql = "SELECT COUNT(*) FROM members WHERE username = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    @Override
    public void create(Member member) throws Exception {
        String sql = "INSERT INTO members(username, password_hash, name, email, role) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getPasswordHash());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());
            ps.setString(5, member.getRole());
            ps.executeUpdate();
        }
    }

    @Override
    public void update(Member member) throws Exception {
        String sql = "UPDATE members SET username = ?, password_hash = ?, name = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getPasswordHash());
            ps.setString(3, member.getName());
            ps.setString(4, member.getEmail());
            ps.setString(5, member.getRole());
            ps.setInt(6, member.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void updateWithoutPassword(Member member) throws Exception {
        String sql = "UPDATE members SET username = ?, name = ?, email = ?, role = ? WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getName());
            ps.setString(3, member.getEmail());
            ps.setString(4, member.getRole());
            ps.setInt(5, member.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM members WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    private Member mapRow(ResultSet rs) throws Exception {
        return new Member(
            rs.getInt("id"),
            rs.getString("username"),
            rs.getString("password_hash"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("role"),
            rs.getTimestamp("created_at")
        );
    }
}
