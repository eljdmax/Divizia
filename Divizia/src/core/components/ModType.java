/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import core.utils.Constants;

/**
 *
 * @author tchabole
 */
public enum ModType {
    
    //Weapon Mod Type
    MAGAZINE("MAG",Constants.WEAPON_MOD),
    MUZZLE("MUZ",Constants.WEAPON_MOD),
    OPTICS("OPT",Constants.WEAPON_MOD),
    UNDERBARREL("UND",Constants.WEAPON_MOD),
    
    //Gear Mod Type
    FIREARM("FA",Constants.GEAR_MOD),
    STAMINA("ST",Constants.GEAR_MOD),
    ELECTRONIC("EL",Constants.GEAR_MOD),
    
    //Perf Mod Type
    PULSE("PS",Constants.GEAR_SKI),
    STATION("SS",Constants.GEAR_SKI),
    SEEKER("SM",Constants.GEAR_SKI),
    TURRET("TU",Constants.GEAR_SKI),
    SHIELD("SH",Constants.GEAR_SKI),
    COVER("SC",Constants.GEAR_SKI);
    
    
    private String value;
    private int modFamily;
    
    private ModType(String value, int modFamily) {
        this.value = value;
        this.modFamily = modFamily;
    }

    @Override
    public String toString() {
        return value;
    }
    
    public int getModFamily() {
        return modFamily;
    }
}
