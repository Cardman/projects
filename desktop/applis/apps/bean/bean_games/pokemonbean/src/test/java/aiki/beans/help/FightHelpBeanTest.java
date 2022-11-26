package aiki.beans.help;

import aiki.beans.moves.AikiBeansMovesStd;
import aiki.db.DataBase;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.game.fight.Fight;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.BaseSixtyFourUtil;
import code.maths.Rate;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanTest extends InitDbFightHelp{

    public static final String CC = "CC";
    @Test
    public void getTrStatistic() {
        assertEq(CC,callFightHelpBeanGetTrStatistic(bean(db()),0));
    }
    @Test
    public void getAnimStatistic() {
        assertEq("AAABAAAA",callFightHelpBeanGetAnimStatistic(bean(db()),0));
    }
    @Test
    public void getAnimAbsorb() {
        assertEq("AAABAAAA",callFightHelpBeanGetAnimAbsorb(bean(db())));
    }
    @Test
    public void getStab() {
        assertEq("1",callFightHelpBeanGetStab(bean(db())));
    }
    @Test
    public void getDefaultBoostValue() {
        assertEq(1,callFightHelpBeanDefaultBoostValueGet(bean(db())));
    }
    @Test
    public void getMinHpNotKo() {
        assertEq(Rate.one(),callFightHelpBeanMinHpNotKoGet(bean(db())));
    }
    @Test
    public void strMove() {
        assertEq(Rate.one(),callFightHelpBeanStrongMoveGet(bean(db())));
    }
    @Test
    public void happ() {
        assertEq(Rate.one(),callFightHelpBeanWonHappinessPointsLevelGet(bean(db())));
    }
    @Test
    public void getHappinessPoints() {
        assertEq(1,callFightHelpBeanHappinessPointsGet(bean(db())));
    }
    @Test
    public void formCat() {
        assertEq(Fight.TEMPS_TOUR,callFightHelpBeanCatchingFormulaGet(bean(db())));
    }
    @Test
    public void formDam() {
        assertEq(Fight.TEMPS_TOUR,callFightHelpBeanDamgeFormulaGet(bean(db())));
    }
    @Test
    public void formFlee() {
        assertEq(Fight.TEMPS_TOUR,callFightHelpBeanFleeingFormulaGet(bean(db())));
    }
    @Test
    public void varsCat() {
        assertSizeEq(1,callFightHelpBeanVarCatchingFormulaGet(bean(db())));
    }
    @Test
    public void varsDam() {
        assertSizeEq(1,callFightHelpBeanMapVarGet(bean(db())));
    }
    @Test
    public void varsFlee() {
        assertSizeEq(1,callFightHelpBeanVarFleeingFormulaGet(bean(db())));
    }
    @Test
    public void anims() {
        assertSizeEq(8,callFightHelpBeanStatisticAnimGet(bean(db())));
    }
    @Test
    public void tr() {
        assertEq(M_DAM_TR,callFightHelpBeanGetTrDefaultMove(bean(db())));
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
        Struct b_ = bean(db());
        return toStr(callFightHelpBeanClickDefaultMove(b_,0));
    }
    private String clickId() {
        Struct b_ = bean(db());
        callFightHelpBeanClickDefaultMove(b_,0);
        return getValMoveId(b_);
    }
    private static FacadeGame db() {
        FacadeGame f_ = facade();
        f_.getData().completeMembers(M_DAM,Instances.newDamagingMoveData());
        f_.getData().setDefMove(M_DAM);
        f_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        f_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        f_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic,String>());
        f_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK, CC);
        f_.getData().getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        f_.getData().setAnimAbsorb(BaseSixtyFourUtil.getImageByString("AAABAAAA"));
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        f_.getData().setDamageFormula(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        f_.getData().setRateFleeing(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        f_.getData().setRateCatching(DataBase.VAR_PREFIX+Fight.TEMPS_TOUR);
        f_.getData().addConstNumTest(DataBase.BONUS_BOOST, Rate.one());
        f_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, Rate.one());
        f_.getData().addConstNumTest(DataBase.MIN_HP, Rate.one());
        f_.getData().addConstNumTest(DataBase.STRONG_MOVE, Rate.one());
        f_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, Rate.one());
        f_.getData().addConstNumTest(DataBase.EVO_BONHEUR, Rate.one());
        return f_;
    }
}
