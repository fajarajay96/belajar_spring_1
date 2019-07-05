/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.dao.impl;

import com.fajar.siswa.dao.KelasDAO;
import com.fajar.siswa.entitiy.Jurusan;
import com.fajar.siswa.entitiy.Kelas;
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
public class KelasDAOImpl implements KelasDAO{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Kelas> findByKelas(Kelas param) {
        String sql = "select * from kelas where kelas like ?";
        return jdbcTemplate.query(sql, new Object[]{"%" + param.getKelas()+ "%"}, new BeanPropertyRowMapper<>(Kelas.class));
    }

    @Override
    public Kelas save(Kelas param) {
        String sql = "insert into kelas (kelas,id_jurusan) values (?,?)";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getKelas());
            ps.setInt(2, param.getId_jurusan());
            return ps;
        });
        param.setId_kelas(rtn);
        return param;
    }

    @Override
    public Kelas update(Kelas param) {
        String sql = "update kelas set kelas=?,id_jurusan=? where id_kelas=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, param.getKelas());
            ps.setInt(2, param.getId_jurusan());
            ps.setInt(3, param.getId_kelas());
            return ps;
        });
        param.setId_kelas(rtn);
        return param;
    }

    @Override
    public int delete(Kelas param) {
        String sql = "delete from kelas where id_kelas=?";
        int rtn = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, param.getId_kelas());
            return ps;
        });
        return rtn;
    }

    @Override
    public Kelas findById(int id_kelas) {
        String sql = "select kelas.*, jurusan.jurusan from kelas inner join jurusan on jurusan.id_jurusan=kelas.id_jurusan where kelas.id_kelas=?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id_kelas}, new BeanPropertyRowMapper<>(Kelas.class));
    }

    @Override
    public List<Kelas> findAll() {
        String sql = "select kelas.*, jurusan.jurusan from kelas inner join jurusan on jurusan.id_jurusan=kelas.id_jurusan";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Kelas.class));
    }
    
}
