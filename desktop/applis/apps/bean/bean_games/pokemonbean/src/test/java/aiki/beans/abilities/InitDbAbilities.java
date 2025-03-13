package aiki.beans.abilities;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.abilities.AbilityData;
import aiki.fight.enums.Statistic;
import aiki.fight.util.*;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.*;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.TranslationsFile;
import code.util.*;

public abstract class InitDbAbilities extends InitDbConstr {

    public static final String BEAN_ABILITIES="abilities";
    public static final String BEAN_ABILITY="ability";
    protected static final String A_ABILITY2="B_ABILITY";
    protected static final String A_ABILITY2_TR="B_ABILITY_TR";
    public static String callAbilitiesBeanClickAbility(NaSt _str, int... _args) {
        return ( (AbilitiesBean) ((PokemonBeanStruct)_str).getInstance()).clickAbility(_args[0]);
    }

    public static String callAbilitiesBeanGetTrAbility(NaSt _str, int... _args) {
        return ( (AbilitiesBean) ((PokemonBeanStruct)_str).getInstance()).getTrSortedAbility(_args[0]);
    }

//    public static Struct callAbilitiesBeanSearch(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanSearch(),_str,_args);
//    }

    public static CustList<TranslatedKey> callAbilitiesBeanSortedAbilitiesGet(NaSt _str, int... _args) {
        return (( (AbilitiesBean) ((PokemonBeanStruct)_str).getInstance()).sortedAbilitiesGet());
    }

    public static String callAbilitiesBeanTypedAbilityGet(NaSt _str, int... _args) {
        return ( (AbilitiesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedAbility().tryRet();
    }
//    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
//        callEffectWhileSendingBeanEffectSet(_update,callAbilityBeanGetEffectSending(_use));
//    }
//    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
//    }
    public static void callAbilitiesBeanTypedAbilitySet(NaSt _str, String _args) {
        ( (AbilitiesBean) ((PokemonBeanStruct)_str).getInstance()).getTypedAbility().setupValue(_args);
    }

    protected static String navigateAbilitiesSearch(NaSt _moves) {
        return navigateData(new AbilitiesBeanSearch(((AbilitiesBean) ((PokemonBeanStruct)_moves).getBean())), _moves);
    }
    protected static NaSt transitToAllAbilities(PkData _pk, StringMap<NaSt> _all, int _index) {
        NaSt welcome_ = _all.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt items_ = _all.getVal(BEAN_ABILITIES);
        transit(_pk,new WelcomeBeanClickAbilities(((WelcomeBean) ((PokemonBeanStruct)welcome_).getBean())),welcome_,items_);
        transit(_pk,new AbilitiesBeanSearch(((AbilitiesBean) ((PokemonBeanStruct)items_).getBean())),items_,items_);
        NaSt itData_ = _all.getVal(BEAN_ABILITY);
//        setFormsBy(_pk,itData_,items_);
        transit(_pk,new EntityClickFormEvent(((AbilitiesBean) ((PokemonBeanStruct)items_).getBean()),((AbilitiesBean) ((PokemonBeanStruct)items_).getBean()).sortedAbilitiesGet().get(_index)),items_,itData_);
//        transit(_pk,new AbilitiesBeanClickAbility(),items_, itData_,_index);
        return itData_;
    }

    protected static NaSt transitToAllAbilitiesQuick(PkData _pk, StringMap<NaSt> _all, int _index) {
        NaSt welcome_ = _all.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt items_ = _all.getVal(BEAN_ABILITIES);
        transit(_pk,new WelcomeBeanClickAbilities(((WelcomeBean) ((PokemonBeanStruct)welcome_).getBean())),welcome_,items_);
        transit(_pk,new AbilitiesBeanSearch(((AbilitiesBean) ((PokemonBeanStruct)items_).getBean())),items_,items_);
        return _all.getVal(BEAN_ABILITY);
    }
    protected static NaSt dispAllAbilities(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllAbilities(pk_);
    }

    private static NaSt dispAllAbilities(PkData _pk) {
        StringMap<NaSt> all_ = beanToAbilities(_pk);
        NaSt welcome_ = all_.getVal(BeanPokemonCommonTs.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = all_.getVal(BEAN_ABILITIES);
        transit(_pk,new WelcomeBeanClickAbilities(((WelcomeBean) ((PokemonBeanStruct)welcome_).getBean())),welcome_,moves_);
        return moves_;
    }
    public static StringMap<NaSt> beanToAbilities(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        WelcomeBean w_ = beanWelcomeBean(_pk,EN);
        map_.addEntry(BeanPokemonCommonTs.BEAN_WELCOME, _pk.bean(w_, EN));
        AbilitiesBean pkDex_ = new AbilitiesBean();
        w_.getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ABILITIES,new TranslationsFile());
        w_.getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.ABILITIES,new TranslationsFile());
        pkDex_.setBuilder(w_.getBuilder());
        w_.getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ABILITY_ABILITIES_HTML,pkDex_);
        map_.addEntry(BEAN_ABILITIES, _pk.bean(pkDex_, EN));
        return map_;
    }

    public static StringMap<NaSt> beanToAbility(PkData _pk) {
        StringMap<NaSt> map_ = beanToAbilities(_pk);
        AbilityBean ab_ = new AbilityBean();
        ab_.setBuilder(((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(EN).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.AB_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getTranslations().getMapping().getVal(FR).getMapping().getVal(MessagesPkBean.APP_BEAN_DATA).getMapping().addEntry(MessagesPkBean.AB_DATA,new TranslationsFile());
        ((CommonBean)((PokemonBeanStruct)map_.getValue(0)).getBean()).getBuilder().getRenders().addEntry(CommonBean.REN_ADD_WEB_HTML_ABILITY_DATA_HTML,ab_);
        map_.addEntry(BEAN_ABILITY, _pk.bean(ab_, EN));
        return map_;
    }
    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        facade_.getData().completeMembers(A_ABILITY2, Instances.newAbilityData());
        trsCore(facade_);
        facade_.getData().completeVariables();
        return facade_;
    }
    protected static void trsCore(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY2,A_ABILITY2_TR);
        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE2, T_TYPE2_TR);
        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VAR,M_DAM_VAR_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_BAD,M_DAM_BAD_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_VERY_BAD,M_DAM_VERY_BAD_TR);
        _facade.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM_POW,M_DAM_POW_TR);
        _facade.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON,P_POKEMON_TR);
        _facade.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
        _facade.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
        _facade.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        _facade.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        _facade.getData().getTranslatedBooleans().addEntry(EN,new IdMap<SelectedBoolean, String>());
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.NO, B_NO);
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES, B_YES);
        _facade.getData().getTranslatedBooleans().getVal(EN).addEntry(SelectedBoolean.YES_AND_NO," ");
        _facade.getData().getLitterals().addEntry(EN,new StringMap<String>());
        _facade.getData().getLitterals().getVal(EN).addEntry(MessagesDataBaseConstants.DEF_TEMPS_TOUR, TAB+ MessagesDataBaseConstants.DEF_TEMPS_TOUR +TAB+TIME);
    }
    protected static AbilityData ability(){
        return ability(true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,true,1,1,"");
    }
    protected static AbilityData ability(boolean _forbidUseBerryAgainstFoes, boolean _chgtTypeByDamage, boolean _ignFoeStatisBoost, boolean _immuCh, boolean _immuDamageTrappingMoves, boolean _immuDamageAllyMoves, boolean _immuDamageRecoil, boolean _copyMovesTypes, boolean _reverseEffectsPowerMovesTypesGlobal, boolean _takeItemByDamagingMove, boolean _giveItemToAllyHavingUsed, boolean _inflictingDamageInsteadOfSuffering, boolean _nbHits, boolean _breakProtection, boolean _plate, boolean _healedStatusBySwitch, boolean _achievedDisappearedPk, boolean _mumy, boolean _immuRechargeRound, boolean _slowing, boolean _immuSufferedDamageLowEff, boolean _cancelSecEffectOther, boolean _cancelSecEffectOwner, int _nbUsedPp, int _decreaseNecStepsHatch, String _typeForMoves){
        AbilityData a_ = Instances.newAbilityData();
        a_.setForbidUseBerryAgainstFoes(_forbidUseBerryAgainstFoes);
        a_.setChgtTypeByDamage(_chgtTypeByDamage);
        a_.setIgnFoeStatisBoost(_ignFoeStatisBoost);
        a_.setImmuCh(_immuCh);
        a_.setImmuDamageTrappingMoves(_immuDamageTrappingMoves);
        a_.setImmuDamageAllyMoves(_immuDamageAllyMoves);
        a_.setImmuDamageRecoil(_immuDamageRecoil);

        a_.setCopyMovesTypes(_copyMovesTypes);

        a_.setReverseEffectsPowerMovesTypesGlobal(_reverseEffectsPowerMovesTypesGlobal);

        a_.setTakeItemByDamagingMove(_takeItemByDamagingMove);

        a_.setGiveItemToAllyHavingUsed(_giveItemToAllyHavingUsed);
        a_.setInflictingDamageInsteadOfSuffering(_inflictingDamageInsteadOfSuffering);
        a_.setNbHits(_nbHits);
        a_.setBreakProtection(_breakProtection);
        a_.setPlate(_plate);
        a_.setHealedStatusBySwitch(_healedStatusBySwitch);
        a_.setAchievedDisappearedPk(_achievedDisappearedPk);
        a_.setMumy(_mumy);
        a_.setImmuRechargeRound(_immuRechargeRound);
        a_.setSlowing(_slowing);
        a_.setImmuSufferedDamageLowEff(_immuSufferedDamageLowEff);
        a_.setCancelSecEffectOther(_cancelSecEffectOther);
        a_.setCancelSecEffectOwner(_cancelSecEffectOwner);


        a_.setNbUsedPp(_nbUsedPp);
        a_.setDecreaseNecStepsHatch(_decreaseNecStepsHatch);

        a_.setTypeForMoves(_typeForMoves);
        a_.setRecoilDamageFoe(Rate.one());
        a_.setMultStab(Rate.one());
        a_.setMultEvtRateSecEffectOwner(Rate.one());
        a_.setMultVarBoost(Rate.one());
        a_.setMultAllyDamage(Rate.one());
        a_.setMultEvtRateCh(Rate.one());
        a_.setMultDamageCh(Rate.one());
        a_.setMultSufferedDamageSuperEff(Rate.one());
        a_.setMaxHpForUsingBerry(Rate.one());
        a_.setHealedHpRateBySwitch(Rate.one());
        a_.setHealHpWhileUsingBerry(Rate.one());
        a_.setMultDamage(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        a_.setMultPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        a_.getBonusStatRank().addEntry(Statistic.SPEED,1L);
        a_.getBoostStatRankEndRound().addEntry(Statistic.SPEED,1L);
        a_.getBoostStatRankProtected().addEntry(Statistic.SPEED,1L);
        a_.getMultStatIfKoFoe().addEntry(Statistic.SPEED,1L);
        a_.getMultStatIfLowStat().addEntry(Statistic.SPEED,1L);
        a_.getLowStatFoeHit().addEntry(Statistic.SPEED,1L);
        a_.getMultStatAlly().addEntry(Statistic.SPEED,Rate.one());
        a_.getMultStat().addEntry(Statistic.SPEED,VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        a_.getImmuMoveTypesByWeather().addEntry(NULL_REF,new StringList(T_TYPE1));
        a_.getImmuMoveTypesByWeather().addEntry(M_DAM,new StringList(T_TYPE1));
        a_.getChgtTypeByWeather().addEntry(NULL_REF,T_TYPE1);
        a_.getChgtTypeByWeather().addEntry(M_DAM,T_TYPE2);
        a_.getImmuStatus().addEntry(NULL_REF,new StringList(S_STA_SIM));
        a_.getImmuStatus().addEntry(M_DAM,new StringList(S_STA_SIM));
        a_.getHealHpByWeather().addEntry(NULL_REF,Rate.one());
        a_.getHealHpByWeather().addEntry(M_DAM,Rate.newRate("2"));
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType(NULL_REF,T_TYPE1),Rate.one());
        a_.getHealHpByTypeIfWeather().addEntry(new WeatherType(M_DAM,T_TYPE1),Rate.newRate("2"));
        a_.getImmuStatusBeginRound().add(S_STA_SIM);
        a_.getImmuMove().add(M_DAM);
        a_.getIgnFoeTeamMove().add(M_DAM);
        a_.getImmuAllyFromMoves().add(M_DAM);
        a_.getIgnAbility().add(A_ABILITY2);
        a_.getImmuAbility().add(A_ABILITY2);
        a_.getImmuStatus().addEntry(M_DAM,new StringList(S_STA_SIM));
        a_.getImmuWeather().add(M_DAM);
        a_.getMaxStatisticsIfCh().add(Statistic.SPEED);
        a_.getImmuLowStat().add(Statistic.SPEED);
        a_.getSingleStatus().addQuickEvent(NULL_REF, LgInt.newLgInt("1"));
        a_.getSingleStatus().addQuickEvent(S_STA_SIM,LgInt.newLgInt("3"));
        a_.getFailStatus().addEntry(S_STA_SIM,VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        a_.getForwardStatus().addEntry(S_STA_SIM,S_STA_REL);
        a_.getDivideStatusRound().addEntry(S_STA_SIM,Rate.one());
        a_.getBreakFoeImmune().add(new TypesDuo(T_TYPE1,T_TYPE2));
        a_.getImmuLowStatIfStatus().add(new StatisticStatus(Statistic.SPEED,S_STA_SIM));
        a_.getMultDamageFoe().addEntry(T_TYPE1,Rate.one());
        a_.getMultStatIfCat().addEntry(new StatisticCategory(Statistic.SPEED,C_CAT),Rate.one());
        a_.getMultStatIfDamageCat().addEntry(new StatisticCategory(Statistic.SPEED,C_CAT),1L);
        a_.getMultStatIfDamgeType().addEntry(new StatisticType(Statistic.SPEED,T_TYPE1),1L);
        a_.getMultStatIfStatutRank().addEntry(new StatisticStatus(Statistic.SPEED,S_STA_SIM),1L);
        a_.getIncreasedPrio().addEntry(C_CAT,1L);
        a_.getIncreasedPrioTypes().addEntry(T_TYPE1,1L);
        a_.getChangingBoostTypes().addEntry(T_TYPE1,new TypeDamageBoost(T_TYPE2,Rate.one()));
        a_.getImmuStatusTypes().addEntry(T_TYPE1,new StringList(S_STA_SIM));
        a_.getMultPowerMovesTypesGlobal().addEntry(T_TYPE1,Rate.one());
        IdList<Statistic> ls_ = new IdList<Statistic>();
        ls_.add(Statistic.SPEED);
        a_.getImmuLowStatisTypes().addEntry(T_TYPE1, ls_);
        return a_;
    }
}