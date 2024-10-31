package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Equipaje;
import java.util.List;
import java.util.Set;

public interface IEquipajeServicio {

    // Método para obtener equipaje por ID de cliente (mantiene List ya que se usa en Map internamente)
    List<Equipaje> obtenerEquipajePorCliente(Long clienteId);

    // Método para obtener equipaje por ID de vuelo (también mantiene List debido al uso de Map)
    List<Equipaje> obtenerEquipajePorVuelo(Long vueloId);

    // Método para obtener equipaje por tipo utilizando TreeSet para unicidad y orden
    Set<Equipaje> obtenerEquipajePorTipo(String tipo);

    // Método para registrar equipaje
    Equipaje registrarEquipaje(Equipaje equipaje, String clase, boolean esNacional);

    // Método para validar si se puede llevar una mascota (añadido ya que está en ServicioEquipaje)
    boolean puedeLlevarMascota(float pesoMascota);

    // Método para registrar mascota (mantiene el cálculo del costo)
    float registrarMascota(float pesoMascota);

    // Método para validar equipaje de mano
    boolean validarEquipajeDeMano(Equipaje equipaje);

    // Método para obtener el peso máximo permitido
    float obtenerPesoMaximoPermitido(String clase, boolean esNacional);

    // Método para obtener cantidad de equipaje permitido
    int obtenerCantidadEquipajePermitido(String clase, boolean esNacional);

    // Método para validar la cantidad de equipaje utilizando HashSet para evitar duplicados
    boolean validarCantidadEquipaje(List<Equipaje> equipajes, String clase, boolean esNacional);

    // Método para validar el equipaje según restricciones
    boolean validarEquipaje(Equipaje equipaje, String clase, boolean esNacional);

    // Método para calcular costo adicional
    float calcularCostoAdicional(Equipaje equipaje, float pesoMaximoPermitido);

    // Método para calcular costo de transporte de mascota
    float calcularCostoMascota(float pesoMascota);
}
