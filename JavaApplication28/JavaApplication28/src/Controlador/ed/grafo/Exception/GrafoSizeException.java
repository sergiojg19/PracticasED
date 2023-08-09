/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador.ed.grafo.Exception;

/**
 *
 * @author Mateo
 */
public class GrafoSizeException extends Exception{

    public GrafoSizeException(String msg) {
        super(msg);
    }
    
    public GrafoSizeException() {
        super("Accedio a un tamaño fuera del grafo");   
    }
}
