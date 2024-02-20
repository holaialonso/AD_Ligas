package controller;

import database.HibernateUtil;
import model.Liga;
import model.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DAOPartido {

    private SessionFactory sessionFactory;

    public DAOPartido(){

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    //Consultas
    //Método para insertar una liga
    public void insertarPartido(Partido partido){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Persistir
        session.persist(partido);

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

    }


    //Método para mostrar todos los partidos de una liga concreta
    public void getPartidos(Liga liga){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Query
        Query<Partido> query = session.createQuery("SELECT a FROM Partido a WHERE a.liga.id_liga=:id_liga").setParameter("id_liga", liga.getId_liga());
        List<Partido> listado = query.list();

        for(Partido item : listado){

            System.out.println(item.getFecha_partido());
            System.out.println(item.getEquipoLocal().getNombre_equipo());
            System.out.println(item.getEquipoVisitante().getNombre_equipo());

        }

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

    }
}
