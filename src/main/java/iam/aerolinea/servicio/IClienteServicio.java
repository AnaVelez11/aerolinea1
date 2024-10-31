package iam.aerolinea.servicio;

import iam.aerolinea.modelo.*;

import java.util.List;
import java.util.Optional;

public interface IClienteServicio {

    List<Cliente> listarClientes();
    public List<Cliente> guardarCliente();
    Optional <Cliente> obtenerClientePorId();
    public List<Cliente> listarRutas();
}
