package cards.gui.panels;

import code.gui.Panel;
/** */
abstract class ScrollableList {
    private Panel container = Panel.newBorder();
    ScrollableList() {
        container.setLoweredBorder();
    }

    public Panel getContainer() {
        return container;
    }
}
