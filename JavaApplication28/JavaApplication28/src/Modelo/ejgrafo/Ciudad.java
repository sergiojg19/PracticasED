/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ejgrafo;

/**
 *
 * @author sergio
 */
public class Ciudad {
    private Integer id;
    private String nom_Ciudad;
    private Double peso;

    public Ciudad() {
    }

    public Ciudad(Integer id, String nom_Ciudad, Double peso) {
        this.id = id;
        this.nom_Ciudad = nom_Ciudad;
        this.peso = peso;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom_Ciudad() {
        return nom_Ciudad;
    }

    public void setNom_Ciudad(String nom_Ciudad) {
        this.nom_Ciudad = nom_Ciudad;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }
    
    @Override
    public String toString() {
        return this.nom_Ciudad;
    }
}
