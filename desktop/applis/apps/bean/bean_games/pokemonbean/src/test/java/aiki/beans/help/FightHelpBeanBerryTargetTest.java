package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.items.Berry;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanBerryTargetTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.berryTargetInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanBerryTargetGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrBerryTarget(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_BERRY_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickBerryTarget(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickBerryTarget(b_,0);
        return getValItemId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        Berry t_ = Instances.newBerry();
        t_.setCategoryBoosting("_");
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newBerry());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
