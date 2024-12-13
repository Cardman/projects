package aiki.gui.components.editor;

import aiki.facade.*;
import aiki.game.params.enums.*;
import code.util.*;

public final class NumStrIdRetrieverDifficultyWinPointsFight implements NumStrIdRetriever<DifficultyWinPointsFight> {
    @Override
    public IdMap<DifficultyWinPointsFight, String> infos(FacadeGame _fac) {
        return _fac.getData().getRates();
    }
}
