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
import core.utils.*;

/**
 *
 * @author tchabole
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HunterFaith myGs = HunterFaith.getInstance();
        
        Holster myHol = new Holster(myGs, 739F, 990F, 1003F, 1003F);
        
        myHol.addBonus(new PropValue(Property.SKILL_HASTE, 5F, RecalibrationPosition.MAJOR_1));
        myHol.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.SKILL_1));
        
        Backpack myPac = new Backpack(myGs, 919F, 148F, 148F, 1003F);      
        
        myPac.addBonus(new PropValue(Property.ARMOR, 850F, RecalibrationPosition.MAJOR_1));
        myPac.addBonus(new PropValue(Property.BLEED_RESISTANCE, 14F, RecalibrationPosition.MINOR_1));
        myPac.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.MAJOR_2));
        myPac.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.SKILL_1));
        myPac.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.SKILL_2));
        
        BodyArmor myBod = new BodyArmor(myGs, 1546F, 148F, 148F, 976F);
        
        myBod.addBonus(new PropValue(Property.ARMOR, 1131F, RecalibrationPosition.MAJOR_1));
        myBod.addBonus(new PropValue(Property.DAMAGE_VS_ELITES, 6F, RecalibrationPosition.MAJOR_2));
        myBod.addBonus(new PropValue(Property.KILL_XP, 27F, RecalibrationPosition.MINOR_1));
        myBod.addBonus(new PropValue(Property.PULSE_DURATION, 2.5F, RecalibrationPosition.SKILL_1));
        myBod.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.MINOR_2));
        myBod.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.MINOR_3));
        
        Kneepads myPad = new Kneepads(myGs, 1250F, 148F, 994F, 148F);
        
        myPad.addBonus(new PropValue(Property.ARMOR, 1094F, RecalibrationPosition.MAJOR_1));
        myPad.addBonus(new PropValue(Property.ENEMY_ARMOR_DAMAGE, 12F, RecalibrationPosition.MINOR_1));
        myPad.addBonus(new PropValue(Property.BURN_RESISTANCE, 31F, RecalibrationPosition.MINOR_2));
        myPad.addBonus(new PropValue(Property.BLIND_DEAF_RESITANCE, 28F, RecalibrationPosition.MINOR_3));
        myPad.addBonus(new PropValue(Property.SUPPORT_STATION_HEALING_SPEED, 2.5F, RecalibrationPosition.SKILL_1));
        myPad.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.MAJOR_2));
        //myPad.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.SKILL_2));
        
        Mask myMas = new Mask(myGs, 737F, 148F, 148F, 998F);
        
        myMas.addBonus(new PropValue(Property.DAMAGE_VS_ELITES, 9F, RecalibrationPosition.MAJOR_1));
        myMas.addBonus(new PropValue(Property.ENEMY_ARMOR_DAMAGE, 9F, RecalibrationPosition.MINOR_1));
        myMas.addBonus(new PropValue(Property.BALLISTIC_SHIELD_DAMAGE_RESILIENCE, 1.5F, RecalibrationPosition.SKILL_1));
        myMas.addBonus(new PropValue(Property.EMPTY,Float.NaN, RecalibrationPosition.MINOR_2));
        
        
        Gloves myGlo = new Gloves(myGs, 751F, 976F, 148F, 148F);
   
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
        gMod5.addBonus(new PropValue(Property.PULSE_DURATION, 4F));

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


        
        Weapon weapon1 = new Weapon(WeaponFamily.LMG, WeaponType.Black_Market_M60_E6, 9491F, new Destructive( 15F,Float.NaN) , new PropValue( Property.OUT_OF_COVER_DAMAGE , 20F) );
        weapon1.addTalent(new Ferocious(10F, Float.NaN) );
        weapon1.addTalent(new Deadly(25F, Float.NaN) );
        
        ModdedWeapon modw1 = new ModdedWeapon(weapon1);
        
        WeaponMod wMod1 = new WeaponMod("Muzz RX",ModType.MUZZLE);
        wMod1.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 3.0F));
        wMod1.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 18F));
        wMod1.addBonus(new PropValue(Property.HEADSHOT_DAMAGE, 5F));
        
        modw1.addMod(wMod1);
        
        WeaponMod wMod2 = new WeaponMod("Mag RX",ModType.MAGAZINE);
        wMod2.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 3.0F));
        wMod2.addBonus(new PropValue(Property.RATE_OF_FIRE, 6F));
        wMod2.addBonus(new PropValue(Property.MAGAZINE_SIZE, 111F));
        
        modw1.addMod(wMod2);
        
        WeaponMod wMod3 = new WeaponMod("scope RX",ModType.OPTICS);
        wMod3.addBonus(new PropValue(Property.CRITICAL_HIT_CHANCE, 8.0F));
        wMod3.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 4F));
        
        modw1.addMod(wMod3);
        
        WeaponMod wMod4 = new WeaponMod("barrel RX",ModType.UNDERBARREL);
        wMod4.addBonus(new PropValue(Property.CRITICAL_HIT_DAMAGE, 18F));
        
        modw1.addMod(wMod4);
        
        
        
        FullBuild testBuild = new FullBuild("testBuild", myModPac, myModBod, myModGlo, myModHol, myModPad, myModMas );
        testBuild.setWeapon1(modw1);
        
        Stats w1Stats = testBuild.getWeapon1().evaluate(testBuild.getStats());
        
        System.out.println(testBuild.getWeapon1().getAdjustedDamage(w1Stats));
        System.out.println(testBuild.getWeapon1().getDamageToElites(w1Stats));
        System.out.println(testBuild.getWeapon1().getDamageEnemyArmor(w1Stats));
        
        System.out.println(testBuild.getWeapon1().getCriticalDamage(w1Stats,34F));
        
        System.out.println(testBuild.getWeapon1().getHeadshotDamage(w1Stats));
        
        //Stats mStats = testBuild.getStats();
        /*
        System.out.println(w1Stats.getMitigation(4));
        System.out.println(w1Stats.getSkillPower());
        System.out.println(w1Stats.getThoughness(0, 4));
        System.out.println(w1Stats.getThoughness(1, 4));
        System.out.println(w1Stats.getThoughness(2, 4));
        */
        
        System.out.println(testBuild);
        
        
        //System.out.println(testBuild.getStats().getProp(Property.HEADSHOT_DAMAGE));
        
        //System.out.println(w1Stats.getProp(Property.HEADSHOT_DAMAGE));
        //System.out.println(w1Stats.getProp(Property.ACCURACY));
        
    }
    
}
