package co.taller2.grupo12.grupo12.services;

import java.sql.Date;
import java.util.List;

import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Estado;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.entity.Pago;
import co.taller2.grupo12.grupo12.entity.Solicitud;
import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.FincaRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.PagoRepository;
import co.taller2.grupo12.grupo12.ApplicationRepository.SolicitudRepository;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PagoService {

    private final PagoRepository pagoRepository;
    private final SolicitudRepository solicitudRepository;
    private final ArrendatarioRepository arrendatarioRepository;
    private final FincaRepository fincaRepository;
    private final ModelMapper modelMapper;

    public PagoService(PagoRepository pagoRepository, ModelMapper modelMapper, SolicitudRepository solicitudRepository, ArrendatarioRepository arrendatarioRepository, FincaRepository fincaRepository) {
        this.pagoRepository = pagoRepository;
        this.solicitudRepository = solicitudRepository;
        this.modelMapper = modelMapper;
        this.arrendatarioRepository = arrendatarioRepository;
        this.fincaRepository = fincaRepository;
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


    public PagoDTO createPago(PagoDTO pagoDTO, String correoArrendatario, Long idSolicitud) {
        if ((correoArrendatario) == null) {
            throw new IllegalArgumentException("El ID del arrendatario no puede ser nulo.");
        } else {
            Pago pago = convertToEntity(pagoDTO);

            Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró ninguna solicitud con el ID proporcionado."));

            pago.setSolicitud(solicitud);
            System.out.println("Datos de la entidad Pago antes del guardado:");
            System.out.println(pago);
            Pago savedPago = pagoRepository.save(pago);
            System.out.println("Datos de la entidad Finca después del guardado:");
            System.out.println(savedPago);

            Finca finca = fincaRepository.findById(solicitud.getFinca().getId_finca())
                    .orElseThrow(() -> new IllegalArgumentException("No se encontró ninguna finca con el ID proporcionado."));
            finca.setActiva(false);
            solicitud.setEstado(Estado.FINALIZADA);
            fincaRepository.save(finca);
            solicitudRepository.save(solicitud);               
            return convertToDTO(savedPago);
        }
    }

    public PagoDTO updatePago(Long id, PagoDTO pagoDTO) {
        Optional<Pago> pagoOptional = pagoRepository.findById(id);
        if (pagoOptional.isPresent()) {
            Pago existingPago = pagoOptional.get();
            existingPago.setFecha((Date) pagoDTO.getFecha());
            existingPago.setBanco(pagoDTO.getBanco());
            existingPago.setNumeroCuenta(pagoDTO.getNumeroCuenta());
            return convertToDTO(pagoRepository.save(existingPago));
        } else {
            return null;
        }
    }

    public void deletePago(Long id) {
        pagoRepository.deleteById(id);
    }

    private PagoDTO convertToDTO(Pago pago) {
        PagoDTO pagoDTO = modelMapper.map(pago, PagoDTO.class);
        // Set IDs directly to DTO from entity
        pagoDTO.setId_solicitud(pago.getSolicitud().getId_solicitud());
        return pagoDTO;
    }

    private Pago convertToEntity(PagoDTO pagoDTO) {
        Pago pago = modelMapper.map(pagoDTO, Pago.class);
        return pago;
    }
}
