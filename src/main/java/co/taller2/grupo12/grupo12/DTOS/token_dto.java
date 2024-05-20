package co.taller2.grupo12.grupo12.DTOS;

import java.util.Calendar;
import java.util.Date;

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

    public String getType() {
        return "Bearer ";
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    public String accType;
    public String nombres;
    public long id;
}