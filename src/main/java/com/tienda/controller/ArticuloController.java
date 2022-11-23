package com.tienda.controller;

import com.tienda.domain.Articulo;
import com.tienda.services.ArticuloService;
import com.tienda.services.CategoriaService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ArticuloController {

    @Autowired
    private ArticuloService articuloService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/articulo/listado")
    public String listado(Model model) {

        List articulos = articuloService.getArticulos(false);

        model.addAttribute("articulos", articulos);

        return "/articulo/listado";
    }

    @GetMapping("/articulo/nuevo")
    public String nuevoArticulo(Model model, Articulo Articulo) {
        List categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        return "/articulo/modificar";
    }

    @PostMapping("/articulo/guardar")
    public String guardarArticulo(Articulo Articulo) {
        articuloService.save(Articulo);
        return "redirect:/articulo/listado";
    }

    @GetMapping("/articulo/modificar/{idArticulo}")
    public String modificarArticulo(Articulo articulo, Model model) {
        articulo = articuloService.getArticulo(articulo);
        List categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);
        model.addAttribute("articulo", articulo);
        return "/articulo/modificar";
    }

    @GetMapping("/articulo/eliminar/{idArticulo}")
    public String eliminarrArticulo(Articulo articulo) {
        articuloService.delete(articulo);
        return "redirect:/articulo/listado";
    }

}
