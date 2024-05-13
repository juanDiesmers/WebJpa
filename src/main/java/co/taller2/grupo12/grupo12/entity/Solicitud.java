package co.taller2.grupo12.grupo12.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    @ManyToOne
    @JoinColumn(name = "id_arrendatario", referencedColumnName = "id_arrendatario", unique=false, nullable=false)
    private Arrendatario arrendatario;

    @ManyToOne
    @JoinColumn(name = "id_finca", referencedColumnName = "id_finca", unique=false, nullable=false)
    private Finca finca;

    private Date fecha;
    private Estado estado;

    public Solicitud(Date fecha, Estado estado) {
        this.fecha = fecha;
        this.estado = estado;
    }
}
