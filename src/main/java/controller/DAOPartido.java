package controller;

import database.HibernateUtil;
import model.Equipo;
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
    public void insertPartido(Partido partido){

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


    //Método que devuelve un partido dado una id
    public Partido getPartido(int id){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Query
        Query<Partido> query = session.createQuery("SELECT a FROM Partido a WHERE a.id_partido=:id").setParameter("id", id);
        Partido partido = query.uniqueResult();

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

        return partido;
    }

    //Método para mostrar todos los partidos de una liga concreta
    public List<Partido> getPartidos(Liga liga){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Query
        Query<Partido> query = session.createQuery("SELECT a FROM Partido a WHERE a.liga.id_liga=:id_liga").setParameter("id_liga", liga.getId_liga());
        List<Partido> listado = query.list();

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

        return listado;

    }


    //Método que devuelve todos los partidos (sin ningún tipo de filtro)
    public List<Partido> getAllPartidos(){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Query
        Query<Partido> query = session.createQuery("SELECT a FROM Partido a ORDER BY a.fecha_partido ASC");
        List<Partido> listado = query.list();

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

        return listado;

    }

    //Método que modifica los resultados de un partido dado
    public void modifyPartidoResult(Partido partido){

        //sesión
        Session session = sessionFactory.getCurrentSession();

        //Activar la transaccion
        session.beginTransaction();

        //Query
        Query query = session.createQuery("UPDATE Partido a SET a.goles_equipo_local=:goles_equipo_local, a.goles_equipo_visitante=:goles_equipo_visitante WHERE a.id_partido=:id_partido").setParameter("goles_equipo_local", partido.getGoles_equipo_local()).setParameter("goles_equipo_visitante", partido.getGoles_equipo_visitante()).setParameter("id_partido", partido.getId_partido());
        query.executeUpdate();

        //Commit
        session.getTransaction().commit();

        //Cierro la conexión
        session.close();

    }
}
