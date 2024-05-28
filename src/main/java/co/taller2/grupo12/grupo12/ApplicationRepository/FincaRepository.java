package co.taller2.grupo12.grupo12.ApplicationRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Finca;

@Repository

public interface FincaRepository extends CrudRepository<Finca, Long> {

    static List<Finca> findByIdArrendador(Long idArrendador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdArrendador'");
    }
    @Query("SELECT f FROM Finca f WHERE f.arrendador.id_arrendador = :arrendadorId")
    List<Finca> findByArrendadorId(Long arrendadorId);
}