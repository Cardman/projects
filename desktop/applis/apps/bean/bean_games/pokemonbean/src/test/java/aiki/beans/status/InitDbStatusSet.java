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
import aiki.instances.Instances;
import code.bean.nat.*;
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
    public static final int IMG_00 = 0;
    public static final int IMG_01 = IMG_00 + 1;
    public static final int IMG_02 = IMG_01 + 1;
    public static final int IMG_03 = IMG_02 + 1;
    public static final int IMG_04 = IMG_03 + 1;
    public static final int IMG_05 = IMG_04 + 1;
    public static final int IMG_06 = IMG_05 + 1;
    public static final int IMG_07 = IMG_06 + 1;
    public static final int IMG_08 = IMG_07 + 1;
    public static final int IMG_09 = IMG_08 + 1;
    public static final int IMG_10 = IMG_09 + 1;
    public static final int IMG_11 = IMG_10 + 1;
    public static final int IMG_12 = IMG_11 + 1;

    public static String callStatusSetBeanClickStatus(long... _args) {
        return callStatusSetBeanClickStatus(dispAllStatusSearch(),_args);
    }

    public static String callStatusSetBeanClickStatus(NaSt _str, long... _args) {
        return navigateData(new StatusSetBeanClickStatus(),_str,_args);
    }

    public static String callStatusSetBeanClickStatusId(long... _args) {
        NaSt bean_ = dispAllStatusSearch();
        callStatusSetBeanClickStatus(bean_,_args);
        return getValStatusId(bean_);
    }

    public static NaSt callStatusSetBeanGetTrStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanGetTrStatus(),_str,_args);
    }

    public static NaSt callStatusSetBeanSearch(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSearch(),_str,_args);
    }

    public static NaSt callStatusSetBeanSortedStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanSortedStatusGet(),_str,_args);
    }

    public static NaSt callStatusSetBeanTypedStatusGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new StatusSetBeanTypedStatusGet(),_str,_args);
    }

    public static NaSt callStatusSetBeanTypedStatusSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new StatusSetBeanTypedStatusSet(),_str,_args);
    }

    protected static NaSt dispAllStatus() {
        PkData pk_ = pkDataByFacade(feedDb());
        return dispAllStatus(pk_);
    }

    private static NaSt dispAllStatus(PkData _pk) {
        StringMap<NaSt> all_ = beanToStatusSet(_pk);
        NaSt welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = all_.getVal(AikiBeansStatusStd.BEAN_STATUS_SET);
        transit(_pk,new WelcomeBeanClickStatus(),welcome_,moves_);
        return moves_;
    }

    protected static NaSt dispAllStatusSearch() {
        PkData pk_ = pkDataByFacade(feedDb());
        NaSt moves_ = dispAllStatus(pk_);
        transit(pk_,new StatusSetBeanSearch(),moves_,moves_);
        return moves_;
    }

    protected static NaSt transitToAllStatus(PkData _pk, StringMap<NaSt> _all,int _index) {
        NaSt welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt pks_ = _all.getVal(AikiBeansStatusStd.BEAN_STATUS_SET);
        NaSt pk_ = _all.getVal(AikiBeansStatusStd.BEAN_STATUS);
        transit(_pk,new WelcomeBeanClickStatus(),welcome_,pks_);
        transit(_pk,new StatusSetBeanSearch(),pks_,pks_);
        transit(_pk,new StatusSetBeanClickStatus(),pks_,pk_,_index);
        return pk_;
    }
    protected static String navigateStatusSearch(NaSt _moves) {
        return navigateData(new StatusSetBeanSearch(), _moves);
    }
    public static StringMap<NaSt> beanToStatusSet(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansStatusStd.BEAN_STATUS_SET,_pk.beanStatusSetBean(EN));
        return map_;
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(S_STA_00,staRel(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,0,false,false));
        facade_.getData().completeMembers(S_STA_01,staRel(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,1,true,true));
        Status rel1_ = staRel(VAR_PREFIX + MessagesDataBaseConstants.DEF_TEMPS_TOUR, 1, true, true);
        rel1_.getEffectEndRound().add(Instances.newEffectEndRoundStatusRelation());
        facade_.getData().completeMembers(S_STA_02,rel1_);
        Status si1_ = staSimple(VAR_PREFIX + MessagesDataBaseConstants.DEF_TEMPS_TOUR, 1, true, true);
        EffectEndRoundSingleStatus e1_ = Instances.newEffectEndRoundSingleStatus();
        e1_.setFailEndRound(VAR_PREFIX + MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        e1_.setEndRoundRank(5);
        e1_.setIncrementingDamageByRounds(false);
        si1_.getEffectEndRound().add(e1_);
        facade_.getData().completeMembers(S_STA_03,si1_);
        facade_.getData().completeMembers(S_STA_04,staSimple(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,0,false,false));
        facade_.getData().completeMembers(S_STA_05,staSimple(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR,1,true,true));
        Status si2_ = staSimple(VAR_PREFIX + MessagesDataBaseConstants.DEF_TEMPS_TOUR, 1, true, true);
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
        b2_.setPower(Rate.one());
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
        facade_.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
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
        facade_.getData().getAnimStatus().addEntry(S_STA_00,instance(two(IMG_00)));
        facade_.getData().getAnimStatus().addEntry(S_STA_01,instance(two(IMG_01)));
        facade_.getData().getAnimStatus().addEntry(S_STA_02,instance(two(IMG_02)));
        facade_.getData().getAnimStatus().addEntry(S_STA_03,instance(two(IMG_03)));
        facade_.getData().getAnimStatus().addEntry(S_STA_04,instance(two(IMG_04)));
        facade_.getData().getAnimStatus().addEntry(S_STA_05,instance(two(IMG_05)));
        facade_.getData().getAnimStatus().addEntry(S_STA_06,instance(two(IMG_06)));
        facade_.getData().getAnimStatus().addEntry(S_STA_07,instance(two(IMG_07)));
        facade_.getData().getAnimStatus().addEntry(S_STA_08,instance(two(IMG_08)));
        facade_.getData().getAnimStatus().addEntry(S_STA_09,instance(two(IMG_09)));
        facade_.getData().getAnimStatus().addEntry(S_STA_10,instance(two(IMG_10)));
        facade_.getData().getAnimStatus().addEntry(S_STA_11,instance(two(IMG_11)));
        facade_.getData().getAnimStatus().addEntry(S_STA_12,instance(two(IMG_12)));
        return facade_;
    }

    private static EffectPartnerStatus part(boolean _weddingAlly) {
        EffectPartnerStatus e_ = Instances.newEffectPartnerStatus();
        e_.setMultDamageAgainstFoe(Rate.one());
        e_.setRestoredHpRateLovedAlly(Rate.one());
        e_.setWeddingAlly(_weddingAlly);
        return e_;
    }
    protected static int[][] two(int _i) {
        return new int[][]{new int[]{_i, 262144},new int[]{16777215, 16777215}};
    }
}
