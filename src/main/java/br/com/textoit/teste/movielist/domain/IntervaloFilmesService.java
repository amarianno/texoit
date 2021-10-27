package br.com.textoit.teste.movielist.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IntervaloFilmesService {

    @Autowired
    IntervaloPremiacaoPort intervaloPremiacaoPort;

    /**
     * Retorna o MENOR intervalo entre as premiações
     * @return
     */
    public List<DiferencaPremio> obterMenoresDiferencasEntrePremiacoes() {
        return intervaloPremiacaoPort.obterMenoresDiferencasOrdenadasPorAno();
    }

    /**
     * Retorna o MAIOR intervalo entre as premiações
     * @return
     */
    public List<DiferencaPremio> obterMaioresDiferencasEntrePremiacoes() {
        return intervaloPremiacaoPort.obterMaioresDiferencas();
    }
}
