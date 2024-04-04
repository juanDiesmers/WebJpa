package co.taller2.grupo12.grupo12.Controller;


import org.apache.tomcat.util.http.parser.MediaType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import co.taller2.grupo12.grupo12.Grupo12Application;
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.DTOS.ArrendadorDTO;
import co.taller2.grupo12.grupo12.entity.Arrendador;

import co.taller2.grupo12.grupo12.entity.Arrendatario;
import co.taller2.grupo12.grupo12.services.ArrendadorService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.MOCK,
    classes = Grupo12Application.class
) 
@AutoConfigureMockMvc
@RunWith(MockitoJUnitRunner.class)
class ArrendadorControllerTest {

@Mock
ArrendadorService arrendadorService;

    

@InjectMocks
ArrendadorController arrendadorController;

   

    @Test
    void testObtenerArrendadoroPorId() {
        // Configurar objeto mock
        Arrendador arrendadorMock = new Arrendador();
        // Configurar el comportamiento esperado del método obtenerArrendatarioPorId() del servicio
        when(arrendadorService.obtenerArrendadorPorId(Mockito.anyLong())).thenReturn(Optional.of(arrendadorMock));
        // Llamar al método del controlador
        ResponseEntity<Arrendador> response = arrendadorController.obtenerArrendadorPorId(1L);
        // Verificar el resultado
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }


    @Test
    void testObtenerTodosLosArrendadores() {
        // Configurar objeto mock para el servicio
        List<Arrendador> arrendadorMock = new ArrayList<>();
        arrendadorMock.add(new Arrendador());
        arrendadorMock.add(new Arrendador());
        // Configurar el comportamiento esperado del método obtenerTodosLosArrendatarios() del servicio
        when(arrendadorService.obtenerTodosLosArrendadores()).thenReturn(arrendadorMock);
        // Llamar al método del controlador
        List<ArrendadorDTO> resultado = arrendadorController.obtenerTodoslosArrendadores();
        // Verificar el resultadooo
        assertNotNull(resultado);
        assertEquals(2, resultado.size()); // Verificar que se devuelvan todos los arrendatarios esperados
    }
    
    @Test
    void testActualizarArrendatarioExistente() {
        // Datos de prueba
        Long id = 1L;
        ArrendadorDTO arrendadorDTO = new ArrendadorDTO();
        // Configurar objeto mock para el servicio
        Arrendador arrendatarioMock = new Arrendador();
        // Configurar el comportamiento esperado del método actualizarArrendatario() del servicio
        when(arrendadorService.actualizarArrendador(eq(id), any(ArrendadorDTO.class))).thenReturn(arrendatarioMock);
        // Llamar al método del controlador
        ResponseEntity<Arrendador> response = arrendadorController.actualizarArrendador(id, arrendadorDTO);
        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(arrendatarioMock, response.getBody());
    }

}