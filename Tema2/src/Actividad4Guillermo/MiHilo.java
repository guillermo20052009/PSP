package Actividad4;

public class MiHilo extends Thread {

    // Instancia de la clase SolicitarSuspender para controlar la suspensión
    private SolicitarSuspender solicitarSuspender = new SolicitarSuspender();
    private int contador = 0; // Contador del hilo
    private boolean parar = false; // Indica si el hilo debe detenerse

    // Constructor sin parámetros
    public MiHilo() {}

    // Método para suspender el hilo
    public void suspende() {
        solicitarSuspender.setSuspender(true); // Activa la suspensión
    }

    // Método para reanudar el hilo si estaba suspendido
    public void reanuda() {
        solicitarSuspender.setSuspender(false); // Desactiva la suspensión
    }

    // Método para detener el hilo completamente
    public void detener() {
        parar = true; // Indica que el hilo debe detenerse
        reanuda(); // Asegura que el hilo no esté suspendido, permitiendo la salida del bucle
        interrupt(); // Interrumpe el hilo en caso de que esté esperando o en sleep
    }

    @Override
    public void run() {
        try {
            while (!parar) { // Ejecuta el bucle mientras no se indique detener el hilo
                contador++; // Incrementa el contador en cada iteración
                System.out.println(contador); // Imprime el valor actual del contador

                // Pausa de 500 ms entre iteraciones
                try {
                    Thread.sleep(500); // El hilo se pausa por 500 ms
                } catch (InterruptedException e) {
                    // Si el hilo es interrumpido durante el sleep, sale del bucle
                    System.out.println("Hilo interrumpido durante sleep");
                    break;
                }

                // Verifica si el hilo está en estado de suspensión
                solicitarSuspender.esperando(); // Espera si el hilo está suspendido
            }
            // Mensaje final cuando el hilo se detiene
            System.out.println("Hilo detenido. Contador final: " + contador);
        } catch (Exception e) {
            System.out.println("Excepción en hilo: " + e.getMessage()); // Captura y muestra cualquier excepción
        }
    }

    // Método para obtener el valor actual del contador
    public int getContador() {
        return contador;
    }
}
