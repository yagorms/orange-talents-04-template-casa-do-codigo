package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.form.LivroForm;
import br.com.zupacademy.yago.casadocodigo.orm.Livro;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.yago.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.yago.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private AutorRepository autorRepository;


    @PostMapping
    @Transactional
    private String cadastrar (@RequestBody @Valid LivroForm form){
        Livro livro = form.converter(livroRepository, categoriaRepository, autorRepository);
        livroRepository.save(livro);
        return livro.toString();
    }
}
