package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArrendadorService {

    private final ArrendadorRepository arrendadorRepository;
    private final ModelMapper modelMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String message = "El Arrendador con ID: ";
    private String noExiste = " no existe";
    private String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";
    private String errorContrasena = "La contraseña debe tener al menos 8 caracteres.";

    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ArrendadorDTO> getArrendadores() {
        Iterable<Arrendador> arrendadores = arrendadorRepository.findAll();
        return StreamSupport.stream(arrendadores.spliterator(), false)
                .map(arrendador -> {
                    ArrendadorDTO arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
                    List<String> nombresFincas = arrendador.getFincas().stream()
                            .map(Finca::getNombre)
                            .collect(Collectors.toList());
                    arrendadorDTO.setNombresFincas(nombresFincas);
                    return arrendadorDTO;
                })
                .collect(Collectors.toList());
    }

    public Optional<Arrendador> obtenerArrendadorPorId(Long id) {
        return arrendadorRepository.findById(id);
    }

    public Arrendador actualizarArrendador(Long id, ArrendadorDTO arrendadorDTO) {
        Optional<Arrendador> arrendadorOp = arrendadorRepository.findById(id);
        if (arrendadorOp.isPresent()) {
            Arrendador arrendador = arrendadorOp.get();
            arrendador.setNombre(arrendadorDTO.getNombre());
            arrendador.setApellido(arrendadorDTO.getApellido());
            arrendador.setCorreo(arrendadorDTO.getCorreo());
            String encodedPassword = passwordEncoder.encode(arrendadorDTO.getContrasena());
            arrendadorDTO.setContrasena(encodedPassword);
            // arrendador.setContrasena(arrendadorDTO.getContrasena());
            return arrendadorRepository.save(arrendador);
        }
        return null;
    }

    public ArrendadorDTO getArrendadorById(Long id) {
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
        return arrendadorOptional.map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class)).orElse(null);
    }

    public ArrendadorDTO createArrendador(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = modelMapper.map(arrendadorDTO, Arrendador.class);
        Arrendador savedArrendador = arrendadorRepository.save(arrendador);
        return modelMapper.map(savedArrendador, ArrendadorDTO.class);
    }

    public Arrendador crearArrendadorSIN(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setApellido(arrendadorDTO.getApellido());
        arrendador.setCorreo(arrendadorDTO.getCorreo());

        // Codificar la contraseña desde el DTO y luego establecerla en el objeto
        // Arrendador
        if (arrendadorDTO.getContrasena() != null && !arrendadorDTO.getContrasena().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(arrendadorDTO.getContrasena());
            arrendador.setContrasena(encodedPassword);
        } else {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        return arrendadorRepository.save(arrendador);
    }

    public List<Arrendador> obtenerTodosLosArrendadores() {
        return (List<Arrendador>) arrendadorRepository.findAll();
    }

    public Arrendador guardarArrendador(ArrendadorDTO arrendadorDTO) {
        // Crear una instancia de Arrendador a partir de los datos del DTO
        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendadorDTO.getNombre());
        arrendador.setApellido(arrendadorDTO.getApellido());

        // Guardar el arrendador utilizando el repositorio
        return arrendadorRepository.save(arrendador);
    }

    public void deleteArrendador(Long id) {
        arrendadorRepository.deleteById(id);
    }

    // UPDATE ARRENDADOR FOR AUTHENTICATED USER
    public ArrendadorDTO updateForAuthenticatedUser(ArrendadorDTO arrendador_dto) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof String) {
            String jsonString = (String) principal;
            Long authenticatedUserId;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(jsonString);
                authenticatedUserId = jsonNode.get("id").asLong();
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse JSON string: " + jsonString, e);
            }

            // Fetch the authenticated user
            Arrendador existingArrendador = arrendadorRepository.findById(authenticatedUserId)
                    .orElseThrow(() -> new IllegalArgumentException(message + authenticatedUserId + noExiste));

            // ERROR HANDLING IF PASSWORD IS TOO SHORT
            if (arrendador_dto.getContrasena().length() < 8) {
                throw new IllegalArgumentException(errorContrasena);
            }

            // Update the existing arrendador with new details
            existingArrendador.setNombre(arrendador_dto.getNombre());
            existingArrendador.setApellido(arrendador_dto.getApellido());
            existingArrendador.setCorreo(arrendador_dto.getCorreo());
            existingArrendador.setTelefono(arrendador_dto.getTelefono());
            existingArrendador.setContrasena(passwordEncoder.encode(arrendador_dto.getContrasena()));

            // Save the updated arrendador
            Arrendador updatedArrendador = arrendadorRepository.save(existingArrendador);
            return modelMapper.map(updatedArrendador, ArrendadorDTO.class);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }

}
