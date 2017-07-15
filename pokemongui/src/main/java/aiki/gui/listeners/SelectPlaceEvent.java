package aiki.gui.listeners;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.fight.Battle;

public class SelectPlaceEvent extends MouseAdapter {

    private Battle battle;

    private byte index;

    public SelectPlaceEvent(Battle _battle, byte _index) {
        battle = _battle;
        index = _index;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.changeFrontPlace(index);
    }
}
