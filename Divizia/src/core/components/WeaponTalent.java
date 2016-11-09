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
    
    protected String name;
    
    protected Float value1;
    
    protected Float value2;
    
    protected Float requiredFA;
    protected Float requiredST;
    protected Float requiredEL;
    
    
    protected boolean alwaysActived;
    
    public WeaponTalent(String name,Float requiredFA,Float requiredST,Float requiredEL, Float value1, Float value2) {
        this.name = name;
        this.value1 = value1;
        this.value2 = value2;
        
        this.requiredFA = requiredFA;
        this.requiredST = requiredST;
        this.requiredEL = requiredEL;
        
        this.alwaysActived = false;
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
    
}
