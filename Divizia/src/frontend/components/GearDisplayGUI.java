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
        
        selectedGearPanel = new SelectedGearPanel(this);
        scrollPane = new javax.swing.JScrollPane();
        gearList = new GearList();
        
        
        javax.swing.border.TitledBorder titledBorder = javax.swing.BorderFactory.createTitledBorder(gearType);
        titledBorder.setTitleFont( MainGUI.DEFAULT_FONT_BOLD );
        this.setBorder(titledBorder);
        this.setLayout(new java.awt.GridLayout(0, 2));

        
        selectedGearPanel.setTransferHandler(new ModdedGearListTransferHandler());
        this.add(selectedGearPanel,java.awt.BorderLayout.CENTER);

        gearList.setFont(MainGUI.DEFAULT_FONT);
        gearList.setDragEnabled(true);
        gearList.setTransferHandler(new ModdedGearListTransferHandler());
        
        scrollPane.setViewportView(gearList);

        this.add(scrollPane);

    }
    
    public void refresh() {

        this.selectedGearPanel.refresh();
        this.gearList.setModel(getGearListModel());
        
    }
    
    private javax.swing.JScrollPane scrollPane;
    private GearList gearList;
    private SelectedGearPanel selectedGearPanel;

    public MainGUI getMainGUI() {
        return mainGUI;
    }

    public ModdedGear getModdedGear() {
        return moddedGear;
    }

    public String getGearType() {
        return gearType;
    }

    public void updateModdedGear(ModdedGear moddedGear) {
        this.moddedGear = moddedGear;
        this.selectedGearPanel.refresh();
    }
    
    private javax.swing.AbstractListModel getGearListModel() {
        return new javax.swing.AbstractListModel() {
                List<ModdedGear> moddedGears =  mainGUI.getModdedGears(gearType);
                public int getSize() { return moddedGears.size(); }
                public Object getElementAt(int i) { return moddedGears.get(i); }
            };
    }
    
    public class GearList extends javax.swing.JList {
        
        GearList() {
            
            setModel(getGearListModel());
            
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
