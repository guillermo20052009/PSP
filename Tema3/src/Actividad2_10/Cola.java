package Actividad2_10;

public class Cola {
    private int numero;
    private boolean disponible=false;

    public synchronized int get() throws InterruptedException {
        while (!disponible){
            wait();
        }
            disponible=false;
            notify();
            return numero;
    }
    public synchronized void put(int valor) throws InterruptedException {
        while (disponible){
            wait();
        }
        numero=valor;
        disponible=true;
        notify();
    }
}
