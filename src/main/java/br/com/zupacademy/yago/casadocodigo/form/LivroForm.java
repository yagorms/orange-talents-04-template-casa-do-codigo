package br.com.zupacademy.yago.casadocodigo.form;

import br.com.zupacademy.yago.casadocodigo.orm.Autor;
import br.com.zupacademy.yago.casadocodigo.orm.Categoria;
import br.com.zupacademy.yago.casadocodigo.orm.Livro;
import br.com.zupacademy.yago.casadocodigo.repository.AutorRepository;
import br.com.zupacademy.yago.casadocodigo.repository.CategoriaRepository;
import br.com.zupacademy.yago.casadocodigo.repository.LivroRepository;
import br.com.zupacademy.yago.casadocodigo.validator.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public class LivroForm {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal preco;
    @NotNull
    @Min(100)
    private int numeroPaginas;
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @NotNull
    @Future
    private LocalDate dataPublicacao;
    @NotNull
    private Long idCategoria;
    @NotNull
    private Long idAutor;

    @Deprecated
    public LivroForm(){
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public LivroForm(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
                     @NotBlank String sumario, @NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroPaginas,
                     @NotBlank String isbn, @NotNull @Future LocalDate dataPublicacao, @NotNull Long idCategoria, @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    /*
    Criei esse setter pq o jackson não estava sendo capaz de desserializar o json com a data no parâmetro pelo construtor.
    */
    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }


    public Livro converter(LivroRepository livroRepository, CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(idCategoria);
        Optional<Autor> autor = autorRepository.findById(idAutor);
        if (categoria.isPresent() && autor.isPresent()){
            Livro livro = new Livro(titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria.get(), autor.get());
            return livro;
        }
        return null;
    }
}
