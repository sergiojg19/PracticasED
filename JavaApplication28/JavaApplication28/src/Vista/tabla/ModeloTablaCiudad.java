/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista.tabla;

import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author alejandro
 */
public class ModeloTablaCiudad extends AbstractTableModel{
    private ListaEnlazada<Ciudad> lista = new ListaEnlazada<>();

    public ListaEnlazada<Ciudad> getLista() {
        return lista;
    }

    public void setLista(ListaEnlazada<Ciudad> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Ciudad c = null;
        try {
            c = lista.get(i);
        } catch (Exception e) {
        }
        switch (i1) {
            case 0: return c.getId();
            case 1: return c.getNom_Ciudad();     
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {// TODO add your handling code here:
            case 0: return "Id";
            case 1: return "Ciudad";          
            default:
                return null;
        }
    }  
}
