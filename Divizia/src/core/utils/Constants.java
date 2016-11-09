/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core.utils;

/**
 *
 * @author tchabole
 */
public enum Constants {
    
    SPACE("//"),
    NEWLINE("\n");
    
    
    private String value;
    
    private Constants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
    
}
