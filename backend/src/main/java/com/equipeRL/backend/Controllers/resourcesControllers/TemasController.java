package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Controllers.propertyEditors.AreaConhecimentoPropertyEditor;
import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Models.Permissao;
import com.equipeRL.backend.Models.Tema;
import com.equipeRL.backend.Services.resourcesServices.TemaService;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

/**
 * Responsável por tratar as requisições e manipular as informações da entidade tema 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/temas")
public class TemasController implements ControllerCRUDInterface<Tema> {

    @Autowired
    private TemaService temaService;

    @Autowired
    private AreaConhecimentoPropertyEditor areaConhecimentoPropertyEditor;
    
    /**
   	 * Retorna uma lista com todos os temas cadastrados
   	 * 
   	 * @return temas
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @GetMapping()
    public ResponseEntity<List<Tema>> listAll() {

        try {

            List<Tema> temas = temaService.getAll();

            return new ResponseEntity<>(temas, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e cadastra um objeto tipo tema recebido como parâmetro
   	 * 
   	 * @param Tema model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Tema model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (temaService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a área com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva a area
            temaService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/temas/{id}").buildAndExpand(model.getId()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e altera um tema cadastrado
   	 * 
   	 * @param long id
   	 * @param Cidade model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Tema model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Tema findTema = temaService.findById(id);

            if (findTema == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza a area
            temaService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Tema tema = temaService.findById(id);

            if (tema == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            temaService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(AreaConhecimento.class, areaConhecimentoPropertyEditor);
    }

    
}
