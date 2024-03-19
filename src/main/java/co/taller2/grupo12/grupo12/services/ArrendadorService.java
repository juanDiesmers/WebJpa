package co.taller2.grupo12.grupo12.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.entity.Arrendador;

@Service
public class ArrendadorService {
    @Autowired
    ArrendadorRepository arrendadorRepository;

    public ArrayList<Arrendador> getArrendadores(){
        return (ArrayList<Arrendador>) arrendadorRepository.findAll();
    }
    
    public Arrendador guardarArrendador(Arrendador arrendador){
        return arrendadorRepository.save(arrendador);
    }

    public Optional<Arrendador> getById(Long id){
        return arrendadorRepository.findById(id);
    }
}
