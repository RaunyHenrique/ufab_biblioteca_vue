package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.Orientador;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import com.equipeRL.backend.Services.resourcesServices.OrientadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Responsável por tratar as requisições e manipular as informações da entidade orientador 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/orientadores")
public class OrientadoresController implements ControllerCRUDInterface<Orientador> {

    @Autowired
    private OrientadorService orientadorService;

    /**
   	 * Retorna uma lista com todos as cidades cadastrados
   	 * 
   	 * @return List<Orientador> orientadores
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @GetMapping()
    public ResponseEntity<List<Orientador>> listAll() {

        try {

            List<Orientador> areasConhecimento = orientadorService.getAll();

            return new ResponseEntity<>(areasConhecimento, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
	 * Valida e cadastra um objeto tipo Orientador recebido como parâmetro
	 * 
	 * @param Orientador model
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Orientador model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (orientadorService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a área com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva a area
            orientadorService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/orientadores/{id}").buildAndExpand(model.getId()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e altera um orientador cadastrado
   	 * 
   	 * @param long id, Orientador model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Orientador model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Orientador findOrientador = orientadorService.findById(id);

            if (findOrientador == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza a area
            orientadorService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Exclui um orientador que possua o id recebido como parametro
   	 * 
   	 * @param long id
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Orientador orientador = orientadorService.findById(id);

            if (orientador == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            orientadorService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
}
