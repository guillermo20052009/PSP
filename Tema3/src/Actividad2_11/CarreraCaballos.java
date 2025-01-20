package Actividad2_11;

import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarreraCaballos extends Observable implements Runnable {

    private String nombre;

    public CarreraCaballos(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {

        int porcentaje = 0;
        int numAleatorio;
        try {
            while (porcentaje < 100) {
                numAleatorio = generaNumeroAleatorio(1, 15);

                // Ajustar el número aleatorio según la prioridad del hilo
                int prioridad = Thread.currentThread().getPriority();
                if (prioridad == Thread.MAX_PRIORITY) {
                    numAleatorio += 3; // Incremento adicional para hilos con máxima prioridad
                } else if (prioridad == Thread.MIN_PRIORITY) {
                    numAleatorio -= 9; // Penalización para hilos con mínima prioridad
                } else {
                    numAleatorio -= 3;
                }

                numAleatorio = Math.max(numAleatorio, 0); // Asegurarse de no restar progreso

                System.out.println("Caballo " + nombre + " ha aumentado en " + numAleatorio);
                porcentaje += numAleatorio;

                this.setChanged();
                this.notifyObservers(porcentaje);
                this.clearChanged();

                Thread.sleep(1000);

            }
        } catch (InterruptedException ex) {
            System.out.println("Hilo interrumpido");
        }

    }


    public static int generaNumeroAleatorio(int minimo, int maximo) {
        int num = (int) Math.floor(Math.random() * (maximo - minimo + 1) + (minimo));
        return num;
    }

}

