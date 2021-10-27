package br.com.textoit.teste.movielist.domain;

import java.util.List;

public interface IntervaloPremiacaoPort {
    List<DiferencaPremio> obterMaioresDiferencas();
    List<Filme> obterTodosVencedores();
    List<DiferencaPremio> obterMenoresDiferencasOrdenadasPorAno();
}
