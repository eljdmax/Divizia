/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.build;

import core.components.Gear;
import core.components.GearMod;
import core.components.PropValue;
import core.components.Property;
import core.components.Stats;
import core.components.Weapon;
import core.components.WeaponMod;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author tchabole
 */
public class ModdedWeapon {
    private Weapon weapon;
    private List<WeaponMod> mods;
    
    private Stats modStats;
    
    
    public ModdedWeapon(Weapon weapon) {
        this.weapon = weapon;
        
        mods = new ArrayList<WeaponMod>() ;
        modStats = new Stats();
    }
    
    public void addMod( WeaponMod weaponMod) {
        //safeguard!!
        mods.add(weaponMod);
        
        for (PropValue prop : weaponMod.getBonus()) {
            modStats.addBonus(prop);
        }
    }
    
    public boolean removeMod(WeaponMod weaponMod){
 
        if (mods.remove(weaponMod)) {
            for (PropValue prop : weaponMod.getBonus()) {
                modStats.removeBonus(prop);
            }
            return true;
        }
        
        return false;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public List<WeaponMod> getMods() {
        return mods;
    }

    public Stats getModStats() {
        return modStats;
    }
    
    
    
    @Override
    public String toString() {
        return "Modded "+ weapon.toString();
    }
    
    public Stats evaluate(Stats stats) {
        Stats ret = weapon.applyTalents(stats);
        ret.mergeStats(modStats);
        
        return ret;
    }
    
    public Float getAdjustedDamage(Stats stats) {
        
        /*
        System.out.println("Weapon Base Damage: " + weapon.getBaseDamage());
        System.out.println("Firearm: " + stats.getProp(Property.FIREARM));
        System.out.println("Weapon Type Scaling: " + weapon.getWeaponType().getValue());
        System.out.println("Weapon Family Damage: " + stats.getProp(weapon.getWeaponFamily().getDamageProperty()));
        
        
        System.out.println("Weapon Damage Bonus: " + stats.getProp(Property.WEAPON_DAMAGE));
        System.out.println("Weapon Family Percentange Damage: " + stats.getProp(weapon.getWeaponFamily().getPercentageDamageProperty()));
        System.out.println("Out Of Cover Damage Bonus: " + stats.getProp(Property.OUT_OF_COVER_DAMAGE));
        */
        
        return ( (weapon.getBaseDamage() + (stats.getProp(Property.FIREARM)*weapon.getWeaponType().getDamageScaling()) + stats.getProp(weapon.getWeaponFamily().getDamageProperty()) )  *
                 ( 1F + stats.getProp(Property.WEAPON_DAMAGE)  +  stats.getProp(weapon.getWeaponFamily().getPercentageDamageProperty())  )  
                ) * (  1F + stats.getProp(Property.OUT_OF_COVER_DAMAGE)/100F) ;
    
    }
    
    public Float getDamageToElites(Stats stats) {
        //System.out.println("Damage VS Elits: " + stats.getProp(Property.DAMAGE_VS_ELITES));
        
        return getAdjustedDamage(stats) * (1F + stats.getProp(Property.DAMAGE_VS_ELITES)/100F );
    }
    
    public Float getDamageEnemyArmor(Stats stats) {
        //System.out.println("Enemy Armor Damage: " + stats.getProp(Property.ENEMY_ARMOR_DAMAGE));
        
        return getDamageToElites(stats) * (1F + stats.getProp(Property.ENEMY_ARMOR_DAMAGE)/100F );
    }
    
    //TODO: add set critical damage bonuses
    public Float getCriticalDamage(Stats stats, Float pulseCritDmg) {
        
        //System.out.println("Critical Hit Damage: " + (stats.getProp(Property.CRITICAL_HIT_DAMAGE) +  pulseCritDmg ));   
        
        return getDamageEnemyArmor(stats) * (1F + (stats.getProp(Property.CRITICAL_HIT_DAMAGE)+ pulseCritDmg)/ 100F ) ;
    }
    
    //TODO: add set headshot damage bonuses
    public Float getHeadshotDamage(Stats stats) {
        
        System.out.println("headshot Damage: " + (stats.getProp(Property.HEADSHOT_DAMAGE) ));   
        System.out.println("Gear bonus headshot Damage: " + (stats.getProp(Property.GEAR_HEADSHOT_DAMAGE) )); 
        
        Float headShotDamage = ( weapon.getWeaponType().getHeadShotScaling() + stats.getProp(Property.HEADSHOT_DAMAGE)/100F ) * (1 + stats.getProp(Property.GEAR_HEADSHOT_DAMAGE)/100F);
        
        return getDamageEnemyArmor(stats) * ( headShotDamage ) ;
    }
    
}
