/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components.gear;

import core.components.Gear;
import core.components.GearSet;

/**
 *
 * @author tchabole
 */
public class Gloves extends Gear {

    public Gloves() {
        super();
        this.shortName = "gloves";
    }
    
    public Gloves(GearSet gearSet, Float baseArmor, Float baseFA, Float baseST, Float baseEL ) {
        super(gearSet, baseArmor, baseFA, baseST, baseEL );
        this.shortName = "gloves";
    }
    
}
