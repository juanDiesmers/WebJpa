package co.taller2.grupo12.grupo12.Controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.taller2.grupo12.grupo12.DTOS.UsuarioDTO;
import co.taller2.grupo12.grupo12.DTOS.token_dto;
import co.taller2.grupo12.grupo12.services.JWTTokenService;
import co.taller2.grupo12.grupo12.services.UsuarioService;

@RestController
@RequestMapping("/jwt/security/autenticar")
public class AutenticacionController {

    @Autowired
    private JWTTokenService jwtTokenService;

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin
    @PostMapping(value = "/autenticar-correo-contrasena", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> autenticar(@RequestBody UsuarioDTO credentials) {
        String correo = credentials.getCorreo();
        String contrasena = credentials.getContrasena();

        if (correo != null && contrasena != null) {
            // Perform authentication using correo and contrasena
            Optional<UsuarioDTO> usuarioDTO = usuarioService.autenticar(correo, contrasena);

            if (usuarioDTO.isPresent()) {
                System.out.println("User authentication successful");
                // User authentication successful, generate JWT token
                String token = jwtTokenService.generarToken(usuarioDTO.get());
                token_dto tokenDTO = new token_dto(token, usuarioDTO.get(), usuarioDTO.get().getTipo(),
                        usuarioDTO.get().getNombres(), usuarioDTO.get().getId());
                return ResponseEntity.ok(tokenDTO);
            }
        }

        // User authentication failed
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }
}
