package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Finca;
import jakarta.persistence.Column;
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


public class ArrendadorDTO {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_arrendador;

    @Column
    private String nombre;
    
    @Column
    private String apellido;

    @Column
    private String correo;

    @Column
    private String telefono;

    @Column
    private String contrasena;

    @OneToMany(mappedBy = "arrendador")
    private List<Finca> fincas = new ArrayList<Finca>();

    @OneToMany(mappedBy = "arrendador")
    private List<Comentario> comentarios = new ArrayList<Comentario>();
}
