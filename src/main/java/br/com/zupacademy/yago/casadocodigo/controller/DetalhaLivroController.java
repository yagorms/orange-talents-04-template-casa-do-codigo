package br.com.zupacademy.yago.casadocodigo.controller;

import br.com.zupacademy.yago.casadocodigo.dto.DetalhaLivroDTO;
import br.com.zupacademy.yago.casadocodigo.orm.Livro;
import br.com.zupacademy.yago.casadocodigo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class DetalhaLivroController {


    @Autowired
    private LivroRepository livroRepository;

    @GetMapping("/{id}")
    public ResponseEntity<DetalhaLivroDTO> detalhar (@PathVariable Long id){
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()){
            return ResponseEntity.ok(new DetalhaLivroDTO(livro.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
