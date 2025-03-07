package Actividad4;

import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;  // Puerto en el que el servidor escucha
        boolean salir = false;  // Variable para determinar cuándo salir del bucle
        System.setProperty("javax.net.ssl.keyStore","C:\\Users\\guill\\OneDrive\\Escritorio\\PSP\\Tema6\\AlmacenSrv");
        System.setProperty("javax.net.ssl.keyStorePassword", "1234567");
        SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

        try (ServerSocket serverSocket = (ServerSocket) ssf.createServerSocket(puerto)) {
            System.out.println("Servidor escuchando en el puerto " + puerto);

            try (SSLSocket socket = (SSLSocket) serverSocket.accept() ;  // Aceptar la conexión del cliente
                 ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());  // Flujo de entrada para recibir objetos
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {  // Flujo de salida para enviar objetos

                Numero numero;  // Variable para almacenar objetos del tipo Numero
                do {
                    System.out.println("Cliente conectado.");

                    // Leer el objeto enviado por el cliente
                    numero = (Numero) objectInputStream.readObject();
                    System.out.println("Número recibido: " + numero.getNumero());

                    // Si el número es 0, cerrar el bucle y terminar el servidor
                    if (numero.getNumero() == 0) {
                        System.out.println("Número recibido es 0. Cerrando servidor.");
                        salir = true;
                    }

                    // Calcular cuadrado y cubo del número recibido
                    numero.setCuadrado(numero.getNumero() * numero.getNumero());
                    numero.setCubo(numero.getNumero() * numero.getNumero() * numero.getNumero());

                    // Enviar el objeto con resultados de vuelta al cliente
                    objectOutputStream.writeObject(numero);
                    System.out.println("Objeto enviado de vuelta al cliente.");

                } while (!salir);  // Mantener el bucle hasta que se reciba el número 0

            } catch (ClassNotFoundException e) {
                System.err.println("Error al leer el objeto: " + e.getMessage());
            } catch (IOException e) {
                System.err.println("Error en la conexión: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
