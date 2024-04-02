package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.el.stream.Stream;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
        return StreamSupport.stream(arrendadores.spliterator(), false)
            .map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class))
            .collect(Collectors.toList());
    }

    @PostMapping
    public Arrendador guardarArrendador(@RequestBody Arrendador arrendador) {
        return arrendadorRepository.save(arrendador);
    }

    @GetMapping("/{id}")
    public Optional<Arrendador> getById(@PathVariable Long id) {
        return arrendadorRepository.findById(id);
    }

    @PostMapping("/eliminar")
    public void eliminarArrendador(@RequestBody Arrendador arrendador) {
        arrendadorRepository.delete(arrendador);
    }
}