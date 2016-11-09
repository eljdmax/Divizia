/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import core.utils.Constants;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author tchabole
 */
public class Stats {
    
    
    private HashMap<Property,Float> params;
    private final static HashMap<String,Float> constants;
    
    static {
        
        constants = new HashMap<String,Float>();
        
        constants.put("Mitigation_Factor_WT1",0.0121951219512195F);
        constants.put("Mitigation_Factor_WT2",0.00783494384956907F);
        constants.put("Mitigation_Factor_WT3",0.00643362642075916F);
        constants.put("Mitigation_Factor_WT4",0.00518806744487678F);
        
        constants.put("Mitigation_Limit_WT1",60F);
        constants.put("Mitigation_Limit_WT2",60F);
        constants.put("Mitigation_Limit_WT3",60F);
        constants.put("Mitigation_Limit_WT4",70F);
        
        constants.put("ElectronicsToSkillPowerConversionRate",30F);
        constants.put("Stamina_To_Health",30F);
        constants.put("Signature_Resource_Gain_Offset",2F);
        
        
    }
    
    public Stats() {
        params = new HashMap<Property,Float>();
        
        for (Property bonus : Property.values()) {
            params.put(bonus, 0.0F);
        }
        
    }
    
    public Stats(Stats toClone) {
        
        params = new HashMap<Property,Float>();
        
        for (Property bonus : Property.values()) {
            params.put(bonus, toClone.getProp(bonus));
        }
        
    }
    
    public void mergeStats(Stats toMerge, List<Property> properties) {
        
        for (Property prop : properties) {
            this.addProp(prop, toMerge.getProp(prop));
        }
        
    }
    
    public void mergeStats(Stats toMerge) {
        
        for (Property bonus : Property.values()) {
            this.addProp(bonus, toMerge.getProp(bonus));
        }
        
    }
    
    public void subStats(Stats toSub, List<Property> properties){
        
        for (Property prop : properties) {
               this.removeProp(prop, toSub.getProp(prop));
        }
    }
    
    public void subStats(Stats toSub){
        
        for (Property bonus : Property.values()) {
            this.removeProp(bonus, toSub.getProp(bonus));
        }
    }
    
    
    public String displayMainStats() {
        StringBuilder ret = new StringBuilder();
        
        ret.append(Float.toString(this.params.get(Property.ARMOR))).append(" ")
           .append(Float.toString(this.params.get(Property.FIREARM))).append(Constants.SPACE)
           .append(Float.toString(this.params.get(Property.STAMINA))).append(Constants.SPACE)
           .append(Float.toString(this.params.get(Property.ELECTRONIC)));
        
        return ret.toString();
    }

    public Float getProp(Property property)  {

        return this.params.get(property);
    }
    
    
    public void addProp(Property property, Float value) {
        
        params.put(property, params.get(property) + value);
    }
    
    public void removeProp(Property property, Float value)   {

        params.put(property, params.get(property) - value);
        
    }
    
    public void addBonus(PropValue bonus) {
        addProp(bonus.getProperty(), bonus.getValue());
    }
    
    public void removeBonus(PropValue bonus){
        removeProp(bonus.getProperty(), bonus.getValue());
    }
    
    public Float getHealth() {
        return (params.get(Property.STAMINA) * constants.get("Stamina_To_Health")) + params.get(Property.HEALTH);
    }
    
    public Float getMitigation(int worldTier){
        
        String factor = "Mitigation_Factor_WT"+Integer.toString(worldTier);
        String limit = "Mitigation_Limit_WT"+Integer.toString(worldTier);
        
        return Math.min( constants.get(limit) , constants.get(factor) * params.get(Property.ARMOR) );
    }
    
    public Float getSkillPower(){
        return ( params.get(Property.ELECTRONIC)*constants.get("ElectronicsToSkillPowerConversionRate")+ params.get(Property.SKILL_POWER) ) *
               (1F + params.get(Property.PERCENTAGE_SKILL_POWER));
    }

    public Float getThoughness(int enemyType, int worldTier) {
        // 0 -> base thoughness
        // 1 -> Vs Elites
        // 2 -> Vs Exotic
        
        
        switch (enemyType) {
            case 0:
                return 100F*getHealth()/(100F - getMitigation(worldTier));
                
            case 1:
                return 10000F * getHealth()/( (100F - getMitigation(worldTier)) * (100F - params.get(Property.PROTECTION_VS_ELITES)) );
                
            case 2:
                return 100F*getHealth()/(100F - params.get(Property.EXOTIC_DAMAGE_RESILIENCE));
        }
        
        return -1F;
    }
    
    
}

