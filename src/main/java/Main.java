import controller.DAOEquipo;
import controller.DAOPartido;
import model.Equipo;
import model.Liga;
import controller.DAOLiga;
import model.Partido;

import java.time.LocalDate;


public class Main {

    public static void main (String[] args){

        String currentWorkingDirectory = System.getProperty("user.dir");
        System.out.println("Directorio de trabajo actual: " + currentWorkingDirectory);

        //1. Crea 1 liga
        Liga liga = new Liga("Liga Santander", LocalDate.of(2024,5,12),LocalDate.of(2023,4,23));
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

        //4. Muestra datos de todos los equipos



        //5. Elimina 2 equipos


        //6. Muestra todos los partidos de una liga concreta
    }
}
