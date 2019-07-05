/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fajar.siswa.dao;

import com.fajar.siswa.entitiy.User;
import java.util.List;

/**
 *
 * @author Fajar
 */
public interface UserDAO extends BaseDAO<User>{
    
    List<User> findByUsername(User param);
    List<User> login(User param);
}
