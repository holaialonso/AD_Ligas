package model;

import database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name=SchemaDB.TAB_EQUIPOS)
public class Equipo implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_equipo;
    @Column
    private String nombre_equipo;
    @Column
    private String ciudad;
    @ManyToOne
    @JoinColumn(name= SchemaDB.COL_IDLIGA)
    private Liga liga;

    @OneToMany(mappedBy = "equipoLocal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partido> partidoLocal;

    @OneToMany(mappedBy = "equipoVisitante", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partido> partidoVisitante;



    //Constructor sin el id
    public Equipo(String nombre_equipo, String ciudad, Liga liga){
        this.nombre_equipo=nombre_equipo;
        this.ciudad=ciudad;
        this.liga=liga;
    }

    //Constructor sin la liga y sin el id
    public Equipo(String nombre_equipo, String ciudad){
        this.nombre_equipo=nombre_equipo;
        this.ciudad=ciudad;
    }
}
