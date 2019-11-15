package ar.com.ag.mlp.AG_MLP;

import java.security.SecureRandom;
import java.util.ArrayList;
import ar.com.ag.mlp.AG_MLP.modelo.Cromosoma;
import ar.com.ag.mlp.AG_MLP.modelo.Gene;


public class AG_MLP {
	
	// Mutación estructural
		private double probEliminarNeuOculta= 1.0;
		private double probAgregarNeuOculta= 0.4;
		private int cantNeuEntrada= 7;
		private int cantNeuSalida= 1;
		private static int cantMaxNeuOculta= 15;
		private static int tamPoblacion= 20;
		private int cantGeneraciones= 300;
		// Mutación paramétrica
		private double probMutacionNeu= 0.8;
		
		
		// The fitness function.
	/*	private static double fitness(final double x) {
			double fitness = 0;
		    for (int i = 0; i < individual.getDefaultGeneLength()
		      && i < solution.length; i++) {
		        if (individual.getSingleGene(i) == solution[i]) {
		            fitness++;
		        }
		    }
		    return fitness;
			return cos(0.5 + sin(x))*cos(x);
		}
		*/
		
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
		
		public static void main( String[] args ) {
			ArrayList<Cromosoma> poblacion;
			double menorAptitud= 0.0;
			double aptitud;
			int posicion= 0;
			
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
        	
        	
        	
	        
		}
	
}
