/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import controlador.dao.MarcaDao;
import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;
import modelo.Computadora;
import modelo.Marca;

/**
 *
 * @author sergio
 */
public class ModeloTablaComputadora extends AbstractTableModel {

    private ListaEnlazada<Computadora> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Computadora c = null;
        Marca m = null;
        try {
            c = lista.get(i);
            m = new MarcaDao().obtener(c.getId_marca());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        switch (i1) {
            case 0:
                return (c != null) ? c.getNombre() : "No definido";
            case 1:
                return (c != null) ? c.getPrecio() : "No definido";
            case 2:
                return (m != null) ? m.getNombre() : "No definido";
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nombre";
            case 1:
                return "Precio";
            case 2:
                return "Marca";
            default:
                return null;
        }
    }

    /**
     * @return the lista
     */
    public ListaEnlazada<Computadora> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Computadora> lista) {
        this.lista = lista;
    }

}
