package aiki.gui.listeners;
import aiki.gui.components.walk.ScenePanel;
import code.gui.ListSelection;
import code.gui.SelectionInfo;

public class PokemonSelectionMoveTutor implements ListSelection {

    private ScenePanel window;

    public PokemonSelectionMoveTutor(ScenePanel _window) {
        window = _window;
    }

    @Override
    public void valueChanged(SelectionInfo _e) {
        window.selectPokemonMoveTutor();
    }

}
