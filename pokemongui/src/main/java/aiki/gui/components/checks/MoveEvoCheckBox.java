package aiki.gui.components.checks;
import aiki.gui.components.walk.ScenePanel;

public final class MoveEvoCheckBox extends CheckBox {

    private ScenePanel window;

    public MoveEvoCheckBox(String _key, String _text, boolean _selected, ScenePanel _window) {
        super(_key, _text, _selected);
        window = _window;
    }

    @Override
    protected void processKey(String _key) {
        window.learnMove(_key);
    }

}
