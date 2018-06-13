package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.acervo.Revista;
import com.equipeRL.backend.Services.RevistaService;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}/jornais")
public class RevistasController implements ControllerCRUDInterface<Revista> {

    @Autowired
    private RevistaService revistaService;

    @GetMapping()
    public ResponseEntity<List<Revista>> listAll() {

        try {

            List<Revista> revistas = revistaService.getAll();

            return new ResponseEntity<>(revistas, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid Revista revista, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (revistaService.isExist(revista)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a revista com título " +
                        revista.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            revistaService.save(revista);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/revistas/{id}").buildAndExpand(revista.getId()).toUri());

            return new ResponseEntity<>(revista, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Revista revista, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Revista findRevista = revistaService.findById(id);

            if (findRevista == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o curso
            revistaService.update(revista);

            return new ResponseEntity<>(revista, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Revista revista = revistaService.findById(id);

            if (revista == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            revistaService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

}
