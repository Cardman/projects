package aiki.beans.items;

import aiki.beans.AikiBeansStd;
import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.util.CustList;
public final class AikiBeansItemsStd{
    public static final String BEAN_BALL="ball";
    public static final String BEAN_BERRY="berry";
    public static final String BEAN_BOOST="boost";
    public static final String BEAN_EVO_ITEM="evo_item";
    public static final String BEAN_EVO_STONE="evo_stone";
    public static final String BEAN_FOSSIL="fossil";
    public static final String BEAN_HEALINGHP="healinghp";
    public static final String BEAN_HEALINGHPSTATUS="healinghpstatus";
    public static final String BEAN_HEALINGITEM="healingitem";
    public static final String BEAN_HEALINGPP="healingpp";
    public static final String BEAN_HEALINGSTATUS="healingstatus";
    public static final String BEAN_ITEMS="items";
    public static final String BEAN_ITEM="item";
    public static final String BEAN_ITEMFORBATTLE="itemforbattle";
    public static final String BEAN_REPEL="repel";
    public static final String BEAN_SELLINGITEM="sellingitem";
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
//    private static final String CLICK_ITEMS = "clickItems";
//    private static final String GET_EFFECT_SENDING = "getEffectSending";
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
//    private static final String SEARCH = "search";
    private static final String GET_MINI_IMAGE = "getMiniImage";
    private static final String CLICK_LINK = "clickLink";
    private static final String DISPLAY_NAME = "displayName";
//    private static final String ITEM_BEAN = "itemBean";
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
//    private static final String EFFECT_SEND_BEAN = "effectSendBean";
    private static final String CANCEL_IMMU_TYPE = "cancelImmuType";
    private static final String AGAINST_EVO = "againstEvo";
    private static final String ATTACK_LAST = "attackLast";
    private static final String BOOST_EXP = "boostExp";
//    private static final String REPELLING_WILD_PK = "repellingWildPk";
    private static final String IMMU_LOW_STATIS = "immuLowStatis";
    private static final String ATTACKS_SOON = "attacksSoon";
    private static final String PROTECT_AGAINST_KO = "protectAgainstKo";
    private static final String PROTECT_AGAINST_KO_IF_FULL_HP = "protectAgainstKoIfFullHp";
    private static final String DRAINED_HP_BY_DAMAGE_RATE = "drainedHpByDamageRate";
    private static final String MULT_TRAPPING_DAMAGE = "multTrappingDamage";
//    private static final String MULT_WINNING_MONEY = "multWinningMoney";
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
//    private static final String TYPED_NAME = "typedName";
//    private static final String TYPED_PRICE = "typedPrice";
//    private static final String TYPED_CLASS = "typedClass";
    private static final String ITEMS = "items";
    private static final String STEPS = "steps";
    private AikiBeansItemsStd(){}
    public static void build(PokemonStandards _std) {
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
        buildHealingHpStatusBean(_std);
        buildItemBean(_std);
        buildItemForBattleBean(_std);
        buildItemsBean(_std);
        buildRepelBean(_std);
        buildSellingItemBean(_std);
    }
    private static void buildBallBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(CATCHING_RATE,BeanNatCommonLgNames.STRING, new BallBeanCatchingRateGet(),null));
        fields_.add(new StandardField(MAP_VARS, BeanNatCommonLgNames.TYPE_MAP, new BallBeanMapVarsGet(),null));
        _std.getStds().addEntry(TYPE_BALL_BEAN, type_);
    }
    private static void buildBerryBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(HEAL_HP_BY_SUPER_EFF_MOVE,BeanNatCommonLgNames.TYPE_RATE, new BerryBeanHealHpBySuperEffMoveGet(),null));
        fields_.add(new StandardField(LAW_FOR_ATTACK_FIRST,BeanNatCommonLgNames.PRIM_BOOLEAN, new BerryBeanLawForAttackFirstGet(),null));
        fields_.add(new StandardField(WITHOUT_FAIL,BeanNatCommonLgNames.PRIM_BOOLEAN, new BerryBeanWithoutFailGet(),null));
        fields_.add(new StandardField(HEAL_PP, BeanNatCommonLgNames.PRIM_INTEGER, new BerryBeanHealPpGet(),null));
        fields_.add(new StandardField(HEAL_HP,BeanNatCommonLgNames.TYPE_RATE, new BerryBeanHealHpGet(),null));
        fields_.add(new StandardField(MAX_HP_HEALING_HP,BeanNatCommonLgNames.TYPE_RATE, new BerryBeanMaxHpHealingHpGet(),null));
        fields_.add(new StandardField(HEAL_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, new BerryBeanHealHpRateGet(),null));
        fields_.add(new StandardField(MAX_HP_HEALING_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, new BerryBeanMaxHpHealingHpRateGet(),null));
        fields_.add(new StandardField(MULT_FOES_DAMAGE, BeanNatCommonLgNames.TYPE_MAP, new BerryBeanMultFoesDamageGet(),null));
        fields_.add(new StandardField(MULT_STAT, BeanNatCommonLgNames.TYPE_MAP, new BerryBeanMultStatGet(),null));
        fields_.add(new StandardField(HEAL_STATUS, BeanNatCommonLgNames.TYPE_LIST, new BerryBeanHealStatusGet(),null));
        fields_.add(new StandardField(DAMAGE_RATE_RECOIL_FOE, BeanNatCommonLgNames.TYPE_MAP, new BerryBeanDamageRateRecoilFoeGet(),null));
        fields_.add(new StandardField(BOOST_STATIS, BeanNatCommonLgNames.TYPE_MAP, new BerryBeanBoostStatisGet(),null));
        fields_.add(new StandardField(CATEGORY_BOOSTING,BeanNatCommonLgNames.STRING, new BerryBeanCategoryBoostingGet(),null));
        methods_.add( new SpecNatMethod(IS_HEALING_PP,BeanNatCommonLgNames.PRIM_BOOLEAN, new BerryBeanIsHealingPp()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_FOES_DAMAGE,BeanNatCommonLgNames.STRING, new BerryBeanGetTrMultFoesDamage()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT,BeanNatCommonLgNames.STRING, new BerryBeanGetTrMultStat()));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, new BerryBeanClickStatus()));
        methods_.add( new SpecNatMethod(GET_TR_STATUS,BeanNatCommonLgNames.STRING, new BerryBeanGetTrStatus()));
        methods_.add( new SpecNatMethod(GET_TR_CATEG_RECOIL,BeanNatCommonLgNames.STRING, new BerryBeanGetTrCategRecoil()));
        methods_.add( new SpecNatMethod(GET_TR_BOOST_STAT,BeanNatCommonLgNames.STRING, new BerryBeanGetTrBoostStat()));
        _std.getStds().addEntry(TYPE_BERRY_BEAN, type_);
    }
    private static void buildBoostBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(WIN_PP,BeanNatCommonLgNames.TYPE_RATE, new BoostBeanWinPpGet(),null));
        fields_.add(new StandardField(HAPPINESS, BeanNatCommonLgNames.TYPE_MAP, new BoostBeanHappinessGet(),null));
        fields_.add(new StandardField(EVS, BeanNatCommonLgNames.TYPE_MAP, new BoostBeanEvsGet(),null));
        fields_.add(new StandardField(MAX_EV, BeanNatCommonLgNames.PRIM_INTEGER, new BoostBeanMaxEvGet(),null));
        methods_.add( new SpecNatMethod(IS_BALL,BeanNatCommonLgNames.PRIM_BOOLEAN, new BoostBeanIsBall()));
        methods_.add( new SpecNatMethod(CLICK_HAPPINESS,BeanNatCommonLgNames.STRING, new BoostBeanClickHappiness()));
        methods_.add( new SpecNatMethod(GET_TR_HAPPINESS,BeanNatCommonLgNames.STRING, new BoostBeanGetTrHappiness()));
        methods_.add( new SpecNatMethod(GET_TR_EV,BeanNatCommonLgNames.STRING, new BoostBeanGetTrEv()));
        _std.getStds().addEntry(TYPE_BOOST_BEAN, type_);
    }
    private static void buildEvolvingItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(POKEMON, BeanNatCommonLgNames.TYPE_LIST, new EvolvingItemBeanPokemonGet(),null));
        methods_.add( new SpecNatMethod(CLICK_POKEMON,BeanNatCommonLgNames.STRING, new EvolvingItemBeanClickPokemon()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON,BeanNatCommonLgNames.STRING, new EvolvingItemBeanGetTrPokemon()));
        _std.getStds().addEntry(TYPE_EVOLVING_ITEM_BEAN, type_);
    }
    private static void buildEvolvingStoneBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(POKEMON, BeanNatCommonLgNames.TYPE_LIST, new EvolvingStoneBeanPokemonGet(),null));
        methods_.add( new SpecNatMethod(CLICK_POKEMON,BeanNatCommonLgNames.STRING, new EvolvingStoneBeanClickPokemon()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON,BeanNatCommonLgNames.STRING, new EvolvingStoneBeanGetTrPokemon()));
        _std.getStds().addEntry(TYPE_EVOLVING_STONE_BEAN, type_);
    }
    private static void buildFossilBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(LEVEL, BeanNatCommonLgNames.PRIM_INTEGER, new FossilBeanLevelGet(),null));
        methods_.add( new SpecNatMethod(CLICK_POKEMON,BeanNatCommonLgNames.STRING, new FossilBeanClickPokemon()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON,BeanNatCommonLgNames.STRING, new FossilBeanGetTrPokemon()));
        _std.getStds().addEntry(TYPE_FOSSIL_BEAN, type_);
    }
    private static void buildHealingHpBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN);
        fields_.add(new StandardField(HP,BeanNatCommonLgNames.TYPE_RATE, new HealingHpBeanHpGet(),null));
        _std.getStds().addEntry(TYPE_HEALING_HP_BEAN, type_);
    }
    private static void buildHealingItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(HEALING_ITEM_BEAN,BeanNatCommonLgNames.STRING, new HealingItemBeanHealingItemBeanGet(),null));
        fields_.add(new StandardField(HEALING_TEAM,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingItemBeanHealingTeamGet(),null));
        fields_.add(new StandardField(HAPPINESS, BeanNatCommonLgNames.TYPE_MAP, new HealingItemBeanHappinessGet(),null));
        methods_.add( new SpecNatMethod(IS_BALL,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingItemBeanIsBall()));
        methods_.add( new SpecNatMethod(CLICK_HAPPINESS,BeanNatCommonLgNames.STRING, new HealingItemBeanClickHappiness()));
        methods_.add( new SpecNatMethod(GET_TR_HAPPINESS,BeanNatCommonLgNames.STRING, new HealingItemBeanGetTrHappiness()));
        _std.getStds().addEntry(TYPE_HEALING_ITEM_BEAN, type_);
    }
    private static void buildHealingPpBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN);
        fields_.add(new StandardField(HEALING_ALL_MOVES_PP,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingPpBeanHealingAllMovesPpGet(),null));
        fields_.add(new StandardField(HEALING_MOVE_FULLPP,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingPpBeanHealingMoveFullppGet(),null));
        fields_.add(new StandardField(HEALED_MOVE_PP,BeanNatCommonLgNames.PRIM_LONG, new HealingPpBeanHealedMovePpGet(),null));
        fields_.add(new StandardField(HEALING_ALL_MOVES_FULLPP,BeanNatCommonLgNames.PRIM_LONG, new HealingPpBeanHealingAllMovesFullppGet(),null));
        methods_.add( new SpecNatMethod(LIMITED_PP_MOVE,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingPpBeanLimitedPpMove()));
        methods_.add( new SpecNatMethod(LIMITED_PP_MOVES,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingPpBeanLimitedPpMoves()));
        _std.getStds().addEntry(TYPE_HEALING_PP_BEAN, type_);
    }
    private static void buildHealingStatusBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN);
        fields_.add(new StandardField(HEALED_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, new HealingStatusBeanHealedHpRateGet(),null));
        fields_.add(new StandardField(HEALING_STATUS_BEAN,BeanNatCommonLgNames.STRING, new HealingStatusBeanHealingStatusBeanGet(),null));
        fields_.add(new StandardField(HEALING_KO,BeanNatCommonLgNames.PRIM_BOOLEAN, new HealingStatusBeanHealingKoGet(),null));
        fields_.add(new StandardField(STATUS, BeanNatCommonLgNames.TYPE_LIST, new HealingStatusBeanStatusGet(),null));
        methods_.add( new SpecNatMethod(CLICK_STATUS,BeanNatCommonLgNames.STRING, new HealingStatusBeanClickStatus()));
        methods_.add( new SpecNatMethod(GET_TR_STATUS,BeanNatCommonLgNames.STRING, new HealingStatusBeanGetTrStatus()));
        _std.getStds().addEntry(TYPE_HEALING_STATUS_BEAN, type_);
    }
    private static void buildHealingHpStatusBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN);
        _std.getStds().addEntry(TYPE_HEALING_HP_STATUS_BEAN, type_);
    }
    private static void buildItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
        fields_.add(new StandardField(DISPLAY_NAME,BeanNatCommonLgNames.STRING, new ItemBeanDisplayNameGet(),null));
//        fields_.add(new StandardField(ITEM_BEAN,BeanNatCommonLgNames.STRING, new ItemBeanItemBeanGet(),null));
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING, new ItemBeanNameGet(),new ItemBeanNameSet()));
        fields_.add(new StandardField(ITEM_IMAGE,BeanNatCommonLgNames.STRING, new ItemBeanItemImageGet(),null));
        fields_.add(new StandardField(DESCRIPTION,BeanNatCommonLgNames.STRING, new ItemBeanDescriptionGet(),null));
        fields_.add(new StandardField(PRICE, BeanNatCommonLgNames.PRIM_INTEGER, new ItemBeanPriceGet(),null));
//        methods_.add( new SpecNatMethod(CLICK_ITEMS,BeanNatCommonLgNames.STRING, new ItemBeanClickItems()));
        _std.getStds().addEntry(TYPE_ITEM_BEAN, type_);
    }
    private static void buildItemForBattleBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanEndRoundGet(),null));
        fields_.add(new StandardField(END_ROUND_RANK, BeanNatCommonLgNames.PRIM_INTEGER, new ItemForBattleBeanEndRoundRankGet(),null));
        fields_.add(new StandardField(REASONS_END_ROUND, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanReasonsEndRoundGet(),null));
        fields_.add(new StandardField(MAP_VARS_FAIL_END_ROUND, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanMapVarsFailEndRoundGet(),null));
        fields_.add(new StandardField(SENDING,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanSendingGet(),null));
//        fields_.add(new StandardField(EFFECT_SEND_BEAN,BeanNatCommonLgNames.STRING, new ItemForBattleBeanEffectSendBeanGet(),null));
        fields_.add(new StandardField(CANCEL_IMMU_TYPE,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanCancelImmuTypeGet(),null));
        fields_.add(new StandardField(AGAINST_EVO,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanAgainstEvoGet(),null));
        fields_.add(new StandardField(ATTACK_LAST,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanAttackLastGet(),null));
        fields_.add(new StandardField(BOOST_EXP,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanBoostExpGet(),null));
//        fields_.add(new StandardField(REPELLING_WILD_PK,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new ItemForBattleBeanRepellingWildPkGet(),null));
        fields_.add(new StandardField(IMMU_LOW_STATIS,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanImmuLowStatisGet(),null));
        fields_.add(new StandardField(ATTACKS_SOON,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanAttacksSoonGet(),null));
        fields_.add(new StandardField(PROTECT_AGAINST_KO,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanProtectAgainstKoGet(),null));
        fields_.add(new StandardField(PROTECT_AGAINST_KO_IF_FULL_HP,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanProtectAgainstKoIfFullHpGet(),null));
        fields_.add(new StandardField(DRAINED_HP_BY_DAMAGE_RATE,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanDrainedHpByDamageRateGet(),null));
        fields_.add(new StandardField(MULT_TRAPPING_DAMAGE,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanMultTrappingDamageGet(),null));
//        fields_.add(new StandardField(MULT_WINNING_MONEY,BeanNatCommonLgNames.TYPE_RATE,false,false,new ItemForBattleBeanMultWinningMoneyGet(),null));
        fields_.add(new StandardField(MULT_WINNING_HAPPINESS,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanMultWinningHappinessGet(),null));
        fields_.add(new StandardField(MULT_WINNING_EV,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanMultWinningEvGet(),null));
        fields_.add(new StandardField(MULT_WINNING_EXP,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanMultWinningExpGet(),null));
        fields_.add(new StandardField(MULT_DRAINED_HP,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanMultDrainedHpGet(),null));
        fields_.add(new StandardField(DAMAGE_RECOIL,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanDamageRecoilGet(),null));
        fields_.add(new StandardField(MULT_POWER,BeanNatCommonLgNames.STRING, new ItemForBattleBeanMultPowerGet(),null));
        fields_.add(new StandardField(MULT_DAMAGE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanMultDamageGet(),null));
        fields_.add(new StandardField(MULT_STAT_RANK, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanMultStatRankGet(),null));
        fields_.add(new StandardField(MULT_STAT_POKEMON_RANK, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanMultStatPokemonRankGet(),null));
        fields_.add(new StandardField(BOOST_STATIS_SUPER_EFF, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanBoostStatisSuperEffGet(),null));
        fields_.add(new StandardField(BOOST_STATIS_TYPES, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanBoostStatisTypesGet(),null));
        fields_.add(new StandardField(MULT_STAT, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanMultStatGet(),null));
        fields_.add(new StandardField(FAIL_STATUS, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanFailStatusGet(),null));
        fields_.add(new StandardField(MAP_VARS, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanMapVarsGet(),null));
        fields_.add(new StandardField(TYPES_PK, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanTypesPkGet(),null));
        fields_.add(new StandardField(TYPES_PK_ABILITIES, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanTypesPkAbilitiesGet(),null));
        fields_.add(new StandardField(IMMU_STATUS, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanImmuStatusGet(),null));
        fields_.add(new StandardField(IMMU_TYPES, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanImmuTypesGet(),null));
        fields_.add(new StandardField(SYNCHRO_STATUS, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanSynchroStatusGet(),null));
        fields_.add(new StandardField(WIN_EV_FIGHT, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanWinEvFightGet(),null));
        fields_.add(new StandardField(INCREASING_MAX_NB_ROUND_TRAP, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanIncreasingMaxNbRoundTrapGet(),null));
        fields_.add(new StandardField(INCREASING_MAX_NB_ROUND_GLOBAL_MOVE, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanIncreasingMaxNbRoundGlobalMoveGet(),null));
        fields_.add(new StandardField(INCREASING_MAX_NB_ROUND_TEAM_MOVE, BeanNatCommonLgNames.TYPE_MAP, new ItemForBattleBeanIncreasingMaxNbRoundTeamMoveGet(),null));
        fields_.add(new StandardField(IMMU_MOVES, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanImmuMovesGet(),null));
        fields_.add(new StandardField(IMMU_WEATHER, BeanNatCommonLgNames.TYPE_LIST, new ItemForBattleBeanImmuWeatherGet(),null));
//        methods_.add( new SpecNatMethod(GET_EFFECT_SENDING,PokemonStandards.TYPE_EFFECT_WHILE_SENDING, new ItemForBattleBeanGetEffectSending()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT_RANK,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatRank()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT_PK_RANK,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatPkRank()));
        methods_.add( new SpecNatMethod(CLICK_MULT_STAT_PK,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickMultStatPk()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT_PK,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatPk()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STATIS_SUPER_EFF,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatisSuperEff()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STATIS_TYPES,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatisTypes()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STATIS_TYPES_STAT,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStatisTypesStat()));
        methods_.add( new SpecNatMethod(GET_TR_MULT_STAT,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrMultStat()));
        methods_.add( new SpecNatMethod(CLICK_FAIL_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickFailStatus()));
        methods_.add( new SpecNatMethod(GET_TR_FAIL_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrFailStatus()));
        methods_.add( new SpecNatMethod(CLICK_TYPES_PK_ABILITY,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickTypesPkAbility()));
        methods_.add( new SpecNatMethod(GET_TR_TYPES_PK_ABILITY,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrTypesPkAbility()));
        methods_.add( new SpecNatMethod(GET_TR_TYPES_PK,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrTypesPk()));
        methods_.add( new SpecNatMethod(CLICK_IMMU_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickImmuStatus()));
        methods_.add( new SpecNatMethod(GET_TR_IMMU_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrImmuStatus()));
        methods_.add( new SpecNatMethod(GET_TR_IMMU_TYPES,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrImmuTypes()));
        methods_.add( new SpecNatMethod(CLICK_SYNCHRO_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickSynchroStatus()));
        methods_.add( new SpecNatMethod(GET_TR_SYNCHRO_STATUS,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrSynchroStatus()));
        methods_.add( new SpecNatMethod(GET_TR_WIN_EV_FIGHT,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrWinEvFight()));
        methods_.add( new SpecNatMethod(CLICK_TRAP_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickTrapMove()));
        methods_.add( new SpecNatMethod(GET_TR_TRAP_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrTrapMove()));
        methods_.add( new SpecNatMethod(CLICK_GLOBAL_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickGlobalMove()));
        methods_.add( new SpecNatMethod(GET_TR_GLOBAL_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrGlobalMove()));
        methods_.add( new SpecNatMethod(CLICK_TEAM_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickTeamMove()));
        methods_.add( new SpecNatMethod(GET_TR_TEAM_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrTeamMove()));
        methods_.add( new SpecNatMethod(CLICK_IMMU_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickImmuMove()));
        methods_.add( new SpecNatMethod(GET_TR_IMMU_MOVE,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrImmuMove()));
        methods_.add( new SpecNatMethod(CLICK_WEATHER,BeanNatCommonLgNames.STRING, new ItemForBattleBeanClickWeather()));
        methods_.add( new SpecNatMethod(GET_TR_WEATHER,BeanNatCommonLgNames.STRING, new ItemForBattleBeanGetTrWeather()));
        methods_.add( new SpecNatMethod(RATE_FOR_ATTACK_FIRST,BeanNatCommonLgNames.TYPE_RATE, new ItemForBattleBeanRateForAttackFirst()));
        methods_.add( new SpecNatMethod(DETERMINATED,BeanNatCommonLgNames.PRIM_BOOLEAN, new ItemForBattleBeanDeterminated()));
        _std.getStds().addEntry(TYPE_ITEM_FOR_BATTLE_BEAN, type_);
    }
    private static void buildItemsBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansStd.TYPE_COMMON_BEAN);
//        fields_.add(new StandardField(TYPED_NAME,BeanNatCommonLgNames.STRING, new ItemsBeanTypedNameGet(),new ItemsBeanTypedNameSet()));
//        fields_.add(new StandardField(TYPED_PRICE,BeanNatCommonLgNames.STRING, new ItemsBeanTypedPriceGet(),new ItemsBeanTypedPriceSet()));
//        fields_.add(new StandardField(TYPED_CLASS,BeanNatCommonLgNames.STRING, new ItemsBeanTypedClassGet(),new ItemsBeanTypedClassSet()));
        fields_.add(new StandardField(ITEMS, BeanNatCommonLgNames.TYPE_LIST, new ItemsBeanItemsGet(),null));
//        methods_.add( new SpecNatMethod(SEARCH,BeanNatCommonLgNames.STRING, new ItemsBeanSearch()));
        methods_.add( new SpecNatMethod(GET_MINI_IMAGE,BeanNatCommonLgNames.STRING, new ItemsBeanGetMiniImage()));
        methods_.add( new SpecNatMethod(CLICK_LINK,BeanNatCommonLgNames.STRING, new ItemsBeanClickLink()));
        _std.getStds().addEntry(TYPE_ITEMS_BEAN, type_);
    }
    private static void buildRepelBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        fields_.add(new StandardField(STEPS,BeanNatCommonLgNames.PRIM_LONG, new RepelBeanStepsGet(),null));
        _std.getStds().addEntry(TYPE_REPEL_BEAN, type_);
    }
    private static void buildSellingItemBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, AikiBeansItemsStd.TYPE_ITEM_BEAN);
        _std.getStds().addEntry(TYPE_SELLING_ITEM_BEAN, type_);
    }
}
