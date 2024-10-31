package iam.aerolinea.modelo;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String metodoPago; // tarjeta de crédito, débito, etc.
    private float costo; // Costo total de la factura

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tiquete> listaTiquetes; // Relación con los tiquetes comprados

    @ManyToOne
    private Cliente cliente; // Relación con el cliente que realizó la compra

}
