package com.equipeRL.backend.Controllers.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

public interface ControllerCRUDInterface<T> {

    ResponseEntity<List<T>> listAll();

    ResponseEntity<?> create(@Valid T model, BindingResult result, UriComponentsBuilder ucBuilder);

    ResponseEntity<?> update(@PathVariable("id") long id, @Valid T model, BindingResult result);

    ResponseEntity<?> delete(@PathVariable("id") long id);

}
