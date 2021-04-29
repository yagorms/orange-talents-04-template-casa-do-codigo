package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import br.com.zupacademy.yago.casadocodigo.orm.Pais;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class EstadoForm {

    @NotBlank
    private String nome;
    @NotNull
    private Long idPais;

    @Deprecated
    public EstadoForm() {
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }



    public EstadoForm(@NotBlank String nome, @NotNull Long idPais) {
        this.nome = nome;
        this.idPais = idPais;
    }

    public Estado converter(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        Optional<Pais> pais = paisRepository.findById(idPais);
        if (pais.isPresent()){
            Estado estado = new Estado(nome, pais.get());
            return estado;
        }
        return null;
    }
}
