package aiki.gui.components.walk.events;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import aiki.gui.components.walk.ScenePanel;

public class SeePokemonDetailEvent extends MouseAdapter {

    private ScenePanel scene;

    public SeePokemonDetailEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void mouseReleased(MouseEvent _e) {
        scene.seePokemonDetail();
    }
}
