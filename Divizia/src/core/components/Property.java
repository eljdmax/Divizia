/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import core.utils.Constants;

/**
 *
 * @author tchabole
 */
public enum Property {
    //Weapon Property
    CRITICAL_HIT_CHANCE("Critical Hit Chance", Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    CRITICAL_HIT_DAMAGE("Critical Hit Damage",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    HEADSHOT_DAMAGE("Headshot Damage",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    WEAPON_DAMAGE("Weapon Damage",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    ACCURACY("Accuracy",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    STABILITY("Stability",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    OPTIMAL_RANGE("Optimal Range",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    MAGAZINE_SIZE("Managizine Size",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    RELOAD_SPEED("Reload Speed",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    REDUCED_THREAT("Reduced Threat",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    INCREASED_THREAT("Increased Thread",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    RATE_OF_FIRE("Rate Of Fire",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    OUT_OF_COVER_DAMAGE("Out Of Cover Damage",Constants.GEAR_MAJ|Constants.GEAR_SKI|Constants.WEAPON_MOD),
    
    //Gear Main Property
    FIREARM("Firearms",Constants.GEAR_MOD),
    STAMINA("Stamina",Constants.GEAR_MOD),
    ELECTRONIC("Electronics",Constants.GEAR_MOD),
    
    //Empty Mod Slot
    EMPTY("MOD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    
    //Gear Additional Property
    ARMOR("ARM",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    HEALTH("HEA",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    HEALTH_ON_KILL("HOK",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    EXOTIC_DAMAGE_RESILIENCE("EDR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SKILL_HASTE("SKH",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SKILL_POWER("SKP",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_SKILL_POWER("PSKP",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SIGNATURE_RESOURCE_GAIN("SRG",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    ENEMY_ARMOR_DAMAGE("EAD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    DAMAGE_VS_ELITES("DVE",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PROTECTION_VS_ELITES("PVE",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SHOCK_RESISTANCE("SHR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    BURN_RESISTANCE("BUR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    DISORIENT_RESISTANCE("DOR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    BLIND_DEAF_RESITANCE("BDR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    DISRUPT_RESISTANCE("DRI",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    BLEED_RESISTANCE("BLR",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SCAVENGING("SCA",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    KILL_XP("KXP",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    AMMO_CAPACITY("AMC",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    LMG_DAMAGE("LMGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SMG_DAMAGE("SMGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    MMR_DAMAGE("MMRD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PISTOL_DAMAGE("PISD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SHOTGUN_DAMAGE("SHGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    ASSAULT_DAMAGE("ASRD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_LMG_DAMAGE("PLMGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_SMG_DAMAGE("PSMGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_MMR_DAMAGE("PMMRD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_PISTOL_DAMAGE("PPISD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_SHOTGUN_DAMAGE("PSHGD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    PERCENTAGE_ASSAULT_DAMAGE("PASRD",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    
    
    //Perf Mod Property
    PULSE_RANGE("PUR",Constants.GEAR_SKI),
    PULSE_DURATION("PUD",Constants.GEAR_SKI),
    PULSE_CRITICAL_HIT_CHANCE("PCHC",Constants.GEAR_SKI),
    PULSE_CRITICAL_HIT_DAMAGE("PCHD",Constants.GEAR_SKI),
    FIRST_AID_SELF_HEAL("FASH",Constants.GEAR_SKI),
    FIRST_AID_ALLY_HEAL("FAAH",Constants.GEAR_SKI),
    FIRST_AID_RADIUS("FAR",Constants.GEAR_SKI),
    FIRST_AID_DEPLOYMENT_RANGE("FADR",Constants.GEAR_SKI),
    SUPPORT_STATION_RANGE("SSR",Constants.GEAR_SKI),
    SUPPORT_STATION_HEALING_SPEED("SSHS",Constants.GEAR_SKI),
    SUPPORT_STATION_REVIVE_TIME("SSRT",Constants.GEAR_SKI),
    SUPPORT_STATION_DURATION("SSD",Constants.GEAR_SKI),
    SUPPORT_STATION_HEALTH("SSH",Constants.GEAR_SKI),
    STICKY_BOMB_DAMAGE("SBB",Constants.GEAR_SKI),
    STICKY_BOMB_EXPLOSION_RADIUS("SBER",Constants.GEAR_SKI),
    STICKY_BOMB_DEPLOYMENT_RANGE("SBDR",Constants.GEAR_SKI),
    TURRET_DAMAGE("TD",Constants.GEAR_SKI),
    TURRET_ATTACK_RANGE("TAR",Constants.GEAR_SKI),
    TURRET_DURATION("TD",Constants.GEAR_SKI),
    TURRET_HEALTH("TH",Constants.GEAR_SKI),
    SEEKER_MINE_DAMAGE("SMD",Constants.GEAR_SKI),
    SEEKER_MINE_EXPLOSION_RADIUS("SMER",Constants.GEAR_SKI),
    SEEKER_MINE_DETECTION_RANGE("SMDR",Constants.GEAR_SKI),
    SEEKER_MINE_DURATION("SMD",Constants.GEAR_SKI),
    SEEKER_MINE_HEALTH("SMH",Constants.GEAR_SKI),
    BALLISTIC_SHIELD_HEALTH("BSH",Constants.GEAR_SKI),
    BALLISTIC_SHIELD_DAMAGE("BSD",Constants.GEAR_SKI),
    BALLISTIC_SHIELD_DAMAGE_RESILIENCE("BSDR",Constants.GEAR_SKI),
    SMART_COVER_DAMAGE_RESILIENCE("SCDR",Constants.GEAR_SKI),
    SMART_COVER_WEAPON_STABILITY("SCWS",Constants.GEAR_MAJ|Constants.GEAR_SKI),
    SMART_COVER_WEAPON_ACCURACY("SCWA",Constants.GEAR_SKI),
    SMART_COVER_RANGE("SCR",Constants.GEAR_SKI),
    SMART_COVER_DURATION("SCD",Constants.GEAR_SKI),
    SMART_COVER_DEPLOYMENT_RANGE("SCDR",Constants.GEAR_SKI),
    MOBILE_COVER_HEALTH("MCH",Constants.GEAR_SKI),
    MOBILE_COVER_DAMAGE_RESILIENCE("MCDR",Constants.GEAR_SKI),
    MOBILE_COVER_BLAST_RESILIENCE("MCBR",Constants.GEAR_SKI),
    
    //Gear Set Bonuses
    GEAR_HEADSHOT_DAMAGE("G_HSD",Constants.GEAR_SET),
    GEAR_OPTIMAL_RANGE("G_OPR",Constants.GEAR_SET);
    
    
    
    private String value;
    private int category; // bitwise flag 0001: gear major/minor | 0010: gear skill | 0100: weapon ?? 
    
    private Property(String value, int category) {
        this.value = value;
        this.category = category;
    }

    @Override
    public String toString() {
        return value;
    }
    
    public int getCategory() {
        return category;
    }
    
    
}
