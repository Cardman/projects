package aiki.gui.listeners;
import aiki.gui.components.walk.ScenePanel;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class PokemonSelectionItems extends ListSelection {

    private ScenePanel window;

    public PokemonSelectionItems(ScenePanel _window) {
        window = _window;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        window.selectPokemon();
    }

}
