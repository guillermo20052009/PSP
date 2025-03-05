package Actividad6;

import java.security.Principal;
import java.io.Serializable;

public class EjemploPrincipal implements Principal, Serializable {
    private String name;

    // Constructor que recibe el nombre del usuario autenticado
    public EjemploPrincipal(String name) {
        if (name == null) {
            throw new NullPointerException("Entrada nula.");
        }
        this.name = name;
    }

    // Método que devuelve el nombre del usuario autenticado
    public String getName() {
        return name;
    }

    // Método para comparar si dos objetos Principal son iguales
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (!(obj instanceof EjemploPrincipal)) return false;
        EjemploPrincipal other = (EjemploPrincipal) obj;
        return name.equals(other.getName());
    }

    // Método hashCode basado en el nombre del usuario
    public int hashCode() {
        return name.hashCode();
    }

    // Representación en cadena del Principal (usuario autenticado)
    public String toString() {
        return name;
    }
}
