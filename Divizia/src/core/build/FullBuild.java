/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.build;

import core.components.GearSet;
import core.components.PropValue;
import core.components.Property;
import core.components.Stats;
import core.utils.Constants;
import java.util.HashMap;

/**
 *
 * @author tchabole
 */
public class FullBuild {
    
    private String id = null;
    
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
        
        this.stats.addProp(Property.CRITICAL_HIT_DAMAGE, 25F);
        
        this.weapon1 = weapon1;
        this.weapon2 = weapon2;
        
        
        setBackpack(backpack);
        
        setBodyArmor(bodyArmor);
        
        setGloves(gloves);
        
        setHolster(holster);
        
        setKneepads(kneepads);
        
        setMask(mask);

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        
        ret.append(Constants.NEWLINE).append("Weapon 1: ");
        if (weapon1 != null) {
            ret.append(weapon1.toString());
        }
        
        ret.append(Constants.NEWLINE).append("Weapon 2: ");
        if (weapon2 != null) {
            ret.append(weapon2.toString());
        }
        
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

    public void updateBackpack(ModdedGear backpack) {
        removeBackpack();
        setBackpack(backpack);
    }    
    
    private void setBackpack(ModdedGear backpack) {

        this.backpack = backpack;
        if (backpack != null) {
            this.stats.mergeStats(backpack.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(backpack.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(backpack.getGear().getGearSet(), count + 1);
            }
        }
        
    }
    
    private void removeBackpack() {
        if (this.backpack != null) {
            this.stats.subStats(this.backpack.getFullStats());
            gearsetCounts.put(this.backpack.getGear().getGearSet(), gearsetCounts.get(this.backpack.getGear().getGearSet())  - 1);
        }
        this.backpack = null;
    }

    public ModdedGear getBodyArmor() {
        return bodyArmor;
    }

    public void updateBodyArmor(ModdedGear bodyArmor) {
        removeBodyArmor();
        setBodyArmor(bodyArmor);
    }
    
    private void setBodyArmor(ModdedGear bodyArmor) {
        this.bodyArmor = bodyArmor;
        if (bodyArmor != null) {
            this.stats.mergeStats(bodyArmor.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(bodyArmor.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(bodyArmor.getGear().getGearSet(), count + 1);
            }
        }
    }
    
    private void removeBodyArmor() {
        if (this.bodyArmor != null) {
            this.stats.subStats(this.bodyArmor.getFullStats());
            gearsetCounts.put(this.bodyArmor.getGear().getGearSet(), gearsetCounts.get(this.bodyArmor.getGear().getGearSet())  - 1);
        }
        this.bodyArmor = null;
    }

    public ModdedGear getGloves() {
        return gloves;
    }

    public void updateGloves(ModdedGear gloves) {
        removeGloves();
        setGloves(gloves);
    }
    
    private void setGloves(ModdedGear gloves) {
        this.gloves = gloves;
        if (gloves != null) {
            this.stats.mergeStats(gloves.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(gloves.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(gloves.getGear().getGearSet(), count + 1);
            }
        }
    }
    
    private void removeGloves() {
        if (this.gloves != null) {
            this.stats.subStats(this.gloves.getFullStats());
            gearsetCounts.put(this.gloves.getGear().getGearSet(), gearsetCounts.get(this.gloves.getGear().getGearSet())  - 1);
        }
        this.gloves = null;
    }

    public ModdedGear getHolster() {
        return holster;
    }

    public void updateHolster(ModdedGear holster) {
        removeHolster();
        setHolster(holster);
    }
    
    private void setHolster(ModdedGear holster) {
        this.holster = holster;
        if (holster != null) {
            this.stats.mergeStats(holster.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(holster.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(holster.getGear().getGearSet(), count + 1);
            }
        }
    }
    
    private void removeHolster() {
        if (this.holster != null) {
            this.stats.subStats(this.holster.getFullStats());
            gearsetCounts.put(this.holster.getGear().getGearSet(), gearsetCounts.get(this.holster.getGear().getGearSet())  - 1);
        }
        this.holster = null;
    }

    public ModdedGear getKneepads() {
        return kneepads;
    }
    
    public void updateKneepads(ModdedGear kneepads) {
        removeKneepads();
        setKneepads(kneepads);
    }

    private void setKneepads(ModdedGear kneepads) {
        this.kneepads = kneepads;
        if (kneepads != null) {
            this.stats.mergeStats(kneepads.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(kneepads.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(kneepads.getGear().getGearSet(), count + 1);
            }
        }
    }
    
    private void removeKneepads() {
        if (this.kneepads != null) {
            this.stats.subStats(this.kneepads.getFullStats());
            gearsetCounts.put(this.kneepads.getGear().getGearSet(), gearsetCounts.get(this.kneepads.getGear().getGearSet())  - 1);
        }
        this.kneepads = null;
    }


    public ModdedGear getMask() {
        return mask;
    }

    
    public void updateMask(ModdedGear mask) {
        this.removeMasks();
        this.setMask(mask);
    }
    
    private void setMask(ModdedGear mask) {
        this.mask = mask;
        if (mask != null) {
            this.stats.mergeStats(mask.getFullStats());
            Integer count = gearsetCounts.putIfAbsent(mask.getGear().getGearSet(), 0);
            if (count != null) {
                gearsetCounts.put(mask.getGear().getGearSet(), count + 1);
            }
        }
    }    
    
    private void removeMasks() {
        if (this.mask != null) {
            this.stats.subStats(this.mask.getFullStats());
            gearsetCounts.put(this.mask.getGear().getGearSet(), gearsetCounts.get(this.mask.getGear().getGearSet())  - 1);
        }
        this.mask = null;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public void updateSetBonuses() {
       
        for ( GearSet gearSet : gearsetCounts.keySet()) {
            for (PropValue bonus : gearSet.getSetBonus(gearsetCounts.get(gearSet)) ) {
                this.stats.addBonus(bonus);
            }
        }
        
    }

    
    //skills ? talents ?
    
}
