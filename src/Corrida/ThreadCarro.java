package Corrida;

import Entidade.Carro;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

public class ThreadCarro implements Runnable {

	
    private Thread thread;

    private JLabel delorean;
    private JLabel Car2;
    private JLabel Car3;
    private JLabel Car4;
    private JLabel Car5;
    private Carro carro;
    
    private Semaphore SM;

    private static final int MAXIMO_DESCANSO = 500;
    private static final int MAXIMO_Andamento = 50;

    private int distanciaTotal;
    private int distanciaPercorrida = 0;
    private  ArrayList<Integer> listaPercorrida;
    private int ultimoPulo;
    
    private CorridaCarro car;
    
    private ArrayList<Carro> _ListaCarros; 
    private CorridaController corridaControle;

    public ThreadCarro(Carro Carrinho, Integer distanciaTotal, JLabel Carro, JLabel Car2, JLabel Car3, JLabel Car4, JLabel Car5) {
      
    	this.carro = Carrinho;
        this.distanciaTotal = distanciaTotal;
        this.corridaControle = CorridaController.getInstance();
        this.delorean = Carro;
        this.Car2 = Car2;
        this.Car3 = Car3;
        this.Car4 = Car4;
        this.Car5 = Car5;
        
        _ListaCarros = new ArrayList<Carro>();
        
        carro.getListaCarros();
        
        
        
        _ListaCarros.add(Carrinho);
        
        
       this.carro.setCarros(_ListaCarros);
        
        for(Carro meuCarro : this.carro.getCarros()){
			meuCarro.getNome();
			thread = new Thread(this, Carrinho.getNome());  
	        SM = new Semaphore(1);
	        thread.start();
		}
        
        //thread = new Thread(this, Carrinho..getNome());  
        //SM = new Semaphore(1);

        //thread.start();
        
      
    }
    

    public void pular(Carro _carro) {

        ultimoPulo = (int) (Math.random() * MAXIMO_Andamento);
        distanciaPercorrida += ultimoPulo;  
       
        for(Carro meuCarro : _carro.getCarros()) 
        {
        	if(meuCarro.getNome().equals("Carro 1")) Car3.setBounds(distanciaPercorrida, -30, 328, 129);
        	if(meuCarro.getNome().equals("Carro 2")) Car2.setBounds(distanciaPercorrida, 60, 328, 129);
        	if(meuCarro.getNome().equals("Carro 3")) delorean.setBounds(distanciaPercorrida, 160, 328, 129);
        	if(meuCarro.getNome().equals("Carro 4")) Car4.setBounds(distanciaPercorrida, 290, 328, 129);
        	if(meuCarro.getNome().equals("Carro 5")) Car5.setBounds(distanciaPercorrida, 410, 328, 129);
        	
        	
        }
        
        if(distanciaPercorrida > distanciaTotal) {
    		distanciaPercorrida = distanciaTotal;
    	}
    }
    
    
    

    private void avisarSituacao() {
    	
        System.out.println(carro.getNome() + " Andou " + ultimoPulo + " M . A distancia percorrida foi de "
                + distanciaPercorrida + "M!");
    }

    private void cruzarLinhaDeChegada() {
        try {
            SM.acquire();
            corridaControle.carroChegou(carro);
            SM.release();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void descansar() {
        int tempo = (int) (Math.random() * MAXIMO_DESCANSO);
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        }
    }
		

    @Override
    public void run() {
    	while (distanciaPercorrida < distanciaTotal)
        {
			pular(this.carro);
			avisarSituacao();
			descansar();
        }
    	
        cruzarLinhaDeChegada();
        

		}
    }

    

