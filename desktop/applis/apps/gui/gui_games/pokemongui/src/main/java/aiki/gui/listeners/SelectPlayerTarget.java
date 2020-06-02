package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class SelectPlayerTarget extends MouseAdapter {

    private Battle battle;

    private byte number;

    private int index;

    public SelectPlayerTarget(Battle _battle, byte _number, int _index) {
        battle = _battle;
        number = _number;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _arg0) {
        battle.choosePlayerTarget(number, index);
    }
}
