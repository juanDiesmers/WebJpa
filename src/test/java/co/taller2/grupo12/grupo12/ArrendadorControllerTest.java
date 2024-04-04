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
import co.taller2.grupo12.grupo12.Controller.ArrendadorController;
import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.services.ArrendadorService;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.mockito.Mockito.when;


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

    /*@Test
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
    }*/

   
}
