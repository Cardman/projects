package cards.gui.panels;

import code.gui.AbsCustComponent;
import code.util.AbsBasicTreeMap;

public final class CoordsHandsMap extends AbsBasicTreeMap<CoordsHands, AbsCustComponent> {
    @Override
    protected int compare(CoordsHands _one, CoordsHands _two) {
        return _one.cmp(_two);
    }
}
