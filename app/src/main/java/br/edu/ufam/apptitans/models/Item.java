package br.edu.ufam.apptitans.models;

public class Item {
    String mood, musica;

    public Item(String mood, String musica) {
        this.mood = mood;
        this.musica = musica;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }
}
