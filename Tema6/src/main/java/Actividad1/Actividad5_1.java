package Actividad1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Actividad5_1 {
    public static void main(String[] args) {
        MessageDigest md;
        try {
            // Se obtiene una instancia del algoritmo MD5
            md = MessageDigest.getInstance("MD5");

            // Texto que se va a codificar
            String texto = "Este texto es de guillermo antes de codificar";

            // Se convierte el texto en un arreglo de bytes
            byte dataBytes[] = texto.getBytes();

            // Se actualiza el digest con los bytes del texto
            md.update(dataBytes);

            // Se obtiene el resumen (hash) del texto
            byte[] resumen = md.digest();

            // Se imprime el mensaje original
            System.out.println("Mensaje de texto es: " + texto);
            // Se imprime el número de bytes del resumen
            System.out.println("Numero de bytes: " + md.getDigestLength());
            // Se imprime el algoritmo utilizado (MD5)
            System.out.println("Algoritmo: " + md.getAlgorithm());
            // Se imprime el resumen como un texto
            System.out.println("Resumen: " + new String(resumen));
            // Se imprime el resumen en formato hexadecimal
            System.out.println("Mensaje en hexadecimal: " + Hexadecimal(resumen));

            // Se obtiene y se imprime el proveedor del algoritmo MD5
            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: " + proveedor.toString());

        } catch (NoSuchAlgorithmException e) {
            // En caso de que no exista el algoritmo MD5
            throw new RuntimeException(e);
        }
    }

    // Método para convertir el resumen (hash) en una cadena hexadecimal
    private static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            // Se convierte cada byte a su representación hexadecimal
            String h = Integer.toHexString(resumen[i] & 0xFF);
            // Se asegura que cada byte tenga dos caracteres hexadecimales
            if (h.length() == 1) {
                hex += '0';
            }
            hex += h;
        }
        return hex;
    }
}
