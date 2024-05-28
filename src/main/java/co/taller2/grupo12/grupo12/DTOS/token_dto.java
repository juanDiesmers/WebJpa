package co.taller2.grupo12.grupo12.DTOS;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class token_dto {

    private String token;
    private UsuarioDTO usuario;
    private String accType;
    private String nombres;
    private long id;

    public String getType() {
        return "Bearer ";
    }
}
