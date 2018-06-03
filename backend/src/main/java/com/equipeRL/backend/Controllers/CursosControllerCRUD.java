package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Services.CursosService;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("${spring.data.rest.base-path}/cursos")
public class CursosControllerCRUD implements ControllerCRUDInterface<Curso> {

    @Autowired
    private CursosService cursosService;

    @GetMapping()
    public ResponseEntity<List<Curso>> listAll() {

        try {

            List<Curso> users = cursosService.getAll();

            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid Curso curso, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //validas campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (cursosService.isExist(curso)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o curso com nome " +
                        curso.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o curso
            cursosService.save(curso);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/cursos/{id}").buildAndExpand(curso.getId()).toUri());

            return new ResponseEntity<String>(headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Curso curso, BindingResult result) {

        try {

            //validas campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Curso findCurso = cursosService.findById(id);

            if (findCurso == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o curso
            cursosService.update(curso);

            return new ResponseEntity<>(curso, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Curso curso = cursosService.findById(id);

            if (curso == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            cursosService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

}
