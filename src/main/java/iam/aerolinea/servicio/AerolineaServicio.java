package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Aerolinea;
import iam.aerolinea.modelo.Aeronave;
import iam.aerolinea.modelo.Ruta;
import iam.aerolinea.modelo.Tripulante;
import iam.aerolinea.repositorio.AerolineaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AerolineaServicio implements IAerolineaServicio{

    @Autowired
    private AerolineaRepositorio aerolineaRepositorio;

    // Método para obtener la aerolínea (asumiendo que hay una sola)
    @Override
    public Optional<Aerolinea> obtenerAerolinea() {
        return aerolineaRepositorio.findById(1L);  // ID 1 para la única aerolínea
    }

    // Método para listar aeronaves de la única aerolínea
    @Override
    public List<Aeronave> listarAeronaves() {
        Optional<Aerolinea> aerolineaOpt = obtenerAerolinea();
        if (aerolineaOpt.isPresent()) {
            return aerolineaOpt.get().getListaAeronaves();  // Devuelve la lista de aeronaves
        } else {
            throw new RuntimeException("Aerolínea no encontrada");
        }
    }

    // Método para listar tripulantes de la única aerolínea
    @Override
    public List<Tripulante> listarTripulantes() {
        Optional<Aerolinea> aerolineaOpt = obtenerAerolinea();
        if (aerolineaOpt.isPresent()) {
            return aerolineaOpt.get().getListaTripulantes();  // Devuelve la lista de tripulantes
        } else {
            throw new RuntimeException("Aerolínea no encontrada");
        }
    }

    // Método para listar rutas de la única aerolínea
    @Override
    public List<Ruta> listarRutas() {
        Optional<Aerolinea> aerolineaOpt = obtenerAerolinea();
        if (aerolineaOpt.isPresent()) {
            return aerolineaOpt.get().getListaRutas();  // Devuelve la lista de rutas
        } else {
            throw new RuntimeException("Aerolínea no encontrada");
        }
    }

    public Tripulante agregarTripulante(Tripulante tripulante) {
        Optional<Aerolinea> aerolineaOpt = obtenerAerolinea();
        if (aerolineaOpt.isPresent()) {
            Aerolinea aerolinea = aerolineaOpt.get();
            aerolinea.getListaTripulantes().add(tripulante);  // Añade el tripulante a la lista
            aerolineaRepositorio.save(aerolinea);  // Guarda la aerolínea actualizada
            return tripulante;
        } else {
            throw new RuntimeException("Aerolínea no encontrada");
        }
    }

    public Ruta agregarRuta(Ruta ruta) {
        Optional<Aerolinea> aerolineaOpt = obtenerAerolinea();
        if (aerolineaOpt.isPresent()) {
            Aerolinea aerolinea = aerolineaOpt.get();
            aerolinea.getListaRutas().add(ruta);  // Añade la ruta a la lista
            aerolineaRepositorio.save(aerolinea);  // Guarda la aerolínea actualizada
            return ruta;
        } else {
            throw new RuntimeException("Aerolínea no encontrada");
        }
    }



}

