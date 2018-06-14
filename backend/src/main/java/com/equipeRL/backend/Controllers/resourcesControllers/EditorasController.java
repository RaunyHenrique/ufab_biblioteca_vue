package com.equipeRL.backend.Controllers.resourcesControllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Models.Editora;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import com.equipeRL.backend.Services.resourcesServices.EditoraService;

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
 * Responsável por tratar as requisições e manipular as informações da entidade
 * editora
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/editoras")
public class EditorasController implements ControllerCRUDInterface<Editora> {

	@Autowired
	private EditoraService editoraService;

	/**
	 * Retorna uma lista com todos as editoras cadastrados
	 * 
	 * @return List<Editora> editoras
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	@GetMapping()
	public ResponseEntity<List<Editora>> listAll() {

		try {

			List<Editora> editoras = editoraService.getAll();

			return new ResponseEntity<>(editoras, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

	}

	/**
	 * Valida e cadastra um objeto tipo Editora recebido como parâmetro
	 * 
	 * @param Editora model
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
	@PostMapping()
	public ResponseEntity<?> create(@Valid Editora model, BindingResult result, UriComponentsBuilder ucBuilder) {

		try {

			// valida campos
			if (result.hasErrors()) {
				return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// verifica se já esta cadastrado
			if (editoraService.isExist(model)) {
				return new ResponseEntity(new CustomErrorType(
						"Não é possivel cadastrar a área com nome " + model.getNome() + " pois já está cadastrado."),
						HttpStatus.CONFLICT);
			}

			// salva a editora
			editoraService.save(model);

			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/api/editoras/{id}").buildAndExpand(model.getId()).toUri());

			return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

		} catch (Exception e) {

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

	}

	/**
   	 * Valida e altera uma editora cadastrada
   	 * 
   	 * @param long id, Editora model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Editora model, BindingResult result) {

		try {

			// valida campos
			if (result.hasErrors()) {
				return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
			}

			// Verifica se está cadastrado
			Editora findEditora = editoraService.findById(id);

			if (findEditora == null) {
				return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
						HttpStatus.NOT_FOUND);
			}

			// atualiza a editora
			editoraService.update(model);

			return new ResponseEntity<>(model, HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

	}

	/**
   	 * Exclui uma editora que possua o id recebido como parametro
   	 * 
   	 * @param long id
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {

		try {

			Editora editora = editoraService.findById(id);

			if (editora == null) {
				return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
						HttpStatus.NOT_FOUND);
			}

			// deleta item
			editoraService.deleteById(id);

			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {

			return new ResponseEntity(HttpStatus.BAD_REQUEST);

		}

	}
}
