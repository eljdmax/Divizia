package frontend.components;

import core.build.ModdedWeapon;
import frontend.main.MainGUI;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tchabole
 */
public class WeaponDisplayGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private ModdedWeapon moddedWeapon;
    private String title;

    
    public WeaponDisplayGUI(MainGUI mainGUI, ModdedWeapon moddedWeapon, String title) {
        this.mainGUI = mainGUI;
        this.moddedWeapon = moddedWeapon;
        this.title = title;
        
        initComponents();
    }
    
    private void initComponents() {
        
        selectedWeaponPanel = new SelectedWeaponPanel(this);
        
        javax.swing.border.TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(title);
        titledBorder.setTitleFont( MainGUI.DEFAULT_FONT_BOLD );
        this.setBorder(titledBorder);
        this.setLayout(new java.awt.BorderLayout());

        
        selectedWeaponPanel.setTransferHandler(new ModdedWeaponListTransferHandler());
        this.add(selectedWeaponPanel,java.awt.BorderLayout.CENTER);


    }
    
    public void refresh() {

        this.selectedWeaponPanel.refresh();
        
    }
    
    private SelectedWeaponPanel selectedWeaponPanel;

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public ModdedWeapon getModdedWeapon() {
        return moddedWeapon;
    }


    public void updateModdedWeapon(ModdedWeapon moddedWeapon) {
        this.moddedWeapon = moddedWeapon;
        this.selectedWeaponPanel.refresh();
    }
    
   
    
}
