package com.tienda.controller;

import com.tienda.domain.Articulo;
import com.tienda.domain.Item;
import com.tienda.services.ArticuloService;
import com.tienda.services.ItemService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class CarritoController {
    
    @Autowired
    private ItemService itemService;
    @Autowired
    private ArticuloService articuloService;
    
    // Para ver el carrito de compras
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        List<Item> items = itemService.getItems();
        model.addAttribute("items", items);
        double carritoTotalVenta = 0;
        for (Item i: items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return "/carrito/listado";
    }
    
    @GetMapping("/carrito/agregar/{idArticulo}")
    public ModelAndView agregaItem(Model model, Item item) {
        Item item2 = itemService.getItem(item);
        if (item2 == null) {
            Articulo articulo = articuloService.getArticulo(item);
            item2 = new Item(articulo);
        }
        itemService.save(item2);
        var lista = itemService.getItems();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i: lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("listaTotal", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentosCarrito :: verCarrito");
    }
    
    @GetMapping("/carrito/modificar/{idArticulo}")
    public String modificarItem(Item item, Model model) {
        item = itemService.getItem(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }
    
    @GetMapping("/carrito/eliminar/{idArticulo}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }
    
    @PostMapping("/carrito/guardar") 
    public String guardarItem(Item item) {
        itemService.actualizar(item);
        return "redirect:/carrito/listado";
    }
    
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
    
}



