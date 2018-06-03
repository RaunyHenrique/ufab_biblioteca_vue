package com.equipeRL.backend.Controllers.propertyEditors;

import com.equipeRL.backend.Models.Curso;
import com.equipeRL.backend.Repositories.CursosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class CursoPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CursosRepository cursosRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idCurso = Long.parseLong(text);

        Optional<Curso> curso = cursosRepository.findById(idCurso);
        //seta o objeto
        setValue(curso.get());

    }

}
