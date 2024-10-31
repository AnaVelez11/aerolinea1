package iam.aerolinea.repositorio;

import iam.aerolinea.modelo.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RutaRepositorio extends JpaRepository<Ruta, Long>{

    List<Ruta> findByOrigen(String origen);
    List<Ruta> findByDestino(String destino);
    List<Ruta> findByEsInternacional(boolean esInternacional);

}
