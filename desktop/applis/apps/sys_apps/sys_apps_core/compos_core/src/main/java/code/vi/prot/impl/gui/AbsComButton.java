package code.vi.prot.impl.gui;

import code.gui.AbsButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsActionListenerAct;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;

public abstract class AbsComButton extends CustComponent implements AbsButton {
    private final IdMap<AbsActionListener, WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private final IdMap<AbsAdvActionListener, WrAdvActionListener> mapAdvAction = new IdMap<AbsAdvActionListener, WrAdvActionListener>();

    public String getText() {
        return button().getText();
    }

    public void setText(String _text) {
        button().setText(_text);
    }

    public void removeActionListener(AbsActionListener _mouseListener) {
        WrActionListener wr_ = mapAction.getVal(_mouseListener);
        button().removeActionListener(wr_);
        mapAction.removeKey(_mouseListener);
    }

    @Override
    public void removeActionListener(AbsAdvActionListener _list) {
        WrAdvActionListener wr_ = mapAdvAction.getVal(_list);
        button().removeActionListener(wr_);
        mapAdvAction.removeKey(_list);
    }

    @Override
    public void removeActionListenerMap(AbsAdvActionListener _list) {
        mapAdvAction.removeKey(_list);
    }

    @Override
    public CustList<AbsActionListener> getActionListeners() {
        return mapAction.getKeys();
    }

    @Override
    public void addActionListener(AbsActionListenerAct _c, AbsActionListener _list) {
        WrActionListener wr_ = new WrActionListener(_c,_list);
        button().addActionListener(wr_);
        mapAction.addEntry(_list,wr_);
    }

    public void addActionListener(AbsActionListener _l) {
        WrActionListener wr_ = new WrActionListener(_l);
        button().addActionListener(wr_);
        mapAction.addEntry(_l,wr_);
    }

    @Override
    public void addActionListenerMap(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_l);
        mapAdvAction.addEntry(_l,wr_);
    }

    public void addActionListener(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_l);
        button().addActionListener(wr_);
        mapAdvAction.addEntry(_l,wr_);
    }

    @Override
    public JComponent getNatComponent() {
        return button();
    }

    protected abstract AbstractButton button();
}
