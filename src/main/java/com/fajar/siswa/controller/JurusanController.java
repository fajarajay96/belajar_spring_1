/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.controller;


import com.fajar.siswa.dao.JurusanDAO;
import com.fajar.siswa.entitiy.Jurusan;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Fajar
 */
@Controller
public class JurusanController {

    @Autowired
    private JurusanDAO jurusanDAO;

//    @GetMapping(path = "/jurusan")
//    public String list() {
//        return "list";
//    }

    @GetMapping(path = "/jurusan")
    public String viewData(Model model, @RequestParam(value = "search", required = false) String param,
            @RequestParam(value = "filter", required = false) String param1) {
        if (param == null && param1 == null) {
            model.addAttribute("dataSets", jurusanDAO.findAll());
        } else {
            Jurusan jurusan = new Jurusan();
            jurusan.setJurusan(param);
            model.addAttribute("dataSets", jurusanDAO.findByJurusan(jurusan));
        }
        return "/jurusan/list";
    }

    @GetMapping(path = "/jurusan/create")
    public String viewCreate(Model model) {
        model.addAttribute("dataSets", new Jurusan());
        return "/jurusan/create";
    }

    @PostMapping(value = "/jurusan/create")
    public String save(Jurusan param) {
        Jurusan data = jurusanDAO.save(param);
        if (data.getIdJurusan()== 0) {
            return "redirect:/jurusan/create?failed";
        } else {
            return "redirect:/jurusan/create?success";
        }
    }
    
    @GetMapping(path = "/jurusan/update/{id_jurusan}")
    public String viewUpdate(Model model, @PathVariable(value = "id_jurusan") int id_jurusan) {
        model.addAttribute("dataSets", jurusanDAO.findById(id_jurusan));
        return "/jurusan/update";
    }
    
    @PutMapping(path = "/jurusan/update")
    public String update(Jurusan param) {
        Jurusan data = jurusanDAO.update(param);
        if (data.getIdJurusan()== 0) {
            return "redirect:/jurusan?ufailed";
        } else {
            return "redirect:/jurusan?usuccess";
        }
    }
    
    @DeleteMapping(path = "/jurusan/delete")
    public String delete(Jurusan param) {
        int data = jurusanDAO.delete(param);
        if (data == 0) {
            return "redirect:/jurusan?dfailed";
        } else {
            return "redirect:/jurusan?dsuccess";
        }
    }
}
