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
        int lineBack_ = facade.getLineFirstBox();
//        SelectPokemon select_ = new SelectPokemon(window, facade, false);
        SelectPokemon.setSelectPokemon(window, facade, false, window.getSelectPokemon());
        SelectPokemon.setVisible(window.getSelectPokemon());
        facade.setLinePokemonFirstBox(lineBack_);
        facade.clearSortingFirstBox();
    }
}
