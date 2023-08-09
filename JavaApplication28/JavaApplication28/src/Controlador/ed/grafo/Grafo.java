/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.ed.grafo;

import Controlador.ed.grafo.Exception.GrafoSizeException;
import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;
import Modelo.ejgrafo.Distancia;
import java.util.Objects;
import javax.swing.JOptionPane;

/**
 *
 * @author sergio
 */
public abstract class Grafo {

    private int numV;
    private int numA;
    private Distancia[] distancias;
    private Ciudad[] ciudades;

    public Grafo(int numV, int numA, Distancia[] distancias, Ciudad[] ciudades) {
        this.numV = numV;
        this.numA = numA;
        this.distancias = distancias;
        this.ciudades = ciudades;
    }

    public Grafo() {
    }

    public abstract Integer numVertices();

    public abstract Integer numAristas();

    public abstract Boolean existeArista(Integer i, Integer j) throws GrafoSizeException;

    public abstract Double pesoArista(Integer i, Integer j) throws GrafoSizeException;

    //1 ----- 3
    //3 ----- 1
    public abstract void insertar(Integer i, Integer j) throws GrafoSizeException;

    //1 ----- 3 -->peso 5
    public abstract void insertar(Integer i, Integer j, Double peso) throws GrafoSizeException;

    public abstract ListaEnlazada<Adycencia> adycentes(Integer i);

    @Override
    public String toString() {
        StringBuilder grafo = new StringBuilder("GRAFO" + "\n");
        for (int i = 1; i <= numVertices(); i++) {
            grafo.append(" V " + i + "\n");
            ListaEnlazada<Adycencia> lista = adycentes(i);
            grafo.append((!lista.isEmpty()) ? "Adycencias" : "No Adycencias");
            grafo.append("\n");
            for (int j = 0; j < lista.size(); j++) {
                try {
                    Adycencia aux = lista.get(j);
                    grafo.append(" -- V " + aux.getDestino() + " PESO --> " + aux.getPeso() + "\n");
                } catch (Exception e) {
                }
            }
        }
        return grafo.toString();
    }

    public ListaEnlazada camin0(Integer i, Integer d) throws VacioException, PosicionException {
        ListaEnlazada camino = new ListaEnlazada();
        System.out.println(i + "   " + d);
        if (estaConectado()) {
            ListaEnlazada pesos = new ListaEnlazada();
            Boolean finalizar = false;
            Integer inicial = i;
            camino.insertarNodo(i);
            while (!finalizar) {
                ListaEnlazada<Adycencia> adycencias = adycentes(inicial);
                Double peso = Double.MAX_VALUE;
                //System.out.println(peso);
                int T = -1;//vertice destino
                for (int j = 0; j < adycencias.size(); j++) {
                    Adycencia ad = adycencias.get(j);

                    if (!estaCamino(camino, ad.getDestino())) {
                        //System.out.println("PASO CAMINO");
                        Double pesoArista = ad.getPeso();
                        if (d.intValue() == ad.getDestino().intValue()) {
                            T = ad.getDestino();
                            peso = pesoArista;
                            break;
                        } else if (pesoArista < peso) {//camino minimo (pesoArista < peso) ---- camino maximo (pesoArista > peso)
                            T = ad.getDestino();
                            peso = pesoArista;
                        }
                    }
                    //System.out.println("PASO CAMINO --");
                }
                if (T == -1) {
                    System.out.println("PASO POR AQUI vacio");
                    camino.deleteAll();
                    break;
                }
                pesos.insertarNodo(peso);
                camino.insertarNodo(T);
                inicial = T;
                if (d.intValue() == inicial.intValue()) {
                    finalizar = true;
                }
            }
        }
        return camino;
    }

    private Boolean estaCamino(ListaEnlazada<Integer> lista, Integer vertice) throws VacioException, PosicionException {
        Boolean band = false;
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).intValue() == vertice.intValue()) {
                band = true;
                break;
            }
        }
        return band;
    }

    private Boolean estaConectado() {
        Boolean band = true;
        for (int i = 1; i <= numVertices(); i++) {
            ListaEnlazada<Adycencia> lista = adycentes(i);
            if (lista.size() == 0) {
                band = false;
                break;
            }
        }
        return band;
    }

    //Metodo Bellman Ford
    public ListaEnlazada<Integer> bellmanFord(Integer origen, Integer destino) {
        ListaEnlazada<Integer> respuesta = new ListaEnlazada<>();
        double[] distanciasMinimas = new double[numV];
        String[] camino = new String[numV];

        for (int i = 0; i < numV; ++i) {
            distanciasMinimas[i] = Double.MAX_VALUE;
            camino[i] = "Inf";
        }

        distanciasMinimas[origen - 1] = 0;
        camino[origen - 1] = String.valueOf(origen);

        for (int i = 1; i < numV; ++i) {
            for (int j = 0; j < numA; ++j) {
                Integer u = distancias[j].getId()- 1;
                Integer v = distancias[j].getId_Ciudad() - 1;
                Double peso = distancias[j].getDistanciaV();
                if (distanciasMinimas[u] != Double.MAX_VALUE && distanciasMinimas[u] + peso < distanciasMinimas[v]) {
                    distanciasMinimas[v] = distanciasMinimas[u] + peso;
                    camino[v] = String.valueOf(u + 1);
                }
            }
        }

        for (int j = 0; j < numA; ++j) {
            Integer u = distancias[j].getId()- 1;
            Integer v = distancias[j].getId_Ciudad() - 1;
            Double peso = distancias[j].getDistanciaV();
            if (distanciasMinimas[u] != Double.MAX_VALUE && distanciasMinimas[u] + peso < distanciasMinimas[v]) {
                System.out.println("El grafo tiene un ciclo");
                return null;
            }
        }

        if (camino[destino - 1].equals("Inf")) {
            JOptionPane.showMessageDialog(null, "No hay camino");
            System.out.println("No hay Camino");
            return null;
        }

        respuesta.insertarNodo(destino);

        do {
            destino = Integer.valueOf(camino[destino - 1]);
            respuesta.insertarInicio(destino);
        } while (!Objects.equals(destino, origen));

        return respuesta;
    }
    // Metodo de FLoyd

    public Double[][] adyacencia() {
        Double[][] adjacencyMatrix = new Double[numV + 1][numV + 1];

        // Inicializa la matriz de adyacencia con valores infinitos
        for (int i = 1; i <= numV; i++) {
            for (int j = 1; j <= numV; j++) {
                adjacencyMatrix[i][j] = Double.POSITIVE_INFINITY;
            }
        }

        // Llena la matriz de adyacencia utilizando los objetos Distancia
        for (Distancia distancia : distancias) {
            Integer u = distancia.getId();
            Integer v = distancia.getId_Ciudad();
            Double peso = distancia.getDistanciaV();

            adjacencyMatrix[u][v] = peso;
        }

        // Establece los elementos diagonales en 0
        for (int i = 1; i <= numV; i++) {
            adjacencyMatrix[i][i] = 0.0;
        }

        return adjacencyMatrix;
    }

    public ListaEnlazada<Integer> floyd(Integer origen, Integer destino) throws Exception {
        ListaEnlazada<Integer> respuesta = new ListaEnlazada<>();

        Double[][] adj = adyacencia();

        // Resto de tu implementación del algoritmo de Floyd-Warshall
        Integer[][] ruta = new Integer[numV + 1][numV + 1];
        Integer[][] camino = new Integer[numV + 1][numV + 1];

        for (int i = 1; i <= numV; i++) {
            for (int j = 1; j <= numV; j++) {
                if (i == j) {
                    camino[i][j] = 0;
                    ruta[i][j] = 0;
                } else {
                    camino[i][j] = j;
                    ruta[i][j] = i;
                }
            }
        }

        for (int k = 1; k <= numV; k++) {
            for (int i = 1; i <= numV; i++) {
                for (int j = 1; j <= numV; j++) {
                    if (adj[i][k] + adj[k][j] < adj[i][j]) {
                        adj[i][j] = adj[i][k] + adj[k][j];
                        camino[i][j] = camino[i][k];
                    }
                }
            }
        }

        Integer i = origen;
        Integer j = destino;

        if (camino[i][j] == j) {
            //JOptionPane.showMessageDialog(null, "No hay camino");
            System.out.println("No hay camino");
            return respuesta;
        }

        while (i != j) {
            respuesta.insertarNodo(i);
            i = camino[i][j];
        }

        respuesta.insertarNodo(i);

        return respuesta;
    }
}
