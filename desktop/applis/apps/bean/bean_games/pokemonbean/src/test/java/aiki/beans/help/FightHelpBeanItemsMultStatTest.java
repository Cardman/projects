package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.items.ItemForBattle;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanItemsMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.itemsMultStatInit(db(Statistic.CRITICAL_HIT).getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void itemMultNormal1() {
        assertFalse(callFightHelpBeanItemMultNormal(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemMultNormal2() {
        assertTrue(callFightHelpBeanItemMultNormal(bean(db(Statistic.SPECIAL_ATTACK)),0));
    }
    @Test
    public void itemMultNormalAny1() {
        assertFalse(callFightHelpBeanItemMultNormalAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void itemMultNormalAny2() {
        assertTrue(callFightHelpBeanItemMultNormalAny(bean(db(Statistic.ATTACK))));
    }
    @Test
    public void itemMultSpeed1() {
        assertFalse(callFightHelpBeanItemMultSpeed(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void itemMultSpeed2() {
        assertTrue(callFightHelpBeanItemMultSpeed(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemMultSpeedAny1() {
        assertFalse(callFightHelpBeanItemMultSpeedAny(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void itemMultSpeedAny2() {
        assertTrue(callFightHelpBeanItemMultSpeedAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemMultEvasinessAny1() {
        assertFalse(callFightHelpBeanItemMultEvasinessAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemMultEvasinessAny2() {
        assertTrue(callFightHelpBeanItemMultEvasinessAny(bean(db(Statistic.EVASINESS))));
    }
    @Test
    public void itemMultAccuracy1() {
        assertFalse(callFightHelpBeanItemMultAccuracy(bean(db(Statistic.SPEED)),0));
    }
    @Test
    public void itemMultAccuracy2() {
        assertTrue(callFightHelpBeanItemMultAccuracy(bean(db(Statistic.ACCURACY)),0));
    }
    @Test
    public void itemMultAccuracyAny1() {
        assertFalse(callFightHelpBeanItemMultAccuracyAny(bean(db(Statistic.SPEED))));
    }
    @Test
    public void itemMultAccuracyAny2() {
        assertTrue(callFightHelpBeanItemMultAccuracyAny(bean(db(Statistic.ACCURACY))));
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanItemsMultStatGet(bean(db(Statistic.CRITICAL_HIT))));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrItemsMultStat(bean(db(Statistic.CRITICAL_HIT)),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ITEMS_ITEMFORBATTLE_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        return toStr(callFightHelpBeanClickItemsMultStat(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db(Statistic.CRITICAL_HIT));
        callFightHelpBeanClickItemsMultStat(b_,0);
        return getValItemId(b_);
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        ItemForBattle t_ = Instances.newItemForBattle();
        t_.getMultStat().addEntry(_st,NULL_REF);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newItemForBattle());
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().setCombos(Instances.newCombos());
        return f_;
    }
}
