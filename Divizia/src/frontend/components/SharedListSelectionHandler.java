/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frontend.components;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Administrator
 */
public abstract class SharedListSelectionHandler implements ListSelectionListener {
        
    private javax.swing.JList list;

    public SharedListSelectionHandler(javax.swing.JList list) {
        this.list = list;
    }

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel)e.getSource();

        if (!lsm.isSelectionEmpty()) {
            int minIndex = lsm.getMinSelectionIndex();
            int maxIndex = lsm.getMaxSelectionIndex();
            for (int i = minIndex; i <= maxIndex; i++) {
                if (lsm.isSelectedIndex(i)){
                    onValueChanged(list.getModel().getElementAt(i));
                    break;
                }
            }
        }
    }

    public abstract void onValueChanged(Object object);
}   