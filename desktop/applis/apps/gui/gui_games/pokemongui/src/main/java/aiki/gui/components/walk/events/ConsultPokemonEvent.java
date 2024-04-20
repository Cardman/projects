package aiki.gui.components.walk.events;

import aiki.facade.FacadeGame;
import aiki.gui.WindowAiki;
import aiki.gui.dialogs.SelectPokemon;
import code.gui.events.AbsActionListener;

public class ConsultPokemonEvent implements AbsActionListener {

    private WindowAiki window;

    private FacadeGame facade;

    public ConsultPokemonEvent(WindowAiki _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void action() {
//        SelectPokemon select_ = new SelectPokemon(window, facade, false);
        SelectPokemon.setSelectPokemon(window, facade, false, window.getSelectPokemon(), true);
    }

    public static void consult(int _lineBack, FacadeGame _facade) {
        _facade.setLinePokemonFirstBox(_lineBack);
        _facade.clearSortingFirstBox();
    }
}
