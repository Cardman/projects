package cards.gui.panels;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;

import code.gui.Panel;
/** */
abstract class ScrollableList extends Panel {
    ScrollableList() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
    }
}
