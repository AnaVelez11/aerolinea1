package iam.aerolinea.controlador;

import iam.aerolinea.modelo.Equipaje;
import iam.aerolinea.servicio.EquipajeServicio;
import iam.aerolinea.servicio.IEquipajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/equipaje")
public class EquipajeControlador {

    @Autowired
    private IEquipajeServicio equipajeServicio;

    @PostMapping("/registrar")
    public ResponseEntity<Equipaje> registrarEquipaje(@RequestBody Equipaje equipaje,
                                                      @RequestParam String clase,
                                                      @RequestParam boolean esNacional) {
        try {
            Equipaje equipajeRegistrado = equipajeServicio.registrarEquipaje(equipaje, clase, esNacional);
            return ResponseEntity.status(HttpStatus.CREATED).body(equipajeRegistrado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Retornar un error si el equipaje no es válido
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Equipaje>> listarEquipajePorCliente(@PathVariable Long clienteId) {
        List<Equipaje> equipajes = equipajeServicio.obtenerEquipajePorCliente(clienteId);
        return ResponseEntity.ok(equipajes);
    }

    @GetMapping("/vuelo/{vueloId}")
    public ResponseEntity<List<Equipaje>> listarEquipajePorVuelo(@PathVariable Long vueloId) {
        List<Equipaje> equipajes = equipajeServicio.obtenerEquipajePorVuelo(vueloId);
        return ResponseEntity.ok(equipajes);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<Set<Equipaje>> listarEquipajePorTipo(@PathVariable String tipo) {
        Set<Equipaje> equipajes = equipajeServicio.obtenerEquipajePorTipo(tipo);
        return ResponseEntity.ok(equipajes);
    }
}
