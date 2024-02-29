import controller.DAOEquipo;
import controller.DAOPartido;
import model.Equipo;
import model.Liga;
import controller.DAOLiga;
import model.Partido;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Main {

    public static void main (String[] args) throws IOException {


        //ESTRUCTURA
            //1. GESTIÓN DE LIGAS

                //1.1. Crear, editar y eliminar ligas
                    makeNewLigas(); //-> crear
                    makeEditLigas(); //-> editar
                    makeDeleteLigas(); //->eliminar

                //1.2. Asignar equipos a las ligas
                    makeEquiposLigas();

                //1.3. Consultar información de las ligas disponibles
                    showLigas();


            //2. GESTIÓN DE EQUIPOS

                //2.1. Registrar nuevos equipos
                    makeNewEquipos();

                //2.2. Modificar información de equipos existentes
                    makeEditEquipos();

                //2.3. Eliminar equipos de una liga
                    deleteEquiposLiga();


            //3. GESTIÓN DE PARTIDOS

                //3.1. Registrar nuevos partidos, indicando equipos involucrados y fecha
                    makeNewPartidos();

                //3.2. Asignar resultados a los partidos jugados
                    makeEditPartidos();

                //3.3. Consultar el calendario de partidos y sus resultados
                   showCalendarPartidos();

        //ACCIONES
            makeAcciones();

    }


    //LIGAS

        //Método para crear nuevas ligas
        public static void makeNewLigas(){

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

                DAOLiga.insertLiga(ligas.get(i));
            }
        }


        //Método para editar ligas
        public static void makeEditLigas(){

            DAOLiga DAOLiga = new DAOLiga();

            Liga ligaEditada = new Liga("Liga Santander / Edit",  LocalDate.of(2023, 8, 17),  LocalDate.of(2023, 9, 17));
            DAOLiga.modifyLiga(1, ligaEditada);

        }

        //Método para eliminar ligas
        public static void makeDeleteLigas(){

            DAOLiga DAOLiga = new DAOLiga();

            DAOLiga.deleteLiga(2);
            DAOLiga.deleteLiga(3);
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
            DAOLiga.insertLigaEquipos(liga);
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


    //EQUIPOS

        //Método para crear nuevos equipos
        public static void makeNewEquipos(){

            //1. Obtengo las ligas a las que irán asignadas los equipos
            DAOLiga DAOLiga = new DAOLiga();
            Liga liga1 = DAOLiga.getLiga(1);
            Liga liga2 = DAOLiga.getLiga(4);


            //2. Creo los objetos de los equipos
            List<Equipo> equipos = new ArrayList<>();
                         equipos.add(new Equipo("U.D. Almería", "Almería", liga1));
                         equipos.add(new Equipo("Deportivo Alavés", "Álava", liga1));
                         equipos.add(new Equipo("Getafe C.F.", "Madrid", liga1));
                         equipos.add(new Equipo("Betis", "Sevilla", liga2));
                         equipos.add(new Equipo("Sevilla", "Sevilla", liga2));

            DAOEquipo DAOEquipo = new DAOEquipo();

            for(int i=0; i<equipos.size(); i++){

                DAOEquipo.insertEquipo(equipos.get(i));
            }


        }

        //Método para editar la información de los equipos existentes
        public static void makeEditEquipos(){

            DAOEquipo DAOEquipo = new DAOEquipo();
            DAOLiga DAOLiga = new DAOLiga();

            //Información del dequipo
            Equipo equipo = DAOEquipo.getEquipo(3);


            //Modificar la información del equipo
            equipo.setNombre_equipo("Granada F.C.");
            equipo.setCiudad("Granada");
            equipo.setLiga(DAOLiga.getLiga(4));

            DAOEquipo.modifyEquipo(equipo);
        }

        //Método para eliminar equipos de una liga
        public static void deleteEquiposLiga(){

            //Obtengo la liga
            DAOLiga DAOLiga = new DAOLiga();
            Liga liga = DAOLiga.getLiga(4);

            //Elimino todos los equipos de esa liga
            DAOEquipo DAOEquipo = new DAOEquipo();
            DAOEquipo.deleteEquipos(liga);


        }


    //PARTIDOS

        //Método para crear nuevos partidos, indicando los equipos y la fecha
        public static void makeNewPartidos(){

            DAOLiga DAOLiga = new DAOLiga();
            DAOEquipo DAOEquipo = new DAOEquipo();
            DAOPartido DAOPartido = new DAOPartido();

            List<Partido> partidos = new ArrayList<>();
                          partidos.add(new Partido(LocalDate.of(2024, 1,6), 0, 0, DAOEquipo.getEquipo(1), DAOEquipo.getEquipo(2), DAOLiga.getLiga(1)));
                          partidos.add(new Partido(LocalDate.of(2024, 1,13), 0, 0, DAOEquipo.getEquipo(4), DAOEquipo.getEquipo(5), DAOLiga.getLiga(1)));
                          partidos.add(new Partido(LocalDate.of(2024, 1,20), 0, 0, DAOEquipo.getEquipo(1), DAOEquipo.getEquipo(2), DAOLiga.getLiga(4)));
                          partidos.add(new Partido(LocalDate.of(2024, 1,27), 0, 0, DAOEquipo.getEquipo(4), DAOEquipo.getEquipo(5), DAOLiga.getLiga(4)));

            for(int i=0; i<partidos.size(); i++){

                DAOPartido.insertPartido(partidos.get(i));

            }

        }


        //Método para asignar resultados a los partidos jugados
        public static void makeEditPartidos(){

            DAOPartido DAOPartido = new DAOPartido();

            Partido partido1 = DAOPartido.getPartido(1);
                    partido1.setGoles_equipo_local(3);
                    partido1.setGoles_equipo_visitante(2);
            DAOPartido.modifyPartidoResult(partido1);

            Partido partido2 = DAOPartido.getPartido(2);
                    partido2.setGoles_equipo_local(0);
                    partido2.setGoles_equipo_visitante(4);
            DAOPartido.modifyPartidoResult(partido2);

        }


        //Método para mostar el calendario de partidos
        public static void showCalendarPartidos(){

            DAOPartido DAOPartido = new DAOPartido();

            List<Partido> partidos = DAOPartido.getAllPartidos();

            //Imprimo el calendario en pantalla
            System.out.println("CALENDARIO DE PARTIDOS");
            for(int i=0; i<partidos.size(); i++){

                Partido partido = partidos.get(i);

                System.out.println(makeFormatFecha(partido.getFecha_partido())+" -> "+partido.getEquipoLocal().getNombre_equipo()+" ("+partido.getGoles_equipo_local()+") vs. "+partido.getEquipoVisitante().getNombre_equipo()+" ("+partido.getGoles_equipo_visitante()+") // "+partido.getLiga().getNombre_liga());
            }
        }

            //Método para formatear la fecha
            public static String makeFormatFecha(LocalDate fecha){

                // Crear un formateador de fecha personalizado
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("d/M/yyyy");

                // Formatear la fecha utilizando el formateador
                String fechaFormateada = fecha.format(formateador);

                return fechaFormateada;

            }


    //ACCIONES
        public static void makeAcciones(){

            //Creo los controladores
            DAOLiga DAOLiga = new DAOLiga();
            DAOEquipo DAOEquipo = new DAOEquipo();
            DAOPartido DAOPartido = new DAOPartido();

            //Realizo las diferentes acciones propuestas en el enunciado

                //- Crea 1 liga
                Liga liga = new Liga("Premier League", LocalDate.of(2023, 6,1), LocalDate.of(2024, 6, 1));
                DAOLiga.insertLiga(liga);


                //- Crea 8 equipos
                List<Equipo> equipos = new ArrayList<>();
                                equipos.add(new Equipo("Everton", "Liverpool", liga));
                                equipos.add(new Equipo("Crystal Palace", "Londes", liga));
                                equipos.add(new Equipo("Luton", "Luton", liga));
                                equipos.add(new Equipo("Manchester United", "Manchester", liga));
                                equipos.add(new Equipo("Sheffield United", "Sheffield", liga));
                                equipos.add(new Equipo("Brighton", "Brighton", liga));
                                equipos.add(new Equipo("Liverpool", "Liverpool", liga));
                                equipos.add(new Equipo("Chelsea", "Londres", liga));

                for(int i=0; i<equipos.size(); i++){
                    DAOEquipo.insertEquipo(equipos.get(i));
                }




                //- Crea 6 partidos
                List<Partido> partidos = new ArrayList<>();
                                partidos.add(new Partido(LocalDate.of(2023, 12, 30), 2, 3, equipos.get(2), equipos.get(7), liga));
                                partidos.add(new Partido(LocalDate.of(2024, 1, 6), 1, 0, equipos.get(0), equipos.get(3), liga));
                                partidos.add(new Partido(LocalDate.of(2023, 1, 13), 2, 2, equipos.get(4), equipos.get(6), liga));
                                partidos.add(new Partido(LocalDate.of(2023, 1, 20), 1, 3, equipos.get(1), equipos.get(2), liga));
                                partidos.add(new Partido(LocalDate.of(2023, 1, 27), 4, 2, equipos.get(3), equipos.get(6), liga));
                                partidos.add(new Partido(LocalDate.of(2023, 2, 3), 1, 1, equipos.get(7), equipos.get(2), liga));

                for(int i=0; i<partidos.size(); i++){
                    DAOPartido.insertPartido(partidos.get(i));
                }


                //- Muestra los datos de todos los equipos
               List<Equipo> listadoEquipos = DAOEquipo.getEquipos();

                System.out.println("LISTADO DE EQUIPOS");
                for(int i=0; i<listadoEquipos.size(); i++){

                    Equipo equipo = listadoEquipos.get(i);
                    System.out.println(equipo.getNombre_equipo()+" - "+equipo.getCiudad()+" / "+equipo.getLiga().getNombre_liga());

                }


                //- Elimina dos equipos
                DAOEquipo.deleteEquipo(1);
                DAOEquipo.deleteEquipo(2);


                //- Muestra todos los partidos de una liga concreta
                List<Partido> listadoPartidos = DAOPartido.getPartidos(liga);

                System.out.println("LISTADO DE PARTIDOS - "+liga.getNombre_liga());
                for(int i=0; i<listadoPartidos.size(); i++){

                    Partido partido = listadoPartidos.get(i);
                    System.out.println(makeFormatFecha(partido.getFecha_partido())+" -> "+partido.getEquipoLocal().getNombre_equipo()+" ("+partido.getGoles_equipo_local()+") vs. "+partido.getEquipoVisitante().getNombre_equipo()+" ("+partido.getGoles_equipo_visitante()+") // "+partido.getLiga().getNombre_liga());
                }

        }


}
