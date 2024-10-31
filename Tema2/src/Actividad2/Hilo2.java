package Actividad2;

public class Hilo2 implements Runnable {
    // Constructor que indica que el hilo ha sido creado
    public Hilo2() {
        System.out.println("Hilo2 creado");
    }

    @Override
    public void run() {
        // Cambiar el bucle for a un bucle while para que sea infinito
        for (int i = 0; i > -1; i++) { // Esta condición nunca se cumple
            System.out.println("TAC"); // Imprime "TAC"
            try {
                Thread.sleep(5000); // Duerme el hilo por 5 segundos
            } catch (InterruptedException e) {
                // Maneja la interrupción del hilo
                throw new RuntimeException(e);
            }
        }
    }
}
