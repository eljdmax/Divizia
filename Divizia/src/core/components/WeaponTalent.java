/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

/**
 *
 * @author tchabole
 */
public abstract class WeaponTalent {
    
    protected String id = null;
    
    protected String name;
    
    protected Float[] values;
    
    protected Float requiredFA;
    protected Float requiredST;
    protected Float requiredEL;
    
    
    protected boolean alwaysActived;
    
    public WeaponTalent(String name,Float requiredFA,Float requiredST,Float requiredEL, Float[] values) {
        this.name = name;
        this.values = values;
        
        this.requiredFA = requiredFA;
        this.requiredST = requiredST;
        this.requiredEL = requiredEL;
        
        this.alwaysActived = false;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
     
    public String getName() {
        return name;
    }
    
    public void setAlwaysActived(boolean alwaysActived) {
        this.alwaysActived = alwaysActived;
    }
    
    public boolean isActivated(Stats stats) {
        return (this.alwaysActived || ( this.requiredFA <= stats.getProp(Property.FIREARM) &&
                                    this.requiredST <= stats.getProp(Property.STAMINA) &&
                                    this.requiredEL <= stats.getProp(Property.ELECTRONIC)) );
    }
    
    public abstract boolean applyTalent(Stats stats);
    
    public abstract boolean removeTalent(Stats stats);
    
    @Override
    public String toString() {
        return  name;
    }
    
    public String printAllDetails() {
        String ret = name + " ";
        
        for (int i=0; i < values.length ; i++) {
            ret += " // "+ values[i].toString();
        }
        
        return ret;
    }
    
}
