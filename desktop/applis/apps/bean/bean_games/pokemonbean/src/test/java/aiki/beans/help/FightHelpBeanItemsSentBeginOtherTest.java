package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.effects.EffectWhileSendingWithStatistic;
import aiki.fight.items.ItemForBattle;
import aiki.instances.Instances;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
import org.junit.Test;

public final class FightHelpBeanItemsSentBeginOtherTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        StringList ls_ = FightHelpBean.itemsSentBeginOtherInit(db().getData());
        assertEq(1,ls_.size());
        assertTrue(StringUtil.contains(ls_,M_DAM));
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        ItemForBattle t_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic e_ = Instances.newEffectWhileSendingWithStatistic();
        t_.getEffectSending().add(e_);
        f_.getData().completeMembers(M_DAM, t_);
        ItemForBattle i_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic e2_ = Instances.newEffectWhileSendingWithStatistic();
        e2_.setEnabledWeather("_");
        e2_.setDisableWeather(false);
        i_.getEffectSending().add(e2_);
        f_.getData().completeMembers(M_DAM_BAD, i_);
        f_.getData().completeMembers(M_STA, Instances.newBall());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newItemForBattle());
        ItemForBattle w_ = Instances.newItemForBattle();
        EffectWhileSendingWithStatistic e3_ = Instances.newEffectWhileSendingWithStatistic();
        e3_.setEnabledWeather("");
        e3_.setDisableWeather(true);
        w_.getEffectSending().add(e3_);
        f_.getData().completeMembers(M_WEA, w_);
        f_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        f_.getData().getTranslatedItems().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        return f_;
    }
}
