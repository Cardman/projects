package code.mock;

import code.gui.AbsCustComponent;
import code.gui.EnabledMenu;
import code.util.CustList;
import code.util.IdList;

public abstract class MockEnabledMenu extends MockAbsButton implements EnabledMenu {

    private final IdList<AbsCustComponent> subs = new IdList<AbsCustComponent>();

    private EnabledMenu parentMenu;

    private boolean selected;

    protected MockEnabledMenu(String _s) {
        super(_s);
    }

    public int getSubCount() {
        return subs.size();
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
    public CustList<AbsCustComponent> getItems() {
        return subs;
    }

    @Override
    public int getItemCount() {
        return getSubCount();
    }

    @Override
    public void addMenuItem(EnabledMenu _menuItem) {
        _menuItem.setParentMenu(this);
        subs.add(_menuItem);
    }

    @Override
    public void addMenuItem(AbsCustComponent _menu) {
        subs.add(_menu);
    }

    @Override
    public void removeMenuItem(EnabledMenu _menuItem) {
        _menuItem.setParentMenu(null);
        subs.removeObj(_menuItem);
    }
    @Override
    public void removeMenuItem(AbsCustComponent _component) {
        subs.removeObj(_component);
    }

    @Override
    public void setAccelerator(int _a, int _b) {
        setEnabled(isEnabled());
    }

    @Override
    public void setEnabledMenu(boolean _b) {
        setEnabled(_b);
    }
    @Override
    public EnabledMenu getParentMenu() {
        return parentMenu;
    }
    @Override
    public void setParentMenu(EnabledMenu _par) {
        parentMenu = _par;
    }
}
