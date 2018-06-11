package com.equipeRL.backend.Controllers.propertyEditors;

import com.equipeRL.backend.Models.Permissao;
import com.equipeRL.backend.Repositories.PermissoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.PropertyEditorSupport;
import java.util.Optional;

@Component
public class PermissaoPropertyEditor extends PropertyEditorSupport {

    @Autowired
    private PermissoesRepository permissoesRepository;

    @Override
    public void setAsText(String text) throws IllegalArgumentException {

        long idPermissao = Long.parseLong(text);

        Optional<Permissao> permissao = permissoesRepository.findById(idPermissao);
        //seta o objeto
        setValue(permissao.get());

    }

}
