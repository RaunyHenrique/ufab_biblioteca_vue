package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.Orientador;
import com.equipeRL.backend.Repositories.OrientadoresRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class OrientadorPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private OrientadoresRepository orientadoresRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idOrientador = Long.parseLong(text);

        Optional<Orientador> orientador = orientadoresRepository.findById(idOrientador);
        //seta o objeto
        setValue(orientador.get());

    }

}
