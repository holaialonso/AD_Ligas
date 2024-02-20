package controller;

import database.HibernateUtil;
import model.Equipo;
import model.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DAOEquipo {

    private SessionFactory sessionFactory;

    public DAOEquipo(){

        sessionFactory = HibernateUtil.getSessionFactory();
    }


    //Consultas
        //Método para insertar una liga
        public void insertEquipo(Equipo equipo){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Persistir
            session.persist(equipo);

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();
        }

        //Método para mostrar todos los equipos (select)
        public List<Equipo> getEquipos(){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query<Equipo> query = session.createQuery("SELECT a FROM Equipo a");
            List<Equipo> equipos = query.list();

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

            return equipos;
        }

        //Método para eliminar un equipo
        public void deleteEquipo(int idEquipo){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("DELETE FROM Equipo a WHERE a.id_equipo=:id_equipo").setParameter("id_equipo", idEquipo);
            query.executeUpdate();

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();
        }


        //Método para eliminar todos los equipos de una liga
        public void deleteEquipos(Liga liga){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("DELETE FROM Equipo a WHERE a.liga=:id_liga").setParameter("id_liga", liga);
            query.executeUpdate();

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

        }

        //Método para obtener la información de un equipo dada su id
        public Equipo getEquipo(int id){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query<Equipo> query = session.createQuery("SELECT a FROM Equipo a WHERE a.id_equipo=:id").setParameter("id", id);
            Equipo equipo = query.uniqueResult();

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

            return equipo;

        }


        //Método para modificar la información de un equipo
        public void modifyEquipo(Equipo equipo){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("UPDATE Equipo a SET a.nombre_equipo=:nombre_equipo, a.ciudad=:ciudad, a.liga=:id_liga WHERE a.id_equipo=:id_equipo").setParameter("nombre_equipo", equipo.getNombre_equipo()).setParameter("ciudad", equipo.getCiudad()).setParameter("id_liga", equipo.getLiga()).setParameter("id_equipo", equipo.getId_equipo());
            query.executeUpdate();


            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();


        }
}
