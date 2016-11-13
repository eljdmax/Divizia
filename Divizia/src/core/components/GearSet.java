/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.components;

import java.util.List;

/**
 *
 * @author tchabole
 */
public abstract class GearSet {
    
    protected String id = null;
    protected String name;
    
    public String getName() {
        return this.name;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
    public abstract List<PropValue> getSetBonus(int pieces);
    
    // Set bonuses ??
}
