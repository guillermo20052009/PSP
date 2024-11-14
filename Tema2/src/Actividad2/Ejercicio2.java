package Actividad2;

public class Ejercicio2 implements Runnable {

    //Sin el slepp no se aprecia que son 5 ejecuciones diferentes, al poner sleep y esperar el tiempo que indiquemos se diferencias
    // los 5 hilos perfectamente

    // Metodo que se ejecuta cuando el hilo es iniciado
    @Override
    public void run() {
        // Imprime el ID del hilo actual en ejecución
        System.out.println("ID del hilo: " + Thread.currentThread().getId());
    }

    public static void main(String[] args) {
        // Bucle para crear y lanzar 5 hilos
        for (int i = 0; i < 5; i++) {
            // Crea una nueva instancia de Thread, pasando la implementación de Runnable (Ejercicio2)
            Thread thread = new Thread(new Ejercicio2());
            try {
                // Pausa la ejecución del hilo principal durante 2 segundos antes de iniciar el siguiente hilo
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // Maneja la excepción si el hilo es interrumpido durante el sueño
                throw new RuntimeException(e);
            }
            // Inicia la ejecución del nuevo hilo
            thread.start();
        }
    }
}
