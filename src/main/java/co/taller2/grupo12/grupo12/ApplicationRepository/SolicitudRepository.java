package co.taller2.grupo12.grupo12.ApplicationRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Solicitud;

public interface SolicitudRepository extends CrudRepository<Solicitud, Long> {

    static List<Solicitud> findByIdArrendatario(Long idArrendatario) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdArrendatario'");
    }

    Optional<Solicitud> findByArrendatario(Arrendatario arrendatario);

}
