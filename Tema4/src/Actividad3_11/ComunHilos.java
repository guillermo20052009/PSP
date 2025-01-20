package Actividad3_11;

import java.net.Socket;

public class ComunHilos {
    int conexiones;
    int actuales;
    int maximo;
    Socket tabla[];
    String mensajes;

    public ComunHilos(Socket[] tabla, int maximo, int actuales, int conexiones) {
        this.tabla = tabla;
        this.maximo = maximo;
        this.actuales = actuales;
        this.conexiones = conexiones;
        mensajes = "";
    }

    public ComunHilos() {
        super();
    }

    public int getConexiones() {
        return conexiones;
    }

    public synchronized void setConexiones(int conexiones) {
        this.conexiones = conexiones;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public String getMensajes() {
        return mensajes;
    }

    public synchronized void setMensajes(String mensajes) {
        this.mensajes = mensajes;
    }

    public int getActuales() {
        return actuales;
    }

    public synchronized void setActuales(int actuales) {
        this.actuales = actuales;
    }

    public synchronized void addTabla(Socket s, int i) {
        tabla[i] = s;
    }

    public Socket getTabla(int i) {
        return tabla[i];
    }
}
