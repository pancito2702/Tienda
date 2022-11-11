package com.tienda.controller;

import com.tienda.services.ArticuloService;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class IndexController {

    @Autowired
    private ArticuloService ArticuloService;

    @GetMapping("/")
    public String inicio(Model model) {
        List articulos = ArticuloService.getArticulos(false);
        model.addAttribute("articulos", articulos);
        return "index";
    }

}
