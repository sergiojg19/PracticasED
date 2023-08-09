/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;

import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;
import Modelo.ejgrafo.Distancia;
import java.io.IOException;

/**
 *
 * @author sergio
 */
public class DistanciaDao extends AdaptadorDAO<Distancia> {

    private Distancia distancia;

    public DistanciaDao() {
        super(Distancia.class);
    }

    public Distancia getDistancia() {
        if (this.distancia == null) {
            this.distancia = new Distancia();
        }
        return distancia;
    }

    public void setDistancia(Distancia distancia) {
        this.distancia = distancia;
    }

    public void guardar() throws IOException {
        distancia.setId(generateID());
        this.guardar(distancia);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(distancia, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public ListaEnlazada<Distancia> listaPorCiudad(Integer id) throws VacioException, PosicionException {

        ListaEnlazada<Distancia> lista = new ListaEnlazada<>();
        ListaEnlazada<Distancia> listado = listar();
        for (int i = 0; i < listado.size(); i++) {
            Distancia aux = listado.get(i);
            if (aux.getId_Ciudad().intValue() == id) {
                lista.insertarNodo(aux);
            }
        }
        return lista;
    }

    public Boolean validarDistancia(Ciudad cb, Double valor) {
        Boolean band = true;
        if (cb.getPeso()< valor) {
            band = false;
        }
        return band;
    }

}
