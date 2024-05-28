package co.taller2.grupo12.grupo12.ApplicationRepository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.taller2.grupo12.grupo12.entity.Comentario;

public interface ComentarioRepository extends CrudRepository<Comentario, Long> {

    static List<Comentario> findByIdArrendador(Long idArrendador) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByIdArrendador'");
    }

}
