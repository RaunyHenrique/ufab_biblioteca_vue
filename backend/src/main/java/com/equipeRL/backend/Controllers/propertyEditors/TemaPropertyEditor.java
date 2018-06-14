package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.Tema;
import com.equipeRL.backend.Repositories.TemasRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class TemaPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private TemasRepository temasRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idTema = Long.parseLong(text);

        Optional<Tema> tema = temasRepository.findById(idTema);
        //seta o objeto
        setValue(tema.get());

    }

}
