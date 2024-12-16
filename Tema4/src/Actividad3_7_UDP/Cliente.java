package Actividad3_7_UDP;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Dirección del servidor
        int puerto = 8080; // Puerto del servidor
        boolean salir = false;
        Scanner sc = new Scanner(System.in);

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            System.out.println("Cliente UDP iniciado.");

            while (!salir) {
                // Crear objeto Numero
                Numero numero = new Numero();
                System.out.print("Introduce un número (0 para salir): ");
                numero.setNumero(sc.nextInt());

                // Serializar el objeto
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(numero);
                byte[] sendBuffer = baos.toByteArray();

                // Enviar paquete al servidor
                InetAddress serverAddress = InetAddress.getByName(host);
                DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, puerto);
                clientSocket.send(sendPacket);

                // Si el número es 0, finalizar el cliente
                if (numero.getNumero() == 0) {
                    System.out.println("Finalizando cliente...");
                    salir = true;
                    break;
                }

                // Recibir respuesta del servidor
                byte[] receiveBuffer = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                clientSocket.receive(receivePacket);

                // Deserializar el objeto recibido
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Numero respuesta = (Numero) ois.readObject();

                // Mostrar respuesta
                System.out.println("Respuesta del servidor: " + respuesta);
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en el cliente: " + e.getMessage());
        }
    }
}
