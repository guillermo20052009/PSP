package Actividad3_9;

import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        final int PUERTO = 44444;

        System.out.println("Servidor iniciado...");
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            while (true) {
                // Aceptar una conexión
                Socket socket = serverSocket.accept();
                System.out.println("=>Conecta IP " + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());

                // Crear un hilo para atender al cliente
                new Thread(new ManejadorCliente(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ManejadorCliente implements Runnable {
    private Socket socket;

    public ManejadorCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String mensaje;
            while ((mensaje = entrada.readLine()) != null) {
                if (mensaje.equals("*")) {
                    System.out.println("=>Desconecta IP " + socket.getInetAddress() + ", Puerto remoto: " + socket.getPort());
                    break;
                }
                System.out.println("Mensaje recibido: " + mensaje);
                salida.println(mensaje.toUpperCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
