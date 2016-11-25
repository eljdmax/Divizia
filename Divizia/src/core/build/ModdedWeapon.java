/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.build;

import core.components.Gear;
import core.components.GearMod;
import core.components.ModType;
import core.components.PropValue;
import core.components.Property;
import core.components.Stats;
import core.components.Weapon;
import core.components.WeaponFamily;
import core.components.WeaponMod;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author tchabole
 */
public class ModdedWeapon {
    
    private final static Float ONEISNONE_RECCURSIVE = 20F;
    private final static Float MAX_CRITICAL_CHANCE = 0.6F;
    
    private String id = null;
    
    private Weapon weapon;
    private List<WeaponMod> mods;
    private HashMap<WeaponMod, ModType> modsPosition;
    
    private Stats modStats;
    
    // Computed numbers 
    private Float adjustedDamage = 0F;
    private Float damageToElites = 0F;
    private Float damageEnemyArmor = 0F;
    private Float percCriticalDamage = 0F;
    private Float criticalDamage = 0F;
    private Float percHeadshotDamage = 0F;
    private Float headshotDamage = 0F;
    private Float critHeadshotDamage = 0F;
    private Float RPM = 0F;
    private Long mag = 0L;
    private Float reloadSpeed = 0F;
    private Float fullBodyshotDmg = 0F;
    private Float percCriticalChance = 0F;
    private Float fullBodyshotAccurateDmg = 0F;
    private Float fullHeadshotDmg = 0F;
    private Float fullHeadshotAccurateDmg = 0F;
    private Float fullCritBodyshotDmg = 0F;
    private Float fullCritBodyshotAccurateDmg = 0F;
    private Float fullCritHeadshotDmg = 0F;
    private Float fullCritHeadshotAccurateDmg = 0F;
    private Float timeDumpMag = 0F;
    private Float sustainedBodyShotDmg = 0F;
    private Float sustainedHeadShotDmg = 0F;
    private Float sustainedDPS = 0F;
    
    public ModdedWeapon() {
        this(null) ;
    }
    
    public ModdedWeapon(Weapon weapon) {
        this.weapon = weapon;
        
        mods = new ArrayList<WeaponMod>() ;
        modsPosition = new HashMap<WeaponMod,ModType>() ;
        modStats = new Stats();
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void clearMods() {
        for (WeaponMod weaponMod : mods) {
            for (PropValue prop : weaponMod.getBonus()) {
                modStats.removeBonus(prop);
            }
        }
        
        modsPosition.clear();
        mods.clear();
    }
    
    public void addMod( WeaponMod weaponMod, ModType pos) {
        //safeguard!!
        
        if (weaponMod == null) {
            return;
        }
        
        mods.add(weaponMod);
        
        for (PropValue prop : weaponMod.getBonus()) {
            modStats.addBonus(prop);
        }
        
        modsPosition.put(weaponMod,pos);
    }
    
    public boolean removeMod(WeaponMod weaponMod){
 
        if (mods.remove(weaponMod)) {
            for (PropValue prop : weaponMod.getBonus()) {
                modStats.removeBonus(prop);
            }
            modsPosition.remove(weaponMod);
            return true;
        }
        
        return false;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    public Weapon getWeapon() {
        return weapon;
    }

    public List<WeaponMod> getMods() {
        return mods;
    }
    
    public HashMap<WeaponMod, ModType> getModsPosition() {
        return modsPosition;
    }

    public Stats getModStats() {
        return modStats;
    }

    public String displayText() {
        String ret;
        
        ret = "<html>" + weapon.toString() + "<br />"
            + "</html>"; //+ talents
        
        return ret;
    }
    
    @Override
    public String toString() {
        String ret;
        
        ret = "<html>" + Integer.toString(this.weapon.getGearScore()) + " "+this.weapon.getWeaponType().name() + "<br />"
            + this.weapon.getBaseDamage().toString()
            + "</html>";
        
        return ret;
    }
    
    public String printAllDetails() {
        String ret = "Modded "+ weapon.toString();
        
        ret += "\n   Modds:";
        for (WeaponMod mod : mods) {
            ret += "\n\t" + mod.toString();
        }
        
        return ret;
    }
    
    public Float getAdjustedDamage() {
        return adjustedDamage;
    }

    public Float getDamageToElites() {
        return damageToElites;
    }

    public Float getDamageEnemyArmor() {
        return damageEnemyArmor;
    }

    public Float getPercCriticalDamage() {
        return percCriticalDamage;
    }

    public Float getCriticalDamage() {
        return criticalDamage;
    }

    public Float getPercHeadshotDamage() {
        return percHeadshotDamage;
    }

    public Float getHeadshotDamage() {
        return headshotDamage;
    }

    public Float getCritHeadshotDamage() {
        return critHeadshotDamage;
    }

    public Float getRPM() {
        return RPM;
    }

    public Long getMag() {
        return mag;
    }

    public Float getTimeDumpMag() {
        return timeDumpMag;
    }

    public Float getReloadSpeed() {
        return reloadSpeed;
    }

    public Float getFullBodyshotDmg() {
        return fullBodyshotDmg;
    }

    public Float getPercCriticalChance() {
        return percCriticalChance;
    }

    public Float getFullBodyshotAccurateDmg() {
        return fullBodyshotAccurateDmg;
    }

    public Float getFullHeadshotDmg() {
        return fullHeadshotDmg;
    }

    public Float getFullHeadshotAccurateDmg() {
        return fullHeadshotAccurateDmg;
    }

    public Float getFullCritBodyshotDmg() {
        return fullCritBodyshotDmg;
    }

    public Float getFullCritBodyshotAccurateDmg() {
        return fullCritBodyshotAccurateDmg;
    }

    public Float getFullCritHeadshotDmg() {
        return fullCritHeadshotDmg;
    }

    public Float getFullCritHeadshotAccurateDmg() {
        return fullCritHeadshotAccurateDmg;
    }

    public Float getSustainedBodyShotDmg() {
        return sustainedBodyShotDmg;
    }

    public Float getSustainedHeadShotDmg() {
        return sustainedHeadShotDmg;
    }

    public Float getSustainedDPS() {
        return sustainedDPS;
    }

    
    
    public Stats evaluate(Stats stats , Float pulseCritChance, Float pulseCritDmg, boolean oneIsNone, Float headshotAccuracy ) {
        Stats ret = weapon.applyTalents(stats);
        ret.mergeStats(modStats);
        
        setAdjustedDamage(ret);
        setDamageToElites(ret);
        setDamageEnemyArmor(ret);
        
        setPercCriticalDamage(ret,pulseCritDmg);
        setCriticalDamage();
        
        setPercHeadshotDamage(ret);
        setHeadshotDamage();
        
        setCritHeadshotDamage();
        setRPM(ret);
        setMag(ret, oneIsNone, headshotAccuracy);
        setTimeDumpMag();
        setReloadSpeed(ret);
        
        setPercCriticalChance(ret,pulseCritChance);
        
        setFullBodyshotDmg(headshotAccuracy);
        setFullHeadshotDmg(headshotAccuracy);
        
        setFullCritBodyshotDmg(headshotAccuracy);
        setFullCritHeadshotDmg(headshotAccuracy);
        
        setSustainedDmg();
        
        return ret;
    }
    
    private void setAdjustedDamage(Stats stats) {
        
        /*
        System.out.println("Weapon Base Damage: " + weapon.getBaseDamage());
        System.out.println("Firearm: " + stats.getProp(Property.FIREARM));
        System.out.println("Weapon Type Scaling: " + weapon.getWeaponType().getValue());
        System.out.println("Weapon Family Damage: " + stats.getProp(weapon.getWeaponFamily().getDamageProperty()));
        
        
        System.out.println("Weapon Damage Bonus: " + stats.getProp(Property.WEAPON_DAMAGE));
        System.out.println("Weapon Family Percentange Damage: " + stats.getProp(weapon.getWeaponFamily().getPercentageDamageProperty()));
        System.out.println("Out Of Cover Damage Bonus: " + stats.getProp(Property.OUT_OF_COVER_DAMAGE));
        */
        
        this.adjustedDamage = ( (weapon.getBaseDamage() + (stats.getProp(Property.FIREARM)*weapon.getWeaponType().getDamageScaling()) + stats.getProp(weapon.getWeaponType().getWeaponFamily().getDamageProperty()) )  *
                 ( 1F + stats.getProp(Property.WEAPON_DAMAGE)  +  stats.getProp(weapon.getWeaponType().getWeaponFamily().getPercentageDamageProperty())  )  
                ) * (  1F + stats.getProp(Property.OUT_OF_COVER_DAMAGE)/100F) ;
    
    }
    
    //TODO: add set bonuses
    private void setDamageToElites(Stats stats) {
        //System.out.println("Damage VS Elits: " + stats.getProp(Property.DAMAGE_VS_ELITES));
        
        this.damageToElites =  this.adjustedDamage * (1F + stats.getProp(Property.DAMAGE_VS_ELITES)/100F );
    }
    
    //TODO: add set bonuses
    private void setDamageEnemyArmor(Stats stats) {
        //System.out.println("Enemy Armor Damage: " + stats.getProp(Property.ENEMY_ARMOR_DAMAGE));
        
        this.damageEnemyArmor = this.damageToElites * (1F + stats.getProp(Property.ENEMY_ARMOR_DAMAGE)/100F );
    }
    
    //TODO: add set critical damage bonuses
    private void setPercCriticalDamage(Stats stats, Float pulseCritDmg){
        //System.out.println("Critical Hit Damage: " + (stats.getProp(Property.CRITICAL_HIT_DAMAGE) +  pulseCritDmg ));   
        
        this.percCriticalDamage = (stats.getProp(Property.CRITICAL_HIT_DAMAGE)+ pulseCritDmg)/ 100F;
    }
    
    private void setCriticalDamage() {
        this.criticalDamage  = this.damageEnemyArmor * (1F +  this.percCriticalDamage) ;
    }
     
    //TODO: add set headshot damage bonuses
    private void setPercHeadshotDamage(Stats stats){
        //System.out.println("headshot Damage: " + (stats.getProp(Property.HEADSHOT_DAMAGE) ));   
        //System.out.println("Gear bonus headshot Damage: " + (stats.getProp(Property.GEAR_HEADSHOT_DAMAGE) )); 
        Float headShotScaling = 1F; 
        
        if (weapon.getWeaponType().getWeaponFamily() != WeaponFamily.MARSKMAN) {
            headShotScaling = weapon.getWeaponType().getHeadShotScaling();
        }
        
        this.percHeadshotDamage  = ( headShotScaling + stats.getProp(Property.HEADSHOT_DAMAGE)/100F ) * (1 + stats.getProp(Property.GEAR_HEADSHOT_DAMAGE)/100F);
    }
    
    private void setHeadshotDamage() {
        this.headshotDamage = this.damageEnemyArmor * this.percHeadshotDamage;
    }
    
    private void setCritHeadshotDamage() {
        this.critHeadshotDamage =  this.damageEnemyArmor * ( this.percCriticalDamage + this.percHeadshotDamage );
    }
    
    private void setRPM(Stats stats) {
       this.RPM  = weapon.getWeaponType().getRPM() * (1F + stats.getProp(Property.RATE_OF_FIRE)/100F) ;
    }
    
    private void setMag(Stats stats, boolean oneIsNone, Float headShotAccuracy) {
        
        Double normMag = (double) weapon.getWeaponType().getMag() * ( 1F + stats.getProp(Property.MAGAZINE_SIZE)/100F) ;
        
        if (oneIsNone) {
            Double adjAccuracy = (double) headShotAccuracy/200F;            
            
            Double coef = ( Math.pow( adjAccuracy,ONEISNONE_RECCURSIVE) - 1F) / ( adjAccuracy - 1F);
            
            normMag = normMag*coef;
            
        }
        
        this.mag = Math.round(Math.floor( normMag));
        
    }
    
    private void setReloadSpeed(Stats stats) {
        //System.out.println("Reload Speed: " + stats.getProp(Property.RELOAD_SPEED));
        Float normReloadSpeed = ( weapon.getWeaponType().getReloadSpeed()/1000F)/(1F + stats.getProp(Property.RELOAD_SPEED)/100F );
        
        if ( normReloadSpeed < 1F) {
            normReloadSpeed = 1F;
        }
        
        this.reloadSpeed = normReloadSpeed;
    }
    
    private void setPercCriticalChance(Stats stats, Float pulseCritChance) {
        
        Float critChance = ( pulseCritChance + stats.getProp(Property.CRITICAL_HIT_CHANCE) ) /100F ;
        
        if (critChance > MAX_CRITICAL_CHANCE) {
            critChance = MAX_CRITICAL_CHANCE;
        }
        this.percCriticalChance = critChance;
    }
    
    private void setFullBodyshotDmg(Float headshotAccuracy) {
        this.fullBodyshotDmg = this.mag*this.damageEnemyArmor*(1F - this.percCriticalChance);
        this.fullBodyshotAccurateDmg = this.fullBodyshotDmg * (1F - headshotAccuracy/100F);
    }
    
    
    private void setFullHeadshotDmg(Float headshotAccuracy) {
        this.fullHeadshotDmg = this.damageEnemyArmor * (   this.mag *(1F - this.percCriticalChance)  + ( this.percHeadshotDamage - 1F)  );
        this.fullHeadshotAccurateDmg =  this.damageEnemyArmor * (   this.mag *(1F - this.percCriticalChance)*headshotAccuracy/100F  + (this.percHeadshotDamage - 1F) );
    }
    
    
    private void setFullCritBodyshotDmg(Float headshotAccuracy) {
        this.fullCritBodyshotDmg = this.mag * this.damageEnemyArmor * this.percCriticalChance * ( 1F + this.percCriticalDamage );
        this.fullCritBodyshotAccurateDmg = this.fullCritBodyshotDmg * (1F - headshotAccuracy/100F);
    }
    
    
    private void setFullCritHeadshotDmg(Float headshotAccuracy) {
        this.fullCritHeadshotDmg = this.damageEnemyArmor * this.mag * this.percCriticalChance * (  this.percCriticalDamage  +  this.percHeadshotDamage  );
        this.fullCritHeadshotAccurateDmg = this.fullCritHeadshotDmg  * headshotAccuracy/100F ;
    }
    
    private void setTimeDumpMag() {
        this.timeDumpMag = 60F * (this.mag / this.RPM);
    }
   
    private void setSustainedDmg() {
        this.sustainedBodyShotDmg = (this.fullBodyshotDmg + this.fullCritBodyshotDmg) / (this.reloadSpeed + this.timeDumpMag);
        this.sustainedHeadShotDmg = (this.fullHeadshotDmg + this.fullCritHeadshotDmg) / (this.reloadSpeed + this.timeDumpMag);
        
        this.sustainedDPS = ( this.fullBodyshotAccurateDmg + this.fullHeadshotAccurateDmg +  this.fullCritBodyshotAccurateDmg + this.fullCritHeadshotAccurateDmg ) / (this.reloadSpeed + this.timeDumpMag);
    }
    
    
}
