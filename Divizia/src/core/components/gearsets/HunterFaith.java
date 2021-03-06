/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components.gearsets;

import core.components.GearSet;
import core.components.PropValue;
import core.components.Property;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class HunterFaith extends GearSet{
    
    private static HunterFaith singleton = new HunterFaith();
    
    private HunterFaith() {
        this.name = "Hunter's Faith";
    }
    
    public static HunterFaith getInstance() {
        return singleton;
    }
    
    //TODO: More complex bonuses 
    @Override
    public List<PropValue> getSetBonus(int pieces) {
        
        List<PropValue> ret = new ArrayList<PropValue>();
        switch (pieces) {
            case 6:
            case 5:
            case 4:
            case 3:
                ret.add(new PropValue(Property.GEAR_HEADSHOT_DAMAGE, 20F));
            case 2:
                ret.add(new PropValue(Property.GEAR_OPTIMAL_RANGE, 20F));
                break;
        }
        
        return ret;
    }
    
}
