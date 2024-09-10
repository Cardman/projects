package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanAbilitiesSentStatisTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringMap<AbilityData> map_ = FightHelpBean.abilitiesSentStatisInit(db().getData());
        StringList ls_ = new StringList();
        FightHelpBean.feed(map_,ls_,new StringList(),new StringList());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitGlTest() {
        StringMap<AbilityData> map_ = FightHelpBean.abilitiesSentStatisInit(dbGlobal().getData());
        StringList ls_ = new StringList();
        FightHelpBean.feed(map_,new StringList(),ls_,new StringList());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitCatTest() {
        StringMap<AbilityData> map_ = FightHelpBean.abilitiesSentStatisInit(dbGlobalWea().getData());
        StringList ls_ = new StringList();
        FightHelpBean.feed(map_,new StringList(),new StringList(),ls_);
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void movesTypesDefWeatherInitWeTest() {
        StringMap<AbilityData> map_ = FightHelpBean.abilitiesSentStatisInit(dbCat().getData());
        StringList ls_ = new StringList();
        FightHelpBean.feed(map_,new StringList(),new StringList(),ls_);
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    @Test
    public void init1() {
        assertSizeEq(1,callFightHelpBeanCopyAbilitiesGet(bean(db())));
    }
    @Test
    public void tr1() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrCopyAbilities(bean(db()),0));
    }
    @Test
    public void cl1() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click1());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId1());
    }
    @Test
    public void init2() {
        assertSizeEq(1,callFightHelpBeanAbilitiesSentBeginOtherGet(bean(dbGlobal())));
    }
    @Test
    public void tr2() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesSentBeginOth(bean(dbGlobal()),0));
    }
    @Test
    public void cl2() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click2());
    }
    @Test
    public void clId2() {
        assertEq(M_DAM,clickId2());
    }
    @Test
    public void init3() {
        assertSizeEq(1,callFightHelpBeanAbilitiesSentBeginWeatherGet(bean(dbGlobalWea())));
    }
    @Test
    public void tr3() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesSentBegin(bean(dbGlobalWea()),0));
    }
    @Test
    public void cl3() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click3());
    }
    @Test
    public void clId3() {
        assertEq(M_DAM,clickId3());
    }
    @Test
    public void init4() {
        assertSizeEq(1,callFightHelpBeanAbilitiesSentStatisGet(bean(dbGlobalWea())));
    }
    @Test
    public void tr4() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrAbilitiesSentStatis(bean(dbGlobalWea()),0));
    }
    @Test
    public void cl4() {
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,click4());
    }
    @Test
    public void clId4() {
        assertEq(M_DAM,clickId4());
    }
    private String click1() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickCopyAbilities(b_,0));
    }
    private String clickId1() {
        NaSt b_ = bean(db());
        callFightHelpBeanClickCopyAbilities(b_,0);
        return getValAbilityId(b_);
    }
    private String click2() {
        NaSt b_ = bean(dbGlobal());
        return toStr(callFightHelpBeanClickAbilitiesSentBeginOth(b_,0));
    }
    private String clickId2() {
        NaSt b_ = bean(dbGlobal());
        callFightHelpBeanClickAbilitiesSentBeginOth(b_,0);
        return getValAbilityId(b_);
    }
    private String click3() {
        NaSt b_ = bean(dbGlobalWea());
        return toStr(callFightHelpBeanClickAbilitiesSentBegin(b_,0));
    }
    private String clickId3() {
        NaSt b_ = bean(dbGlobalWea());
        callFightHelpBeanClickAbilitiesSentBegin(b_,0);
        return getValAbilityId(b_);
    }
    private String click4() {
        NaSt b_ = bean(dbGlobalWea());
        return toStr(callFightHelpBeanClickAbilitiesSentStatis(b_,0));
    }
    private String clickId4() {
        NaSt b_ = bean(dbGlobalWea());
        callFightHelpBeanClickAbilitiesSentStatis(b_,0);
        return getValAbilityId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setCopyingAbility(true);
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
    private static FacadeGame dbGlobal() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
    private static FacadeGame dbGlobalWea() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setEnabledWeather("_");
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
    private static FacadeGame dbCat() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setDisableWeather(true);
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
