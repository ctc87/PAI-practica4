package excepcionesEnNumeros;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 * <h1>ExcepcionesEnNumeros</h1>
 * ExcepcionesEnNumeros es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 *  ExcepcionesEnNumeros  es una clase que suma varios números enteros. Y comprueba que
 *  efectivamente son enteros. <br/>
 *  Cuenta con interfaz gráfica de usuario(No habilitada).
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  6 de mar. de 2016 
 */
public class ExcepcionesEnNumeros {
    /**
     * Constante porcentaje iconos. 
     */
    static final Float PORCENTAJE_NUEVO_TAMANYO_ICONO = (float) 0.5;
    
    /**
     * Constante de cantidad de número a sumar.
     */
    static final Integer CANTIDAD_NUMEROS = 2;

    /**
     * Array que guarda los números.
     */
    private ArrayList<Integer> numeros;

    /**
     * Frame principal para la ventana .
     */
    private JFrame frame;
    
    /**
     * Paneles contenedores para la ventana.
     */
    private JPanel panelSuperior, panelInferior, panelBoton;
    
    /**
     * Panel para la lista.
     */
    private JTextPane pane;
    
    /**
     * Cadena que contiene todos los números a sumar.
     */
    private String lista;

    /**
     * Constructor de la clase. Inicializa la lista de números a sumar con una cabecera,
     * y pide números por la entrada iguales a la constante {@link ExcepcionesEnNumeros#CANTIDAD_NUMEROS}.<br>
     * También inicializa la interfaz gráfica.
     * @throws BadLocationException
     */
    ExcepcionesEnNumeros() throws BadLocationException {
        setNumeros(new ArrayList<Integer>(CANTIDAD_NUMEROS));
        setLista("SUMAR:\n");
        for (int i = 0; i < CANTIDAD_NUMEROS; i++) {
            entradaNumeros();
        }
        System.out.println("La suma de los número es" + sumar());
        construyePanelSuperior();
        construyePanelInferior();
        construyePanelBoton();
        construyeVentana();
    }

    /**
     * Método sumar. Suma todos los números del array.
     * @return Integer Suma de todos los números.
     */
    private Integer sumar() {
        Integer aux = 0;
        for (int i = 0; i < CANTIDAD_NUMEROS; i++) {
            aux += getNumeros().get(i);
        }
        return aux;
    }

    /**
     * Método que inserta un solo número en el array. Si falla lanza una excepción
     * {@link InputMismatchException}. 
     * @throws InputMismatchException 
     */
    private void insertarUnNumero() throws InputMismatchException {
        System.out.println("Introduzca un número entero.");
        Scanner sc = new Scanner(System.in);
        Integer aux = sc.nextInt();
        getNumeros().add(aux);
        setLista(getLista() + aux + "\n");
        //sc.close();
    }

    /**
     * Método que recoge y gestiona las excepciones de @link( {@link ExcepcionesEnNumeros#insertarUnNumero}.
     * Mientras haya números que insertar.
     */
    private void entradaNumeros() {
        try {
            insertarUnNumero();
        } catch (InputMismatchException e) {
            System.out.println("No se inserto un número entero válido");
            entradaNumeros();
        }

    }

    /**
     *  Método que construye el panel superior de la GUI.
     * @throws BadLocationException 
     */
    void construyePanelSuperior() throws BadLocationException{
        setPanelSuperior(new JPanel()); 
        getPanelSuperior().setBorder(new EmptyBorder(10, 50, 0, 50));
        getPanelSuperior().setLayout(new GridLayout(2,1,1,1));
        
        StyleContext sc = new StyleContext();
        final DefaultStyledDocument doc = new DefaultStyledDocument(sc);
        pane = new JTextPane(doc);
        
        final Style heading2Style = sc.addStyle("Heading2", null);
        heading2Style.addAttribute(StyleConstants.Foreground, Color.MAGENTA);
        heading2Style.addAttribute(StyleConstants.FontSize, new Integer(16));
        heading2Style.addAttribute(StyleConstants.FontFamily, "serif");
        heading2Style.addAttribute(StyleConstants.Bold, new Boolean(true));
        
        doc.insertString(0, lista, null);
        System.out.println(lista);
        doc.setParagraphAttributes(0, 1, heading2Style, false);
        JLabel label1 = new JLabel();
        label1.setText("Número enteros a sumar");
        getPanelSuperior().add(label1);

    }

    /**
     *  Método que construye el panel inferior de la GUI.
     * @throws BadLocationException 
     */
    void construyePanelInferior() throws BadLocationException{
        setPanelInferior(new JPanel ()); 
        getPanelInferior().setBorder(new EmptyBorder(10, 10, 10, 10));
        getPanelInferior().setLayout(new GridLayout(1,2,5,5));
        JLabel label1 = new JLabel();
        label1.setText("Suma");
        label1.setHorizontalAlignment(SwingConstants.CENTER);
        getPanelInferior().add(label1);
        JTextField text = new JTextField(10);
        text.setText(sumar().toString());
        getPanelInferior().add(text);
        

    }

    /**
     *  Método que construye el panel del botón de la GUI.
     * @throws BadLocationException 
     */
    void construyePanelBoton() {
        setPanelBoton(new JPanel ()); 
        getPanelBoton().setBorder(new EmptyBorder(10, 10, 20, 10));
        getPanelBoton().setLayout(new FlowLayout());
        JButton button = new JButton();
        try {
          Image img1 = ImageIO.read(getClass().getResource("./addBlue.png"));
          Image img2 = ImageIO.read(getClass().getResource("./addGreen.png"));
          Image img3 = ImageIO.read(getClass().getResource("./addRed.png"));
          img1 = img1.getScaledInstance((int) (img1.getWidth(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), (int) (img1.getHeight(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), Image.SCALE_DEFAULT);
          img2 = img2.getScaledInstance((int) (img2.getWidth(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), (int) (img2.getHeight(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), Image.SCALE_DEFAULT);
          img3 = img3.getScaledInstance((int) (img3.getWidth(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), (int) (img3.getHeight(button) * PORCENTAJE_NUEVO_TAMANYO_ICONO), Image.SCALE_DEFAULT);
          button.setIcon(new ImageIcon(img1));
          button.setRolloverIcon(new ImageIcon(img2));
          button.setPressedIcon(new ImageIcon(img3));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        button.setBorderPainted(false);
        button.setBorder(null);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setContentAreaFilled(false);
        
        getPanelBoton().add(button);
 

    }

    /**
     *  Método que construye la ventana con todos los paneles,
     *  inferior, superior y botón. 
     */
    void construyeVentana(){
        setFrame(new JFrame("Suma de numeros enteros"));
        getFrame().setLayout(new BoxLayout(getFrame().getContentPane(),BoxLayout.Y_AXIS));
        getFrame().add(getPanelSuperior());
        JScrollPane jsp = new JScrollPane(pane);
        jsp.setBorder(new EmptyBorder(10, 10, 10, 10));
        getFrame().getContentPane().add(jsp);
        getFrame().add(getPanelInferior());
        getFrame().add(getPanelBoton());
        getFrame().pack();
        getFrame().setVisible(true);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#numeros}
     * @return Retorna numeros 
     */
    public ArrayList<Integer> getNumeros() {
        return numeros;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#numeros}
     * @param  numeros en numeros 
     */


    public void setNumeros(ArrayList<Integer> numeros) {
        this.numeros = numeros;
    }



    /**
     * Getter.  {@link ExcepcionesEnNumeros#frame}
     * @return Retorna frame 
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#frame}
     * @param  frame en frame 
     */
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#panelSuperior}
     * @return Retorna panelSuperior 
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#panelSuperior}
     * @param  panelSuperior en panelSuperior 
     */
    
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#panelInferior}
     * @return Retorna panelInferior 
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#panelInferior}
     * @param  panelInferior en panelInferior 
     */
    
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#pane}
     * @return Retorna pane 
     */
    public JTextPane getPane() {
        return pane;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#pane}
     * @param  pane en pane 
     */
    
    public void setPane(JTextPane pane) {
        this.pane = pane;
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#lista}
     * @return Retorna lista 
     */
    public String getLista() {
        return lista;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#lista}
     * @param  lista en lista 
     */
    
    public void setLista(String lista) {
        this.lista = lista;
    }

    /**
     * Getter.  {@link ExcepcionesEnNumeros#panelBoton}
     * @return Retorna panelBoton 
     */
    public JPanel getPanelBoton() {
        return panelBoton;
    }

    /**
     * Setter. {@link ExcepcionesEnNumeros#panelBoton}
     * @param  panelBoton en panelBoton 
     */
    
    
    public void setPanelBoton(JPanel panelBoton) {
        this.panelBoton = panelBoton;
    }

    public static void main(String[] args) throws BadLocationException {
        new ExcepcionesEnNumeros();
    }



}
