package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import com.equipeRL.backend.Services.resourcesServices.CidadeService;

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
 * Responsável por tratar as requisições e manipular as informações da entidade cidade 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/cidades")
public class CidadesController implements ControllerCRUDInterface<Cidade> {

    @Autowired
    private CidadeService cidadeService;

    /**
	 * Retorna uma lista com todos as cidades cadastrados
	 * 
	 * @return List<Cidade> cidades
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @GetMapping()
    public ResponseEntity<List<Cidade>> listAll() {

        try {

            List<Cidade> cidades = cidadeService.getAll();

            return new ResponseEntity<>(cidades, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
     * Retorna uma lista com todos as cidades de acordo com a busca
     *
     * @return List<Cidade> cidades
     * @author Luis Lancellote
     * @author Rauny Henrique
     */
    @GetMapping("/buscar/{cidade}")
    public ResponseEntity<List<Cidade>> buscarCidade(@PathVariable("cidade") String cidade) {

        try {

            if (!cidade.isEmpty()) {

                List<Cidade> cidades = cidadeService.buscar(cidade);

                return new ResponseEntity<>(cidades, HttpStatus.OK);

            } else {

                return new ResponseEntity(HttpStatus.BAD_REQUEST);

            }

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
	 * Valida e cadastra um objeto tipo Cidade recebido como parâmetro
	 * 
	 * @param Cidade model
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Cidade model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (cidadeService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar a cidade com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva a cidade
            cidadeService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/cidades/{id}").buildAndExpand(model.getCod_cidades()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e altera uma cidade cadastrada
   	 * 
   	 * @param long id
   	 * @param Cidade model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Cidade model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Cidade findCidade = cidadeService.findById(id);

            if (findCidade == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza a cidade
            cidadeService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Exclui um tema que possua o id recebido como parametro
   	 * 
   	 * @param long id
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Cidade cidade = cidadeService.findById(id);

            if (cidade == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            cidadeService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
}
