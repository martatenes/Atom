package com.fractalmedia.codechallenge.atom.repositories.responses;

import com.fractalmedia.codechallenge.atom.models.Credit;

import java.util.List;

public class MovieCreditsResponse {

    private long id;
    private List<Credit> cast;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Credit> getCast() {
        return cast;
    }

    public void setCast(List<Credit> cast) {
        this.cast = cast;
    }
}
