/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.dao;


import com.fajar.siswa.entitiy.Kelas;
import java.util.List;

/**
 *
 * @author Fajar
 */
public interface KelasDAO extends BaseDAO<Kelas>{
    List<Kelas> findByKelas(Kelas param);
}
