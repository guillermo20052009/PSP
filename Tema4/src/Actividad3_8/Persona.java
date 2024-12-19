package Actividad3_8;

import java.io.Serializable;

/**
 * Clase Persona que implementa Serializable para ser enviada/recibida en objetos serializados.
 */
public class Persona implements Serializable {
    private String nombre; // Nombre de la persona
    private int edad;      // Edad de la persona

    // Constructor por defecto
    public Persona() {
        super();
    }

    // Constructor con parámetros
    public Persona(String nombre, int edad) {
        super();
        this.nombre = nombre;
        this.edad = edad;
    }

    // Getter y setter para el nombre
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter y setter para la edad
    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Sobreescribiendo el método toString para representar la persona en formato legible
    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
