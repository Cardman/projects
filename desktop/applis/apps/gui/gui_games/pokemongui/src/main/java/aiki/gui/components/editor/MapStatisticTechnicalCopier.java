package aiki.gui.components.editor;


import aiki.fight.enums.*;
import code.util.*;

public final class MapStatisticTechnicalCopier implements AbsTechnicalCopier<IdMap<Statistic,Byte>> {
    @Override
    public IdMap<Statistic,Byte> copy(IdMap<Statistic,Byte> _e) {
        return new IdMap<Statistic,Byte>(_e);
    }
}
