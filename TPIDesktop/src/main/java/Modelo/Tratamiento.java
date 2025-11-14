package modelo;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tratamiento")
public class Tratamiento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTratamiento;

    private String nombreTratamiento;
    private String medicamento;

    public Tratamiento() {}

    // getters y setters
    public int getIdTratamiento() { return idTratamiento; }
    public void setIdTratamiento(int idTratamiento) { this.idTratamiento = idTratamiento; }
    public String getNombreTratamiento() { return nombreTratamiento; }
    public void setNombreTratamiento(String nombreTratamiento) { this.nombreTratamiento = nombreTratamiento; }
    public String getMedicamento() { return medicamento; }
    public void setMedicamento(String medicamento) { this.medicamento = medicamento; }
}
