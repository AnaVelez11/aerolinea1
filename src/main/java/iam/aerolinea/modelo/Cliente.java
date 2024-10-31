package iam.aerolinea.modelo;
import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Cliente {
    @Id

    String id;
    String nombre;
    String apellido;
    String email;
    LocalDate fechaNacimiento;
    String direccion;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tiquete> listaTiquetes = new ArrayList<>();


}
