/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components.weapontalents;

import core.components.Property;
import core.components.Stats;
import core.components.WeaponTalent;

/**
 *
 * @author tchabole
 */
public class Deadly extends WeaponTalent {
    
    public Deadly(Float value1, Float value2) {
        
        super("Deadly", 2000F, 2000F, 0F,  value1,  value2);
    }
    
    @Override
    public boolean applyTalent(Stats stats) {
        
        if (this.isActivated(stats))  {
            stats.addProp(Property.CRITICAL_HIT_DAMAGE, this.value1);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean removeTalent(Stats stats) {
        
        if (this.isActivated(stats))  { //??
            stats.removeProp(Property.CRITICAL_HIT_DAMAGE, this.value1);
            return true;
        }
        
        return false;
    }
    
}
