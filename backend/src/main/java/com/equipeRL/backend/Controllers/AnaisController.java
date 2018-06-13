package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Controllers.propertyEditors.CidadePropertyEditor;
import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Models.acervo.Anal;
import com.equipeRL.backend.Models.enums.Tipo_anal;
import com.equipeRL.backend.Services.AnaisService;
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

@RestController
@RequestMapping("${spring.data.rest.base-path}/anais")
public class AnaisController implements ControllerCRUDInterface<Anal> {

    @Autowired
    private AnaisService anaisService;
    
    @Autowired
    private CidadePropertyEditor cidadePropertyEditor;

    @GetMapping()
    public ResponseEntity<List<Anal>> listAll() {

        try {

            List<Anal> anais = anaisService.getAll();

            return new ResponseEntity<>(anais, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
    
    @GetMapping("/tipos_de_anais")
    public ResponseEntity<List<Tipo_anal>> listAllTipos() {

        try {

            List<Tipo_anal> tipos_anais = Arrays.asList(Tipo_anal.values());

            return new ResponseEntity<>(tipos_anais, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }


    @PostMapping()
    public ResponseEntity<?> create(@Valid Anal anal, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (anaisService.isExist(anal)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o anal com título " +
                        anal.getTitulo()+ " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o jornal
            anaisService.save(anal);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/anais/{id}").buildAndExpand(anal.getId()).toUri());

            return new ResponseEntity<>(anal, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") long id, @Valid Anal anal, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Anal findAnal = anaisService.findById(id);

            if (findAnal == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o anal
            anaisService.update(anal);

            return new ResponseEntity<>(anal, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {

        try {

            Anal anal = anaisService.findById(id);

            if (anal == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            anaisService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Cidade.class, cidadePropertyEditor);
        binder.registerCustomEditor(Date.class, editor);
    }

}
