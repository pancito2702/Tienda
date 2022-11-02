package com.tienda.services;

import com.tienda.dao.ArticuloDao;
import com.tienda.domain.Articulo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ArticuloServiceImpl implements ArticuloService {

    /* Se implementan los 4 metodos de un CRUD
    Create Read Update Delete mediante ArticuloDao */

    // Se utiliza una anotacion Autoriwed para que el objeto 
    // articuloDao si ya esta en memoria se use ese...sino se crea(Singleton) 
    @Autowired
    private ArticuloDao ArticuloDao;

    // Retorna la lista de articulos
    @Override
    @Transactional(readOnly = true)
    public List<Articulo> getArticulos(boolean activos) {
        var lista = (List<Articulo>) ArticuloDao.findAll();
        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    /* Dado un articulo.id se busca en 
    la tabla y se retorna todo el objeto articulo */
    @Override
    @Transactional(readOnly = true)
    public Articulo getArticulo(Articulo articulo) {
        return ArticuloDao.findById(articulo.getIdArticulo()).orElse(null);
    }

    /* Si articulo.id tiene un valor se busca y se actualiza
    Si el articulo.id no tiene un valor, se inserta el valor en la tabla */
    @Override
    @Transactional
    public void save(Articulo articulo) {
        ArticuloDao.save(articulo);
    }

    // Elimina el registro que tiene el ide igual a articulo.id
    @Override
    @Transactional
    public void delete(Articulo articulo) {
        ArticuloDao.delete(articulo);
    }

}
