package iam.aerolinea.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Silla {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grupo;  // ABC o DEF para identificar la fila/grupo
    private String estado; // Estado de la silla (disponible, ocupada, reservada, etc.)

    @ManyToOne
    private Aeronave aeronave; // Relación con Aeronave

}
