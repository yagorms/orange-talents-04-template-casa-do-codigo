package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.dto.LivroDTO;
import br.com.zupacademy.yago.casadocodigo.orm.Livro;
import br.com.zupacademy.yago.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lista-livro")
public class ListaLivroController {

    @Autowired
    private LivroRepository livroRepository;


    @GetMapping
    public List<LivroDTO> lista (){
        List<Livro> livros = livroRepository.findAll();
        return LivroDTO.converter(livros);
    }
}
