package pack4_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Scanner;

import org.apache.commons.net.pop3.POP3Client;
import org.apache.commons.net.pop3.POP3MessageInfo;

public class Actividad5 {
    public static void main(String[] args) {
        // Crear un objeto Scanner para leer la entrada del usuario
        Scanner entrada = new Scanner(System.in);

        // Solicitar al usuario los datos de conexión
        System.out.print("Servidor POP3: ");
        String servidorPOP = entrada.next();

        System.out.print("Puerto: ");
        int puertoPOP = entrada.nextInt();

        System.out.print("Usuario: ");
        String usuario = entrada.next();

        System.out.print("Contraseña: ");
        String clave = entrada.next();

        // Crear cliente POP3 para conectar con el servidor
        POP3Client clientePOP = new POP3Client();
        try {
            // Intentar conectar con el servidor POP3
            clientePOP.connect(servidorPOP, puertoPOP);
            System.out.println("Conexión establecida: " + clientePOP.getReplyString());

            // Intentar autenticar con el usuario y la contraseña proporcionados
            if (!clientePOP.login(usuario, clave)) {
                System.err.println("Error: Autenticación fallida");
                clientePOP.disconnect(); // Desconectar si la autenticación falla
                return;
            }

            System.out.println("Autenticación correcta");

            // Obtener la lista de mensajes en la bandeja de entrada
            POP3MessageInfo[] mensajes = clientePOP.listMessages();
            if (mensajes == null || mensajes.length == 0) {
                System.out.println("No hay correos en la bandeja de entrada");
            } else {
                System.out.println("Mensajes en la bandeja: " + mensajes.length);
                // Iterar sobre los mensajes y leer su contenido
                for (POP3MessageInfo mensaje : mensajes) {
                    System.out.println("Leyendo mensaje " + mensaje.number);

                    Reader reader = clientePOP.retrieveMessage(mensaje.number); // Obtener el mensaje
                    if (reader != null) {
                        try (BufferedReader bufferedReader = new BufferedReader(reader)) {
                            String linea;
                            while ((linea = bufferedReader.readLine()) != null) {
                                System.out.println(linea);  // Mostrar el contenido del mensaje
                            }
                        }
                    } else {
                        System.out.println("No se pudo recuperar el mensaje " + mensaje.number);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error al conectar con el servidor");
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar la sesión y desconectar del servidor al finalizar
            try {
                clientePOP.logout();  // Realizar logout del servidor POP3
                clientePOP.disconnect();  // Desconectar del servidor
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Proceso finalizado");
    }
}