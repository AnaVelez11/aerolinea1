package iam.aerolinea.modelo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Equipaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float peso;
    private String tipo;
    private String dimensiones;
    private float costoAdicional;

    @ManyToOne
    private Vuelo vuelo;

    @ManyToOne
    private Cliente cliente;

}
