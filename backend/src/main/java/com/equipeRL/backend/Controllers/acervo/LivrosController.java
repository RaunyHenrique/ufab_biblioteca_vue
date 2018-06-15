package com.equipeRL.backend.Controllers.acervo;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Controllers.propertyEditors.EditoraPropertyEditor;
import com.equipeRL.backend.Controllers.propertyEditors.TemaPropertyEditor;
import com.equipeRL.backend.Models.Editora;
import com.equipeRL.backend.Models.Tema;
import com.equipeRL.backend.Models.acervo.Livro;
import com.equipeRL.backend.Services.acervo.LivroService;
import com.equipeRL.backend.Services.exceptions.CustomErrorType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Responsável por tratar as requisições e manipular as informações da entidade Livro 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/livros")
public class LivrosController implements ControllerCRUDInterface<Livro> {

    @Autowired
    private LivroService livroService;

    @Autowired
    private EditoraPropertyEditor editoraPropertyEditor;
    
    @Autowired
    private TemaPropertyEditor temaPropertyEditor;
    
    /**
	 * Retorna uma lista com todos os livros cadastrados
	 * 
	 * @return List<Livro> livros
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @GetMapping()
    public ResponseEntity<List<Livro>> listAll() {

        try {

            List<Livro> livros = livroService.getAll();

            return new ResponseEntity<>(livros, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
   	 * Valida e cadastra um objeto tipo livro recebido como parâmetro
   	 * 
   	 * @param Livro model
   	 * @author Luis Lancellote
   	 * @author Rauny Henrique
   	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Livro livro, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (livroService.isExist(livro)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o livro com título " +
                        livro.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            livroService.save(livro);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/livros/{id}").buildAndExpand(livro.getId()).toUri());

            return new ResponseEntity<>(livro, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
	 * Valida e altera um livro cadastrado
	 * 
	 * @param long id, Livro model
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Livro livro, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Livro findLivro = livroService.findById(id);

            if (findLivro== null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o livro
            livroService.update(livro);

            return new ResponseEntity<>(livro, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
 	 * Exclui um livro que possua o id recebido como parametro
 	 * 
 	 * @param long id
 	 * @author Luis Lancellote
 	 * @author Rauny Henrique
 	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Livro livro = livroService.findById(id);

            if (livro == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            livroService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }
    }

    /**
	 * Registra o PropertyEditor para editora e tema, transformando id's em entidades de
	 * editora e tema
	 * 
	 * @param WebDataBinder binder
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Editora.class, editoraPropertyEditor);
        binder.registerCustomEditor(Tema.class, temaPropertyEditor);
        binder.registerCustomEditor(Date.class, editor);
    }

    
}
