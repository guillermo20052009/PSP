package Actividad3_11;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class HiloServidorChat extends Thread {
    private DataInputStream fentrada;
    private final Socket socket;
    private final ComunHilos comun;
    private boolean salir = false;

    public HiloServidorChat(Socket socket, ComunHilos comun) {
        this.socket = socket;
        this.comun = comun;
        try {
            fentrada = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.err.println("Error al obtener el flujo de entrada del cliente: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Número de conexiones actuales: " + comun.getActuales());

        // Enviar mensajes previos al nuevo cliente
        EnviarMensaje(comun.getMensajes());

        while (!salir) {
            try {
                // Leer mensaje del cliente
                String cadena = fentrada.readUTF();
                if (cadena.trim().equals("*")) { // El cliente quiere salir
                    comun.setActuales(comun.getActuales() - 1);
                    System.out.println("Número de conexiones actuales tras salida: " + comun.getActuales());
                    salir = true;
                } else {
                    // Agregar mensaje al historial y enviarlo a todos los clientes
                    comun.setMensajes(comun.getMensajes() + cadena + "\n");
                    EnviarMensaje(comun.getMensajes());
                }
            } catch (IOException e) {
                System.err.println("Error en la comunicación con el cliente: " + e.getMessage());
                salir = true; // Salir del bucle si hay un error
            }
        }

        // Cerrar el socket al finalizar el hilo
        cerrarSocket();
    }

    private void EnviarMensaje(String mensaje) {
        for (int i = 0; i < comun.getConexiones(); i++) {
            Socket s1 = comun.getTabla(i);
            if (s1 != null && !s1.isClosed()) {
                try {
                    DataOutputStream dos = new DataOutputStream(s1.getOutputStream());
                    dos.writeUTF(mensaje);
                } catch (IOException e) {
                    System.err.println("Error al enviar mensaje al cliente: " + e.getMessage());
                }
            }
        }
    }

    private void cerrarSocket() {
        try {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar el socket: " + e.getMessage());
        }
    }
}
