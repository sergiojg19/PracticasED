/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo.ejgrafo;

import java.util.Date;

/**
 *
 * @author sergio
 */
public class Distancia {
    private Integer id;
    private Double distanciaV;
    private String nom_Ciudad;
    private Integer id_Ciudad;
    private TipoDistancia tipo;

    
    public Distancia(){
        
    }

    public Distancia(Integer id, Double distanciaV, String nom_Ciudad, Integer id_Ciudad, TipoDistancia tipo) {
        this.id = id;
        this.distanciaV = distanciaV;
        this.nom_Ciudad = nom_Ciudad;
        this.id_Ciudad = id_Ciudad;
        this.tipo = tipo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDistanciaV() {
        return distanciaV;
    }

    public void setDistanciaV(Double distanciaV) {
        this.distanciaV = distanciaV;
    }

    public String getNom_Ciudad() {
        return nom_Ciudad;
    }

    public void setNom_Ciudad(String nom_Ciudad) {
        this.nom_Ciudad = nom_Ciudad;
    }

    public Integer getId_Ciudad() {
        return id_Ciudad;
    }

    public void setId_Ciudad(Integer id_Ciudad) {
        this.id_Ciudad = id_Ciudad;
    }

    public TipoDistancia getTipo() {
        return tipo;
    }

    public void setTipo(TipoDistancia tipo) {
        this.tipo = tipo;
    }


}
