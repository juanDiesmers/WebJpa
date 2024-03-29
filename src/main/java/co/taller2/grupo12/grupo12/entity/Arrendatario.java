package co.taller2.grupo12.grupo12.entity;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE application SET  status = 1 WHERE id=?")

public class Arrendatario {
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