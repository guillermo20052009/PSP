package Actividad2_7;

public class HiloRunNoSync implements Runnable{
    static int cont;

    public HiloRunNoSync() {
    }

    public void run() {
        for (int i = 0; i < 5000; i++) {
            cont++;
        }
    }
    public static void main(String[] args) {
        HiloRunNoSync h1=new HiloRunNoSync();
        HiloRunNoSync h2=new HiloRunNoSync();
        HiloRunNoSync h3=new HiloRunNoSync();
        HiloRunNoSync h4=new HiloRunNoSync();
        HiloRunNoSync h5=new HiloRunNoSync();

        h1.run();
        h2.run();
        h3.run();
        h4.run();
        h5.run();

        System.out.println(cont);


    }
}
