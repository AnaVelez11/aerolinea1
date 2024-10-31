package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Aeronave;
import iam.aerolinea.modelo.Vuelo;
import iam.aerolinea.repositorio.AeronaveRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AeronaveServicio implements IAeronaveServicio {

    @Autowired
    private AeronaveRepositorio aeronaveRepositorio;


}
