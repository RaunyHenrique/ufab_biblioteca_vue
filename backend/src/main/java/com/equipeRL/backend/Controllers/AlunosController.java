package com.equipeRL.backend.Controllers;

import com.equipeRL.backend.Models.Aluno;
import com.equipeRL.backend.Models.enums.Tipo_nivel;
import com.equipeRL.backend.Repositories.CursosRepository;
import com.equipeRL.backend.Services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

//    @Autowired
//    private GrupoService cadastroGrupoService;

    @Autowired
    private CursosRepository cursos;

    /**
     * Esse método é responsável por adicionar os parâmetros que vão ser exibidos na view renderizada ao acessar a rota alunos/novo
     * @param aluno, que é o objeto a ser acessado
     * @return mv, que é um objeto ModelAndView que contém os parâmetros que foram adicionados para exibir na view.
     */

//    @GetMapping("/")
//    public ResponseEntity<List<Aluno>> listarTodos() {
//
//        try {
//
////            ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
////            mv.addObject("cursos", cursos.findAll());
////            mv.addObject("niveis", Tipo_nivel.values());
////        mv.addObject("grupos", cadastroGrupoService.buscaAluno());
//
//            List<Aluno> allAlunos = alunoService.getAllAlunos();
//
//            return new ResponseEntity<>(allAlunos, HttpStatus.OK);
//
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//
//    }

}
