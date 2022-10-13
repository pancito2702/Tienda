package com.tienda.services;

import com.tienda.dao.ClienteDao;
import com.tienda.domain.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImpl implements ClienteService {

    /* Se implementan los 4 metodos de un CRUD
    Create Read Update Delete mediante ClienteDao */

    // Se utiliza una anotacion Autoriwed para que el objeto 
    // clienteDao si ya esta en memoria se use ese...sino se crea(Singleton) 
    @Autowired
    private ClienteDao ClienteDao;

    // Retorna la lista de clientes
    @Override
    @Transactional(readOnly = true)
    public List<Cliente> getClientes() {
        return (List<Cliente>) ClienteDao.findAll();
    }

    /* Dado un cliente.id se busca en 
    la tabla y se retorna todo el objeto cliente */
    @Override
    @Transactional(readOnly = true)
    public Cliente getCliente(Cliente cliente) {
        return ClienteDao.findById(cliente.getIdCliente()).orElse(null);
    }

    /* Si cliente.id tiene un valor se busca y se actualiza
    Si el cliente.id no tiene un valor, se inserta el valor en la tabla */
    @Override
    @Transactional
    public void save(Cliente cliente) {
        ClienteDao.save(cliente);
    }

    // Elimina el registro que tiene el ide igual a cliente.id
    @Override
    @Transactional
    public void delete(Cliente cliente) {
        ClienteDao.delete(cliente);
    }

}
