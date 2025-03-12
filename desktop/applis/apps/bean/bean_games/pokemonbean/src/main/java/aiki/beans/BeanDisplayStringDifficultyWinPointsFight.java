package aiki.beans;

import aiki.facade.*;
import aiki.game.params.enums.*;
import code.util.core.*;

public final class BeanDisplayStringDifficultyWinPointsFight implements BeanDisplay<String> {
    private final FacadeGame dataBase;

    public BeanDisplayStringDifficultyWinPointsFight(FacadeGame _d) {
        this.dataBase = _d;
    }

    @Override
    public int display(CommonBean _rend, String _info, int _index) {
        DifficultyWinPointsFight diff_ = DifficultyWinPointsFight.getDiffWonPtsByName(_info);
        _rend.formatMessageDirCts(StringUtil.nullToEmpty(dataBase.getTranslatedDiffWinPts().getVal(diff_)));
        return 1;
    }

}
