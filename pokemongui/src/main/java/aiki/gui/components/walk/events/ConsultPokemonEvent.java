package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.facade.FacadeGame;
import aiki.gui.MainWindow;
import aiki.gui.dialogs.SelectPokemon;

public class ConsultPokemonEvent extends MouseAdapter {

    private MainWindow window;

    private FacadeGame facade;

    public ConsultPokemonEvent(MainWindow _window, FacadeGame _facade) {
        window = _window;
        facade = _facade;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        int lineBack_ = facade.getLineFirstBox();
//        SelectPokemon select_ = new SelectPokemon(window, facade, false);
        SelectPokemon.setSelectPokemon(window, facade, false);
        SelectPokemon.setVisible();
        facade.setLinePokemonFirstBox(lineBack_);
        facade.clearSortingFirstBox();
    }
}
