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
public class PropValue {
    
    private Property property;
    private Float value;
    private RecalibrationPosition recalibrationPosition;   

    public PropValue(Property property, Float value) {
        this(property,value,RecalibrationPosition.NONE);
    }
    
    public PropValue(Property property, Float value, RecalibrationPosition recalibrationPosition ) {
        this.property = property;
        this.value = value;
        this.recalibrationPosition = recalibrationPosition;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public RecalibrationPosition getRecalibrationPosition() {
        return recalibrationPosition;
    }

    public void setRecalibrationPosition(RecalibrationPosition recalibrationPosition) {
        this.recalibrationPosition = recalibrationPosition;
    }
    
    
    
}
