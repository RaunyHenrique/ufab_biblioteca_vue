package com.equipeRL.backend.Controllers.propertyEditors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.equipeRL.backend.Models.AreaConhecimento;
import com.equipeRL.backend.Repositories.AreasConhecimentoRepository;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class AreaConhecimentoPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private AreasConhecimentoRepository areasConhecimentoRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idArea = Long.parseLong(text);

        Optional<AreaConhecimento> area = areasConhecimentoRepository.findById(idArea);
        //seta o objeto
        setValue(area.get());

    }

}
