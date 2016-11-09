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
public enum ModType {
    
    //Weapon Mod Type
    MAGAZINE("MAG"),
    MUZZLE("MUZ"),
    OPTICS("OPT"),
    UNDERBARREL("UND"),
    
    //Gear Mod Type
    FIREARM("FA"),
    STAMINA("ST"),
    ELECTRONIC("EL"),
    
    //Perf Mod Type
    PULSE("PS"),
    STATION("SS"),
    SEEKER("SM"),
    TURRET("TU"),
    SHIELD("SH"),
    COVER("SC");
    
    
    private String value;
    
    private ModType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
