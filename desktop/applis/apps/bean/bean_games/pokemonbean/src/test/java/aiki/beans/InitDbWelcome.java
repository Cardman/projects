package aiki.beans;

import aiki.beans.db.InitDbConstr;
import aiki.facade.FacadeGame;
import aiki.fight.enums.Statistic;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.enums.GenderRepartition;
import aiki.instances.Instances;
import code.bean.nat.*;
import code.util.IdMap;
import code.util.StringList;
import code.util.StringMap;

public abstract class InitDbWelcome extends InitDbConstr {

    public static String navigateAbilities(NaSt _str) {
        return navigateData(new WelcomeBeanClickAbilities(),_str);
    }
    public static String navigateItems(NaSt _str) {
        return navigateData(new WelcomeBeanClickItems(),_str);
    }
    public static String navigatePokedex(NaSt _str) {
        return navigateData(new WelcomeBeanClickPokedex(),_str);
    }
    public static String navigateSimulation(NaSt _str) {
        return navigateData(new WelcomeBeanClickSimulation(),_str);
    }
    public static String navigateStatus(NaSt _str) {
        return navigateData(new WelcomeBeanClickStatus(),_str);
    }
    public static String navigateAllMoves(NaSt _str) {
        return navigateData(new WelcomeBeanSeeAllMoves(),_str);
    }

    public static String navigateLearntMoves(NaSt _str) {
        return navigateData(new WelcomeBeanSeeLearntMoves(),_str);
    }

    public static String navigateNotLearntMoves(NaSt _str) {
        return navigateData(new WelcomeBeanSeeNotLearntMoves(),_str);
    }
    public static NaSt beanWelcome(FacadeGame _dataBase) {
        PkData pk_ = pkDataByFacade(_dataBase);
        return pk_.beanWelcomeBean(EN);
    }
    public static NaSt callWelcomeBeanClickAbilities(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickAbilities(),_str,_args);
    }

    public static NaSt callWelcomeBeanClickItems(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickItems(),_str,_args);
    }

    public static NaSt callWelcomeBeanClickPokedex(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickPokedex(),_str,_args);
    }

    public static NaSt callWelcomeBeanClickSimulation(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickSimulation(),_str,_args);
    }

    public static NaSt callWelcomeBeanClickStatus(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanClickStatus(),_str,_args);
    }

    public static NaSt callWelcomeBeanSeeAllMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeAllMoves(),_str,_args);
    }

    public static NaSt callWelcomeBeanSeeLearntMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeLearntMoves(),_str,_args);
    }

    public static NaSt callWelcomeBeanSeeNotLearntMoves(NaSt _str, long... _args) {
        return BeanPokemonCommonTs.callLongs(new WelcomeBeanSeeNotLearntMoves(),_str,_args);
    }

    protected static FacadeGame feedDb() {
        FacadeGame facade_ = dbBaseWelcome();
        feedTm(facade_.getData().getTm(),facade_.getData().getTmPrice());
        feedHm(facade_.getData().getHm());
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static FacadeGame feedDbBase() {
        FacadeGame facade_ = dbBaseWelcome();
        facade_.getData().completeVariables();
        return facade_;
    }

    protected static FacadeGame dbBaseWelcome() {
        FacadeGame facade_ = facade();
        facade_.getData().completeMembers(M_DAM,moveDam(TargetChoice.ANY_FOE));
        facade_.getData().completeMembers(M_STA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(M_WEA,moveSta(TargetChoice.TOUS_ADV));
        facade_.getData().completeMembers(I_ITEM,ball());
        facade_.getData().completeMembers(S_STA_REL,staRel(""));
        facade_.getData().completeMembers(S_STA_SIM,staSimple(""));
        facade_.getData().completeMembers(P_POKEMON,pk(new StringList("__"), GenderRepartition.NO_GENDER));
        facade_.getData().completeMembers(A_ABILITY,Instances.newAbilityData());
        facade_.getData().getTranslatedAbilities().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedAbilities().getVal(EN).addEntry(A_ABILITY,A_ABILITY_TR);
        facade_.getData().getTranslatedTypes().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedTypes().getVal(EN).addEntry(T_TYPE1, T_TYPE1_TR);
        facade_.getData().getTranslatedCategories().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedCategories().getVal(EN).addEntry(C_CAT, C_CAT1_TR);
        facade_.getData().getTranslatedItems().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedItems().getVal(EN).addEntry(I_ITEM,I_ITEM_TR);
        facade_.getData().getTranslatedMoves().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_DAM,M_DAM_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_STA,M_STA_TR);
        facade_.getData().getTranslatedMoves().getVal(EN).addEntry(M_WEA,M_WEA_TR);
        facade_.getData().getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedPokemon().getVal(EN).addEntry(P_POKEMON,P_POKEMON_TR);
        facade_.getData().getTranslatedStatus().addEntry(EN,new StringMap<String>());
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_REL,S_STA_REL_TR);
        facade_.getData().getTranslatedStatus().getVal(EN).addEntry(S_STA_SIM,S_STA_SIM_TR);
        facade_.getData().getTranslatedStatistics().addEntry(EN,new IdMap<Statistic, String>());
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.ATTACK,ST_ATT_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.DEFENSE,ST_DEF_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_ATTACK,ST_ATT_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPECIAL_DEFENSE,ST_DEF_SPE_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.SPEED,ST_SPEED_TR);
        facade_.getData().getTranslatedStatistics().getVal(EN).addEntry(Statistic.HP,ST_HP_TR);
        return facade_;
    }
}
