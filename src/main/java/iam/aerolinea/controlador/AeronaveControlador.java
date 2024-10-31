package iam.aerolinea.controlador;

import iam.aerolinea.modelo.Aeronave;
import iam.aerolinea.modelo.Vuelo;
import iam.aerolinea.servicio.IAerolineaServicio;
import iam.aerolinea.servicio.IAeronaveServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aeronave")
public class AeronaveControlador {

    @Autowired
    private IAeronaveServicio aeronaveServicio;


}
