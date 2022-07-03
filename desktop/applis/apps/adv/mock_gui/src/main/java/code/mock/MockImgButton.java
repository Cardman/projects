package code.mock;

import code.gui.AbsImgButton;
import code.gui.events.AbsActionListener;
import code.util.CustList;

public final class MockImgButton extends MockInput implements AbsImgButton {
    private final CustList<AbsActionListener> absActionListeners = new CustList<AbsActionListener>();
    @Override
    public void addMouseList(AbsActionListener _l) {
        absActionListeners.add(_l);
    }

    public CustList<AbsActionListener> getAbsActionListeners() {
        return absActionListeners;
    }
}
