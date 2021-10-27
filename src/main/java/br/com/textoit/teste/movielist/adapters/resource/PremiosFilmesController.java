package br.com.textoit.teste.movielist.adapters.resource;

import br.com.textoit.teste.movielist.adapters.model.DiferencaDTO;
import br.com.textoit.teste.movielist.adapters.model.DiferencasPremiacaoDTO;
import br.com.textoit.teste.movielist.domain.DiferencaPremio;
import br.com.textoit.teste.movielist.domain.IntervaloFilmesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/filme", produces = MediaType.APPLICATION_JSON_VALUE)
public class PremiosFilmesController {

    @Autowired
    IntervaloFilmesService intervaloFilmesService;

    @GetMapping(value = "/intervalos")
    public DiferencasPremiacaoDTO obterDiferencasEntrePremiacoes() {

        DiferencasPremiacaoDTO diferencasPremiacaoDTO = new DiferencasPremiacaoDTO();

        final DiferencaPremio maxDiferencaPremios = intervaloFilmesService.obterMaioresDiferencasEntrePremiacoes();
        final DiferencaPremio minDiferencaPremios = intervaloFilmesService.obterMenoresDiferencasEntrePremiacoes();


        diferencasPremiacaoDTO.getMax().add(
                new DiferencaDTO(
                        maxDiferencaPremios.getNome(),
                        maxDiferencaPremios.getDiferenca(),
                        maxDiferencaPremios.getMinimo(),
                        maxDiferencaPremios.getMaximo()
                )
        );

        diferencasPremiacaoDTO.getMin().add(
                new DiferencaDTO(
                        minDiferencaPremios.getNome(),
                        minDiferencaPremios.getDiferenca(),
                        minDiferencaPremios.getMinimo(),
                        minDiferencaPremios.getMaximo()
                )
        );

        return diferencasPremiacaoDTO;
    }
}
