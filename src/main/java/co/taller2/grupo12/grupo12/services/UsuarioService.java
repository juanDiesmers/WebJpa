package co.taller2.grupo12.grupo12.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.DTOS.UsuarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;

@Service
public class UsuarioService {

    @Autowired
    private ArrendadorRepository arrendadorRepository;

    @Autowired
    private ArrendatarioRepository arrendatarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private ObjectMapper objectMapper = new ObjectMapper();

    public Optional<UsuarioDTO> autenticar(String correo, String contrasena) {
        Optional<UsuarioDTO> usuarioDTO = Optional.empty();
        System.out.println("Autenticando usuario con correo: " + correo);
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findByCorreo(correo);
        if (arrendadorOptional.isPresent()) {
            System.out.println("Arrendador encontrado");
            Arrendador arrendador = arrendadorOptional.get();
            if (passwordEncoder.matches(contrasena, arrendador.getContrasena())) {
                System.out.println("Contrasena correcta");
                usuarioDTO = Optional.of(new UsuarioDTO(arrendador.getId_arrendador(), arrendador.getNombre(),
                        arrendador.getApellido(), arrendador.getCorreo(), arrendador.getContrasena(), "Arrendador"));
            }
        }

        if (!usuarioDTO.isPresent()) {
            Optional<Arrendatario> arrendatarioOptional = arrendatarioRepository.findByCorreo(correo);
            if (arrendatarioOptional.isPresent()) {
                System.out.println("Arrendatario encontrado");
                Arrendatario arrendatario = arrendatarioOptional.get();
                if (passwordEncoder.matches(contrasena, arrendatario.getContrasena())) {
                    System.out.println("Contrasena correcta");
                    usuarioDTO = Optional.of(new UsuarioDTO(arrendatario.getId_arrendatario(),
                            arrendatario.getNombre(), arrendatario.getApellido(), arrendatario.getCorreo(),
                            arrendatario.getContrasena(), "Arrendatario"));
                }
            }
        }

        return usuarioDTO;
    }

    public UsuarioDTO autorizacion(Authentication authentication) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("-----------------------");
        System.out.println(authentication.getName());
        UsuarioDTO usuario = objectMapper.readValue(authentication.getName(), UsuarioDTO.class);
        System.out.println("-----------------------");
        return usuario;
    }
}
