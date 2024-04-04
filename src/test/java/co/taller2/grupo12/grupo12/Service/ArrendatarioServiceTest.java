package co.taller2.grupo12.grupo12.Service;

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

}