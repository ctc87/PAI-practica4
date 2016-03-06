package javaAHTML;

import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import constantesHtml.ConstantesHtml;

/**
 * <h1>JavaAHTML</h1>
 * JavaAHTML es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 *  JavaAHTML es una clase que a partir de un archivo de java,
 *  genera un html.<br/>
 *  
 * <h4>Salida<h4/>
 *  Palabras reservadas: negrita.<br/>
 *  Cadenas literales: amarillo.<br/>
 *  Comentarios: Azul.<br/>
 *  Resto: negro.<br/>
 * @see ConstantesHtml
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  5 de mar. de 2016 
 */
public class JavaAHTML {

    /**
     * Expr constante para el inicio de un comentario de bloque.
     */
    private final static Pattern COMENTARIOS_BLOQUE_JAVA_REGEXP_INICIO = Pattern.compile("^((/\\*)+(.)*)");
    
    /**
     * Expr constante para el final de un comentario de bloque.
     */
    private final static Pattern COMENTARIOS_BLOQUE_JAVA_REGEXP_FINAL = Pattern.compile("(.)*(\\*/)");
    
    /**
     * Expr constante para un comentario de línea.
     */
    private final static Pattern COMENTARIOS_LINEA = Pattern.compile("(//)(.)*");
    
    /**
     * Expr constante para el inicio de una cadena literal.
     */
    private final static Pattern CADENAS_LITERALES_INICIO = Pattern.compile("\"(.)*");
    
    /**
     * Expr constante para el final de una cadena literal.
     */
    private final static Pattern CADENAS_LITERALES_FINAL = Pattern.compile("(.)+\"(.)*");

    /**
     * ArrayList que contiene un ArrayList por línea de fichero de salida.
     */
    private ArrayList<ArrayList<String>> htmlLineaALInea;

    /**
     * Frame principal para la ventana .
     */
    private JFrame frame;
    
    /**
     * Paneles contenedores para la ventana.
     */
    private JPanel panelSuperior, panelInferior;

    /**
     * Constructor de la clase. Lee del archivo java y escribe en el html.
     * Además también implementa la interfaz.
     * @param in Archivo de entrada.
     * @param out Archivo de salida
     * @throws IOException
     */
    public JavaAHTML(String in, String out) throws IOException {
        leer(in);
        escribir(out);
        construyePanelSuperior();
        construyePanelInferior();
        construyeVentana();
    }

    /**
     * Método que devuelve verdadero si recibe una palabra reservada de java.
     * @param keyword palabra clave
     * @return boolean
     */
    public static boolean esPalabraReservadaJava(String keyword) {
        final String keywords[] = { "abstract", "assert", "boolean",
                "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "extends", "false",
                "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native",
                "new", "null", "package", "private", "protected", "public",
                "return", "short", "static", "strictfp", "super", "switch",
                "synchronized", "this", "throw", "throws", "transient", "true",
                "try", "void", "volatile", "while" };
        return (Arrays.binarySearch(keywords, keyword) >= 0);
    }

    /**
     * Método que lee y formatea en html, metiéndolo en el ArrayList línea a línea.
     * Para ello utiliza el método esPalabraReservadaJava y las expr definidas.
     * @param in Archivo de entrada
     * @throws IOException 
     */
    private void leer(String in) throws IOException {
        setHtmlLineaALInea(new ArrayList<ArrayList<String>>());
        BufferedReader bf = new BufferedReader(new FileReader(in));
        String linea;
        ArrayList<String> lineaActual;
        while ((linea = bf.readLine())!=null) {
            lineaActual = new ArrayList<String>();
            Matcher matcherInicioComentarioBloque = COMENTARIOS_BLOQUE_JAVA_REGEXP_INICIO.matcher(linea);
            Matcher matcherFinalComentarioBloque = COMENTARIOS_BLOQUE_JAVA_REGEXP_FINAL.matcher(linea);
            if(matcherInicioComentarioBloque.matches()) {
                linea = ConstantesHtml.SPAN_TAG_START_BLUE + linea;
                while(!matcherFinalComentarioBloque.matches()) {
                    lineaActual.add(linea);
                    matcherFinalComentarioBloque = COMENTARIOS_BLOQUE_JAVA_REGEXP_FINAL.matcher(linea);
                    getHtmlLineaALinea().add(lineaActual);
                    lineaActual = new ArrayList<String>();
                    linea = bf.readLine();
                }
                linea += ConstantesHtml.SPAN_TAG_END;
                lineaActual.add(linea);
                getHtmlLineaALinea().add(lineaActual);
            } else {
                boolean terminadoComentarioLinea = true;
                String[] arr = linea.split(" ");    
                for(String key : arr) {
                    //key = key.trim();
                    Matcher matcherComentarioLinea = COMENTARIOS_LINEA.matcher(key);
                    Matcher matcherCadenaLiteralFinal = CADENAS_LITERALES_FINAL.matcher(key);
                    Matcher matcherCadenaLiteralInico = CADENAS_LITERALES_INICIO.matcher(key);
                    if(esPalabraReservadaJava(key)) {
                        lineaActual.add(ConstantesHtml.SPAN_TAG_START_BOLD
                                + key
                                + ConstantesHtml.SPAN_TAG_END
                                + " ");
                    } else if(matcherComentarioLinea.matches()) {
                        lineaActual.add(ConstantesHtml.SPAN_TAG_START_BLUE
                                + key 
                                + " ");
                        terminadoComentarioLinea = false;
                    } else if(matcherCadenaLiteralInico.matches()) {
                        if(matcherCadenaLiteralFinal.matches()) {
                            int ind = key.lastIndexOf("\"");
                            key = new StringBuilder(key).replace(ind, ind+1,"\"" + ConstantesHtml.SPAN_TAG_END).toString();
                        }
                        lineaActual.add(ConstantesHtml.SPAN_TAG_START_YELLOW
                                + key 
                                + " ");
                    } else {
                        if(matcherCadenaLiteralFinal.matches()) {
                            int ind = key.lastIndexOf("\"");
                            key = new StringBuilder(key).replace(ind, ind+1,"\"" + ConstantesHtml.SPAN_TAG_END).toString();
                        }
                        lineaActual.add(key + " ");
                    }
                }
                if(!terminadoComentarioLinea) {
                    lineaActual.add(ConstantesHtml.SPAN_TAG_END);
                }
                getHtmlLineaALinea().add(lineaActual);
            }
        }
        bf.close();
    }

    
    /**
     * Método que escribe en el archivo del parámetro la salida
     * formateada en html.
     * @param out Archivo de salida
     * @throws IOException 
     *	void
     */
    private void escribir(String out) throws IOException {        
        BufferedWriter bw = new BufferedWriter(new FileWriter(out));
        bw.write(ConstantesHtml.HTML_TAG_START);
        bw.newLine();
        for(ArrayList<String> lineaEnArray : getHtmlLineaALinea()) {
            for(String key : lineaEnArray) {
                bw.write(key + " ");
            }
            bw.write(ConstantesHtml.BR_TAG);
            bw.newLine();
        }
        bw.write(ConstantesHtml.HTML_TAG_END);
        bw.close();


    }

    /**
     *  Método que construye el panel superior de la GUI.
     */
    void construyePanelSuperior(){
        setPanelSuperior(new JPanel ()); 
        getPanelSuperior().setBorder(new EmptyBorder(10, 10, 50, 50));
        getPanelSuperior().setLayout(new GridLayout(2,2,5,5));
        
        TitledBorder titl = new TitledBorder("Traducción de java a html");
        titl.setTitleColor(Color.GRAY);
        Border border = getPanelSuperior().getBorder();
        getPanelSuperior().setBorder(new CompoundBorder(border, titl));
        
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText("Archivo de entrada java.");
        label2.setText("Archivo html de salida.");
        getPanelSuperior().add(label1);
        getPanelSuperior().add(new JTextField(20));
        getPanelSuperior().add(label2);
        getPanelSuperior().add(new JTextField(20));
    }


    /**
     *  Método que construye el panel inferior de la GUI.
     */
    void construyePanelInferior() {
        setPanelInferior(new JPanel ()); 
        getPanelInferior().setLayout(new FlowLayout());
        TitledBorder titl = new TitledBorder("Inicar Traduccion");
        titl.setTitleColor(Color.BLUE);
        getPanelInferior().setBorder(titl);
        getPanelInferior().add(new JButton("Iniciar"));

    }

    /**
     *  Método que construye la ventana con todos los paneles,
     *  inferior y superior.
     */
    void construyeVentana(){
        setFrame(new JFrame("De java a html"));
        getFrame().setLayout(new BoxLayout(getFrame().getContentPane(),BoxLayout.Y_AXIS));
        getFrame().add(getPanelSuperior());
        getFrame().add(getPanelInferior());
        getFrame().pack();
        getFrame().setVisible(true);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Getter.  {@link JavaAHTML#frame}
     * @return Retorna frame 
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Setter. {@link JavaAHTML#frame}
     * @param  frame en frame 
     */

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Getter.  {@link JavaAHTML#panelSuperior}
     * @return Retorna panelSuperior 
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Setter. {@link JavaAHTML#panelSuperior}
     * @param  panelSuperior en panelSuperior 
     */

    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Getter.  {@link JavaAHTML#panelInferior}
     * @return Retorna panelInferior 
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Setter. {@link JavaAHTML#panelInferior}
     * @param  panelInferior en panelInferior 
     */

    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Getter.  {@link JavaAHTML#htmlLineaALInea}
     * @return Retorna htmlLineaALInea 
     */
    public ArrayList<ArrayList<String>> getHtmlLineaALinea() {
        return htmlLineaALInea;
    }

    /**
     * Setter. {@link JavaAHTML#htmlLineaALInea}
     * @param  htmlLineaALInea en htmlLineaALInea 
     */

    public void setHtmlLineaALInea(ArrayList<ArrayList<String>> htmlLineaALInea) {
        this.htmlLineaALInea = htmlLineaALInea;
    }


}
