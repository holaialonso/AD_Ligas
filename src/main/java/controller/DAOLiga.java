package controller;

import database.HibernateUtil;
import model.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class DAOLiga {

    private SessionFactory sessionFactory;

    public DAOLiga(){

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    //Consultas
        //Método para insertar una liga
        public void insertarLiga(Liga liga){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Persistir
            session.persist(liga);

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

        }
}
