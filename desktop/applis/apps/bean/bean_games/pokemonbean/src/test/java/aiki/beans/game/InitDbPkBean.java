package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.beans.PkInd;
import aiki.beans.UsesOfMoveGetCurrent;
import aiki.beans.UsesOfMoveGetMax;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetEv;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetIv;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetName;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetRate;
import aiki.facade.FacadeGame;
import code.expressionlanguage.structs.Struct;

public abstract class InitDbPkBean extends InitDbBean {
    public static Struct callPokemonPlayerBeanNameGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNameGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanUsedBallCatchingGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanUsedBallCatchingGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanGenderGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGenderGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanAbilityGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanAbilityGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanItemGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanItemGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanImageGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanImageGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanGetEvo(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGetEvo(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanEvolutionsGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanEvolutionsGet(),_str,_args);
    }

    public static Struct callStatisticInfoPkPlayerGetEv(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetEv(),_str,_args);
    }

    public static Struct callStatisticInfoPkPlayerGetIv(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetIv(),_str,_args);
    }

    public static Struct callStatisticInfoPkPlayerGetName(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetName(),_str,_args);
    }

    public static Struct callStatisticInfoPkPlayerGetRate(Struct _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetRate(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanStatisticsGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatisticsGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanMovesGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanMovesGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanStatusGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatusGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanTypesGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanTypesGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNbStepsTeamLeadGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNbStepsTeamLeadGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanHappinessGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanHappinessGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanWonExpSinceLastLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanWonExpSinceLastLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNecessaryPointsNextLevelGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNecessaryPointsNextLevelGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanFullHpGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanFullHpGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanNicknameGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNicknameGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanRemainingHpGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpGet(),_str,_args);
    }

    public static Struct callPokemonPlayerBeanRemainingHpPerCentGet(Struct _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpPerCentGet(),_str,_args);
    }

    public static Struct beanPk(String _language, FacadeGame _dataBase) {
        PkInd stds_ = new PkInd();
        stds_.setDataBase(_dataBase);
        return stds_.beanPk(_language);
    }

    public static Struct callUsesOfMoveGetCurrent(Struct _str, long... _args) {
        return callLongs(new UsesOfMoveGetCurrent(),_str,_args);
    }

    public static Struct callUsesOfMoveGetMax(Struct _str, long... _args) {
        return callLongs(new UsesOfMoveGetMax(),_str,_args);
    }
}
