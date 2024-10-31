package iam.aerolinea.modelo;

import jakarta.persistence.*;
import lombok.*;
import lombok.ToString;
import java.util.ArrayList;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Tripulante {
    @Id

    private String id;
    private String nombre;
    private String difreccion;
    private String email;
    private LocalDate fechaNacimiento;
    private ArrayList<String> estudios;
    private String cargo;

    @ManyToOne
    private Aerolinea aerolinea;




}