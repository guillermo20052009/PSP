package Actividad4;

public class SolicitarSuspender {
    // Variable para controlar el estado de suspensión
    private boolean suspender;

    // Constructor sin parámetros
    public SolicitarSuspender() {}

    // Método sincronizado para establecer el estado de suspensión
    public synchronized void setSuspender(boolean suspender) {
        this.suspender = suspender; // Asigna el nuevo valor de suspensión
        notifyAll(); // Notifica a todos los hilos en espera para que revisen el estado
    }

    // Método sincronizado para que los hilos esperen mientras esté suspendido
    public synchronized void esperando() throws InterruptedException {
        while (suspender) { // Mientras la variable suspender esté activada
            wait(); // Espera hasta que se llame a notifyAll() en setSuspender
        }
    }
}