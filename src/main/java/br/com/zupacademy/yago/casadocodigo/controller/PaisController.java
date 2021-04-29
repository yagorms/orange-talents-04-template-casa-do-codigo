package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.PaisForm;
import br.com.zupacademy.yago.casadocodigo.orm.Pais;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/pais")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    @Transactional
    public String cadastrar (@RequestBody @Valid PaisForm form){
        Pais pais = form.converter(paisRepository);
        paisRepository.save(pais);
        return pais.toString();
    }
}
