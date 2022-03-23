package code.vi.prot.impl.variant;

import code.gui.AbstractSelectionListener;
import code.gui.FrameUtil;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public final class DefLocalListSelectionListener implements ListSelectionListener, AbstractSelectionListener {
    private final ListSelection listener;

    public DefLocalListSelectionListener(ListSelection _listener){
        listener = _listener;
    }
    public void valueChanged(ListSelectionEvent _e){
        FrameUtil.act(listener,new SelectionInfo(_e.getFirstIndex(),_e.getLastIndex(),false),_e.getValueIsAdjusting());
    }

    public ListSelection getListener() {
        return listener;
    }
}
