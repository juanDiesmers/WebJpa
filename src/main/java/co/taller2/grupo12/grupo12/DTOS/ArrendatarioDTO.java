package co.taller2.grupo12.grupo12.DTOS;

import java.util.ArrayList;
import java.util.List;

import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArrendatarioDTO {
    private Long id_arrendatario;

    @NotEmpty(message = "Nombre es obligatorio")
    private String nombre;

    @NotEmpty(message = "Apellido es obligatorio")
    private String apellido;

    @NotEmpty(message = "Correo es obligatorio")
    private String correo;

    @NotEmpty(message = "Teléfono es obligatorio")
    private String telefono;

    @NotEmpty(message = "Contraseña es obligatoria")
    private String contrasena;

    private List<Solicitud> solicitudes = new ArrayList<>();
    private List<Comentario> comentarios = new ArrayList<>();
}
