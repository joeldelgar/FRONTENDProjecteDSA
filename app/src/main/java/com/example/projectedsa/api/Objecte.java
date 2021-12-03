package com.example.projectedsa.api;

public class Objecte {
    public String nom;
    public String description;
    public String points;

    public Objecte(String nom, String description, String points) {
        this.nom = nom;
        this.description = description;
        this.points = points;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
