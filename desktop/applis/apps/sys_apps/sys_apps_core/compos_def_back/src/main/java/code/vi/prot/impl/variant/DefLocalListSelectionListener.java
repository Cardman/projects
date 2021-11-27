package code.vi.prot.impl.variant;

import code.adv.ValueChangingUtil;
import code.gui.AbstractSelectionListener;
import code.gui.ListSelection;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class DefLocalListSelectionListener implements ListSelectionListener, AbstractSelectionListener {
    private final ListSelection listener;

    public DefLocalListSelectionListener(ListSelection _listener){
        listener = _listener;
    }
    public void valueChanged(ListSelectionEvent _e){
        ValueChangingUtil.act(new DefValueChangingImpl(listener,_e));
    }

    public ListSelection getListener() {
        return listener;
    }
}
