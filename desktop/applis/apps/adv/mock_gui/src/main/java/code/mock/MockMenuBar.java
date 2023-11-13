package code.mock;

import code.gui.EnabledMenu;
import code.gui.AbsMenuBar;
import code.util.IdList;

public final class MockMenuBar extends MockCustComponent implements AbsMenuBar {
    private final IdList<EnabledMenu> menus = new IdList<EnabledMenu>();
    @Override
    public int getMenuCount() {
        return menus.size();
    }

    @Override
    public void add(EnabledMenu _m) {
        menus.add(_m);
    }

    @Override
    public void remove(EnabledMenu _m) {
        menus.removeObj(_m);
    }
}
