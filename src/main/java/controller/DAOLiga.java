package controller;

import database.HibernateUtil;
import model.Liga;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class DAOLiga {

    private SessionFactory sessionFactory;

    public DAOLiga(){

        sessionFactory = HibernateUtil.getSessionFactory();
    }

    //Consultas
        //Método para insertar una liga
        public void insertLiga(Liga liga){

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


        //Método para añadir equipos a la liga
        public void insertLigaEquipos(Liga liga){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Persistir
            session.merge(liga); //si la liga está creada, no la vuelve a crear, en caso contrario la crea de cero

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

        }


        //Método para actualizar la información de una liga
        public void modifyLiga(int id, Liga liga){

            //Antes de actualizar la liga -> compruebo que existe
            if(issetLiga(id)) {

                //sesión
                Session session = sessionFactory.getCurrentSession();

                //Activar la transaccion
                session.beginTransaction();

                //Query
                Query query = session.createQuery("UPDATE Liga a SET a.nombre_liga=:nombre_liga, a.fecha_inicio=:fecha_inicio, a.fecha_fin=:fecha_fin WHERE a.id_liga=:id_liga").setParameter("nombre_liga", liga.getNombre_liga()).setParameter("fecha_inicio", liga.getFecha_inicio()).setParameter("fecha_fin", liga.getFecha_fin()).setParameter("id_liga", id);
                query.executeUpdate();


                //Commit
                session.getTransaction().commit();

                //Cierro la conexión
                session.close();
            }

        }


        //Método para comprobar si una liga existe
        public boolean issetLiga(int id){

            boolean aux = false;

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("SELECT a FROM Liga a WHERE a.id_liga=:id_liga").setParameter("id_liga", id);
            Liga liga = (Liga) query.uniqueResult();

            if(liga!=null){
                aux=true;
            }


            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

            return aux;

        }


        //Método para eliminar una liga
        public void deleteLiga(int id){

            //Antes de eliminar la liga -> compruebo que existe
            if(issetLiga(id)) {

                //sesión
                Session session = sessionFactory.getCurrentSession();

                //Activar la transaccion
                session.beginTransaction();

                //Query
                Query query = session.createQuery("DELETE FROM Liga a WHERE a.id_liga=:id_liga").setParameter("id_liga", id);
                query.executeUpdate();


                //Commit
                session.getTransaction().commit();

                //Cierro la conexión
                session.close();
            }

        }


        //Método que devuelve la información de todas las ligas disponibles
        public List<Liga> getLigas(){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("SELECT a FROM Liga a");
            List<Liga> listado = query.list();


            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

            return listado;
        }


        //Método para obtener una liga cuando le pasamos el id
        public Liga getLiga(int id){

            //sesión
            Session session = sessionFactory.getCurrentSession();

            //Activar la transaccion
            session.beginTransaction();

            //Query
            Query query = session.createQuery("SELECT a FROM Liga a WHERE a.id_liga=:id").setParameter("id", id);
            Liga liga = (Liga) query.uniqueResult();

            //Commit
            session.getTransaction().commit();

            //Cierro la conexión
            session.close();

            return liga;
        }

}
