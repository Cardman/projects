package code.vi.prot.impl.variant;

import code.adv.ValueChangingSecondUtil;
import code.gui.AbstractSelectionListener;
import code.gui.ListSelection;

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
        ValueChangingSecondUtil.act(new DefValueChangingSecondImpl(combo,listener,_e),ItemEvent.SELECTED);
    }

    public ListSelection getListener() {
        return listener;
    }
}
