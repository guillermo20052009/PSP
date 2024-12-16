package Actividad3_7_UDP;

import java.io.Serializable;

public class Numero implements Serializable {
    private int numero;
    private long cuadrado;
    private long cubo;

    public Numero(int numero, long cuadrado, long cubo) {
         this.numero = numero;
         this.cuadrado = cuadrado;
         this.cubo = cubo;
    }
    public Numero() {super();}

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    @Override
    public String toString() {
        return "Numero{" +
                "numero=" + numero +
                ", cuadrado=" + cuadrado +
                ", cubo=" + cubo +
                '}';
    }
}
