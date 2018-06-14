package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.Autor;
import com.equipeRL.backend.Repositories.AutoresRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class AutorPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private AutoresRepository autoresRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idAutor = Long.parseLong(text);

        Optional<Autor> autor = autoresRepository.findById(idAutor);
        //seta o objeto
        setValue(autor.get());

    }

}
