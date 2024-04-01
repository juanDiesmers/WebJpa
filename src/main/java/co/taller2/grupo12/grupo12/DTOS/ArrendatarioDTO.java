package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ArrendatarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arrendatario;

    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;

    @OneToMany(mappedBy = "arrendatario")
    private List<Solicitud> solicitudes = new ArrayList<Solicitud>();

    @OneToMany(mappedBy = "arrendatario")
    private List<Pago> pagos = new ArrayList<Pago>();

    @OneToMany(mappedBy = "arrendatario")
    private List<Comentario> comentarios = new ArrayList<Comentario>();
}
