package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.Cidade;
import com.equipeRL.backend.Repositories.CidadesRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class CidadePropertyEditor extends PropertyEditorSupport {

    @Autowired
    private CidadesRepository cidadeRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idCidade = Long.parseLong(text);

        Optional<Cidade> cidade = cidadeRepository.findById(idCidade);
        //seta o objeto
        setValue(cidade.get());

    }

}
