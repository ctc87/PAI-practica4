package numerosHexadecimales;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;


/**
 * <h1>NumerosHexademiales</h1>
 * NumerosHexademiales es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 * NumerosHexademiales es una clase que convierte números Hexadecimales
 * en números decimales. Para ello cuenta con una excepción personalizada
 * {@link HexFormatException}.<br/>
 * Cuenta con interfaz gráfica de usuario con botones personalizados. 
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  6 de mar. de 2016 
 */
public class NumerosHexademiales { 
    /**
     * Constante porcentaje iconos. 
     */
    static final Float PORCENTAJE_NUEVO_TAMANYO_ICONO = (float) 1;
    /**
     * Constante cantidad de números a traducir
     */
    static final Integer CANTIDAD_NUMEROS = 1;
    
    /**
     * Expr constante para números hexadecimales.
     */
    private final static Pattern NUMERO_HEXADECIMAL = Pattern.compile("(\\d|[A-Fa-f])+");
    
    /**
     * ArrayList que contiene los números hexadecimales usados durante la ejecución.
     */
    private ArrayList<String> hexadecimales;
    
    /**
     * ArrayList que contiene los números decimales usados durante la ejecución.
     */
    private ArrayList<String> decimales;

    /**
     * Frame principal para la ventana .
     */
    private JFrame frame;
    
    /**
     * Paneles contenedores para la ventana.
     */
    private JPanel panelSuperior, panelInferior;

    /**
     * Constructor de la clase. Lee números y los convierte en Hexadeciamles.<br/>
     * Además también implementa la interfaz.
     */
    public NumerosHexademiales() {
        setHexadecimales(new ArrayList<String>());
        setDecimales(new ArrayList<String>());
        leerNumeros( );
        construyePanelSuperior();
        construyePanelInferior();
        construyeVentana();
    }

   
    /**
     * Método que tiene un for para leer N números.
     */
    private void leerNumeros() {
        for (int i = 0; i < CANTIDAD_NUMEROS; i++) {
            leerUnNumero();  
        }      
    }

    /**
     * Método que lee un solo número y hace una llamada a la conversión
     * a hexadecimal.
     */
    private void leerUnNumero() {
        System.out.println("Introduzca un número entero.");
        Scanner sc = new Scanner(System.in);
        String aux = sc.next();
        try {
            getDecimales().add(String.valueOf((hex2Dec(aux).intValue())));
            System.out.println(String.valueOf((hex2Dec(aux).intValue())));
            getHexadecimales().add(aux);
        } catch (HexFormatException e) {
            System.out.println(e.getMessage());
            leerNumeros();
        } finally {
            sc.close();
        }
    }
    
    /**
     * Método que comprueba si una cadena es un hexadecimal. Si no lo es lanza una
     * {@link HexFormatException}. Si lo es devuelve un Long que representa al número.  
     * @param hexStr Número hexadecimal en formato String.
     * @return Long
     * @throws HexFormatException 
     */
    private Long hex2Dec(String hexStr) throws HexFormatException {
        Matcher matcherHexadecimal = NUMERO_HEXADECIMAL.matcher(hexStr);
        if(!matcherHexadecimal.matches()) {
                throw new HexFormatException(new NumberFormatException(), hexStr+ " no es formato Hexadecimal.");
        }
        return  Long.parseLong(hexStr, 16);
    }

    /**
     *  Método que construye el panel superior de la GUI.
     */
    void construyePanelSuperior(){
        setPanelSuperior(new JPanel ()); 
        getPanelSuperior().setBorder(new EmptyBorder(10, 10, 50, 50));
        getPanelSuperior().setLayout(new GridLayout(2,2,5,5));
        
        TitledBorder titl = new TitledBorder("Introduzca un número hexadecimal");
        titl.setTitleColor(Color.BLUE);
        Border border = getPanelSuperior().getBorder();
        getPanelSuperior().setBorder(new CompoundBorder(border, titl));
        
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        label1.setText("Número hexadecimal");
        label2.setText("Número decimal");
        getPanelSuperior().add(label1);
        getPanelSuperior().add(label2);
        JTextField jt1 = new JTextField(20);
        JTextField jt2 = new JTextField(20);
        jt1.setText(getHexadecimales().get(0));
        jt2.setText(getDecimales().get(0));
        getPanelSuperior().add(jt1);
        getPanelSuperior().add(jt2);
    }


    /**
     *  Método que construye el panel inferior de la GUI.
     */
    
    void construyePanelInferior() {
        setPanelInferior(new JPanel ()); 
        getPanelInferior().setBorder(new EmptyBorder(0, 10, 30, 10));
        getPanelInferior().setLayout(new FlowLayout());
        JButton button = new JButton();
        try {
          Image img1 = ImageIO.read(getClass().getResource("./submit-button04.png"));
          Image img2 = ImageIO.read(getClass().getResource("./submit-button02.png"));
          Image img3 = ImageIO.read(getClass().getResource("./submit-button03.png"));
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
        
        getPanelInferior().add(button);

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
     * Getter.  {@link NumerosHexademiales#frame}
     * @return Retorna frame 
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Setter. {@link NumerosHexademiales#frame}
     * @param  frame en frame 
     */

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Getter.  {@link NumerosHexademiales#panelSuperior}
     * @return Retorna panelSuperior 
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Setter. {@link NumerosHexademiales#panelSuperior}
     * @param  panelSuperior en panelSuperior 
     */

    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Getter.  {@link NumerosHexademiales#panelInferior}
     * @return Retorna panelInferior 
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Setter. {@link NumerosHexademiales#panelInferior}
     * @param  panelInferior en panelInferior 
     */

    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Getter.  {@link NumerosHexademiales#hexadecimales}
     * @return Retorna hexadecimales 
     */
    public ArrayList<String> getHexadecimales() {
        return hexadecimales;
    }


    /**
     * Setter. {@link NumerosHexademiales#hexadecimales}
     * @param  hexadecimales en hexadecimales 
     */
    
    
    public void setHexadecimales(ArrayList<String> hexadecimales) {
        this.hexadecimales = hexadecimales;
    }

    /**
     * Getter.  {@link NumerosHexademiales#decimales}
     * @return Retorna decimales 
     */
    public ArrayList<String> getDecimales() {
        return decimales;
    }


    /**
     * Setter. {@link NumerosHexademiales#decimales}
     * @param  decimales en decimales 
     */
    
    public void setDecimales(ArrayList<String> decimales) {
        this.decimales = decimales;
    }


    public static void main(String[] args) {
        new NumerosHexademiales();
    }

}
