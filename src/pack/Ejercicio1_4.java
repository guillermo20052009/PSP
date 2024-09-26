package pack;



import java.io.IOException;

import java.util.Arrays;

public class Ejercicio1_4 {

    public static void main(String[] args) throws IOException {
        if (args.length <= 0){
            System.err.println("Se necesita un programa a ejecutar");
            System.exit(-1);
        } else {
            System.out.println(Arrays.toString(args));
            System.exit(1);
        }
    }

}
