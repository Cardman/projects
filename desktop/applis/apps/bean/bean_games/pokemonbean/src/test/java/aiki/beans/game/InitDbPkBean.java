package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.beans.PkInd;
import aiki.beans.facade.UsesOfMoveGetCurrent;
import aiki.beans.facade.UsesOfMoveGetMax;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetEv;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetIv;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetName;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayerGetRate;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import code.bean.nat.*;

public abstract class InitDbPkBean extends InitDbBean {
    public static NaSt callPokemonPlayerBeanNameGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNameGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanUsedBallCatchingGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanUsedBallCatchingGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanLevelGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanLevelGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanGenderGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGenderGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanAbilityGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanAbilityGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanItemGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanItemGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanImageGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanImageGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanGetEvo(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGetEvo(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanGetEvoImg(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGetEvoImg(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanGetEvoKey(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanGetEvoKey(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanEvolutionsGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanEvolutionsGet(),_str,_args);
    }

    public static NaSt callStatisticInfoPkPlayerGetEv(NaSt _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetEv(),_str,_args);
    }

    public static NaSt callStatisticInfoPkPlayerGetIv(NaSt _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetIv(),_str,_args);
    }

    public static NaSt callStatisticInfoPkPlayerGetName(NaSt _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetName(),_str,_args);
    }

    public static NaSt callStatisticInfoPkPlayerGetRate(NaSt _str, long... _args) {
        return callLongs(new StatisticInfoPkPlayerGetRate(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanStatisticsGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatisticsGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanMovesGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanMovesGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanStatusGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanStatusGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanTypesGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanTypesGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanNbStepsTeamLeadGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNbStepsTeamLeadGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanHappinessGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanHappinessGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanWonExpSinceLastLevelGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanWonExpSinceLastLevelGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanNecessaryPointsNextLevelGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNecessaryPointsNextLevelGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanFullHpGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanFullHpGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanNicknameGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanNicknameGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanRemainingHpGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpGet(),_str,_args);
    }

    public static NaSt callPokemonPlayerBeanRemainingHpPerCentGet(NaSt _str, long... _args) {
        return callLongs(new PokemonPlayerBeanRemainingHpPerCentGet(),_str,_args);
    }

    public static NaSt beanPk(String _language, FacadeGame _dataBase) {
        PkInd stds_ = new PkInd();
        stds_.setDataBase(_dataBase);
//        stds_.setBaseEncode(BASE);
        return stds_.beanPk(_language);
    }

    public static NaSt callUsesOfMoveGetCurrent(NaSt _str, long... _args) {
        return callLongs(new UsesOfMoveGetCurrent(),_str,_args);
    }

    public static NaSt callUsesOfMoveGetMax(NaSt _str, long... _args) {
        return callLongs(new UsesOfMoveGetMax(),_str,_args);
    }
}
