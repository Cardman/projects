package aiki.beans.help;

import aiki.beans.moves.AikiBeansMovesStd;
import aiki.facade.FacadeGame;
import aiki.fight.moves.DamagingMoveData;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanDeleteStatusMoveTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.deleteStatusMoveInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanDeleteStatusMoveGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrDeleteStatusMove(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(AikiBeansMovesStd.WEB_HTML_MOVES_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickDeleteStatusMove(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickDeleteStatusMove(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        DamagingMoveData t_ = Instances.newDamagingMoveData();
        t_.getDeletedStatus().add(NULL_REF);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newStatusMoveData());
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
