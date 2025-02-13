package Actividad3;

import java.io.*;
import java.security.*;
import java.security.spec.*;

public class Actividad3 {
    public static void main(String[] args) {
        try {
            // Generar el par de claves RSA
            KeyPairGenerator generadorClaves = KeyPairGenerator.getInstance("RSA");
            generadorClaves.initialize(2048); // Se define el tamaño de la clave (2048 bits)
            KeyPair parClaves = generadorClaves.generateKeyPair(); // Se genera el par de claves (pública y privada)
            PublicKey clavePublica = parClaves.getPublic(); // Se obtiene la clave pública
            PrivateKey clavePrivada = parClaves.getPrivate(); // Se obtiene la clave privada

            // Guardar las claves en archivos
            guardarClavePublica(clavePublica, "clave.publica"); // Guardar la clave pública en un archivo
            guardarClavePrivada(clavePrivada, "clave.privada"); // Guardar la clave privada en un archivo

            // Mensaje indicando que las claves se han generado y guardado correctamente
            System.out.println("Claves generadas y guardadas correctamente.");

        } catch (Exception e) {
            // Captura cualquier excepción y muestra el error
            e.printStackTrace();
        }
    }

    // Método para guardar la clave pública en un archivo
    private static void guardarClavePublica(PublicKey clavePublica, String nombreArchivo) throws IOException {
        X509EncodedKeySpec x509Spec = new X509EncodedKeySpec(clavePublica.getEncoded());
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo)) {
            fos.write(x509Spec.getEncoded()); // Se escribe la clave pública en el archivo
        }
    }

    // Método para guardar la clave privada en un archivo
    private static void guardarClavePrivada(PrivateKey clavePrivada, String nombreArchivo) throws IOException {
        PKCS8EncodedKeySpec pkcs8Spec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo)) {
            fos.write(pkcs8Spec.getEncoded()); // Se escribe la clave privada en el archivo
        }
    }
}
