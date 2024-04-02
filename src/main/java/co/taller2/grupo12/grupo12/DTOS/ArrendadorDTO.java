package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Finca;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendadorDTO {
    private Long id_arrendador;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String contrasena;
    private List<Finca> fincas = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();
}
