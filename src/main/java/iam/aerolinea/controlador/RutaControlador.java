package iam.aerolinea.controlador;

import iam.aerolinea.modelo.Ruta;
import iam.aerolinea.servicio.RutaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rutas")
public class RutaControlador {

    @Autowired
    private RutaServicio rutaServicio;

    // Endpoint para obtener destinos según el origen
    @GetMapping("/rutasPorOrigen")
    public List<String> listarDestinosPorOrigen(@RequestParam String origen) {
        List<Ruta> rutas = rutaServicio.buscarRutasPorOrigen(origen); // Obtiene rutas según el origen
        return rutas.stream()
                .map(Ruta::getDestino) // Extrae solo los destinos de las rutas obtenidas
                .distinct() // Filtra destinos duplicados
                .collect(Collectors.toList()); // Devuelve la lista de destinos únicos
    }

    // Buscar rutas por destino
    @GetMapping("/rutasPorDestino")
    public List<Ruta> buscarRutasPorDestino(@PathVariable String destino) {
        return rutaServicio.buscarRutasPorDestino(destino);
    }

    // Buscar rutas internacionales/nacionales
    @GetMapping("/tipo/{esInternacional}")
    public List<Ruta> buscarRutasPorTipo(@PathVariable boolean esInternacional) {
        return rutaServicio.buscarRutasPorTipo(esInternacional);
    }

    // Guardar una nueva ruta
    @PostMapping
    public Ruta guardarRuta(@RequestBody Ruta ruta) {
        return rutaServicio.guardarRuta(ruta);
    }

    // Buscar una ruta por ID
    @GetMapping("/{id}")
    public Optional<Ruta> buscarRutaPorId(@PathVariable Long id) {
        return rutaServicio.buscarRutaPorId(id);
    }

    // Eliminar una ruta
    @DeleteMapping("/{id}")
    public void eliminarRuta(@PathVariable Long id) {
        rutaServicio.eliminarRuta(id);
    }

}
