package aiki.gui.components.editor;

import aiki.fight.util.*;
import code.maths.*;

public final class EfficiencyRateTechnicalCopier implements AbsTechnicalCopier<EfficiencyRate>{
    public EfficiencyRate copy(EfficiencyRate _e){
        return new EfficiencyRate(new Rate(_e.getEff()),new Rate(_e.getHpRate()));
    }
}
