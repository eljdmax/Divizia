/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend.dbs;

import backend.*;
import core.build.*;
import core.components.*;
import java.io.File;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class SQLLite implements Persistable{
    
    private String dbName;
    
    public SQLLite(String dbName) {
        this.dbName = dbName;
        init();
        
    }
    
    private Connection getConnection() throws Exception {
        Class.forName("org.sqlite.JDBC");
        return DriverManager.getConnection("jdbc:sqlite:"+this.dbName);
    }
    
    private long getInsertedId(Statement stmt) throws Exception {
        ResultSet rs = stmt.executeQuery("select last_insert_rowid() as IID");
        
        long ret = -1;
        
        while (rs.next()) {
            ret = rs.getLong("IID");
        }
        
        return ret;
       
    }
    
    private void init() {
        
        Connection c = null;
        try {
            String content = new Scanner(new File("resource/schemas/sqlite.sql")).useDelimiter("\\Z").next();
            c = getConnection();
            
            
            Statement stmt = c.createStatement();
            stmt.executeUpdate(content);
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    @Override
    public HashMap<Integer,GearSet> loadGearSets(HashMap<String,GearSet> innerMap) {
        
        HashMap<Integer,GearSet> ret = new HashMap<Integer,GearSet>();
        
        Connection c = null;
        try {
            c = getConnection();
            
            String query = "SELECT rowid,* FROM GEARSET";
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            GearSet gearSet;
            while (rs.next()) {
                gearSet = innerMap.get(rs.getString("NAME"));
                if (gearSet == null) {
                    continue;
                }
                gearSet.setId(Integer.toString(rs.getInt("rowid")));
                ret.put(rs.getInt("rowid"), gearSet );
                
            }
            
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    private void fetchGearBonuses(Gear gear) {
        
        if (gear.getId() == null) {
            return;
        }
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            String query = "SELECT p.rowid, p.* FROM PROP_VALUE p JOIN GEAR_BONUSES gb ON p.rowid = gb.PROP_VALUE_ID "
                    + "                         WHERE gb.GEAR_ID = "+gear.getId();


            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                PropValue prop = new PropValue( Property.valueOf(rs.getString("PROPERTY")),
                                      rs.getFloat("VALUE"),
                                      RecalibrationPosition.valueOf(rs.getString("POSITION")));

                prop.setId(Integer.toString(rs.getInt("rowid")));

                gear.addBonus(prop);
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    private HashMap<Integer,Gear> loadGears(HashMap<Integer,GearSet> gearSets, String gearId, Statement stmt) throws Exception {
        HashMap<Integer,Gear> ret = new HashMap<Integer,Gear>();
        
        String query = "SELECT rowid, * FROM GEAR ";
        if (gearId != null) {
            query += " WHERE rowid = "+gearId;
        }

        ResultSet rs = stmt.executeQuery(query);
        GearSet gearSet;
        Gear gear;
        while (rs.next()) {
            gearSet = gearSets.get(rs.getInt("GEAR_SET_ID"));
            if (gearSet == null) {
                continue;
            }

            gear = (Gear) Class.forName("core.components.gear."+rs.getString("CLASS_TYPE")).newInstance();

            gear.setId(Integer.toString(rs.getInt("rowid")));
            gear.setGearSet(gearSet);
            gear.setArmor(rs.getFloat("ARMOR"));
            gear.setFA(rs.getFloat("FA"));
            gear.setST(rs.getFloat("ST"));
            gear.setEL(rs.getFloat("EL"));
            gear.setGearScore(rs.getInt("GEARSCORE"));

            fetchGearBonuses(gear);
            
            ret.put(rs.getInt("rowid"), gear );
        }

        rs.close();
        
        return ret;
    }
    
    
    @Override
    public HashMap<Integer,Gear> loadGears(HashMap<Integer,GearSet> gearSets) {
        HashMap<Integer,Gear> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadGears(gearSets, null, stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    
    private void saveOrUpdateGear(Gear gear, Statement stmt) throws Exception {
        String[] parts = gear.getClass().getName().split("\\.");
        String gearName = parts[ parts.length - 1];
        String query;

        if (gear.getId() == null) { //INSERT

            query = "INSERT INTO GEAR (CLASS_TYPE, GEAR_SET_ID, GEARSCORE, ARMOR, FA, ST, EL) "
                         + " VALUES ('"+ gearName +"',"
                         + "          "+ gear.getGearSet().getId()+","
                         + "          "+ gear.getGearScore()+","
                         + "          "+ gear.getArmor()+","
                         + "          "+ gear.getFA()+","
                         + "          "+ gear.getST()+","
                         + "          "+ gear.getEL()+")";

        } else { //UPDATE

            query = "UPDATE GEAR SET CLASS_TYPE = '"+ gearName +"',"
                    +             "   GEAR_SET_ID = "+ gear.getGearSet().getId() + ","
                    +             "   GEARSCORE = "+ gear.getGearScore()+ ","
                    +             "   ARMOR = "+ gear.getArmor() + ","
                    +             "   FA = "+ gear.getFA() + ","
                    +             "   ST = "+ gear.getST() + ","
                    +             "   EL = "+ gear.getEL() + ""
                    + "   WHERE rowid = "+gear.getId();

        }

        stmt.executeUpdate(query);

        if (gear.getId() == null) {
            gear.setId(Long.toString(getInsertedId(stmt)));
        }

        //Bonuses
        stmt.executeUpdate("DELETE FROM GEAR_BONUSES WHERE GEAR_ID = "+gear.getId());
        
        for (PropValue prop : gear.getBaseBonuses()) {
            saveOrUpdatePropValue(prop,stmt);
            stmt.executeUpdate("INSERT INTO GEAR_BONUSES ( GEAR_ID , PROP_VALUE_ID) VALUES ( "+gear.getId()+","+ prop.getId() +" )"  );
        }
             
    }
    
    @Override
    public void saveOrUpdateGear(Gear gear) {
        
        
        Connection c = null;
        try {
            c = getConnection();
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateGear(gear, stmt);
            
            c.commit();
            
            stmt.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    
    private HashMap<Integer,PropValue> loadPropValues(String propValueId, Statement stmt) throws Exception {
        HashMap<Integer,PropValue> ret = new HashMap<Integer,PropValue>();
        
        String query = "SELECT rowid, * FROM PROP_VALUE ";
        if (propValueId != null) {
            query += " WHERE rowid = "+propValueId;
        }

        ResultSet rs = stmt.executeQuery(query);
        PropValue prop;
        while (rs.next()) {

            prop = new PropValue( Property.valueOf(rs.getString("PROPERTY")),
                                  rs.getFloat("VALUE"),
                                  RecalibrationPosition.valueOf(rs.getString("POSITION")));

            prop.setId(Integer.toString(rs.getInt("rowid")));

            ret.put(rs.getInt("rowid"), prop );
        }
        
        return ret;
    }
    
    
    @Override
    public HashMap<Integer,PropValue> loadPropValues() {
        HashMap<Integer,PropValue> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadPropValues(null,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
 
    private void saveOrUpdatePropValue(PropValue prop, Statement stmt) throws Exception {
        String query;

        if (prop.getId() == null) { //INSERT

            query = "INSERT INTO PROP_VALUE (PROPERTY, `VALUE`, `POSITION`) "
                         + " VALUES ('"+ prop.getProperty().name() +"',"
                         + "          "+ prop.getValue()+","
                         + "          '"+ prop.getRecalibrationPosition().name()+"')";

        } else { //UPDATE

            query = "UPDATE PROP_VALUE SET PROPERTY = '"+ prop.getProperty().name() +"',"
                    +             "   `VALUE` = "+ prop.getValue() + ","
                    +             "   `POSITION` = '"+ prop.getRecalibrationPosition().name() + "'"
                    + "   WHERE rowid = "+prop.getId();

        }

        stmt.executeUpdate(query);

        if (prop.getId() == null) {
            prop.setId(Long.toString(getInsertedId(stmt)));
        }
    }
    
    @Override
    public void saveOrUpdatePropValue(PropValue prop) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdatePropValue(prop, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    private void fetchModBonuses(Mod mod) {
        
        if (mod.getId() == null) {
            return;
        }
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            String query = "SELECT p.rowid, p.* FROM PROP_VALUE p JOIN MOD_BONUSES mb ON p.rowid = mb.PROP_VALUE_ID "
                    + "                         WHERE mb.MOD_ID = "+mod.getId();


            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                PropValue prop = new PropValue( Property.valueOf(rs.getString("PROPERTY")),
                                      rs.getFloat("VALUE"),
                                      RecalibrationPosition.valueOf(rs.getString("POSITION")));

                prop.setId(Integer.toString(rs.getInt("rowid")));

                mod.addBonus(prop);
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    private HashMap<Integer,Mod> loadMods(String modId, Statement stmt) throws Exception {
        HashMap<Integer,Mod> ret = new HashMap<Integer,Mod>();
        
        String query = "SELECT rowid, * FROM MODS ";
        if (modId != null) {
            query += " WHERE rowid = "+modId;
        }

        ResultSet rs = stmt.executeQuery(query);
        
        Mod mod;
        while (rs.next()) {

            mod = (Mod) Class.forName("core.components."+rs.getString("CLASS_TYPE")).newInstance();

            mod.setId(Integer.toString(rs.getInt("rowid")));
            mod.setName(rs.getString("Name"));
            mod.setType(ModType.valueOf(rs.getString("MOD_TYPE")));
            
            fetchModBonuses(mod);
            
            ret.put(rs.getInt("rowid"), mod );
        }

        rs.close();   
        
        return ret;
    }
    
    @Override
    public HashMap<Integer,Mod> loadMods() {
        HashMap<Integer,Mod> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadMods(null,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    private void saveOrUpdateMod(Mod mod, Statement stmt) throws Exception {
        String[] parts = mod.getClass().getName().split("\\.");
        String modName = parts[ parts.length - 1];
        String query;

        if (mod.getId() == null) { //INSERT

            query = "INSERT INTO MODS (CLASS_TYPE, `NAME`, MOD_TYPE) "
                         + " VALUES ('"+ modName +"',"
                         + "         '"+ mod.getName()+"',"
                         + "         '"+ mod.getType().name()+"')";

        } else { //UPDATE

            query = "UPDATE MODS SET CLASS_TYPE = '"+ modName +"',"
                    +             "   `NAME` = '"+ mod.getName() + "',"
                    +             "   MOD_TYPE = '"+ mod.getType().name() + "'"
                    + "   WHERE rowid = "+mod.getId();

        }

        stmt.executeUpdate(query);

        if (mod.getId() == null) {
            mod.setId(Long.toString(getInsertedId(stmt)));
        }

        //Bonuses
        stmt.executeUpdate("DELETE FROM MOD_BONUSES WHERE MOD_ID = "+mod.getId());
        
        for (PropValue prop : mod.getBonus()) {
            saveOrUpdatePropValue(prop,stmt);
            stmt.executeUpdate("INSERT INTO MOD_BONUSES ( MOD_ID , PROP_VALUE_ID) VALUES ( "+mod.getId()+","+ prop.getId() +" )"  );
        }
             
    }
    
    
    @Override
    public void saveOrUpdateMod(Mod mod) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateMod(mod, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    private void fetchModdedGearMods(ModdedGear moddedGear) {
        
        if (moddedGear.getId() == null) {
            return;
        }
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            String query = "SELECT m.rowid, mgm.MOD_POSITION, m.* FROM MODS m JOIN MODDED_GEAR_MODS mgm ON m.rowid = mgm.MOD_ID "
                    + "                         WHERE mgm.MODDED_GEAR_ID = "+moddedGear.getId();


            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                GearMod mod = (GearMod) Class.forName("core.components."+rs.getString("CLASS_TYPE")).newInstance();

                mod.setId(Integer.toString(rs.getInt("rowid")));
                mod.setName(rs.getString("Name"));
                mod.setType(ModType.valueOf(rs.getString("MOD_TYPE")));

                fetchModBonuses(mod);
                
                moddedGear.addMod(mod, RecalibrationPosition.valueOf(rs.getString("MOD_POSITION")));
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    private Gear fetchGear(HashMap<Integer,GearSet> gearSets,String gearId) {
        
        if (gearId == null) {
            return null;
        }
        
        Gear gear = null;
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            HashMap<Integer,Gear> mappedGear = loadGears(gearSets, gearId, stmt);
            
            for (Integer i : mappedGear.keySet() ){
                gear = mappedGear.get(i);
                break;
            }
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return gear;
    }
    
    private HashMap<Integer,ModdedGear> loadModdedGears(HashMap<Integer,GearSet> gearSets, String moddedGearId, String gearType, Statement stmt) throws Exception {
        HashMap<Integer,ModdedGear> ret = new HashMap<Integer,ModdedGear>();
        
        String query = "SELECT rowid, * FROM MODDED_GEAR ";
        
        
        if (moddedGearId != null) {
            query += " WHERE rowid = "+moddedGearId;
        }
        
        if (gearType != null) {
            query = " SELECT mg.rowid, mg.* FROM ("+ query +") mg JOIN GEAR g ON mg.GEAR_ID = g.rowid  WHERE g.CLASS_TYPE = '"+gearType+"' ";
        }

        ResultSet rs = stmt.executeQuery(query);
        
        ModdedGear moddedGear;
        Gear gear;
        while (rs.next()) {

            moddedGear = new ModdedGear();
            moddedGear.setId(Integer.toString(rs.getInt("rowid")));

            gear =fetchGear(gearSets, Long.toString(rs.getLong("GEAR_ID")));
            
            if (gear !=null) {
                moddedGear.setGear(gear);
            }
            
            fetchModdedGearMods(moddedGear);
            
            ret.put(rs.getInt("rowid"), moddedGear );
        }

        rs.close();   
        
        return ret;
    }
    
    
    @Override
    public HashMap<Integer,ModdedGear> loadModdedGears(HashMap<Integer,GearSet> gearSets) {
        return loadModdedGears(gearSets, null);
    }
    
    @Override
    public HashMap<Integer,ModdedGear> loadModdedGears(HashMap<Integer,GearSet> gearSets , String gearType) {
        HashMap<Integer,ModdedGear> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadModdedGears(gearSets,null,gearType,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    
    private void saveOrUpdateModdedGear(ModdedGear moddedGear, Statement stmt) throws Exception {

        // Gear first
        if (moddedGear == null) {
            return;
        }
        
        saveOrUpdateGear(moddedGear.getGear(), stmt);
        
        
        String query;
        
        if (moddedGear.getId() == null) { //INSERT

            query = "INSERT INTO MODDED_GEAR (GEAR_ID) "
                         + " VALUES ("+ moddedGear.getGear().getId() +")";

        } else { //UPDATE

            query = "UPDATE MODDED_GEAR SET GEAR_ID = "+ moddedGear.getGear().getId() 
                    + "   WHERE rowid = "+moddedGear.getId();

        }

        stmt.executeUpdate(query);

        if (moddedGear.getId() == null) {
            moddedGear.setId(Long.toString(getInsertedId(stmt)));
        }

        //Bonuses
        stmt.executeUpdate("DELETE FROM MODDED_GEAR_MODS WHERE MODDED_GEAR_ID = "+moddedGear.getId());
        
        HashMap<GearMod,RecalibrationPosition> modsPosition = moddedGear.getModsPosition();
        
        for (GearMod mod : moddedGear.getMods()) {
            saveOrUpdateMod(mod,stmt);
            stmt.executeUpdate("INSERT INTO MODDED_GEAR_MODS ( MODDED_GEAR_ID , MOD_ID, MOD_POSITION) VALUES ( "+moddedGear.getId()+","+ mod.getId() +",'"+ modsPosition.get(mod).name() +"')"  );
        }
             
    }
    
    @Override
    public void saveOrUpdateModdedGear(ModdedGear moddedGear) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateModdedGear(moddedGear, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    
    @Override
    public HashMap<Integer,WeaponTalent> loadWeaponTalents(HashMap<String,WeaponTalent> innerMap) {
        
        HashMap<Integer,WeaponTalent> ret = new HashMap<Integer,WeaponTalent>();
        
        Connection c = null;
        try {
            c = getConnection();
            
            String query = "SELECT rowid,* FROM WEAPON_TALENT";
            
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            WeaponTalent weaponTalent;
            while (rs.next()) {
                weaponTalent = innerMap.get(rs.getString("NAME"));
                if (weaponTalent == null) {
                    continue;
                }
                weaponTalent.setId(Integer.toString(rs.getInt("rowid")));
                ret.put(rs.getInt("rowid"), weaponTalent );
                
            }
            
            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    private PropValue fetchPropValue(String propValueId) {
        
        if (propValueId == null) {
            return null;
        }
        
        PropValue propValue = null;
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            HashMap<Integer,PropValue> mappedPropValue = loadPropValues(propValueId, stmt);
            
            for (Integer i : mappedPropValue.keySet() ){
                propValue = mappedPropValue.get(i);
                break;
            }
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return propValue;
    }
    
    
    private void fetchWeaponExtraTalents(Weapon weapon,HashMap<Integer,WeaponTalent> weaponTalents) {
        
        if (weapon.getId() == null) {
            return;
        }
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            String query = "SELECT wt.rowid, wt.* FROM WEAPON_TALENT wt JOIN WEAPON_EXTRA_TALENTS wet ON wt.rowid = wet.WEAPON_TALENT_ID "
                    + "                         WHERE wet.WEAPON_ID = "+weapon.getId();


            ResultSet rs = stmt.executeQuery(query);

            WeaponTalent weaponTalent;
            
            while (rs.next()) {
                
                weaponTalent =  weaponTalents.get(rs.getInt("rowid")); 
                
                weapon.addTalent(weaponTalent);
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    private HashMap<Integer,Weapon> loadWeapons(HashMap<Integer,WeaponTalent> weaponTalents, String weaponId, Statement stmt) throws Exception {
        HashMap<Integer,Weapon> ret = new HashMap<Integer,Weapon>();
        
        String query = "SELECT rowid, * FROM WEAPON ";
        if (weaponId != null) {
            query += " WHERE rowid = "+weaponId;
        }

        ResultSet rs = stmt.executeQuery(query);
        
        Weapon weapon;
        WeaponTalent mainTalent;
        PropValue bonus;
        
        while (rs.next()) {

            mainTalent = weaponTalents.get(rs.getInt("MAIN_TALENT_ID"));
            
            bonus = fetchPropValue( Long.toString(rs.getLong("BONUS_ID")) );
                    
            weapon = new Weapon( WeaponType.valueOf(rs.getString("TYPE")), rs.getFloat("BASE_DMG"), mainTalent, bonus );

            weapon.setGearScore(rs.getInt("GEAR_SCORE"));
            
            weapon.setId(Integer.toString(rs.getInt("rowid")));
            
            fetchWeaponExtraTalents(weapon,weaponTalents);
            
            ret.put(rs.getInt("rowid"), weapon );
        }

        rs.close();   
        
        return ret;
    }
    
    
    @Override
    public HashMap<Integer,Weapon> loadWeapons(HashMap<Integer,WeaponTalent> weaponTalents) {
        HashMap<Integer,Weapon> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadWeapons(weaponTalents,null,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    private void saveOrUpdateWeapon(Weapon weapon, Statement stmt) throws Exception {

        
        String bonusId = "''";
        if (weapon.getWeaponBonus() != null) {
            saveOrUpdatePropValue(weapon.getWeaponBonus(), stmt);
            bonusId = weapon.getWeaponBonus().getId();
        }
        
        String query;
        
        if (weapon.getId() == null) { //INSERT

            query = "INSERT INTO WEAPON (`TYPE`, GEAR_SCORE, BONUS_ID, BASE_DMG, MAIN_TALENT_ID) "
                         + " VALUES ('"+ weapon.getWeaponType().name()+"',"+weapon.getGearScore()+", "+ bonusId +", "+ weapon.getBaseDamage() +", " + weapon.getMainTalent().getId() + ")";

        } else { //UPDATE

            query = "UPDATE WEAPON SET `TYPE` = '"+ weapon.getWeaponType().name() +"', "
                    + "                      GEAR_SCORE = "+ weapon.getGearScore() + ", "
                    + "                      BONUS_ID = "+ bonusId + ", "
                    + "                      BASE_DMG = "+ weapon.getBaseDamage() + ", "
                    + "                      MAIN_TALENT_ID = "+ weapon.getMainTalent().getId() 
                    + "   WHERE rowid = "+weapon.getId();

        }

        stmt.executeUpdate(query);

        if (weapon.getId() == null) {
            weapon.setId(Long.toString(getInsertedId(stmt)));
        }

        //extra talents
        stmt.executeUpdate("DELETE FROM WEAPON_EXTRA_TALENTS WHERE WEAPON_ID = "+weapon.getId());
        
        for (WeaponTalent weaponTalent : weapon.getExtraTalents()) {
            stmt.executeUpdate("INSERT INTO WEAPON_EXTRA_TALENTS ( WEAPON_ID , WEAPON_TALENT_ID) "
                              +"       VALUES ( "+weapon.getId()+","+ weaponTalent.getId() +" )"  );
        }
             
    }
    
    @Override
    public void saveOrUpdateWeapon(Weapon weapon) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateWeapon(weapon, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    private void fetchModdedWeaponMods(ModdedWeapon moddedWeapon) {
        
        if (moddedWeapon.getId() == null) {
            return;
        }
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            String query = "SELECT m.rowid, mwm.MOD_POSITION, m.* FROM MODS m JOIN MODDED_WEAPON_MODS mwm ON m.rowid = mwm.MOD_ID "
                    + "                         WHERE mwm.MODDED_WEAPON_ID = "+moddedWeapon.getId();


            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                
                WeaponMod mod = (WeaponMod) Class.forName("core.components."+rs.getString("CLASS_TYPE")).newInstance();

                mod.setId(Integer.toString(rs.getInt("rowid")));
                mod.setName(rs.getString("Name"));
                mod.setType(ModType.valueOf(rs.getString("MOD_TYPE")));

                fetchModBonuses(mod);
                
                moddedWeapon.addMod(mod, ModType.valueOf(rs.getString("MOD_POSITION")));
            }

            rs.close();
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
    }
    
    private Weapon fetchWeapon(HashMap<Integer,WeaponTalent> weaponTalents, String weaponId) {
        
        if (weaponId == null) {
            return null;
        }
        
        Weapon weapon = null;
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            HashMap<Integer,Weapon> mappedWeapon = loadWeapons(weaponTalents, weaponId, stmt);
            
            for (Integer i : mappedWeapon.keySet() ){
                weapon = mappedWeapon.get(i);
                break;
            }
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return weapon;
    }
    
    
    private HashMap<Integer,ModdedWeapon> loadModdedWeapons(HashMap<Integer,WeaponTalent> weaponTalents, String moddedWeaponId, Statement stmt) throws Exception {
        HashMap<Integer,ModdedWeapon> ret = new HashMap<Integer,ModdedWeapon>();
        
        String query = "SELECT rowid, * FROM MODDED_WEAPON ";
        if (moddedWeaponId != null) {
            query += " WHERE rowid = "+moddedWeaponId;
        }

        ResultSet rs = stmt.executeQuery(query);
        
        ModdedWeapon moddedWeapon;
        Weapon weapon;
        while (rs.next()) {

            moddedWeapon = new ModdedWeapon();
            moddedWeapon.setId(Integer.toString(rs.getInt("rowid")));

            weapon =fetchWeapon(weaponTalents, Long.toString(rs.getLong("WEAPON_ID")));
            
            if (weapon !=null) {
                moddedWeapon.setWeapon(weapon);
            }
            
            fetchModdedWeaponMods(moddedWeapon);
            
            ret.put(rs.getInt("rowid"), moddedWeapon );
        }

        rs.close();   
        
        return ret;
    }
    
    @Override
    public HashMap<Integer,ModdedWeapon> loadModdedWeapons(HashMap<Integer,WeaponTalent> weaponTalents) {
        HashMap<Integer,ModdedWeapon> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadModdedWeapons(weaponTalents,null,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    private void saveOrUpdateModdedWeapon(ModdedWeapon moddedWeapon, Statement stmt) throws Exception {

        // Weapon first
        if (moddedWeapon == null) {
            return;
        }
        
        saveOrUpdateWeapon(moddedWeapon.getWeapon(), stmt);
        
        
        String query;
        
        if (moddedWeapon.getId() == null) { //INSERT

            query = "INSERT INTO MODDED_WEAPON (WEAPON_ID) "
                         + " VALUES ("+ moddedWeapon.getWeapon().getId() +")";

        } else { //UPDATE

            query = "UPDATE MODDED_WEAPON SET WEAPON_ID = "+ moddedWeapon.getWeapon().getId() 
                    + "   WHERE rowid = "+moddedWeapon.getId();

        }

        stmt.executeUpdate(query);

        if (moddedWeapon.getId() == null) {
            moddedWeapon.setId(Long.toString(getInsertedId(stmt)));
        }

        //Bonuses
        stmt.executeUpdate("DELETE FROM MODDED_WEAPON_MODS WHERE MODDED_WEAPON_ID = "+moddedWeapon.getId());
        
        HashMap<WeaponMod,ModType> modsPosition = moddedWeapon.getModsPosition();
        
        for (WeaponMod mod : moddedWeapon.getMods()) {
            saveOrUpdateMod(mod,stmt);
            stmt.executeUpdate("INSERT INTO MODDED_WEAPON_MODS ( MODDED_WEAPON_ID , MOD_ID, MOD_POSITION) VALUES ( "+moddedWeapon.getId()+","+ mod.getId() +",'"+ modsPosition.get(mod).name() +"')"  );
        }
             
    }
    
    @Override
    public void saveOrUpdateModdedWeapon(ModdedWeapon moddedWeapon) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateModdedWeapon(moddedWeapon, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    private ModdedGear fetchModdedGear(HashMap<Integer,GearSet> gearSets,String moddedGearId) {
        
        if (moddedGearId == null) {
            return null;
        }
        
        ModdedGear moddedGear = null;
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            HashMap<Integer,ModdedGear> mappedModdedGear = loadModdedGears(gearSets, moddedGearId, null, stmt);
            
            for (Integer i : mappedModdedGear.keySet() ){
                moddedGear = mappedModdedGear.get(i);
                break;
            }
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return moddedGear;
    }
    
    private ModdedWeapon fetchModdedWeapon(HashMap<Integer,WeaponTalent> weaponTalents, String moddedWeaponId) {
        
        if (moddedWeaponId == null) {
            return null;
        }
        
        ModdedWeapon moddedWeapon = null;
        
        Connection c = null;
        try { 
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            HashMap<Integer,ModdedWeapon> mappedModdedWeapon = loadModdedWeapons( weaponTalents, moddedWeaponId, stmt);
            
            for (Integer i : mappedModdedWeapon.keySet() ){
                moddedWeapon = mappedModdedWeapon.get(i);
                break;
            }
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return moddedWeapon;
    }
    
    private HashMap<Integer,FullBuild> loadFullBuilds(HashMap<Integer,GearSet> gearSets, HashMap<Integer,WeaponTalent> weaponTalents, String fullBuildId, Statement stmt) throws Exception {
        HashMap<Integer,FullBuild> ret = new HashMap<Integer,FullBuild>();
        
        String query = "SELECT rowid, * FROM BUILD ";
        if (fullBuildId != null) {
            query += " WHERE rowid = "+fullBuildId;
        }

        ResultSet rs = stmt.executeQuery(query);
        
        FullBuild fullBuild;
        ModdedGear backPack;
        ModdedGear bodyArmor;
        ModdedGear gloves;
        ModdedGear holster;
        ModdedGear kneePads;
        ModdedGear mask;
        ModdedWeapon weapon1;
        ModdedWeapon weapon2;
        while (rs.next()) {

            backPack = fetchModdedGear(gearSets, Long.toString(rs.getLong("BACKPACK_ID")));
            bodyArmor = fetchModdedGear(gearSets, Long.toString(rs.getLong("BODYARMOR_ID")));
            gloves = fetchModdedGear(gearSets, Long.toString(rs.getLong("GLOVES_ID")));
            holster = fetchModdedGear(gearSets, Long.toString(rs.getLong("HOLSTER_ID")));
            kneePads = fetchModdedGear(gearSets, Long.toString(rs.getLong("KNEEPADS_ID")));
            mask = fetchModdedGear(gearSets, Long.toString(rs.getLong("MASK_ID")));
            
            fullBuild = new FullBuild(rs.getString("NAME"), backPack, bodyArmor, gloves, holster, kneePads, mask);
            fullBuild.setId(Integer.toString(rs.getInt("rowid")));

            weapon1 = fetchModdedWeapon(weaponTalents, Long.toString(rs.getLong("WEAPON_1_ID")));
            
            if (weapon1 !=null) {
                fullBuild.setWeapon1(weapon1);
            }
            
            weapon2 = fetchModdedWeapon(weaponTalents, Long.toString(rs.getLong("WEAPON_2_ID")));
            
            if (weapon2 !=null) {
                fullBuild.setWeapon2(weapon1);
            }
            
            ret.put(rs.getInt("rowid"), fullBuild );
        }

        rs.close();   
        
        return ret;
    }
    
    @Override
    public HashMap<Integer,FullBuild> loadFullBuilds(HashMap<Integer,GearSet> gearSets, HashMap<Integer,WeaponTalent> weaponTalents) {
        HashMap<Integer,FullBuild> ret = null;
        
        Connection c = null;
        try {
            c = getConnection();
            
            Statement stmt = c.createStatement();
            
            ret = loadFullBuilds(gearSets,weaponTalents,null,stmt);
            
            stmt.close();
        } catch (Exception ex) {
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) {
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
        
        return ret;
    }
    
    
    private void saveOrUpdateFullBuild(FullBuild fullBuild, Statement stmt) throws Exception {

        // gears first
        
        saveOrUpdateModdedGear(fullBuild.getBackpack(), stmt);
        saveOrUpdateModdedGear(fullBuild.getBodyArmor(), stmt);
        saveOrUpdateModdedGear(fullBuild.getGloves(), stmt);
        saveOrUpdateModdedGear(fullBuild.getHolster(), stmt);
        saveOrUpdateModdedGear(fullBuild.getKneepads(), stmt);
        saveOrUpdateModdedGear(fullBuild.getMask(), stmt);
        
        // then weapons
        
        String weapon1Id = "''";
        String weapon2Id = "''";
        
        if (fullBuild.getWeapon1() != null) {
            saveOrUpdateModdedWeapon(fullBuild.getWeapon1(), stmt);
            weapon1Id = fullBuild.getWeapon1().getId();
        }
        
        if (fullBuild.getWeapon2() != null) {
            saveOrUpdateModdedWeapon(fullBuild.getWeapon2(), stmt);
            weapon2Id = fullBuild.getWeapon2().getId();
        }
        
        String query;
        
        if (fullBuild.getId() == null) { //INSERT

            query = "INSERT INTO BUILD (`NAME`, BACKPACK_ID, BODYARMOR_ID, GLOVES_ID, HOLSTER_ID, KNEEPADS_ID, MASK_ID, WEAPON_1_ID, WEAPON_2_ID) "
                         + " VALUES ('"+ fullBuild.getName()+"',"
                         + "          "+ fullBuild.getBackpack().getId() +",   "
                         + "          "+ fullBuild.getBodyArmor().getId() +",   "
                         + "          "+ fullBuild.getGloves().getId() +",   "
                         + "          "+ fullBuild.getHolster().getId() +",   "
                         + "          "+ fullBuild.getKneepads().getId() +",   "
                         + "          "+ fullBuild.getMask().getId() +",   "
                         + "          "+ weapon1Id +",   "
                         + "          "+ weapon2Id +" )";

        } else { //UPDATE

            query = "UPDATE BUILD SET `NAME` = '"+ fullBuild.getName() + "', "
                    + "                BACKPACK_ID =  "+ fullBuild.getBackpack().getId()  +",  "
                    + "                BODYARMOR_ID =  "+ fullBuild.getBodyArmor().getId()  +",  "
                    + "                GLOVES_ID =  "+ fullBuild.getGloves().getId()  +",  "
                    + "                HOLSTER_ID =  "+ fullBuild.getHolster().getId()  +",  "
                    + "                KNEEPADS_ID =  "+ fullBuild.getKneepads().getId()  +",  "
                    + "                MASK_ID =  "+ fullBuild.getMask().getId()  +",  "
                    + "                WEAPON_1_ID =  "+ weapon1Id +",  "
                    + "                WEAPON_2_ID =  "+ weapon2Id                   
                    + "   WHERE rowid = "+fullBuild.getId();

        }

        stmt.executeUpdate(query);

        if (fullBuild.getId() == null) {
            fullBuild.setId(Long.toString(getInsertedId(stmt)));
        }

             
    }
    
    @Override
    public void saveOrUpdateFullBuild(FullBuild fullBuild) {
        
        Connection c = null;
        
        try {
            c = getConnection(); 
            c.setAutoCommit(false);
            
            Statement stmt = c.createStatement();
            
            saveOrUpdateFullBuild(fullBuild, stmt);
            
            c.commit();
            stmt.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
        } finally {
            try {
                if (c != null && !c.isClosed() ) { 
                    c.close();
                }
            } catch (Exception ex) {
                System.err.println( ex.getClass().getName() + ": " + ex.getMessage() );
            }
        }
    }
    
    
    
    
    
}
