/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fajar.siswa.controller;

import com.fajar.siswa.dao.UserDAO;
import com.fajar.siswa.entitiy.User;
import java.util.List;
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
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @GetMapping(path = "/home")
    public String bebas() {
        return "index";
    }

    @GetMapping(path = "/")
    public String login() {
        return "/user/login";
    }

    @PostMapping(value = "/login")
    public String login(User param) {
        List<User> data = userDAO.login(param);
        if (data.size() <= 0) {
            return "redirect:/?failed";
        } else {
            return "redirect:/home";
        } 
    }

    @GetMapping(path = "/user")
    public String viewData(Model model, @RequestParam(value = "search", required = false) String param,
            @RequestParam(value = "filter", required = false) String param1) {
        if (param == null && param1 == null) {
            model.addAttribute("dataSets", userDAO.findAll());
        } else {
            User user = new User();
            user.setUsername(param);
            model.addAttribute("dataSets", userDAO.findByUsername(user));
        }
        return "/user/list";
    }

    @GetMapping(path = "/user/create")
    public String viewCreate(Model model) {
        model.addAttribute("dataSets", new User());
        return "/user/create";
    }

    @PostMapping(value = "/user/create")
    public String save(User param) {
        User data = userDAO.save(param);
        if (data.getId() == 0) {
            return "redirect:/user/create?failed";
        } else {
            return "redirect:/user/create?success";
        }
    }
    
    @GetMapping(path = "/user/update/{id}")
    public String viewUpdate(Model model, @PathVariable(value = "id") int id) {
        model.addAttribute("dataSets", userDAO.findById(id));
        return "/user/update";
    }
    
    @PutMapping(path = "/user/update")
    public String update(User param) {
        User data = userDAO.update(param);
        if (data.getId() == 0) {
            return "redirect:/user?ufailed";
        } else {
            return "redirect:/user?usuccess";
        }
    }
    
    @DeleteMapping(path = "/user/delete")
    public String delete(User param) {
        int data = userDAO.delete(param);
        if (data == 0) {
            return "redirect:/user?dfailed";
        } else {
            return "redirect:/user?dsuccess";
        }
    }
}
