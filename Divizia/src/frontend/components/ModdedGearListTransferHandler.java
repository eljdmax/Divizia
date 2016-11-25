/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.ModdedGear;
import core.components.Mod;
import frontend.main.MainGUI;
import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

/**
 *
 * @author tchabole
 */
public class ModdedGearListTransferHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferHandler.TransferSupport support) {
            SelectedGearPanel droppedPanel = getDroppedPanel(support);
            if ( droppedPanel ==null ||  !support.isDataFlavorSupported(ModdedGearTransferable.MODDEDGEAR_DATA_FLAVOR) ) {
                return false;
            }
            
            return  droppedPanel.canReceiveGear(getImportedGear(support));
        }

        private ModdedGear getImportedGear(TransferHandler.TransferSupport support) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(ModdedGearTransferable.MODDEDGEAR_DATA_FLAVOR);
                if (value instanceof ModdedGear) {
                    return ((ModdedGear) value);
                }
            } catch (Exception exp) {
                exp.printStackTrace();
            }
            return null;
        }
        
        private SelectedGearPanel getDroppedPanel(TransferHandler.TransferSupport support) {
            if (support.getComponent() instanceof SelectedGearPanel) {
                return ((SelectedGearPanel) support.getComponent());
            }
            return null;
        }
        
        @Override
        public boolean importData(TransferHandler.TransferSupport support) {
            boolean accept = false;
            if (canImport(support)) {
                ModdedGear moddedGear = getImportedGear(support);
                
                SelectedGearPanel droppedPanel = getDroppedPanel(support);
                if (droppedPanel != null) {
                    droppedPanel.getDisplayParent().updateModdedGear(moddedGear);
                    accept = true;
                }
            }
            return accept;
        }

        @Override
        public int getSourceActions(JComponent c) {
            return DnDConstants.ACTION_COPY_OR_MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            Transferable t = null;
            if (c instanceof GearDisplayGUI.GearList) {
                GearDisplayGUI.GearList list = (GearDisplayGUI.GearList) c;
                Object value = list.getSelectedValue();
                if (value instanceof ModdedGear) {
                    ModdedGear moddedGear = (ModdedGear) value;
                    t = new ModdedGearTransferable(moddedGear);
                }
            }
            return t;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            // Here you need to decide how to handle the completion of the transfer,
            // should you remove the item from the list or not...
        }
    }
