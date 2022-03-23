package code.vi.prot.impl.variant;

import code.gui.AbstractSelectionListener;
import code.gui.FrameUtil;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public final class DefLocalItemListener implements ItemListener, AbstractSelectionListener {
    private final JComboBox combo;
    private final ListSelection listener;
    DefLocalItemListener(JComboBox _combo,ListSelection _listener){
        combo = _combo;
        listener = _listener;
    }
    public void itemStateChanged(ItemEvent _e){
        FrameUtil.act(listener,new SelectionInfo(combo.getSelectedIndex(),combo.getSelectedIndex(),false),_e.getStateChange(),ItemEvent.SELECTED);
    }

    public ListSelection getListener() {
        return listener;
    }
}
