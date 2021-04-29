package br.com.zupacademy.yago.casadocodigo.repository;

import br.com.zupacademy.yago.casadocodigo.orm.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
