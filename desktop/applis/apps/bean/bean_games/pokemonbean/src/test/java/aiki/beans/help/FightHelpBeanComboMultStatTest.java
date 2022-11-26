package aiki.beans.help;

import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.moves.effects.EffectTeam;
import aiki.fight.util.ListEffectCombo;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanComboMultStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        CustList<StringList> ls_ = FightHelpBean.comboMultStatInit(db(Statistic.CRITICAL_HIT).getData(),EN);
        assertEq(2,ls_.size());
    }
    private static FacadeGame db(Statistic _st) {
        FacadeGame f_ = facade();
        f_.getData().completeMembers(M_DAM, Instances.newDamagingMoveData());
        f_.getData().completeMembers(M_STA, Instances.newStatusMoveData());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusMoveData());
        f_.getData().setCombos(Instances.newCombos());
        EffectCombo c_ = Instances.newEffectCombo();
        EffectTeam t_ = Instances.newEffectTeam();
        t_.getMultStatisticFoe().addEntry(_st,Rate.one());
        c_.getTeamMove().add(t_);
        EffectCombo c2_ = Instances.newEffectCombo();
        EffectTeam t2_ = Instances.newEffectTeam();
        t2_.getMultStatisticFoe().addEntry(_st,Rate.one());
        c2_.getTeamMove().add(t2_);
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_DAM,M_STA), c_));
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_DAM,M_DAM_VAR),c2_));
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_STA,M_DAM_VAR),Instances.newEffectCombo()));
        f_.getData().completeMembersCombos();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
