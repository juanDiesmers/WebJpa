package co.taller2.grupo12.grupo12.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;


import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/arrendador")
public class ArrendadorController {
    @Autowired
    private ArrendadorService arrendadorService;

    @GetMapping("/{id}")
    public ResponseEntity<Arrendador> obtenerArrendadorPorId(@PathVariable Long id) {
        Optional<Arrendador> arrendador = arrendadorService.obtenerArrendadorPorId(id);
        return arrendador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
