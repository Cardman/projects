package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.facade.FacadeGame;
import aiki.fight.items.ItemForBattle;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanItemsImmuTypesTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.itemsImmuTypesInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanItemsImmuTypesGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrItemsImmuTypes(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        FightHelpBean b_ = bean(db());
        return callFightHelpBeanClickItemsImmuTypes(b_,0);
    }
    private String clickId() {
        FightHelpBean b_ = bean(db());
        callFightHelpBeanClickItemsImmuTypes(b_,0);
        return getValItemId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        ItemForBattle t_ = Instances.newItemForBattle();
        t_.getImmuTypes().add(NULL_REF);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newItemForBattle());
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
