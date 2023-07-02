/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import java.io.IOException;
import modelo.Marca;
import controlador.ed.lista.ListaEnlazada;

/**
 *
 * @author sergio
 */
public class MarcaDao extends AdaptadorDao<Marca> {

    private Marca marca;

    public MarcaDao() {
        super(Marca.class);
    }

    public Marca getMarca() {
        if (this.marca == null) {
            this.marca = new Marca();
        }
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public void guardar() throws IOException {
        marca.setId(generarId());
        this.guardar(marca);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(marca, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public static ListaEnlazada<Marca> ordenarPorNombre(ListaEnlazada<Marca> marcas) throws Exception {
        int n = marcas.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (marcas.get(j).getNombre().compareToIgnoreCase(marcas.get(minIndex).getNombre()) < 0) {
                    minIndex = j;
                }
            }

            Marca temp = marcas.get(minIndex);
            marcas.modificar(marcas.get(i), minIndex);
            marcas.modificar(temp, i);
        }

        return marcas;
    }

    public Marca buscarPorNombreMarcaBinaria(String clave) throws Exception {
        ListaEnlazada<Marca> lista = listar();
        lista = ordenarPorNombre(lista);
        Marca resultado = new Marca();
        int l = 0, r = lista.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            Marca aux = lista.get(m);
            if (aux.getNombre().compareToIgnoreCase(clave) == 0) {
                resultado = aux;
                return resultado;
            }

            if (aux.getNombre().compareToIgnoreCase(clave) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return null;
    }

    public ListaEnlazada<Marca> buscarPorNombreMarcaLinealBinaria(String clave) throws Exception {
        ListaEnlazada<Marca> lista = listar();
        lista = ordenarPorNombre(lista);
        ListaEnlazada<Marca> resultados = new ListaEnlazada<>();
        int l = 0, r = lista.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            Marca aux = lista.get(m);
            if (aux.getNombre().compareToIgnoreCase(clave) == 0) {
                resultados.insertarNodo(aux);
                int left = m - 1;
                while (left >= 0 && lista.get(left).getNombre().equalsIgnoreCase(clave)) {
                    resultados.insertarNodo(lista.get(left));
                    left--;
                }

                int right = m + 1;
                while (right < lista.size() && lista.get(right).getNombre().equalsIgnoreCase(clave)) {
                    resultados.insertarNodo(lista.get(right));
                    right++;
                }

                return resultados;
            }

            if (aux.getNombre().compareToIgnoreCase(clave) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return resultados;
    }
}
