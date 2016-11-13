/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import core.build.*;
import core.components.*;
import java.util.HashMap;

/**
 *
 * @author Administrator
 */
public interface Persistable {
    
    public HashMap<Integer,GearSet> loadGearSets(HashMap<String,GearSet> innerMap);
    public HashMap<Integer,Gear> loadGears(HashMap<Integer,GearSet> gearSets);
    public HashMap<Integer,PropValue> loadPropValues();
    public HashMap<Integer,Mod> loadMods();
    public HashMap<Integer,ModdedGear> loadModdedGears(HashMap<Integer,GearSet> gearSets);
    public HashMap<Integer,WeaponTalent> loadWeaponTalents();
    public HashMap<Integer,Weapon> loadWeapons();
    public HashMap<Integer,ModdedWeapon> loadModdedWeapons();
    public HashMap<Integer,FullBuild> loadFullBuilds(HashMap<Integer,GearSet> gearSets);
    
    public void saveOrUpdateGear(Gear gear);
    public void saveOrUpdatePropValue(PropValue prop);
    public void saveOrUpdateMod(Mod mod);
    public void saveOrUpdateModdedGear(ModdedGear moddedGear);
    public void saveOrUpdateWeaponTalent(WeaponTalent weaponTalent);
    public void saveOrUpdateWeapon(Weapon weapon);
    public void saveOrUpdateModdedWeapon(ModdedWeapon moddedWeapon);
    public void saveOrUpdateFullBuild(FullBuild fullBuild);
    
}
