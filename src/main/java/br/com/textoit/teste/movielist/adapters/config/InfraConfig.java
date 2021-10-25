package br.com.textoit.teste.movielist.adapters.config;

import br.com.textoit.teste.movielist.domain.IntervaloPremiacaoPort;
import br.com.textoit.teste.movielist.domain.PreencherListaFilmesPort;
import br.com.textoit.teste.movielist.infra.DiferencaRepository;
import br.com.textoit.teste.movielist.infra.PreencherListaFilmesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class InfraConfig {

    @Autowired
    DiferencaRepository diferencaRepository;

    @Autowired
    PreencherListaFilmesImpl preencherListaFilmes;

    @Primary
    @Bean
    public IntervaloPremiacaoPort intervaloPremiacaoPort() {
        return diferencaRepository;
    }

    @Primary
    @Bean
    public PreencherListaFilmesPort preencherListaFilmesPort() {
        return preencherListaFilmes;
    }

}
