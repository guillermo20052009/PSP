package Actividad1;

// Clase que extiende Thread para crear un hilo que imprime "TAC"
public class Hilo2 extends Thread {

    // Constructor de Hilo2
    public Hilo2() {
        System.out.println("Hilo2 creado"); // Mensaje que indica que el hilo ha sido creado
    }

    // Metodo que se ejecuta cuando se inicia el hilo
    @Override
    public void run() {
        // Bucle infinito que imprime "TAC" cada 5 segundos
        for (int i = 0; i > -1; i++) {
            System.out.println("TAC"); // Imprime "TAC"
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
