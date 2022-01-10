package Vrum;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Corrida.CorridaCarro;
import Entidade.Carro;

public class Main extends JFrame{

    public static final int QNTD_Carros = 5;
    public static final int DISTANCIA_TOTAL = 1000;
    
    private Carro _Carro;
    
    private JPanel painel;
   
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
    	
    	Main frame = new Main();
      
        
    }
    public Main() {	
    	setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1160, 600);
        setResizable(false);

        JLabel fundo = new JLabel(); 
        fundo.setBounds(0, 0, 1250, 630);
        fundo.setIcon(new ImageIcon("imagens/fundo.png"));

        JLabel delorean = new JLabel("");
        delorean.setBounds(-180, 160, 328, 129);
        delorean.setIcon(new ImageIcon("imagens/delorean.png"));

        add(delorean);
        
        JLabel Car2 = new JLabel("");
        Car2.setBounds(-180, 60, 328, 129);
        Car2.setIcon(new ImageIcon("imagens/delorean.png"));

        add(Car2);
        
        JLabel Car3 = new JLabel("");
        Car3.setBounds(-180, -30, 328, 129);
        Car3.setIcon(new ImageIcon("imagens/delorean.png"));

        add(Car3);
        
        JLabel Car4 = new JLabel("");
        Car4.setBounds(-180,  290, 328, 129);
        Car4.setIcon(new ImageIcon("imagens/delorean.png"));

        add(Car4);
        
        JLabel Car5 = new JLabel("");
        Car5.setBounds(-180, 410, 328, 129);
        Car5.setIcon(new ImageIcon("imagens/delorean.png"));
        
        this._Carro = new Carro(getName()) ;

        add(Car5);

        add(fundo);

        repaint(100);
        
        

        CorridaCarro corridaCarro = new CorridaCarro(QNTD_Carros, DISTANCIA_TOTAL, delorean, Car2, Car3, Car4, Car5);

        corridaCarro.preparar();
        corridaCarro.iniciar();
        corridaCarro.exibirResultado();
    }
}

