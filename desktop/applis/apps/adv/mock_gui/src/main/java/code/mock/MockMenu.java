package code.mock;

import code.gui.*;
import code.util.CustList;
import code.util.IdList;

public final class MockMenu extends MockEnabledMenu implements AbsMenu {

    private final IdList<EnabledMenu> subs = new IdList<EnabledMenu>();

    public MockMenu() {
        this("");
    }

    public MockMenu(String _s) {
        super(_s);
    }

    public int getSubCount() {
        return subs.size();
    }

    @Override
    public CustList<EnabledMenu> getItems() {
        return subs;
    }

    @Override
    public void addMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        subs.add(_menuItem);
    }

    @Override
    public void addSeparator() {
        getItemCount();
    }

    @Override
    public void addMenuItem(AbsCheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(this);
        subs.add(_menuItem);
    }

    @Override
    public void addMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(this);
        subs.add(_menuItem);
    }

    @Override
    public void removeMenuItem(AbsMenu _menuItem) {
        _menuItem.setParentMenu(null);
        subs.removeObj(_menuItem);
    }

    @Override
    public void removeMenuItem(AbsMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        subs.removeObj(_menuItem);
    }

    @Override
    public void removeMenuItem(AbsCheckBoxMenuItem _menuItem) {
        _menuItem.setParentMenu(null);
        subs.removeObj(_menuItem);
    }

    @Override
    public int getItemCount() {
        return getSubCount();
    }

}
