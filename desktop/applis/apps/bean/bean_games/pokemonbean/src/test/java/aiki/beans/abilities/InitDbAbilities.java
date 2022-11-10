package aiki.beans.abilities;

import aiki.beans.*;
import aiki.beans.db.InitDbConstr;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.instances.Instances;
import code.expressionlanguage.structs.Struct;
import code.util.IdMap;
import code.util.StringMap;

public abstract class InitDbAbilities extends InitDbConstr {

    protected static final String A_ABILITY2="B_ABILITY";
    protected static final String A_ABILITY2_TR="B_ABILITY_TR";
    public static Struct callAbilitiesBeanClickAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanClickAbility(),_str,_args);
    }

    public static Struct callAbilitiesBeanGetTrAbility(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanGetTrAbility(),_str,_args);
    }

//    public static Struct callAbilitiesBeanSearch(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanSearch(),_str,_args);
//    }

    public static Struct callAbilitiesBeanSortedAbilitiesGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanSortedAbilitiesGet(),_str,_args);
    }

    public static Struct callAbilitiesBeanTypedAbilityGet(Struct _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new AbilitiesBeanTypedAbilityGet(),_str,_args);
    }
//    public static void fwdEffectWhileSendingWithStatistic(Struct _update, Struct _use) {
//        callEffectWhileSendingBeanEffectSet(_update,callAbilityBeanGetEffectSending(_use));
//    }
//    public static Struct callAbilityBeanGetEffectSending(Struct _str, long... _args) {
//        return BeanPokemonCommonTs.callLongs(new AbilityBeanGetEffectSending(),_str,_args);
//    }
    public static Struct callAbilitiesBeanTypedAbilitySet(Struct _str, String _args) {
        return BeanPokemonCommonTs.callString(new AbilitiesBeanTypedAbilitySet(),_str,_args);
    }

    protected static String navigateAbilitiesSearch(Struct _moves) {
        return navigateData(new AbilitiesBeanSearch(), _moves);
    }
    protected static Struct transitToAllAbilities(PkData _pk, StringMap<Struct> _all, int _index) {
        Struct welcome_ = _all.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct items_ = _all.getVal(AikiBeansAbilitiesStd.BEAN_ABILITIES);
        transit(_pk,new WelcomeBeanClickAbilities(),welcome_,items_);
        transit(_pk,new AbilitiesBeanSearch(),items_,items_);
        Struct itData_ = _all.getVal(AikiBeansAbilitiesStd.BEAN_ABILITY);
        transit(_pk,new AbilitiesBeanClickAbility(),items_, itData_,_index);
        return itData_;
    }
    protected static Struct dispAllAbilities(FacadeGame _fac) {
        PkData pk_ = pkDataByFacade(_fac);
        return dispAllAbilities(pk_);
    }

    private static Struct dispAllAbilities(PkData _pk) {
        StringMap<Struct> all_ = beanToAbilities(_pk);
        Struct welcome_ = all_.getVal(AikiBeansStd.BEAN_WELCOME);
        beforeDisplaying(welcome_);
        Struct moves_ = all_.getVal(AikiBeansAbilitiesStd.BEAN_ABILITIES);
        transit(_pk,new WelcomeBeanClickAbilities(),welcome_,moves_);
        return moves_;
    }
    public static StringMap<Struct> beanToAbilities(PkData _pk) {
        StringMap<Struct> map_ = new StringMap<Struct>();
        map_.addEntry(AikiBeansStd.BEAN_WELCOME,_pk.beanWelcomeBean(EN));
        map_.addEntry(AikiBeansAbilitiesStd.BEAN_ABILITIES,_pk.beanAbilitiesBean(EN));
        return map_;
    }

    public static StringMap<Struct> beanToAbility(PkData _pk) {
        StringMap<Struct> map_ = beanToAbilities(_pk);
        map_.addEntry(AikiBeansAbilitiesStd.BEAN_ABILITY,_pk.beanAbilityBean(EN));
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
        _facade.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        _facade.getData().getTranslatedCategories().getVal(EN).addEntry(AUTRE,C_CAT2_TR);
//        _facade.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BALL,I_BALL_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BERRY,I_BERRY_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_BOOST,I_BOOST_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEMBATTLE,I_ITEMBATTLE_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_ITEM,I_EVO_ITEM_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_EVO_STONE,I_EVO_STONE_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_FOSSIL,I_FOSSIL_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL,I_HEAL_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP,I_HEAL_HP_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_PP,I_HEAL_PP_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_HP_STATUS,I_HEAL_HP_STATUS_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_HEAL_STATUS,I_HEAL_STATUS_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_REPEL,I_REPEL_TR);
//        _facade.getData().getTranslatedItems().getVal(EN).addEntry(I_SELLING,I_SELLING_TR);
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
}
