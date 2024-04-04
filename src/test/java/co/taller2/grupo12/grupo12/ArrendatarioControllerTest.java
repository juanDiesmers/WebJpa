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
import co.taller2.grupo12.grupo12.DTOS.ArrendatarioDTO;
import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendatarioService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
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

class ArrendatarioControllerTest {

    @Mock
    ArrendatarioService arrendatarioService;

    @InjectMocks
    ArrendatarioController arrendatarioController;

    @Test
    void testObtenerArrendatarioPorId() {
        // Configurar objeto mock
        Arrendatario arrendatarioMock = new Arrendatario();
        // Configurar el comportamiento esperado del método obtenerArrendatarioPorId() del servicio
        when(arrendatarioService.obtenerArrendatarioPorId(Mockito.anyLong())).thenReturn(Optional.of(arrendatarioMock));
        // Llamar al método del controlador
        ResponseEntity<Arrendatario> response = arrendatarioController.obtenerArrendatarioPorId(1L);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testObtenerTodosLosArrendatarios() {
        // Configurar objeto mock para el servicio
        List<Arrendatario> arrendatariosMock = new ArrayList<>();
        arrendatariosMock.add(new Arrendatario());
        arrendatariosMock.add(new Arrendatario());
        // Configurar el comportamiento esperado del método obtenerTodosLosArrendatarios() del servicio
        when(arrendatarioService.obtenerTodosLosArrendatarios()).thenReturn(arrendatariosMock);
        // Llamar al método del controlador
        List<Arrendatario> resultado = arrendatarioController.obtenerTodosLosArrendatarios();
        // Verificar el resultadoo
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Verificar que se devuelvan todos los arrendatarios esperados
    }
}