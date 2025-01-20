package Ejercicio3;

public class Alumno {
    // Atributo: ID único del alumno
    private String idAlumno;

    // Atributo: Nombre del alumno
    private String nombre;

    // Atributo: Instancia de la clase Curso a la que pertenece el alumno
    private Curso curso;

    // Atributo: Nota del alumno en el curso
    private int nota;

    // Constructor que inicializa los atributos del alumno
    public Alumno(String idAlumno, String nombre, Curso curso, int nota) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.curso = curso;
        this.nota = nota;
    }

    // Método para obtener el ID del alumno
    public String getIdAlumno() {
        return idAlumno;
    }

    // Método para establecer el ID del alumno
    public void setIdAlumno(String idAlumno) {
        this.idAlumno = idAlumno;
    }

    // Método para obtener el nombre del alumno
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del alumno
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener el curso al que pertenece el alumno
    public Curso getCurso() {
        return curso;
    }

    // Método para establecer el curso del alumno
    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    // Método para obtener la nota del alumno
    public int getNota() {
        return nota;
    }

    // Método para establecer la nota del alumno
    public void setNota(int nota) {
        this.nota = nota;
    }

    // Método que retorna una representación en cadena del alumno
    @Override
    public String toString() {
        return "Alumno{" +
                "idAlumno='" + idAlumno + '\'' +
                ", nombre='" + nombre + '\'' +
                ", curso=" + curso +
                ", nota=" + nota +
                '}';
    }
}
