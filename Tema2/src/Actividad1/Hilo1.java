package Actividad1;

public class Hilo1 extends Thread {
    public Hilo1() {
        System.out.println("Hilo1 creado");
    }

    @Override
    public void run() {
        for (int i = 0; i > -1; i++) {
            System.out.println("TIC");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
