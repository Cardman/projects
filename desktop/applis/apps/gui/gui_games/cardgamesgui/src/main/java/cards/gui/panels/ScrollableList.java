package cards.gui.panels;

import code.gui.AbsPanel;
import code.gui.initialize.AbsCompoFactory;

/** */
abstract class ScrollableList {
    private final AbsPanel container;
    ScrollableList(AbsCompoFactory _absCompoFactory) {
        container = _absCompoFactory.newBorder();
        container.setLoweredBorder();
    }

    public AbsPanel getContainer() {
        return container;
    }
}
