package Corrida;

import Entidade.Carro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import javax.swing.JLabel;

public class CorridaCarro {

    private int distanciaTotal;

    private CorridaController corridaControle;

    public Carro _carro = new Carro(null);
    public ArrayList<Carro> CarroList;
    public ArrayList<Carro> _ListaCarros; 
    
    JLabel Delorean;
    
    JLabel Car2;
    JLabel Car3;
    JLabel Car4;
    JLabel Car5;
    
   
    public int quantidadeCarro;

    public CorridaCarro(int quantidadeCarros, int distanciaTotal, JLabel Delorean, JLabel Car2, JLabel Car3, JLabel Car4, JLabel Car5) {
    	this.Delorean = Delorean;
        this.distanciaTotal = distanciaTotal;
        this.quantidadeCarro = quantidadeCarros;
        this.Car2 = Car2;
        this.Car3 = Car3;
        this.Car4 = Car4;
        this.Car5 = Car5;
        
        this.corridaControle = CorridaController.getInstance();

        corridaControle.setQtdeCarros(quantidadeCarros);
        
        
    }

    public void preparar() {
    	
        CarroList = new ArrayList<>();
        System.out.println("A corrida vai começar. -  " + quantidadeCarro + " Carro correndo um percurso de " + distanciaTotal + "M. ");

        for (int i = 0; i < corridaControle.getQtdeCarros(); i++) {
            CarroList.add(new Carro("Carro " + (i+1)));
            System.out.println("Carro " + (i+1) + " preparado para corrida!");
            
        }
        
        this._carro._Carros = CarroList;
    }

    public void iniciar() {
    	

        if (CarroList == null) {
            System.out.println("Os Carros devem ser preparados antes da corrida iniciar!");
        }

        for (Carro car : CarroList ) {
            new Thread(new ThreadCarro(car, distanciaTotal, Delorean, Car2, Car3, Car4, Car5));
        }

        while(!corridaControle.isFinalizada()) {
            try {
                Thread.sleep(1000);
            }catch(Exception e){
                e.printStackTrace(); 
            }
        }
    }

    public void exibirResultado() {

        if (!corridaControle.isFinalizada()) {
            System.out.println("A corrida nÃo foi finalizada. O resultado não pode ser exibido!");
        }

        corridaControle.getCarrosChegada().forEach((k, v) -> System.out.println(new String(k  + "º lugar - " + ((Carro)v).getNome())));
    }
}
