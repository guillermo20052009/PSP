package Actividad2_10;

public class ProductorPing extends Thread {
    private ColaPing cola;
    private int n;
    private boolean continuar = true;

    public void detener() {
        continuar = false;
    }
    public ProductorPing(ColaPing cola, int n) {
        this.cola = cola;
        this.n = n;
    }
    public void dormir(){
        try {
            sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        while (continuar) {
            try {
                cola.put("Ping");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dormir();
            try {
                cola.put("pong");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        dormir();
    }
}

