package br.com.textoit.teste.movielist.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervaloFilmesService {

    @Autowired
    IntervaloPremiacaoPort intervaloPremiacaoPort;

    /**
     * Retorna os 2 MENORES intervalos entre as premiações
     * @return
     */
    public List<DiferencaPremio> obterMenoresDiferencasEntrePremiacoes() {
        return intervaloPremiacaoPort.obterMenoresDiferencasOrdenadasPorAno();
    }

    /**
     * Retorna os 2 MAIORES intervalos entre as premiações
     * @return
     */
    public List<DiferencaPremio> obterMaioresDiferencasEntrePremiacoes() {
        return intervaloPremiacaoPort.obterMaioresDiferencas();
    }
}
