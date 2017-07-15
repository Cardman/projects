package aiki.gui.components.walk;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.FacadeGame;
import aiki.gui.dialogs.SelectHealedMove;

public class HealedMoveEvent extends MouseAdapter {

    private SelectHealedMove dialog;

    private FacadeGame facade;

    private String key;

    public HealedMoveEvent(SelectHealedMove _dialog, FacadeGame _facade, String _key) {
        dialog = _dialog;
        facade = _facade;
        key = _key;
    }

    @Override
    public void mouseReleased(MouseEvent _arg0) {
        facade.healMove(key);
        dialog.closeWindow();
    }
}
