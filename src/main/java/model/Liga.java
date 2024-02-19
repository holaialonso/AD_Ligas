package model;

import database.SchemaDB;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

@Entity
@Table(name= SchemaDB.TAB_LIGAS)
public class Liga implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_liga;
    @Column
    private String nombre_liga;
    @Column
    private LocalDate fecha_inicio;
    @Column
    private LocalDate fecha_fin;
    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Equipo> equipos;

    @OneToMany(mappedBy = "liga", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Partido> partidos;




    //Constructor sin la id
    public Liga (String nombre_liga, LocalDate fecha_inicio, LocalDate fecha_fin){

        this.nombre_liga=nombre_liga;
        this.fecha_inicio=fecha_inicio;
        this.fecha_fin=fecha_fin;

    }



}
