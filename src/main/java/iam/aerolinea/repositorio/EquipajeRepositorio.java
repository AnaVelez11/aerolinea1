package iam.aerolinea.repositorio;

import iam.aerolinea.modelo.Cliente;
import iam.aerolinea.modelo.Equipaje;
import iam.aerolinea.modelo.Vuelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipajeRepositorio extends JpaRepository <Equipaje, Long>{
    List<Equipaje> findByClienteId(Long clienteId); // Buscar equipaje por cliente
    List<Equipaje> findByVueloId(Long vueloId);   // Buscar equipaje por vuelo
    List<Equipaje> findByTipo(String tipo);         // Buscar equipaje por tipo

}
