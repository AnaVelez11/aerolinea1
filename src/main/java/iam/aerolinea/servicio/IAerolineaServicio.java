package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Aerolinea;
import iam.aerolinea.modelo.Aeronave;
import iam.aerolinea.modelo.Ruta;
import iam.aerolinea.modelo.Tripulante;

import java.util.List;
import java.util.Optional;

public interface IAerolineaServicio {

    Optional<Aerolinea> obtenerAerolinea();
    public List<Aeronave> listarAeronaves();
    public List<Tripulante> listarTripulantes();
    public List<Ruta> listarRutas();


}
