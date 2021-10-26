package br.com.textoit.teste.movielist.adapters.resource;

import br.com.textoit.teste.movielist.adapters.model.DiferencaDTO;
import br.com.textoit.teste.movielist.adapters.model.DiferencasPremiacaoDTO;
import br.com.textoit.teste.movielist.domain.DiferencaPremio;
import br.com.textoit.teste.movielist.domain.IntervaloPremiacaoPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/filme", produces = MediaType.APPLICATION_JSON_VALUE)
public class PremiosFilmesController {

    @Autowired
    IntervaloPremiacaoPort intervaloPremiacaoPort;

    @GetMapping(value = "/intervalos")
    public DiferencasPremiacaoDTO obterDiferencasEntrePremiacoes() {

        DiferencasPremiacaoDTO diferencasPremiacaoDTO = new DiferencasPremiacaoDTO();

        final List<DiferencaPremio> maxDiferencaPremios = intervaloPremiacaoPort.obterMaioresDiferencas();
        final List<DiferencaPremio> minDiferencaPremios = intervaloPremiacaoPort.obterMenoresDiferencasOrdenadasPorAno();


        maxDiferencaPremios.forEach( diffPremio ->
            diferencasPremiacaoDTO.getMax().add(
                    new DiferencaDTO(
                            diffPremio.getNome(),
                            diffPremio.getDiferenca(),
                            diffPremio.getMinimo(),
                            diffPremio.getMaximo()
                    )
            )
        );

        minDiferencaPremios.forEach( diffPremio ->
            diferencasPremiacaoDTO.getMin().add(
                    new DiferencaDTO(
                            diffPremio.getNome(),
                            diffPremio.getDiferenca(),
                            diffPremio.getMinimo(),
                            diffPremio.getMaximo()
                    )
            )
        );

        return diferencasPremiacaoDTO;
    }
}
