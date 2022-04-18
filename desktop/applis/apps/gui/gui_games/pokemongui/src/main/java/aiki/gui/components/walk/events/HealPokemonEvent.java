package aiki.gui.components.walk.events;

import aiki.gui.components.walk.ScenePanel;
import code.gui.events.AbsActionListener;

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
