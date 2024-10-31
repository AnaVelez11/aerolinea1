package iam.aerolinea.controlador;

import iam.aerolinea.modelo.CarroEmbarque;
import iam.aerolinea.servicio.EstacionamientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estacionamiento")
public class EstacionamientoControlador {

    @Autowired
    private EstacionamientoServicio estacionamientoServicio;

    @PostMapping("/llegada")
    public String llegada(@RequestBody CarroEmbarque carro) {
        return estacionamientoServicio.llegada(carro);
    }

    @GetMapping("/salida")
    public Optional<CarroEmbarque> salida() {
        return estacionamientoServicio.salida();
    }

    @DeleteMapping("/retirada/{id}")
    public Optional<CarroEmbarque> retirarCarro(@PathVariable Long id) {
        return estacionamientoServicio.retirarCarro(id);
    }

    @GetMapping("/carros")
    public List<CarroEmbarque> obtenerCarros() {
        return estacionamientoServicio.obtenerCarrosEnEstacionamiento();
    }
}
