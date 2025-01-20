package Actividad2_7;

public class HiloThreadSync extends Thread {
    static int cont;

    public HiloThreadSync() {
    }

    public void run() {
        synchronized (HiloThreadSync.class) {
            for (int i = 0; i < 5000; i++) {
                cont++;
            }
        }

    }
    public static void main(String[] args) {
        HiloThreadSync h1 = new HiloThreadSync();
        HiloThreadSync h2 = new HiloThreadSync();
        HiloThreadSync h3 = new HiloThreadSync();
        HiloThreadSync h4 = new HiloThreadSync();
        HiloThreadSync h5 = new HiloThreadSync();

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        System.out.println(cont);


    }
}
