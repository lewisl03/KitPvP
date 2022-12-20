package uk.lewisl.kitpvp.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import uk.lewisl.kitpvp.KitPvp;
import uk.lewisl.kitpvp.types.PvPPlayer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DeathScreenCancel implements Listener {


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e)
    {
        final Player player = e.getEntity();
        KitPvp.getPlugin().getServer().getScheduler().scheduleSyncDelayedTask(KitPvp.getPlugin(), new Runnable()
        {
            public void run()
            {
                if(player.isDead())
                {


                    try {

                        String bukkitVersion = Bukkit.getServer().getClass()
                                .getPackage().getName().substring(23);

                        Class<?> cp = Class.forName("org.bukkit.craftbukkit."
                                + bukkitVersion + ".entity.CraftPlayer");
                        Class<?> clientCmd = Class.forName("net.minecraft.server."
                                + bukkitVersion + ".PacketPlayInClientCommand");
                        Class enumClientCMD = Class.forName("net.minecraft.server."
                                + bukkitVersion + ".PacketPlayInClientCommand$EnumClientCommand");

                        Method handle = cp.getDeclaredMethod("getHandle");

                        Object entityPlayer = handle.invoke(player);

                        Constructor<?> packetConstr = clientCmd
                                .getDeclaredConstructor(enumClientCMD);
                        Enum<?> num = Enum.valueOf(enumClientCMD, "PERFORM_RESPAWN");

                        Object packet = packetConstr.newInstance(num);

                        Object playerConnection = entityPlayer.getClass()
                                .getDeclaredField("playerConnection").get(entityPlayer);
                        Method send = playerConnection.getClass().getDeclaredMethod("a",
                                clientCmd);

                        send.invoke(playerConnection, packet);

                        player.teleport(KitPvp.dataManager.data.storage.getSpawn().getLocation(), PlayerTeleportEvent.TeleportCause.PLUGIN);

                    } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                            NoSuchMethodException | ClassNotFoundException | NoSuchFieldException ex) {
                        ex.printStackTrace();
                    }











/*

                    try
                    {
                        Object nmsPlayer = player.getClass().getMethod("getHandle").invoke(player);
                        Object packet = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".PacketPlayInClientCommand").newInstance();
                        Class<?> enumClass = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EnumClientCommand");

                        for(Object ob : enumClass.getEnumConstants()){
                            if(ob.toString().equals("PERFORM_RESPAWN")){
                                packet = packet.getClass().getConstructor(enumClass).newInstance(ob);
                            }
                        }

                        Object con = nmsPlayer.getClass().getField("playerConnection").get(nmsPlayer);
                        con.getClass().getMethod("a", packet.getClass()).invoke(con, packet);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    */
                }
            }
        });
    }



}
