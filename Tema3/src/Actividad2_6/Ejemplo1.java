package Actividad2_6;

public class Ejemplo1 extends Thread{
    private int cont = 0;
    private boolean stop = false;
    public Ejemplo1(String nombre){
        super(nombre);
    }
    public void run(){
        while(!stop){
            try{
                Thread.sleep(2);
            } catch (InterruptedException e){}
            cont++;
        }

    }
    public int getCont() {
        return cont;
    }
    public void pararHilo(){
        stop = true;
    }
}
