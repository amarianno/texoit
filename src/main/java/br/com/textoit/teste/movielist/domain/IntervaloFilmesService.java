package br.com.textoit.teste.movielist.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class IntervaloFilmesService {

    @Autowired
    IntervaloPremiacaoPort intervaloPremiacaoPort;

    /**
     * Retorna o MENOR intervalo entre as premiações
     * @return
     */
    public DiferencaPremio obterMenoresDiferencasEntrePremiacoes() {
        return intervaloPremiacaoPort.obterMenoresDiferencasOrdenadasPorAno().get(0);
    }

    /**
     * Retorna o MAIOR intervalo CONSECUTIVO entre as premiações
     * @return
     */
    public DiferencaPremio obterMaioresDiferencasEntrePremiacoes() {

        final List<Filme> filmes = intervaloPremiacaoPort.obterTodosVencedores();
        Map<String, List<Integer>> listaDeAnosDeVitoriaDosProcures = new HashMap<>();
        Map<String, Integer> producersOrdenados = new LinkedHashMap<>();

        //Separo todos os producers e junto aos anos que venceram
        filmes.stream()
                .forEach( filme -> {

                    if (listaDeAnosDeVitoriaDosProcures.containsKey(filme.getProducers())) {
                        List<Integer> anos = listaDeAnosDeVitoriaDosProcures.get(filme.getProducers());
                        anos.add(filme.getAno());
                        listaDeAnosDeVitoriaDosProcures.put(filme.getProducers(), anos);
                    } else {
                        List<Integer> anos = new ArrayList<>();
                        anos.add(filme.getAno());
                        listaDeAnosDeVitoriaDosProcures.put(filme.getProducers(), anos);
                    }
                });

        //ordeno os anos tirando a diferença
        for (var entry : listaDeAnosDeVitoriaDosProcures.entrySet()) {

            if (entry.getValue().size() > 1) {
                entry.getValue().sort(Comparator.reverseOrder());
                int maiorAno = entry.getValue().get(0);
                int menorAno = entry.getValue().get(1);
                producersOrdenados.put(entry.getKey(), (maiorAno - menorAno));
            }
        }

        //pego o com a maior diferenca
        final Map.Entry<String, Integer> producerComMaiorDiferenca = producersOrdenados
                .entrySet()
                .stream()
                .max(Map.Entry.<String, Integer>comparingByValue())
                .get();

        int maiorAno = listaDeAnosDeVitoriaDosProcures.get(producerComMaiorDiferenca.getKey()).get(0);
        int menorAno = listaDeAnosDeVitoriaDosProcures.get(producerComMaiorDiferenca.getKey()).get(1);

        return new DiferencaPremioModel(
                producerComMaiorDiferenca.getKey(),
                maiorAno,
                menorAno,
                producerComMaiorDiferenca.getValue()

        );
    }
}
