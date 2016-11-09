/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components.gearsets;

import core.components.GearSet;
import core.components.PropValue;
import core.components.Property;

/**
 *
 * @author tchabole
 */
public class HunterFaith extends GearSet{
    
    private static HunterFaith singleton = new HunterFaith();
    
    private HunterFaith() {
        this.name = "Hunter Faith";
    }
    
    public static HunterFaith getInstance() {
        return singleton;
    }
    
    //TODO: More complex bonuses 
    public PropValue getSetBonus(int pieces) {
        
        switch (pieces) {
            case 2:
                return new PropValue(Property.GEAR_HEADSHOT_DAMAGE, 20F);
                    
        }
        
        return null;
    }
    
}
