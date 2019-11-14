package ar.com.ag.mlp.AG_MLP;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

import java.io.FileNotFoundException;
import java.io.FileReader;

import com.opencsv.CSVReader;


public class AG_MLP {
	
	// Mutación estructural
	private double probEliminarNeuOculta= 1.0;
	private double probAgregarNeuOculta= 0.4;
	private int cantNeuEntrada= 7;
	private int cantNeuSalida= 1;
	private int cantMaxNeuOculta= 15;
	private int tamPoblacion= 20;
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
	
	
}
