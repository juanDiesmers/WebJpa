package co.taller2.grupo12.grupo12.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/arrendatarios")
public class ArrendatarioController {

    @Autowired
    private ArrendatarioService arrendatarioService;

    @PostMapping("/crearArrendatario")
    public ResponseEntity<Arrendatario> crearArrendatario(@RequestBody ArrendatarioDTO arrendatarioDTO) {
        if (arrendatarioDTO.getContrasena() == null || arrendatarioDTO.getContrasena().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        Arrendatario createdArrendatario = arrendatarioService.crearArrendatario(arrendatarioDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArrendatario);
    }

    @GetMapping
    public List<Arrendatario> obtenerTodosLosArrendatarios() {
        return arrendatarioService.obtenerTodosLosArrendatarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arrendatario> obtenerArrendatarioPorId(@PathVariable Long id) {
        Optional<Arrendatario> arrendatario = arrendatarioService.obtenerArrendatarioPorId(id);
        return arrendatario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arrendatario> actualizarArrendatario(@PathVariable Long id,
            @RequestBody ArrendatarioDTO arrendatarioDTO) {
        Arrendatario arrendatario = arrendatarioService.actualizarArrendatario(id, arrendatarioDTO);
        return arrendatario != null ? ResponseEntity.ok(arrendatario) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArrendatario(@PathVariable Long id) {
        arrendatarioService.eliminarArrendatarioPorId(id);
        return ResponseEntity.noContent().build();
    }
}
