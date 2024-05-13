package co.taller2.grupo12.grupo12.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Arrendatario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_arrendatario")
    private Long id_arrendatario;

    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;

    @OneToMany(mappedBy = "arrendatario", cascade = CascadeType.ALL)
    private List<Solicitud> solicitudes = new ArrayList<>();
    
    @OneToMany(mappedBy = "arrendatario")
    private List<Comentario> comentarios = new ArrayList<Comentario>();
}