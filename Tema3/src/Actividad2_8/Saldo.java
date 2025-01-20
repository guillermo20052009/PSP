package Actividad2_8;

public class Saldo {
    private int saldo;

    // Constructor que inicializa el saldo
    public Saldo(int saldoInicial) {
        this.saldo = saldoInicial;
    }
    public synchronized int getSaldo() {
        return saldo;
    }
    public synchronized void setSaldo(int saldo) {
        this.saldo = saldo;
    }
    public synchronized void addSaldo(String nombre, int cantidad) {
        int saldoInicial = saldo;
        saldo += cantidad;
        System.out.println("Hilo " + nombre + " añade " + cantidad +
                " al saldo. Saldo inicial: " + saldoInicial +
                ", Saldo final: " + saldo);
    }
}