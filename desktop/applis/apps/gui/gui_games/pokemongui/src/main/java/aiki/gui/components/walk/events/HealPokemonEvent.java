package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.AbsMouseButtons;
import code.gui.AbsCtrlKeyState;
import code.gui.AbsMouseLocation;
import code.gui.events.AbsActionListener;
import code.gui.events.AbsMouseListenerRel;

public class HealPokemonEvent implements AbsActionListener {

    private ScenePanel scene;

    public HealPokemonEvent(ScenePanel _scene) {
        scene = _scene;
    }

    @Override
    public void action() {
        scene.healPokemon();
    }
}
