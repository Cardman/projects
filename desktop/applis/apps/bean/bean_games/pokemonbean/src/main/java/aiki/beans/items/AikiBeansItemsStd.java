package aiki.beans.items;
import aiki.beans.AikiBeansStd;
import aiki.beans.DefaultStruct;
import aiki.beans.PokemonStandards;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.LongStruct;
import code.expressionlanguage.structs.NullStruct;
import code.bean.RealInstanceStruct;
import code.expressionlanguage.structs.ShortStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansItemsStd {
    public static final String TYPE_BALL_BEAN = "aiki.beans.items.BallBean";
    public static final String TYPE_BERRY_BEAN = "aiki.beans.items.BerryBean";
    public static final String TYPE_BOOST_BEAN = "aiki.beans.items.BoostBean";
    public static final String TYPE_EVOLVING_ITEM_BEAN = "aiki.beans.items.EvolvingItemBean";
    public static final String TYPE_EVOLVING_STONE_BEAN = "aiki.beans.items.EvolvingStoneBean";
    public static final String TYPE_FOSSIL_BEAN = "aiki.beans.items.FossilBean";
    public static final String TYPE_HEALING_HP_BEAN = "aiki.beans.items.HealingHpBean";
    public static final String TYPE_HEALING_HP_STATUS_BEAN = "aiki.beans.items.HealingHpStatusBean";
    public static final String TYPE_HEALING_ITEM_BEAN = "aiki.beans.items.HealingItemBean";
    public static final String TYPE_HEALING_PP_BEAN = "aiki.beans.items.HealingPpBean";
    public static final String TYPE_HEALING_STATUS_BEAN = "aiki.beans.items.HealingStatusBean";
    public static final String TYPE_ITEM_BEAN = "aiki.beans.items.ItemBean";
    public static final String TYPE_ITEM_FOR_BATTLE_BEAN = "aiki.beans.items.ItemForBattleBean";
    public static final String TYPE_ITEMS_BEAN = "aiki.beans.items.ItemsBean";
    public static final String TYPE_REPEL_BEAN = "aiki.beans.items.RepelBean";
    public static final String TYPE_SELLING_ITEM_BEAN = "aiki.beans.items.SellingItemBean";

    private static final String IS_HEALING_PP = "isHealingPp";
    private static final String GET_TR_MULT_FOES_DAMAGE = "getTrMultFoesDamage";
    private static final String GET_TR_MULT_STAT = "getTrMultStat";
    private static final String CLICK_STATUS = "clickStatus";
    private static final String GET_TR_STATUS = "getTrStatus";
    private static final String GET_TR_CATEG_RECOIL = "getTrCategRecoil";
    private static final String GET_TR_BOOST_STAT = "getTrBoostStat";
    private static final String IS_BALL = "isBall";
    private static final String CLICK_HAPPINESS = "clickHappiness";
    private static final String GET_TR_HAPPINESS = "getTrHappiness";
    private static final String GET_TR_EV = "getTrEv";
    private static final String CLICK_POKEMON = "clickPokemon";
    private static final String GET_TR_POKEMON = "getTrPokemon";
    private static final String LIMITED_PP_MOVE = "limitedPpMove";
    private static final String LIMITED_PP_MOVES = "limitedPpMoves";
    private static final String CLICK_ITEMS = "clickItems";
    private static final String GET_EFFECT_SENDING = "getEffectSending";
    private static final String GET_TR_MULT_STAT_RANK = "getTrMultStatRank";
    private static final String GET_TR_MULT_STAT_PK_RANK = "getTrMultStatPkRank";
    private static final String CLICK_MULT_STAT_PK = "clickMultStatPk";
    private static final String GET_TR_MULT_STAT_PK = "getTrMultStatPk";
    private static final String GET_TR_MULT_STATIS_SUPER_EFF = "getTrMultStatisSuperEff";
    private static final String GET_TR_MULT_STATIS_TYPES = "getTrMultStatisTypes";
    private static final String GET_TR_MULT_STATIS_TYPES_STAT = "getTrMultStatisTypesStat";
    private static final String CLICK_FAIL_STATUS = "clickFailStatus";
    private static final String GET_TR_FAIL_STATUS = "getTrFailStatus";
    private static final String CLICK_TYPES_PK_ABILITY = "clickTypesPkAbility";
    private static final String GET_TR_TYPES_PK_ABILITY = "getTrTypesPkAbility";
    private static final String GET_TR_TYPES_PK = "getTrTypesPk";
    private static final String CLICK_IMMU_STATUS = "clickImmuStatus";
    private static final String GET_TR_IMMU_STATUS = "getTrImmuStatus";
    private static final String GET_TR_IMMU_TYPES = "getTrImmuTypes";
    private static final String CLICK_SYNCHRO_STATUS = "clickSynchroStatus";
    private static final String GET_TR_SYNCHRO_STATUS = "getTrSynchroStatus";
    private static final String GET_TR_WIN_EV_FIGHT = "getTrWinEvFight";
    private static final String CLICK_TRAP_MOVE = "clickTrapMove";
    private static final String GET_TR_TRAP_MOVE = "getTrTrapMove";
    private static final String CLICK_GLOBAL_MOVE = "clickGlobalMove";
    private static final String GET_TR_GLOBAL_MOVE = "getTrGlobalMove";
    private static final String CLICK_TEAM_MOVE = "clickTeamMove";
    private static final String GET_TR_TEAM_MOVE = "getTrTeamMove";
    private static final String CLICK_IMMU_MOVE = "clickImmuMove";
    private static final String GET_TR_IMMU_MOVE = "getTrImmuMove";
    private static final String CLICK_WEATHER = "clickWeather";
    private static final String GET_TR_WEATHER = "getTrWeather";
    private static final String RATE_FOR_ATTACK_FIRST = "rateForAttackFirst";
    private static final String DETERMINATED = "determinated";
    private static final String SEARCH = "search";
    private static final String GET_MINI_IMAGE = "getMiniImage";
    private static final String CLICK_LINK = "clickLink";
    private static final String DISPLAY_NAME = "displayName";
    private static final String ITEM_BEAN = "itemBean";
    private static final String NAME = "name";
    private static final String CATCHING_RATE = "catchingRate";
    private static final String MAP_VARS = "mapVars";
    private static final String HEAL_HP_BY_SUPER_EFF_MOVE = "healHpBySuperEffMove";
    private static final String LAW_FOR_ATTACK_FIRST = "lawForAttackFirst";
    private static final String WITHOUT_FAIL = "withoutFail";
    private static final String HEAL_PP = "healPp";
    private static final String HEAL_HP = "healHp";
    private static final String MAX_HP_HEALING_HP = "maxHpHealingHp";
    private static final String HEAL_HP_RATE = "healHpRate";
    private static final String MAX_HP_HEALING_HP_RATE = "maxHpHealingHpRate";
    private static final String MULT_FOES_DAMAGE = "multFoesDamage";
    private static final String MULT_STAT = "multStat";
    private static final String HEAL_STATUS = "healStatus";
    private static final String DAMAGE_RATE_RECOIL_FOE = "damageRateRecoilFoe";
    private static final String BOOST_STATIS = "boostStatis";
    private static final String CATEGORY_BOOSTING = "categoryBoosting";
    private static final String WIN_PP = "winPp";
    private static final String HAPPINESS = "happiness";
    private static final String EVS = "evs";
    private static final String MAX_EV = "maxEv";
    private static final String POKEMON = "pokemon";
    private static final String LEVEL = "level";
    private static final String HEALING_ITEM_BEAN = "healingItemBean";
    private static final String HP = "hp";
    private static final String HEALING_STATUS_BEAN = "healingStatusBean";
    private static final String HEALED_HP_RATE = "healedHpRate";
    private static final String HEALING_TEAM = "healingTeam";
    private static final String HEALING_ALL_MOVES_PP = "healingAllMovesPp";
    private static final String HEALING_MOVE_FULLPP = "healingMoveFullpp";
    private static final String HEALED_MOVE_PP = "healedMovePp";
    private static final String HEALING_ALL_MOVES_FULLPP = "healingAllMovesFullpp";
    private static final String HEALING_KO = "healingKo";
    private static final String STATUS = "status";
    private static final String ITEM_IMAGE = "itemImage";
    private static final String DESCRIPTION = "description";
    private static final String PRICE = "price";
    private static final String END_ROUND = "endRound";
    private static final String END_ROUND_RANK = "endRoundRank";
    private static final String REASONS_END_ROUND = "reasonsEndRound";
    private static final String MAP_VARS_FAIL_END_ROUND = "mapVarsFailEndRound";
    private static final String SENDING = "sending";
    private static final String EFFECT_SEND_BEAN = "effectSendBean";
    private static final String CANCEL_IMMU_TYPE = "cancelImmuType";
    private static final String AGAINST_EVO = "againstEvo";
    private static final String ATTACK_LAST = "attackLast";
    private static final String BOOST_EXP = "boostExp";
    private static final String REPELLING_WILD_PK = "repellingWildPk";
    private static final String IMMU_LOW_STATIS = "immuLowStatis";
    private static final String ATTACKS_SOON = "attacksSoon";
    private static final String PROTECT_AGAINST_KO = "protectAgainstKo";
    private static final String PROTECT_AGAINST_KO_IF_FULL_HP = "protectAgainstKoIfFullHp";
    private static final String DRAINED_HP_BY_DAMAGE_RATE = "drainedHpByDamageRate";
    private static final String MULT_TRAPPING_DAMAGE = "multTrappingDamage";
    private static final String MULT_WINNING_MONEY = "multWinningMoney";
    private static final String MULT_WINNING_HAPPINESS = "multWinningHappiness";
    private static final String MULT_WINNING_EV = "multWinningEv";
    private static final String MULT_WINNING_EXP = "multWinningExp";
    private static final String MULT_DRAINED_HP = "multDrainedHp";
    private static final String DAMAGE_RECOIL = "damageRecoil";
    private static final String MULT_POWER = "multPower";
    private static final String MULT_DAMAGE = "multDamage";
    private static final String MULT_STAT_RANK = "multStatRank";
    private static final String MULT_STAT_POKEMON_RANK = "multStatPokemonRank";
    private static final String BOOST_STATIS_SUPER_EFF = "boostStatisSuperEff";
    private static final String BOOST_STATIS_TYPES = "boostStatisTypes";
    private static final String FAIL_STATUS = "failStatus";
    private static final String TYPES_PK = "typesPk";
    private static final String TYPES_PK_ABILITIES = "typesPkAbilities";
    private static final String IMMU_STATUS = "immuStatus";
    private static final String IMMU_TYPES = "immuTypes";
    private static final String SYNCHRO_STATUS = "synchroStatus";
    private static final String WIN_EV_FIGHT = "winEvFight";
    private static final String INCREASING_MAX_NB_ROUND_TRAP = "increasingMaxNbRoundTrap";
    private static final String INCREASING_MAX_NB_ROUND_GLOBAL_MOVE = "increasingMaxNbRoundGlobalMove";
    private static final String INCREASING_MAX_NB_ROUND_TEAM_MOVE = "increasingMaxNbRoundTeamMove";
    private static final String IMMU_MOVES = "immuMoves";
    private static final String IMMU_WEATHER = "immuWeather";
    private static final String TYPED_NAME = "typedName";
    private static final String TYPED_PRICE = "typedPrice";
    private static final String TYPED_CLASS = "typedClass";
    private static final String ITEMS = "items";
    private static final String STEPS = "steps";

    public static void build(BeanLgNames _std) {
        buildBallBean(_std);
        buildBerryBean(_std);
        buildBoostBean(_std);
        buildEvolvingItemBean(_std);
        buildEvolvingStoneBean(_std);
        buildFossilBean(_std);
        buildHealingHpBean(_std);
        buildHealingItemBean(_std);
        buildHealingPpBean(_std);
        buildHealingStatusBean(_std);
        buildItemBean(_std);
        buildItemForBattleBean(_std);
        buildItemsBean(_std);
        buildRepelBean(_std);
        buildSellingItemBean(_std);
    }
    private static void buildBallBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_BALL_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(CATCHING_RATE,new StandardField(CATCHING_RATE,_std.getAliasString(),false,false,type_));
        fields_.put(MAP_VARS,new StandardField(MAP_VARS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        _std.getStandards().put(TYPE_BALL_BEAN, type_);
    }
    private static void buildBerryBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_BERRY_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(HEAL_HP_BY_SUPER_EFF_MOVE,new StandardField(HEAL_HP_BY_SUPER_EFF_MOVE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(LAW_FOR_ATTACK_FIRST,new StandardField(LAW_FOR_ATTACK_FIRST,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(WITHOUT_FAIL,new StandardField(WITHOUT_FAIL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(HEAL_PP,new StandardField(HEAL_PP,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(HEAL_HP,new StandardField(HEAL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MAX_HP_HEALING_HP,new StandardField(MAX_HP_HEALING_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HEAL_HP_RATE,new StandardField(HEAL_HP_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MAX_HP_HEALING_HP_RATE,new StandardField(MAX_HP_HEALING_HP_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_FOES_DAMAGE,new StandardField(MULT_FOES_DAMAGE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_STAT,new StandardField(MULT_STAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(HEAL_STATUS,new StandardField(HEAL_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(DAMAGE_RATE_RECOIL_FOE,new StandardField(DAMAGE_RATE_RECOIL_FOE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(BOOST_STATIS,new StandardField(BOOST_STATIS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(CATEGORY_BOOSTING,new StandardField(CATEGORY_BOOSTING,_std.getAliasString(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(IS_HEALING_PP,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_FOES_DAMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_CATEG_RECOIL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_BOOST_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_BERRY_BEAN, type_);
    }
    private static void buildBoostBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_BOOST_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(WIN_PP,new StandardField(WIN_PP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HAPPINESS,new StandardField(HAPPINESS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(EVS,new StandardField(EVS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAX_EV,new StandardField(MAX_EV,_std.getAliasPrimInteger(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_BALL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_HAPPINESS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HAPPINESS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_EV,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_BOOST_BEAN, type_);
    }
    private static void buildEvolvingItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLVING_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(POKEMON,new StandardField(POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLVING_ITEM_BEAN, type_);
    }
    private static void buildEvolvingStoneBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_EVOLVING_STONE_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(POKEMON,new StandardField(POKEMON, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_EVOLVING_STONE_BEAN, type_);
    }
    private static void buildFossilBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_FOSSIL_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_TR_POKEMON,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_FOSSIL_BEAN, type_);
    }
    private static void buildHealingHpBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_HEALING_HP_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(HP,new StandardField(HP,PokemonStandards.TYPE_RATE,false,false,type_));
        _std.getStandards().put(TYPE_HEALING_HP_BEAN, type_);
    }

    private static void buildHealingItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_HEALING_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(HEALING_ITEM_BEAN,new StandardField(HEALING_ITEM_BEAN,_std.getAliasString(),false,false,type_));
        fields_.put(HEALING_TEAM,new StandardField(HEALING_TEAM,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(HAPPINESS,new StandardField(HAPPINESS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(IS_BALL,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_HAPPINESS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_HAPPINESS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_HEALING_ITEM_BEAN, type_);
    }
    private static void buildHealingPpBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_HEALING_PP_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(HEALING_ALL_MOVES_PP,new StandardField(HEALING_ALL_MOVES_PP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(HEALING_MOVE_FULLPP,new StandardField(HEALING_MOVE_FULLPP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(HEALED_MOVE_PP,new StandardField(HEALED_MOVE_PP,_std.getAliasPrimLong(),false,false,type_));
        fields_.put(HEALING_ALL_MOVES_FULLPP,new StandardField(HEALING_ALL_MOVES_FULLPP,_std.getAliasPrimLong(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(LIMITED_PP_MOVE,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(LIMITED_PP_MOVES,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_HEALING_PP_BEAN, type_);
    }
    private static void buildHealingStatusBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_HEALING_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(HEALING_STATUS_BEAN,new StandardField(HEALING_STATUS_BEAN,_std.getAliasString(),false,false,type_));
        fields_.put(HEALING_KO,new StandardField(HEALING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STATUS,new StandardField(STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(HEALED_HP_RATE,new StandardField(HEALED_HP_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_HEALING_STATUS_BEAN, type_);
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_HEALING_HP_STATUS_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN, MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_HEALING_HP_STATUS_BEAN, type_);
    }
    private static void buildItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(DISPLAY_NAME,new StandardField(DISPLAY_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(ITEM_BEAN,new StandardField(ITEM_BEAN,_std.getAliasString(),false,false,type_));
        fields_.put(NAME,new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.put(ITEM_IMAGE,new StandardField(ITEM_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(DESCRIPTION,new StandardField(DESCRIPTION,_std.getAliasString(),false,false,type_));
        fields_.put(PRICE,new StandardField(PRICE,_std.getAliasPrimInteger(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CLICK_ITEMS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_ITEM_BEAN, type_);
    }
    private static void buildItemForBattleBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_ITEM_FOR_BATTLE_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(END_ROUND,new StandardField(END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(END_ROUND_RANK,new StandardField(END_ROUND_RANK,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(REASONS_END_ROUND,new StandardField(REASONS_END_ROUND, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(MAP_VARS_FAIL_END_ROUND,new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(SENDING,new StandardField(SENDING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(EFFECT_SEND_BEAN,new StandardField(EFFECT_SEND_BEAN,_std.getAliasString(),false,false,type_));
        fields_.put(CANCEL_IMMU_TYPE,new StandardField(CANCEL_IMMU_TYPE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(AGAINST_EVO,new StandardField(AGAINST_EVO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ATTACK_LAST,new StandardField(ATTACK_LAST,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(BOOST_EXP,new StandardField(BOOST_EXP,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(REPELLING_WILD_PK,new StandardField(REPELLING_WILD_PK,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(IMMU_LOW_STATIS,new StandardField(IMMU_LOW_STATIS,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ATTACKS_SOON,new StandardField(ATTACKS_SOON,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(PROTECT_AGAINST_KO,new StandardField(PROTECT_AGAINST_KO,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(PROTECT_AGAINST_KO_IF_FULL_HP,new StandardField(PROTECT_AGAINST_KO_IF_FULL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(DRAINED_HP_BY_DAMAGE_RATE,new StandardField(DRAINED_HP_BY_DAMAGE_RATE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_TRAPPING_DAMAGE,new StandardField(MULT_TRAPPING_DAMAGE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_WINNING_MONEY,new StandardField(MULT_WINNING_MONEY,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_WINNING_HAPPINESS,new StandardField(MULT_WINNING_HAPPINESS,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_WINNING_EV,new StandardField(MULT_WINNING_EV,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_WINNING_EXP,new StandardField(MULT_WINNING_EXP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_DRAINED_HP,new StandardField(MULT_DRAINED_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(DAMAGE_RECOIL,new StandardField(DAMAGE_RECOIL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(MULT_POWER,new StandardField(MULT_POWER,_std.getAliasString(),false,false,type_));
        fields_.put(MULT_DAMAGE,new StandardField(MULT_DAMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(MULT_STAT_RANK,new StandardField(MULT_STAT_RANK, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_STAT_POKEMON_RANK,new StandardField(MULT_STAT_POKEMON_RANK, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(BOOST_STATIS_SUPER_EFF,new StandardField(BOOST_STATIS_SUPER_EFF, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(BOOST_STATIS_TYPES,new StandardField(BOOST_STATIS_TYPES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MULT_STAT,new StandardField(MULT_STAT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(FAIL_STATUS,new StandardField(FAIL_STATUS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(MAP_VARS,new StandardField(MAP_VARS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(TYPES_PK,new StandardField(TYPES_PK, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(TYPES_PK_ABILITIES,new StandardField(TYPES_PK_ABILITIES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMU_STATUS,new StandardField(IMMU_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMU_TYPES,new StandardField(IMMU_TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(SYNCHRO_STATUS,new StandardField(SYNCHRO_STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(WIN_EV_FIGHT,new StandardField(WIN_EV_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(INCREASING_MAX_NB_ROUND_TRAP,new StandardField(INCREASING_MAX_NB_ROUND_TRAP, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(INCREASING_MAX_NB_ROUND_GLOBAL_MOVE,new StandardField(INCREASING_MAX_NB_ROUND_GLOBAL_MOVE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(INCREASING_MAX_NB_ROUND_TEAM_MOVE,new StandardField(INCREASING_MAX_NB_ROUND_TEAM_MOVE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.put(IMMU_MOVES,new StandardField(IMMU_MOVES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.put(IMMU_WEATHER,new StandardField(IMMU_WEATHER, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(GET_EFFECT_SENDING,params_,PokemonStandards.TYPE_EFFECT_WHILE_SENDING, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_RANK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_PK_RANK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_MULT_STAT_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STATIS_SUPER_EFF,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STATIS_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STATIS_TYPES_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_MULT_STAT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_FAIL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_FAIL_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TYPES_PK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPES_PK_ABILITY,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TYPES_PK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_TYPES,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_SYNCHRO_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_SYNCHRO_STATUS,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_WIN_EV_FIGHT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TRAP_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TRAP_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_GLOBAL_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_GLOBAL_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_TEAM_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_TEAM_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_IMMU_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_IMMU_MOVE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_WEATHER,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(RATE_FOR_ATTACK_FIRST,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(DETERMINATED,params_,_std.getAliasPrimBoolean(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_ITEM_FOR_BATTLE_BEAN, type_);
    }
    private static void buildItemsBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_ITEMS_BEAN, fields_, constructors_, methods_, AikiBeansStd.TYPE_COMMON_BEAN, MethodModifier.NORMAL);
        fields_.put(TYPED_NAME,new StandardField(TYPED_NAME,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_PRICE,new StandardField(TYPED_PRICE,_std.getAliasString(),false,false,type_));
        fields_.put(TYPED_CLASS,new StandardField(TYPED_CLASS,_std.getAliasString(),false,false,type_));
        fields_.put(ITEMS,new StandardField(ITEMS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(SEARCH,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_MINI_IMAGE,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(CLICK_LINK,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_ITEMS_BEAN, type_);
    }
    private static void buildRepelBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_REPEL_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        fields_.put(STEPS,new StandardField(STEPS,_std.getAliasPrimLong(),false,false,type_));
        _std.getStandards().put(TYPE_REPEL_BEAN, type_);
    }
    private static void buildSellingItemBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_SELLING_ITEM_BEAN, fields_, constructors_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN, MethodModifier.NORMAL);
        _std.getStandards().put(TYPE_SELLING_ITEM_BEAN, type_);
    }
    public static ResultErrorStd getResultBallBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        BallBean instance_ = (BallBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,CATCHING_RATE)) {
            res_.setResult(new StringStruct(instance_.getCatchingRate()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS)) {
            res_.setResult(new DefaultStruct(instance_.getMapVars(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultBerryBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        BerryBean instance_ = (BerryBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HEAL_HP_BY_SUPER_EFF_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpBySuperEffMove(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LAW_FOR_ATTACK_FIRST)) {
            res_.setResult(BooleanStruct.of(instance_.getLawForAttackFirst()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WITHOUT_FAIL)) {
            res_.setResult(BooleanStruct.of(instance_.getWithoutFail()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_PP)) {
            res_.setResult(new IntStruct(instance_.getHealPp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_HP)) {
            res_.setResult(new DefaultStruct(instance_.getHealHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_HP_HEALING_HP)) {
            res_.setResult(new DefaultStruct(instance_.getMaxHpHealingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_HP_RATE)) {
            res_.setResult(new DefaultStruct(instance_.getHealHpRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_HP_HEALING_HP_RATE)) {
            res_.setResult(new DefaultStruct(instance_.getMaxHpHealingHpRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_FOES_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMultFoesDamage(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEAL_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getHealStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_RECOIL_FOE)) {
            res_.setResult(new DefaultStruct(instance_.getDamageRateRecoilFoe(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STATIS)) {
            res_.setResult(new DefaultStruct(instance_.getBoostStatis(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CATEGORY_BOOSTING)) {
            res_.setResult(new StringStruct(instance_.getCategoryBoosting()));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultBoostBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        BoostBean instance_ = (BoostBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WIN_PP)) {
            res_.setResult(new DefaultStruct(instance_.getWinPp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new DefaultStruct(instance_.getHappiness(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVS)) {
            res_.setResult(new DefaultStruct(instance_.getEvs(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAX_EV)) {
            res_.setResult(new IntStruct(instance_.getMaxEv()));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolvingItemBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EvolvingItemBean instance_ = (EvolvingItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultEvolvingStoneBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        EvolvingStoneBean instance_ = (EvolvingStoneBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,POKEMON)) {
            res_.setResult(new DefaultStruct(instance_.getPokemon(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultFossilBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        FossilBean instance_ = (FossilBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultHealingHpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        HealingHpBean instance_ = (HealingHpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HP)) {
            res_.setResult(new DefaultStruct(instance_.getHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return AikiBeansItemsStd.getResultHealingItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultHealingItemBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        HealingItemBean instance_ = (HealingItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HEALING_ITEM_BEAN)) {
            res_.setResult(new StringStruct(HealingItemBean.HEALING_ITEM_BEAN));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_TEAM)) {
            res_.setResult(BooleanStruct.of(instance_.getHealingTeam()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new DefaultStruct(instance_.getHappiness(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultHealingPpBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        HealingPpBean instance_ = (HealingPpBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HEALING_ALL_MOVES_PP)) {
            res_.setResult(BooleanStruct.of(instance_.getHealingAllMovesPp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_MOVE_FULLPP)) {
            res_.setResult(BooleanStruct.of(instance_.getHealingMoveFullpp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALED_MOVE_PP)) {
            res_.setResult(new LongStruct(instance_.getHealedMovePp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_ALL_MOVES_FULLPP)) {
            res_.setResult(new LongStruct(instance_.getHealingAllMovesFullpp()));
            return res_;
        }
        return AikiBeansItemsStd.getResultHealingItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultHealingStatusBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        HealingStatusBean instance_ = (HealingStatusBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,HEALED_HP_RATE)) {
            res_.setResult(new DefaultStruct(instance_.getHealedHpRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_STATUS_BEAN)) {
            res_.setResult(new StringStruct(HealingStatusBean.HEALING_STATUS_BEAN));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HEALING_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getHealingKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansItemsStd.getResultHealingItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultItemBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        ItemBean instance_ = (ItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DISPLAY_NAME)) {
            res_.setResult(new StringStruct(instance_.getDisplayName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEM_BEAN)) {
            res_.setResult(new StringStruct(ItemBean.ITEM_BEAN));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEM_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getItemImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DESCRIPTION)) {
            res_.setResult(new StringStruct(instance_.getDescription()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PRICE)) {
            res_.setResult(new IntStruct(instance_.getPrice()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultItemForBattleBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        ItemForBattleBean instance_ = (ItemForBattleBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getEndRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_ROUND_RANK)) {
            res_.setResult(new IntStruct(instance_.getEndRoundRank()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REASONS_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getReasonsEndRound(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS_FAIL_END_ROUND)) {
            res_.setResult(new DefaultStruct(instance_.getMapVarsFailEndRound(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SENDING)) {
            res_.setResult(BooleanStruct.of(instance_.getSending()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EFFECT_SEND_BEAN)) {
            res_.setResult(new StringStruct(ItemForBattleBean.EFFECT_SEND_BEAN));
            return res_;
        }
        if (StringList.quickEq(fieldName_,CANCEL_IMMU_TYPE)) {
            res_.setResult(BooleanStruct.of(instance_.getCancelImmuType()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,AGAINST_EVO)) {
            res_.setResult(BooleanStruct.of(instance_.getAgainstEvo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ATTACK_LAST)) {
            res_.setResult(BooleanStruct.of(instance_.getAttackLast()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_EXP)) {
            res_.setResult(BooleanStruct.of(instance_.getBoostExp()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REPELLING_WILD_PK)) {
            res_.setResult(BooleanStruct.of(instance_.getRepellingWildPk()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_LOW_STATIS)) {
            res_.setResult(BooleanStruct.of(instance_.getImmuLowStatis()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ATTACKS_SOON)) {
            res_.setResult(BooleanStruct.of(instance_.getAttacksSoon()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_AGAINST_KO)) {
            res_.setResult(new DefaultStruct(instance_.getProtectAgainstKo(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PROTECT_AGAINST_KO_IF_FULL_HP)) {
            res_.setResult(new DefaultStruct(instance_.getProtectAgainstKoIfFullHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DRAINED_HP_BY_DAMAGE_RATE)) {
            res_.setResult(new DefaultStruct(instance_.getDrainedHpByDamageRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_TRAPPING_DAMAGE)) {
            res_.setResult(new DefaultStruct(instance_.getMultTrappingDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_WINNING_MONEY)) {
            res_.setResult(new DefaultStruct(instance_.getMultWinningMoney(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_WINNING_HAPPINESS)) {
            res_.setResult(new DefaultStruct(instance_.getMultWinningHappiness(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_WINNING_EV)) {
            res_.setResult(new DefaultStruct(instance_.getMultWinningEv(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_WINNING_EXP)) {
            res_.setResult(new DefaultStruct(instance_.getMultWinningExp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DRAINED_HP)) {
            res_.setResult(new DefaultStruct(instance_.getMultDrainedHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RECOIL)) {
            res_.setResult(new DefaultStruct(instance_.getDamageRecoil(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_POWER)) {
            res_.setResult(new StringStruct(instance_.getMultPower()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getMultDamage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_RANK)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatRank(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT_POKEMON_RANK)) {
            res_.setResult(new DefaultStruct(instance_.getMultStatPokemonRank(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STATIS_SUPER_EFF)) {
            res_.setResult(new DefaultStruct(instance_.getBoostStatisSuperEff(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BOOST_STATIS_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getBoostStatisTypes(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MULT_STAT)) {
            res_.setResult(new DefaultStruct(instance_.getMultStat(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FAIL_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getFailStatus(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MAP_VARS)) {
            res_.setResult(new DefaultStruct(instance_.getMapVars(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES_PK)) {
            res_.setResult(new DefaultStruct(instance_.getTypesPk(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES_PK_ABILITIES)) {
            res_.setResult(new DefaultStruct(instance_.getTypesPkAbilities(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getImmuStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_TYPES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuTypes(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SYNCHRO_STATUS)) {
            res_.setResult(new DefaultStruct(instance_.getSynchroStatus(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WIN_EV_FIGHT)) {
            res_.setResult(new DefaultStruct(instance_.getWinEvFight(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCREASING_MAX_NB_ROUND_TRAP)) {
            res_.setResult(new DefaultStruct(instance_.getIncreasingMaxNbRoundTrap(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCREASING_MAX_NB_ROUND_GLOBAL_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getIncreasingMaxNbRoundGlobalMove(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,INCREASING_MAX_NB_ROUND_TEAM_MOVE)) {
            res_.setResult(new DefaultStruct(instance_.getIncreasingMaxNbRoundTeamMove(), BeanNatLgNames.TYPE_MAP));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_MOVES)) {
            res_.setResult(new DefaultStruct(instance_.getImmuMoves(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMMU_WEATHER)) {
            res_.setResult(new DefaultStruct(instance_.getImmuWeather(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd getResultItemsBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        ItemsBean instance_ = (ItemsBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            res_.setResult(new StringStruct(instance_.getTypedName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_PRICE)) {
            res_.setResult(new StringStruct(instance_.getTypedPrice()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_CLASS)) {
            res_.setResult(new StringStruct(instance_.getTypedClass()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEMS)) {
            res_.setResult(new DefaultStruct(instance_.getItems(), BeanNatLgNames.TYPE_LIST));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultRepelBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        RepelBean instance_ = (RepelBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,STEPS)) {
            res_.setResult(new LongStruct(instance_.getSteps()));
            return res_;
        }
        return AikiBeansItemsStd.getResultItemBean(_cont, _classField, _instance);
    }
    public static ResultErrorStd setResultItemBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        ItemBean instance_ = (ItemBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,NAME)) {
            instance_.setName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultItemsBean(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        ItemsBean instance_ = (ItemsBean) ((RealInstanceStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,TYPED_NAME)) {
            instance_.setTypedName((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_PRICE)) {
            instance_.setTypedPrice((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPED_CLASS)) {
            instance_.setTypedClass((String) _value);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodBerryBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BerryBean instance_ = (BerryBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_HEALING_PP)) {
            res_.setResult(BooleanStruct.of(instance_.isHealingPp()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_FOES_DAMAGE)) {
            res_.setResult(new StringStruct(instance_.getTrMultFoesDamage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_CATEG_RECOIL)) {
            res_.setResult(new StringStruct(instance_.getTrCategRecoil((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_BOOST_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrBoostStat((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodBoostBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BoostBean instance_ = (BoostBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_BALL)) {
            res_.setResult(BooleanStruct.of(instance_.isBall((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_HAPPINESS)) {
            res_.setResult(new StringStruct(instance_.clickHappiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HAPPINESS)) {
            res_.setResult(new StringStruct(instance_.getTrHappiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_EV)) {
            res_.setResult(new StringStruct(instance_.getTrEv((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEvolvingItemBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolvingItemBean instance_ = (EvolvingItemBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodEvolvingStoneBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        EvolvingStoneBean instance_ = (EvolvingStoneBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodFossilBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        FossilBean instance_ = (FossilBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_POKEMON)) {
            res_.setResult(new StringStruct(instance_.clickPokemon()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON)) {
            res_.setResult(new StringStruct(instance_.getTrPokemon()));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodHealingItemBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        HealingItemBean instance_ = (HealingItemBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,IS_BALL)) {
            res_.setResult(BooleanStruct.of(instance_.isBall((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_HAPPINESS)) {
            res_.setResult(new StringStruct(instance_.clickHappiness((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_HAPPINESS)) {
            res_.setResult(new StringStruct(instance_.getTrHappiness((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodHealingPpBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        HealingPpBean instance_ = (HealingPpBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,LIMITED_PP_MOVE)) {
            res_.setResult(BooleanStruct.of(instance_.limitedPpMove()));
            return res_;
        }
        if (StringList.quickEq(methodName_,LIMITED_PP_MOVES)) {
            res_.setResult(BooleanStruct.of(instance_.limitedPpMoves()));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodHealingItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodHealingStatusBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        HealingStatusBean instance_ = (HealingStatusBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrStatus((Long)_args[0])));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodHealingItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodItemBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        ItemBean instance_ = (ItemBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CLICK_ITEMS)) {
            res_.setResult(new StringStruct(instance_.clickItems()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodItemForBattleBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        ItemForBattleBean instance_ = (ItemForBattleBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_EFFECT_SENDING)) {
            res_.setResult(DefaultStruct.newInstance(instance_.getEffectSending(),PokemonStandards.TYPE_EFFECT_WHILE_SENDING));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_RANK)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatRank((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_PK_RANK)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatPkRank((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_MULT_STAT_PK)) {
            res_.setResult(new StringStruct(instance_.clickMultStatPk((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT_PK)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatPk((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STATIS_SUPER_EFF)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatisSuperEff((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STATIS_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatisTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STATIS_TYPES_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStatisTypesStat((Long)_args[0],(Long)_args[1])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_MULT_STAT)) {
            res_.setResult(new StringStruct(instance_.getTrMultStat((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_FAIL_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickFailStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_FAIL_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrFailStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TYPES_PK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.clickTypesPkAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TYPES_PK_ABILITY)) {
            res_.setResult(new StringStruct(instance_.getTrTypesPkAbility((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TYPES_PK)) {
            res_.setResult(new StringStruct(instance_.getTrTypesPk((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickImmuStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrImmuStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_TYPES)) {
            res_.setResult(new StringStruct(instance_.getTrImmuTypes((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_SYNCHRO_STATUS)) {
            res_.setResult(new StringStruct(instance_.clickSynchroStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_SYNCHRO_STATUS)) {
            res_.setResult(new StringStruct(instance_.getTrSynchroStatus((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_WIN_EV_FIGHT)) {
            res_.setResult(new StringStruct(instance_.getTrWinEvFight((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TRAP_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickTrapMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TRAP_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrTrapMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_GLOBAL_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickGlobalMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_GLOBAL_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrGlobalMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_TEAM_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_TEAM_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrTeamMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_IMMU_MOVE)) {
            res_.setResult(new StringStruct(instance_.clickImmuMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_IMMU_MOVE)) {
            res_.setResult(new StringStruct(instance_.getTrImmuMove((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_WEATHER)) {
            res_.setResult(new StringStruct(instance_.clickWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_WEATHER)) {
            res_.setResult(new StringStruct(instance_.getTrWeather((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,RATE_FOR_ATTACK_FIRST)) {
            res_.setResult(new DefaultStruct(instance_.rateForAttackFirst(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(methodName_,DETERMINATED)) {
            res_.setResult(BooleanStruct.of(instance_.determinated()));
            return res_;
        }
        return AikiBeansItemsStd.invokeMethodItemBean(_cont, _instance, _method, _args);
    }
    public static ResultErrorStd invokeMethodItemsBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        ItemsBean instance_ = (ItemsBean) ((RealInstanceStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,SEARCH)) {
            res_.setResult(new StringStruct(instance_.search()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_MINI_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getMiniImage((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,CLICK_LINK)) {
            res_.setResult(new StringStruct(instance_.clickLink((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
