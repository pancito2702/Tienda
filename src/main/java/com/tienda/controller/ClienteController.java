package com.tienda.controller;

import com.tienda.domain.Cliente;
import com.tienda.services.ClienteService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ClienteController {

    @Autowired
    private ClienteService ClienteService;

    @GetMapping("/cliente/listado")
    public String listado(Model model) {

        List clientes = ClienteService.getClientes();

        model.addAttribute("clientes", clientes);

        return "/cliente/listado";
    }

    @GetMapping("/cliente/nuevo")
    public String nuevoCliente(Cliente cliente) {
        return "/cliente/modificar";
    }

    @PostMapping("/cliente/guardar")
    public String guardarCliente(Cliente cliente) {
        ClienteService.save(cliente);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/cliente/modificar/{idCliente}")
    public String modificarCliente(Cliente cliente, Model model) {
        cliente = ClienteService.getCliente(cliente);
        model.addAttribute("cliente", cliente);
        return "/cliente/modificar";
    }

    @GetMapping("/cliente/eliminar/{idCliente}")
    public String eliminarrCliente(Cliente cliente) {
        ClienteService.delete(cliente);
        return "redirect:/cliente/listado";
    }

    @GetMapping("/cliente/busqueda")
    public String buscarCliente() {
        return "/cliente/busqueda";
    }

    @PostMapping("/cliente/modificar")
    public String modificarClienteApellidos(@RequestParam("apellidos") String apellidos, Model model) {
        List Cliente = ClienteService.buscarPorApellidos(apellidos);
        model.addAllAttributes(Cliente);
        return "/cliente/modificar";
    }

}
