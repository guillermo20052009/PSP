package Actividad2_6;

public class main2 {
    static void iniciarHilos(Thread h){
        h.start();
    }
    static Ejemplo2 crearHilos(String nombre, boolean ex){
        return new Ejemplo2(nombre, ex);
    }
    public static void main(String[] args) {
        // Creación de los hilos principales con impresión
        Ejemplo2[] hilosPrincipales = new Ejemplo2[5];
        for (int i = 0; i < hilosPrincipales.length; i++) {
            hilosPrincipales[i] = crearHilos("hilo"+(i),true);
        }

        // Array para almacenar hilos adicionales sin impresión
        Ejemplo2[] hilosAdicionales = new Ejemplo2[2000];
        for (int i = 0; i < hilosAdicionales.length; i++) {
            hilosAdicionales[i] = crearHilos("hilo"+(i+6),false);
        }
        // Establecer las prioridades de los hilos principales
        hilosPrincipales[0].setPriority(Thread.MIN_PRIORITY);
        hilosPrincipales[1].setPriority(Thread.MIN_PRIORITY);
        hilosPrincipales[2].setPriority(Thread.MIN_PRIORITY);
        hilosPrincipales[3].setPriority(Thread.MIN_PRIORITY);
        hilosPrincipales[4].setPriority(Thread.MIN_PRIORITY);

        for (Ejemplo2 hilo : hilosPrincipales) {
            hilo.start();
        }

        // Iniciar los hilos adicionales sin impresión
        for (Ejemplo2 hilo : hilosAdicionales) {
            hilo.start();
        }
    }
}
