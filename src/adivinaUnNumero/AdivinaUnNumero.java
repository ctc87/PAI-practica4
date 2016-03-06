package adivinaUnNumero;

import java.awt.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.border.*;
import constantesHtml.*;

/**
 * <h1>AdivinaUnNumero</h1>
 * AdivinaUnNumero es una ejercicio de una práctica de la asignatura: <br/>
 * <em>Programación de aplicaciones interactivas</em> <br/>
 *  AdivinaUnNumero es la clase base de un juego de azar. El juego trata
 *  básicamente sobre adivinar un número de tres cifras o algunas de
 *  sus cifras<br/>
 *  
 *  
 * <h4>Premios<h4/>
 *  Número completo: 1000 Euros <br/>
 *  Tres cifra: 3000 Euros <br/>
 *  Dos cifra: 2000 Euros <br/>
 *  Una cifra: 1000 Euros <br/>
 * <br/>
 * @see Scanner
 * @see ConstantesHtml
 * @author  Carlos Troyano Carmona
 * @version 1.0  
 * @since  5 de mar. de 2016 
 * 
 */
public class AdivinaUnNumero {
    /**
     * Constante que indica el mínimo número.
     */
    private final static Integer MIN = 100;
    
    /**
     * Constante que indica el máximo número.
     */
    private final static Integer MAX = 999;
    
    /**
     * Constante que indica el numero de dígitos.
     */
    private final static Integer DGITS = 3;
    
    /**
     * Constante de premio.
     */
    private final static Integer PREMIO1 = 10000;
    
    /**
     * Constante de premio.
     */
    private final static Integer PREMIO2 = 3000;
    
    /**
     * Constante de premio.
     */
    private final static Integer PREMIO3 = 2000;

    /**
     * Constante de premio.
     */
    private final static Integer PREMIO4 = 1000;

    /**
     * Frame principal para la ventana .
     */
    private JFrame frame;
    
    /**
     * Paneles contenedores para la ventana.
     */
    private JPanel panelSuperior, panelInferior;
    
    /**
     * Entrada de número.
     */
    private JTextField numeroIn;
    
    /**
     * Numeros para adivinar y de entrada.
     */
    private Integer numeroEntrada, numeroAleatorio;
    
    /**
     * Premio a recibir.
     */
    private Integer totalPremio = 0;

    /**
     * Cadena con la que se informa de los premios. 
     */
    private String premiosStr = "Usted a ganado:";

    /**
     * Constructor de la clase {@link AdivinaUnNumero}. Ejecuta secuencialmente
     * el juego y la interfaz gráfica.
     */
    public AdivinaUnNumero() {
        pideNumero();
        calculaPremio(numeroEntrada);
        construyePanelSuperior();
        construyePanelInferior();
        construyeVentana();
    }

    /**
     * Método caclulaPremio. Recibe el número de entrada del usuario
     * y calcula que premio hay que darle teniendo en cuenta los aciertos.
     * @param num 
     */
    private void calculaPremio(Integer num) {
        String numEntrada = getNumeroEntrada().toString();
        String numAdivinar = getNumeroAleatorio().toString();
        if(numEntrada.equals(numAdivinar)) {
            setPrimosStr(getPrimosStr() + " " + PREMIO1);
            setTotalPremio(getTotalPremio() + PREMIO1);
            System.out.println("Has ganado " + PREMIO1);
        } else {
            int aciertos = 0;
            for(int i = 0; i < DGITS; i++)
                for(int j = 0; j < DGITS; j++)
                    if(numEntrada.charAt(i) == numAdivinar.charAt(j))
                        aciertos ++;
            switch(aciertos) {
            case 1:
                setPrimosStr(getPrimosStr() + " " + PREMIO4);
                break;
            case 2:
                setPrimosStr(getPrimosStr() + " " + PREMIO3);
                break;
            case 3:
                setPrimosStr(getPrimosStr() + " " + PREMIO2);
                break;
            }
        }
    }

    /**
     * Método pideNumero. Pide al usuario un número de tres cifras,
     * 100-999, en caso de recibir otra entrada vuelve a pedirlo.
     */
    private void pideNumero() {    
        setNumeroAleatorio(MIN + (int)(Math.random() * ((MAX - MIN) + 1))); 
        System.out.println(getNumeroAleatorio());
        setNumeroEntrada(MIN - 1);
        Scanner sc = new Scanner(System.in);
        while(getNumeroEntrada() > MAX || getNumeroEntrada() < MIN) {
            System.out.println(" Adivina el número, introduce un número de tres cifras: ");
            setNumeroEntrada(sc.nextInt());
        }
        sc.close();
        System.out.println(getNumeroEntrada().toString());
    }

    /**
     *  Método que construye el panel superior de la GUI.
     */
    void construyePanelSuperior(){
        setPanelSuperior(new JPanel ()); 
        getPanelSuperior().setLayout(new FlowLayout());
        setPantalla(new JTextField(20)); 
        TitledBorder titl = new TitledBorder("Introduce un número \n de tres cifras:");
        titl.setTitleColor(Color.GRAY);

        getPanelSuperior().setBorder(titl);
        getPanelSuperior().add(getPantalla());
    }

    /**
     *  Método que construye el panel inferior de la GUI.
     */
    void construyePanelInferior(){
        setPanelInferior(new JPanel()); 
        getPanelInferior().setBorder(new EmptyBorder(10, 10, 10, 10));
        getPanelInferior().setLayout(new GridLayout(2,2,4,4));
        JLabel label1 = new JLabel();
        JLabel label2 = new JLabel();
        JLabel label3 = new JLabel();
        
        label1.setText(
                ConstantesHtml.HTML_TAG_START + ConstantesHtml.H2_TAG_START
                + "Su número es el: " + getNumeroEntrada().toString()  
                + ConstantesHtml.H2_TAG_END +  ConstantesHtml.HTML_TAG_END
                );
        label2.setText(ConstantesHtml.HTML_TAG_START + ConstantesHtml.H2_TAG_START
                + "El número que debía adivinar es:" + getNumeroAleatorio()
                + ConstantesHtml.H2_TAG_END +  ConstantesHtml.HTML_TAG_END
                );
        label3.setText(ConstantesHtml.HTML_TAG_START + ConstantesHtml.H2_TAG_START
                + getPrimosStr() + " Euros."
                +  ConstantesHtml.H2_TAG_END +  ConstantesHtml.HTML_TAG_END
                );
        
        getPanelInferior().add(label1);
        getPanelInferior().add(label2);
        getPanelInferior().add(label3);
    }

    /**
     *  Método que construye la ventana con todos los paneles,
     *  inferior y superior.
     */
    void construyeVentana(){
        setFrame(new JFrame("Calculadora "));
        getFrame().setLayout(new BoxLayout(getFrame().getContentPane(),BoxLayout.Y_AXIS));
        getFrame().add(getPanelSuperior());
        getFrame().add(getPanelInferior());
        getFrame().pack();
        getFrame().setVisible(true);
        getFrame().setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Getter.  {@link AdivinaUnNumero#frame}
     * @return Retorna frame 
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Setter. {@link AdivinaUnNumero#frame}
     * @param  frame en frame 
     */
    
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#panelSuperior}
     * @return Retorna panelSuperior 
     */
    public JPanel getPanelSuperior() {
        return panelSuperior;
    }

    /**
     * Setter. {@link AdivinaUnNumero#panelSuperior}
     * @param  panelSuperior en panelSuperior 
     */
    
    public void setPanelSuperior(JPanel panelSuperior) {
        this.panelSuperior = panelSuperior;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#panelInferior}
     * @return Retorna panelInferior 
     */
    public JPanel getPanelInferior() {
        return panelInferior;
    }

    /**
     * Setter. {@link AdivinaUnNumero#panelInferior}
     * @param  panelInferior en panelInferior 
     */
    
    public void setPanelInferior(JPanel panelInferior) {
        this.panelInferior = panelInferior;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#numeroIn}
     * @return Retorna pantalla 
     */
    public JTextField getPantalla() {
        return numeroIn;
    }

    /**
     * Setter. {@link AdivinaUnNumero#numeroIn}
     * @param  pantalla en pantalla 
     */
    
    public void setPantalla(JTextField pantalla) {
        this.numeroIn = pantalla;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#numeroEntrada}
     * @return Retorna numeroEntrada 
     */
    public Integer getNumeroEntrada() {
        return numeroEntrada;
    }

    /**
     * Setter. {@link AdivinaUnNumero#numeroEntrada}
     * @param  numeroEntrada en numeroEntrada 
     */
    
    public void setNumeroEntrada(Integer numeroEntrada) {
        this.numeroEntrada = numeroEntrada;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#numeroAleatorio}
     * @return Retorna numeroAleatorio 
     */
    public Integer getNumeroAleatorio() {
        return numeroAleatorio;
    }

    /**
     * Setter. {@link AdivinaUnNumero#numeroAleatorio}
     * @param  numeroAleatorio en numeroAleatorio 
     */
    
    public void setNumeroAleatorio(Integer numeroAleatorio) {
        this.numeroAleatorio = numeroAleatorio;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#totalPremio}
     * @return Retorna totalPremio 
     */
    public Integer getTotalPremio() {
        return totalPremio;
    }

    /**
     * Setter. {@link AdivinaUnNumero#totalPremio}
     * @param  totalPremio en totalPremio 
     */
    
    public void setTotalPremio(Integer totalPremio) {
        this.totalPremio = totalPremio;
    }

    /**
     * Getter.  {@link AdivinaUnNumero#premiosStr}
     * @return Retorna primosStr 
     */
    public String getPrimosStr() {
        return premiosStr;
    }

    /**
     * Setter. {@link AdivinaUnNumero#premiosStr}
     * @param  primosStr en primosStr 
     */
    
    public void setPrimosStr(String primosStr) {
        this.premiosStr = primosStr;
    }

    public static void main(String [] inforux) {
        new AdivinaUnNumero();
    }
}