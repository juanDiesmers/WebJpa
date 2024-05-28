package co.taller2.grupo12.grupo12.Controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.taller2.grupo12.grupo12.DTOS.PagoDTO;
import co.taller2.grupo12.grupo12.services.PagoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pagos")
public class PagoController {

    private final PagoService pagoService;

    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    @GetMapping
    public ResponseEntity<List<PagoDTO>> getAllPagos() {
        List<PagoDTO> pagos = pagoService.getAllPagos();
        return ResponseEntity.ok(pagos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> getPagoById(@PathVariable Long id) {
        PagoDTO pago = pagoService.getPagoById(id);
        if (pago != null) {
            return ResponseEntity.ok(pago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PreAuthorize("hasRole('ROLE_ARRENDATARIO')")
    @PostMapping
    public ResponseEntity<PagoDTO> createPago(@RequestBody PagoDTO pagoDTO, org.springframework.security.core.Authentication authentication) {
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
        PagoDTO createdPago = pagoService.createPago(pagoDTO, correoArrendatario);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPago);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> updatePago(@PathVariable Long id, @RequestBody PagoDTO pagoDTO) {
        PagoDTO updatedPago = pagoService.updatePago(id, pagoDTO);
        if (updatedPago != null) {
            return ResponseEntity.ok(updatedPago);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable Long id) {
        pagoService.deletePago(id);
        return ResponseEntity.noContent().build();
    }
}
