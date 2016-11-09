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
public enum RecalibrationPosition {
    
    NONE("N/A"),
    
    MAJOR_1("MA1"),
    MAJOR_2("MA2"),
    MAJOR_3("MA3"),
    MAJOR_4("MA4"),
    
    MINOR_1("MI1"),
    MINOR_2("MI2"),
    MINOR_3("MI3"),
    MINOR_4("MI4"),
    
    
    SKILL_1("SK1"),
    SKILL_2("SK2"),
    SKILL_3("SK3");
    
    private String value;
    
    private RecalibrationPosition(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
