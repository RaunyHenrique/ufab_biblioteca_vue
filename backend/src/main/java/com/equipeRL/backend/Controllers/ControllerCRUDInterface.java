package com.equipeRL.backend.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

public interface ControllerCRUDInterface<T> {

    @GetMapping()
    ResponseEntity<List<T>> listAll();

    @PostMapping()
    ResponseEntity<?> create(@Valid T model, BindingResult result, UriComponentsBuilder ucBuilder);

    @PutMapping("/{id}")
    ResponseEntity<?> update(@PathVariable("id") long id, @Valid T model, BindingResult result);

    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") long id);

}
