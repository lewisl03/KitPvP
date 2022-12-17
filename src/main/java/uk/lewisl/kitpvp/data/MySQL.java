package uk.lewisl.kitpvp.data;

import org.bukkit.Bukkit;
import uk.lewisl.kitpvp.KitPvp;

import java.sql.*;
import java.util.logging.Level;

public class MySQL {

    private Connection conn = null;
    private PreparedStatement preparedStatement = null;
    private String sql;


    private Connection createConn(){


        try {

            conn = DriverManager.getConnection("jdbc:mysql://"+
                            KitPvp.configManager.getConfig().getString("mysql.host") +":"+
                            KitPvp.configManager.getConfig().getInt("mysql.port")+"/"+
                            KitPvp.configManager.getConfig().getString("mysql.database")+
                            "?autoReconnect=true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",

                    KitPvp.configManager.getConfig().getString("mysql.username"),
                    KitPvp.configManager.getConfig().getString("mysql.password"));


            if(conn == null && conn.isClosed()){
                KitPvp.logger(Level.SEVERE, "Unable to connect to MySQL, Shutting plugin down");
                KitPvp.getPlugin().getPluginLoader().disablePlugin(KitPvp.getPlugin());
            }else{

            }

            return conn;
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        System.out.println("Unable to connect to database...");
        System.exit(0);
        return null;
    }


    public Connection getConn(){

        try {
            conn = (conn == null ? createConn() : conn.isClosed() ? createConn() : conn);
            if(conn == null && conn.isClosed()){
                KitPvp.logger(Level.SEVERE, "Unable to connect to MySQL, Shutting plugin down");
                Bukkit.getPluginManager().disablePlugin(KitPvp.getPlugin());

            }
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setup(){


        sql = "CREATE TABLE IF NOT EXISTS `PlayerBalance` (\n" +
                "`UUID` varchar(254) NOT NULL,\n" +
                "`Balance` int(254) NOT NULL" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;";
      //  execute(sql);

    }
    private void execute(String sql){
        try {
            preparedStatement = getConn().prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ResultSet executeQuery(String sql){
        try {
            preparedStatement = getConn().prepareStatement(sql,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);

            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



}
