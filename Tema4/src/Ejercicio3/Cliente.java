package Ejercicio3;

import java.net.*;
import java.io.*;

public class Cliente {
    // Puerto utilizado por el servidor para recibir los datagramas
    private static final int PUERTO = 12345;

    // Tamaño del buffer para los datagramas
    private static final int TAMANO_BUFFER = 256;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {  // Se crea un socket de datagramas
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                // Pedir al usuario el ID del alumno hasta que se indique "*"
                System.out.print("Introduce el ID del alumno (* para salir): ");
                String idAlumno = entrada.readLine();

                // Verificar si el usuario quiere salir del programa
                if (idAlumno.equals("*")) {
                    break;
                }

                // Convertir el ID del alumno a bytes y crear el paquete de datagrama
                byte[] buffer = idAlumno.getBytes();
                DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, InetAddress.getByName("localhost"), PUERTO);

                // Enviar el paquete al servidor
                socket.send(paquete);

                // Recibir la respuesta del servidor
                buffer = new byte[TAMANO_BUFFER];
                DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);
                socket.receive(paqueteRecibido);

                // Convertir la respuesta del servidor a una cadena y mostrarla al cliente
                String respuesta = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
                System.out.println("Respuesta del servidor: " + respuesta);
            }

        } catch (IOException e) {
            e.printStackTrace();  // Manejo de excepciones de entrada/salida
        }
    }
}
