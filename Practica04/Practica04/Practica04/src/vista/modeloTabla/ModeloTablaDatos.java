/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package vista.modeloTabla;

import controlador.ed.lista.ListaEnlazada;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author sergio
 */
public class ModeloTablaDatos extends AbstractTableModel {
    private ListaEnlazada<Integer> lista = new ListaEnlazada<>();

    @Override
    public int getRowCount() {
        return getLista().size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Integer s = null;
        try {
            s = getLista().get(i);
        } catch (Exception e) {
        }
        switch (i1) {
            case 0:
                return s;
            
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Nro";
            default:
                return null;
        }
    }

    /**
     * @return the lista
     */
    public ListaEnlazada<Integer> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(ListaEnlazada<Integer> lista) {
        this.lista = lista;
    }

}
