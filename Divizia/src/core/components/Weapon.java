/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class Weapon {
    
    private String id = null;
    
    private WeaponType weaponType;
    private PropValue weaponBonus;
    
    private Float baseDamage;

    private WeaponTalent mainTalent;
    
    private List<WeaponTalent> extraTalents;
    
    public Weapon(WeaponType weaponType, Float baseDamage, WeaponTalent mainTalent) {
        this(weaponType, baseDamage, mainTalent, null);
    }
    
    public Weapon(WeaponType weaponType, Float baseDamage, WeaponTalent mainTalent, PropValue weaponBonus) {
        this.weaponType = weaponType;
        this.baseDamage = baseDamage;
        this.mainTalent = mainTalent;
        
        if (this.mainTalent != null) {
            this.mainTalent.setAlwaysActived(true);
        }
        
        this.weaponBonus = weaponBonus;
        
        this.extraTalents = new ArrayList<WeaponTalent>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void addTalent(WeaponTalent talent) {
        this.extraTalents.add(talent);
    }
    

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public Float getBaseDamage() {
        return baseDamage;
    }

    public WeaponTalent getMainTalent() {
        return mainTalent;
    }

    public List<WeaponTalent> getExtraTalents() {
        return extraTalents;
    }

    public PropValue getWeaponBonus() {
        return weaponBonus;
    }
    
    public void setWeaponBonus(PropValue weaponBonus) {
        this.weaponBonus = weaponBonus;
    }
    
    public Stats applyTalents(Stats stats){
        
        Stats ret = new Stats(stats);
        
        if (weaponBonus != null) {
            ret.addBonus(weaponBonus);
        }
        
        if (mainTalent != null) {
            mainTalent.applyTalent(ret);
        }
        
        for (WeaponTalent wt : extraTalents) {
            wt.applyTalent(ret);
        }
        
        return ret;
    }
    
    @Override
    public String toString() {
        String ret = weaponType.name() + " " + baseDamage.toString() ;
        if (weaponBonus != null){
            ret += "\n\tBonus: " + weaponBonus.toString();
        }
        
        if (mainTalent != null){
            ret += "\n\tMain Talent: " + mainTalent.toString();
        }
        
        ret += "\n\tExtra Talents: ";
        for (WeaponTalent wt : extraTalents) {
            ret += "\n\t"+ wt.toString();
        }
        
        return ret;
    }
    
}
