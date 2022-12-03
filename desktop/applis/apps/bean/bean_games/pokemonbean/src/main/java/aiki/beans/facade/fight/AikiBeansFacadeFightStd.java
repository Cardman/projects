package aiki.beans.facade.fight;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansFacadeFightStd{
    public static final String TYPE_KEY_HYPOTHESIS = "aiki.beans.facade.fight.KeyHypothesis";
    public static final String TYPE_MULT_POWER_MOVES = "aiki.beans.facade.fight.MultPowerMoves";
    public static final String TYPE_STATISTIC_INFO = "aiki.beans.facade.fight.StatisticInfo";
    public static final String TYPE_SUFFERED_DAMAGE_CATEGORY = "aiki.beans.facade.fight.SufferedDamageCategory";
    private static final String GET_PLAYER_POKEMON = "getPlayerPokemon";
    private static final String GET_MOVE = "getMove";
    private static final String GET_TARGET_POKEMON = "getTargetPokemon";
    private static final String IS_BELONGS_TO_USER = "isBelongsToUser";
//    private static final String GET_NUMBER_TARGET = "getNumberTarget";
    private static final String GET_DAMAGE = "getDamage";
    private static final String GET_DAMAGE_SECOND = "getDamageSecond";
    private static final String GET_DISPLAY_STATISTIC = "getDisplayStatistic";
    private static final String IS_BASE = "isBase";
    private static final String GET_STATIS_BASE = "getStatisBase";
    private static final String GET_EV = "getEv";
    private static final String GET_IV = "getIv";
    private static final String IS_BOOST = "isBoost";
    private static final String GET_STATIS_BOOST = "getStatisBoost";
    private static final String GET_MULT_INFLICTED = "getMultInflicted";
    private static final String GET_MULT_SUFFERING = "getMultSuffering";
    private static final String GET_ROUND = "getRound";
    private static final String GET_USING = "getUsing";
    private AikiBeansFacadeFightStd(){}
    public static void build(PokemonStandards _std) {
        buildKeyHypothesis(_std);
        buildMultPowerMoves(_std);
        buildStatisticInfo(_std);
        buildSufferedDamageCategory(_std);
    }
    private static void buildKeyHypothesis(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_PLAYER_POKEMON,BeanNatCommonLgNames.STRING, new KeyHypothesisGetPlayerPokemon()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, new KeyHypothesisGetMove()));
        methods_.add( new SpecNatMethod(GET_TARGET_POKEMON,BeanNatCommonLgNames.STRING, new KeyHypothesisGetTargetPokemon()));
        methods_.add( new SpecNatMethod(IS_BELONGS_TO_USER,BeanNatCommonLgNames.PRIM_BOOLEAN, new KeyHypothesisIsBelongsToUser()));
//        methods_.add( new SpecNatMethod(GET_NUMBER_TARGET, BeanNatCommonLgNames.PRIM_INTEGER, false, MethodModifier.NORMAL,new KeyHypothesisGetNumberTarget()));
        methods_.add( new SpecNatMethod(GET_DAMAGE,BeanNatCommonLgNames.TYPE_RATE, new KeyHypothesisGetDamage()));
        methods_.add( new SpecNatMethod(GET_DAMAGE_SECOND,BeanNatCommonLgNames.TYPE_RATE, new KeyHypothesisGetDamageSecond()));
        _std.getStds().addEntry(TYPE_KEY_HYPOTHESIS, type_);
    }
    private static void buildMultPowerMoves(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_MULT_INFLICTED,BeanNatCommonLgNames.TYPE_RATE, new MultPowerMovesGetMultInflicted()));
        methods_.add( new SpecNatMethod(GET_MULT_SUFFERING,BeanNatCommonLgNames.TYPE_RATE, new MultPowerMovesGetMultSuffering()));
        _std.getStds().addEntry(TYPE_MULT_POWER_MOVES, type_);
    }
    private static void buildStatisticInfo(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_DISPLAY_STATISTIC,BeanNatCommonLgNames.STRING, new StatisticInfoGetDisplayStatistic()));
        methods_.add( new SpecNatMethod(IS_BASE,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatisticInfoIsBase()));
        methods_.add( new SpecNatMethod(GET_STATIS_BASE,BeanNatCommonLgNames.TYPE_RATE, new StatisticInfoGetStatisBase()));
        methods_.add( new SpecNatMethod(GET_EV, BeanNatCommonLgNames.PRIM_INTEGER, new StatisticInfoGetEv()));
        methods_.add( new SpecNatMethod(GET_IV, BeanNatCommonLgNames.PRIM_INTEGER, new StatisticInfoGetIv()));
        methods_.add( new SpecNatMethod(IS_BOOST,BeanNatCommonLgNames.PRIM_BOOLEAN, new StatisticInfoIsBoost()));
        methods_.add( new SpecNatMethod(GET_STATIS_BOOST, BeanNatCommonLgNames.PRIM_INTEGER, new StatisticInfoGetStatisBoost()));
        _std.getStds().addEntry(TYPE_STATISTIC_INFO, type_);
    }
    private static void buildSufferedDamageCategory(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_ROUND,BeanNatCommonLgNames.TYPE_RATE, new SufferedDamageCategoryGetRound()));
        methods_.add( new SpecNatMethod(GET_USING,BeanNatCommonLgNames.TYPE_RATE, new SufferedDamageCategoryGetUsing()));
        _std.getStds().addEntry(TYPE_SUFFERED_DAMAGE_CATEGORY, type_);
    }
}
