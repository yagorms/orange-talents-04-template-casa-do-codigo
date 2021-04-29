package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.EstadoForm;
import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.yago.casadocodigo.validator.ProibeEstadoDuplicadoNoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/estado")
public class EstadoController{

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private ProibeEstadoDuplicadoNoPaisValidator proibeEstadoDuplicadoNoPaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeEstadoDuplicadoNoPaisValidator);}

    @PostMapping
    @Transactional
    private ResponseEntity<String> cadastrar (@RequestBody @Valid EstadoForm form){
        Estado estado = (form.converter(estadoRepository, paisRepository));
        if (estado != null) {
           estadoRepository.save(estado);
           return ResponseEntity.ok(estado.toString());
        }
        else return ResponseEntity.badRequest().build();
    }
}
