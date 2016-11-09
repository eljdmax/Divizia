/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.build;

import core.components.GearSet;
import core.components.Property;
import core.components.Stats;
import core.utils.Constants;
import java.util.HashMap;

/**
 *
 * @author tchabole
 */
public class FullBuild {
    
    private String name;
    
    private ModdedWeapon weapon1;
    private ModdedWeapon weapon2;
    
    private ModdedGear backpack;
    private ModdedGear bodyArmor;
    private ModdedGear gloves;
    private ModdedGear holster;
    private ModdedGear kneepads;
    private ModdedGear mask;
    
    private Stats stats;
    private HashMap<GearSet, Integer> gearsetCounts;
    
    public FullBuild() {
        this("Tmp Build");
    }

    public FullBuild(String name) {
        this(name,null,null,null,null,null,null,null,null);
    }
    
    public FullBuild(String name, ModdedGear backpack, ModdedGear bodyArmor, ModdedGear gloves, ModdedGear holster, ModdedGear kneepads, ModdedGear mask) {
        this(name,null,null,backpack,bodyArmor,gloves,holster,kneepads,mask);
    }
    
    public FullBuild(String name, ModdedWeapon weapon1, ModdedWeapon weapon2, ModdedGear backpack, ModdedGear bodyArmor, ModdedGear gloves, ModdedGear holster, ModdedGear kneepads, ModdedGear mask) {
        this.name = name;
        
        this.stats = new Stats();
        this.gearsetCounts = new HashMap<GearSet, Integer>();
        
        //base Values
        this.stats.addProp(Property.FIREARM, 535F);
        this.stats.addProp(Property.STAMINA, 535F);
        this.stats.addProp(Property.ELECTRONIC, 535F);
        
        this.weapon1 = weapon1;
        this.weapon2 = weapon2;
        
        Integer count;
        
        this.backpack = backpack;
        this.stats.mergeStats(backpack.getFullStats());
        count = gearsetCounts.putIfAbsent(backpack.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(backpack.getGear().getGearSet(), count + 1);
        }
        
        this.bodyArmor = bodyArmor;
        this.stats.mergeStats(bodyArmor.getFullStats());
        count = gearsetCounts.putIfAbsent(bodyArmor.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(bodyArmor.getGear().getGearSet(), count + 1);
        }
        
        this.gloves = gloves;
        this.stats.mergeStats(gloves.getFullStats());
        count = gearsetCounts.putIfAbsent(gloves.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(gloves.getGear().getGearSet(), count + 1);
        }
        
        this.holster = holster;
        this.stats.mergeStats(holster.getFullStats());
        count = gearsetCounts.putIfAbsent(holster.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(holster.getGear().getGearSet(), count + 1);
        }
        
        this.kneepads = kneepads;
        this.stats.mergeStats(kneepads.getFullStats());
        count = gearsetCounts.putIfAbsent(kneepads.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(kneepads.getGear().getGearSet(), count + 1);
        }
        
        this.mask = mask;
        this.stats.mergeStats(mask.getFullStats());
        count = gearsetCounts.putIfAbsent(mask.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(mask.getGear().getGearSet(), count + 1);
        }
        

    }
    
    
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        
        ret.append(backpack.toString()).append(Constants.NEWLINE)
           .append(bodyArmor.toString()).append(Constants.NEWLINE)
           .append(gloves.toString()).append(Constants.NEWLINE)
           .append(holster.toString()).append(Constants.NEWLINE)
           .append(kneepads.toString()).append(Constants.NEWLINE)     
           .append(mask.toString());
        
        ret.append(Constants.NEWLINE);
        
        ret.append("Total :").append(this.stats.displayMainStats());
        
        return ret.toString();
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ModdedWeapon getWeapon1() {
        return weapon1;
    }

    public void setWeapon1(ModdedWeapon weapon1) {
        this.weapon1 = weapon1;
    }
    
    public void removeWeapon1() {
        this.weapon1 = null;
    }

    public ModdedWeapon getWeapon2() {
        return weapon2;
    }

    public void setWeapon2(ModdedWeapon weapon2) {
        this.weapon2 = weapon2;
    }
    
    public void removeWeapon2() {
        this.weapon2 = null;
    }

    public ModdedGear getBackpack() {
        return backpack;
    }

    public void setBackpack(ModdedGear backpack) {
        removeBackpack();
        this.backpack = backpack;
        
        Integer count = gearsetCounts.putIfAbsent(backpack.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(backpack.getGear().getGearSet(), count + 1);
        }
        
    }
    
    public void removeBackpack() {
        gearsetCounts.put(this.backpack.getGear().getGearSet(), gearsetCounts.get(this.backpack.getGear().getGearSet())  - 1);
        this.backpack = null;
    }

    public ModdedGear getBodyArmor() {
        return bodyArmor;
    }

    public void setBodyArmor(ModdedGear bodyArmor) {
        removeBodyArmor();
        this.bodyArmor = bodyArmor;
        
        Integer count = gearsetCounts.putIfAbsent(bodyArmor.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(bodyArmor.getGear().getGearSet(), count + 1);
        }
    }
    
    public void removeBodyArmor() {
        gearsetCounts.put(this.bodyArmor.getGear().getGearSet(), gearsetCounts.get(this.bodyArmor.getGear().getGearSet())  - 1);
        this.bodyArmor = null;
    }

    public ModdedGear getGloves() {
        return gloves;
    }

    public void setGloves(ModdedGear gloves) {
        removeGloves();
        this.gloves = gloves;
        
        Integer count = gearsetCounts.putIfAbsent(gloves.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(gloves.getGear().getGearSet(), count + 1);
        }
    }
    
    public void removeGloves() {
        gearsetCounts.put(this.gloves.getGear().getGearSet(), gearsetCounts.get(this.gloves.getGear().getGearSet())  - 1);
        this.gloves = null;
    }

    public ModdedGear getHolster() {
        return holster;
    }

    public void setHolster(ModdedGear holster) {
        removeHolster();
        this.holster = holster;
        
        Integer count = gearsetCounts.putIfAbsent(holster.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(holster.getGear().getGearSet(), count + 1);
        }
    }
    
    public void removeHolster() {
        gearsetCounts.put(this.holster.getGear().getGearSet(), gearsetCounts.get(this.holster.getGear().getGearSet())  - 1);
        this.holster = null;
    }

    public ModdedGear getKneepads() {
        return kneepads;
    }

    public void setKneepads(ModdedGear kneepads) {
        removeKneepads();
        this.kneepads = kneepads;
        
        Integer count = gearsetCounts.putIfAbsent(kneepads.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(kneepads.getGear().getGearSet(), count + 1);
        }
    }
    
    public void removeKneepads() {
        gearsetCounts.put(this.kneepads.getGear().getGearSet(), gearsetCounts.get(this.kneepads.getGear().getGearSet())  - 1);
        this.kneepads = null;
    }


    public ModdedGear getMask() {
        return mask;
    }

    public void setMask(ModdedGear mask) {
        removeMasks();
        this.mask = mask;
        
        Integer count = gearsetCounts.putIfAbsent(mask.getGear().getGearSet(), 0);
        if (count != null) {
            gearsetCounts.put(mask.getGear().getGearSet(), count + 1);
        }
    }    
    
    public void removeMasks() {
        gearsetCounts.put(this.mask.getGear().getGearSet(), gearsetCounts.get(this.mask.getGear().getGearSet())  - 1);
        this.mask = null;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void updateSetBonuses() {
       
            
    }

    
    //skills ? talents ?
    
}
