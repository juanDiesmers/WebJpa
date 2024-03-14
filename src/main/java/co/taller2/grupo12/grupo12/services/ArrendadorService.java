package co.taller2.grupo12.grupo12.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.entity.Arrendador;

@Service
public class ArrendadorService {
    @Autowired
    ArrendadorRepository arrendadorRepository;

    public ArrayList<Arrendador> getArrendador(){
        return (ArrayList<Arrendador>) arrendadorRepository.findAll();
    }
    
    public Arrendador guardarArrendador(Arrendador arrendador){
        return arrendadorRepository.save(arrendador);
    }
}
