package Actividad3_7_UDP;

import java.io.Serializable;

/**
 * Clase Numero que implementa Serializable para ser enviada/recibida en objetos serializados.
 */
public class Numero implements Serializable {
    private int numero;         // Número original
    private long cuadrado;      // Cuadrado del número
    private long cubo;          // Cúbico del número

    // Constructor por defecto
    public Numero() {
        super();
    }

    // Constructor con parámetros
    public Numero(int numero, long cuadrado, long cubo) {
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    // Getter y setter para el número
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Getter y setter para el cuadrado
    public long getCuadrado() {
        return cuadrado;
    }

    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    // Getter y setter para el cubo
    public long getCubo() {
        return cubo;
    }

    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    // Método toString para representar el objeto en una cadena de texto
    @Override
    public String toString() {
        return "Numero{" +
                "numero=" + numero +
                ", cuadrado=" + cuadrado +
                ", cubo=" + cubo +
                '}';
    }
}
