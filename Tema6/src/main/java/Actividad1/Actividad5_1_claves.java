package Actividad1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class Actividad5_1_claves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer las cadenas de texto y la clave
        System.out.println("Introduce la primera cadena:");
        String cadena1 = scanner.nextLine();

        System.out.println("Introduce la segunda cadena:");
        String cadena2 = scanner.nextLine();

        System.out.println("Introduce la clave (16 caracteres):");
        String clave = scanner.nextLine();

        // Verificar que la clave tenga exactamente 16 caracteres
        if (clave.length() != 16) {
            System.out.println("La clave debe tener exactamente 16 caracteres.");
            return;
        }

        // Convertir la clave en una clave secreta para AES
        SecretKeySpec secretKey = new SecretKeySpec(clave.getBytes(), "AES");

        // Cifrar las dos cadenas con AES
        String cifrado1 = cifrarAES(cadena1, secretKey);
        String cifrado2 = cifrarAES(cadena2, secretKey);

        // Generar los hashes SHA-256 de los textos cifrados
        String hash1 = generarSHA256(cifrado1);
        String hash2 = generarSHA256(cifrado2);

        // Mostrar los textos cifrados
        System.out.println("\nTexto cifrado 1: " + cifrado1);
        System.out.println("Texto cifrado 2: " + cifrado2);

        // Mostrar los hashes generados
        System.out.println("\n Hash SHA-256 de la primera cadena cifrada: " + hash1);
        System.out.println(" Hash SHA-256 de la segunda cadena cifrada: " + hash2);

        // Comparar si los hashes son iguales o no
        if (hash1.equals(hash2)) {
            System.out.println("\n Los hashes son iguales.");
        } else {
            System.out.println("\n Los hashes son diferentes.");
        }

        // Descifrar los textos cifrados y mostrar los resultados
        System.out.println("\nDescifrado de la primera cadena: " + descifrarAES(cifrado1, secretKey));
        System.out.println("Descifrado de la segunda cadena: " + descifrarAES(cifrado2, secretKey));

        // Cerrar el scanner
        scanner.close();
    }

    // Método para cifrar con AES
    private static String cifrarAES(String texto, SecretKeySpec clave) {
        try {
            // Se crea una instancia del cifrador AES
            Cipher cipher = Cipher.getInstance("AES");
            // Se inicializa en modo cifrado con la clave
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            // Se cifra el texto y se convierte en un arreglo de bytes
            byte[] cifrado = cipher.doFinal(texto.getBytes());
            // Se codifica el resultado en Base64 y se devuelve
            return Base64.getEncoder().encodeToString(cifrado);
        } catch (Exception e) {
            // Si ocurre un error, se lanza una excepción con mensaje
            throw new RuntimeException("Error al cifrar con AES", e);
        }
    }

    // Método para descifrar con AES
    private static String descifrarAES(String textoCifrado, SecretKeySpec clave) {
        try {
            // Se crea una instancia del cifrador AES
            Cipher cipher = Cipher.getInstance("AES");
            // Se inicializa en modo descifrado con la clave
            cipher.init(Cipher.DECRYPT_MODE, clave);
            // Se decodifica el texto cifrado en Base64 y se descifra
            byte[] decifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            // Se devuelve el texto descifrado
            return new String(decifrado);
        } catch (Exception e) {
            // Si ocurre un error, se lanza una excepción con mensaje
            throw new RuntimeException("Error al descifrar con AES", e);
        }
    }

    // Método para generar un hash SHA-256 del texto
    private static String generarSHA256(String texto) {
        try {
            // Se crea una instancia de MessageDigest para SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            // Se calcula el hash
            byte[] hash = digest.digest(texto.getBytes());
            // Se convierte el hash en formato hexadecimal
            return convertirHexadecimal(hash);
        } catch (Exception e) {
            // Si ocurre un error, se lanza una excepción con mensaje
            throw new RuntimeException("Error al generar SHA-256", e);
        }
    }

    // Método para convertir el resumen (hash) en una cadena hexadecimal
    private static String convertirHexadecimal(byte[] resumen) {
        StringBuilder hex = new StringBuilder();
        for (byte b : resumen) {
            // Se convierte cada byte a su representación hexadecimal
            String h = Integer.toHexString(0xFF & b);
            // Asegura que cada valor hexadecimal tenga 2 caracteres
            if (h.length() == 1) hex.append('0');
            hex.append(h);
        }
        // Se retorna el resultado en formato hexadecimal
        return hex.toString();
    }
}
