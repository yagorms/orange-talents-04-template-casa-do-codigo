package br.com.zupacademy.yago.casadocodigo.repository;

import br.com.zupacademy.yago.casadocodigo.orm.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {

    Optional<Estado> findByNomeAndPaisId(String nome, Long idPais);

    List<Estado> findByPaisId(Long idPais);

    Optional<Estado> findByIdAndPaisId(Long idEstado, Long idPais);
}
