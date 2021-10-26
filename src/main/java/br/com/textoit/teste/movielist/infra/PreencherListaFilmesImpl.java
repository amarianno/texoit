package br.com.textoit.teste.movielist.infra;

import br.com.textoit.teste.movielist.domain.PreencherListaFilmesPort;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Component
public class PreencherListaFilmesImpl implements PreencherListaFilmesPort {

    @Value("classpath:movielist.csv")
    Resource listaFilmesCsv;

    @Autowired
    FilmeRepository filmeRepository;


    /**
     * Faz o parser entre do arquivo CSV transformando em uma lista de filmes
     * Que é inserida na base de dados em seguida
     *
     * @throws IOException
     */
    public void preencherListaFilmesAPartirDoCsv() throws IOException {

        try (CSVReader reader = new CSVReaderBuilder(new FileReader(listaFilmesCsv.getFile()))
                .withCSVParser(new CSVParserBuilder()
                        .withSeparator(';')
                        .withEscapeChar('\\')
                        .withStrictQuotes(false)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withIgnoreQuotations(false)
                        .withErrorLocale(Locale.getDefault())
                        .build())
                .build()) {

            List<String[]> todasLinhasCsv = reader.readAll();
            todasLinhasCsv.remove(0);
            todasLinhasCsv.forEach(linha -> {
                final List<Filme> filmes = obterFilmeAPartirDoCsv(linha);
                filmes.forEach( filme -> filmeRepository.save(filme));
            });

        } catch (IOException ioe) {
            throw  ioe;
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Transforma uma linha do CSV em um Filme
     *
     * @param dadosCsv
     * @return
     */
    private List<Filme> obterFilmeAPartirDoCsv(final String[] dadosCsv) {

        List<Filme> filmes = new ArrayList<>();
        List<String> nomesProducers = new ArrayList<>();

        String produces = dadosCsv[3];

        if (!produces.contains(",") && !produces.contains(" and ")) {
            nomesProducers.add(produces);
        }

        if (produces.contains(",")) {
            nomesProducers.addAll(Arrays.asList(produces.split(",")));
        }

        if (!produces.contains(",") && produces.contains(" and ")) {
            nomesProducers.addAll(Arrays.asList(produces.split(" and ")));
        }

        if (produces.contains(",") && produces.contains(" and ")) {
            nomesProducers.addAll(Arrays.asList(nomesProducers.get(1).split(" and ")));
            nomesProducers.remove(1);
        }

        nomesProducers.forEach( nome -> {
            Filme filme = new Filme();
            filme.setAno(Integer.parseInt(dadosCsv[0]));
            filme.setTitulo(dadosCsv[1]);
            filme.setProducers(nome);
            if ("yes".equals(dadosCsv[4])) {
                filme.setVencedor(true);
            }

            filmes.add(filme);
        });

        return filmes;
    }


}
