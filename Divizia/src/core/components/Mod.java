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
    public String name;
    public ModType type;
    List<PropValue> bonus;
    
    public Mod(String name, ModType type) {
        this.name = name;
        this.type = type;
        
        this.bonus = new ArrayList<PropValue>();
    }

    public void addBonus(PropValue prop){
        bonus.add(prop);
    }
    
    public String getName() {
        return name;
    }

    public ModType getType() {
        return type;
    }

    public List<PropValue> getBonus() {
        return bonus;
    }
}
