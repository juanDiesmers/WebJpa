package co.taller2.grupo12.grupo12.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import co.taller2.grupo12.grupo12.ApplicationRepository.ArrendadorRepository;

import co.taller2.grupo12.grupo12.entity.Arrendador;
import co.taller2.grupo12.grupo12.services.ArrendadorService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController

@RequestMapping("/submit")

public class FormController {    

    @Autowired
    private  ArrendadorService arrendadorService;

    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Arrendador> get() throws Exception{
        return (List<Arrendador>) arrendadorService.getArrendador();
    }

    @PostMapping
    public Arrendador guardarArrendador(@RequestBody Arrendador arrendador) {
        return this.arrendadorService.guardarArrendador(arrendador);
    }
    

}