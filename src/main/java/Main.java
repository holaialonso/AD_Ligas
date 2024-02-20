import controller.DAOEquipo;
import controller.DAOPartido;
import model.Equipo;
import model.Liga;
import controller.DAOLiga;
import model.Partido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main (String[] args) throws IOException {

        //1. GESTIÓN DE LIGAS

            //1.1. Crear, editar y eliminar ligas
                //makeLigas();

            //1.2. Asignar equipos a las ligas
                //makeEquiposLigas();

            //1.3. Consultar información de las ligas disponibles
                //showLigas();


        //2. GESTIÓN DE EQUIPOS




        /*
            TRUNCATE TABLE Equipos;
            TRUNCATE TABLE Ligas;
            TRUNCATE TABLE Partido;
         */

        //1. Crea 1 liga
    /*    Liga liga = new Liga("Liga Santander", LocalDate.of(2024,5,12),LocalDate.of(2023,4,23));
        DAOLiga DAOLiga = new DAOLiga();
        DAOLiga.insertarLiga(liga);


        //2. Crea 8 equipos
        Equipo equipo = new Equipo ("prueba", "prueba", liga);
        Equipo equipo2 = new Equipo("PRUEBA2", "TOLEDO", liga);
        DAOEquipo DAOEquipo = new DAOEquipo();
        DAOEquipo.insertarEquipo(equipo);
        DAOEquipo.insertarEquipo(equipo2);


        //3. Crea 6 partidos
        Partido partido = new Partido(LocalDate.of(2024,5,12), 9, 0, equipo, equipo2, liga);
        DAOPartido DAOPartido = new DAOPartido();
        DAOPartido.insertarPartido(partido);
    */
        //4. Muestra datos de todos los equipos
    /*    DAOEquipo DAOEquipo = new DAOEquipo();
        DAOEquipo.getEquipos();
    */

        //5. Elimina 2 equipos
   /*     DAOEquipo DAOEquipo = new DAOEquipo();
        DAOEquipo.deleteEquipo(2);
    */

        //6. Muestra todos los partidos de una liga concreta
    /*    DAOPartido DAOPartido = new DAOPartido();
        Liga liga = new Liga(6,"Liga Santander", LocalDate.of(2024,5,12),LocalDate.of(2023,4,23));
        DAOPartido.getPartidos(liga);


     */
    }


    //LIGAS

        //Método para crear, editar y eliminar ligas
        public static void makeLigas(){

            DAOLiga DAOLiga = new DAOLiga();

            //CREACIÓN
                //Creo un arraylist con las ligas
                List<Liga> ligas = new ArrayList();
                                ligas.add(new Liga("Liga Santander", LocalDate.of(2023, 8, 15), LocalDate.of(2024,5,24)));
                                ligas.add(new Liga("Serie A", LocalDate.of(2023, 10, 15), LocalDate.of(2024,3,27)));
                                ligas.add(new Liga("Bundesliga", LocalDate.of(2023, 1, 31), LocalDate.of(2024,2,27)));
                                ligas.add(new Liga("Ligue 1", LocalDate.of(2023, 12, 25), LocalDate.of(2024,3,30)));

                //Las inserto en la bbdd
                for(int i=0; i<ligas.size(); i++){

                    DAOLiga.insertarLiga(ligas.get(i));
                }

            //EDITAR

                Liga ligaEditada = new Liga("Liga Santander / Edit",  LocalDate.of(2023, 8, 17),  LocalDate.of(2023, 9, 17));
                DAOLiga.editarLiga(1, ligaEditada);


            //ELIMINAR

                DAOLiga.eliminarLiga(2);
                DAOLiga.eliminarLiga(3);


        }

        //Método para asignar equipos a las ligas
        public static void makeEquiposLigas(){

            DAOLiga DAOLiga = new DAOLiga();

            //Obtengo una liga
            Liga liga = DAOLiga.getLiga(1);

            //Creo una lista de equipos
            List<Equipo> equipos = new ArrayList<>();
                         equipos.add(new Equipo("Real Madrid", "Madrid", liga));
                         equipos.add(new Equipo("Barcelona FC", "Barcelona", liga));

            //Los seteo dentro de la liga
            liga.setEquipos(equipos);

            //Asigno en la bbdd los equipos
            DAOLiga.insertarLiga(liga);
        }

        //Método para consultar la información de las ligas disponibles
        public static void showLigas(){

            //Consulto la información
            DAOLiga DAOLiga = new DAOLiga();
            List<Liga> listado = DAOLiga.getLigas();

            //Imprimir
            System.out.println("LISTADO DE LIGAS DISPONIBLES");
            if(listado.size()>0) {
                for (Liga item : listado) {

                    System.out.println("LIGA -> " + item.getNombre_liga());
                    System.out.println("Fecha inicio -> " + item.getFecha_inicio());
                    System.out.println("Fecha fin -> " + item.getFecha_fin());
                    System.out.println("\n");
                }
            }
            else{
                System.out.println("No hay ligas disponibles para mostrar");
            }

        }


}
