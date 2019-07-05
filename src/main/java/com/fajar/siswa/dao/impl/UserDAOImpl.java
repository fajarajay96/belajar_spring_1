/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fajar.siswa.dao.impl;

import com.fajar.siswa.dao.UserDAO;
import com.fajar.siswa.entitiy.User;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fajar
 */
@Repository
public class UserDAOImpl implements UserDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public List<User> findByUsername(User param) {
        String sql = "select * from user where username like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getUsername() + "%"}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public User save(User param) {
        String sql = "insert into user (username,password) values (?,?)";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getUsername());
            ps.setString(2, param.getPassword());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public User update(User param) {
        String sql = "update user set username=?,password=? where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getUsername());
            ps.setString(2, param.getPassword());
            ps.setInt(3, param.getId());
            return ps;
        });
        param.setId(rtn);
        return param;
    }

    @Override
    public int delete(User param) {
        String sql = "delete from user where id=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getId());
            return ps;
        });
        return rtn;
    }

    @Override
    public User findById(int id) {
        String sql = "select * from user where id=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> findAll() {
        String sql = "select * from user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> login(User param) {
        String sql = "select * from user where username=? and password=?";
        return jdbcTemplate.query(sql, new Object[]{param.getUsername(),param.getPassword()}, new BeanPropertyRowMapper<>(User.class));
    }

    
}
