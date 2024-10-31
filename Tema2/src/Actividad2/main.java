package Actividad2;



public class main {
    public static void main(String[] args) {
        // Crear instancias de Hilo1 y Hilo2
        Hilo1 hilo1 = new Hilo1(); // Crea un nuevo hilo que imprimirá "TIC"
        Hilo2 hilo2 = new Hilo2(); // Crea un nuevo hilo que imprimirá "TAC"

        // Iniciar ambos hilos
        new Thread(hilo1).start(); // Inicia el hilo que ejecuta Hilo1
        new Thread(hilo2).start(); // Inicia el hilo que ejecuta Hilo2
    }
}