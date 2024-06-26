package co.taller2.grupo12.grupo12.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private String contrasena;
    private String tipo;
}