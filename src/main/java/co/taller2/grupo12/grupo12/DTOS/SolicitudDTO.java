package co.taller2.grupo12.grupo12.DTOS;

import java.sql.Date;

import co.taller2.grupo12.grupo12.entity.Estado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    private Long idSolicitud;
    private Date fecha;
    private Estado estado;
    private Long idArrendatario;
    private Long idFinca;
}