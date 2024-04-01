package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;


import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


public class ComentarioDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    private Date fecha;
    private double calificacion;
    private String comentario;

    
    @ManyToOne
    @JoinColumn(name = "id_arrendatario", referencedColumnName = "id_arrendatario", unique=false, nullable=false)
    private Arrendatario arrendatario;

    @ManyToOne
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador", unique=false, nullable=false)
    private Arrendador arrendador;    
}
