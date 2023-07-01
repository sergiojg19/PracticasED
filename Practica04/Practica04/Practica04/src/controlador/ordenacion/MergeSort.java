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
public class MergeSort<E extends Comparable<E>> {

    public ListaEnlazada<E> ordenar(ListaEnlazada<E> lista, int orden) throws VacioException, PosicionException {
        boolean ascendente = (orden == 0);
        return mergeSort(lista, ascendente);
    }

    private ListaEnlazada<E> mergeSort(ListaEnlazada<E> lista, boolean ascendente)
            throws VacioException, PosicionException {
        if (lista.getSize() > 1) {
            ListaEnlazada<E> mitadIzquierda = new ListaEnlazada<>();
            ListaEnlazada<E> mitadDerecha = new ListaEnlazada<>();
            int medio = lista.getSize() / 2;

            for (int i = 0; i < medio; i++) {
                mitadIzquierda.insertarNodo(lista.get(i));
            }

            for (int i = medio; i < lista.getSize(); i++) {
                mitadDerecha.insertarNodo(lista.get(i));
            }

            mitadIzquierda = mergeSort(mitadIzquierda, ascendente);
            mitadDerecha = mergeSort(mitadDerecha, ascendente);

            return fusionar(mitadIzquierda, mitadDerecha, ascendente);
        }

        return lista;
    }

    private ListaEnlazada<E> fusionar(ListaEnlazada<E> mitadIzquierda, ListaEnlazada<E> mitadDerecha,
            boolean ascendente) throws VacioException, PosicionException {
        ListaEnlazada<E> listaOrdenada = new ListaEnlazada<>();

        int tamanoIzquierda = mitadIzquierda.getSize();
        int tamanoDerecha = mitadDerecha.getSize();
        int indiceIzquierda = 0;
        int indiceDerecha = 0;

        while (indiceIzquierda < tamanoIzquierda && indiceDerecha < tamanoDerecha) {
            E elementoIzquierda = mitadIzquierda.get(indiceIzquierda);
            E elementoDerecha = mitadDerecha.get(indiceDerecha);

            int resultadoComparacion;
            if (ascendente) {
                resultadoComparacion = elementoIzquierda.compareTo(elementoDerecha);
            } else {
                resultadoComparacion = elementoDerecha.compareTo(elementoIzquierda);
            }

            if (resultadoComparacion <= 0) {
                listaOrdenada.insertarNodo(elementoDerecha);
                indiceDerecha++;
            } else {
                listaOrdenada.insertarNodo(elementoIzquierda);
                indiceIzquierda++;
            }
        }

        while (indiceIzquierda < tamanoIzquierda) {
            listaOrdenada.insertarNodo(mitadIzquierda.get(indiceIzquierda));
            indiceIzquierda++;
        }

        while (indiceDerecha < tamanoDerecha) {
            listaOrdenada.insertarNodo(mitadDerecha.get(indiceDerecha));
            indiceDerecha++;
        }

        return listaOrdenada;
    }
}
