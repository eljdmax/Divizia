/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frontend.components;

import core.build.ModdedWeapon;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 *
 * @author tchabole
 */
public class ModdedWeaponTransferable implements Transferable {

        public static final DataFlavor MODDEDWEAPON_DATA_FLAVOR = new DataFlavor(ModdedWeapon.class, "java/ModdedWeapon");
        private ModdedWeapon moddedWeapon;

        public ModdedWeaponTransferable(ModdedWeapon moddedWeapon) {
            this.moddedWeapon = moddedWeapon;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{MODDEDWEAPON_DATA_FLAVOR};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.equals(MODDEDWEAPON_DATA_FLAVOR);
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
            return moddedWeapon;
        }
    }