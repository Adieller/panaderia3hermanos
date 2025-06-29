package mx.ipn.upiita.panaderia3hermanos.web;

import mx.ipn.upiita.panaderia3hermanos.web.Producto;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class PanaderiaBean {

    private List<Producto> productos;

    public PanaderiaBean() {
        productos = new ArrayList<>();
        productos.add(new Producto("Pan Francés", "Crujiente y recién horneado", 1.50, "resources/images/pan-frances.jpg"));
        productos.add(new Producto("Croissant", "Relleno de chocolate o jamón y queso", 2.00, "resources/images/croissant.jpg"));
        productos.add(new Producto("Pastel de Frutas", "Ideal para celebraciones", 12.00, "resources/images/pastel-frutas.jpg"));
        productos.add(new Producto("Dona de chocolate", "Masa tradicional de dona cubierta completamente con chocolate y granillo de chocolate semi amargo.", 21.50, "resources/images/dona-chocolate.jpg"));
        productos.add(new Producto("Supremo de chocolate", "Masa tradicional de dona cubierta completamente con chocolate y granillo de chocolate semi amargo.", 360, "resources/images/supremochocolate.jpg"));
        productos.add(new Producto("Cuernito", "Pan danés tipo hojaldrado con suave sabor a mantequilla. Elaborado con forma de cuerno y barnizado con huevo.", 11.50, "resources/images/cuernito.jpg"));
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
