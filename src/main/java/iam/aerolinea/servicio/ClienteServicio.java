package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Cliente;
import iam.aerolinea.repositorio.ClienteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServicio implements IClienteServicio {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Override
    public List<Cliente> listarClientes() {
        return List.of();
    }

    @Override
    public List<Cliente> guardarCliente() {
        return List.of();
    }

    @Override
    public Optional<Cliente> obtenerClientePorId() {
        return Optional.empty();
    }

    @Override
    public List<Cliente> listarRutas() {
        return List.of();
    }
}
