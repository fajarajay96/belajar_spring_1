/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.fajar.siswa.entitiy;

/**
 *
 * @author Fajar
 */
public class Jurusan {
    
    protected int id_jurusan;
    private String jurusan;
    

    public Jurusan() {

    }

    public Jurusan(int id_jurusan, String jurusan) {
        this.id_jurusan = id_jurusan;
        this.jurusan = jurusan;
    }

    public int getIdJurusan() {
        return id_jurusan;
    }

    public void setIdJurusan(int id_jurusan) {
        this.id_jurusan = id_jurusan;
    }

    public String getJurusan() {
        return jurusan;
    }

    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }
    
}
