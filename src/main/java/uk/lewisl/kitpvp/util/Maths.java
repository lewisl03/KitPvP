package uk.lewisl.kitpvp.util;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import uk.lewisl.kitpvp.types.RLocation;
import uk.lewisl.kitpvp.types.Region;

import java.text.DecimalFormat;
import java.util.Arrays;

public class Maths {
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static String withSuffix(long count) {
        if (count < 1000) return "" + count;
        int exp = (int) (Math.log(count) / Math.log(1000));
        return String.format("%.1f %c",
                count / Math.pow(1000, exp),
                "kMGTPE".charAt(exp-1));
    }

    public static String longComma(long amount){
        DecimalFormat formatter = new DecimalFormat("#,###");
        return formatter.format(amount);

    }

    public static RLocation LoctoRLoc(Location loc){
        return new RLocation(loc.getWorld().getName(),  loc.getX(), loc.getY(), loc.getZ());
    }

    public static boolean isInRect(Player player, Region region)
    {
        double[] dim = new double[2];

        dim[0] = region.getPos1().getX();
        dim[1] = region.getPos2().getX();
        Arrays.sort(dim);
        if(player.getLocation().getX() > dim[1] || player.getLocation().getX() < dim[0])
            return false;

        dim[0] = region.getPos1().getZ();
        dim[1] = region.getPos2().getZ();
        Arrays.sort(dim);
        if(player.getLocation().getZ() > dim[1] || player.getLocation().getZ() < dim[0])
            return false;

        //cba to do Y

        return true;
    }

    public static double getKDR(long kills, long deaths){
        return (double) kills == 0 ?(double) deaths : (double)deaths == 0 ? (double)kills : (double)kills/(double)deaths;
    }





}
