package Actividad1;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Scanner;

public class Actividad5_1_claves {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Leer las cadenas y la clave
        System.out.println("Introduce la primera cadena:");
        String cadena1 = scanner.nextLine();

        System.out.println("Introduce la segunda cadena:");
        String cadena2 = scanner.nextLine();

        System.out.println("Introduce la clave (16 caracteres):");
        String clave = scanner.nextLine();

        if (clave.length() != 16) {
            System.out.println("La clave debe tener exactamente 16 caracteres.");
            return;
        }

        // Convertir la clave en una clave secreta de AES
        SecretKeySpec secretKey = new SecretKeySpec(clave.getBytes(), "AES");

        // Cifrar ambas cadenas con AES
        String cifrado1 = cifrarAES(cadena1, secretKey);
        String cifrado2 = cifrarAES(cadena2, secretKey);

        // Generar los hashes SHA-256 de los textos cifrados
        String hash1 = generarSHA256(cifrado1);
        String hash2 = generarSHA256(cifrado2);

        // Mostrar resultados
        System.out.println("\nTexto cifrado 1: " + cifrado1);
        System.out.println("Texto cifrado 2: " + cifrado2);
        System.out.println("\n Hash SHA-256 de la primera cadena cifrada: " + hash1);
        System.out.println(" Hash SHA-256 de la segunda cadena cifrada: " + hash2);

        // Comparar los hashes
        if (hash1.equals(hash2)) {
            System.out.println("\n Los hashes son iguales.");
        } else {
            System.out.println("\n Los hashes son diferentes.");
        }

        // Descifrar los textos
        System.out.println("\nDescifrado de la primera cadena: " + descifrarAES(cifrado1, secretKey));
        System.out.println("Descifrado de la segunda cadena: " + descifrarAES(cifrado2, secretKey));

        scanner.close();
    }

    // Método para cifrar con AES
    private static String cifrarAES(String texto, SecretKeySpec clave) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, clave);
            byte[] cifrado = cipher.doFinal(texto.getBytes());
            return Base64.getEncoder().encodeToString(cifrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al cifrar con AES", e);
        }
    }

    // Método para descifrar con AES
    private static String descifrarAES(String textoCifrado, SecretKeySpec clave) {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, clave);
            byte[] decifrado = cipher.doFinal(Base64.getDecoder().decode(textoCifrado));
            return new String(decifrado);
        } catch (Exception e) {
            throw new RuntimeException("Error al descifrar con AES", e);
        }
    }

    // Método para generar hash SHA-256
    private static String generarSHA256(String texto) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(texto.getBytes());
            return convertirHexadecimal(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al generar SHA-256", e);
        }
    }

    // Método para convertir bytes en hexadecimal
    private static String convertirHexadecimal(byte[] resumen) {
        StringBuilder hex = new StringBuilder();
        for (byte b : resumen) {
            String h = Integer.toHexString(0xFF & b);
            if (h.length() == 1) hex.append('0'); // Asegura que tenga 2 caracteres
            hex.append(h);
        }
        return hex.toString();
    }
}
