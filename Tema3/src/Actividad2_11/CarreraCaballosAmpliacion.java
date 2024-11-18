package Actividad2_11;

import java.util.Observable;

class CarreraCaballosAmpliacion extends Observable implements Runnable {

    private String nombre;
    private int porcentaje;

    public CarreraCaballosAmpliacion(String nombre) {
        this.nombre = nombre;
        this.porcentaje = 0;  // Inicia con 0% de avance
    }

    public String getNombre() {
        return nombre;
    }

    public int getPorcentaje() {
        return porcentaje;
    }

    public void avanzar() {
        if (porcentaje < 100) {
            porcentaje += 10; // Aumentar el porcentaje
            setChanged(); // Marca que el objeto ha cambiado
            notifyObservers(porcentaje); // Notifica a los observadores (la interfaz)
        }
    }

    @Override
    public void run() {
        // El hilo solo espera para que se le indique que avance, no hace nada automáticamente
        while (porcentaje < 100) {
            try {
                Thread.sleep(100);  // Aquí puedes controlar la velocidad con la que se revisa el avance
            } catch (InterruptedException e) {
                System.out.println("El hilo ha terminado");
            }
        }
    }
}
