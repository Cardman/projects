package aiki.gui.listeners;
import aiki.gui.components.walk.*;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class PokemonSelectionTrading implements ListSelection {

    private ScenePanelMulti window;

    public PokemonSelectionTrading(ScenePanelMulti _window) {
        window = _window;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        window.selectPkTeamTrade();
    }

}
