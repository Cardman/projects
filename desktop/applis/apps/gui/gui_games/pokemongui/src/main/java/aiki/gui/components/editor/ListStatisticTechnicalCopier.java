package aiki.gui.components.editor;


import aiki.fight.enums.*;
import code.util.*;

public final class ListStatisticTechnicalCopier implements AbsTechnicalCopier<IdList<Statistic>> {
    @Override
    public IdList<Statistic> copy(IdList<Statistic> _e) {
        return new IdList<Statistic>(_e);
    }
}
