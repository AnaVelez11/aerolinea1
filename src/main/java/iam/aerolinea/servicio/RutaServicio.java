package iam.aerolinea.servicio;

import iam.aerolinea.excepcion.ResourceNotFoundException;
import iam.aerolinea.modelo.Ruta;
import iam.aerolinea.repositorio.RutaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RutaServicio{

    @Autowired
    private RutaRepositorio rutaRepositorio;

    // Buscar rutas por origen
    public List<Ruta> buscarRutasPorOrigen(String origen) {
        return rutaRepositorio.findByOrigen(origen);
    }

    // Buscar rutas por destino
    public List<Ruta> buscarRutasPorDestino(String destino) {
        return rutaRepositorio.findByDestino(destino);
    }

    // Buscar rutas internacionales o nacionales
    public List<Ruta> buscarRutasPorTipo(boolean esInternacional) {
        return rutaRepositorio.findByEsInternacional(esInternacional);
    }

    // Guardar una nueva ruta
    public Ruta guardarRuta(Ruta ruta) {
        return rutaRepositorio.save(ruta);
    }

    // Buscar ruta por ID
    public Optional<Ruta> buscarRutaPorId(Long id) {
        return rutaRepositorio.findById(id);
    }

    // Eliminar una ruta
    public void eliminarRuta(Long id) {
        rutaRepositorio.deleteById(id);
    }
    // Obtener rutas de ida y vuelta desde CDMX, con tiempo de espera de retorno
    public List<Ruta> obtenerRutasIdaYVuelta(String origen) {
        List<Ruta> rutas = rutaRepositorio.findByOrigen(origen);
        rutas.forEach(ruta -> {
            Ruta rutaRetorno = new Ruta(
                    null,
                    ruta.getDestino(),
                    ruta.getOrigen(),
                    ruta.getDuracion(),
                    calcularHoraRetorno(ruta.getHoraSalida(), ruta.getTiempoEsperaRetorno()),
                    ruta.isEsInternacional(),
                    ruta.getTiempoEsperaRetorno(),
                    ruta.getAerolinea(),
                    new ArrayList<>(ruta.getListaVuelos())
            );
            rutas.add(rutaRetorno);
        });
        return rutas;
    }
    // Método para calcular la hora de retorno añadiendo el tiempo de espera en minutos
    private String calcularHoraRetorno(String horaSalida, int minutosEspera) {
        // Parsear la hora de salida a un objeto LocalTime
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime hora = LocalTime.parse(horaSalida, formatter);

        // Sumar los minutos de espera
        LocalTime horaRetorno = hora.plusMinutes(minutosEspera);

        // Retornar la hora de retorno en el formato adecuado
        return horaRetorno.format(formatter);
    }
    // Actualizar ruta existente
    public Ruta actualizarRuta(Long id, Ruta rutaDetalles) {
        return rutaRepositorio.findById(id).map(ruta -> {
            ruta.setOrigen(rutaDetalles.getOrigen());
            ruta.setDestino(rutaDetalles.getDestino());
            ruta.setDuracion(rutaDetalles.getDuracion());
            ruta.setHoraSalida(rutaDetalles.getHoraSalida());
            ruta.setEsInternacional(rutaDetalles.isEsInternacional());
            ruta.setTiempoEsperaRetorno(rutaDetalles.getTiempoEsperaRetorno());
            ruta.setAerolinea(rutaDetalles.getAerolinea());
            ruta.setListaVuelos(rutaDetalles.getListaVuelos());
            return rutaRepositorio.save(ruta);
        }).orElseThrow(() -> new ResourceNotFoundException("Ruta no encontrada con ID: " + id));
    }




}
