package Actividad4;

public class MiHilo extends Thread {
    private int 
    private SolicitarSuspender solicitarSuspender=new SolicitarSuspender();

    public void Suspende(){
        solicitarSuspender.setSuspender(true);
    }
    public void Reanuda(){
        solicitarSuspender.setSuspender(false);
    }

    @Override
    public void run() {
        try{
            while(true){


             solicitarSuspender.esperando();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
