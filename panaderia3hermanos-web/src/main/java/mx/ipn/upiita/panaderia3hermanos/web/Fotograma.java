
package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("fotograma")
@SessionScoped
public class Fotograma implements Serializable {

    private int indice = 0;
    private final String[] imagenes = {
            "pag.png", "pag1.png", "pag2.png", "pag3.png",
    };

    public String getImagenActual() {
        return "/resources/img/" + imagenes[indice];
    }

    public void siguiente() {
        indice = (indice + 1) % imagenes.length;
    }

    public void anterior() {
        indice = (indice - 1 + imagenes.length) % imagenes.length;
    }

    public String verDetalle() {
        switch (imagenes[indice]) {
            case "pag.png":
                return "conocenos?faces-redirect=true";
            case "pag1.png":
                return "flujoejemplo";
            case "pag2.png":
                return "mensajesforma?faces-redirect=true";
            case "pag3.png":
                return "iniciocatalogo?faces-redirect=true";
            default:
                return "error?faces-redirect=true";
        }
    }

    public String verPedido() {
        return "index?faces-redirect=true";
    }
}
