package iam.aerolinea.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Aeronave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private TipoAeronave tipo;
    private int capacidadPasajeros;
    private int capacidadCarga;
    private int capacidadClaseEjecutiva;
    private int capacidadClaseEconomica;
    private boolean esInternacional;

    @ElementCollection
    private List<String> configuracionEjecutiva; // ["AC", "DF"]
    @ElementCollection
    private List<String> configuracionEconomica; // ["ABC", "DEF"]

    @ManyToOne
    private Aerolinea aerolinea;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vuelo> listaVuelos = new ArrayList<>();


}
