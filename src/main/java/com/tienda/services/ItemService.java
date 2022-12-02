
package com.tienda.services;

import com.tienda.domain.Item;
import java.util.ArrayList;
import java.util.List;

public interface ItemService {
    /* Se definen los 4 metodos de un CRUD
    Create Read Update Delete */
    
    // Em lugar de una tabla de BD se usa una ArrayList para registro temporal
    public List<Item> listaItems = new ArrayList<>();
    
    
    // Retorna la lista de items
    public List<Item> getItems();
    
    /* Dado un item.id se busca en 
    la tabla y se retorna todo el objeto item */
    public Item getItem(Item item);
    
    /* Si item.id tiene un valor se busca y se actualiza
    Si el item.id no tiene un valor, se inserta el valor en la tabla */
    public void save(Item item);
    
    // Elimina el registro que tiene el ide igual a item.id
    public void delete(Item item);
    
    public void actualizar(Item item);
    
    public void facturar();
    
}