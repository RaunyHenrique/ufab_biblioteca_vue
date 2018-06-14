package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import com.equipeRL.backend.Services.resourcesServices.AutorService;

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
 * Responsável por tratar as requisições e manipular as informações da entidade autor 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/autores")
public class AutoresController implements ControllerCRUDInterface<Autor> {

    @Autowired
    private AutorService autorService;

    /**
	 * Retorna uma lista com todos os autores cadastrados
	 * 
	 * @return List<Autor> autores
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @GetMapping()
    public ResponseEntity<List<Autor>> listAll() {

        try {

            List<Autor> autores = autorService.getAll();

            return new ResponseEntity<>(autores, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e cadastra um objeto tipo Autor recebido como parâmetro
   	 * 
   	 * @param Autor model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Autor model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (autorService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o autor com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva autor
            autorService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/autores/{id}").buildAndExpand(model.getId()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e altera um autor cadastrado
   	 * 
   	 * @param long id, Autor model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Autor model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Autor findAutor = autorService.findById(id);

            if (findAutor == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza a area
            autorService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
  	 * Exclui um autor que possua o id recebido como parametro
  	 * 
  	 * @param long id
  	 * @author Luis Lancellote
  	 * @author Rauny Henrique
  	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Autor autor = autorService.findById(id);

            if (autor == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            autorService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
}
