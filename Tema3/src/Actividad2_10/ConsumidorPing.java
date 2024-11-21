package Actividad2_10;

public class ConsumidorPing extends Thread {
    private ColaPing cola;
    private int n;
    boolean continuar=true;

    public void detener() {
        continuar = false;
    }

    public ConsumidorPing(ColaPing cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    @Override
    public void run() {
        while (continuar) {
            try {
                System.out.println(cola.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                sleep(610);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


        }
}




