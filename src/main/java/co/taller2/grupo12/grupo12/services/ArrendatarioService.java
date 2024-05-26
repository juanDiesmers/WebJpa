package co.taller2.grupo12.grupo12.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import java.util.List;
import java.util.Optional;

@Service
public class ArrendatarioService {

    ModelMapper modelMapper;

    @Autowired
    private ArrendatarioRepository arrendatarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private String message = "El arrendatario con ID: ";
    private String noExiste = " no existe";
    private String noPuedeSEEEERRR = " no existe y por lo tanto no puede ser eliminado";
    private String errorContrasena = "La contraseña debe tener al menos 8 caracteres.";

    public Arrendatario crearArrendatario(ArrendatarioDTO arrendatarioDTO) {
        if (arrendatarioDTO.getContrasena() == null) {
            throw new IllegalArgumentException("La contraseña no puede ser nula.");
        }

        Arrendatario arrendatario = new Arrendatario();
        arrendatario.setNombre(arrendatarioDTO.getNombre());
        arrendatario.setApellido(arrendatarioDTO.getApellido());
        arrendatario.setCorreo(arrendatarioDTO.getCorreo());
        String encodedPassword = passwordEncoder.encode(arrendatarioDTO.getContrasena());
        arrendatario.setContrasena(encodedPassword);

        return arrendatarioRepository.save(arrendatario);
    }

    public List<Arrendatario> obtenerTodosLosArrendatarios() {
        return (List<Arrendatario>) arrendatarioRepository.findAll();
    }

    public Optional<Arrendatario> obtenerArrendatarioPorId(Long id) {
        return arrendatarioRepository.findById(id);
    }

    public Arrendatario actualizarArrendatario(Long id, ArrendatarioDTO arrendatarioDTO) {
        Optional<Arrendatario> arrendatarioOptional = arrendatarioRepository.findById(id);
        if (arrendatarioOptional.isPresent()) {
            Arrendatario arrendatario = arrendatarioOptional.get();
            arrendatario.setNombre(arrendatarioDTO.getNombre());
            arrendatario.setApellido(arrendatarioDTO.getApellido());
            arrendatario.setCorreo(arrendatarioDTO.getCorreo());
            String encodedPassword = passwordEncoder.encode(arrendatarioDTO.getContrasena());
            arrendatarioDTO.setContrasena(encodedPassword);
            // arrendatario.setContrasena(arrendatarioDTO.getContrasena());
            return arrendatarioRepository.save(arrendatario);
        }
        return null;
    }

    public void eliminarArrendatarioPorId(Long id) {
        arrendatarioRepository.deleteById(id);
    }

    // UPDATE ARRENDATARIO BY AUTHENTICATED USER
    public ArrendatarioDTO updateForAuthenticatedUser(ArrendatarioDTO arrendatario_dto) {
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

            if (!arrendatarioRepository.existsById(authenticatedUserId)) {
                throw new IllegalArgumentException(message + authenticatedUserId + noExiste);
            }

            if (arrendatario_dto.getContrasena().length() < 8) {
                throw new IllegalArgumentException(errorContrasena);
            }
            String contrasena = passwordEncoder.encode(arrendatario_dto.getContrasena());
            arrendatario_dto.setContrasena(contrasena);

            arrendatario_dto.setId_arrendatario(authenticatedUserId);
            Arrendatario arrendatario = modelMapper.map(arrendatario_dto, Arrendatario.class);
            arrendatario = arrendatarioRepository.save(arrendatario);
            return modelMapper.map(arrendatario, ArrendatarioDTO.class);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }

    // DELETE ARRENDATARIO BY AUTHENTICATED USER
    public void deleteForAuthenticatedUser() {
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

            if (!arrendatarioRepository.existsById(authenticatedUserId)) {
                throw new IllegalArgumentException(message + authenticatedUserId + noPuedeSEEEERRR);
            }
            arrendatarioRepository.deleteById(authenticatedUserId);
        } else {
            throw new IllegalStateException("Unexpected principal type: " + principal.getClass().getName());
        }
    }
}
