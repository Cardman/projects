package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.abilities.AbilityData;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.instances.Instances;
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
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        AbilityData t_ = Instances.newAbilityData();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        e_.setCopyingAbility(true);
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        f_.getData().completeMembers(M_STA, Instances.newAbilityData());
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
        f_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedAbilities().getVal(EN).addEntry(M_STA,M_STA_TR);
        return f_;
    }
}
