package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Controllers.interfaces.ControllerCRUDInterface;
import com.equipeRL.backend.Controllers.propertyEditors.CursoPropertyEditor;
import com.equipeRL.backend.Models.Aluno;
import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Models.enums.Tipo_nivel;
import com.equipeRL.backend.Services.AlunoService;
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
@RequestMapping("${spring.data.rest.base-path}/alunos")
public class AlunosController implements ControllerCRUDInterface<Aluno> {

    @Autowired
    private AlunoService alunoService;

    @Autowired
    private CursoPropertyEditor cursoPropertyEditor;

    @GetMapping()
    public ResponseEntity<List<Aluno>> listAll() {

        try {

            List<Aluno> alunos = alunoService.getAll();

            return new ResponseEntity<>(alunos, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/tipos_de_niveis")
    public ResponseEntity<List<Tipo_nivel>> listAllNiveis() {

        try {

            List<Tipo_nivel> tipos_nivel = Arrays.asList(Tipo_nivel.values());

            return new ResponseEntity<>(tipos_nivel, HttpStatus.OK);

        }catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping()
    public ResponseEntity<?> create(@Valid Aluno model, BindingResult result, UriComponentsBuilder ucBuilder) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //verifica se já esta cadastrado
            if (alunoService.isExist(model)) {
                return new ResponseEntity(new CustomErrorType("Não é possivel cadastrar o aluno com nome " +
                        model.getNome() + " pois já está cadastrado."), HttpStatus.CONFLICT);
            }

            //salva o aluno
            model.gerarMatricula();
            alunoService.save(model);

            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/api/alunos/{id}").buildAndExpand(model.getId()).toUri());

            return new ResponseEntity<>(model, headers, HttpStatus.CREATED);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(long id, @Valid Aluno model, BindingResult result) {

        try {

            //valida campos
            if(result.hasErrors()) {
                return new ResponseEntity(result.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
            }

            //Verifica se está cadastrado
            Aluno findAluno = alunoService.findById(id);

            if (findAluno == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //atualiza o aluno
            alunoService.update(model);

            return new ResponseEntity<>(model, HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(long id) {

        try {

            Aluno aluno = alunoService.findById(id);

            if (aluno == null) {
                return new ResponseEntity(new CustomErrorType("Item de id = " + id + " não encontrado."),
                        HttpStatus.NOT_FOUND);
            }

            //deleta item
            alunoService.deleteById(id);

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {

            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        }

    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
        binder.registerCustomEditor(Curso.class, cursoPropertyEditor);
        binder.registerCustomEditor(Date.class, editor);
    }

}
