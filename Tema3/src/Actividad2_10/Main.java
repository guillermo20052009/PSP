package Actividad2_10;

import javax.swing.plaf.synth.SynthOptionPaneUI;

public class Main {
    public static void main(String[] args) {
//        System.out.println("1ª Parte");
//        Cola cola = new Cola();
//        Productor p=new Productor(cola,1);
//        Consumidor c=new Consumidor(cola,1);
//        Consumidor c2=new Consumidor(cola,2);
//        p.start();
//        c.start();
//        c2.start();

        System.out.println("\n2ª Parte: Ping Pong\n");
        ColaPing cola2 = new ColaPing();
        ProductorPing productorPing = new ProductorPing(cola2,1);
        ConsumidorPing  consumidorPing = new ConsumidorPing(cola2,1);
        ConsumidorPing  consumidorPing2 = new ConsumidorPing(cola2,2);
        productorPing.start();
        consumidorPing.start();
        consumidorPing2.start();

    }
}
