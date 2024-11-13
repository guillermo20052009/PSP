package Actividad2_6;

public class main1 {
    public static void main(String[] args) {
        Ejemplo1 h1= new Ejemplo1("Hilo1");
        Ejemplo1 h2= new Ejemplo1("Hilo2");
        Ejemplo1 h3= new Ejemplo1("Hilo3");

        Ejemplo1[] hilos = new Ejemplo1[2000]; // Array para almacenar desde h4 hasta h15

        for (int i = 4; i <= 2003; i++) {
            hilos[i - 4] = new Ejemplo1("hilo" + i);
        }


        h1.setPriority(Thread.MIN_PRIORITY);
        h2.setPriority(Thread.MAX_PRIORITY);
        h3.setPriority(Thread.NORM_PRIORITY);

       for(int i=0;i<2000;i++){
            hilos[i].start();
       }
       h1.start();
       h2.start();
       h3.start();

        try{
            Thread.sleep(20000);
        } catch(InterruptedException e){}

        for (int i=0;i<2000;i++){
            hilos[i].pararHilo();
        }

        System.out.println("h1 (Minima Prioridad) : "+h1.getCont());
        System.out.println("h2 (Maxima Prioridad) : "+h2.getCont());
        System.out.println("h3 (Prioridad normal) : "+h3.getCont());

    }
}
