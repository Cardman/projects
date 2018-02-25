package code.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public final class Popup extends MouseAdapter {

    private GraphicComboInt grInt;

    public Popup(GraphicComboInt _grInt) {
        grInt = _grInt;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        grInt.popup();
    }
}
