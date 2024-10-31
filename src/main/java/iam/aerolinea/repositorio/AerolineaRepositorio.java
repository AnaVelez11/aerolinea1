package iam.aerolinea.repositorio;

import iam.aerolinea.modelo.Aerolinea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AerolineaRepositorio extends JpaRepository<Aerolinea,Long> {

}
