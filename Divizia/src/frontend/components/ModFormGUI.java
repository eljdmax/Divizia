package frontend.components;

import core.build.ModdedGear;
import core.components.Mod;
import core.components.PropValue;
import core.components.Property;
import core.utils.Constants;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tchabole
 */
public class ModFormGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private Mod mod;
    
    public ModFormGUI(MainGUI mainGUI) {
        this.mainGUI = mainGUI;
        
        
        initComponents();
    }
    
    private void initComponents() {
        
        propertyListGUI = new ArrayList<PropertyGUI>();
        
        this.setLayout(new java.awt.GridLayout(0, 1));
        
        PropertyGUI attribPanel;
        PropValue prop;
        for ( int i =0; i < Constants.MAX_MOD_ATTRIBUTES; i ++) {
            prop = new PropValue();
            attribPanel = new PropertyGUI(mainGUI,prop);
            propertyListGUI.add(attribPanel);
            this.add(attribPanel);
        }
        
    }
    
    public Property[] getPropertyList() {
        //if (mod == null) {
            return mainGUI.getPropertyList(Constants.GEAR_MAJ);
        //}
    }
    
    private List<PropertyGUI> propertyListGUI;
    
}
