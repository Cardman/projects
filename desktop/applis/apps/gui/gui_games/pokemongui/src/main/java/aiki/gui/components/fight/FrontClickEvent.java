package aiki.gui.components.fight;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;


public class FrontClickEvent extends AbsMouseListenerRel {

    private FrontBattle battle;

    public FrontClickEvent(FrontBattle _battle) {
        battle = _battle;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        battle.openActions();
    }
}
