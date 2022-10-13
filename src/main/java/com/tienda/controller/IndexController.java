package com.tienda.controller;

import com.tienda.services.ClienteService;
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
    private ClienteService ClienteService;

    @GetMapping("/")
    public String inicio(Model model) {
        String texto = "Estamos en semana 4";
        model.addAttribute("mensaje", texto);
        List clientes = ClienteService.getClientes();

        model.addAttribute("clientes", clientes);

        return "index";
    }

}
