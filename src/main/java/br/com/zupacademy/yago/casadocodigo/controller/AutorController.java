package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.AutorForm;
import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.yago.casadocodigo.validator.ProibeEmailDuplicadoAutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private ProibeEmailDuplicadoAutorValidator proibeEmailDuplicadoAutorValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibeEmailDuplicadoAutorValidator);
    }

    @PostMapping
    @Transactional
    public String cadastrar (@RequestBody @Valid AutorForm form){
        Autor autor = form.converter(autorRepository);
        autorRepository.save(autor);
        return autor.toString();

    }
}
