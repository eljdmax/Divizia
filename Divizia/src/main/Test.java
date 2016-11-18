/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import core.build.*;
import core.components.*;
import core.components.gear.*;
import core.components.gearsets.*;
import core.components.weapontalents.*;

import backend.*;
import backend.dbs.SQLLite;
import java.util.HashMap;

/**
 *
 * @author tchabole
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HashMap<String,GearSet> innerMap = new HashMap<String,GearSet>();
        innerMap.put(HunterFaith.getInstance().getName(), HunterFaith.getInstance());
        innerMap.put(Reclaimer.getInstance().getName(), Reclaimer.getInstance());
        
        Persistable backend = new SQLLite("resource/dbs/test.db");
        
        
        HashMap<Integer,GearSet> gearSets = backend.loadGearSets(innerMap);
        
        for (Integer i : gearSets.keySet()) {
            System.out.println(gearSets.get(i).getName());
        }
        
        
        HashMap<Integer,Gear> gears = backend.loadGears(gearSets);
        
        //for (Integer i : gears.keySet()) {
        //    System.out.println(gears.get(i).toString());
        //}
        
        HashMap<Integer,Mod> mods = backend.loadMods();
        
        for (Integer i : mods.keySet()) {
           System.out.println(mods.get(i).toString());
        }
        
        HashMap<Integer,ModdedGear> moddedGears = backend.loadModdedGears(gearSets);
        
        //for (Integer i : moddedGears.keySet()) {
        //    System.out.println(moddedGears.get(i).toString());
        //}
        
        
        HashMap<Integer,Weapon> weapons = backend.loadWeapons();
        
        //for (Integer i : weapons.keySet()) {
        //    System.out.println(weapons.get(i).toString());
        //}
        
        HashMap<Integer,ModdedWeapon> moddedWeapons = backend.loadModdedWeapons();
        
        //for (Integer i : moddedWeapons.keySet()) {
        //    System.out.println(moddedWeapons.get(i).toString());
        //}
        
        
        
        HashMap<Integer,FullBuild> fullbuilds = backend.loadFullBuilds(gearSets);
        
        for (Integer i : fullbuilds.keySet()) {
            FullBuild testBuild = fullbuilds.get(i);
            System.out.println(testBuild.toString());
            
            
            testBuild.updateSetBonuses();
            
            ModdedWeapon modw1 = testBuild.getWeapon1();
            Stats w1Stats = modw1.evaluate(testBuild.getStats(), 1F, 34.1F, true, 60F);
            
            System.out.println(w1Stats.getMitigation(4));
            System.out.println(w1Stats.getSkillPower());
            System.out.println(w1Stats.getThoughness(0, 4));
            
            
            System.out.println(modw1.getSustainedBodyShotDmg());
            System.out.println(modw1.getSustainedHeadShotDmg());
            System.out.println(modw1.getSustainedDPS());
        }
        
        /*
        
        Holster myHol = new Holster(gearSets.get(1), 739F, 990F, 1003F, 1003F);
        
        myHol.addBonus(new PropValue(Property.SKILL_HASTE, 5F, RecalibrationPosition.MAJOR_1));
        myHol.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.SKILL_1));
        
        Backpack myPac = new Backpack(gearSets.get(1), 919F, 148F, 148F, 1003F);      
        
        myPac.addBonus(new PropValue(Property.ARMOR, 850F, RecalibrationPosition.MAJOR_1));
        myPac.addBonus(new PropValue(Property.BLEED_RESISTANCE, 14F, RecalibrationPosition.MINOR_1));
        myPac.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.MAJOR_2));
        myPac.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.SKILL_1));
        myPac.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.SKILL_2));
        
        BodyArmor myBod = new BodyArmor(gearSets.get(1), 1546F, 148F, 148F, 976F);
        
        myBod.addBonus(new PropValue(Property.ARMOR, 1131F, RecalibrationPosition.MAJOR_1));
        myBod.addBonus(new PropValue(Property.DAMAGE_VS_ELITES, 6F, RecalibrationPosition.MAJOR_2));
        myBod.addBonus(new PropValue(Property.KILL_XP, 27F, RecalibrationPosition.MINOR_1));
        myBod.addBonus(new PropValue(Property.PULSE_DURATION, 2.5F, RecalibrationPosition.SKILL_1));
        myBod.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.MINOR_2));
        myBod.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.MINOR_3));
        
        Kneepads myPad = new Kneepads(gearSets.get(1), 1250F, 148F, 994F, 148F);
        
        myPad.addBonus(new PropValue(Property.ARMOR, 1094F, RecalibrationPosition.MAJOR_1));
        myPad.addBonus(new PropValue(Property.ENEMY_ARMOR_DAMAGE, 12F, RecalibrationPosition.MINOR_1));
        myPad.addBonus(new PropValue(Property.BURN_RESISTANCE, 31F, RecalibrationPosition.MINOR_2));
        myPad.addBonus(new PropValue(Property.BLIND_DEAF_RESITANCE, 28F, RecalibrationPosition.MINOR_3));
        myPad.addBonus(new PropValue(Property.SUPPORT_STATION_HEALING_SPEED, 2.5F, RecalibrationPosition.SKILL_1));
        myPad.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.MAJOR_2));
        //myPad.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.SKILL_2));
        
        Mask myMas = new Mask(gearSets.get(1), 737F, 148F, 148F, 998F);
        
        myMas.addBonus(new PropValue(Property.DAMAGE_VS_ELITES, 9F, RecalibrationPosition.MAJOR_1));
        myMas.addBonus(new PropValue(Property.ENEMY_ARMOR_DAMAGE, 9F, RecalibrationPosition.MINOR_1));
        myMas.addBonus(new PropValue(Property.BALLISTIC_SHIELD_DAMAGE_RESILIENCE, 1.5F, RecalibrationPosition.SKILL_1));
        myMas.addBonus(new PropValue(Property.EMPTY,0F, RecalibrationPosition.MINOR_2));
        
        
        Gloves myGlo = new Gloves(gearSets.get(1), 751F, 976F, 148F, 148F);
   
        myGlo.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 5.5F, RecalibrationPosition.MAJOR_1));
        myGlo.addBonus(new PropValue(Property.DAMAGE_VS_ELITES, 9F, RecalibrationPosition.MAJOR_2));
        myGlo.addBonus(new PropValue(Property.LMG_DAMAGE, 921F, RecalibrationPosition.MAJOR_3));
        
        
        
        
       
        
        
        ModdedGear myModHol = new ModdedGear(myHol);
        
        GearMod gMod8 = new GearMod("SK",ModType.PULSE);
        gMod8.addBonus(new PropValue(Property.SUPPORT_STATION_HEALING_SPEED, 5F));
        
        myModHol.addMod(gMod8);
        
        ModdedGear myModPac = new ModdedGear(myPac);
        
        GearMod gMod4 = new GearMod("EL4",ModType.ELECTRONIC);
        gMod4.addBonus(new PropValue(Property.ELECTRONIC, 210F));
        gMod4.addBonus(new PropValue(Property.ARMOR, 264F));

        GearMod gMod5 = new GearMod("SK1",ModType.PULSE);
        gMod5.addBonus(new PropValue(Property.PULSE_CRITICAL_HIT_CHANCE, 2F));
        
        GearMod gMod6 = new GearMod("SK2",ModType.PULSE);
        gMod6.addBonus(new PropValue(Property.PULSE_DURATION, 4F));

        myModPac.addMod(gMod4);
        myModPac.addMod(gMod5);
        myModPac.addMod(gMod6);
        
        ModdedGear myModBod = new ModdedGear(myBod);
        
        GearMod gMod2 = new GearMod("EL2",ModType.ELECTRONIC);
        gMod2.addBonus(new PropValue(Property.ELECTRONIC, 193F));
        gMod2.addBonus(new PropValue(Property.ARMOR, 262F));

        GearMod gMod3 = new GearMod("EL3",ModType.ELECTRONIC);
        gMod3.addBonus(new PropValue(Property.ELECTRONIC, 204F));
        gMod3.addBonus(new PropValue(Property.ARMOR, 244F));

        myModBod.addMod(gMod2);
        myModBod.addMod(gMod3);
        
        ModdedGear myModPad = new ModdedGear(myPad);
        
        GearMod gMod7 = new GearMod("EL2",ModType.ELECTRONIC);
        gMod7.addBonus(new PropValue(Property.ELECTRONIC, 215F));
        gMod7.addBonus(new PropValue(Property.ARMOR, 235F));

        myModPad.addMod(gMod7);
        
        
        ModdedGear myModMas = new ModdedGear(myMas);

        GearMod gMod1 = new GearMod("EL1",ModType.ELECTRONIC);
        gMod1.addBonus(new PropValue(Property.ELECTRONIC, 198F));
        gMod1.addBonus(new PropValue(Property.ARMOR, 269F));
        
        myModMas.addMod(gMod1);
        
        ModdedGear myModGlo = new ModdedGear(myGlo);

        
        //backend.saveOrUpdateModdedGear(myModHol);
        //backend.saveOrUpdateModdedGear(myModBod);
        //backend.saveOrUpdateModdedGear(myModPac);
        //backend.saveOrUpdateModdedGear(myModPad);
        //backend.saveOrUpdateModdedGear(myModMas);
        //backend.saveOrUpdateModdedGear(myModGlo);

        Weapon weapon1 = new Weapon( WeaponType.Black_Market_M60_E6, 9491F, new Destructive( 15F,0F) , new PropValue( Property.OUT_OF_COVER_DAMAGE , 20F) );
        weapon1.addTalent(new Ferocious(10F, 0F) );
          
        
        
        
        Weapon weapon2 = new Weapon(WeaponType.Military_SCAR_H, 27157.98F, new Destructive( 15F,0F) , new PropValue( Property.HEADSHOT_DAMAGE , 161F) );
        weapon2.addTalent(new Brutal(12F, 0F) );
        //weapon2.addTalent(new Deadly(25F, 0F) );
        
        
        ModdedWeapon modw1 = new ModdedWeapon(weapon1);
        
        WeaponMod wMod1 = new WeaponMod("Muzz RX",ModType.MUZZLE);
        wMod1.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 3.0F));
        wMod1.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 18F));
        wMod1.addBonus(new PropValue(Property.HEADSHOT_DAMAGE, 5F));
        
        modw1.addMod(wMod1);
        
        WeaponMod wMod2 = new WeaponMod("Mag RX",ModType.MAGAZINE);
        wMod2.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 3.0F));
        wMod2.addBonus(new PropValue(Property.RATE_OF_FIRE, 5.7F));
        wMod2.addBonus(new PropValue(Property.MAGAZINE_SIZE, 111F));
        
        modw1.addMod(wMod2);
        
        WeaponMod wMod3 = new WeaponMod("scope RX",ModType.OPTICS);
        wMod3.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 8.0F));
        wMod3.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 4F));
        
        modw1.addMod(wMod3);
        
        WeaponMod wMod4 = new WeaponMod("barrel RX",ModType.UNDERBARREL);
        wMod4.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 18F));
        
        modw1.addMod(wMod4);
        
        
        //backend.saveOrUpdateModdedWeapon(modw1);
        
        
        
        FullBuild testBuild = new FullBuild("testBuild", myModPac, myModBod, myModGlo, myModHol, myModPad, myModMas );
        testBuild.setWeapon1(modw1);
        
        backend.saveOrUpdateFullBuild(testBuild);
        
        /*
        testBuild.updateSetBonuses();
        
        Stats w1Stats = modw1.evaluate(testBuild.getStats(), 1F, 34.1F, true, 60F);
        
        
        System.out.println("Adjusted Damage: " + modw1.getAdjustedDamage());
        System.out.println("Damage to Elites: " +modw1.getDamageToElites());
        System.out.println("Enemy Armor Damage: " +modw1.getDamageEnemyArmor());

        
        System.out.println("Damage After Crit: " + modw1.getCriticalDamage());
        
        System.out.println("Perc Headshot Damage : " +modw1.getPercHeadshotDamage());
        
        System.out.println("Headshot Damage: " +modw1.getHeadshotDamage());
        System.out.println("Headshot Dmg After Crit: " + modw1.getCritHeadshotDamage());
        
        
        
        System.out.println("RPM: " +modw1.getRPM());
        
        System.out.println("Mag: " +modw1.getMag());
        
        System.out.println("Reload speed: " +modw1.getReloadSpeed());

        System.out.println("Perc Crit Chance: " +modw1.getPercCriticalChance()); 
        
        
        System.out.println(modw1.getFullBodyshotDmg()); 
        System.out.println(modw1.getFullBodyshotAccurateDmg()); 
        System.out.println(modw1.getFullHeadshotDmg()); 
        System.out.println(modw1.getFullHeadshotAccurateDmg());
        System.out.println(modw1.getFullCritBodyshotDmg());
        System.out.println(modw1.getFullCritBodyshotAccurateDmg());
        
        System.out.println(modw1.getFullCritHeadshotDmg());
        System.out.println(modw1.getFullCritHeadshotAccurateDmg());
        
        System.out.println(modw1.getTimeDumpMag());
        
        
        System.out.println(modw1.getSustainedBodyShotDmg());
        System.out.println(modw1.getSustainedHeadShotDmg());
        System.out.println(modw1.getSustainedDPS());
        
        /*Stats mStats = testBuild.getStats();
        System.out.println(w1Stats.getMitigation(4));
        System.out.println(w1Stats.getSkillPower());
        System.out.println(w1Stats.getThoughness(0, 4));
        System.out.println(w1Stats.getThoughness(1, 4));
        System.out.println(w1Stats.getThoughness(2, 4));
        */
        
        //System.out.println(testBuild);
        
                
                
    }
    
}
