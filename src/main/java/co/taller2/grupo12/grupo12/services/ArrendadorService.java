package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ArrendadorService {
    
    private final ArrendadorRepository arrendadorRepository;
    private final ModelMapper modelMapper;
    
    public ArrendadorService(ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<ArrendadorDTO> getArrendadores() {
        Iterable<Arrendador> arrendadores = arrendadorRepository.findAll();
        return StreamSupport.stream(arrendadores.spliterator(), false)
            .map(arrendador -> {
                ArrendadorDTO arrendadorDTO = modelMapper.map(arrendador, ArrendadorDTO.class);
                List<FincaDTO> fincasDTO = arrendador.getFincas().stream()
                    .map(finca -> {
                        // Mapeo de la finca  y su DTO
                        FincaDTO fincaDTO = modelMapper.map(finca, FincaDTO.class);
                        return fincaDTO;
                    })
                    .collect(Collectors.toList());
                arrendadorDTO.setFincas(fincasDTO);
                return arrendadorDTO;
            })
            .collect(Collectors.toList());
    }

    public ArrendadorDTO getArrendadorById(Long id) {
        Optional<Arrendador> arrendadorOptional = arrendadorRepository.findById(id);
        return arrendadorOptional.map(arrendador -> modelMapper.map(arrendador, ArrendadorDTO.class)).orElse(null);
    }

    public ArrendadorDTO createArrendador(ArrendadorDTO arrendadorDTO) {
        Arrendador arrendador = modelMapper.map(arrendadorDTO, Arrendador.class);
        Arrendador savedArrendador = arrendadorRepository.save(arrendador);
        return modelMapper.map(savedArrendador, ArrendadorDTO.class);
    }

    public void deleteArrendador(Long id) {
        arrendadorRepository.deleteById(id);
    }
}