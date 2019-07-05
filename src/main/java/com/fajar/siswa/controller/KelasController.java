/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.controller;

import com.fajar.siswa.dao.JurusanDAO;
import com.fajar.siswa.dao.KelasDAO;
import com.fajar.siswa.entitiy.Jurusan;
import com.fajar.siswa.entitiy.Kelas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fajar
 */
@Controller
public class KelasController {
    
    @Autowired
    private KelasDAO kelasDAO;
    
    @Autowired
    private JurusanDAO jurusanDAO;
    
    @GetMapping(path = "/kelas")
    public String viewData(Model model, @RequestParam(value = "search", required = false) String param,
            @RequestParam(value = "filter", required = false) String param1) {
        if (param == null && param1 == null) {
            model.addAttribute("dataSets", kelasDAO.findAll());
        } else {
            Kelas kelas = new Kelas();
            kelas.setKelas(param);
            model.addAttribute("dataSets", kelasDAO.findByKelas(kelas));
        }
        return "/kelas/list";
    }
    
    @GetMapping(path = "/kelas/create")
    public String viewCreate(Model model) {
        model.addAttribute("dataSets", new Kelas());
        model.addAttribute("dataSetsJurusan", jurusanDAO.findAll());
        return "/kelas/create";
    }

    @PostMapping(value = "/kelas/create")
    public String save(Kelas param) {
        Kelas data = kelasDAO.save(param);
        if (data.getId_kelas()== 0) {
            return "redirect:/kelas/create?failed";
        } else {
            return "redirect:/kelas/create?success";
        }
    }
    
    @DeleteMapping(path = "/kelas/delete")
    public String delete(Kelas param) {
        int data = kelasDAO.delete(param);
        if (data == 0) {
            return "redirect:/kelas?dfailed";
        } else {
            return "redirect:/kelas?dsuccess";
        }
    }
    
    @GetMapping(path = "/kelas/update/{id_kelas}")
    public String viewUpdate(Model model, @PathVariable(value = "id_kelas") int id_kelas) {
        model.addAttribute("dataSets", kelasDAO.findById(id_kelas));
        model.addAttribute("dataSetsJurusan", jurusanDAO.findAll());
        return "/kelas/update";
    }
    
    @PutMapping(path = "/kelas/update")
    public String update(Kelas param) {
        Kelas data = kelasDAO.update(param);
        if (data.getId_kelas()== 0) {
            return "redirect:/kelas?ufailed";
        } else {
            return "redirect:/kelas?usuccess";
        }
    }
    
}
