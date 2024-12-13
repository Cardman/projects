package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.fight.pokemon.enums.*;
import code.util.*;

public final class NumStrIdRetrieverExpType implements NumStrIdRetriever<ExpType> {
    @Override
    public IdMap<ExpType, String> infos(FacadeGame _fac) {
        return _fac.getData().getExpGrowth();
    }
}
