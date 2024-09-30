package pack;



import java.io.IOException;

import java.util.Arrays;

public class LeerNombre {

    public static void main(String[] args) throws IOException {
        // Si tiene argumentos los imprimiremos y el valor de salida sera 1, en caso contrario será -1
        if (args.length <= 0){
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        } else {
            System.out.println(args[0]);
            System.exit(1);
        }
    }
}
