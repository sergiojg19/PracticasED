/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Utilidades;

import Controlador.ed.lista.Exception.PosicionException;
import Controlador.ed.lista.Exception.VacioException;
import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;

/**
 *
 * @author alejandro
 */
public class UtilidadesVistaGrafo {
    public static void cargarCombo(ListaEnlazada<Ciudad> lista, JComboBox cbx){
        cbx.removeAllItems();
        for (int i = 0; i < lista.size(); i++) {
            try {
                cbx.addItem(lista.get(i));
            } catch (Exception ex) {
                Logger.getLogger(UtilidadesVistaGrafo.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public static Ciudad obtenerComboBox(ListaEnlazada<Ciudad> lista, JComboBox cbx) throws Exception{
        return lista.get(cbx.getSelectedIndex());
    }
}
