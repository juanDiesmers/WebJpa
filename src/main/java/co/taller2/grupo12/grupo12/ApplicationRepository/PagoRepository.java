package co.taller2.grupo12.grupo12.ApplicationRepository;

import org.springframework.data.repository.CrudRepository;

import co.taller2.grupo12.grupo12.entity.Solicitud;

public interface PagoRepository extends CrudRepository<Solicitud, Long>{

}