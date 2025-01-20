package aiki.beans.items;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.beans.facade.dto.*;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Berry;
import aiki.fight.items.Boost;
import aiki.fight.items.ItemForBattle;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.fight.util.BoostHpRate;
import aiki.fight.util.EfficiencyRate;
import aiki.fight.util.StatisticPokemon;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.BoolVal;

public abstract class InitDbItems extends InitDbConstr {

    protected static final int IMG_MAX_RAI = 143;
    protected static final int IMG_MAX_RAI2 = 144;

    protected static final String CI_BALL_TR = "CI_BALL_TR";
    protected static final String CI_BERRY_TR = "CI_BERRY_TR";
    protected static final String CI_BOOST_TR = "CI_BOOST_TR";
    protected static final String CI_EVO_ITEM_TR = "CI_EVO_ITEM_TR";
    protected static final String CI_EVO_STONE_TR = "CI_EVO_STONE_TR";
    protected static final String CI_FOSSIL_TR = "CI_FOSSIL_TR";
    protected static final String CI_HEAL_TR = "CI_HEAL_TR";
    protected static final String CI_HEAL_HP_STATUS_TR = "CI_HEAL_HP_STATUS_TR";
    protected static final String CI_HEAL_HP_TR = "CI_HEAL_HP_TR";
    protected static final String CI_HEAL_PP_TR = "CI_HEAL_PP_TR";
    protected static final String CI_HEAL_STATUS_TR = "CI_HEAL_STATUS_TR";
    protected static final String CI_ITEMBATTLE_TR = "CI_ITEMBATTLE_TR";
    protected static final String CI_REPEL_TR = "CI_REPEL_TR";
    protected static final String CI_SELLING_TR = "CI_SELLING_TR";

    protected static final String I_BALL_TR = "I_BALL_TR";
    protected static final String I_BERRY_TR = "I_BERRY_TR";
    protected static final String I_BOOST_TR = "I_BOOST_TR";
    protected static final String I_EVO_ITEM_TR = "I_EVO_ITEM_TR";
    protected static final String I_EVO_STONE_TR = "I_EVO_STONE_TR";
    protected static final String I_FOSSIL_TR = "I_FOSSIL_TR";
    protected static final String I_HEAL_TR = "I_HEAL_TR";
    protected static final String I_HEAL_HP_STATUS_TR = "I_HEAL_HP_STATUS_TR";
    protected static final String I_HEAL_HP_TR = "I_HEAL_HP_TR";
    protected static final String I_HEAL_PP_TR = "I_HEAL_PP_TR";
    protected static final String I_HEAL_STATUS_TR = "I_HEAL_STATUS_TR";
    protected static final String I_ITEMBATTLE_TR = "I_ITEMBATTLE_TR";
    protected static final String I_REPEL_TR = "I_REPEL_TR";
    protected static final String I_SELLING_TR = "I_SELLING_TR";
    protected static final String I_BALL = "I_BALL";
    protected static final String I_BERRY = "I_BERRY";
    protected static final String I_BOOST = "I_BOOST";
    protected static final String I_EVO_ITEM = "I_EVO_ITEM";
    protected static final String I_EVO_STONE = "I_EVO_STONE";
    protected static final String I_FOSSIL = "I_FOSSIL";
    protected static final String I_HEAL = "I_HEAL";
    protected static final String I_HEAL_HP_STATUS = "I_HEAL_HP_STATUS";
    protected static final String I_HEAL_HP = "I_HEAL_HP";
    protected static final String I_HEAL_PP = "I_HEAL_PP";
    protected static final String I_HEAL_STATUS = "I_HEAL_STATUS";
    protected static final String I_ITEMBATTLE = "I_ITEMBATTLE";
    protected static final String I_REPEL = "I_REPEL";
    protected static final String I_SELLING = "I_SELLING";

    public static NaSt callItemLineDescriptionClassGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemLineDescriptionClassGet(),_str,_args);
    }

    public static NaSt callItemLineDisplayNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemLineDisplayNameGet(),_str,_args);
    }

    public static NaSt callItemLinePriceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemLinePriceGet(),_str,_args);
    }
    public static String callItemsBeanClickLink(NaSt _str, long... _args) {
        return navigateData(new ItemsBeanClickLink(),_str,_args);
    }

    public static NaSt callItemsBeanGetMiniImage(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanGetMiniImage(),_str,_args);
    }

    public static NaSt callItemsBeanItemsGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanItemsGet(),_str,_args);
    }

//    public static Struct callItemsBeanSearch(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemsBeanSearch(),_str,_args);
//    }

    public static NaSt callItemsBeanTypedClassGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedClassGet(),_str,_args);
    }

    public static NaSt callItemsBeanTypedNameGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedNameGet(),_str,_args);
    }

    public static NaSt callItemsBeanTypedPriceGet(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new ItemsBeanTypedPriceGet(),_str,_args);
    }
    public static NaSt callItemsBeanTypedClassSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedClassSet(),_str,_args);
    }

    public static NaSt callItemsBeanTypedNameSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedNameSet(),_str,_args);
    }

    public static NaSt callItemsBeanTypedPriceSet(NaSt _str, String _args) {
        return BeanPokemonCommonTs.callString(new ItemsBeanTypedPriceSet(),_str,_args);
    }
//    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
//        callEffectWhileSendingBeanEffectSet(_update,callItemForBattleBeanGetEffectSending(_use));
//    }
//    public static Struct callItemForBattleBeanGetEffectSending(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new ItemForBattleBeanGetEffectSending(),_str,_args);
//    }

    protected static String navigateItemsSearch(NaSt _moves) {
        return navigateData(new ItemsBeanSearch(), _moves);
    }

    protected static NaSt transitToAllItems(PkData _pk, StringMap<NaSt> _all, String _it) {
        NaSt welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt items_ = _all.getVal(AikiBeansItemsStd.BEAN_ITEMS);
        transit(_pk,new WelcomeBeanClickItems(),welcome_,items_);
        transit(_pk,new ItemsBeanSearch(),items_,items_);
        NaSt itData_ = _all.getVal(_it);
        transit(_pk,new ItemsBeanClickLink(),items_, itData_, 0);
        return itData_;
    }

    protected static NaSt dispAllItems(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllItems(pk_);
    }

    private static NaSt dispAllItems(PkData _pk) {
        StringMap<NaSt> all_ = beanToItems(_pk);
        NaSt welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        NaSt moves_ = all_.getVal(AikiBeansItemsStd.BEAN_ITEMS);
        transit(_pk,new WelcomeBeanClickItems(),welcome_,moves_);
        return moves_;
    }
    public static StringMap<NaSt> beanToItems(PkData _pk) {
        StringMap<NaSt> map_ = new StringMap<NaSt>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansItemsStd.BEAN_ITEMS,_pk.beanItemsBean(EN));
        return map_;
    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(I_BALL,ball());
        facade_.getData().completeMembers(I_BERRY,berry());
        facade_.getData().completeMembers(I_BOOST,boost());
        facade_.getData().completeMembers(I_EVO_ITEM,Instances.newEvolvingItem());
        facade_.getData().completeMembers(I_EVO_STONE,Instances.newEvolvingStone());
        facade_.getData().completeMembers(I_FOSSIL,Instances.newFossil());
        facade_.getData().completeMembers(I_HEAL,Instances.newHealingSimpleItem());
        facade_.getData().completeMembers(I_HEAL_HP,Instances.newHealingHp());
        facade_.getData().completeMembers(I_HEAL_HP_STATUS,Instances.newHealingHpStatus());
        facade_.getData().completeMembers(I_HEAL_PP,Instances.newHealingPp());
        facade_.getData().completeMembers(I_HEAL_STATUS,Instances.newHealingSimpleStatus());
        facade_.getData().completeMembers(I_ITEMBATTLE,itemForBattle());
        facade_.getData().completeMembers(I_REPEL,Instances.newRepel());
        facade_.getData().completeMembers(I_SELLING,Instances.newSellingItem());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY, Instances.newAbilityData());
        trsCore(facade_);
        facade_.getData().getTranslatedClassesDescriptions().addEntry(LANGUAGE,new StringMap<String>());
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BALL).getItemType(),CI_BALL_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BERRY).getItemType(),CI_BERRY_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_BOOST).getItemType(),CI_BOOST_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_ITEMBATTLE).getItemType(),CI_ITEMBATTLE_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_EVO_ITEM).getItemType(),CI_EVO_ITEM_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_EVO_STONE).getItemType(),CI_EVO_STONE_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_FOSSIL).getItemType(),CI_FOSSIL_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_HEAL).getItemType(),CI_HEAL_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_HEAL_HP).getItemType(),CI_HEAL_HP_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_HEAL_PP).getItemType(),CI_HEAL_PP_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_HEAL_HP_STATUS).getItemType(),CI_HEAL_HP_STATUS_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_HEAL_STATUS).getItemType(),CI_HEAL_STATUS_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_REPEL).getItemType(),CI_REPEL_TR);
        facade_.getData().getTranslatedClassesDescriptions().getVal(EN).addEntry(facade_.getData().getItem(I_SELLING).getItemType(),CI_SELLING_TR);
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        facade_.getData().getMiniItems().addEntry(I_BALL,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_BERRY,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_BOOST,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_ITEMBATTLE,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_EVO_ITEM,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_EVO_STONE,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_FOSSIL,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_HEAL,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_HEAL_HP,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_HEAL_PP,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_HEAL_HP_STATUS,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_HEAL_STATUS,instance(IMG_MAX_RAI2));
        facade_.getData().getMiniItems().addEntry(I_REPEL,instance(IMG_MAX_RAI));
        facade_.getData().getMiniItems().addEntry(I_SELLING,instance(IMG_MAX_RAI2));
        return facade_;
    }

    public static StringMap<NaSt> beanToItem(PkData _pk) {
        StringMap<NaSt> map_ = beanToItems(_pk);
        map_.addEntry(AikiBeansItemsStd.BEAN_ITEM,_pk.beanSellingItemBean(EN));
        return map_;
    }
    protected static void trsCore(FacadeGame _facade) {
        _facade.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        _facade.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BALL,I_BALL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BERRY,I_BERRY_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BOOST,I_BOOST_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEMBATTLE,I_ITEMBATTLE_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_ITEM,I_EVO_ITEM_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_STONE,I_EVO_STONE_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_FOSSIL,I_FOSSIL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL,I_HEAL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP,I_HEAL_HP_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_PP,I_HEAL_PP_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP_STATUS,I_HEAL_HP_STATUS_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_STATUS,I_HEAL_STATUS_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_REPEL,I_REPEL_TR);
        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_SELLING,I_SELLING_TR);
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
    }

    protected static Boost boost() {
        Boost b_ = Instances.newBoost();
        b_.setWinPp(Rate.one());
        b_.getHappiness().addEntry(I_BALL,1L);
        b_.getHappiness().addEntry(I_BOOST,2L);
        b_.getEvs().addEntry(Statistic.SPEED,1L);
        b_.setPrice(2);
        return b_;
    }

    protected static Berry berry() {
        return berry(Rate.one(), 1, Rate.one(), Rate.one(), NULL_REF, true, true);
    }
    protected static Berry berry(Rate _r, int _pp, Rate _hp, Rate _eff, String _cat, boolean _lawForAttackFirst, boolean _withoutFail) {
        Berry b_ = Instances.newBerry();
        b_.setHealHp(_r);
        b_.setHealPp(_pp);
        b_.setHealHpRate(_hp);
        b_.setHealHpBySuperEffMove(_eff);
        b_.setCategoryBoosting(_cat);
        b_.setLawForAttackFirst(_lawForAttackFirst);
        b_.setWithoutFail(_withoutFail);
        b_.setMaxHpHealingHp(Rate.one());
        b_.setMaxHpHealingHpRate(Rate.one());
        b_.getBoostStatis().addEntry(Statistic.SPEED,1L);
        b_.getDamageRateRecoilFoe().addEntry(C_CAT,Rate.one());
        b_.getHealStatus().add(S_STA_SIM);
        b_.getMultFoesDamage().addEntry(T_TYPE1,new EfficiencyRate(Rate.one(),Rate.one()));
        b_.getMultStat().addEntry(Statistic.SPEED,new BoostHpRate(1,Rate.one()));
        b_.setPrice(1);
        return b_;
    }
    protected static ItemForBattle itemForBattle() {
        return itemForBattle(true, true, true, true, true, true, LgInt.one(), LgInt.zero());
    }
    protected static ItemForBattle itemForBattle(boolean _againstEvo, boolean _attackLast, boolean _attacksSoon, boolean _boostExp, boolean _cancelImmuType, boolean _immuLowStatis, LgInt _trueEff, LgInt _falseEff) {
        ItemForBattle b_ = Instances.newItemForBattle();
        b_.setDamageRecoil(Rate.one());
        b_.setMultWinningEv(Rate.one());
        b_.setMultDrainedHp(Rate.one());
        b_.setProtectAgainstKo(Rate.one());
        b_.setProtectAgainstKoIfFullHp(Rate.one());
        b_.setMultWinningHappiness(Rate.one());
        b_.setMultTrappingDamage(Rate.one());
        b_.setDrainedHpByDamageRate(Rate.one());
        b_.setMultWinningExp(Rate.one());
        b_.setMultDamage(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        b_.setMultPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        b_.setAgainstEvo(_againstEvo);
        b_.setAttackLast(_attackLast);
        b_.setAttacksSoon(_attacksSoon);
        b_.setBoostExp(_boostExp);
        b_.setCancelImmuType(_cancelImmuType);
        b_.setImmuLowStatis(_immuLowStatis);
        b_.getMultStatRank().addEntry(Statistic.SPEED,1L);
        b_.getWinEvFight().addEntry(Statistic.SPEED,1L);
        b_.getMultStat().addEntry(Statistic.SPEED,VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        b_.getBoostStatisSuperEff().addEntry(Statistic.SPEED,1L);
        b_.getIncreasingMaxNbRoundGlobalMove().addEntry(M_DAM,1L);
        b_.getIncreasingMaxNbRoundTeamMove().addEntry(M_DAM,1L);
        b_.getIncreasingMaxNbRoundTrap().addEntry(M_DAM,1L);
        b_.getBoostStatisTypes().addEntry(T_TYPE1,new IdMap<Statistic,Long>());
        b_.getBoostStatisTypes().getVal(T_TYPE1).addEntry(Statistic.SPEED,1L);
        b_.getMultStatPokemonRank().addEntry(new StatisticPokemon(Statistic.SPEED,P_POKEMON),1L);
        b_.getLawForAttackFirst().addQuickEvent(BoolVal.TRUE, _trueEff);
        b_.getLawForAttackFirst().addQuickEvent(BoolVal.FALSE, _falseEff);
        b_.getImmuMoves().add(M_DAM);
        b_.getImmuTypes().add(T_TYPE1);
        b_.getImmuStatus().add(S_STA_SIM);
        b_.getTypesPk().add(T_TYPE1);
        b_.getSynchroStatus().add(S_STA_SIM);
        b_.getHatching().add(P_POKEMON);
        b_.getImmuWeather().add(M_DAM);
        b_.getFailStatus().addEntry(S_STA_SIM,VAR_PREFIX+ MessagesDataBaseConstants.DEF_TEMPS_TOUR);
        b_.setPrice(1);
        return b_;
    }
}
