package palabrasNoDuplicadas;

import java.awt.Color;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;


/**
 * <h1>PalabrasNoDuplicadas</h1>
 * PalabrasNoDuplicadas es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 * PalabrasNoDuplicadas es una clase que recibe un archivo te texto
 * de entrada y imprime en una lista por pantalla, las palabras no repetidas
 * en orden alfabético inverso.
 * <br/>
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  6 de mar. de 2016 
 */
public class PalabrasNoDuplicadas {

    /**
     * Frame principal para la ventana .
     */
    private JFrame frame;
    
    /**
     * Paneles contenedores para la ventana.
     */
    private JPanel panelSuperior, panelInferior;
    
    /**
     * Entrada de archivo.
     */
    private JTextField archivoEntrada;
    
    /**
     * Panel para la lista.
     */
    JTextPane pane;
    
    /**
     * Cadena que contiene la lista final
     */
    private String lista;
    
    
    /**
     * Estructura de datos map que contiene la clave como 
     * la palabra y un booleano que indica si esta o no repetida.
     */
    HashMap<String, Boolean> noDuplicadas;
    
    /**
     * Constructor de la clase que analiza el archivo e inicializa la GUI.
     * @param in Archivo de entrada
     * @throws IOException
     * @throws BadLocationException
     */
    PalabrasNoDuplicadas(String in) throws IOException, BadLocationException{
        noDuplicadas = new HashMap<String, Boolean>();
        leer(in);
        imprimirResultados();
        construyePanelSuperior();
        construyePanelInferior();
        construyeVentana();
    }
    
    /**
     * Método que lee las palabras del archivo, ensartándolas 
     * en la estructura de datos correctamente formateadas.
     * @param in
     * @throws IOException 
     */
    private void leer(String in) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(in));
        String linea;
        while ((linea = bf.readLine())!=null) {
               String[] arr = linea.split(" ");
               for(String key : arr) {
                   key = key.replaceAll("[\\W]|_", "");
                   if(!getNoDuplicadas().containsKey(key)) {
                       getNoDuplicadas().put(key, true);
                   } else {
                       getNoDuplicadas().put(key, false);
                   }
               }
            }
        bf.close();
        }
    
    
    /**
     *  Método que traduce la estructura de datos a una cadena
     *  para utilizarla en la GUI.
     */
    public void imprimirResultados() {
        setLista("Orden alfabético inverso: \n");
        Iterator<String> it = getNoDuplicadas().keySet().iterator();
        System.out.println("Orden alfabético inverso");
        ArrayList<String> aux = new ArrayList<String>();
        while(it.hasNext()) {
          String key = it.next();
          if(getNoDuplicadas().get(key))
              aux.add(key);
        }
        Collections.sort(aux);
        Collections.reverse(aux);
        for (String palabra : aux) {
            System.out.println(palabra);
            setLista(getLista() +  palabra + "\n");  
        }
       }

    /**
     *  Método que construye el panel superior de la GUI.
     */
    void construyePanelSuperior(){
        setPanelSuperior(new JPanel ()); 
        getPanelSuperior().setBorder(new EmptyBorder(10, 10, 50, 50));
        getPanelSuperior().setLayout(new GridLayout(1,2,5,5));
        // Font font = new Font("Verdana", Font.BOLD, 9);
        TitledBorder titl = new TitledBorder("Traducción de java a html");
        titl.setTitleColor(Color.GRAY);
        Border border = getPanelSuperior().getBorder();
        getPanelSuperior().setBorder(new CompoundBorder(border, titl));
        JLabel label1 = new JLabel();
        label1.setText("Archivo de entrada.");
        getPanelSuperior().add(label1);
        getPanelSuperior().add(new JTextField(20));
    }

    /**
     *  Método que construye el panel inferior de la GUI.
     * @throws BadLocationException 
     */
    void construyePanelInferior() throws BadLocationException{
        setPanelInferior(new JPanel()); 
        getPanelInferior().setBorder(new EmptyBorder(10, 10, 10, 10));
        getPanelInferior().setLayout(new GridLayout(2,2,4,4));
        
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        pane = new JTextPane(doc);
        
        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.Foreground, Color.red);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));
        
        doc.insertString(0, lista, null);
        System.out.println(lista);
        doc.setParagraphAttributes(0, 1, heading2Style, false);

    }

    /**
     *  Método que construye la ventana con todos los paneles,
     *  inferior y superior.
     */
    void construyeVentana(){
        setFrame(new JFrame("Palabras no duplicadas"));
        getFrame().setLayout(new BoxLayout(getFrame().getContentPane(),BoxLayout.Y_AXIS));
        getFrame().add(getPanelSuperior());
        getFrame().add(getPanelInferior());
        getFrame().getContentPane().add(new JScrollPane(pane));
        getFrame().pack();
        getFrame().setVisible(true);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    /**
     * Getter.  {@link PalabrasNoDuplicadas#noDuplicadas}
     * @return Retorna noDuplicadas 
     */
    public HashMap<String, Boolean> getNoDuplicadas() {
        return noDuplicadas;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#noDuplicadas}
     * @param noDuplicadas en noDuplicadas 
     */
    
    public void setNoDuplicadas(HashMap<String, Boolean> noDuplicadas) {
        this.noDuplicadas = noDuplicadas;
    }

    /**
     * Getter.  {@link PalabrasNoDuplicadas#frame}
     * @return Retorna frame 
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#frame}
     * @param frame en frame 
     */
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Getter.  {@link PalabrasNoDuplicadas#panelSuperior}
     * @return Retorna panelSuperior 
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#panelSuperior}
     * @param panelSuperior en panelSuperior 
     */
    
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Getter.  {@link PalabrasNoDuplicadas#panelInferior}
     * @return Retorna panelInferior 
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#panelInferior}
     * @param panelInferior en panelInferior 
     */
    
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Getter.  {@link PalabrasNoDuplicadas#archivoEntrada}
     * @return Retorna numeroIn 
     */
    public JTextField getNumeroIn() {
        return archivoEntrada;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#archivoEntrada}
     * @param numeroIn en numeroIn 
     */
    
    public void setNumeroIn(JTextField numeroIn) {
        this.archivoEntrada = numeroIn;
    }

    /**
     * Getter.  {@link PalabrasNoDuplicadas#lista}
     * @return Retorna lista 
     */
    public String getLista() {
        return lista;
    }

    /**
     * Setter. {@link PalabrasNoDuplicadas#lista}
     * @param lista en lista 
     */
    
    public void setLista(String lista) {
        this.lista = lista;
    }

    public static void main(String[] args) throws IOException, BadLocationException {
       new PalabrasNoDuplicadas("PalabrasNoDuplicadas.java");
    }
    
}
