package aiki.beans.status;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.db.*;
import aiki.facade.*;
import aiki.facade.enums.*;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.effects.*;
import aiki.fight.status.*;
import aiki.fight.status.effects.*;
import aiki.game.fight.*;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.images.*;
import code.maths.*;
import code.util.*;
import code.util.core.BoolVal;

public abstract class InitDbStatusSet extends InitDbConstr {

    public static final String S_STA_00 = "S_STA_00";
    public static final String S_STA_01 = "S_STA_01";
    public static final String S_STA_02 = "S_STA_02";
    public static final String S_STA_03 = "S_STA_03";
    public static final String S_STA_04 = "S_STA_04";
    public static final String S_STA_05 = "S_STA_05";
    public static final String S_STA_06 = "S_STA_06";
    public static final String S_STA_07 = "S_STA_07";
    public static final String S_STA_08 = "S_STA_08";
    public static final String S_STA_09 = "S_STA_09";
    public static final String S_STA_10 = "S_STA_10";
    public static final String S_STA_11 = "S_STA_11";
    public static final String S_STA_12 = "S_STA_12";
    public static final String S_STA_00_TR = "S_STA_00_TR";
    public static final String S_STA_01_TR = "S_STA_01_TR";
    public static final String S_STA_02_TR = "S_STA_02_TR";
    public static final String S_STA_03_TR = "S_STA_03_TR";
    public static final String S_STA_04_TR = "S_STA_04_TR";
    public static final String S_STA_05_TR = "S_STA_05_TR";
    public static final String S_STA_06_TR = "S_STA_06_TR";
    public static final String S_STA_07_TR = "S_STA_07_TR";
    public static final String S_STA_08_TR = "S_STA_08_TR";
    public static final String S_STA_09_TR = "S_STA_09_TR";
    public static final String S_STA_10_TR = "S_STA_10_TR";
    public static final String S_STA_11_TR = "S_STA_11_TR";
    public static final String S_STA_12_TR = "S_STA_12_TR";
    public static final String EV_TR = "EV_TR";

    public static String callStatusSetBeanClickStatus(long... _args) {
        return callStatusSetBeanClickStatus(dispAllStatusSearch(),_args);
    }

    public static String callStatusSetBeanClickStatus(Struct _str, long... _args) {
        return navigateData(new StatusSetBeanClickStatus(),_str,_args);
    }

    public static String callStatusSetBeanClickStatusId(long... _args) {
        Struct bean_ = dispAllStatusSearch();
        callStatusSetBeanClickStatus(bean_,_args);
        return getValStatusId(bean_);
    }

    public static Struct callStatusSetBeanGetTrStatus(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanGetTrStatus(),_str,_args);
    }

    public static Struct callStatusSetBeanSearch(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSearch(),_str,_args);
    }

    public static Struct callStatusSetBeanSortedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSortedStatusGet(),_str,_args);
    }

    public static Struct callStatusSetBeanTypedStatusGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanTypedStatusGet(),_str,_args);
    }

    public static Struct callStatusSetBeanTypedStatusSet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new StatusSetBeanTypedStatusSet(),_str,_args);
    }

    protected static Struct dispAllStatus() {
        PkData pk_ = pkDataByFacade(feedDb());
        return dispAllStatus(pk_);
    }

    private static Struct dispAllStatus(PkData _pk) {
        StringMap<Struct> all_ = beanToStatusSet(_pk);
        Struct welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct moves_ = all_.getVal(AikiBeansStatusStd.BEAN_STATUS_SET);
        transit(_pk,new WelcomeBeanClickStatus(),welcome_,moves_);
        return moves_;
    }

    protected static Struct dispAllStatusSearch() {
        PkData pk_ = pkDataByFacade(feedDb());
        Struct moves_ = dispAllStatus(pk_);
        transit(pk_,new StatusSetBeanSearch(),moves_,moves_);
        return moves_;
    }

    protected static Struct transitToAllStatus(PkData _pk, StringMap<Struct> _all,int _index) {
        Struct welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct pks_ = _all.getVal(AikiBeansStatusStd.BEAN_STATUS_SET);
        Struct pk_ = _all.getVal(AikiBeansStatusStd.BEAN_STATUS);
        transit(_pk,new WelcomeBeanClickStatus(),welcome_,pks_);
        transit(_pk,new StatusSetBeanSearch(),pks_,pks_);
        transit(_pk,new StatusSetBeanClickStatus(),pks_,pk_,_index);
        return pk_;
    }
    protected static String navigateStatusSearch(Struct _moves) {
        return navigateData(new StatusSetBeanSearch(), _moves);
    }
    public static StringMap<Struct> beanToStatusSet(PkData _pk) {
        StringMap<Struct> map_ = new StringMap<Struct>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansStatusStd.BEAN_STATUS_SET,_pk.beanStatusSetBean(EN));
        return map_;
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(S_STA_00,staRel(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,0,false,false));
        facade_.getData().completeMembers(S_STA_01,staRel(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,1,true,true));
        Status rel1_ = staRel(DataBase.VAR_PREFIX + Fight.TEMPS_TOUR, 1, true, true);
        rel1_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        facade_.getData().completeMembers(S_STA_02,rel1_);
        Status si1_ = staSimple(DataBase.VAR_PREFIX + Fight.TEMPS_TOUR, 1, true, true);
        EffectEndRoundSingleStatus e1_ = Instances.newEffectEndRoundSingleStatus();
        e1_.setEndRoundRank(5);
        e1_.setIncrementingDamageByRounds(true);
        si1_.getEffectEndRound().add(e1_);
        facade_.getData().completeMembers(S_STA_03,si1_);
        facade_.getData().completeMembers(S_STA_04,staSimple(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,0,false,false));
        facade_.getData().completeMembers(S_STA_05,staSimple(DataBase.VAR_PREFIX+ Fight.TEMPS_TOUR,1,true,true));
        Status si2_ = staSimple(DataBase.VAR_PREFIX + Fight.TEMPS_TOUR, 1, true, true);
        EffectEndRoundSingleStatus e2_ = Instances.newEffectEndRoundSingleStatus();
        e2_.setEndRoundRank(5);
        e2_.setIncrementingDamageByRounds(true);
        si2_.getEffectEndRound().add(e2_);
        facade_.getData().completeMembers(S_STA_06,si2_);
        StatusBeginRoundSimple b1_ = Instances.newStatusBeginRoundSimple();
        b1_.getLawForUsingAMoveNbRound().addQuickEvent(Rate.one(), LgInt.newLgInt("5"));
        b1_.getLawForUsingAMoveNbRound().addQuickEvent(Rate.newRate("2"),LgInt.newLgInt("3"));
        b1_.getLawForUsingAMove().addQuickEvent(BoolVal.FALSE, LgInt.newLgInt("22"));
        b1_.getLawForUsingAMove().addQuickEvent(BoolVal.TRUE,LgInt.newLgInt("25"));
        b1_.getLawForUsingAMoveIfFoe().addQuickEvent(BoolVal.FALSE, LgInt.newLgInt("43"));
        b1_.getLawForUsingAMoveIfFoe().addQuickEvent(BoolVal.TRUE,LgInt.newLgInt("35"));
        b1_.getLawForFullHealIfMove().addQuickEvent(BoolVal.FALSE, LgInt.newLgInt("56"));
        b1_.getLawForFullHealIfMove().addQuickEvent(BoolVal.TRUE,LgInt.newLgInt("34"));
        facade_.getData().completeMembers(S_STA_07,b1_);
        StatusBeginRoundAutoDamage b2_ = Instances.newStatusBeginRoundAutoDamage();
        b2_.setAttack(Statistic.ATTACK);
        b2_.setDefense(Statistic.DEFENSE);
        b2_.getLawForUsingAMove().addQuickEvent(BoolVal.FALSE, LgInt.newLgInt("22"));
        b2_.getLawForUsingAMoveIfFoe().addQuickEvent(BoolVal.FALSE, LgInt.newLgInt("43"));
        facade_.getData().completeMembers(S_STA_08,b2_);
        StatusBeginRoundSimple b3_ = Instances.newStatusBeginRoundSimple();
        b3_.getLawForUsingAMove().addQuickEvent(BoolVal.TRUE,LgInt.newLgInt("25"));
        b3_.getLawForUsingAMoveIfFoe().addQuickEvent(BoolVal.TRUE,LgInt.newLgInt("35"));
        facade_.getData().completeMembers(S_STA_09,b3_);
        facade_.getData().completeMembers(S_STA_10,Instances.newStatusBeginRoundSimple());
        StatusBeginRoundSimple b4_ = Instances.newStatusBeginRoundSimple();
        b4_.getEffectsPartner().add(part(true));
        facade_.getData().completeMembers(S_STA_11,b4_);
        StatusBeginRoundSimple b5_ = Instances.newStatusBeginRoundSimple();
        b5_.getEffectsPartner().add(part(false));
        facade_.getData().completeMembers(S_STA_12,b5_);
        facade_.getData().getLitterals().addEntry(EN,new StringMap<String>());
        facade_.getData().getLitterals().getVal(EN).addEntry(Fight.TEMPS_TOUR, TAB+Fight.TEMPS_TOUR+TAB+TIME);
        facade_.getData().completeVariables();
        facade_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.EVASINESS, EV_TR);
        facade_.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
        facade_.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_00,S_STA_00_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_01,S_STA_01_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_02,S_STA_02_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_03,S_STA_03_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_04,S_STA_04_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_05,S_STA_05_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_06,S_STA_06_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_07,S_STA_07_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_08,S_STA_08_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_09,S_STA_09_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_10,S_STA_10_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_11,S_STA_11_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_12,S_STA_12_TR);
        facade_.getData().getAnimStatus().addEntry(S_STA_00,BaseSixtyFourUtil.getImageByString("AAACAAAABAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_01,BaseSixtyFourUtil.getImageByString("AAACAAABBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_02,BaseSixtyFourUtil.getImageByString("AAACAAACBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_03,BaseSixtyFourUtil.getImageByString("AAACAAADBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_04,BaseSixtyFourUtil.getImageByString("AAACAAAEBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_05,BaseSixtyFourUtil.getImageByString("AAACAAAFBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_06,BaseSixtyFourUtil.getImageByString("AAACAAAGBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_07,BaseSixtyFourUtil.getImageByString("AAACAAAHBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_08,BaseSixtyFourUtil.getImageByString("AAACAAAIBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_09,BaseSixtyFourUtil.getImageByString("AAACAAAJBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_10,BaseSixtyFourUtil.getImageByString("AAACAAAKBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_11,BaseSixtyFourUtil.getImageByString("AAACAAALBAAA////////"));
        facade_.getData().getAnimStatus().addEntry(S_STA_12,BaseSixtyFourUtil.getImageByString("AAACAAAMBAAA////////"));
        return facade_;
    }

    private static EffectPartnerStatus part(boolean _weddingAlly) {
        EffectPartnerStatus e_ = Instances.newEffectPartnerStatus();
        e_.setMultDamageAgainstFoe(Rate.one());
        e_.setRestoredHpRateLovedAlly(Rate.one());
        e_.setWeddingAlly(_weddingAlly);
        return e_;
    }
}
