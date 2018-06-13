package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.acervo.Jornal;
import com.equipeRL.backend.Services.JornalService;
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
public class JornaisController implements ControllerCRUDInterface<Jornal> {

    @Autowired
    private JornalService jornalService;

    @GetMapping()
    public ResponseEntity<List<Jornal>> listAll() {

        try {

            List<Jornal> jornais = jornalService.getAll();

            return new ResponseEntity<>(jornais, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid Jornal jornal, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (jornalService.isExist(jornal)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o jornal com título " +
                        jornal.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o jornal
            jornalService.save(jornal);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/jornais/{id}").buildAndExpand(jornal.getId()).toUri());

            return new ResponseEntity<>(jornal, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Jornal jornal, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Jornal findJornal = jornalService.findById(id);

            if (findJornal == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o curso
            jornalService.update(jornal);

            return new ResponseEntity<>(jornal, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Jornal jornal = jornalService.findById(id);

            if (jornal == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            jornalService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

}
