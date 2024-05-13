package co.taller2.grupo12.grupo12.entity;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.sql.Date;
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


public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_pago;

    private Date fecha;
    private String banco;
    private String numeroCuenta;

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    @OneToOne
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud")
    private Solicitud solicitud;
}
