package code.mock;

import code.gui.AbsMenu;
import code.gui.EnabledMenu;

public abstract class MockEnabledMenu extends MockInput implements EnabledMenu {

    private AbsMenu parentMenu;
    private String text;

    protected MockEnabledMenu(String _s) {
        text = _s;
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
