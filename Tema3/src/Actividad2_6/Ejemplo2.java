package Actividad2_6;

public class Ejemplo2 extends Thread {
    private int cont = 0;
    private boolean shouldPrint; // Variable para controlar si imprime o no

    public Ejemplo2(String nombre, boolean shouldPrint) {
        super(nombre);
        this.shouldPrint = shouldPrint;
    }

    @Override
    public void run() {
            if (shouldPrint) {
                System.out.println("Ejecutando hilo " + getName());
                for (int i = 0; i < 5; i++) {
                    System.out.println(getName() + ": [" + (i+1) + "]");
                }
            }
        }
}
