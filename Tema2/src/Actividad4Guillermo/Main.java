package Actividad4;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Crea e inicia un nuevo hilo de tipo MiHilo
        MiHilo miHilo = new MiHilo();
        miHilo.start();
        boolean condicion = true; // Controla el ciclo principal

        // Inicializa el Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        String input; // Almacena el comando introducido por el usuario

        // Mensaje para indicar al usuario los comandos disponibles
        System.out.println("Introduce 'S' para suspender el hilo, 'R' para reanudarlo o '*' para finalizar:");

        // Bucle principal que se ejecuta mientras la condición sea verdadera
        while (condicion) {
            input = scanner.nextLine(); // Lee la entrada del usuario

            // Si el usuario introduce '*', interrumpe y finaliza el hilo
            if (input.equals("*")) {
                miHilo.interrupt(); // Interrumpe el hilo para finalizar
                condicion = false; // Cambia la condición para salir del bucle
            } else if (input.equalsIgnoreCase("S")) { // Suspende el hilo si el comando es 'S'
                miHilo.suspende(); // Suspende el hilo
                System.out.println("Hilo suspendido.");
            } else if (input.equalsIgnoreCase("R")) { // Reanuda el hilo si el comando es 'R'
                miHilo.reanuda(); // Reanuda el hilo
                System.out.println("Hilo reanudado.");
            }
        }

        // Muestra el valor final del contador después de finalizar el hilo
        System.out.println("Valor final del contador: " + miHilo.getContador());

        // Cierra el Scanner para liberar recursos
        scanner.close();
    }
}
