package cl.acg._23people.springrest.controller;

import cl.acg._23people.springrest.exception.PersonaNotFoundException;
import cl.acg._23people.springrest.model.Persona;
import cl.acg._23people.springrest.repository.PersonaRepository;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Andres Cruz
 */
@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    
    @Autowired
    private PersonaRepository personaRepository;
    
    @GetMapping
    public Iterable<Persona> getPersonas(){
        return personaRepository.findAll();
    }
    
    @GetMapping("/{id:[\\d]+}")
    public Persona getPersona(@PathVariable("id") Long id){
        Optional<Persona> o = personaRepository.findById(id);
        if (o.isPresent())
            return o.get();
        else
            throw new PersonaNotFoundException();
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Persona addPersona(
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "apellido") String apellido){

        return personaRepository.save(new Persona(nombre, apellido));
    }
    @PostMapping("/{id:[\\d]+}") // HTTP Method Patch?
    public Persona updatePersona(
            @PathVariable("id") Long id,
            @RequestParam(name = "nombre", required = false) String nombre,
            @RequestParam(name = "apellido", required = false) String apellido){
        Optional<Persona> o = personaRepository.findById(id);
        Persona p;
        if (o.isPresent()){
            p = o.get();
            if(nombre != null)
                p.setNombre(nombre);
            if(apellido != null)
                p.setApellido(apellido);
            if(nombre == null && apellido == null)
                return p;
            else
                return personaRepository.save(p);
        }
        else
            throw new PersonaNotFoundException(); 
    }
    
    
    @PutMapping("/{id:[\\d]+}")
    public Persona replacePersona(
            @PathVariable("id") Long id,
            @RequestParam(name = "nombre") String nombre,
            @RequestParam(name = "apellido") String apellido){
        Optional<Persona> o = personaRepository.findById(id);
        Persona p;
        if (o.isPresent()){
            p = o.get();
            p.setNombre(nombre);
            p.setApellido(apellido);
            return personaRepository.save(p);
        }
        else
            throw new PersonaNotFoundException();  
    }
    
    @DeleteMapping("/{id:[\\d]+}") //alternativa: redirect:/
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersona(@PathVariable("id") Long id){
        Optional<Persona> o = personaRepository.findById(id);
        if (o.isPresent())
            personaRepository.deleteById(id);
        else
            throw new PersonaNotFoundException();        
        
        
    }
}
