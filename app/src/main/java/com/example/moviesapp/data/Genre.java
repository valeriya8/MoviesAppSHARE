package com.example.moviesapp.data;

public class Genre {
    private final int id;
    private final String nome;

    public Genre(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
