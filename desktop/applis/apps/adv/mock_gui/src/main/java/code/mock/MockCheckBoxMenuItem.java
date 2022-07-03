package code.mock;

import code.gui.AbsCheckBoxMenuItem;
import code.gui.AbsMenu;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsAdvActionListener;
import code.util.CustList;

public final class MockCheckBoxMenuItem extends MockInput implements AbsCheckBoxMenuItem {

    private AbsMenu parentMenu;
    private final CustList<AbsActionListener> actionListeners = new CustList<AbsActionListener>();
    private final CustList<AbsAdvActionListener> advActionListeners = new CustList<AbsAdvActionListener>();
    private String text;
    private boolean selected;
    public MockCheckBoxMenuItem() {
        this("");
    }
    public MockCheckBoxMenuItem(String _s) {
        this(_s,false);
    }
    public MockCheckBoxMenuItem(String _s, boolean _b) {
        text=_s;
        selected = _b;
    }
    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean _b) {
        selected = _b;
    }

    @Override
    public void setAccelerator(char _c) {
        setEnabled(isEnabled());
    }

    @Override
    public void setAccelerator(String _s) {
        setAccelerator(' ');
    }

    @Override
    public void setAccelerator(int _a, int _b) {
        setAccelerator(' ');
    }

    @Override
    public void addActionListener(AbsActionListener _l) {
        actionListeners.add(_l);
    }

    public CustList<AbsActionListener> getActionListeners() {
        return actionListeners;
    }

    @Override
    public void addActionListener(AbsAdvActionListener _l) {
        advActionListeners.add(_l);
    }

    public CustList<AbsAdvActionListener> getAdvActionListeners() {
        return advActionListeners;
    }

    @Override
    public void setEnabledMenu(boolean _b) {
        setEnabled(_b);
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String _s) {
        text = _s;
    }

    @Override
    public AbsMenu getParentMenu() {
        return parentMenu;
    }

    @Override
    public void setParentMenu(AbsMenu _par) {
        parentMenu = _par;
    }
}
