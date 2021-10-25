package br.com.textoit.teste.movielist.infra;

import br.com.textoit.teste.movielist.domain.PreencherListaFilmesPort;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PreencherListaFilmesImpl implements PreencherListaFilmesPort {

    @Value("classpath:movielist.csv")
    Resource listaFilmesCsv;

    @Autowired
    FilmeRepository filmeRepository;


    public void preencherListaFilmesAPartirDoCsv() throws IOException {

        try (CSVReader reader = new CSVReader(new FileReader(listaFilmesCsv.getFile()))) {

            List<String[]> r = reader.readAll();
            r.remove(0);
            r.forEach(x -> {
                String[] dadosCsv = dadosCsv(x);
                filmeRepository.save(obterValor(dadosCsv));
            });

        } catch (Exception e) {
            e.getMessage();
        }

    }

    private String[] dadosCsv(String[] dadosBrutos) {

        List<String> dadosCsv = new ArrayList<>();
        dadosCsv.addAll(Arrays.asList(dadosBrutos[0].split(";")));

        try {
            final String[] split = dadosBrutos[1].split(";");
            dadosCsv.add(split[0]);
            dadosCsv.add(split[1]);
        } catch (Exception e) {
            e.getMessage();
        }

        return dadosCsv.toArray(new String[0]);

    }

    private Filme obterValor(String[] dadosCsv) {

        Filme filme = new Filme();
        filme.setAno(Integer.parseInt(dadosCsv[0]));
        filme.setTitulo(dadosCsv[1]);
        filme.setProducers(dadosCsv[3]);


        try {
            if ("yes".equals(dadosCsv[4])) {
                filme.setVencedor(true);
            }
            if ("yes".equals(dadosCsv[5])) {
                filme.setVencedor(true);
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            aiobe.getMessage();
            return filme;
        }

        return filme;
    }


}
