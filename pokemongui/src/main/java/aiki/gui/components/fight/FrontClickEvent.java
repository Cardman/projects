package aiki.gui.components.fight;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrontClickEvent extends MouseAdapter {

    private FrontBattle battle;

    public FrontClickEvent(FrontBattle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        battle.openActions();
    }
}
