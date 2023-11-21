package code.vi.prot.impl.gui;

import code.gui.CoreMouseButtons;

import javax.swing.*;
import java.awt.event.MouseEvent;

public final class DefMouseButtons extends CoreMouseButtons {

    public DefMouseButtons(MouseEvent _action) {
        super(SwingUtilities.isLeftMouseButton(_action),SwingUtilities.isMiddleMouseButton(_action),SwingUtilities.isRightMouseButton(_action),_action.getClickCount());

    }

}
