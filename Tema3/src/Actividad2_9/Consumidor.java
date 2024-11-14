package Actividad2_9;

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
            System.out.println("Consumidor recoge el numero: "+cola.get());
            try{
                sleep(110);
            } catch (InterruptedException e) { }
        }
    }
}
