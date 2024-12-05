package Actividad3_4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        int puerto = 8080;  // Puerto en el que el servidor escucha

        try {
            // Crea el ServerSocket y empieza a escuchar el puerto
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Aceptar dos clientes
            Socket socket = serverSocket.accept();  // Acepta la primera conexión de cliente
            System.out.println("Primer cliente conectado.");



            OutputStream salida = null;
            salida = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(salida);
            String envio = "DEVOLVER EN MAYUSCULAS";
            dataOutputStream.writeUTF(envio);
            System.out.println("Mensaje enviado en el servidor: "+envio);

            InputStream in = null;
            in = socket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            System.out.println("Mensaje del cliente: "+dis.readUTF());

            serverSocket.close();  // Cerrar el servidor
            salida.close();
            dataOutputStream.close();
            in.close();
            dis.close();
            socket.close();
            System.out.println("Servidor cerrado.");

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
