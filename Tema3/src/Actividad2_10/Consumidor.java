package Actividad2_10;

public class Consumidor extends Thread {
    private Cola cola;
    private int n;
    public Consumidor(Cola cola, int n) {
        this.cola = cola;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Consumidor "+n+" recoge el numero: "+cola.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try{
                sleep(110);
            } catch (InterruptedException e) { }
        }
    }
}
