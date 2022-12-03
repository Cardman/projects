package aiki.beans.effects;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansEffectsStd{
    public static final String EFFECT_SENDING = "effsending";
    public static final String TYPE_COMBO_DTO = "aiki.beans.effects.ComboDto";
    public static final String TYPE_COMBOS_BEAN = "aiki.beans.effects.CombosBean";
    public static final String BEAN_COMBOS = "combos";
    public static final String BEAN_COMBO = "eff_combo";
    public static final String TYPE_EFFECT_COMBO_BEAN = "aiki.beans.effects.EffectComboBean";
    public static final String TYPE_EFFECT_WHILE_SENDING_BEAN = "aiki.beans.effects.EffectWhileSendingBean";
    private static final String CLICK_MOVE = "clickMove";
    private static final String GET_TR_STATISTIC = "getTrStatistic";
    private static final String GET_COMBOS_KEY = "getCombosKey";
    private static final String CLICK_WEATHER = "clickWeather";
    private static final String GET_TR_WEATHER = "getTrWeather";
    private static final String RANDOM_STATIS = "randomStatis";
    private static final String IS_ALWAYS_ENABLED = "isAlwaysEnabled";
    private static final String NOT_EMPTY_VAR_BOOST = "notEmptyVarBoost";
    private static final String GET_FAIL = "getFail";
    private static final String GET_RATE = "getRate";
    private static final String GET_SWAP_FAIL = "getSwapFail";
    private static final String EFFECT = "effect";
    private static final String MOVES = "moves";
    private static final String INDEX = "index";
    private static final String END_ROUND = "endRound";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String MULT_EVT_RATE_SEC_EFF = "multEvtRateSecEff";
    private static final String MULT_STATISTIC_FOE = "multStatisticFoe";
    private static final String RANK_INCREMENT_NB_ROUND = "rankIncrementNbRound";
    private static final String REPEATED_ROUNDS_LAW = "repeatedRoundsLaw";
    private static final String COMBO = "combo";
    private static final String COMBOS = "combos";
    private static final String DISABLE_WEATHER = "disableWeather";
    private static final String ENABLED_WEATHER = "enabledWeather";
    private static final String COPYING_ABILITY = "copyingAbility";
//    private static final String PLATE = "plate";
    private static final String MULT_WEIGHT = "multWeight";
    private static final String STATISTIC = "statistic";
    private static final String REASONS = "reasons";
    private static final String MAP_VARS_FAIL = "mapVarsFail";
    private static final String EVT_RATE = "evtRate";
    private static final String EVT_RATE_PER_CENT = "evtRatePerCent";
    private static final String STATIS_VAR_RANK = "statisVarRank";
    private static final String MAP_VARS_STATISTICS = "mapVarsStatistics";
    private static final String SWAP_BOOST_STATIS = "swapBoostStatis";
    private static final String CANCEL_LOW_STAT = "cancelLowStat";
    private static final String DEFAULT_BOOST = "defaultBoost";
    private static final String CANCEL_CHGT_STAT = "cancelChgtStat";
    private static final String COPY_BOOST = "copyBoost";
    private AikiBeansEffectsStd(){}
    public static void build(PokemonStandards _std) {
        buildCombosBean(_std);
        buildEffectComboBean(_std);
        buildEffectWhileSendingBean(_std);
    }

    private static void buildCombosBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(COMBO,BeanNatCommonLgNames.STRING, new CombosBeanComboGet(),null));
        fields_.add(new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO, new CombosBeanCombosGet(),null));
        methods_.add( new SpecNatMethod(GET_COMBOS_KEY, BeanNatCommonLgNames.TYPE_LIST, new CombosBeanGetCombosKey()));
        _std.getStds().addEntry(TYPE_COMBOS_BEAN, type_);
    }
    private static void buildEffectComboBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_LIST, new EffectComboBeanMovesGet(),null));
        fields_.add(new StandardField(INDEX, BeanNatCommonLgNames.PRIM_INTEGER, new EffectComboBeanIndexGet(),new EffectComboBeanIndexSet()));
        fields_.add(new StandardField(END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectComboBeanEndRoundGet(),null));
        fields_.add(new StandardField(END_ROUND_RANK, BeanNatCommonLgNames.PRIM_INTEGER, new EffectComboBeanEndRoundRankGet(),null));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatCommonLgNames.TYPE_LIST, new EffectComboBeanReasonsEndRoundGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatCommonLgNames.TYPE_MAP, new EffectComboBeanMapVarsFailEndRoundGet(),null));
        fields_.add(new StandardField(MULT_EVT_RATE_SEC_EFF,BeanNatCommonLgNames.TYPE_RATE, new EffectComboBeanMultEvtRateSecEffGet(),null));
        fields_.add(new StandardField(MULT_STATISTIC_FOE, BeanNatCommonLgNames.TYPE_MAP, new EffectComboBeanMultStatisticFoeGet(),null));
        fields_.add(new StandardField(RANK_INCREMENT_NB_ROUND, BeanNatCommonLgNames.PRIM_INTEGER, new EffectComboBeanRankIncrementNbRoundGet(),null));
        fields_.add(new StandardField(REPEATED_ROUNDS_LAW, BeanNatCommonLgNames.TYPE_MAP, new EffectComboBeanRepeatedRoundsLawGet(),null));
        fields_.add(new StandardField(COMBOS,AikiBeansEffectsStd.TYPE_COMBO_DTO, null,new EffectComboBeanCombosSet()));
        methods_.add( new SpecNatMethod(CLICK_MOVE,BeanNatCommonLgNames.STRING, new EffectComboBeanClickMove()));
        methods_.add( new SpecNatMethod(GET_TR_STATISTIC,BeanNatCommonLgNames.STRING, new EffectComboBeanGetTrStatistic()));
        _std.getStds().addEntry(TYPE_EFFECT_COMBO_BEAN, type_);
    }
    private static void buildEffectWhileSendingBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(DISABLE_WEATHER,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanDisableWeatherGet(),null));
        fields_.add(new StandardField(ENABLED_WEATHER,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanEnabledWeatherGet(),null));
        fields_.add(new StandardField(COPYING_ABILITY,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanCopyingAbilityGet(),null));
//        fields_.add(new StandardField(PLATE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new EffectWhileSendingBeanPlateGet(),null));
        fields_.add(new StandardField(MULT_WEIGHT,BeanNatCommonLgNames.TYPE_RATE, new EffectWhileSendingBeanMultWeightGet(),null));
        fields_.add(new StandardField(STATISTIC,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanStatisticGet(),null));
        fields_.add(new StandardField(REASONS, BeanNatCommonLgNames.TYPE_LIST, new EffectWhileSendingBeanReasonsGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL, BeanNatCommonLgNames.TYPE_MAP, new EffectWhileSendingBeanMapVarsFailGet(),null));
        fields_.add(new StandardField(EVT_RATE,BeanNatCommonLgNames.TYPE_RATE, new EffectWhileSendingBeanEvtRateGet(),null));
        fields_.add(new StandardField(EVT_RATE_PER_CENT,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanEvtRatePerCentGet(),null));
        fields_.add(new StandardField(STATIS_VAR_RANK, BeanNatCommonLgNames.TYPE_MAP, new EffectWhileSendingBeanStatisVarRankGet(),null));
        fields_.add(new StandardField(MAP_VARS_STATISTICS, BeanNatCommonLgNames.TYPE_MAP, new EffectWhileSendingBeanMapVarsStatisticsGet(),null));
        fields_.add(new StandardField(SWAP_BOOST_STATIS, BeanNatCommonLgNames.TYPE_LIST, new EffectWhileSendingBeanSwapBoostStatisGet(),null));
        fields_.add(new StandardField(CANCEL_LOW_STAT, BeanNatCommonLgNames.TYPE_LIST, new EffectWhileSendingBeanCancelLowStatGet(),null));
        fields_.add(new StandardField(DEFAULT_BOOST, BeanNatCommonLgNames.PRIM_INTEGER, new EffectWhileSendingBeanDefaultBoostGet(),null));
        fields_.add(new StandardField(CANCEL_CHGT_STAT, BeanNatCommonLgNames.TYPE_LIST, new EffectWhileSendingBeanCancelChgtStatGet(),null));
        fields_.add(new StandardField(COPY_BOOST, BeanNatCommonLgNames.TYPE_LIST, new EffectWhileSendingBeanCopyBoostGet(),null));
        fields_.add(new StandardField(EFFECT,PokemonStandards.TYPE_EFFECT_WHILE_SENDING, null,new EffectWhileSendingBeanEffectSet()));
        methods_.add( new SpecNatMethod(CLICK_WEATHER,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanClickWeather()));
        methods_.add( new SpecNatMethod(GET_TR_WEATHER,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanGetTrWeather()));
        methods_.add( new SpecNatMethod(RANDOM_STATIS,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanRandomStatis()));
        methods_.add( new SpecNatMethod(IS_ALWAYS_ENABLED,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanIsAlwaysEnabled()));
        methods_.add( new SpecNatMethod(NOT_EMPTY_VAR_BOOST,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectWhileSendingBeanNotEmptyVarBoost()));
        methods_.add( new SpecNatMethod(GET_FAIL,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanGetFail()));
        methods_.add( new SpecNatMethod(GET_RATE,BeanNatCommonLgNames.TYPE_RATE, new EffectWhileSendingBeanGetRate()));
        methods_.add( new SpecNatMethod(GET_SWAP_FAIL,BeanNatCommonLgNames.STRING, new EffectWhileSendingBeanGetSwapFail()));
        _std.getStds().addEntry(TYPE_EFFECT_WHILE_SENDING_BEAN, type_);
    }
}
