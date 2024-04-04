package co.taller2.grupo12.grupo12.Controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;

import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.services.ArrendadorService;



import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/arrendador")
public class ArrendadorController {
    @Autowired
    private ArrendadorService arrendadorService;


    @PostMapping
    public Arrendador crearArrendador(@RequestBody ArrendadorDTO arrendatarioDTO) {
        return arrendadorService.crearArrendador(arrendatarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Arrendador> obtenerArrendadorPorId(@PathVariable Long id) {
        Optional<Arrendador> arrendador = arrendadorService.obtenerArrendadorPorId(id);
        return arrendador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
