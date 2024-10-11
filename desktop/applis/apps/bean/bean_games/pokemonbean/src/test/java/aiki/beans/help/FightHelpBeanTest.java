package aiki.beans.help;

import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.Rate;
import code.scripts.confs.PkScriptPages;
import code.util.IdMap;
import code.util.StringMap;
import org.junit.Test;

public final class FightHelpBeanTest extends InitDbFightHelp{

    public static final String CC = "CC";
    public static final int IMG_ANIM = 0;

    @Test
    public void getTrStatistic() {
        assertEq(CC,callFightHelpBeanGetTrStatistic(bean(db()),0));
    }
    @Test
    public void getAnimStatistic() {
        assertEq(one(IMG_ANIM),callFightHelpBeanGetAnimStatistic(bean(db()),0));
    }
    @Test
    public void getAnimAbsorb() {
        assertEq(one(IMG_ANIM),callFightHelpBeanGetAnimAbsorb(bean(db())));
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
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callFightHelpBeanCatchingFormulaGet(bean(db())));
    }
    @Test
    public void formDam() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callFightHelpBeanDamgeFormulaGet(bean(db())));
    }
    @Test
    public void formFlee() {
        assertEq(MessagesDataBaseConstants.DEF_TEMPS_TOUR,callFightHelpBeanFleeingFormulaGet(bean(db())));
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
        assertEq(PkScriptPages.REN_ADD_WEB_HTML_MOVES_DATA_HTML,click());
    }
    @Test
    public void clId1() {
        assertEq(M_DAM,clickId());
    }
    private String click() {
        NaSt b_ = bean(db());
        return toStr(callFightHelpBeanClickDefaultMove(b_,0));
    }
    private String clickId() {
        NaSt b_ = bean(db());
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
        f_.getData().getAnimStatis().addEntry(Statistic.ATTACK.getStatName(), instance(IMG_ANIM));
        f_.getData().setAnimAbsorb(instance(IMG_ANIM));
        f_.getData().setCombos(Instances.newCombos());
        f_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        f_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
        f_.getData().setDamageFormula(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        f_.getData().setRateFleeing(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        f_.getData().setRateCatching(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        f_.getData().addConstNumTest(DataBase.BONUS_BOOST, Rate.one());
        f_.getData().addConstNumTest(DataBase.VALEUR_DEF_STATIS, Rate.one());
        f_.getData().addConstNumTest(DataBase.MIN_HP, Rate.one());
        f_.getData().addConstNumTest(DataBase.STRONG_MOVE, Rate.one());
        f_.getData().addConstNumTest(DataBase.GAIN_BONHEUR_NIV, Rate.one());
        f_.getData().addConstNumTest(DataBase.EVO_BONHEUR, Rate.one());
        return f_;
    }
}
