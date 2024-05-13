package co.taller2.grupo12.grupo12.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.FetchType;

import java.util.ArrayList;
import java.util.List;

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

public class Finca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_finca;
    private String nombre;
    private double precio;
    private String descripcion;
    private String municipio;
    private boolean activa;

    @ManyToOne(fetch = FetchType.LAZY) // Un arrendador puede tener muchas fincas, pero una finca pertenece a un solo arrendador
    @JoinColumn(name = "id_arrendador", referencedColumnName = "id_arrendador")
    private Arrendador arrendador;

    @OneToMany(mappedBy = "finca")
    private List<Solicitud> solicitudes = new ArrayList<>();

    @Override
    public String toString() {
        return "Finca{" +
                "id_finca=" + id_finca +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", arrendador=" + (arrendador != null ? arrendador.getId_arrendador() : "null") +
                '}';
    }
    public Finca(Long id_finca, String nombre, double precio, String descripcion, String municipio, boolean activa, Arrendador arrendador) {
        this.id_finca = id_finca;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.municipio = municipio;
        this.activa = activa;
        this.arrendador = arrendador;
    }
}