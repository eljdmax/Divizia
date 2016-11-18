/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.utils;

import core.components.RecalibrationPosition;

/**
 *
 * @author tchabole
 */
public enum Constants {
    
    SPACE("|"),
    NEWLINE("\n");
    
    
    private String value;
    
    private Constants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
    
    public final static int MAX_MOD_ATTRIBUTES = 5;
    
    public final static int GEAR_MAJ = 1;
    public final static int GEAR_SKI = 2;
    public final static int GEAR_MOD = 8;
    public final static int WEAPON_MOD = 8;
    public final static int GEAR_SET = 16;
    
    public final static int recalibrationList(RecalibrationPosition pos) {
        
        switch (pos) {
            case MAJOR_1 :
            case MAJOR_2 :
            case MAJOR_3 :
            case MAJOR_4 :
            case MINOR_1 :
            case MINOR_2 :
            case MINOR_3 :
            case MINOR_4 :    
                return GEAR_MAJ;
                
            case SKILL_1 :
            case SKILL_2 :
            case SKILL_3 :
                return GEAR_SKI;
        }
        
        return -1;
    }
    
}
