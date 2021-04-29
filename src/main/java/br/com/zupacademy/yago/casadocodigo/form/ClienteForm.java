package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Cliente;
import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import br.com.zupacademy.yago.casadocodigo.orm.Pais;
import br.com.zupacademy.yago.casadocodigo.repository.ClienteRepository;
import br.com.zupacademy.yago.casadocodigo.repository.EstadoRepository;
import br.com.zupacademy.yago.casadocodigo.repository.PaisRepository;
import br.com.zupacademy.yago.casadocodigo.validator.CPForCNPJ;
import br.com.zupacademy.yago.casadocodigo.validator.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Optional;

public class ClienteForm {

    @Email @NotBlank @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank @CPForCNPJ
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotNull
    private Long idPais;
    private Long idEstado;
    @Size(min = 10, max = 11)
    private String telefone;
    @Size(min = 8, max = 8)
    private String cep;

    @Deprecated
    public ClienteForm() {
    }

    public ClienteForm(@Email @NotBlank String email, @NotBlank String nome,
                       @NotBlank String sobrenome, @NotBlank String documento,
                       @NotBlank String endereco, @NotBlank String complemento,
                       @NotBlank String cidade, @NotBlank Long idPais, Long idEstado,
                       @Size(min = 10, max = 11) String telefone, @Size(min = 8, max = 8) String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.telefone = telefone;
        this.cep = cep;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }


    public Cliente converter(EstadoRepository estadoRepository, PaisRepository paisRepository) {
        Optional<Pais> pais = paisRepository.findById(idPais);
        Cliente cliente = new Cliente(email, nome, sobrenome, documento, endereco, complemento, cidade, pais.get(), telefone, cep);
        if (idEstado !=null){
            Optional<Estado> estado = estadoRepository.findById(idEstado);
            cliente.setEstado(estado.get());
        }
        return cliente;
    }
}
