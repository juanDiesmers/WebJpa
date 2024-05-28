package co.taller2.grupo12.grupo12.services;

import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.ApplicationRepository.FincaRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class FincaService {
    
    @Autowired
    private  FincaRepository fincaRepository;
   
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private ArrendadorRepository arrendadorRepository;

    public FincaService(FincaRepository fincaRepository, ArrendadorRepository arrendadorRepository, ModelMapper modelMapper) {
        this.fincaRepository = fincaRepository;
        this.arrendadorRepository = arrendadorRepository;
        this.modelMapper = modelMapper;
    }

    public List<FincaDTO> getAllFincas() {
        System.out.println("PRUEBAAAAAAAAA:");
        Iterable<Finca> fincas = fincaRepository.findAll();
        return StreamSupport.stream(fincas.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FincaDTO> getAllFincasArrendador(String correoArrendador) {
        System.out.println("PRUEBAAAAAAAAA:" + correoArrendador);
                if ((correoArrendador) == null) {
                    throw new IllegalArgumentException("El ID del arrendador no puede ser nulo.");
                } else {
                    Arrendador arrendador = arrendadorRepository.findByCorreo(correoArrendador)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró ningún arrendador con el ID proporcionado."));

                    System.out.println("PRUEBAAAAAAAAA:" + arrendador.getId_arrendador());

                    Iterable<Finca> fincas = fincaRepository.findByArrendadorId(arrendador.getId_arrendador());
                    return StreamSupport.stream(fincas.spliterator(), false)
                            .map(this::convertToDTO)
                            .collect(Collectors.toList());
                }
    }



    public FincaDTO getFincaById(Long id) {
        Optional<Finca> fincaOptional = fincaRepository.findById(id);
        if (fincaOptional.isPresent()) {
            return convertToDTO(fincaOptional.get());
        } else {
            return null;
        }
    }


    public FincaDTO createFinca(FincaDTO fincaDTO, String correoArrendador) {
        if ((correoArrendador) == null) {
            throw new IllegalArgumentException("El ID del arrendador no puede ser nulo.");
        } else {
            Finca finca = convertToEntity(fincaDTO);

            Arrendador arrendador = arrendadorRepository.findByCorreo(correoArrendador)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró ningún arrendador con el ID proporcionado."));
            finca.setArrendador(arrendador);
            System.out.println("Datos de la entidad Finca antes del guardado:");
            System.out.println(finca);
            Finca savedFinca = fincaRepository.save(finca);
            System.out.println("Datos de la entidad Finca después del guardado:");
            System.out.println(savedFinca);
            return convertToDTO(savedFinca);
        }
    }

    public FincaDTO updateFinca(Long id, FincaDTO fincaDTO) {
        Optional<Finca> fincaOptional = fincaRepository.findById(id);
        if (fincaOptional.isPresent()) {
            Finca existingFinca = fincaOptional.get();
            existingFinca.setNombre(fincaDTO.getNombre());
            existingFinca.setPrecio(fincaDTO.getPrecio());
            existingFinca.setDescripcion(fincaDTO.getDescripcion());
            existingFinca.setMunicipio(fincaDTO.getMunicipio());
            existingFinca.setActiva(fincaDTO.isActiva());
            Finca updatedFinca = fincaRepository.save(existingFinca);
            return convertToDTO(updatedFinca);
        } else {
            return null;
        }
    }

    public void deleteFinca(Long id) {
        fincaRepository.deleteById(id);
    }

    private FincaDTO convertToDTO(Finca finca) {
        FincaDTO fincaDTO = modelMapper.map(finca, FincaDTO.class);
        // Verificar si el arrendador no es nulo y establecer su ID en el DTO
        if (finca.getArrendador() != null) {
            fincaDTO.setId_arrendador(finca.getArrendador().getId_arrendador());
        }
        
        return fincaDTO;
    }
    

    private Finca convertToEntity(FincaDTO fincaDTO) {
        Finca finca = modelMapper.map(fincaDTO, Finca.class);
        return finca;
    }

}