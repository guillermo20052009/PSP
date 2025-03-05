package Actividad6;

import java.io.*;
import java.security.PrivilegedAction;

public class EjemploAccion implements PrivilegedAction<Object> {
    public Object run() {
        File f = new File("fichero.txt");

        if (f.exists()) {
            System.out.println("EL FICHERO EXISTE ...");
            try {
                FileReader fic = new FileReader(f);
                int i;
                System.out.println("Su contenido es: ");
                while ((i = fic.read()) != -1) {
                    System.out.print((char) i);
                }
                fic.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("EL FICHERO NO EXISTE, LO CREO ...");
            try {
                FileWriter fic = new FileWriter(f);
                fic.write("Esta es una línea de texto");
                fic.close();
                System.out.println("Fichero creado con datos...");
            } catch (IOException e) {
                System.out.println("ERROR => " + e.getMessage());
            }
        }
        return null;
    }
}
