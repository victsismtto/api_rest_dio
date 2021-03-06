package one.digitalinnovation.personal.api.controller;

import lombok.AllArgsConstructor;
import one.digitalinnovation.personal.api.dto.request.PersonDTO;
import one.digitalinnovation.personal.api.dto.response.MessageResponseDTO;
import one.digitalinnovation.personal.api.exception.PersonNotFoundException;
import one.digitalinnovation.personal.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/people")    //classe fica com esse dominio (ou 'caminho')
@AllArgsConstructor(onConstructor = @__(@Autowired))        //automatic constructor
public class PersonController {

    private PersonService personService;

    //@GetMapping         recebe o 'caminho' da url denominado pela classe (@RequestMapping) e returna um valor
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return personService.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> listAll() {
        return personService.listAll();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable Long id) throws PersonNotFoundException {          //pathvarieble vc esta passando o id de uma pessoa -> throughout a http request
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable Long id, @RequestBody @Valid PersonDTO personDTO) throws PersonNotFoundException {      //Valid mapping from dto e authenticate by it
        return personService.updateById (id, personDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws PersonNotFoundException {
        personService.delete(id);
    }


}
