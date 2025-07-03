package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

@Named(value="mensajesController")
@RequestScoped
public class MensajesController {
    private String email;
    private String password;
    @Inject
    private FacesContext facesContext;

    public String enviar(){
        try{
            if (password.equals("123456")){
                return "/basic/salida/salida1";
            }else{
                throw new Exception("Error en el password");
                //return "/basic/salida/salida2";
            }
        } catch (Exception e){
            FacesMessage fm= new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),null);
            facesContext.addMessage("formEtiquetaMensajes:enviarBtn",fm);
            return null;
        }
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
