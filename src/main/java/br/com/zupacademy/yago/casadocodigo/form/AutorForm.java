package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorForm {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    @Length(max = 400)
    private String descricao;

    public AutorForm (@NotBlank String nome,
                      @NotBlank @Email String email,
                      @NotBlank @Length(max = 400) String descricao){
            super();
            this.nome = nome;
            this.email = email;
            this.descricao = descricao;
    }

    public Autor converter(AutorRepository autorRepository) {

        return new Autor(nome, email, descricao);
    }

    public String getEmail() {
        return this.email;
    }
}
