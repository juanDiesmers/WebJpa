package co.taller2.grupo12.grupo12.Controller;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.DTOS.SolicitudDTO;
import co.taller2.grupo12.grupo12.services.SolicitudService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> getAllSolicitudes() {
        List<SolicitudDTO> solicitudes = solicitudService.getAllSolicitudes();
        return ResponseEntity.ok(solicitudes);
    }


    @GetMapping("/arrendatario")
    public ResponseEntity<List<SolicitudDTO>> getAllSolicitudesArrendatario(org.springframework.security.core.Authentication authentication) {
        String correoArrendatario = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                // Parsear el JSON para obtener el correo
                String jsonString = (String) principal;
                JSONObject json = new JSONObject(jsonString);
                correoArrendatario = json.getString("correo");
            } else {
                System.out.println("Authentication principal is not an instance of String");
            }
        } else {
            System.out.println("Authentication object is null");
        }
        List<SolicitudDTO> solicitudes = solicitudService.getAllSolicitudArrendatario(correoArrendatario);
        return ResponseEntity.ok(solicitudes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> getSolicitudById(@PathVariable Long id) {
        SolicitudDTO solicitud = solicitudService.getSolicitudById(id);
        if (solicitud != null) {
            return ResponseEntity.ok(solicitud);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> createSolicitud(@RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO createdSolicitud = solicitudService.createSolicitud(solicitudDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSolicitud);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudDTO> updateSolicitud(@PathVariable Long id, @RequestBody SolicitudDTO solicitudDTO) {
        SolicitudDTO updatedSolicitud = solicitudService.updateSolicitud(id, solicitudDTO);
        if (updatedSolicitud != null) {
            return ResponseEntity.ok(updatedSolicitud);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSolicitud(@PathVariable Long id) {
        solicitudService.deleteSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}