package iam.aerolinea.controlador;
import iam.aerolinea.modelo.Aeronave;
import iam.aerolinea.modelo.Ruta;
import iam.aerolinea.modelo.Tripulante;
import iam.aerolinea.servicio.IAerolineaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/aerolinea")
public class AerolineaControlador {

    @Autowired
    private IAerolineaServicio aerolineaServicio;

    // Obtener todas las aeronaves de la única aerolínea
    @GetMapping("/aeronaves")
    public List<Aeronave> listarAeronaves() {
        return aerolineaServicio.listarAeronaves();
    }

    // Obtener todos los tripulantes de la única aerolínea
    @GetMapping("/tripulantes")
    public List<Tripulante> listarTripulantes() {
        return aerolineaServicio.listarTripulantes();
    }

    // Obtener todas las rutas de la única aerolínea
    @GetMapping("/rutas")
    public List<Ruta> listarRutas() {
        return aerolineaServicio.listarRutas();
    }
}

