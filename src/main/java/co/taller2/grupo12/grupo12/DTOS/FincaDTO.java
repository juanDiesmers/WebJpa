package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Solicitud;
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

public class FincaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_finca;

    private String nombre;
    private double precio;

    @ManyToOne
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador", unique=false, nullable=false)
    private Arrendador arrendador;

    @OneToMany(mappedBy = "finca")
    private List<Solicitud> solicitudes = new ArrayList<Solicitud>();
}
