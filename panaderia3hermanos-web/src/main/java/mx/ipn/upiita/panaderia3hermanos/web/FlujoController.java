package mx.ipn.upiita.panaderia3hermanos.web;
import jakarta.faces.flow.FlowScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named(value = "flujoController")
@FlowScoped("flujoejemplo")
public class FlujoController implements Serializable {
    private static final Logger logger= Logger.getLogger(FlujoController.class.getName());
    private String campoTexto1;
    private String campoTexto2;
    private String campoTexto3;

    public void submit(){
        logger.log(Level.INFO, "Procesando el final del Flujo");
        logger.log(Level.INFO, "Text1:{0}",campoTexto1);
        logger.log(Level.INFO, "Text1:{0}",campoTexto2);
        logger.log(Level.INFO, "Text1:{0}",campoTexto3);
    }

    public void inicializaFlujo(){
        logger.log(Level.INFO,"Inicializando Flujo.");
    }
    public String getRetorno(){
        return "/flujoejemplo-return?faces-redirect=true";
    }
    public String getCampoTexto2() {
        return campoTexto2;
    }

    public void setCampoTexto2(String campoTexto2) {
        this.campoTexto2 = campoTexto2;
    }

    public String getCampoTexto1() {
        return campoTexto1;
    }

    public void setCampoTexto1(String campoTexto1) {
        this.campoTexto1 = campoTexto1;
    }

    public String getCampoTexto3() {
        return campoTexto3;
    }

    public void setCampoTexto3(String campoTexto3) {
        this.campoTexto3 = campoTexto3;
    }

}
