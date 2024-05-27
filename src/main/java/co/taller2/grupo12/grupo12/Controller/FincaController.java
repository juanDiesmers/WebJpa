package co.taller2.grupo12.grupo12.Controller;

import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.services.FincaService;

import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "Authorization")
@RequestMapping("/fincas")
public class FincaController {

    private final FincaService fincaService;

    public FincaController(FincaService fincaService) {
        this.fincaService = fincaService;
    }

    @GetMapping
    public ResponseEntity<List<FincaDTO>> getAllFincas() {
        List<FincaDTO> fincas = fincaService.getAllFincas();
        return ResponseEntity.ok(fincas);
    }

    @PreAuthorize("hasRole('ROLE_ARRENDADOR')")
    @PostMapping
    public ResponseEntity<FincaDTO> guardarFinca(@RequestBody FincaDTO fincaDTO, org.springframework.security.core.Authentication authentication) {
        String correoArrendador = null;
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof String) {
                // Parsear el JSON para obtener el correo
                String jsonString = (String) principal;
                JSONObject json = new JSONObject(jsonString);
                correoArrendador = json.getString("correo");
            } else {
                System.out.println("Authentication principal is not an instance of String");
            }
        } else {
            System.out.println("Authentication object is null");
        }

        System.out.println("El correo del arrendador: " + correoArrendador);
        fincaDTO = fincaService.createFinca(fincaDTO, correoArrendador);
        return ResponseEntity.ok(fincaDTO);
        //return fincaService.createFinca(fincaDTO);
    }

    @GetMapping("/{id}")
    public FincaDTO getFincaById(@PathVariable Long id) {
        return fincaService.getFincaById(id);
    }

    @PutMapping("/{id}")
    public FincaDTO actualizarFinca(@PathVariable Long id, @RequestBody FincaDTO fincaDTO) {
        return fincaService.updateFinca(id, fincaDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminarFinca(@PathVariable Long id) {
        fincaService.deleteFinca(id);
    }

}