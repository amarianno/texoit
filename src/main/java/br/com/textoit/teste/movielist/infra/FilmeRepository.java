package br.com.textoit.teste.movielist.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmeRepository extends JpaRepository<FilmeEntity, Long> {
    List<FilmeEntity> findAByVencedor(boolean venceu);
}
