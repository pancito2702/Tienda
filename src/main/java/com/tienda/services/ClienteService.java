package com.tienda.services;

import com.tienda.domain.Cliente;
import java.util.List;

public interface ClienteService {

    /* Se definen los 4 metodos de un CRUD
    Create Read Update Delete */

    // Retorna la lista de clientes
    public List<Cliente> getClientes();

    /* Dado un cliente.id se busca en 
    la tabla y se retorna todo el objeto cliente */
    public Cliente getCliente(Cliente cliente);

    /* Si cliente.id tiene un valor se busca y se actualiza
    Si el cliente.id no tiene un valor, se inserta el valor en la tabla */
    public void save(Cliente cliente);

    // Elimina el registro que tiene el ide igual a cliente.id
    public void delete(Cliente cliente);

    public List<Cliente> buscarPorApellidos(String apellidos);

}
