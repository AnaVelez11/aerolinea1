package iam.aerolinea;

import iam.aerolinea.modelo.Equipaje;
import iam.aerolinea.repositorio.EquipajeRepositorio;
import iam.aerolinea.servicio.EquipajeServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EquipajeServicioTest {

    @InjectMocks
    private EquipajeServicio equipajeServicio;

    @Mock
    private EquipajeRepositorio equipajeRepositorio;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarEquipaje_Valido() {
        Equipaje equipaje = new Equipaje();
        equipaje.setDimensiones("50,50,50");
        equipaje.setPeso(20);
        String clase = "economica";
        boolean esNacional = true;

        when(equipajeRepositorio.save(any(Equipaje.class))).thenReturn(equipaje);

        Equipaje resultado = equipajeServicio.registrarEquipaje(equipaje, clase, esNacional);

        assertNotNull(resultado);
        assertEquals(equipaje, resultado);
        verify(equipajeRepositorio, times(1)).save(equipaje);
    }

    @Test
    void testRegistrarEquipaje_Invalido() {
        Equipaje equipaje = new Equipaje();
        equipaje.setDimensiones("60,60,60"); // Excede dimensiones
        equipaje.setPeso(25);
        String clase = "economica";
        boolean esNacional = true;

        assertThrows(IllegalArgumentException.class, () -> {
            equipajeServicio.registrarEquipaje(equipaje, clase, esNacional);
        });
    }

    @Test
    void testPuedeLlevarMascota_Valido() {
        assertTrue(equipajeServicio.puedeLlevarMascota(5));
    }

    @Test
    void testPuedeLlevarMascota_Invalido() {
        assertFalse(equipajeServicio.puedeLlevarMascota(2));
    }

    @Test
    void testCalcularCostoMascota_Fijo() {
        float costo = equipajeServicio.calcularCostoMascota(5);
        assertEquals(48.0f, costo);
    }

    @Test
    void testCalcularCostoMascota_Adicional() {
        float costo = equipajeServicio.calcularCostoMascota(10);
        assertEquals(50.0f, costo); // 48 + 2
    }








}
