package numerosHexadecimales;
import java.io.Serializable;

/**
 * <h1>HexFormatException</h1>
 * HexFormatException es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 *  HexFormatException es la clase que crea una excepción de tipo checked
 *  hija de la clase Exception. Esta informa sobre un Error el formato de
 *  un número hexadecimal.
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  28 de feb. de 2016 
 * @see Exception
 * @see Serializable
 */
public class HexFormatException extends Exception {

/**
 * Constante de clase exigida en la interfaz {@link Serializable}. 
 * @see Serializable
 */
	private static final long serialVersionUID = 1L;

/**
 * Constructor con mensaje. Establece el mensaje de la Excepción llamando
 * al constructor de la clase padre Exception. 
 * @param msg
 */
	public HexFormatException(String msg) {
		super(msg);
	}

/**
 * Constructor con mensaje. Establece el mensaje de la Excepción llamando
 * al constructor de la clase padre Exception con el mensaje pasado por parámetro, 
 * más el mensaje de la excepción pasada por parámetro, además le pasa una 
 * excepción como parámetro para anidarla. 
 * @param msg String Mensaje para la excepción.
 * @param e Exception Excepción que se anida.
 */	
	public HexFormatException(Exception e, String msg) {
		super(e.toString() + " " +  msg, e);
	}
}
