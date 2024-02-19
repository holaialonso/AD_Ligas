package model;

import database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table
public class Partido implements Serializable {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_partido;
    @Column
    private LocalDate fecha_partido;
    @Column
    private int goles_equipo_local;
    @Column
    private int goles_equipo_visitante;
    @ManyToOne
    @JoinColumn(name = SchemaDB.COL_IDEQUIPOLOCAL)
    private Equipo equipoLocal;

    @ManyToOne()
    @JoinColumn(name = SchemaDB.COL_IDEQUIPOVISITANTE)
    private Equipo equipoVisitante;
    @ManyToOne()
    @JoinColumn(name=SchemaDB.COL_IDLIGA)
    private Liga liga;

    //Constructor sin la id
    public Partido(LocalDate fecha_partido, int goles_equipo_local, int goles_equipo_visitante, Equipo equipoLocal, Equipo equipoVisitante, Liga liga){

        this.fecha_partido=fecha_partido;
        this.goles_equipo_local=goles_equipo_local;
        this.goles_equipo_visitante=goles_equipo_visitante;
        this.equipoLocal=equipoLocal;
        this.equipoVisitante=equipoVisitante;
        this.liga=liga;
    }
}
