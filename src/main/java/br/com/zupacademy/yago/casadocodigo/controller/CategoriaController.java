package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.CategoriaForm;
import br.com.zupacademy.yago.casadocodigo.orm.Categoria;
import br.com.zupacademy.yago.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.yago.casadocodigo.validator.ProibeNomeDuplicadoCategoriaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private ProibeNomeDuplicadoCategoriaValidator proibeNomeDuplicadoCategoriaValidator;

    @InitBinder
    public void init(WebDataBinder binder) { binder.addValidators(proibeNomeDuplicadoCategoriaValidator); }


    @PostMapping
    @Transactional
    public String cadastrar (@RequestBody @Valid CategoriaForm form){
        Categoria categoria = form.converter(categoriaRepository);
        categoriaRepository.save(categoria);
        return categoria.toString();
    }
}
