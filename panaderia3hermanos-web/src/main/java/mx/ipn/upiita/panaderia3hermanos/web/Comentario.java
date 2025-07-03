package mx.ipn.upiita.panaderia3hermanos.web;

import java.time.LocalDateTime;

public class Comentario {
    private int comentId;
    private String comentTexto;
    private LocalDateTime comentFecha;
    private String clieNombre;

    // Constructor
    public Comentario() {}

    // Getters y Setters
    public int getComentId() {
        return comentId;
    }

    public void setComentId(int comentId) {
        this.comentId = comentId;
    }

    public String getComentTexto() {
        return comentTexto;
    }

    public void setComentTexto(String comentTexto) {
        this.comentTexto = comentTexto;
    }

    public LocalDateTime getComentFecha() {
        return comentFecha;
    }

    public void setComentFecha(LocalDateTime comentFecha) {
        this.comentFecha = comentFecha;
    }

    public String getClieNombre() {
        return clieNombre;
    }

    public void setClieNombre(String clieNombre) {
        this.clieNombre = clieNombre;
    }
}