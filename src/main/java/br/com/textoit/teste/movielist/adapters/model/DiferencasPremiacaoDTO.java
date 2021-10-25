package br.com.textoit.teste.movielist.adapters.model;

import java.util.ArrayList;
import java.util.List;

/**
 * {
 * "min": [
 * {
 * "producer": "Producer 1",
 * "interval": 1,
 * "previousWin": 2008,
 * "followingWin": 2009
 * },
 * {
 * "producer": "Producer 2",
 * "interval": 1,
 * "previousWin": 2018,
 * "followingWin": 2019
 * }
 * ],
 * "max": [
 * {
 * "producer": "Producer 1",
 * "interval": 99,
 * "previousWin": 1900,
 * "followingWin": 1999
 * },
 * {
 * "producer": "Producer 2",
 * "interval": 99,
 * "previousWin": 2000,
 * "followingWin": 2099
 * }
 * ]
 * }
 */
public class DiferencasPremiacaoDTO {
    private List<DiferencaDTO> min;
    private List<DiferencaDTO> max;

    public DiferencasPremiacaoDTO() {
        min = new ArrayList<>();
        max = new ArrayList<>();
    }

    public List<DiferencaDTO> getMin() {
        return min;
    }

    public void setMin(List<DiferencaDTO> min) {
        this.min = min;
    }

    public List<DiferencaDTO> getMax() {
        return max;
    }

    public void setMax(List<DiferencaDTO> max) {
        this.max = max;
    }
}
