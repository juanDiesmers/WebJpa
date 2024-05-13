package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class FincaDTO {
    private Long id_finca;
    private String nombre;
    private double precio;
    private String descripcion;
    private String municipio;
    private boolean activa;
    private Long id_arrendador;
    private List<SolicitudDTO> solicitudes = new ArrayList<>();
}