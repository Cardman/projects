package aiki.beans.game;

import aiki.beans.InitDbBean;
import aiki.beans.facade.game.dto.*;
import aiki.game.UsesOfMove;
import code.maths.Rate;
import code.util.CustList;
import code.util.EntryCust;
import code.util.NatStringTreeMap;
import code.util.StringList;

public abstract class InitDbPkBean extends InitDbBean {
    public static String callPokemonPlayerBeanNameGet(PokemonPlayerBean _str, long... _args) {
        return _str.getName();
    }

    public static String callPokemonPlayerBeanUsedBallCatchingGet(PokemonPlayerBean _str, long... _args) {
        return _str.getUsedBallCatching();
    }

    public static long callPokemonPlayerBeanLevelGet(PokemonPlayerBean _str, long... _args) {
        return _str.getLevel();
    }

    public static String callPokemonPlayerBeanGenderGet(PokemonPlayerBean _str, long... _args) {
        return _str.getGender();
    }

    public static String callPokemonPlayerBeanAbilityGet(PokemonPlayerBean _str, long... _args) {
        return _str.getAbility();
    }

    public static String callPokemonPlayerBeanItemGet(PokemonPlayerBean _str, long... _args) {
        return _str.getItem();
    }

    public static int[][] callPokemonPlayerBeanImageGet(PokemonPlayerBean _str, long... _args) {
        return _str.getImage();
    }

    public static String callPokemonPlayerBeanGetEvo(ImgPkPlayer _str, long... _args) {
        return _str.getKey().getTranslation();
    }

    public static int[][] callPokemonPlayerBeanGetEvoImg(ImgPkPlayer _str, long... _args) {
        return _str.getImage();
    }

    public static String callPokemonPlayerBeanGetEvoKey(ImgPkPlayer _str, long... _args) {
        return _str.getKey().getKey();
    }

    public static CustList<ImgPkPlayer> callPokemonPlayerBeanEvolutionsGet(PokemonPlayerBean _str, long... _args) {
        return _str.getEvolutions();
    }
    public static ImgPkPlayer eltEvo(CustList<ImgPkPlayer> _ls, int _i) {
        return _ls.get(_i);
    }

    public static long callStatisticInfoPkPlayerGetEv(StatisticInfoPkPlayer _str, long... _args) {
        return _str.getEv();
    }

    public static long callStatisticInfoPkPlayerGetIv(StatisticInfoPkPlayer _str, long... _args) {
        return _str.getIv();
    }

    public static String callStatisticInfoPkPlayerGetName(StatisticInfoPkPlayer _str, long... _args) {
        return _str.getName();
    }

    public static Rate callStatisticInfoPkPlayerGetRate(StatisticInfoPkPlayer _str, long... _args) {
        return _str.getRate();
    }

    public static CustList<StatisticInfoPkPlayer> callPokemonPlayerBeanStatisticsGet(PokemonPlayerBean _str, long... _args) {
        return _str.getStatistics();
    }
    public static StatisticInfoPkPlayer eltStat(CustList<StatisticInfoPkPlayer> _ls, int _i) {
        return _ls.get(_i);
    }

    public static NatStringTreeMap<UsesOfMove> callPokemonPlayerBeanMovesGet(PokemonPlayerBean _str, long... _args) {
        return _str.getMoves();
    }
    public static EntryCust<String,UsesOfMove> eltUses(NatStringTreeMap<UsesOfMove> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String nameMove(EntryCust<String,UsesOfMove> _e) {
        return _e.getKey();
    }
    public static UsesOfMove useMove(EntryCust<String,UsesOfMove> _e) {
        return _e.getValue();
    }

    public static StringList callPokemonPlayerBeanStatusGet(PokemonPlayerBean _str, long... _args) {
        return _str.getStatus();
    }

    public static StringList callPokemonPlayerBeanTypesGet(PokemonPlayerBean _str, long... _args) {
        return _str.getTypes();
    }

    public static long callPokemonPlayerBeanNbStepsTeamLeadGet(PokemonPlayerBean _str, long... _args) {
        return _str.getNbStepsTeamLead();
    }

    public static long callPokemonPlayerBeanHappinessGet(PokemonPlayerBean _str, long... _args) {
        return _str.getHappiness();
    }

    public static Rate callPokemonPlayerBeanWonExpSinceLastLevelGet(PokemonPlayerBean _str, long... _args) {
        return _str.getWonExpSinceLastLevel();
    }

    public static Rate callPokemonPlayerBeanNecessaryPointsNextLevelGet(PokemonPlayerBean _str, long... _args) {
        return _str.getNecessaryPointsNextLevel();
    }

    public static Rate callPokemonPlayerBeanFullHpGet(PokemonPlayerBean _str, long... _args) {
        return _str.getFullHp();
    }

    public static String callPokemonPlayerBeanNicknameGet(PokemonPlayerBean _str, long... _args) {
        return _str.getNickname();
    }

    public static Rate callPokemonPlayerBeanRemainingHpGet(PokemonPlayerBean _str, long... _args) {
        return _str.getRemainingHp();
    }

    public static String callPokemonPlayerBeanRemainingHpPerCentGet(PokemonPlayerBean _str, long... _args) {
        return _str.getRemainingHpPerCent();
    }

    public static long callUsesOfMoveGetCurrent(UsesOfMove _str, long... _args) {
        return _str.getCurrent();
    }

    public static long callUsesOfMoveGetMax(UsesOfMove _str, long... _args) {
        return _str.getMax();
    }
}
