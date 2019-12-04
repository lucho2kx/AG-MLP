package ar.com.ag.mlp.AG_MLP.modelo;

import java.util.ArrayList;

public class Gene {

    private double wb, w1, w2, w3, w4, w5, w6, w7, ws;

    public Gene(double wb, double w1, double w2, double w3, double w4, double w5, double w6, double w7, double ws) {
        super();
        this.wb = wb;
        this.w1 = w1;
        this.w2 = w2;
        this.w3 = w3;
        this.w4 = w4;
        this.w5 = w5;
        this.w6 = w6;
        this.w7 = w7;
        this.ws = ws;
    }

    public Gene() {
        super();		
    }

    public double getWb() {
        return wb;
    }

    public void setWb(double wb) {
        this.wb = wb;
    }

    public double getW1() {
        return w1;
    }

    public void setW1(double w1) {
        this.w1 = w1;
    }

    public double getW2() {
        return w2;
    }

    public void setW2(double w2) {
        this.w2 = w2;
    }

    public double getW3() {
        return w3;
    }

    public void setW3(double w3) {
        this.w3 = w3;
    }

    public double getW4() {
        return w4;
    }

    public void setW4(double w4) {
        this.w4 = w4;
    }

    public double getW5() {
        return w5;
    }

    public void setW5(double w5) {
        this.w5 = w5;
    }

    public double getW6() {
        return w6;
    }

    public void setW6(double w6) {
        this.w6 = w6;
    }

    public double getW7() {
        return w7;
    }

    public void setW7(double w7) {
        this.w7 = w7;
    }

    public double getWs() {
        return ws;
    }

    public void setWs(double ws) {
        this.ws = ws;
    }

    public double calcularValorDeSalida(ArrayList<Double> entradas) {

        double entradaNodo = 0;
        double salidaNodo = 0;

        entradaNodo = this.getWb() + this.getW1() * entradas.get(0) + this.getW2() * entradas.get(1)
                + this.getW3() * entradas.get(2) + this.getW4() * entradas.get(3) + this.getW5() * entradas.get(4)
                + this.getW6() * entradas.get(5) + this.getW7() * entradas.get(6);
        salidaNodo = 1 / (1 + Math.exp(-entradaNodo));
        return salidaNodo;
    }

    @Override
    public String toString() {
        return "Gene [wb=" + wb + ", w1=" + w1 + ", w2=" + w2 + ", w3=" + w3 + ", w4=" + w4 + ", w5=" + w5 + ", w6="
                            + w6 + ", w7=" + w7 + ", ws=" + ws + "]\n";
    }
}
