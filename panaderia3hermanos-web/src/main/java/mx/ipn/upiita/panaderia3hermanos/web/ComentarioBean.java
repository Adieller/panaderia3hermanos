package mx.ipn.upiita.panaderia3hermanos.web;

import jakarta.annotation.Resource;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Named("comentarioBean")
@RequestScoped
public class ComentarioBean {

    @Resource(lookup = "java:/ingwebDS")
    private DataSource ds;

    public List<Comentario> getComentarios() {
        List<Comentario> comentarios = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT c.ComentID, c.ComentTexto, c.ComentFecha, cl.ClieNombre " +
                             "FROM Comentario c " +
                             "JOIN Cliente cl ON c.ComentClieID = cl.ClieID " +
                             "ORDER BY c.ComentFecha DESC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Comentario coment = new Comentario();
                coment.setComentId(rs.getInt("ComentID"));
                coment.setComentTexto(rs.getString("ComentTexto"));
                coment.setComentFecha(rs.getTimestamp("ComentFecha").toLocalDateTime());
                coment.setClieNombre(rs.getString("ClieNombre"));
                comentarios.add(coment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo adecuado del error
        }
        return comentarios;
    }
}
