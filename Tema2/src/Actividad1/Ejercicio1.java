package Actividad1;

// Comentario: No se imprime siempre ordenado el TIC TAC porque una característica de los hilos
// es que se ejecutan de manera concurrente, lo que significa que no se garantiza un orden específico
public class Ejercicio1 extends Thread {

    // Metodo que se ejecuta cuando el hilo es iniciado
    @Override
    public void run() {
        // Imprime el ID del hilo actual
        System.out.println("ID del hilo: " + getId());
    }

    public static void main(String[] args) {
        // Bucle para crear y lanzar 5 hilos
        for (int i = 0; i < 5; i++) {
            // Crea una nueva instancia de Ejercicio1 (un hilo)
            Ejercicio1 thread = new Ejercicio1();
            // Inicia la ejecución del hilo
            thread.start();
        }
    }
}
