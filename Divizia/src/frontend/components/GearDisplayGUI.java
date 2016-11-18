package frontend.components;

import core.build.ModdedGear;
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
public class GearDisplayGUI extends javax.swing.JPanel {
    private final MainGUI mainGUI;
    private ModdedGear moddedGear;
    private String gearType;
    
    public GearDisplayGUI(MainGUI mainGUI, ModdedGear moddedGear, String gearType) {
        this.mainGUI = mainGUI;
        this.moddedGear = moddedGear;
        this.gearType = gearType;
        
        
        initComponents();
    }
    
    private void initComponents() {
        
        selectedGearPanel = new SelectedGearPanel();
        gearLabel = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        gearList = new GearList();
        
        
        javax.swing.border.TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(gearType);
        titledBorder.setTitleFont( MainGUI.DEFAULT_FONT_BOLD );
        this.setBorder(titledBorder);
        this.setLayout(new java.awt.GridLayout(0, 2));

        selectedGearPanel.setLayout(new java.awt.BorderLayout());
        if (this.moddedGear != null) {
            gearLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            gearLabel.setFont(MainGUI.DEFAULT_FONT);
            gearLabel.setText(this.moddedGear.displayText());
            selectedGearPanel.add(gearLabel);
        } 
        
        this.add(selectedGearPanel,java.awt.BorderLayout.CENTER);

        gearList.setFont(MainGUI.DEFAULT_FONT);
        
        scrollPane.setViewportView(gearList);

        this.add(scrollPane);

    }
    
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JLabel gearLabel;
    private GearList gearList;
    private SelectedGearPanel selectedGearPanel;

    class SelectedGearPanel extends javax.swing.JPanel {
        
        SelectedGearPanel() {
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    if (moddedGear != null) {
                        mainGUI.getGearFormPanel().updateModdedGear(moddedGear, gearType);
                    }
                }
            });
        }
    }
    
    class GearList extends javax.swing.JList {
        
        GearList() {
            
            setModel(new javax.swing.AbstractListModel() {
                List<ModdedGear> moddedGears =  mainGUI.getModdedGears(gearType);
                public int getSize() { return moddedGears.size(); }
                public Object getElementAt(int i) { return moddedGears.get(i); }
            });
            
            addMouseListener(new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    javax.swing.JList theList = (javax.swing.JList) e.getSource();
                    int index = theList.locationToIndex(e.getPoint());
                    
                    if (index >= 0) {
                        ModdedGear theModdedGear = (ModdedGear) theList.getModel().getElementAt(index);
                        mainGUI.getGearFormPanel().updateModdedGear(theModdedGear, gearType);
                    }
                }
            });
        }
    }
    
}
