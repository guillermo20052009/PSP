package Actividad2_9;

import java.util.ArrayList;

public class Cola {
    private int numero;
    private boolean vacio=false;

    public int get(){
        if (vacio){
            vacio=false;
            return numero;
        }
        return -1;
    }
    public void put(int valor){
        numero=valor;
        vacio=true;
    }
}
