package iam.aerolinea.servicio;

import iam.aerolinea.modelo.CarroEmbarque;
import iam.aerolinea.repositorio.CarroEmbarqueRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EstacionamientoServicio implements IEstacionamientoServicio{

    private final int capacidadMaxima = 10;
    private Deque<CarroEmbarque> estacionamiento = new ArrayDeque<>(capacidadMaxima);
    private Stack<CarroEmbarque> pilaTemporal = new Stack<>();

    @Autowired
    private CarroEmbarqueRepositorio carroEmbarqueRepositorio;

    // Llegada de un nuevo carro
    public String llegada(CarroEmbarque carro) {
        if (estacionamiento.size() < capacidadMaxima) {
            estacionamiento.addLast(carro);
            carroEmbarqueRepositorio.save(carro); // Guardar en la base de datos
            return "Carro agregado al estacionamiento";
        } else {
            return "Estacionamiento lleno. El carro espera";
        }
    }

    // Salida del carro en la primera posición
    public Optional<CarroEmbarque> salida() {
        if (!estacionamiento.isEmpty()) {
            CarroEmbarque carro = estacionamiento.removeFirst();
            carroEmbarqueRepositorio.delete(carro); // Eliminar de la base de datos
            return Optional.of(carro);
        } else {
            return Optional.empty();
        }
    }

    // Retirar un carro específico de la fila
    public Optional<CarroEmbarque> retirarCarro(Long id) {
        Optional<CarroEmbarque> carroOpt = estacionamiento.stream()
                .filter(carro -> carro.getId().equals(id))
                .findFirst();

        if (carroOpt.isPresent()) {
            CarroEmbarque carroARetirar = carroOpt.get();

            // Mover carros a la pila hasta encontrar el carro a retirar
            while (!estacionamiento.isEmpty() && !estacionamiento.peekFirst().getId().equals(id)) {
                pilaTemporal.push(estacionamiento.removeFirst());
            }

            // Remover el carro de la fila
            estacionamiento.removeFirst();

            // Volver a agregar los carros en el orden original
            while (!pilaTemporal.isEmpty()) {
                estacionamiento.addFirst(pilaTemporal.pop());
            }

            carroEmbarqueRepositorio.delete(carroARetirar); // Eliminar de la base de datos
            return Optional.of(carroARetirar);
        } else {
            return Optional.empty();
        }
    }

    public List<CarroEmbarque> obtenerCarrosEnEstacionamiento() {
        return List.copyOf(estacionamiento);
    }




}
