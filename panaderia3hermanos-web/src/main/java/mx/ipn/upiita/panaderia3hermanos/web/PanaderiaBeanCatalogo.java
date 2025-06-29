package mx.ipn.upiita.panaderia3hermanos.web;
import mx.ipn.upiita.panaderia3hermanos.web.Producto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class PanaderiaBeanCatalogo {
    private List<Producto> productos;

    public PanaderiaBeanCatalogo() {
        productos = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            productos.add(new Producto(
                    "Producto " + i,
                    "Delicioso producto de panadería artesanal número " + i,
                    1.00 + i,
                    "resources/images/producto" + i + ".jpg"
            ));
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
