/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador.dao;

import controlador.ed.lista.ListaEnlazada;
import java.io.IOException;
import modelo.Computadora;
import modelo.Marca;

/**
 *
 * @author sergio
 */
public class ComputadoraDao extends AdaptadorDao<Computadora> {

    private Computadora computadora;

    public ComputadoraDao() {
        super(Computadora.class);
    }

    public Computadora getComputadora() {
        if (this.computadora == null) {
            this.computadora = new Computadora();
        }
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public void guardar() throws IOException {
        computadora.setId(generarId());
        this.guardar(computadora);
    }

    public void modificar(Integer pos) throws Exception {
        this.modificar(computadora, pos);
    }

    private Integer generateID() {
        return listar().size() + 1;
    }

    public static ListaEnlazada<Computadora> ordenarPorNombre(ListaEnlazada<Computadora> lista) throws Exception {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getNombre().compareToIgnoreCase(lista.get(minIndex).getNombre()) < 0) {
                    minIndex = j;
                }
            }

            Computadora temp = lista.get(minIndex);
            lista.modificar(lista.get(i), minIndex);
            lista.modificar(temp, i);
        }

        return lista;

    }

    public static ListaEnlazada<Computadora> ordenarPorPrecio(ListaEnlazada<Computadora> lista) throws Exception {
        int n = lista.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (lista.get(j).getPrecio() < lista.get(minIndex).getPrecio()) {
                    minIndex = j;
                }
            }

            Computadora temp = lista.get(minIndex);
            lista.modificar(lista.get(i), minIndex);
            lista.modificar(temp, i);
        }

        return lista;
    }

    public ListaEnlazada<Computadora> buscarPorNombreBinaria(String nombre) throws Exception {
        ListaEnlazada<Computadora> lista = listar();
        lista = ordenarPorNombre(lista);
        ListaEnlazada<Computadora> resultado = new ListaEnlazada<>();
        int l = 0, r = lista.size() - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            Computadora aux = lista.get(m);
            if (aux.getNombre().compareToIgnoreCase(nombre) == 0) {
                resultado.insertarNodo(aux);
                return resultado;
            }

            if (aux.getNombre().compareToIgnoreCase(nombre) < 0) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return null;
    }

    public ListaEnlazada<Computadora> buscarPorPrecioBinaria(String clave) throws Exception {
        ListaEnlazada<Computadora> lista = listar();
        lista = ordenarPorPrecio(lista);
        ListaEnlazada<Computadora> resultado = new ListaEnlazada<>();
        int l = 0, r = lista.size() - 1;
        double precio = Double.parseDouble(clave);

        while (l <= r) {
            int m = l + (r - l) / 2;
            Computadora aux = lista.get(m);

            if (aux.getPrecio() == precio) {
                resultado.insertarNodo(aux);
                return resultado;
            }

            if (aux.getPrecio() < precio) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }

        return null;
    }

    public ListaEnlazada<Computadora> buscarPorNombreLinealBinario(String clave) throws Exception {
        ListaEnlazada<Computadora> lista = listar();
        lista = ordenarPorNombre(lista);
        ListaEnlazada<Computadora> resultados = new ListaEnlazada<>();
        int low = 0;
        int high = lista.size() - 1;
        boolean encontrados = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            Computadora computadora = lista.get(mid);
            int comparacion = computadora.getNombre().compareToIgnoreCase(clave);

            if (comparacion == 0) {
                resultados.insertarNodo(computadora);
                encontrados = true;

                int left = mid - 1;
                while (left >= 0 && lista.get(left).getNombre().equalsIgnoreCase(clave)) {
                    resultados.insertarNodo(lista.get(left));
                    left--;
                }

                int right = mid + 1;
                while (right < lista.size() && lista.get(right).getNombre().equalsIgnoreCase(clave)) {
                    resultados.insertarNodo(lista.get(right));
                    right++;
                }

                break;
            } else if (comparacion < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (!encontrados) {
            for (int i = 0; i < lista.size(); i++) {
                Computadora computadora = lista.get(i);
                if (computadora.getNombre().equalsIgnoreCase(clave)) {
                    resultados.insertarNodo(computadora);
                    encontrados = true;
                }
            }
        }

        return encontrados ? resultados : null;
    }

    public ListaEnlazada<Computadora> buscarPorPrecioLinealBinario(String clave) throws Exception {
        ListaEnlazada<Computadora> lista = listar();
        ListaEnlazada<Computadora> resultados = new ListaEnlazada<>();
        lista = ordenarPorPrecio(lista);
        double precio = Double.parseDouble(clave);
        int low = 0;
        int high = lista.size() - 1;
        boolean encontrados = false;

        while (low <= high) {
            int mid = (low + high) / 2;
            Computadora computadora = lista.get(mid);

            if (computadora.getPrecio() == precio) {
                resultados.insertarNodo(computadora);
                encontrados = true;

                int left = mid - 1;
                while (left >= 0 && lista.get(left).getPrecio() == precio) {
                    resultados.insertarNodo(lista.get(left));
                    left--;
                }

                int right = mid + 1;
                while (right < lista.size() && lista.get(right).getPrecio() == precio) {
                    resultados.insertarNodo(lista.get(right));
                    right++;
                }

                break;
            } else if (computadora.getPrecio() < precio) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (!encontrados) {
            for (int i = 0; i < lista.size(); i++) {
                Computadora computadora = lista.get(i);
                if (computadora.getPrecio() == precio) {
                    resultados.insertarNodo(computadora);
                    encontrados = true;
                }
            }
        }

        return encontrados ? resultados : null;
    }

    public ListaEnlazada<Computadora> buscarMarcaBinaria(String dato) throws Exception {
        ListaEnlazada<Computadora> resultados = new ListaEnlazada<>();
        Marca g = new MarcaDao().buscarPorNombreMarcaBinaria(dato.trim());
        if (g != null) {
            ListaEnlazada<Computadora> lista = listar();
            for (int i = 0; i < lista.size(); i++) {
                Computadora aux = lista.get(i);
                if (aux.getId_marca().intValue() == g.getId().intValue()) {
                    resultados.insertarNodo(aux);
                    break;
                }

            }
        }
        return resultados;
    }

    public ListaEnlazada<Computadora> buscarMarcaLinealBinaria(String marca) throws Exception {
        MarcaDao marcaDao = new MarcaDao();
        ListaEnlazada<Marca> marcasEncontradas = marcaDao.buscarPorNombreMarcaLinealBinaria(marca);
        ListaEnlazada<Computadora> computadorasEncontradas = new ListaEnlazada<>();

        for (int i = 0; i < marcasEncontradas.size(); i++) {
            Marca marcaEncontrada = marcasEncontradas.get(i);
            ListaEnlazada<Computadora> lista = listar();
            for (int j = 0; j < lista.size(); j++) {
                Computadora computadora = lista.get(j);
                if (computadora.getId_marca().intValue() == marcaEncontrada.getId().intValue()) {
                    computadorasEncontradas.insertarNodo(computadora);
                }
            }
        }

        return computadorasEncontradas;
    }

}
