package Actividad3_7;

import java.io.Serializable;

public class Numero implements Serializable {
    // Clase que representa un número con su cuadrado y cubo, implementando Serializable para permitir su transmisión.
    private int numero; // Variable que almacena el número principal.
    private long cuadrado; // Variable que almacena el cuadrado del número.
    private long cubo; // Variable que almacena el cubo del número.

    // Constructor que inicializa un objeto Numero con valores específicos.
    public Numero(int numero, long cuadrado, long cubo) {
        this.numero = numero;
        this.cuadrado = cuadrado;
        this.cubo = cubo;
    }

    // Constructor por defecto que permite crear un objeto Numero sin inicializar valores.
    public Numero() {super();}

    // Método que devuelve el valor del número.
    public int getNumero() {
        return numero;
    }

    // Método que establece el valor del número.
    public void setNumero(int numero) {
        this.numero = numero;
    }

    // Método que devuelve el valor del cubo del número.
    public long getCubo() {
        return cubo;
    }

    // Método que establece el valor del cubo del número.
    public void setCubo(long cubo) {
        this.cubo = cubo;
    }

    // Método que devuelve el valor del cuadrado del número.
    public long getCuadrado() {
        return cuadrado;
    }

    // Método que establece el valor del cuadrado del número.
    public void setCuadrado(long cuadrado) {
        this.cuadrado = cuadrado;
    }

    // Método que devuelve una representación en forma de texto del objeto Numero.
    @Override
    public String toString() {
        return "Numero{" +
                "numero=" + numero +
                ", cuadrado=" + cuadrado +
                ", cubo=" + cubo +
                '}';
    }
}
