package ar.com.ag.mlp.AG_MLP;

import java.security.SecureRandom;
import java.util.ArrayList;
import ar.com.ag.mlp.AG_MLP.modelo.Cromosoma;
import ar.com.ag.mlp.AG_MLP.modelo.Gene;


public class AG_MLP {
	
    // Parámetros del problema
    private static double probEliminarNeuOculta= 1.0;
    private static double probAgregarNeuOculta= 0.4;
    private int cantNeuEntrada= 7;
    private int cantNeuSalida= 1;
    private static int cantMaxNeuOculta= 15;
    private static int tamPoblacion= 20;
    private static int cantGeneraciones= 300;
    // Mutación paramétrica
    private static double probMutacionNeu= 0.8;
    private static double desviacionEstandar = 1;
		
    // Se inicializa la población
    private static ArrayList<Cromosoma> inicializarPoblacion() {
        SecureRandom srNroNeurona = new SecureRandom();
        SecureRandom srPesosGen = new SecureRandom();
        SecureRandom srPesosBias = new SecureRandom();
        int cantNeuronasOcultas;
        double peso;
        Gene genNeurona;
        Cromosoma cromoMLP;
        ArrayList<Cromosoma> poblacion= new ArrayList<Cromosoma>(tamPoblacion+1);

        srNroNeurona.nextBytes(new byte[1]);
        srPesosGen.nextBytes(new byte[1]);
        srPesosBias.nextBytes(new byte[1]);
        for (int i=0; i < tamPoblacion; i++) {
            // Se genera de manera aleatoria
            // la cantidad de neurona en la capa oculta
            // que va a tener el cromosoma.
            byte[] b = new byte[20];
            srNroNeurona.setSeed(b);
            srNroNeurona.setSeed(System.currentTimeMillis());
            cantNeuronasOcultas= srNroNeurona.nextInt(cantMaxNeuOculta)+1;

            cromoMLP= new Cromosoma();
            // Se setea el peso de Bias de salida
            peso= srPesosBias.nextGaussian();
            while ((peso < -1.0) || (peso > 1.0) ) {
                peso= srPesosBias.nextGaussian();
            }
            cromoMLP.setApSesgo(peso);

            ArrayList<Gene> genes= new ArrayList<Gene>();

            // Se crean los genes(Neuronas ocultas)
            for (int j= 0; j < cantNeuronasOcultas; j++) {
                genNeurona= new Gene();

                srPesosGen.setSeed(new byte[20]);
                srPesosGen.setSeed(System.currentTimeMillis());
                // Se obtienen los pesos de entrada
                // de la neurona oculta de manera aletoria entre -1 y 1 
                for (int t=0; t < 8; t++) {
                    peso= srPesosGen.nextGaussian();
                    while ((peso < -1.0) || (peso > 1.0) ) {
                        peso= srPesosGen.nextGaussian();
                    }
                    switch (t) {
                        case 0:
                            genNeurona.setWb(peso);
                            break;
                        case 1:
                            genNeurona.setW1(peso);
                            break;

                        case 2:
                            genNeurona.setW2(peso);
                            break;
                        case 3:
                            genNeurona.setW3(peso);
                            break;

                        case 4:
                            genNeurona.setW4(peso);
                            break;
                        case 5:
                            genNeurona.setW5(peso);
                            break;

                        case 6:
                            genNeurona.setW6(peso);
                            break;
                        case 7:
                            genNeurona.setW7(peso);
                            break;
                        default:
                            break;
                    }
                }
                genes.add(genNeurona);
            }
            // Se agrega los genes creado al cromosoma
            cromoMLP.setGenes(genes);
            // Se agrega el cromosoma a la población
            poblacion.add(cromoMLP);
        }
        return poblacion;
    }
    
    /**
     * Se selecciona un cromosoma de la población,
     * se aplica el proceso conocido como la rueda
     * de la ruleta.
     
     * @param poblacion
     * @return posicionCromoElegido
     */
    private static int seleccionarCromosomaPoblacion(ArrayList<Cromosoma> poblacion) {
    	// Seleccionar la nueva generación
        double aptitudTotal= 0;
        double aptitudAcumulada= 0;
        // Se obtiene la aptitud total
        for (int i= 0; i < tamPoblacion; i++) {
            aptitudTotal= aptitudTotal + poblacion.get(i).getAptitud();
        }
        // Se obtiene la aptitud relativa y la acumulada
        for (int i= 0; i < tamPoblacion; i++) {
            double relApt= poblacion.get(i).getAptitud() / aptitudTotal; 
            poblacion.get(i).setRelAptitud(relApt);
            aptitudAcumulada= aptitudAcumulada + relApt;
            poblacion.get(i).setAcumAptitud(aptitudAcumulada);
        }
        // Se genera un número aleatorio entre 0 y 1
        SecureRandom srRuleta = new SecureRandom();
        byte[] b = new byte[20];
        srRuleta.setSeed(b);
        srRuleta.setSeed(System.currentTimeMillis());
        double x= srRuleta.nextDouble();
        while ( (x < 0.0)  || (x > 1.0)) {
            x= srRuleta.nextDouble();
        }
        // Se elige el cromosoma cuya aptitud aumulada contiene a x
        int posicionCromoElegido= 0;
        for (int i= 0; i < tamPoblacion; i++) {
            if (poblacion.get(i).getAcumAptitud() > x) {
                posicionCromoElegido= i;
            }
        }

        return posicionCromoElegido;
    }
		
    private static ArrayList<Cromosoma> mutacionEstructural(ArrayList<Cromosoma> poblacion, int posicionCromosoma) {
    	int cantNeuronaOculta= poblacion.get(posicionCromosoma).getGenes().size();
    	
    	// Para agregar neurona en la capa oculta
    	if (cantNeuronaOculta < cantMaxNeuOculta) {
			// Se genera un nro aleatorio entre 0 y 1
    		SecureRandom srNroAleatorio = new SecureRandom();
			byte[] b = new byte[20];
			srNroAleatorio.setSeed(b);
			srNroAleatorio.setSeed(System.currentTimeMillis());
		    double x= srNroAleatorio.nextDouble();
		    while ( (x < 0.0)  || (x > 1.0)) {
		    	x= srNroAleatorio.nextDouble();
		    }
			// Se agrega una neurona oculta si x es menor al la probabilidad de agregar neurona
			if (x < probAgregarNeuOculta) {  
                Gene gen = new Gene(0, 0, 0, 0, 0, 0, 0, 0, 0);
                poblacion.get(posicionCromosoma).getGenes().add(gen);
            }
		}
    	
    	// Para eliminar neurona en la capa oculta
    	if (cantNeuronaOculta > 1) {
    		// Se genera un nro aleatorio entre 0 y 1
    		SecureRandom srNroAleatorio = new SecureRandom();
			byte[] b = new byte[20];
			srNroAleatorio.setSeed(b);
			srNroAleatorio.setSeed(System.currentTimeMillis());
		    double x= srNroAleatorio.nextDouble();
		    while ( (x < 0.0)  || (x > 1.0)) {
		    	x= srNroAleatorio.nextDouble();
		    }
			// Se elimina una neurona oculta si x es menor al la probabilidad de eliminar neurona
			if (x < probEliminarNeuOculta) {
				poblacion.get(posicionCromosoma).getGenes().remove(0);
			}
    	}
    	
    	// Se calcula la aptitud o función evaluación 
    	poblacion.get(posicionCromosoma).calcularFuncionDeEvaluacion();
    	
    	return poblacion;
    }
    
    private static ArrayList<Cromosoma> mutacionParametrica(ArrayList<Cromosoma> poblacion, int posicionCromosoma) {
    	
    	if (poblacion.get(posicionCromosoma).getGenes().size() < cantMaxNeuOculta) {
			// Se genera un nro aleatorio entre 0 y 1
    		SecureRandom srNroAleatorio = new SecureRandom();
			byte[] b = new byte[20];
			srNroAleatorio.setSeed(b);
			srNroAleatorio.setSeed(System.currentTimeMillis());
		    double x= srNroAleatorio.nextDouble();
		    while ( (x < 0.0)  || (x > 1.0)) {
		    	x= srNroAleatorio.nextDouble();
		    }
			// Se agrega una neurona oculta si x es menor al la probabilidad de agregar neurona
			if(x < probAgregarNeuOculta) {  
                Gene gen = new Gene(0, 0, 0, 0, 0, 0, 0, 0, 0);
                poblacion.get(posicionCromosoma).getGenes().add(gen);
            }    
        }
    	
    	return poblacion;
    }
    
    
    private static ArrayList<Cromosoma> mutacion(ArrayList<Cromosoma> poblacion) {
        
        for (int i= 0; i < tamPoblacion; i++) {
            
            //Mutación Estructural
            
            //AdicionarNeuronasOcultas
            if(poblacion.get(i).getGenes().size() < cantMaxNeuOculta) {
                double numeroAleatorio = Math.random();
                if(numeroAleatorio < probAgregarNeuOculta) {  
                    Gene gen = new Gene(0, 0, 0, 0, 0, 0, 0, 0, 0);
                    poblacion.get(i).getGenes().add(gen);
                }    
            }
            
            //EliminarNeuronasOcultas
            if(poblacion.get(i).getGenes().size() >= 1) {
                double numeroAleatorio = Math.random();
                if(numeroAleatorio < probEliminarNeuOculta) {  
                    poblacion.get(i).getGenes().remove(0);
                } 
            }
            
            //Acá deberíamos reducir los parametros probEliminarNeuOculta y probAgregarNeuOculta
        
            //MutacionParametrica
            
            for (Gene g : poblacion.get(i).getGenes()) {
                double numeroAleatorio = Math.random();
                if(numeroAleatorio < probMutacionNeu) {
                    //Sumar el valor de la gaussiana
                    g.setWb(g.getWb() + numeroAleatorio);
                    g.setW1(g.getW1() + numeroAleatorio);
                    g.setW2(g.getW2() + numeroAleatorio);
                    g.setW3(g.getW3() + numeroAleatorio);
                    g.setW4(g.getW4() + numeroAleatorio);
                    g.setW5(g.getW5() + numeroAleatorio);
                    g.setW6(g.getW6() + numeroAleatorio);
                    g.setW7(g.getW7() + numeroAleatorio);
                    g.setWs(g.getWs() + numeroAleatorio);
                }
            }
        }
        
        
        
        return poblacion;
    }
    
    
    public static void main( String[] args ) {
        ArrayList<Cromosoma> poblacion;
        double menorAptitud= 0.0;
        double aptitud;
        int posicion= 0;
        int generacion= 0;

        System.out.println( "Hola Algoritmo Genético! :)" );

        poblacion= inicializarPoblacion();
        
        for (int i=0; i < poblacion.size(); i++ ) {
            // Se calcula la función de evaluación de cada cromosoma 
            aptitud= poblacion.get(i).calcularFuncionDeEvaluacion();
            if (i == 0) {
                menorAptitud= aptitud;
                posicion= 0;
            }
            else {
                if (aptitud < menorAptitud) {
                    menorAptitud= aptitud;
                    posicion= i;
                }
            }
            System.out.println( "Cromosoma i="+ i + " => aptitud = " + aptitud );
        }
        System.out.println( "El Cromosoma de menor aptitud es "+ posicion + " => aptitud = " + menorAptitud );
        // Se guarda el cromosoma con mejor aptitud al final de la población
        poblacion.add(poblacion.get(posicion));

        // Bucle While :))))
        while (generacion < cantGeneraciones) {
            // Seleccionar el cromosoma de la población
        	int posicionMejorCromosoma= seleccionarCromosomaPoblacion(poblacion);
        	
             // Aplicar Mutación Estructural y Mutación Paramétrica
            poblacion = mutacion(poblacion);
           
            // Evaluar cada cromosoma

            // Guardar el mejor cromosoma

            // generacion= generacion + 1;
            generacion= generacion + 1;
        }
    }
    
 
    
}
