package base_de_datos;
import io.github.cdimascio.dotenv.Dotenv;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class Conexion {
    //Primero definimos HikariDataSource que es el motor encargado de administrar la pool
    private static HikariDataSource dataSource;

    static {
        Dotenv dotenv = Dotenv.load(); //Encargada de extraer la variable de .env

        HikariConfig config = new HikariConfig();
        config.setDriverClassName("org.postgresql.Driver");
        //Definir conexión a base de datos NEON POSTGRESQL
        String urlNeon = dotenv.get("NEON_STRING");
        config.setJdbcUrl(dotenv.get("NEON_STRING"));
        config.setUsername(dotenv.get("DB_USER"));
        config.setPassword(dotenv.get("DB_PASS"));

        config.setMinimumIdle(2);
        config.setMaximumPoolSize(10);
        config.setConnectionTimeout(30000); //Tiempo de espera del usuario
        config.setIdleTimeout(100000);

        dataSource = new HikariDataSource(config);
    }

    public static Connection getConnection() throws SQLException{
        return dataSource.getConnection();
    }

    public static void cerrar(){
        if (dataSource != null) dataSource.close();
    }
}
