package code.vi.prot.impl.gui;

import code.gui.AbsPlainButton;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.vi.prot.impl.gui.events.WrActionListener;
import code.util.IdMap;
import code.vi.prot.impl.gui.events.WrAdvActionListener;

import javax.swing.*;

public final class PlainButton extends CustComponent implements AbsPlainButton {
    private final JButton button;
    private final IdMap<AbsActionListener,WrActionListener> mapAction = new IdMap<AbsActionListener, WrActionListener>();
    private final IdMap<AbsAdvActionListener,WrAdvActionListener> mapAdvAction = new IdMap<AbsAdvActionListener, WrAdvActionListener>();
    public PlainButton() {
        button = new JButton();
    }
    public PlainButton(String _b) {
        button = new JButton(_b);
    }

    public boolean isDefaultButton() {
        return button.isDefaultButton();
    }

    public boolean isDefaultCapable() {
        return button.isDefaultCapable();
    }

    public String getText() {
        return button.getText();
    }

    public void setText(String _text) {
        button.setText(_text);
    }

    public void addActionListener(AbsActionListener _l) {
        WrActionListener wr_ = new WrActionListener(_l);
        button.addActionListener(wr_);
        mapAction.addEntry(_l,wr_);
    }

    public void addActionListener(AbsAdvActionListener _l) {
        WrAdvActionListener wr_ = new WrAdvActionListener(_l);
        button.addActionListener(wr_);
        mapAdvAction.addEntry(_l,wr_);
    }

    public boolean isEnabled() {
        return button.isEnabled();
    }
    public void setEnabled(boolean _b) {
        button.setEnabled(_b);
    }

    @Override
    public JComponent getNatComponent() {
        return button;
    }
}
