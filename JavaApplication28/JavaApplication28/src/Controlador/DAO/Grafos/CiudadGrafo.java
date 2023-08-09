/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO.Grafos;


import Controlador.DAO.CiudadDao;
import Controlador.DAO.DistanciaDao;
import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;
import Modelo.ejgrafo.Distancia;

import controlador.ed.grafo.GrafoEtiquetadoD;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergio
 */
public class CiudadGrafo {

    private GrafoEtiquetadoD<Ciudad> grafo;
    private ListaEnlazada<Ciudad> lista = new ListaEnlazada<>();

    public CiudadGrafo() {
        CiudadDao cbd = new CiudadDao();
        lista = cbd.listar();
        grafo = new GrafoEtiquetadoD<>(lista.size());
        try {
            for (int i = 0; i < lista.size(); i++) {
                grafo.etiquetarVertice(i + 1, lista.get(i));
                System.out.println(lista.get(i));
            }
            System.out.println("----------------");
            llenarGrafo();
        } catch (Exception e) {
        }
    }

    public ListaEnlazada<Ciudad> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Ciudad> lista) {
        this.lista = lista;
    }

    public GrafoEtiquetadoD<Ciudad> getGrafo() {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoD<Ciudad> grafo) {
        this.grafo = grafo;
    }

    private void llenarGrafo() {
        try {
            for (int i = 0; i < lista.size(); i++) {
                Ciudad cb = lista.get(i);
                HashMap<String, Double> mapa = new HashMap();
                //System.out.println("CUENTA BAMCARIA CLIENTE " + cb.getCliente());
                ListaEnlazada<Distancia> listaT = new DistanciaDao().listaPorCiudad(cb.getId());
                for (int j = 0; j < listaT.size(); j++) {
                    Distancia t = listaT.get(j);
                    if (mapa.get(t.getNom_Ciudad()) != null) {
                        //Double suma = mapa.get(t.getNro_cuenta());
                        Double suma = mapa.get(t.getNom_Ciudad());
                        suma += t.getDistanciaV();
                        mapa.put(t.getNom_Ciudad(), suma);
                    } else {
                        //mapa.put(t.getNro_cuenta(), 1.0);
                        mapa.put(t.getNom_Ciudad(), t.getDistanciaV());
                    }
                }
                //ITERAR
                for (Map.Entry<String, Double> entry : mapa.entrySet()) {
                    //System.out.println(entry.getKey()+" "+entry.getValue());
                    Ciudad aux = getCiudad(entry.getKey());
                    System.out.println(aux);
                    grafo.insertarAristaE(cb, aux, entry.getValue());
                }
            }
        } catch (Exception e) {
            System.out.println("error en crerGarfoEtiqueta" + e);
            e.printStackTrace();
        }
    }

    private Ciudad getCiudad(String nro) throws VacioException, PosicionException {
        Ciudad aux = null;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNom_Ciudad().equals(nro)) {
                aux = lista.get(i);
                break;
            }
        }
        return aux;
    }

    public ListaEnlazada<Ciudad> camino(Ciudad o, Ciudad d) throws VacioException, PosicionException {
        System.out.println(grafo.getVerticeNum(o) + "    " + grafo.getVerticeNum(d));
        ListaEnlazada<Integer> listaA = grafo.camin0(grafo.getVerticeNum(o), grafo.getVerticeNum(d));

        ListaEnlazada<Ciudad> camino = new ListaEnlazada<>();
        System.out.println(listaA.size());
        for (int i = 0; i < listaA.size(); i++) {

            camino.insertarNodo(grafo.getEtiqueta(listaA.get(i)));

        }
        System.out.println("CAMINO");
        return camino;
    }

    
    public ListaEnlazada<Ciudad> belmanFord(Ciudad o, Ciudad d) throws Exception {
        ListaEnlazada<Integer> listaA = grafo.bellmanFord(grafo.getVerticeNum(o), grafo.getVerticeNum(d));
        
        ListaEnlazada<Ciudad> camino = new ListaEnlazada<>();
        for(int i = 0; i < listaA.size(); i++) {
            
            camino.insertarInicio(grafo.getEtiqueta(listaA.get(i)));
            
        }
        return camino;
    }
    
    public ListaEnlazada floyd(Ciudad o, Ciudad d) throws Exception {
        
        ListaEnlazada<Integer> listaA = grafo.floyd(grafo.getVerticeNum(o), grafo.getVerticeNum(d));
        
        ListaEnlazada<Ciudad> camino = new ListaEnlazada<>();
        for (int i = 0; i < listaA.size(); i++) {

            camino.insertarNodo(grafo.getEtiqueta(listaA.get(i)));

        }
        return camino;
    }
    
    public static void main(String[] args) {
        CiudadGrafo cbg = new CiudadGrafo();

        
    }

}
