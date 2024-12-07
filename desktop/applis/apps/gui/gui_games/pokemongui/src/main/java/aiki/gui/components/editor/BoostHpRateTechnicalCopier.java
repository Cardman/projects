package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.maths.*;

public final class BoostHpRateTechnicalCopier implements AbsTechnicalCopier<BoostHpRate>{
    public BoostHpRate copy(BoostHpRate _e){
        return new BoostHpRate(_e.getBoost(), new Rate(_e.getHpRate()));
    }
}
