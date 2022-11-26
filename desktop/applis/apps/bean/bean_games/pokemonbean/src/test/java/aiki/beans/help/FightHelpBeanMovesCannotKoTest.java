package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanMovesCannotKoTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.movesCannotKoInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.setCannotKo(true);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newDamagingMoveData());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusMoveData());
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
