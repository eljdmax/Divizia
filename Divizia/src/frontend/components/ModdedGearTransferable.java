/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.ModdedGear;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author tchabole
 */
public class ModdedGearTransferable implements Transferable {

        public static final DataFlavor MODDEDGEAR_DATA_FLAVOR = new DataFlavor(ModdedGear.class, "java/ModdedGear");
        private ModdedGear moddedGear;

        public ModdedGearTransferable(ModdedGear moddedGear) {
            this.moddedGear = moddedGear;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{MODDEDGEAR_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(MODDEDGEAR_DATA_FLAVOR);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return moddedGear;
        }
    }