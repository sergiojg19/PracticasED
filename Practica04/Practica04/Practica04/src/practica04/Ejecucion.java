package practica04;


/**
 *
 * @author sergio
 */

import controlador.ed.lista.ListaEnlazada;
import controlador.ed.lista.exception.PosicionException;
import controlador.ed.lista.exception.VacioException;
import controlador.ordenacion.MergeSort;
import controlador.ordenacion.Quicksort;



public class Ejecucion {

    public static void main(String[] args) {
        try {
            ListaEnlazada<Integer> lista = new ListaEnlazada<>();
            for (int i = 0; i < 20; i++) {
                int numero = generarN(1, 20000);
                lista.insertarNodo(numero);
            }
            lista.imprimir();

            MergeSort<Integer> mergeSort = new MergeSort<>();
            mergeSort.ordenar(lista, 0);
            System.out.println("\nOrdenacion Ascendente Merge:");
            lista.imprimir();

            /*mergeSort.ordenarDescendente(lista);
            System.out.println("\nDescendente Merge:");
            lista.imprimir();

            System.out.println("===============================");

            Quicksort<Integer> quicksort = new Quicksort<>();

            quicksort.quickSortAscendente(lista);
            System.out.println("Ordenacion Ascendente Quick:");
            lista.imprimir();

            quicksort.quickSortDescendente(lista);
            System.out.println("Ordenacion Descendente Quick:");
            lista.imprimir();*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int generarN(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}


