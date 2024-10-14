package Actividad2;

public class main {
    public static void main(String[] args) {
        Hilo1 hilo1 = new Hilo1();
        Hilo2 hilo2 = new Hilo2();
        new Thread(hilo1).start();
        new Thread(hilo2).start();

    }
}
