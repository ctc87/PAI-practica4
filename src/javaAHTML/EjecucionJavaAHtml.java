package javaAHTML; 

import java.io.IOException;

/**
 * <h1>EjecucionJavaAHtml</h1>
 * EjecucionJavaAHtml es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 *  EjecucionJavaAHtml es la clase que ejecuta un programa principal de
 *  la clase JavaAHTML. 
 * @see JavaAHTML
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  6 de mar. de 2016 
 */
public class EjecucionJavaAHtml {
    final static String STR = "Prueba" + 45 + "2Prieba class";
    final static String STR2 = "Prueba de varias palabras" + 45 + "2Prieba";
    
    public static void main(String [] inforux) throws IOException {
        new JavaAHTML("EjecucionJavaAHtml.java", "index.html" );
    }
}
