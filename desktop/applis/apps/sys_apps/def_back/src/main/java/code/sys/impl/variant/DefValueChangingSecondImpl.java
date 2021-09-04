package code.sys.impl.variant;

import code.adv.ValueChangingSecond;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

import javax.swing.*;
import java.awt.event.ItemEvent;

public final class DefValueChangingSecondImpl implements ValueChangingSecond {
    private final JComboBox combo;
    private final ListSelection listener;
    private final ItemEvent event;

    public DefValueChangingSecondImpl(JComboBox _combo, ListSelection _listener, ItemEvent _event) {
        this.combo = _combo;
        this.listener = _listener;
        this.event = _event;
    }

    @Override
    public int getValue() {
        return event.getStateChange();
    }

    @Override
    public void act() {
        listener.valueChanged(new SelectionInfo(combo.getSelectedIndex(),combo.getSelectedIndex(),false));
    }
}
