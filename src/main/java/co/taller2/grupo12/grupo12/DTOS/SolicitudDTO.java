package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

public class SolicitudDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_solicitud;

    @ManyToOne
    @JoinColumn(name = "id_arrendatario", referencedColumnName = "id_arrendatario", unique=false, nullable=false)
    private Arrendatario arrendatario;

    @ManyToOne
    @JoinColumn(name = "id_finca", referencedColumnName = "id_finca", unique=false, nullable=false)
    private FincaDTO finca;

    @OneToMany(mappedBy = "solicitud")
    private List<PagoDTO> pagos = new ArrayList<PagoDTO>();

    private Date fecha;
    private String estado;
}
