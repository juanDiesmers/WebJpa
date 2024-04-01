package co.taller2.grupo12.grupo12.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.entity.Arrendador;

@RestController
@RequestMapping("/arrendadores")
@Service
public class ArrendadorService {
    @Autowired
    ArrendadorRepository arrendadorRepository;

    @GetMapping
    public ArrayList<Arrendador> getArrendadores(){
        return (ArrayList<Arrendador>) arrendadorRepository.findAll();
    }
    
    @PostMapping
    public Arrendador guardarArrendador(@RequestBody Arrendador arrendador){
        return arrendadorRepository.save(arrendador);
    }

    @GetMapping("/{id}")
    public Optional<Arrendador> getById(@RequestParam Long id){
        return arrendadorRepository.findById(id);
    }
}
