package database;

public interface SchemaDB {

    String DB_NAME="AD_ligas";
    String SERVER="127.0.0.1:3306";
    String USER="root";
    String PASSWORD="";

    String TAB_LIGAS="Ligas";
    String TAB_EQUIPOS="Equipos";
    String TAB_PARTIDOS="Partido";

    String COL_IDLIGA="id_liga";
    String COL_NOMBRELIGA="nombre_liga";
    String COL_FECHAINICIO="fecha_inicio";
    String COL_FECHAFIN="fecha_fin";
    String COL_IDEQUIPO="id_equipo";
    String COL_NOMBREEQUIPO="nombre_equipo";
    String COL_CIUDAD="ciudad";
    String COL_IDPARTIDO="id_partido";
    String COL_FECHAPARTIDO="fecha_partido";
    String COL_GOLESEQUIPOLOCAL="goles_equipo_local";
    String COL_GOLESEQUIPOVISITANTE="goles_equipo_visitante";
    String COL_IDEQUIPOLOCAL="id_equipo_local";
    String COL_IDEQUIPOVISITANTE="id_equipo_visitante";


}
