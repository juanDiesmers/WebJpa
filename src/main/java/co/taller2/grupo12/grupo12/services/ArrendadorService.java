package co.taller2.grupo12.grupo12.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;


import java.util.Optional;



@Service
public class ArrendadorService {
    @Autowired
    private ArrendadorRepository arrendadorRepository;

   public Arrendador crearArrendador(ArrendadorDTO arrendatarioDTO) {
        Arrendador arrendador = new Arrendador();
        arrendador.setNombre(arrendatarioDTO.getNombre());
        arrendador.setApellido(arrendatarioDTO.getApellido());
        arrendador.setCorreo(arrendatarioDTO.getCorreo());
        arrendador.setContrasena(arrendatarioDTO.getContrasena());
        return arrendadorRepository.save(arrendador);
    }


    
    public Optional<Arrendador> obtenerArrendadorPorId(Long id) {
        return arrendadorRepository.findById(id);
    }
        
}
