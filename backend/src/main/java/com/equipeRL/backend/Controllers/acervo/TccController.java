package com.equipeRL.backend.Controllers.acervo;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Controllers.propertyEditors.AutorPropertyEditor;
import com.equipeRL.backend.Controllers.propertyEditors.CidadePropertyEditor;
import com.equipeRL.backend.Controllers.propertyEditors.OrientadorPropertyEditor;
import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Models.Orientador;
import com.equipeRL.backend.Models.acervo.Tcc;
import com.equipeRL.backend.Models.enums.Tipo_tcc;
import com.equipeRL.backend.Services.acervo.TccService;
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
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Responsável por tratar as requisições e manipular as informações da entidade tcc 
 * 
 * @author Luis Lancellote
 * @author Rauny Henrique
 */
@RestController
@RequestMapping("${spring.data.rest.base-path}/tccs")
public class TccController implements ControllerCRUDInterface<Tcc> {

    @Autowired
    private TccService tccService;
        
    @Autowired
    private CidadePropertyEditor cidadePropertyEditor;
    
    @Autowired
    private AutorPropertyEditor autorPropertyEditor;
    
    @Autowired
    private OrientadorPropertyEditor orientadorPropertyEditor;

    /**
	 * Retorna uma lista com todos os tccs cadastrados
	 * 
	 * @return List<Tcc> tccs
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @GetMapping()
    public ResponseEntity<List<Tcc>> listAll() {

        try {

            List<Tcc> tccs = tccService.getAll();

            return new ResponseEntity<>(tccs, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
    
    /**
  	 * Retorna uma lista com os tipos de tcc
  	 * 
  	 * @return List<Tipo_tcc> tipos_tcc
  	 * @author Luis Lancellote
  	 * @author Rauny Henrique
  	 */
    @GetMapping("/tipos_de_tcc")
    public ResponseEntity<List<Tipo_tcc>> listAllTipos() {

        try {

            List<Tipo_tcc> tipos_tcc = Arrays.asList(Tipo_tcc.values());

            return new ResponseEntity<>(tipos_tcc, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
  	 * Valida e cadastra um objeto tipo Tcc recebido como parâmetro
  	 * 
  	 * @param Tcc model
  	 * @author Luis Lancellote
  	 * @author Rauny Henrique
  	 */
    @PostMapping()
    public ResponseEntity<?> create(@Valid Tcc tcc, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (tccService.isExist(tcc)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o tcc com título " +
                        tcc.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o tcc
            tccService.save(tcc);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/tccs/{id}").buildAndExpand(tcc.getId()).toUri());

            return new ResponseEntity<>(tcc, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
	 * Valida e altera um tcc cadastrado
	 * 
	 * @param long id, Tcc model
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Tcc tcc, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Tcc findTcc = tccService.findById(id);

            if (findTcc == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o tcc
            tccService.update(tcc);

            return new ResponseEntity<>(tcc, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    /**
	 * Exclui um tcc que possua o id recebido como parametro
	 * 
	 * @param long id
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        try {
            Tcc tcc = tccService.findById(id);
            if (tcc == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }
            //deleta item
            tccService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
	 * Registra o PropertyEditor para cidade, autor e orientador, transformando id's em entidades
	 * 
	 * @param WebDataBinder binder
	 * @author Luis Lancellote
	 * @author Rauny Henrique
	 */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Cidade.class, cidadePropertyEditor);
        binder.registerCustomEditor(Autor.class, autorPropertyEditor);
        binder.registerCustomEditor(Orientador.class, orientadorPropertyEditor);        
        binder.registerCustomEditor(Date.class, editor);
    }

}
