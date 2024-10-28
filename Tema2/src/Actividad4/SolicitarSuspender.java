package Actividad4;

public class SolicitarSuspender {
    private boolean suspender;


    public SolicitarSuspender (){}

    public void setSuspender(boolean suspender) {
        this.suspender = suspender;
        notifyAll();
    }
    public synchronized void esperando() throws InterruptedException {
        while (suspender) {
            wait();
        }
    }
}
