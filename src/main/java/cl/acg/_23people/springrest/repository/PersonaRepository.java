package cl.acg._23people.springrest.repository;

import cl.acg._23people.springrest.model.Persona;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Andres Cruz
 */

public interface PersonaRepository extends CrudRepository<Persona, Long>{
    
}
