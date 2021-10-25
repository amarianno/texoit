package br.com.textoit.teste.movielist.domain;

import java.io.IOException;

public interface PreencherListaFilmesPort {
    void preencherListaFilmesAPartirDoCsv() throws IOException;
}
