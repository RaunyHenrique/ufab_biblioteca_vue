package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.Editora;
import com.equipeRL.backend.Repositories.EditorasRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class EditoraPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private EditorasRepository editorasRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idEditora = Long.parseLong(text);

        Optional<Editora> editora = editorasRepository.findById(idEditora);
        //seta o objeto
        setValue(editora.get());

    }

}
