package co.taller2.grupo12.grupo12.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

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
@Where(clause = "status = 0")
@SQLDelete(sql = "UPDATE application SET  status = 1 WHERE id=?")

public class Finca {
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