package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.acervo.MidiasEletronicas;
import com.equipeRL.backend.Models.enums.Tipo_curso;
import com.equipeRL.backend.Models.enums.Tipo_midia;
import com.equipeRL.backend.Services.MidiaService;
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
@RequestMapping("${spring.data.rest.base-path}/midias")
public class MidiasController implements ControllerCRUDInterface<MidiasEletronicas> {

    @Autowired
    private MidiaService midiaService;

    @GetMapping()
    public ResponseEntity<List<MidiasEletronicas>> listAll() {

        try {

            List<MidiasEletronicas> midias = midiaService.getAll();

            return new ResponseEntity<>(midias, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
    
    @GetMapping("/tipos_de_midias")
    public ResponseEntity<List<Tipo_midia>> listAllTipos() {

        try {

            List<Tipo_midia> tipos_midia = Arrays.asList(Tipo_midia.values());

            return new ResponseEntity<>(tipos_midia, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }


    @PostMapping()
    public ResponseEntity<?> create(@Valid MidiasEletronicas midia, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (midiaService.isExist(midia)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a midia com título " +
                        midia.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o jornal
            midiaService.save(midia);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/midias/{id}").buildAndExpand(midia.getId()).toUri());

            return new ResponseEntity<>(midia, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid MidiasEletronicas midia, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            MidiasEletronicas findMidia = midiaService.findById(id);

            if (findMidia == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o curso
            midiaService.update(midia);

            return new ResponseEntity<>(midia, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            MidiasEletronicas midia = midiaService.findById(id);

            if (midia == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            midiaService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

}
