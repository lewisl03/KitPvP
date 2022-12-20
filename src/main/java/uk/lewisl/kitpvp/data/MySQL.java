package uk.lewisl.kitpvp.data;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;

import java.sql.*;
import java.util.UUID;
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

                KitPvp.logger(Level.INFO, "Connected to MySQL");

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

        sql = "CREATE TABLE IF NOT EXISTS `KitPvp` (" +
                "  `UUID` varchar(255) NOT NULL," +
                "  `Balance` bigint(255) NOT NULL DEFAULT 0," +
                "  `Kills` bigint(255) NOT NULL DEFAULT 0," +
                "  `Deaths` bigint(255) NOT NULL DEFAULT 0," +
                "  `Assists` bigint(255) NOT NULL DEFAULT 0," +
                "  PRIMARY KEY (`UUID`)" +
                ");";
         execute(sql);


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


    public void addKill(UUID uuid, long amount){
        sql = "UPDATE `KitPvp` SET `Kills` = `Kills`+"+amount+" WHERE `UUID`=  '"+uuid+"'";
        execute(sql);
    }
    public void addAssist(UUID uuid, long amount){
        sql = "UPDATE `KitPvp` SET `Assist` = `Assist`+"+amount+" WHERE `UUID`=  '"+uuid+"'";
        execute(sql);
    }
    public void addDeath(UUID uuid, long amount){
        sql = "UPDATE `KitPvp` SET `Deaths` = `Deaths`+"+amount+" WHERE `UUID`=  '"+uuid+"'";
        execute(sql);
    }
    public void addBalance(UUID uuid, long amount){
        sql = "UPDATE `KitPvp` SET `Balance` = `Balance`+"+amount+" WHERE `UUID`=  '"+uuid+"'";
        execute(sql);
    }
    public void removeBalance(UUID uuid, long amount){
        sql = "UPDATE `KitPvp` SET `Balance` = `Balance`-"+amount+" WHERE `UUID`=  '"+uuid+"'";
        execute(sql);
    }

    public void addPlayer(UUID uuid){
        sql = "INSERT INTO `KitPvp` (`UUID`) VALUES('"+uuid+"')";
        execute(sql);
    }

    //getting info scrips
    public long getBalance(UUID uuid){
        sql = "SELECT `Balance` FROM `KitPvp` WHERE `UUID`= '"+uuid+"'";
        ResultSet rs = executeQuery(sql);
        long balance = -1;

        try {
            if(!rs.last()){
                return -1;
            }
            balance = rs.getLong("Balance");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public long getKills(UUID uuid){
        sql = "SELECT `Kills` FROM `KitPvp` WHERE `UUID`= '"+uuid+"'";
        ResultSet rs = executeQuery(sql);
        long kills = -1;

        try {
            if(!rs.last()){
                return -1;
            }
            kills = rs.getLong("Kills");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kills;
    }

    public long getDeaths(UUID uuid){
        sql = "SELECT `Deaths` FROM `KitPvp` WHERE `UUID`= '"+uuid+"'";
        ResultSet rs = executeQuery(sql);
        long deaths = -1;

        try {
            if(!rs.last()){
                return -1;
            }
            deaths = rs.getLong("Deaths");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return deaths;
    }


    public boolean dataExist(UUID uuid){
        sql = "SELECT `count(*)` FROM `KitPvp` WHERE `UUID`= '"+uuid+"'";
        ResultSet rs = executeQuery(sql);

        int count = -1;
        if(rs != null){
            try {
                rs.last();
                count = rs.getInt("count(*)");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return count <= 0 ? false : true;
    }


    public PvPPlayer getPlayer(UUID uuid){
        sql = "SELECT `Balance`, `Kills`, `Deaths`, `Assists` FROM `KitPvp` WHERE `UUID` = '"+uuid+"'";
        ResultSet rs = executeQuery(sql);
        long bal = -1, kills = -1, deaths = -1, assist = -1;

        try {
            if(!rs.last()){
                return new PvPPlayer(uuid, 0, 0 ,0, 0);
            }
            bal = rs.getLong("Balance");
            kills = rs.getLong("Kills");
            deaths = rs.getLong("Deaths");
            assist = rs.getLong("Assists");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new PvPPlayer(uuid, bal, kills, deaths, assist);
    }

    public void savePlayer(UUID uuid){
        PvPPlayer p = KitPvp.dataManager.data.getOnlinePlayer(uuid);
        if(p == null){System.out.println("Error: Uncached player "+ Bukkit.getPlayer(uuid).getDisplayName());}

        sql = "INSERT INTO `KitPvp` (`UUID`, `Balance`, `Kills`, `Deaths`, `Assists`) VALUES" +
                " ('"+uuid+"', '"+p.getPlayerBalance()+"', '"+p.getKills()+"', '"+p.getDeaths()+"', '"+p.getAssists()+"')" +
                " ON DUPLICATE Key UPDATE `Balance`= '"+p.getPlayerBalance()+"', `Kills`= '"+p.getKills()+"', `Deaths`= '"+p.getDeaths()+"', '"+p.getAssists()+"'";
        execute(sql);


    }






}
