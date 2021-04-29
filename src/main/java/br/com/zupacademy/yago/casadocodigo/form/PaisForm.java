package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Pais;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.yago.casadocodigo.validator.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisForm {

    @NotBlank
    @UniqueValue(domainClass = Pais.class,fieldName = "nome")
    private String nome;

    @Deprecated
    public PaisForm() {
    }

    public PaisForm(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }


    public Pais converter(PaisRepository paisRepository) {
        return new Pais(nome);
    }
}
