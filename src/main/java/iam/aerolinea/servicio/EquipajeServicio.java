package iam.aerolinea.servicio;

import iam.aerolinea.modelo.Equipaje;
import iam.aerolinea.repositorio.EquipajeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipajeServicio implements IEquipajeServicio{

        @Autowired
        private EquipajeRepositorio equipajeRepositorio;

        // Utilización de un HashMap para almacenar equipaje por ID de cliente, mejora la eficiencia de búsqueda
        private Map<Long, List<Equipaje>> equipajePorClienteMap = new HashMap<>();

        // Utilización de un HashMap para almacenar equipaje por ID de vuelo
        private Map<Long, List<Equipaje>> equipajePorVueloMap = new HashMap<>();

        // Constantes para facilitar cambios
        private static final float PESO_MAX_ECONOMICO = 24.0f;
        private static final float PESO_MAX_CLASE_SUPERIOR = 34.0f;
        private static final float TARIFA_SOBREPESO = 8.0f;
        private static final float IVA = 1.0675f;

        // Método para obtener equipaje por ID de cliente usando HashMap
        public List<Equipaje> obtenerEquipajePorCliente(Long clienteId) {
            return equipajePorClienteMap.computeIfAbsent(clienteId, id -> equipajeRepositorio.findByClienteId(id));
        }

        // Método para obtener equipaje por ID de vuelo usando HashMap
        public List<Equipaje> obtenerEquipajePorVuelo(Long vueloId) {
            return equipajePorVueloMap.computeIfAbsent(vueloId, id -> equipajeRepositorio.findByVueloId(id));
        }

        // Método para obtener equipaje por tipo usando un TreeSet para tipos únicos y ordenados
        public Set<Equipaje> obtenerEquipajePorTipo(String tipo) {
            Set<Equipaje> equipajes = new TreeSet<>(Comparator.comparing(Equipaje::getId)); // TreeSet para orden y unicidad
            equipajes.addAll(equipajeRepositorio.findByTipo(tipo));
            return equipajes;
        }

        public Equipaje registrarEquipaje(Equipaje equipaje, String clase, boolean esNacional) {
            if (!validarEquipaje(equipaje, clase, esNacional)) {
                throw new IllegalArgumentException("El equipaje no cumple con las restricciones.");
            }

            float pesoMaximoPermitido = (esNacional && "economica".equalsIgnoreCase(clase)) ? 24 : 34;
            float costoAdicional = calcularCostoAdicional(equipaje, pesoMaximoPermitido);
            equipaje.setCostoAdicional(costoAdicional);

            return equipajeRepositorio.save(equipaje);
        }

        public boolean puedeLlevarMascota(float pesoMascota) {
            return pesoMascota >= 3;
        }

        public float registrarMascota(float pesoMascota) {
            if (!puedeLlevarMascota(pesoMascota)) {
                throw new IllegalArgumentException("La mascota debe pesar al menos 3 kg para ser registrada.");
            }
            return calcularCostoMascota(pesoMascota);
        }

        public boolean validarEquipajeDeMano(Equipaje equipaje) {
            try {
                String[] dimParts = equipaje.getDimensiones().split(",");
                float alto = Float.parseFloat(dimParts[0]);
                float largo = Float.parseFloat(dimParts[1]);
                float ancho = Float.parseFloat(dimParts[2]);
                return (alto + largo + ancho) <= 110;
            }catch(Exception e){
                throw new IllegalArgumentException("Formato de dimensiones inválido: debe ser 'alto,largo,ancho'");
            }
        }

        public float obtenerPesoMaximoPermitido(String clase, boolean esNacional) {
            return esNacional ? ("economica".equalsIgnoreCase(clase) ? PESO_MAX_ECONOMICO : PESO_MAX_CLASE_SUPERIOR) : PESO_MAX_ECONOMICO;
        }

        public int obtenerCantidadEquipajePermitido(String clase, boolean esNacional) {
            return esNacional ? ("economica".equalsIgnoreCase(clase) ? 1 : 2) : 2;
        }

        // HashSet para evitar duplicados en equipaje permitido
        public boolean validarCantidadEquipaje(List<Equipaje> equipajes, String clase, boolean esNacional) {
            Set<Equipaje> equipajeSet = new HashSet<>(equipajes);
            return equipajeSet.size() <= obtenerCantidadEquipajePermitido(clase, esNacional);
        }

        public boolean validarEquipaje(Equipaje equipaje, String clase, boolean esNacional) {
            String[] dimParts = equipaje.getDimensiones().split(",");
            float alto = Float.parseFloat(dimParts[0]);
            float largo = Float.parseFloat(dimParts[1]);
            float ancho = Float.parseFloat(dimParts[2]);
            float sumaDimensiones = alto + largo + ancho;

            if (sumaDimensiones > 170) {
                throw new IllegalArgumentException("Las dimensiones del equipaje exceden el límite permitido de 170 cm.");
            }

            float pesoMaximo = esNacional ? ("economica".equalsIgnoreCase(clase) ? 24 : 34) : 24;
            if (equipaje.getPeso() > pesoMaximo) {
                throw new IllegalArgumentException("El peso del equipaje excede el límite permitido de " + pesoMaximo + " kg.");
            }

            return true;
        }

        public float calcularCostoAdicional(Equipaje equipaje, float pesoMaximoPermitido) {
            float sobrepeso = equipaje.getPeso() - pesoMaximoPermitido;
            return sobrepeso > 0 ? (TARIFA_SOBREPESO * sobrepeso) * IVA : 0;
        }

        public float calcularCostoMascota(float pesoMascota) {
            return (pesoMascota >= 3 && pesoMascota <= 9) ? 48.0f : (pesoMascota > 9) ? 48.0f + (pesoMascota - 9) * 2 : 0;
        }
    }
