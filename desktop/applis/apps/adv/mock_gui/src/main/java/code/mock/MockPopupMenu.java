package code.mock;

import code.gui.*;
import code.util.IdList;

public final class MockPopupMenu extends MockCustComponent implements AbsPopupMenu {

    private final IdList<AbsCustComponent> items = new IdList<AbsCustComponent>();
    @Override
    public void show(AbsCustComponent _c, int _i, int _height) {
        setVisible(true);
    }

    @Override
    public void show(int _i, int _height) {
        setVisible(true);
    }

    @Override
    public void add(AbsCustComponent _c) {
        items.add(_c);
    }

    @Override
    public void remove(AbsCustComponent _c) {
        items.removeObj(_c);
    }
}
