package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Categoria;
import br.com.zupacademy.yago.casadocodigo.repository.CategoriaRepository;

import javax.validation.constraints.NotBlank;

public class CategoriaForm {

    @NotBlank
    private String nome;

    @Deprecated
    public CategoriaForm(){

    }

    public CategoriaForm(@NotBlank String nome) {
        super();
        this.nome = nome;
    }

    public Categoria converter(CategoriaRepository categoriaRepository) {

        return new Categoria(nome);
    }

    public String getNome() {
        return this.nome;
    }
}
