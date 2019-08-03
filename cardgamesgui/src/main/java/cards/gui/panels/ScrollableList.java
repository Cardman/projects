package cards.gui.panels;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import code.gui.Panel;
/** */
abstract class ScrollableList {
    private Panel container = Panel.newBorder();
    ScrollableList() {
        container.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }

    public Panel getContainer() {
        return container;
    }
}
