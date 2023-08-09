/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Vista.Utilidades;

import Controlador.DAO.CiudadDao;

import Controlador.ed.lista.ListaEnlazada;
import Modelo.ejgrafo.Ciudad;

import Modelo.ejgrafo.TipoDistancia;

import javax.swing.JComboBox;

/**
 *
 * @author sergio
 */
public class UtilidadesVista {
    public static void cargarCuenta(JComboBox cbx, CiudadDao cd) throws Exception{
        cbx.removeAllItems();
        ListaEnlazada<Ciudad> lista = cd.listar();
        for (int i = 0; i < lista.size(); i++) {
            cbx.addItem(lista.get(i).getNom_Ciudad());
        }
    }
   
    public static void cargarTipoTransaccion(JComboBox cbx)throws Exception{
        cbx.removeAllItems();
        
        for (TipoDistancia tipo : TipoDistancia.values()) {
            cbx.addItem(tipo);
        }
        }
}
