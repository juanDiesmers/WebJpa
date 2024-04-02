package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/arrendadores")
@Service
public class ArrendadorService {
    @Autowired
    ArrendadorRepository arrendadorRepository;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    public Iterable<ArrendadorDTO> getArrendadores() {
        Iterable<Arrendador> arrendadores = arrendadorRepository.findAll();
        List<ArrendadorDTO> arrendadoresDTO = new ArrayList<>();
        for (Arrendador arrendador : arrendadores) {
            ArrendadorDTO arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
            List<FincaDTO> fincasDTO = arrendador.getFincas().stream()
                .map(finca -> modelMapper.map(finca, FincaDTO.class))
                .collect(Collectors.toList());
            arrendadorDTO.setFincas(fincasDTO);
            arrendadoresDTO.add(arrendadorDTO);
        }
        return arrendadoresDTO;
    }
    


    @PostMapping
    public Arrendador guardarArrendador(@RequestBody Arrendador arrendador) {
        return arrendadorRepository.save(arrendador);
    }

    @GetMapping("/{id}")
public ResponseEntity<ArrendadorDTO> getById(@PathVariable Long id) {
    Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
    
    if (arrendadorOptional.isPresent()) {
        Arrendador arrendador = arrendadorOptional.get();
        ArrendadorDTO arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
        return ResponseEntity.ok(arrendadorDTO);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @PostMapping("/eliminar")
    public void eliminarArrendador(@RequestBody Arrendador arrendador) {
        arrendadorRepository.delete(arrendador);
    }
}
