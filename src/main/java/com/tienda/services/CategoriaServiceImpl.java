package com.tienda.services;

import com.tienda.dao.CategoriaDao;
import com.tienda.domain.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    /* Se implementan los 4 metodos de un CRUD
    Create Read Update Delete mediante CategoriaDao */

    // Se utiliza una anotacion Autoriwed para que el objeto 
    // categoriaDao si ya esta en memoria se use ese...sino se crea(Singleton) 
    @Autowired
    private CategoriaDao CategoriaDao;

    // Retorna la lista de categorias
    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        var lista = (List<Categoria>) CategoriaDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    /* Dado un categoria.id se busca en 
    la tabla y se retorna todo el objeto categoria */
    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        return CategoriaDao.findById(categoria.getIdCategoria()).orElse(null);
    }

    /* Si categoria.id tiene un valor se busca y se actualiza
    Si el categoria.id no tiene un valor, se inserta el valor en la tabla */
    @Override
    @Transactional
    public void save(Categoria categoria) {
        CategoriaDao.save(categoria);
    }

    // Elimina el registro que tiene el ide igual a categoria.id
    @Override
    @Transactional
    public void delete(Categoria categoria) {
        CategoriaDao.delete(categoria);
    }

}
