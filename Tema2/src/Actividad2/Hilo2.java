package Actividad2;

public class Hilo2 implements Runnable {
    public Hilo2() {
        System.out.println("Hilo2 creado");
    }

    @Override
    public void run() {
        for (int i = 0; i > -1; i++) {
            System.out.println("TAC");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
