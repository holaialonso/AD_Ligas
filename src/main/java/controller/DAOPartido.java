package controller;

import database.HibernateUtil;
import model.Liga;
import model.Partido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
}
