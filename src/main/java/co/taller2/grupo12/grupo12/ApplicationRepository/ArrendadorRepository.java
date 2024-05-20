package co.taller2.grupo12.grupo12.ApplicationRepository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.taller2.grupo12.grupo12.entity.Arrendador;

public interface ArrendadorRepository extends CrudRepository<Arrendador, Long> {

    Optional<Arrendador> findByCorreo(String correo);

}
