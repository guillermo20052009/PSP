package Actividad2_7;

public class HiloThreadNosync extends Thread{
    static int cont;

    public HiloThreadNosync() {
    }

    public void run() {
            for (int i = 0; i < 5000; i++) {
                cont++;
            }

    }
    public static void main(String[] args) {
        HiloThreadNosync h1 = new HiloThreadNosync();
        HiloThreadNosync h2 = new HiloThreadNosync();
        HiloThreadNosync h3 = new HiloThreadNosync();
        HiloThreadNosync h4 = new HiloThreadNosync();
        HiloThreadNosync h5 = new HiloThreadNosync();

        h1.start();
        h2.start();
        h3.start();
        h4.start();
        h5.start();

        System.out.println(cont);


    }
}
