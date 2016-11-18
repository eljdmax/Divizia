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
    
    protected String id = null;
    
    protected GearSet gearSet;
    protected String shortName;
    
    protected Float armor = null;
    protected Float FA = null;
    protected Float ST = null;
    protected Float EL = null;
    
    
    protected Stats baseStats;

    protected List<PropValue> baseBonuses;
    
    protected Stats fullStats;
    
    public Gear() {
        this.baseStats = new Stats();
        this.fullStats = new Stats(this.baseStats);
        this.baseBonuses = new ArrayList<PropValue>() ;
    }
    
    public Gear(GearSet gearSet, Float baseArmor, Float baseFA, Float baseST, Float baseEL ) {
        this.gearSet = gearSet;
        
        this.armor = baseArmor;
        this.FA = baseFA;
        this.ST = baseST;
        this.EL = baseEL;
        
        this.baseStats = new Stats();
        
        this.baseStats.addProp(Property.ARMOR, baseArmor);
        this.baseStats.addProp(Property.FIREARM, baseFA);
        this.baseStats.addProp(Property.STAMINA, baseST);
        this.baseStats.addProp(Property.ELECTRONIC, baseEL);
        
        this.fullStats = new Stats(this.baseStats);
        
        this.baseBonuses = new ArrayList<PropValue>() ;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        
        ret.append("<html>").append(this.gearSet.getName()).append(" ")
           .append(this.fullStats.displayMainStats()).append("</html>");
        
        
        return ret.toString();
    }
    
    public String printAllDetails() {
        StringBuilder ret = new StringBuilder();
        
        ret.append(this.gearSet.getName()).append(" ").append(this.shortName).append(" ")
           .append(this.fullStats.displayMainStats());
        
        for (PropValue prop : baseBonuses) {
            ret.append("\n\t").append(prop.toString());
        }
        
        
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

    public Float getArmor() {
        return armor;
    }

    public void setArmor(Float armor) {
        if (this.armor != null){
            this.baseStats.removeProp(Property.ARMOR, this.armor);
            this.fullStats.removeProp(Property.ARMOR, this.armor);
        }
        this.armor = armor;
        this.baseStats.addProp(Property.ARMOR, armor);
        this.fullStats.addProp(Property.ARMOR, armor);
    }

    public Float getFA() {
        return FA;
    }

    public void setFA(Float FA) {
        if (this.FA != null){
            this.baseStats.removeProp(Property.FIREARM, this.FA);
            this.fullStats.removeProp(Property.FIREARM, this.FA);
        }
        this.FA = FA;
        this.baseStats.addProp(Property.FIREARM, FA);
        this.fullStats.addProp(Property.FIREARM, FA);
    }

    public Float getST() {
        return ST;
    }

    public void setST(Float ST) {
        if (this.ST != null){
            this.baseStats.removeProp(Property.STAMINA, this.ST);
            this.fullStats.removeProp(Property.STAMINA, this.ST);
        }
        this.ST = ST;
        this.baseStats.addProp(Property.STAMINA, ST);
        this.fullStats.addProp(Property.STAMINA, ST);
    }

    public Float getEL() {
        return EL;
    }

    public void setEL(Float EL) {
        if (this.EL != null){
            this.baseStats.removeProp(Property.ELECTRONIC, this.EL);
            this.fullStats.removeProp(Property.ELECTRONIC, this.EL);
        }
        this.EL = EL;
        this.baseStats.addProp(Property.ELECTRONIC, EL);
        this.fullStats.addProp(Property.ELECTRONIC, EL);
    }

    
    
    public Stats getBaseStats() {
        return baseStats;
    }

    public Stats getFullStats() {
        return fullStats;
    }
    
    
    
}