package iam.aerolinea.repositorio;

import iam.aerolinea.modelo.Aeronave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AeronaveRepositorio extends JpaRepository<Aeronave,Long> {

}
