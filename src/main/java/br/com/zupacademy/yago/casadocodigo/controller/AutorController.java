package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.AutorForm;
import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public String cadastrar (@RequestBody @Valid AutorForm form){
        Autor autor = form.converter(autorRepository);
        autorRepository.save(autor);
        return autor.toString();

    }
}
