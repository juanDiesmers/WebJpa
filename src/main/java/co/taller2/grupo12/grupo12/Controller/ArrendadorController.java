package co.taller2.grupo12.grupo12.Controller;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ComentarioDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.DTOS.UsuarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.services.ArrendadorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;

@RestController
@CrossOrigin(origins = "*")
// "http://localhost:4200"
@RequestMapping("/arrendadores")

public class ArrendadorController {

    private final ArrendadorService arrendadorService;

    public ArrendadorController(ArrendadorService arrendadorService) {
        this.arrendadorService = arrendadorService;
    }

    /*
     * @GetMapping
     * public ResponseEntity<List<ArrendadorDTO>> getAllArrendadores() {
     * List<ArrendadorDTO> arrendadores = arrendadorService.getArrendadores();
     * return ResponseEntity.ok(arrendadores);
     * }
     */
    @GetMapping
    public List<ArrendadorDTO> obtenerTodoslosArrendadores() {
        return arrendadorService.getArrendadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arrendador> obtenerArrendadorPorId(@PathVariable Long id) {
        Optional<Arrendador> arrendador = arrendadorService.obtenerArrendadorPorId(id);
        return arrendador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arrendador> actualizarArrendador(@PathVariable Long id,
            @RequestBody ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = arrendadorService.actualizarArrendador(id, arrendadorDTO);
        return arrendador != null ? ResponseEntity.ok(arrendador) : ResponseEntity.notFound().build();
    }

    @PostMapping("/arrendadores")
    public ResponseEntity<ArrendadorDTO> createArrendador(@RequestBody ArrendadorDTO arrendadorDTO) {
        ArrendadorDTO createdArrendador = arrendadorService.createArrendador(arrendadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArrendador);
    }

    @PostMapping("/crearArrendador")
    public ResponseEntity<Arrendador> crearArrendador(@RequestBody ArrendadorDTO arrendadorDTO) {
        System.out.println("Datos recibidos: " + arrendadorDTO); // Verificar los datos aquí
        if (arrendadorDTO.getContrasena() == null || arrendadorDTO.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        Arrendador createdArrendador = arrendadorService.crearArrendadorSIN(arrendadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArrendador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendador(@PathVariable Long id) {
        arrendadorService.deleteArrendador(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/misFincas")
    public ResponseEntity<List<FincaDTO>> obtenerFincasPorArrendador(Authentication authentication) {
        Long idArrendador = ((UsuarioDTO) authentication.getPrincipal()).getId();
        List<FincaDTO> fincas = arrendadorService.getFincasPorArrendador(idArrendador);
        return ResponseEntity.ok(fincas);
    }

    @GetMapping("/misComentarios")
    public ResponseEntity<List<ComentarioDTO>> obtenerComentariosPorArrendador(Authentication authentication) {
        Long idArrendador = ((UsuarioDTO) authentication.getPrincipal()).getId();
        List<ComentarioDTO> comentarios = arrendadorService.getComentariosPorArrendador(idArrendador);
        return ResponseEntity.ok(comentarios);
    }

}
