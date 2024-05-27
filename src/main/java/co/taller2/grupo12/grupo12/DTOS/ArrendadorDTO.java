package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendadorDTO {
    private Long id_arrendador;

    @NotEmpty(message = "Nombre is mandatory")
    private String nombre;

    @NotEmpty(message = "Apellido is mandatory")
    private String apellido;

    @NotEmpty(message = "Correo is mandatory")
    private String correo;

    @NotEmpty(message = "Telefono is mandatory")
    private String telefono;

    @NotEmpty(message = "Contrasena is mandatory")
    private String contrasena;

    private List<String> nombresFincas;
    private List<ComentarioDTO> comentarios = new ArrayList<>();
}
