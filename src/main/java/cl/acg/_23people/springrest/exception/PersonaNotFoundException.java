package cl.acg._23people.springrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Andres Cruz
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Persona not found.")
public class PersonaNotFoundException extends RuntimeException{

}
