package aiki.beans.help;

import aiki.beans.CommonBean;
import aiki.beans.TranslatedKey;
import aiki.facade.FacadeGame;
import aiki.fight.moves.effects.EffectCombo;
import aiki.fight.util.ListEffectCombo;
import aiki.instances.Instances;
import code.maths.Rate;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanComboEvtStatTest extends InitDbFightHelp {
    @Test
    public void movesTypesDefWeatherInitTest() {
        CustList<CustList<TranslatedKey>> ls_ = FightHelpBean.comboEvtStatInit(dbTr());
        assertEq(1,ls_.size());
    }
    @Test
    public void init() {
        assertSizeEq(1,callFightHelpBeanComboEvtStatGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR+" - "+M_STA_TR,callFightHelpBeanGetTrComboEvtStat(bean(db()),0));
    }
    @Test
    public void cl() {
        assertEq(CommonBean.REN_ADD_WEB_HTML_COMBO_COMBOS_HTML,clickDest());
    }
    private static String clickDest() {
        return toStr(callFightHelpBeanClickComboEvtStat(bean(db()),0));
    }
    private static FacadeGame dbTr() {
        FacadeGame f_ = db();
        f_.updateTrs();
        return f_;
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().completeMembers(M_DAM, Instances.newDamagingMoveData());
        f_.getData().completeMembers(M_STA, Instances.newStatusMoveData());
        f_.getData().completeMembers(M_DAM_VAR, Instances.newStatusMoveData());
        f_.getData().setCombos(Instances.newCombos());
        EffectCombo c_ = Instances.newEffectCombo();
        c_.setMultEvtRateSecEff(Rate.one());
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_DAM,M_STA), c_));
        f_.getData().getCombos().getEffects().add(new ListEffectCombo(new StringList(M_STA,M_DAM_VAR),Instances.newEffectCombo()));
        f_.getData().completeMembersCombos();
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        return f_;
    }
}
