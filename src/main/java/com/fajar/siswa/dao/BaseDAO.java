/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fajar.siswa.dao;

import java.util.List;

/**
 *
 * @author Fajar
 */
interface BaseDAO<T> {
    T save(T param);

    T update(T param);

    int delete(T param);

    T findById(int id);

    List<T> findAll();
}
