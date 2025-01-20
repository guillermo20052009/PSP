package Actividad2_8;

public class Main {

    public static void main(String[] args) {
        Saldo saldo = new Saldo(1000);
        System.out.println("Saldo inicial: " + saldo.getSaldo());

        // Crear y lanzar varios hilos que añaden diferentes cantidades al saldo
        SaldoHilo hilo1 = new SaldoHilo(saldo, "Hilo1", 200);
        SaldoHilo hilo2 = new SaldoHilo(saldo, "Hilo2", 400);
        SaldoHilo hilo3 = new SaldoHilo(saldo, "Hilo3", 150);

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();

        System.out.println("Saldo final: " + saldo.getSaldo());

    }
}
