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
        productos.add(new Producto("Pan Francés", "Crujiente y recién horneado", 1.50, "pan-frances.jpg"));
        productos.add(new Producto("Croissant", "Relleno de chocolate o jamón y queso", 2.00, "croissant.jpg"));
        productos.add(new Producto("Pastel de Frutas", "Ideal para celebraciones", 12.00, "pastel-frutas.jpg"));
        productos.add(new Producto("Dona de chocolate", "...", 21.50, "dona-chocolate.jpg"));
        productos.add(new Producto("Supremo de chocolate", "...", 360, "supremochocolate.jpg"));
        productos.add(new Producto("Cuernito", "...", 11.50, "cuernito.jpg"));}

    public List<Producto> getProductos() {
        return productos;
    }
}
