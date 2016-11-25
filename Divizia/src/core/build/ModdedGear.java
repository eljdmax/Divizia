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
import core.components.RecalibrationPosition;
import core.components.Stats;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class ModdedGear {
    private String id = null;
    
    private Gear gear;
    private List<GearMod> mods;
    private HashMap<GearMod,RecalibrationPosition> modsPosition;
    
    private Stats fullStats;
    
    public ModdedGear() {
        this(null);
    }
    
    public ModdedGear(Gear gear) {
        this.gear = gear;
        if (gear != null) {
            this.fullStats = new Stats(gear.getFullStats());
        }else {
            this.fullStats = new Stats();
        }
        mods = new ArrayList<GearMod>() ;
        modsPosition = new HashMap<GearMod,RecalibrationPosition>() ;
    }
    
    public void clearMods() {
        for (GearMod gearMod : mods) {
            for (PropValue prop : gearMod.getBonus()) {
                fullStats.removeBonus(prop);
            }
        }
        
        modsPosition.clear();
        mods.clear();
    }
    
    public void addMod(GearMod gearMod,RecalibrationPosition pos) {
        //safeguard!!
        mods.add(gearMod);
        for (PropValue prop : gearMod.getBonus()) {
            fullStats.addBonus(prop);
        }
        modsPosition.put(gearMod,pos);
        //it's affect stats
    }
    
    public boolean removeMod(GearMod gearMod){
        //it's affect stats
        if ( mods.remove(gearMod) ) {
            for (PropValue prop : gearMod.getBonus()) {
                fullStats.removeBonus(prop);
            }
            modsPosition.remove(gearMod);
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
        if (this.gear != null) {
            this.fullStats.subStats(this.gear.getFullStats());
        }
        this.gear = gear;
        this.fullStats.mergeStats(gear.getFullStats());
    }

    public List<GearMod> getMods() {
        return mods;
    }

    public Stats getFullStats() {
        return fullStats;
    }

    public HashMap<GearMod, RecalibrationPosition> getModsPosition() {
        return modsPosition;
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
