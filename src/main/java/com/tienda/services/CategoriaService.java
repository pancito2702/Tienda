
package com.tienda.services;

import com.tienda.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    /* Se definen los 4 metodos de un CRUD
    Create Read Update Delete */
    
    // Retorna la lista de categorias
    public List<Categoria> getCategorias(boolean activos);
    
    /* Dado un categoria.id se busca en 
    la tabla y se retorna todo el objeto categoria */
    public Categoria getCategoria(Categoria categoria);
    
    /* Si categoria.id tiene un valor se busca y se actualiza
    Si el categoria.id no tiene un valor, se inserta el valor en la tabla */
    public void save(Categoria categoria);
    
    // Elimina el registro que tiene el ide igual a categoria.id
    public void delete(Categoria categoria);
    
}