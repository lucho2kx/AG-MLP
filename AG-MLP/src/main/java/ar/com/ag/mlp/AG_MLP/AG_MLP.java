package ar.com.ag.mlp.AG_MLP;

import java.security.SecureRandom;
import java.util.ArrayList;
import ar.com.ag.mlp.AG_MLP.modelo.Cromosoma;
import ar.com.ag.mlp.AG_MLP.modelo.Gene;


public class AG_MLP {
	
    // Parámetros del problema
    private static double probEliminarNeuOculta= 1.0;
    private static double probAgregarNeuOculta= 0.4;
    private static int cantMaxNeuOculta= 15;
    private static int tamPoblacion= 20;
    private static int cantGeneraciones= 300;
    // Mutación paramétrica
    private static double probMutacionNeu= 0.8;
    private static double desviacionEstandar = 1.0;
    
    // Variables para obtener números aleatotios 
    private static SecureRandom srRuleta = new SecureRandom();
    private static SecureRandom numeroAleatorio = new SecureRandom();   
    private static SecureRandom srNroNeurona = new SecureRandom();
    private static SecureRandom srPesosGen = new SecureRandom();
    private static SecureRandom srPesosBias = new SecureRandom();	
    
    private static double probEliminarNeuOcultaMinima= 0.1;
    private static double probAgregarNeuOcultaMinima= 0.1;
    private static double probMutacionNeuMinima= 0.1;
    private static double desviacionEstandarMinima = 0.1;
    
    
    // 
    /**
     * Se genera de manera aleatoria los individuos de la población. 
     * 
     * @return ArrayList<Cromosoma> poblacion
     */
    private static ArrayList<Cromosoma> inicializarPoblacion() {
        int cantNeuronasOcultas, i, t, j;
        double peso;
        Gene genNeurona;
        Cromosoma cromoMLP;
        ArrayList<Cromosoma> poblacion= new ArrayList<Cromosoma>(tamPoblacion+1);

        srPesosGen.setSeed(new byte[20]);
        srPesosGen.setSeed(System.currentTimeMillis());
        srPesosBias.setSeed(new byte[20]);
        srPesosBias.setSeed(System.currentTimeMillis());
        srNroNeurona.setSeed(new byte[20]);
        srNroNeurona.setSeed(System.currentTimeMillis());
        
        System.out.println( "*************** Inicializar población ***************");
        for (i= 0; i < tamPoblacion; i++) {
            // Se genera de manera aleatoria
            // la cantidad de neurona en la capa oculta
            // que va a tener el cromosoma.
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
            for (j= 0; j < cantNeuronasOcultas; j++) {
                genNeurona= new Gene();
                // Se obtienen los pesos de entrada
                // de la neurona oculta de manera aletoria entre -1 y 1 
                for (t=0; t < 8; t++) {
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
        System.out.println( "Poblacion="+ poblacion.toString() );
        return poblacion;
    }
    
    /**
     * Se selecciona un cromosoma de la población,
     * se aplica el proceso conocido como la rueda
     * de la ruleta.
     
     * @param poblacion
     * @return Cromosoma cromosoma
     */
    private static Cromosoma seleccionarCromosomaPoblacion(ArrayList<Cromosoma> poblacion) {
    	int i, posicionCromoElegido= 0;
    	Cromosoma unCromo;
    	
        // Se genera un número aleatorio entre 0 y 1
        double x= srRuleta.nextDouble();
        while ( (x < 0.0)  || (x > 1.0)) {
            x= srRuleta.nextDouble();
        }
        // Se elige el cromosoma cuya aptitud aumulada contiene a x
        
        for (i= 0; i < tamPoblacion; i++) {
        	unCromo= poblacion.get(i);
        	//System.out.println( "El Cromosoma es index= "+ i + " aptitudAcumulada= " + unCromo.getAcumAptitud());
            if (unCromo.getAcumAptitud() >= x) {
                posicionCromoElegido= i;
                break;
            }
        }
      //  System.out.println( "El Cromosoma seleccionado es index="+ posicionCromoElegido + " => aptitudAcumulada= " + poblacion.get(posicionCromoElegido).getAcumAptitud() + " => x= " + x );
       
        return poblacion.get(posicionCromoElegido);
    }
		
    private static ArrayList<Cromosoma> mutacion(ArrayList<Cromosoma> poblacion) { 
        int i;
        
    	numeroAleatorio.setSeed(new byte[20]);
		numeroAleatorio.setSeed(System.currentTimeMillis());
		System.out.println( "Se aplica mutación a la población" );
	    for (i= 0; i < tamPoblacion; i++) {
            
            //Mutación Estructural
            
            //AdicionarNeuronasOcultas
            if(poblacion.get(i).getGenes().size() < cantMaxNeuOculta) {
            	// Se genera un nro aleatorio entre 0 y 1
        	    double x= numeroAleatorio.nextDouble();
    		    while ( (x < 0.0)  || (x > 1.0)) {
    		    	x= numeroAleatorio.nextDouble();
    		    }
                if (x < probAgregarNeuOculta) {  
                    Gene gen = new Gene(0, 0, 0, 0, 0, 0, 0, 0, 0);
                    poblacion.get(i).getGenes().add(gen);
                 // System.out.println("El Cromosoma i= "+ i + " se adicciona una neurona. " + "x= " + x);
                }    
            }
            
            //EliminarNeuronasOcultas
            if(poblacion.get(i).getGenes().size() >= 1) {
            	// Se genera un nro aleatorio entre 0 y 1
        		double x= numeroAleatorio.nextDouble();
    		    while ( (x < 0.0)  || (x > 1.0)) {
    		    	x= numeroAleatorio.nextDouble();
    		    }
    		    if (x < probEliminarNeuOculta) {  
                    poblacion.get(i).getGenes().remove(0);
                 // System.out.println("El Cromosoma i= "+ i + " se elimina una neurona. " + "x= " + x);
                } 
            }
            
            //MutacionParametrica
            for (Gene g : poblacion.get(i).getGenes()) {
            	// Se genera un nro aleatorio entre -1 y 1
        		double x= numeroAleatorio.nextGaussian();
    		    while ( (x < -1.0)  || (x > 1.0)) {
    		    	x= numeroAleatorio.nextGaussian();
    		    }
    		    x= desviacionEstandar*x; 
    		    if(x < probMutacionNeu) {
    		    	// System.out.println("El Cromosoma i= "+ i + " se aplica mutación parámetrica. " + "x= " + x);
                    //Sumar el valor de la gaussiana
                    g.setWb(g.getWb() + x);
                    g.setW1(g.getW1() + x);
                    g.setW2(g.getW2() + x);
                    g.setW3(g.getW3() + x);
                    g.setW4(g.getW4() + x);
                    g.setW5(g.getW5() + x);
                    g.setW6(g.getW6() + x);
                    g.setW7(g.getW7() + x);
                    g.setWs(g.getWs() + x);
                }
            }
        }
        
        return poblacion;
    }
    
    private static ArrayList<Cromosoma> evaluarPoblacion(ArrayList<Cromosoma> poblacion) {
    	double aptitud, mejorAptitud;
    	int i, posicion= 0;
    	
    	aptitud= poblacion.get(0).calcularFuncionDeEvaluacion();
    	mejorAptitud= aptitud;
    	for (i=1; i < tamPoblacion; i++ ) {
            // Se calcula la función de evaluación de cada cromosoma 
            aptitud= poblacion.get(i).calcularFuncionDeEvaluacion();
            if (aptitud < mejorAptitud) {
                mejorAptitud= aptitud;
                posicion= i;
            }
            System.out.println( "Cromosoma i="+ i + " => aptitud = " + aptitud );
        }
        System.out.println( "El Cromosoma de menor aptitud es "+ posicion + " => aptitud = " + mejorAptitud );
        
        // Se guarda el cromosoma con mejor aptitud al final de la población
        if (mejorAptitud < poblacion.get(tamPoblacion).getAptitud()) {
        	poblacion.remove(tamPoblacion);
        	poblacion.add(poblacion.get(posicion));
        }
       
    	return poblacion;
    }
    
    private static ArrayList<Cromosoma> seleccionarNuevaPoblacion(ArrayList<Cromosoma> poblacion) {
    	ArrayList<Cromosoma> poblacionNueva= new ArrayList<Cromosoma>(tamPoblacion+1);
    	double aptitudTotal= 0;
        double aptitudAcumulada= 0;
        int i;
        
        // Se obtiene la aptitud total
        for (i= 0; i < tamPoblacion; i++) {
            aptitudTotal= aptitudTotal + poblacion.get(i).getAptitud();
        }
        // Se obtiene la aptitud relativa y la acumulada
        for (i= 0; i < tamPoblacion; i++) {
            double relApt= poblacion.get(i).getAptitud() / aptitudTotal; 
            poblacion.get(i).setRelAptitud(relApt);
            aptitudAcumulada= aptitudAcumulada + relApt;
            System.out.println( "Cromosoma i= "+ i + " Aptitud Acumulada="+ aptitudAcumulada + " Aptitud relativa="+ relApt + " Aptitud="+ poblacion.get(i).getAptitud());
            poblacion.get(i).setAcumAptitud(aptitudAcumulada);
        }
    	
        
    	for (i=0; i < tamPoblacion; i++) {
    		// Seleccionar el cromosoma de la población
        	Cromosoma unCromo= seleccionarCromosomaPoblacion(poblacion);
        	System.out.println( "Cromosoma i= "+ i + " "+ unCromo.getAptitud());
    		// Generacion poblacion
    		poblacionNueva.add(unCromo);
    	}
    	
    	
    	// Se guarda el mejor cromosoma de la generación anterior
    	poblacionNueva.add(poblacion.get(tamPoblacion));
    	
    	return poblacionNueva;
    }
        
    private static void modificarParametros(int generacion) {
    	
    	// Se actualiza la probabilidad de eliminar una neurona en capa oculta
    	double m= (probEliminarNeuOcultaMinima - probEliminarNeuOculta) / (cantGeneraciones - 1) ;
    	
    	probEliminarNeuOculta= formatearDecimales(((m * (generacion - probEliminarNeuOculta)) + 1), 2);
    	
    	// Se actualiza la probabilidad de adicionar una neurona en capa oculta
    	double t= (probAgregarNeuOcultaMinima - probAgregarNeuOculta) / (cantGeneraciones - 1);
    	probAgregarNeuOculta= formatearDecimales(((t * (generacion - probAgregarNeuOculta)) + 1), 2);
    	
    	// Se actualiza la probabilidad de mutación una neurona en capa oculta
    	double l= (probMutacionNeuMinima - probMutacionNeu) / (cantGeneraciones - 1);
    	probMutacionNeu= formatearDecimales(((l * (generacion - probMutacionNeu)) + 1), 2);
    	
    	// Se actualiza la desviación estandar
    	double s= (desviacionEstandarMinima - desviacionEstandar) / (cantGeneraciones - 1);
    	desviacionEstandar= formatearDecimales(((s * (generacion - desviacionEstandar)) + 1), 2);
    	
    	System.out.println( "Prob. de eliminar "+ probEliminarNeuOculta 
    						+ " Prob. de agregar "+ probAgregarNeuOculta
    						+ " Prob. paramétrica "+ probMutacionNeu
    						+ " Desv. estandart "+ desviacionEstandar);	
    }
    
    public static Double formatearDecimales(Double numero, Integer numeroDecimales) {
    	
    	return Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales);
    
    }
    
    public static void main(String[] args) {
        ArrayList<Cromosoma> poblacion;
        ArrayList<Cromosoma> poblacionNueva;
        ArrayList<Cromosoma> poblacionMutada;
        ArrayList<Cromosoma> poblacionEvaluada;
        int posicion= 0, generacion= 0;
        double mejorAptitud= 0, aptitud= 0;

        System.out.println( "Hola Algoritmo Genético! :)" );

        System.out.println( "*************** Se inicializa la población ***************");
        poblacion= inicializarPoblacion();
        
        for (int i=0; i < poblacion.size(); i++ ) {
            // Se calcula la función de evaluación de cada cromosoma 
            aptitud= poblacion.get(i).calcularFuncionDeEvaluacion();
            if (i == 0) {
                mejorAptitud= aptitud;
                posicion= 0;
            }
            else {
                if (aptitud < mejorAptitud) {
                    mejorAptitud= aptitud;
                    posicion= i;
                }
            }
           // System.out.println( "Cromosoma i="+ i + " => aptitud = " + aptitud );
        }
        //System.out.println( "El Cromosoma de menor aptitud es "+ posicion + " => aptitud = " + mejorAptitud );
        // Se guarda el cromosoma con mejor aptitud al final de la población
        poblacion.add(poblacion.get(posicion));

        // Bucle While :))))
        while (generacion < cantGeneraciones) {
        	System.out.println( "*************** Generación "+ generacion +" ***************");
        	
        	System.out.println( "*************** Selecciona Nueva Población ***************");
        	// Se selecciona la población nueva.
        	poblacionNueva= seleccionarNuevaPoblacion(poblacion);
        	
            System.out.println( "*************** Se aplica mutación a la Nueva Población ***************");
        	// Se aplica Mutación Estructural y Mutación Paramétrica
            poblacionMutada= mutacion(poblacionNueva);
           
            System.out.println( "*************** Se evalua a la Nueva Población ***************");
        	// Se evalua cada cromosoma y se guarda el mejor al final de la lista
            poblacionEvaluada= evaluarPoblacion(poblacionMutada);
            
            poblacion= poblacionEvaluada;
            
            // Se modifican los parámetros de probabilidades y desviación estandar
            modificarParametros(generacion + 1);
          
            // Se incrementa la generación
            generacion= generacion + 1;
        }
    }
    
 
    
}
