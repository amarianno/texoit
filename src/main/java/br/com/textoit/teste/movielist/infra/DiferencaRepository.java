package br.com.textoit.teste.movielist.infra;

import br.com.textoit.teste.movielist.domain.DiferencaPremio;
import br.com.textoit.teste.movielist.domain.IntervaloPremiacaoPort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiferencaRepository extends CrudRepository<Filme, Long>, IntervaloPremiacaoPort {

    @Query(
            nativeQuery = true,
            value = "SELECT \n" +
                    "ff.producers as nome, \n" +
                    "(select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) as maximo,\n" +
                    "(select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) as minimo,\n" +
                    "((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) ) as diferenca,\n" +
                    "FROM filme ff\n" +
                    "WHERE ((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) )  > 0\n" +
                    "GROUP BY producers\n" +
                    "ORDER BY ((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) ) DESC, (select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) DESC\n" +
                    "LIMIT 2"
    )
    List<DiferencaPremio> obterMaioresDiferencas();

    @Query(
            nativeQuery = true,
            value = "SELECT \n" +
                    "ff.producers as nome, \n" +
                    "(select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) as maximo,\n" +
                    "(select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) as minimo,\n" +
                    "((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) ) as diferenca,\n" +
                    "FROM filme ff\n" +
                    "WHERE ((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) )  > 0\n" +
                    "GROUP BY producers\n" +
                    "ORDER BY ((select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) - (select min(ano) as ano from filme where producers = ff.producers AND vencedor = true) ) ASC, (select max(ano) as ano from filme where producers = ff.producers AND vencedor = true) DESC\n" +
                    "LIMIT 2"
    )
    List<DiferencaPremio> obterMenoresDiferencasOrdenadasPorAno();
}
