package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Services.resourcesServices.AreaConhecimentoService;
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
@RequestMapping("${spring.data.rest.base-path}/areas_de_conhecimento")
public class AreaConhecimentoController implements ControllerCRUDInterface<AreaConhecimento> {

    @Autowired
    private AreaConhecimentoService areaConhecimentoService;

    @GetMapping()
    public ResponseEntity<List<AreaConhecimento>> listAll() {

        try {

            List<AreaConhecimento> areasConhecimento = areaConhecimentoService.getAll();

            return new ResponseEntity<>(areasConhecimento, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid AreaConhecimento model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (areaConhecimentoService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a área com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva a area
            areaConhecimentoService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/areas_de_conhecimento/{id}").buildAndExpand(model.getId()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid AreaConhecimento model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            AreaConhecimento findAreaConhecimento = areaConhecimentoService.findById(id);

            if (findAreaConhecimento == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza a area
            areaConhecimentoService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            AreaConhecimento areaConhecimento = areaConhecimentoService.findById(id);

            if (areaConhecimento == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            areaConhecimentoService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
}
