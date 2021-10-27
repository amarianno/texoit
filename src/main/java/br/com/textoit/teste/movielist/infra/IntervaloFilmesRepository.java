package br.com.textoit.teste.movielist.infra;

import br.com.textoit.teste.movielist.domain.DiferencaPremio;
import br.com.textoit.teste.movielist.domain.Filme;
import br.com.textoit.teste.movielist.domain.IntervaloPremiacaoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class IntervaloFilmesRepository implements IntervaloPremiacaoPort {

    @Autowired
    FilmeRepository filmeRepository;

    @Autowired
    DiferencaRepository diferencaRepository;

    @Override
    public List<DiferencaPremio> obterMaioresDiferencas() {
        return diferencaRepository.obterMaioresDiferencas();
    }

    @Override
    public List<DiferencaPremio> obterMenoresDiferencasOrdenadasPorAno() {
        return diferencaRepository.obterMenoresDiferencasOrdenadasPorAno();
    }

    @Override
    public List<Filme> obterTodosVencedores() {
        final List<FilmeEntity> vencedores = filmeRepository.findAByVencedor(true);
        return vencedores.stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    /**
     * Converte o filme entidade do banco
     * em objeto do negocio
     *
     * @param filmeEntity
     * @return
     */
    private Filme toDomain(FilmeEntity filmeEntity) {
        Filme filme = new Filme();
        filme.setAno(filmeEntity.getAno());
        filme.setProducers(filmeEntity.getProducers().trim());
        filme.setTitulo(filmeEntity.getTitulo());
        filme.setVencedor(filmeEntity.isVencedor());

        return filme;
    }


}
