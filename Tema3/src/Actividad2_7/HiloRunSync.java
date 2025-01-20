package Actividad2_7;

public class HiloRunSync implements Runnable {
    static int cont;

    public HiloRunSync() {
        // Constructor vacío para no hacer nada con el nombre
    }

    public void run() {
        synchronized (HiloRunSync.class) {
            for (int i = 0; i < 5000; i++) {
                cont++;
            }
        }
    }

    public static void main(String[] args) {
        HiloRunSync h1=new HiloRunSync();
        HiloRunSync h2=new HiloRunSync();
        HiloRunSync h3=new HiloRunSync();
        HiloRunSync h4=new HiloRunSync();
        HiloRunSync h5=new HiloRunSync();

        h1.run();
        h2.run();
        h3.run();
        h4.run();
        h5.run();

        System.out.println(cont);


    }
}



