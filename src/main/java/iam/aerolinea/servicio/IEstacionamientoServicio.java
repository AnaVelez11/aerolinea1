package iam.aerolinea.servicio;

import iam.aerolinea.modelo.CarroEmbarque;

import java.util.List;
import java.util.Optional;

public interface IEstacionamientoServicio {

    // Método para registrar la llegada de un carro de embarque
    String llegada(CarroEmbarque carro);

    // Método para manejar la salida del carro en la primera posición
    Optional<CarroEmbarque> salida();

    // Método para retirar un carro específico de la fila por ID
    Optional<CarroEmbarque> retirarCarro(Long id);

    // Método para obtener todos los carros actualmente en el estacionamiento
    List<CarroEmbarque> obtenerCarrosEnEstacionamiento();
}
