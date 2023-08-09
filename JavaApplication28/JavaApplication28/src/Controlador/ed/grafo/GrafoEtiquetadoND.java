/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ed.grafo;

import Controlador.ed.grafo.Exception.GrafoSizeException;

/**
 *
 * @author sergio
 */
public class GrafoEtiquetadoND<E> extends GrafoEtiquetadoD<E>{

    public GrafoEtiquetadoND(Integer numV) {
        super(numV);
    }
    
    @Override
    public void insertar(Integer i, Integer j, Double peso) throws GrafoSizeException {
        if (i.intValue() <= numV && j.intValue() <= numV) {
            if(!existeArista(i, j)) {
                listaAdycencia[i].insertarNodo(new Adycencia(j, peso));
                listaAdycencia[j].insertarNodo(new Adycencia(i, peso));
                numA++;
            }
        } else {
            throw new GrafoSizeException();
        }
    }
    
    public void insertarAristaE(E i, E j, Double peso) throws GrafoSizeException {
        this.insertar(getVerticeNum(i), getVerticeNum(j), peso);
        this.insertar(getVerticeNum(j), getVerticeNum(i), peso);
    }
    
}






