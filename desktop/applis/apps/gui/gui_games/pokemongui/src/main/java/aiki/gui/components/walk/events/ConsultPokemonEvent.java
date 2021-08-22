package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.SelectPokemon;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsMouseListenerRel;

public class ConsultPokemonEvent extends AbsMouseListenerRel {

    private WindowAiki window;

    private FacadeGame facade;

    public ConsultPokemonEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void mouseReleased(AbsMouseLocation _location, AbsCtrlKeyState _keyState, AbsMouseButtons _buttons) {
        int lineBack_ = facade.getLineFirstBox();
//        SelectPokemon select_ = new SelectPokemon(window, facade, false);
        SelectPokemon.setSelectPokemon(window, facade, false, window.getSelectPokemon());
        SelectPokemon.setVisible(window.getSelectPokemon());
        facade.setLinePokemonFirstBox(lineBack_);
        facade.clearSortingFirstBox();
    }
}
