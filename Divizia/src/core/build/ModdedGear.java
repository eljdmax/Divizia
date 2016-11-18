/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.build;

import core.components.Gear;
import core.components.GearMod;
import core.components.PropValue;
import core.components.Property;
import core.components.Stats;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class ModdedGear {
    private String id = null;
    
    private Gear gear;
    private List<GearMod> mods;
    
    private Stats fullStats;
    
    public ModdedGear() {
        mods = new ArrayList<GearMod>() ;
    }
    
    public ModdedGear(Gear gear) {
        this.gear = gear;
        
        this.fullStats = new Stats(gear.getFullStats());
        
        mods = new ArrayList<GearMod>() ;
    }
    
    public void addMod(GearMod gearMod) {
        //safeguard!!
        mods.add(gearMod);
        for (PropValue prop : gearMod.getBonus()) {
            fullStats.addBonus(prop);
        }
        //it's affect stats
    }
    
    public boolean removeMod(GearMod gearMod){
        //it's affect stats
        if ( mods.remove(gearMod) ) {
            for (PropValue prop : gearMod.getBonus()) {
                fullStats.removeBonus(prop);
            }
            return true;
        }
        
        return false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public Gear getGear() {
        return gear;
    }
    
    public void setGear(Gear gear) {
        this.gear = gear;
        this.fullStats = new Stats(gear.getFullStats());
    }

    public List<GearMod> getMods() {
        return mods;
    }

    public Stats getFullStats() {
        return fullStats;
    }
    
    
    
    @Override
    public String toString() {
        String ret;
        
        ret = "<html>" + this.gear.getGearSet().getName() + " "
            + this.fullStats.displayMainStats() 
            + "</html>";
        
        return ret;
    }
    
    public String displayText() {
        String ret;
        
        ret = "<html>" + this.gear.getGearSet().getName() + "<br />"
            + "Armor: " + this.fullStats.displayMainStats() 
            + "</html>";
        
        return ret;
    }
    
    public String printAllDetails() {
        String ret = "Modded "+ gear.printAllDetails();
        
        ret += "\n   Modds:";
        for (GearMod mod : mods) {
            ret += "\n\t" + mod.toString();
        }
        
        return ret;
    }
}
