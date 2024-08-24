package ec.com.corebank.banquito.repositories;

import org.springframework.data.repository.CrudRepository;

import ec.com.corebank.banquito.models.entities.Cliente;

public interface ClienteRepository 
    extends CrudRepository<Cliente,String>{

}
