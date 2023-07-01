/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.ordenacion;

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.NodoLista;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;

/**
 *
 * @author sergio
 */
public class Quicksort {

    public ListaEnlazada<Integer> quickSort(ListaEnlazada<Integer> lista, int orden) throws Exception{
        if (lista.size() <= 1) {
            return lista;
        }

        // Seleccione un pivote (en este caso, el primer elemento de la lista)
        int pivot = lista.get(0);

        // Crea dos listas para almacenar elementos menores y mayores que el pivote
        ListaEnlazada<Integer> menores = new ListaEnlazada<>();
        ListaEnlazada<Integer> mayores = new ListaEnlazada<>();

        // Divide la lista en elementos menores y mayores que el pivote
        NodoLista<Integer> nodoActual = lista.getCabecera().getSig();
        while (nodoActual != null) {
            if (nodoActual.getInfo() <= pivot) {
                menores.insertarNodo(nodoActual.getInfo());
            } else {
                mayores.insertarNodo(nodoActual.getInfo());
            }
            nodoActual = nodoActual.getSig();
        }

        // Recursivamente ordena las listas de elementos menores y mayores
        menores = quickSort(menores, orden);
        mayores = quickSort(mayores, orden);

        // Combina las listas ordenadas junto con el pivote
        return concatenarListas(menores, pivot, mayores, orden);
    }

    private ListaEnlazada<Integer> concatenarListas(ListaEnlazada<Integer> menores, int pivot, ListaEnlazada<Integer> mayores, int orden) {
        ListaEnlazada<Integer> listaOrdenada = new ListaEnlazada<>();

        // Agrega los elementos menores en función del orden
        if (orden == 1) { // Orden ascendente
            listaOrdenada = menores;
            listaOrdenada.insertarNodo(pivot);
        } else { // Orden descendente
            listaOrdenada.insertarNodo(pivot);
            listaOrdenada = concatenarListas(menores, listaOrdenada, 0);
        }

        // Agrega los elementos mayores en función del orden
        listaOrdenada = concatenarListas(mayores, listaOrdenada, orden);

        return listaOrdenada;
    }

    private ListaEnlazada<Integer> concatenarListas(ListaEnlazada<Integer> lista, ListaEnlazada<Integer> concatenacion, int orden) {
        NodoLista<Integer> nodoActual = lista.getCabecera();
        while (nodoActual != null) {
            if (orden == 1) { // Orden ascendente
                concatenacion.insertarNodo(nodoActual.getInfo());
            } else { // Orden descendente
                concatenacion.insertarInicio(nodoActual.getInfo());
            }
            nodoActual = nodoActual.getSig();
        }
        return concatenacion;
    }

}
