package Actividad3_11;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorChat {
    static final int MAXIMO = 10;

    public static void main(String[] args) throws IOException {
        int puerto = 44444;
        ServerSocket servidor = new ServerSocket(puerto);
        System.out.println("Servidor conectado");
        Socket[] tabla = new Socket[MAXIMO];
        ComunHilos comun = new ComunHilos(tabla, MAXIMO, 0, 0);

        while (comun.getConexiones() < MAXIMO) {
            Socket cliente = servidor.accept();
            comun.addTabla(cliente, comun.getConexiones());
            comun.setActuales(comun.getActuales() + 1);
            comun.setConexiones(comun.getConexiones() + 1);

            HiloServidorChat hilo = new HiloServidorChat(cliente, comun);
            hilo.start();
        }
        servidor.close();
    }
}
