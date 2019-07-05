/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.dao.impl;

import com.fajar.siswa.dao.JurusanDAO;
import com.fajar.siswa.entitiy.Jurusan;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Fajar
 */
@Repository
public class JurusanDAOImpl implements JurusanDAO{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    @Override
    public Jurusan save(Jurusan param) {
        String sql = "insert into jurusan (jurusan) values (?)";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getJurusan());
            return ps;
        });
        param.setIdJurusan(rtn);
        return param;
    }

    @Override
    public Jurusan update(Jurusan param) {
        String sql = "update jurusan set jurusan=? where id_jurusan=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getJurusan());
            ps.setInt(2, param.getIdJurusan());
            return ps;
        });
        param.setIdJurusan(rtn);
        return param;
    }

    @Override
    public int delete(Jurusan param) {
        String sql = "delete from jurusan where id_jurusan=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getIdJurusan());
            return ps;
        });
        return rtn;
    }

    @Override
    public Jurusan findById(int id_jurusan) {
        String sql = "select * from jurusan where id_jurusan=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_jurusan}, new BeanPropertyRowMapper<>(Jurusan.class));
    }

    @Override
    public List<Jurusan> findAll() {
        String sql = "select * from jurusan";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Jurusan.class));
    }

    @Override
    public List<Jurusan> findByJurusan(Jurusan param) {
        String sql = "select * from jurusan where jurusan like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getJurusan()+ "%"}, new BeanPropertyRowMapper<>(Jurusan.class));
    }
    
}
