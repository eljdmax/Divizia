/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import core.utils.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tchabole
 */
public abstract class Gear {
    
    protected GearSet gearSet;
    protected String shortName;
    
    protected Stats baseStats;

    protected List<PropValue> baseBonuses;
    
    protected Stats fullStats;
    
    
    public Gear(GearSet gearSet, Float baseArmor, Float baseFA, Float baseST, Float baseEL ) {
        this.gearSet = gearSet;
        this.baseStats = new Stats();
        
        this.baseStats.addProp(Property.ARMOR, baseArmor);
        this.baseStats.addProp(Property.FIREARM, baseFA);
        this.baseStats.addProp(Property.STAMINA, baseST);
        this.baseStats.addProp(Property.ELECTRONIC, baseEL);
        
        this.fullStats = new Stats(this.baseStats);
        
        this.baseBonuses = new ArrayList<PropValue>() ;
    }
    
    //public boolean canfit(Gear gear);
    
    public void addBonus(PropValue bonus) {
        fullStats.addBonus(bonus);
        this.baseBonuses.add(bonus);
    }
    
    public void removeBonus(PropValue bonus) {
        fullStats.removeBonus(bonus);
        this.baseBonuses.remove(bonus);
        
    }
    
    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        
        ret.append(this.gearSet.getName()).append(" ").append(this.shortName).append(" ")
           .append(this.fullStats.displayMainStats());
        
        return ret.toString();
    }

    public GearSet getGearSet() {
        return gearSet;
    }

    public void setGearSet(GearSet gearSet) {
        this.gearSet = gearSet;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public List<PropValue> getBaseBonuses() {
        return baseBonuses;
    }

    public void setBaseBonuses(List<PropValue> baseBonuses) {
        this.baseBonuses = baseBonuses;
    }

    public Stats getBaseStats() {
        return baseStats;
    }

    public Stats getFullStats() {
        return fullStats;
    }

}
