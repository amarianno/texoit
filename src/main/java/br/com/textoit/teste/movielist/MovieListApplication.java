package br.com.textoit.teste.movielist;

import br.com.textoit.teste.movielist.domain.PreencherListaFilmesPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MovieListApplication {

	@Autowired
	PreencherListaFilmesPort preencherListaFilmes;

	public static void main(String[] args) {
		SpringApplication.run(MovieListApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			preencherListaFilmes.preencherListaFilmesAPartirDoCsv();
		};
	}

}
