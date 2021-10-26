package br.com.textoit.teste.movielist.infra;

import br.com.textoit.teste.movielist.domain.PreencherListaFilmesPort;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.input.CharSequenceReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
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

        Reader targetReader = new CharSequenceReader(new String(IOUtils.toByteArray(listaFilmesCsv.getInputStream())));

        try (CSVReader reader = new CSVReaderBuilder(targetReader)
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
            todasLinhasCsv.remove(0);//retira o cabeçalho
            todasLinhasCsv.forEach(linha -> {
                final List<Filme> filmes = obterFilmeAPartirDoCsv(linha);
                filmes.forEach( filme -> filmeRepository.save(filme));
            });

            targetReader.close();

        } catch (IOException ioe) {
            throw  ioe;
        } catch (Exception e) {
            e.getMessage();
        }

    }

    /**
     * Transforma uma linha do CSV em um Filme
     * Se o nome dos produtores vierem com virgula ou 'and'
     * Separo os nomes e insiro uma linha pra cada um
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
