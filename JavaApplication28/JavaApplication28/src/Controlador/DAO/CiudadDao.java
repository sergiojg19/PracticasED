/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.DAO;

import Controlador.ed.lista.ListaEnlazada;
import Controlador.ed.lista.NodoLista;
import Modelo.ejgrafo.Ciudad;
import Modelo.ejgrafo.Distancia;
import controlador.ed.grafo.GrafoEtiquetadoD;
import java.io.IOException;

/**
 *
 * @author sergio
 */
public class CiudadDao extends AdaptadorDAO<Ciudad> {

    private Ciudad cb;
    private GrafoEtiquetadoD<Ciudad> grafo;

    public CiudadDao() {
        super(Ciudad.class);
    }

    public Ciudad getCb() {
        if (this.cb == null) {
            this.cb = new Ciudad();
        }
        return cb;
    }

    public void setCb(Ciudad cb) {
        this.cb = cb;
    }

    public void guardar() throws IOException {
        cb.setId(generateID());
        this.guardar(cb);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(cb, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    //Falta Buscar por ID  
    public Ciudad buscarNomCiudad(String nomCiudad) throws Exception {
        ListaEnlazada<Ciudad> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Ciudad aux = lista.get(i);
            if (aux.getNom_Ciudad().equalsIgnoreCase(nomCiudad)) {
                return aux;
            }
        }
        return null;
    }

    public ListaEnlazada<Ciudad> buscarNomCiudades(String nomCiudad) throws Exception {
        ListaEnlazada<Ciudad> ciudadesEncontradas = new ListaEnlazada<>();
        ListaEnlazada<Ciudad> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            Ciudad aux = lista.get(i);
            if (aux.getNom_Ciudad().equalsIgnoreCase(nomCiudad)) {
                ciudadesEncontradas.insertarNodo(aux);
                break;
            }
        }
        return ciudadesEncontradas;
    }

//    public ListaEnlazada<Ciudad> bellmanFord(String origen, String destino, List<Ciudad> ciudades, List<Distancia> distancias) {
//        ListaEnlazada<Ciudad> caminoMinimo = new ListaEnlazada<>();
//        ListaEnlazada<Ciudad> resultado = new ListaEnlazada<>();
//
//        // Inicializar las distancias desde el origen a todas las ciudades como infinito excepto el origen
//        for (Ciudad ciudad : ciudades) {
//            if (ciudad.getNom_Ciudad().equals(origen)) {
//                caminoMinimo.insertarNodo(ciudad, 0.0);
//            } else {
//                caminoMinimo.insertarNodo(ciudad, Double.MAX_VALUE);
//            }
//        }
//
//        // Iterar (V - 1) veces, donde V es el número de vértices (ciudades)
//        for (int i = 1; i < ciudades.size(); i++) {
//            for (Distancia distancia : distancias) {
//                Ciudad origenCiudad = null;
//                Ciudad destinoCiudad = null;
//
//                for (Ciudad ciudad : ciudades) {
//                    if (ciudad.getNom_Ciudad().equals(distancia.getNom_Ciudad())) {
//                        destinoCiudad = ciudad;
//                    }
//                    if (ciudad.getId() == distancia.getId_Ciudad()) {
//                        origenCiudad = ciudad;
//                    }
//                }
//
//                Nodo<Ciudad> nodoActual = caminoMinimo.cabeza;
//                while (nodoActual != null) {
//                    if (nodoActual.elemento.getNom_Ciudad().equals(origenCiudad.getNom_Ciudad())) {
//                        Double distanciaAcumulada = nodoActual.distancia + distancia.getDistanciaV();
//                        if (distanciaAcumulada < caminoMinimo.cabeza.siguiente.distancia) {
//                            caminoMinimo.cabeza.siguiente.distancia = distanciaAcumulada;
//                            resultado.insertarNodo(destinoCiudad, distanciaAcumulada);
//                        }
//                        break;
//                    }
//                    nodoActual = nodoActual.siguiente;
//                }
//            }
//        }
//
//        return resultado;
//    }
}
