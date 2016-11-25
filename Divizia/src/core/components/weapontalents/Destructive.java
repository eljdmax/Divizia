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
public class Destructive extends WeaponTalent {
    
    private static Destructive singleton = new Destructive();
    
    private Destructive() {
        super("Destructive",  2000F, 2000F, 0F, new Float[] {12F} );
    }
    
    public static Destructive getInstance() {
        return singleton;
    }
    
    @Override
    public boolean applyTalent(Stats stats) {
        
        if (this.isActivated(stats))  {
            stats.addProp(Property.ENEMY_ARMOR_DAMAGE, this.values[0]);
            return true;
        }
        
        return false;
    }
    
    @Override
    public boolean removeTalent(Stats stats) {
        
        if (this.isActivated(stats))  { //??
            stats.removeProp(Property.ENEMY_ARMOR_DAMAGE, this.values[0]);
            return true;
        }
        
        return false;
    }
    
}
