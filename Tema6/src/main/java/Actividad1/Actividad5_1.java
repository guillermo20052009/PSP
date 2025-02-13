package Actividad1;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class Actividad5_1 {
    public static void main(String[] args) {
        MessageDigest md;
        try {
            md=MessageDigest.getInstance("MD5");
            String texto="Este texto es de guillermo antes de codificar";
            byte dataBytes[] = texto.getBytes();
            md.update(dataBytes);
            byte[] resumen = md.digest();


            System.out.println("Mensaje de texto es: "+texto);
            System.out.println("Numero de bytes: "+md.getDigestLength());
            System.out.println("Algoritmo: "+md.getAlgorithm());
            System.out.println("Resumen: "+new String(resumen));
            System.out.println("Mensaje en hexadecimal: "+Hexadecimal(resumen));

            Provider proveedor = md.getProvider();
            System.out.println("Proveedor: "+proveedor.toString());


        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String Hexadecimal(byte[] resumen) {
        String hex = "";
        for (int i = 0; i < resumen.length; i++) {
            String h = Integer.toHexString(resumen[i] & 0xFF);
            if (h.length() == 1) {
                hex += '0';
            }
            hex += h;
        }
        return hex;
    }
}
