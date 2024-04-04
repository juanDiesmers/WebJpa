package co.taller2.grupo12.grupo12;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendatarioRepository;
import co.taller2.grupo12.grupo12.Controller.ArrendatarioController;
import co.taller2.grupo12.grupo12.Controller.FincaController;
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.DTOS.FincaDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.entity.Finca;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;
import co.taller2.grupo12.grupo12.services.FincaService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)

class FincaControllerTest {

    @Mock
    FincaService fincaService;

    @InjectMocks
    FincaController fincaController;

    @Test
    void testGetFincaById() {
        // Arrange
        Long id = 1L;
        FincaDTO expectedFincaDTO = new FincaDTO();
        expectedFincaDTO.setId_finca(id);
        // Mockear el comportamiento del servicio
        when(fincaService.getFincaById(id)).thenReturn(expectedFincaDTO);

        // Act
        FincaDTO result = fincaController.getFincaById(id);

        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_finca());
        // Verificar que el método del servicio fue invocado correctamente
        verify(fincaService).getFincaById(id);
    }

    @Test
    void testGetAllFincas() {
        // Arrange
        List<FincaDTO> expectedFincas = new ArrayList<>();
        // Añadir algunas fincas simuladas al resultado esperado
        expectedFincas.add(new FincaDTO());
        expectedFincas.add(new FincaDTO());
        // Mockear el comportamiento del servicio
        when(fincaService.getAllFincas()).thenReturn(expectedFincas);

        // Act
        ResponseEntity<List<FincaDTO>> response = fincaController.getAllFincas();
        List<FincaDTO> result = response.getBody();

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedFincas.size(), result.size());
        // Verificar que el método del servicio fue invocado correctamente
        verify(fincaService).getAllFincas();
    }

    @Test
    void testGuardarFinca() {
        // Arrange
        FincaDTO fincaDTO = new FincaDTO();
        fincaDTO.setId_finca(1L);
        // Mockear el comportamiento del servicio
        when(fincaService.createFinca(any(FincaDTO.class))).thenReturn(fincaDTO);

        // Act
        FincaDTO result = fincaController.guardarFinca(fincaDTO);

        // Assert
        assertNotNull(result);
        assertEquals(fincaDTO.getId_finca(), result.getId_finca());
        // Verificar que el método del servicio fue invocado correctamente con el DTO de finca correcto
        verify(fincaService).createFinca(eq(fincaDTO));
    }

    @Test
    void testActualizarFinca() {
        // Arrange
        Long id = 1L;
        FincaDTO fincaDTO = new FincaDTO();
        fincaDTO.setId_finca(id);
        // Mockear el comportamiento del servicio
        when(fincaService.updateFinca(eq(id), any(FincaDTO.class))).thenReturn(fincaDTO);
    
        // Act
        FincaDTO result = fincaController.actualizarFinca(id, fincaDTO);
    
        // Assert
        assertNotNull(result);
        assertEquals(id, result.getId_finca());
        // Verificar que el método del servicio fue invocado correctamente con el ID y DTO de finca correctos
        verify(fincaService).updateFinca(eq(id), eq(fincaDTO));
    }

    @Test
    void testEliminarFinca() {
        // Arrange
        Long id = 1L;
    
        // Act
        fincaController.eliminarFinca(id);
    
        // Assert
        // Verificar que el método del servicio fue invocado correctamente con el ID de la finca a eliminar
        verify(fincaService).deleteFinca(eq(id));
    }
}