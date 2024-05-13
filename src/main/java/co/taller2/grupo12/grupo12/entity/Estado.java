package co.taller2.grupo12.grupo12.entity;

public enum Estado {
    POR_ACEPTAR(1),
    ACEPTADA(2),
    PAGADA(3),
    POR_CALIFICAR(4),
    RECHAZADA(5),
    FINALIZADA(6);

    private final int codigo;

    Estado(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
