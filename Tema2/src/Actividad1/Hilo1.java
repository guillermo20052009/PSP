package Actividad1;

// Clase que extiende Thread para crear un hilo que imprime "TIC"
public class Hilo1 extends Thread {

    // Constructor de Hilo1
    public Hilo1() {
        System.out.println("Hilo1 creado"); // Mensaje que indica que el hilo ha sido creado
    }

    // Método que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        // Bucle infinito que imprime "TIC" cada 5 segundos
        for (int i = 0; i > -1; i++) {
            System.out.println("TIC"); // Imprime "TIC"
            try {
                // Hace una pausa de 5000 milisegundos (5 segundos)
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                // Manejo de excepción si el hilo es interrumpido mientras duerme
                throw new RuntimeException(e);
            }
        }
    }
}
