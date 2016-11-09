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
public enum WeaponFamily {
    
    SMG("SMG",Property.SMG_DAMAGE,Property.PERCENTAGE_SMG_DAMAGE),
    ASSAULT("ASR",Property.ASSAULT_DAMAGE,Property.PERCENTAGE_ASSAULT_DAMAGE),
    LMG("LMG",Property.LMG_DAMAGE,Property.PERCENTAGE_LMG_DAMAGE),
    SHOTGUN("SHG",Property.SHOTGUN_DAMAGE,Property.PERCENTAGE_SHOTGUN_DAMAGE),
    MARSKMAN("MMR",Property.MMR_DAMAGE,Property.PERCENTAGE_MMR_DAMAGE),
    PISTOL("PIS",Property.PISTOL_DAMAGE,Property.PERCENTAGE_PISTOL_DAMAGE);    
    
    
    private String value;
    private Property damageProperty;
    private Property percentageDamageProperty;
    
    private WeaponFamily(String value, Property damageProperty, Property percentageDamageProperty) {
        this.value = value;
        this.damageProperty = damageProperty;
        this.percentageDamageProperty = percentageDamageProperty;
    }

    @Override
    public String toString() {
        return value;
    }
    
    public Property getDamageProperty(){
        return damageProperty;
    }
    
    public Property getPercentageDamageProperty() {
        return percentageDamageProperty;
    }
    
}
