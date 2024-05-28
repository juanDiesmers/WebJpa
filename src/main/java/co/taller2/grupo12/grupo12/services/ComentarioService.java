package co.taller2.grupo12.grupo12.services;

import java.util.List;

import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.entity.Comentario;
import co.taller2.grupo12.grupo12.entity.Estado;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.ApplicationRepository.ComentarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.SolicitudRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ComentarioService {

    private final ComentarioRepository comentarioRepository;
    private final ArrendatarioRepository arrendatarioRepository;
    private final ArrendadorRepository arrendadorRepository;
    private final SolicitudRepository solicitudRepository;
    private final ModelMapper modelMapper;

    public ComentarioService(ComentarioRepository comentarioRepository, ArrendatarioRepository arrendatarioRepository, ArrendadorRepository arrendadorRepository, SolicitudRepository solicitudRepository, ModelMapper modelMapper) {
        this.comentarioRepository = comentarioRepository;
        this.arrendatarioRepository = arrendatarioRepository;
        this.arrendadorRepository = arrendadorRepository;
        this.solicitudRepository = solicitudRepository;
        this.modelMapper = modelMapper;
    }

    public List<ComentarioDTO> getAllComentarios() {
        Iterable<Comentario> comentarios = comentarioRepository.findAll();
        return StreamSupport.stream(comentarios.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ComentarioDTO getComentarioById(Long id) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        return comentarioOptional.map(this::convertToDTO).orElse(null);
    }

    public ComentarioDTO createComentario(ComentarioDTO comentarioDTO, String correoArrendatario, Long idSolicitud) {
        if ((correoArrendatario) == null) {
            throw new IllegalArgumentException("El ID del arrendatario no puede ser nulo.");
        } else {
            Comentario comentario = convertToEntity(comentarioDTO, idSolicitud);

            Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró ninguna solicitud con el ID proporcionado."));

            comentario.setSolicitud(solicitud);

            //Sacar arrendador y arrendatario a partir de la solicitud

            Arrendador arrendador = solicitud.getFinca().getArrendador();
            Arrendatario arrendatario = solicitud.getArrendatario();

            comentario.setArrendador(arrendador);
            comentario.setArrendatario(arrendatario);
            System.out.println("Datos de la entidad Comentario antes del guardado:");
            System.out.println(comentario);
            Comentario savedComentario = comentarioRepository.save(comentario);
            System.out.println("Datos de la entidad Finca después del guardado:");
            System.out.println(savedComentario);

            return convertToDTO(savedComentario);
        }
    }

    public ComentarioDTO updateComentario(Long id, ComentarioDTO comentarioDTO) {
        Optional<Comentario> comentarioOptional = comentarioRepository.findById(id);
        if (comentarioOptional.isPresent()) {
            Comentario existingComentario = comentarioOptional.get();
            existingComentario.setFecha(comentarioDTO.getFecha());
            existingComentario.setCalificacion(comentarioDTO.getCalificacion());
            existingComentario.setComentario(comentarioDTO.getComentario());
            return convertToDTO(comentarioRepository.save(existingComentario));
        } else {
            return null;
        }
    }

    public void deleteComentario(Long id) {
        comentarioRepository.deleteById(id);
    }

    private ComentarioDTO convertToDTO(Comentario comentario) {
        ComentarioDTO comentarioDTO = modelMapper.map(comentario, ComentarioDTO.class);
        return comentarioDTO;
    }

    private Comentario convertToEntity(ComentarioDTO comentarioDTO, Long idSolicitud) {

        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
        .orElseThrow(() -> new IllegalArgumentException("No se encontró ninguna solicitud con el ID proporcionado."));
        Comentario comentario = modelMapper.map(comentarioDTO, Comentario.class);
        
        Arrendador arrendador = solicitud.getFinca().getArrendador();
        Arrendatario arrendatario = solicitud.getArrendatario();
        
        // Asignar los arrendadores y arrendatarios al comentario
        comentario.setArrendador(arrendador);
        comentario.setArrendatario(arrendatario);
        comentario.setSolicitud(solicitud);
        
        return comentario;
    }
}