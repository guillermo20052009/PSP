package Actividad3_8;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 9876;

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PORT);
            byte[] receiveData = new byte[1024];
            byte[] sendData;
            boolean encontrado = false;

            while (!encontrado) {
                // Recibir paquete
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                // Convertir los bytes recibidos en un objeto Persona
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());
                ObjectInputStream ois = new ObjectInputStream(bais);
                Persona persona = (Persona) ois.readObject();

                System.out.println("Mensaje recibido: " + persona);

                // Finalizar servidor si el nombre de la persona es "*"
                if ("*".equals(persona.getNombre())) {
                    System.out.println("Servidor finalizado.");
                    encontrado = true;
                }

                // Modificar el objeto Persona para enviarlo de vuelta
                persona.setNombre(persona.getNombre().toUpperCase());
                persona.setEdad(persona.getEdad() + 5);

                // Serializar el objeto Persona para enviarlo al cliente
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                System.out.println("Mensaje enviado: " + persona);
                oos.writeObject(persona);
                sendData = baos.toByteArray();

                // Enviar el paquete de vuelta al cliente
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                        receivePacket.getAddress(), receivePacket.getPort());
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
