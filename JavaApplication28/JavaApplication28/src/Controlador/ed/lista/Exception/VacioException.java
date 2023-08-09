/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package Controlador.ed.lista.Exception;

/**
 *
 * @author Mateo
 */
public class VacioException extends Exception{

    /**
     * Creates a new instance of <code>VacioException</code> without detail
     * message.
     */
    public VacioException() {
        super("Lista vacia y, por tanto, no contiene ningun tipo de información");
    }

    /**
     * Constructs an instance of <code>VacioException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public VacioException(String msg) {
        super(msg);
    }
}
