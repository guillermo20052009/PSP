package Actividad2_8;

public class SaldoHilo extends Thread {
    static Saldo saldo;
    private int cantidad;

    // Constructor que inicializa los parámetros necesarios
    public SaldoHilo(Saldo saldo, String nombre, int cantidad) {
        super(nombre);
        this.cantidad = cantidad;
        this.saldo=saldo;
    }


    @Override
    public void run() {
        saldo.addSaldo(this.getName(), cantidad);
    }
}
