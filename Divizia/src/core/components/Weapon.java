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
    
    private WeaponFamily weaponFamily;
    private WeaponType weaponType;
    private PropValue weaponBonus;
    
    private Float baseDamage;

    private WeaponTalent mainTalent;
    
    private List<WeaponTalent> extraTalents;
    
    public Weapon(WeaponFamily weaponFamily, WeaponType weaponType, Float baseDamage, WeaponTalent mainTalent) {
        this(weaponFamily, weaponType, baseDamage, mainTalent, null);
    }
    
    public Weapon(WeaponFamily weaponFamily, WeaponType weaponType, Float baseDamage, WeaponTalent mainTalent, PropValue weaponBonus) {
        this.weaponFamily = weaponFamily;
        this.weaponType = weaponType;
        this.baseDamage = baseDamage;
        this.mainTalent = mainTalent;
        this.mainTalent.setAlwaysActived(true);
        
        this.weaponBonus = weaponBonus;
        
        this.extraTalents = new ArrayList<WeaponTalent>();
    }

    public void addTalent(WeaponTalent talent) {
        this.extraTalents.add(talent);
    }
    
    public WeaponFamily getWeaponFamily() {
        return weaponFamily;
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
    
    public Stats applyTalents(Stats stats){
        
        Stats ret = new Stats(stats);
        
        if (weaponBonus != null) {
            ret.addBonus(weaponBonus);
        }
        
        this.mainTalent.applyTalent(ret);
        
        for (WeaponTalent wt : this.extraTalents) {
            wt.applyTalent(ret);
        }
        
        return ret;
    }
    
    
    
}
