package Ejercicio3;

import java.net.*;
import java.io.*;

public class Cliente {
    private static final int PUERTO = 12345;
    private static final int TAMANO_BUFFER = 256;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print("Introduce el ID del alumno (* para salir): ");
                String idAlumno = entrada.readLine();

                if (idAlumno.equals("*")) {
                    break;
                }

                byte[] buffer = idAlumno.getBytes();
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), PUERTO);
                socket.send(paquete);

                buffer = new byte[TAMANO_BUFFER];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

