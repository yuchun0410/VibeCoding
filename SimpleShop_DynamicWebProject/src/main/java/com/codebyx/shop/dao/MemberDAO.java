package com.codebyx.shop.dao;

import java.util.List;

import com.codebyx.shop.entity.Member;

public interface MemberDAO {
    Member login(String username, String passwordHash) throws Exception;

    List<Member> findAll() throws Exception;

    Member findById(int id) throws Exception;

    boolean existsByUsername(String username) throws Exception;

    void create(Member member) throws Exception;

    void update(Member member) throws Exception;

    void updateWithoutPassword(Member member) throws Exception;

    void delete(int id) throws Exception;
}
