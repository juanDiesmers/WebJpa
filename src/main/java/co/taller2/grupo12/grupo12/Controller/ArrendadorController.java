package co.taller2.grupo12.grupo12.Controller;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;

import co.taller2.grupo12.grupo12.services.ArrendadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/arrendadores")
public class ArrendadorController {

    private final ArrendadorService arrendadorService;

    public ArrendadorController(ArrendadorService arrendadorService) {
        this.arrendadorService = arrendadorService;
    }

    @GetMapping
    public ResponseEntity<List<ArrendadorDTO>> getAllArrendadores() {
        List<ArrendadorDTO> arrendadores = arrendadorService.getArrendadores();
        return ResponseEntity.ok(arrendadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArrendadorDTO> getArrendadorById(@PathVariable Long id) {
        ArrendadorDTO arrendador = arrendadorService.getArrendadorById(id);
        if (arrendador != null) {
            return ResponseEntity.ok(arrendador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ArrendadorDTO> createArrendador(@RequestBody ArrendadorDTO arrendadorDTO) {
        ArrendadorDTO createdArrendador = arrendadorService.createArrendador(arrendadorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdArrendador);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArrendador(@PathVariable Long id) {
        arrendadorService.deleteArrendador(id);
        return ResponseEntity.noContent().build();
    }

}

