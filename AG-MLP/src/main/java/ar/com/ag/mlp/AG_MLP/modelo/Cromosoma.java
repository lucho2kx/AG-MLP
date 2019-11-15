package ar.com.ag.mlp.AG_MLP.modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Cromosoma {
	
    private ArrayList<Gene> genes;
    // apSesgo= wb
    private double apSesgo;
    private int nMax;
    private double aptitud;
    private double relAptitud;
    private double acumAptitud;

    public Cromosoma() {
        super();		
    }

    public ArrayList<Gene> getGenes() {
        return genes;
    }

    public void setGenes(ArrayList<Gene> genes) {
        this.genes = genes;
    }

    public double getApSesgo() {
        return apSesgo;
    }

    public void setApSesgo(double apSesgo) {
        this.apSesgo = apSesgo;
    }

    public int getnMax() {
        return nMax;
    }

    public void setnMax(int nMax) {
        this.nMax = nMax;
    }

    public double getAptitud() {
        return aptitud;
    }

    public void setAptitud(double aptitud) {
        this.aptitud = aptitud;
    }

    public double getRelAptitud() {
        return relAptitud;
    }

    public void setRelAptitud(double relAptitud) {
        this.relAptitud = relAptitud;
    }

    public double getAcumAptitud() {
        return acumAptitud;
    }

    public void setAcumAptitud(double acumAptitud) {
        this.acumAptitud = acumAptitud;
    }

    public double calcularFuncionDeEvaluacion() {

        double cantidadPatrones = 33;
        double sumaErroresMedios = 0;

        try {
            File myObj = new File("C:\\Users\\Pablo\\Desktop\\dataset-neuroph-procesado.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arreglo = data.split("\t");
                ArrayList<Double> entradas = new ArrayList<Double>();
                
                for (int i = 0; i < 7; i++) {
                    entradas.add(Double.parseDouble(arreglo[i]));
                }    
                
                double entradaNodoSalida = this.getApSesgo();

                for (Gene g : this.getGenes()) {
                    entradaNodoSalida += g.getWs() * g.calcularValorDeSalida(entradas);
                }

                double salidaNodoSalida = 1 /(1 + Math.exp(-entradaNodoSalida));
                double salidaEsperada = Double.parseDouble(arreglo[7]);
                
                System.out.println("Salida nodo: " + salidaNodoSalida);
                System.out.println("Salida esperada: " + salidaEsperada);
                
                sumaErroresMedios += Math.pow((salidaEsperada - salidaNodoSalida), 2);
            }
            myReader.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        return (sumaErroresMedios / cantidadPatrones);
    }

    @Override
    public String toString() {
        return "Cromosoma [genes=" + genes + ", apSesgo=" + apSesgo + ", nMax=" + nMax + ", aptitud=" + aptitud
                            + ", relAptitud=" + relAptitud + ", acumAptitud=" + acumAptitud + "]";
    }
}
