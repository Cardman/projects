package aiki.gui.components.editor;

import aiki.fight.util.*;

public final class LevelMoveTechnicalCopier implements AbsTechnicalCopier<LevelMove>{
    public LevelMove copy(LevelMove _e){
        LevelMove cp_ = new LevelMove();
        cp_.setLevel(_e.getLevel());
        cp_.setMove(_e.getMove());
        return cp_;
    }
}
