/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tchabole
 */
public abstract class Mod {
    
    protected String id = null;
    
    protected String name;
    protected ModType type;
    protected List<PropValue> bonus;
    
    public Mod() {
        this(null,null);
    }
    
    public Mod(String name, ModType type) {
        this.name = name;
        this.type = type;
        
        this.bonus = new ArrayList<PropValue>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public void clearBonus() {
        this.bonus.clear();
    }
    
    public void addBonus(PropValue prop){
        if (prop == null) {
            return;
        }
        bonus.add(prop);
    }
    
    public String getName() {
        return name;
    }

    public ModType getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(ModType type) {
        this.type = type;
    }
    
    public List<PropValue> getBonus() {
        return bonus;
    }
    
    public String printAllDetails() {
        String ret = name + " ( "+ type.name() +" ) : ";
        
        for (PropValue prop : bonus) {
            ret += "\n\t"+prop.toString();
        }
        
        return ret;
    }
    
    @Override
    public String toString() {
        String ret = name + " [ "+ type.name() +" ]";
        
        return ret;
    }
}
