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
        public void insertarEquipo(Equipo equipo){

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
        public void getEquipos(){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query<Equipo> query = session.createQuery("SELECT a FROM Equipo a");
            List<Equipo> listado = query.list();

            for(Equipo item : listado){

                System.out.println(item.getId_equipo());
            }


            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();
        }

}
