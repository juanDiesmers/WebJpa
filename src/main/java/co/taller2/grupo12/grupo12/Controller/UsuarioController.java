package co.taller2.grupo12.grupo12.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.taller2.grupo12.grupo12.DTOS.UsuarioDTO;
import co.taller2.grupo12.grupo12.services.UsuarioService;

@RestController
@RequestMapping(value = "/jwt/security/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    // @CrossOrigin
    // @GetMapping( produces = MediaType.APPLICATION_JSON_VALUE )
    // public UsuarioDTO autenticar( @RequestHeader String Authorization ) throws
    // Exception{
    // System.out.println( Authorization );
    // return null;
    // }

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO autenticar(Authentication authentication) throws Exception {
        System.out.println(authentication);
        return usuarioService.autorizacion(authentication);
    }

    @CrossOrigin
    @GetMapping(value = "/error", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO error(Authentication authentication) throws Exception {
        System.out.println(authentication);
        return usuarioService.autorizacion(authentication);
    }
}