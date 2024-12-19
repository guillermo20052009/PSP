package Ejercicio3;

public class Curso {
    // Atributos privados de la clase Curso
    private String id;
    private String descripcion;

    // Constructor para inicializar los atributos
    public Curso(String id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    // Getters y Setters para acceder y modificar los atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Método toString para representar la información del curso en una cadena
    @Override
    public String toString() {
        return "Curso{" +
                "id='" + id + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
