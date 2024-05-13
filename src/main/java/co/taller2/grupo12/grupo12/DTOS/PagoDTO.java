package co.taller2.grupo12.grupo12.DTOS;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {
    private long id_pago;
    private Date fecha;
    private String banco;
    private String numeroCuenta;
    private long id_solicitud; // changed to long from Arrendatario object
}
