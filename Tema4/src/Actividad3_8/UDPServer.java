package Actividad3_8;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Servidor UDP que recibe objetos Persona y devuelve respuestas modificadas.
 */
public class UDPServer {
    public static void main(String[] args) {
        final int PORT = 9876;  // Puerto en el que el servidor escucha

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("Servidor UDP iniciado en el puerto " + PORT);
            byte[] receiveData = new byte[1024];  // Buffer para recibir datos
            byte[] sendData;  // Buffer para enviar datos
            boolean encontrado = false;  // Bandera para controlar el estado de finalización

            while (!encontrado) {
                // Recibir paquete
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);  // Esperar recepción de paquete

                // Convertir los bytes recibidos en un objeto Persona
                ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData(), 0, receivePacket.getLength());  // Flujo de entrada desde el paquete recibido
                ObjectInputStream ois = new ObjectInputStream(bais);  // Convertir bytes a objeto Persona
                Persona persona = (Persona) ois.readObject();  // Leer objeto Persona deserializado

                System.out.println("Mensaje recibido: " + persona);  // Mostrar mensaje recibido

                // Finalizar servidor si el nombre de la persona es "*"
                if ("*".equals(persona.getNombre())) {
                    System.out.println("Servidor finalizado.");
                    encontrado = true;  // Cambiar bandera para finalizar
                }

                // Modificar el objeto Persona para enviarlo de vuelta
                persona.setNombre(persona.getNombre().toUpperCase());  // Convertir nombre a mayúsculas
                persona.setEdad(persona.getEdad() + 5);  // Aumentar edad en 5

                // Serializar el objeto Persona para enviarlo al cliente
                ByteArrayOutputStream baos = new ByteArrayOutputStream();  // Flujo de salida en memoria
                ObjectOutputStream oos = new ObjectOutputStream(baos);  // Convertir objeto en flujo binario
                System.out.println("Mensaje enviado: " + persona);  // Mostrar mensaje que se enviará
                oos.writeObject(persona);  // Escribir objeto en flujo
                sendData = baos.toByteArray();  // Obtener datos serializados como byte array

                // Enviar el paquete de vuelta al cliente
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivePacket.getAddress(), receivePacket.getPort());  // Crear paquete de envío
                serverSocket.send(sendPacket);  // Enviar paquete al cliente
            }
        } catch (Exception e) {
            e.printStackTrace();  // Mostrar rastreo de la excepción en caso de error
        }
    }
}
