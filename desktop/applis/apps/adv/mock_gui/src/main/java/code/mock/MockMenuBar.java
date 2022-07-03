package code.mock;

import code.gui.AbsMenu;
import code.gui.AbsMenuBar;
import code.util.IdList;

public final class MockMenuBar extends MockCustComponent implements AbsMenuBar {
    private final IdList<AbsMenu> menus = new IdList<AbsMenu>();
    @Override
    public int getMenuCount() {
        return menus.size();
    }

    @Override
    public void add(AbsMenu _m) {
        menus.add(_m);
    }

    @Override
    public void remove(AbsMenu _m) {
        menus.removeObj(_m);
    }
}
