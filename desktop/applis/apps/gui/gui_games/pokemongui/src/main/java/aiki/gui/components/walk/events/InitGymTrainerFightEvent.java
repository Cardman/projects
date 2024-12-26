package aiki.gui.components.walk.events;

import aiki.gui.components.walk.*;
import aiki.map.levels.*;
import aiki.util.*;
import code.gui.events.*;

public final class InitGymTrainerFightEvent implements AbsActionListener {
    private final ScenePanel scenePanel;
    private final LevelIndoorGym level;
    private final Coords coords;
    public InitGymTrainerFightEvent(ScenePanel _sc, LevelIndoorGym _l, Coords _c) {
        scenePanel = _sc;
        level = _l;
        coords = _c;
    }

    @Override
    public void action() {
        scenePanel.initGymTrainerFight(level,coords);
    }
}
