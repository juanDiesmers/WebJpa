package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PagoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pago;

    private Date fecha;
    private double valor;

    @ManyToOne
    @JoinColumn(name = "id_arrendatario", referencedColumnName = "id_arrendatario", unique=false, nullable=false)
    private Arrendatario arrendatario;

    @ManyToOne
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", unique=false, nullable=false)
    private Solicitud solicitud;
}
