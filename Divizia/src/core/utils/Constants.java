/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.utils;

import core.components.*;
import core.components.gearsets.*;
import core.components.weapontalents.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

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
    public final static int MAX_WEAPON_TALENTS = 2;
    
    public final static int GEAR_MAJ = 1;
    public final static int GEAR_SKI = 2;
    public final static int GEAR_MOD = 4;
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
    
    
    public static HashMap<String,GearSet> getGearSetsMap() {
        HashMap<String,GearSet> gearSetsMap = new HashMap<String,GearSet>();
    
        gearSetsMap.put(HunterFaith.getInstance().getName(), HunterFaith.getInstance());
        gearSetsMap.put(Reclaimer.getInstance().getName(), Reclaimer.getInstance());
        
        return gearSetsMap;
    }
    
    public static HashMap<String,WeaponTalent> getWeaponTalentsMap() {
        HashMap<String,WeaponTalent> weaponTalentsMap = new HashMap<String,WeaponTalent>();
    
        weaponTalentsMap.put(Brutal.getInstance().getName(), Brutal.getInstance());
        weaponTalentsMap.put(Deadly.getInstance().getName(), Deadly.getInstance());
        weaponTalentsMap.put(Destructive.getInstance().getName(), Destructive.getInstance());
        weaponTalentsMap.put(Ferocious.getInstance().getName(), Ferocious.getInstance());
        
        return weaponTalentsMap;
    }
    
    public static String[] getWeaponTalents() {
        return new String[] {"Brutal", "Deadly", "Destructive", "Ferocious"};
    }
    
    public static String[] getGearTypes() {
        return new String[] {"BodyArmor", "Backpack", "Gloves", "Holster", "Kneepads", "Mask"};
    }
    
    public static <T extends Comparable<? super T>> List<T> asSortedList(Collection<T> c) {
        List<T> list = new ArrayList<T>(c);
        java.util.Collections.sort(list);
        return list;
    }
    
    
    public static HashMap<Integer,Property[]> getPropertyListMap() {
        
        int gearConstants[] = {Constants.GEAR_MAJ, Constants.GEAR_SKI, Constants.WEAPON_MOD, Constants.GEAR_SET};
        
        HashMap<Integer,List<Property>> tmpPropertyListMap = new HashMap<Integer,List<Property>>();
        
        for (int i : gearConstants) {
            tmpPropertyListMap.put(i, new ArrayList<Property>());
            tmpPropertyListMap.get(i).add(null);
        }
        
        int category;
        for (Property prop : Property.values()) {
            category = prop.getCategory();
            for (int i : gearConstants) {
                if ( (category & i) > 0) {
                    tmpPropertyListMap.get(i).add(prop);
                }
            }
        }
        
        HashMap<Integer,Property[]> propertyListMap = new HashMap<Integer,Property[]>();
        for (int i : gearConstants) {
            propertyListMap.put( i ,  tmpPropertyListMap.get(i).toArray(new Property[tmpPropertyListMap.get(i).size()]) );
        }
        
        return propertyListMap;
    }
    
    public static HashMap<Integer,ModType[]> getModPropertyListMap() {
        int gearConstants[] = {0 , Constants.GEAR_MOD, Constants.GEAR_SKI, Constants.WEAPON_MOD};
        
        HashMap<Integer,List<ModType>> tmpModPropertyListMap = new HashMap<Integer,List<ModType>>();
        
        for (int i : gearConstants) {
            tmpModPropertyListMap.put(i, new ArrayList<ModType>());
        }
        
        
        for (ModType modType : ModType.values()) {
            tmpModPropertyListMap.get(0).add(modType);
            tmpModPropertyListMap.get(modType.getModFamily()).add(modType);
        }
        
        HashMap<Integer,ModType[]> modPropertyListMap = new HashMap<Integer,ModType[]>();
        for (int i : gearConstants) {
            modPropertyListMap.put( i ,  tmpModPropertyListMap.get(i).toArray(new ModType[tmpModPropertyListMap.get(i).size()]) );
        }
        
        return modPropertyListMap;
        
    }
    
    public static Mod getNewMod(String name, ModType modType) {
        Mod mod = null;
        
        switch (modType.getModFamily()) {
            case GEAR_MOD:
                mod = new GearMod(name,modType);
                break;
            case GEAR_SKI:
                mod = new PerfMod(name,modType);
                break;
                
            case WEAPON_MOD:
                mod = new WeaponMod(name,modType);
                break;
        }
        
        return mod;
    }
    
}
 