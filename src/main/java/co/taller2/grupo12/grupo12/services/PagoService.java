package co.taller2.grupo12.grupo12.services;

import java.util.List;

import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.ApplicationRepository.PagoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;

    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<PagoDTO> getAllPagos() {
        Iterable<Pago> pagos = pagoRepository.findAll();
        return StreamSupport.stream(pagos.spliterator(), false)
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PagoDTO getPagoById(Long id) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        return pagoOptional.map(this::convertToDTO).orElse(null);
    }

    public PagoDTO createPago(PagoDTO pagoDTO) {
        Pago pago = convertToEntity(pagoDTO);
        return convertToDTO(pagoRepository.save(pago));
    }

    public PagoDTO updatePago(Long id, PagoDTO pagoDTO) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()) {
            Pago existingPago = pagoOptional.get();
            existingPago.setFecha(pagoDTO.getFecha());
            existingPago.setValor(pagoDTO.getValor());
            // Set other attributes if needed
            return convertToDTO(pagoRepository.save(existingPago));
        } else {
            return null;
        }
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertToDTO(Pago pago) {
        PagoDTO pagoDTO = new PagoDTO();
        pagoDTO.setId_pago(pago.getId_pago());
        pagoDTO.setFecha(pago.getFecha());
        pagoDTO.setValor(pago.getValor());
        // Set other attributes if needed
        return pagoDTO;
    }

    private Pago convertToEntity(PagoDTO pagoDTO) {
        Pago pago = new Pago();
        pago.setId_pago(pagoDTO.getId_pago());
        pago.setFecha(pagoDTO.getFecha());
        pago.setValor(pagoDTO.getValor());
        // Set other attributes if needed
        return pago;
    }
}
