package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Tripulante;
import iam.aerolinea.modelo.Vuelo;
import iam.aerolinea.repositorio.VueloRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VueloServicio {

    @Autowired
    private VueloRepositorio vueloRepositorio;

    }


