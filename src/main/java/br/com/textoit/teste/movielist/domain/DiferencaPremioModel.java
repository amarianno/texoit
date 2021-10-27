package br.com.textoit.teste.movielist.domain;

public class DiferencaPremioModel implements DiferencaPremio {

    private String nome;
    private Integer maximo;
    private Integer minimo;
    private Integer diferenca;

    public DiferencaPremioModel(String nome, Integer maximo, Integer minimo, Integer diferenca) {
        this.nome = nome;
        this.maximo = maximo;
        this.minimo = minimo;
        this.diferenca = diferenca;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public Integer getMaximo() {
        return maximo;
    }

    @Override
    public Integer getMinimo() {
        return minimo;
    }

    @Override
    public Integer getDiferenca() {
        return diferenca;
    }
}
