package cards.gui.animations;

import code.gui.ListSelection;
import code.gui.SelectionInfo;

public final class SelectDealPresidentEvent implements ListSelection {
    private final SimulatingPresidentImpl implem;
    public SelectDealPresidentEvent(SimulatingPresidentImpl _i) {
        implem = _i;
    }
    @Override
    public void valueChanged(SelectionInfo _e) {
        implem.applyHistory();
    }
}
