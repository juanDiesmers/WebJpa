package co.taller2.grupo12.grupo12.Controller;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;



@RestController
@RequestMapping("/arrendadores")
public class ArrendadorController {
    
    @Autowired
    private final ArrendadorService arrendadorService;

    public ArrendadorController(ArrendadorService arrendadorService) {
        this.arrendadorService = arrendadorService;
    }
    /*  
    @GetMapping
    public ResponseEntity<List<ArrendadorDTO>> getAllArrendadores() {
        List<ArrendadorDTO> arrendadores = arrendadorService.getArrendadores();
        return ResponseEntity.ok(arrendadores);
    }
    */
    @GetMapping
    public List<Arrendador> obtenerTodoslosArrendadores() {
        return arrendadorService.obtenerTodosLosArrendadores();
    }

    /* 
    @GetMapping("/{id}")
    public ResponseEntity<ArrendadorDTO> getArrendadorById(@PathVariable Long id) {
        ArrendadorDTO arrendador = arrendadorService.getArrendadorById(id);
        if (arrendador != null) {
            return ResponseEntity.ok(arrendador);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    */

    @GetMapping("/{id}")
    public ResponseEntity<Arrendador> obtenerArrendadorPorId(@PathVariable Long id) {
        Optional<Arrendador> arrendador = arrendadorService.obtenerArrendadorPorId(id);
        return arrendador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Arrendador> actualizarArrendador(@PathVariable Long id, @RequestBody ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = arrendadorService.actualizarArrendador(id, arrendadorDTO);
        return arrendador != null ? ResponseEntity.ok(arrendador) : ResponseEntity.notFound().build();
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

