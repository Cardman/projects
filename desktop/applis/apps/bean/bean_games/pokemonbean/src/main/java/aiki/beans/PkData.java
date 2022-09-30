package aiki.beans;

import aiki.beans.abilities.*;
import aiki.beans.effects.*;
import aiki.beans.endround.*;
import aiki.beans.facade.dto.*;
import aiki.beans.facade.map.dto.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.*;
import aiki.beans.help.*;
import aiki.beans.items.*;
import aiki.beans.items.effects.*;
import aiki.beans.map.*;
import aiki.beans.map.characters.*;
import aiki.beans.map.elements.*;
import aiki.beans.map.pokemon.*;
import aiki.beans.moves.*;
import aiki.beans.moves.effects.*;
import aiki.beans.pokemon.*;
import aiki.beans.pokemon.evolutions.*;
import aiki.beans.simulation.*;
import aiki.beans.solution.*;
import aiki.beans.status.*;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.formathtml.Configuration;
import code.util.CustList;

public final class PkData extends PokemonStandards {
    private static final String GET_MOVE = "getMove";
    private static final String GET_BOOST = "getBoost";
    private static final String GET_EFF = "getEff";
    private static final String GET_HP_RATE = "getHpRate";
    private static final String GET_LEVEL = "getLevel";
    private static final String GET_ITEM = "getItem";
    private static final String GET_MOVES = "getMoves";
    private static final String GET_AVG_NB_STEPS = "getAvgNbSteps";
    private static final String GET_WILD_POKEMON = "getWildPokemon";
    private static final String GET_WILD_POKEMON_FISHING = "getWildPokemonFishing";
    private static final String GET_NAME = "getName";
    private static final String GET_DAMAGE_TYPE = "getDamageType";
    private static final String GET_POKEMON_TYPE = "getPokemonType";
    private static final String GET_CATEGORY = "getCategory";
    private static final String GET_MULT = "getMult";
    private static final String GET_WON_EXP_SINCE_LAST_LEVEL = "getWonExpSinceLastLevel";
    private static final String GET_HAPPINESS = "getHappiness";
    private static final String GET_RESTORED_HP_RATE_LOVED_ALLY = "getRestoredHpRateLovedAlly";
    private static final String GET_WEDDING_ALLY = "getWeddingAlly";
    private static final String GET_MULT_DAMAGE_AGAINST_FOE = "getMultDamageAgainstFoe";
    @Override
    public void buildAddon() {
        AikiBeansAbilitiesStd.build(this);
        AikiBeansStd.build(this);
        AikiBeansEffectsStd.build(this);
        AikiBeansEndroundStd.build(this);
        AikiBeansFacadeDtoStd.build(this);
        AikiBeansFacadeMapDtoStd.build(this);
        AikiBeansFacadeSimulationDtoStd.build(this);
        AikiBeansFacadeSolutionDtoStd.build(this);
        AikiBeansHelpStd.build(this);
        AikiBeansItemsStd.build(this);
        AikiBeansItemsEffectsStd.build(this);
        AikiBeansMapCharactersStd.build(this);
        AikiBeansMapElementsStd.build(this);
        AikiBeansMapStd.build(this);
        AikiBeansMapPokemonStd.build(this);
        AikiBeansMovesEffectsStd.build(this);
        AikiBeansMovesStd.build(this);
        AikiBeansPokemonEvolutionsStd.build(this);
        AikiBeansPokemonStd.build(this);
        AikiBeansSimulationStd.build(this);
        AikiBeansSolutionStd.build(this);
        AikiBeansStatusStd.build(this);
        buildTypeDamageBoost(this);
        buildEfficiencyRate(this);
        buildBoostHpRate(this);
        buildPkTrainer(this);
        buildAreaApparition(this);
        buildWildPk(this);
        buildPlace(this);
        buildTypesDuo(this);
        buildCategoryMult(this);
        buildLevelMove(this);
        buildPokemonPlayer(this);
        buildEffectPartnerStatus(this);
    }

    private static void buildTypeDamageBoost(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TYPE_DAMAGE_BOOST, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_BOOST,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new TypeDamageBoostGetBoost()));
        _std.getStds().addEntry(TYPE_TYPE_DAMAGE_BOOST, type_);
    }
    private static void buildEfficiencyRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFICIENCY_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_EFF,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EfficiencyRateGetEff()));
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EfficiencyRateGetHpRate()));
        _std.getStds().addEntry(TYPE_EFFICIENCY_RATE, type_);
    }
    private static void buildBoostHpRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_BOOST_HP_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new BoostHpRateGetHpRate()));
        methods_.add( new SpecNatMethod(GET_BOOST, PRIM_INTEGER, false, MethodModifier.NORMAL,new BoostHpRateGetBoost()));
        _std.getStds().addEntry(TYPE_BOOST_HP_RATE, type_);
    }
    private static void buildPkTrainer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_PK_TRAINER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new PkTrainerGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PkTrainerGetItem()));
        methods_.add( new SpecNatMethod(GET_MOVES, TYPE_LIST, false, MethodModifier.NORMAL,new PkTrainerGetMoves()));
        _std.getStds().addEntry(TYPE_PK_TRAINER, type_);
    }

    private static void buildAreaApparition(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_AREA_APPARITION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_AVG_NB_STEPS, PRIM_INTEGER, false, MethodModifier.NORMAL,new AreaApparitionGetAvgNbSteps()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON, TYPE_LIST, false, MethodModifier.NORMAL,new AreaApparitionGetWildPokemon()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON_FISHING, TYPE_LIST, false, MethodModifier.NORMAL,new AreaApparitionGetWildPokemonFishing()));
        _std.getStds().addEntry(TYPE_AREA_APPARITION, type_);
    }
    private static void buildWildPk(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_WILD_PK, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new WildPkGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new WildPkGetItem()));
        _std.getStds().addEntry(TYPE_WILD_PK, type_);
    }
    private static void buildPlace(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_PLACE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PlaceGetName()));
        _std.getStds().addEntry(TYPE_PLACE, type_);
    }
    private static void buildTypesDuo(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_TYPES_DUO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_DAMAGE_TYPE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TypesDuoGetDamageType()));
        methods_.add( new SpecNatMethod(GET_POKEMON_TYPE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new TypesDuoGetPokemonType()));
        _std.getStds().addEntry(TYPE_TYPES_DUO, type_);
    }
    private static void buildCategoryMult(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_CATEGORY_MULT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_CATEGORY,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new CategoryMultGetCategory()));
        methods_.add( new SpecNatMethod(GET_MULT, PRIM_INTEGER, false, MethodModifier.NORMAL,new CategoryMultGetMult()));
        _std.getStds().addEntry(TYPE_CATEGORY_MULT, type_);
    }
    private static void buildLevelMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_LEVEL_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, false, MethodModifier.NORMAL,new LevelMoveGetLevel()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new LevelMoveGetMove()));
        _std.getStds().addEntry(TYPE_LEVEL_MOVE, type_);
    }
    private static void buildPokemonPlayer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new PokemonPlayerGetWonExpSinceLastLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonPlayerGetItem()));
        methods_.add( new SpecNatMethod(GET_HAPPINESS, PRIM_INTEGER, false, MethodModifier.NORMAL,new PokemonPlayerGetHappiness()));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER, type_);
    }
    private static void buildEffectPartnerStatus(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_EFFECT_PARTNER_STATUS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_RESTORED_HP_RATE_LOVED_ALLY,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EffectPartnerStatusGetRestoredHpRateLovedAlly()));
        methods_.add( new SpecNatMethod(GET_WEDDING_ALLY,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL,new EffectPartnerStatusGetWeddingAlly()));
        methods_.add( new SpecNatMethod(GET_MULT_DAMAGE_AGAINST_FOE,BeanNatCommonLgNames.TYPE_RATE, false, MethodModifier.NORMAL,new EffectPartnerStatusGetMultDamageAgainstFoe()));
        _std.getStds().addEntry(TYPE_EFFECT_PARTNER_STATUS, type_);
    }

    @Override
    public void initBeans(Configuration _conf, String _language) {
        getBeansStruct().setValue(0,bean(new WelcomeBean(), AikiBeansStd.TYPE_WELCOME_BEAN, _language));
        getBeansStruct().setValue(1,bean(new PokedexBean(), AikiBeansPokemonStd.TYPE_POKEDEX_BEAN, _language));
        getBeansStruct().setValue(2,bean(new PokemonBean(), AikiBeansPokemonStd.TYPE_POKEMON_BEAN, _language));
        getBeansStruct().setValue(3,bean(new EvolutionHappinessBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_HAPPINESS_BEAN, _language));
        getBeansStruct().setValue(4,bean(new EvolutionLevelBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_BEAN, _language));
        getBeansStruct().setValue(5,bean(new EvolutionLevelGenderBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_GENDER_BEAN, _language));
        getBeansStruct().setValue(6,bean(new EvolutionMoveBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_BEAN, _language));
        getBeansStruct().setValue(7,bean(new EvolutionItemBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_ITEM_BEAN, _language));
        getBeansStruct().setValue(8,bean(new EvolutionStoneBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_BEAN, _language));
        getBeansStruct().setValue(9,bean(new EvolutionStoneGenderBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_GENDER_BEAN, _language));
        getBeansStruct().setValue(10,bean(new EvolutionTeamBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_TEAM_BEAN, _language));
        getBeansStruct().setValue(11,bean(new EvolutionMoveTypeBean(), AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_TYPE_BEAN, _language));
        getBeansStruct().setValue(12,bean(new MovesBean(), AikiBeansMovesStd.TYPE_MOVES_BEAN, _language));
        getBeansStruct().setValue(13,bean(new MoveLineBean(), AikiBeansMovesStd.TYPE_MOVE_LINE_BEAN, _language));
        getBeansStruct().setValue(14,bean(new MoveBean(), AikiBeansMovesStd.TYPE_MOVE_BEAN, _language));
        getBeansStruct().setValue(15,bean(new EffectBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, _language));
        getBeansStruct().setValue(16,bean(new EffectDamageBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_BEAN, _language));
        getBeansStruct().setValue(17,bean(new EffectDamageRateBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_RATE_BEAN, _language));
        getBeansStruct().setValue(18,bean(new EffectStatisticBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_STATISTIC_BEAN, _language));
        getBeansStruct().setValue(19,bean(new EffectStatusBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_STATUS_BEAN, _language));
        getBeansStruct().setValue(20,bean(new EffectTeamBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_BEAN, _language));
        getBeansStruct().setValue(21,bean(new EffectGlobalBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_GLOBAL_BEAN, _language));
        getBeansStruct().setValue(22,bean(new EffectEndRoundMoveBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_END_ROUND_MOVE_BEAN, _language));
        getBeansStruct().setValue(23,bean(new EffectTeamWhileSendFoeBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN, _language));
        getBeansStruct().setValue(24,bean(new EffectCopyMoveBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_MOVE_BEAN, _language));
        getBeansStruct().setValue(25,bean(new EffectFullHpRateBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_FULL_HP_RATE_BEAN, _language));
        getBeansStruct().setValue(26,bean(new EffectInvokeBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_INVOKE_BEAN, _language));
        getBeansStruct().setValue(27,bean(new EffectSwitchMoveTypesBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN, _language));
        getBeansStruct().setValue(28,bean(new EffectCounterAttackBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_COUNTER_ATTACK_BEAN, _language));
        getBeansStruct().setValue(29,bean(new EffectProtectionBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECTION_BEAN, _language));
        getBeansStruct().setValue(30,bean(new EffectBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, _language));
        getBeansStruct().setValue(31,bean(new EffectCopyFighterBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_FIGHTER_BEAN, _language));
        getBeansStruct().setValue(32,bean(new EffectProtectFromTypesBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN, _language));
        getBeansStruct().setValue(33,bean(new EffectUnprotectFromTypesBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN, _language));
        getBeansStruct().setValue(34,bean(new EffectAllyBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_ALLY_BEAN, _language));
        getBeansStruct().setValue(35,bean(new EffectBatonPassBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_BATON_PASS_BEAN, _language));
        getBeansStruct().setValue(36,bean(new EffectCloneBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_CLONE_BEAN, _language));
        getBeansStruct().setValue(37,bean(new EffectCommonStatisticsBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_COMMON_STATISTICS_BEAN, _language));
        getBeansStruct().setValue(38,bean(new EffectOrderBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_ORDER_BEAN, _language));
        getBeansStruct().setValue(39,bean(new EffectRestrictionBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_RESTRICTION_BEAN, _language));
        getBeansStruct().setValue(40,bean(new EffectSwitchAbilitiesBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ABILITIES_BEAN, _language));
        getBeansStruct().setValue(41,bean(new EffectSwitchItemsBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ITEMS_BEAN, _language));
        getBeansStruct().setValue(42,bean(new EffectSwitchTypesBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_TYPES_BEAN, _language));
        getBeansStruct().setValue(43,bean(new EffectSwitchPointViewBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN, _language));
        getBeansStruct().setValue(44,bean(new EffectRemainedHpRateBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_REMAINED_HP_RATE_BEAN, _language));
        getBeansStruct().setValue(45,bean(new EffectMultUsedMovePowerBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN, _language));
        getBeansStruct().setValue(46,bean(new EffectMultSufferedMovePowerBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN, _language));
        getBeansStruct().setValue(47,bean(new EffectBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN, _language));
        getBeansStruct().setValue(48,bean(new EffectVarPPBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_VAR_P_P_BEAN, _language));
        getBeansStruct().setValue(49,bean(new EffectWinMoneyBean(), AikiBeansMovesEffectsStd.TYPE_EFFECT_WIN_MONEY_BEAN, _language));
        getBeansStruct().setValue(50,bean(new EffectWhileSendingBean(), AikiBeansEffectsStd.TYPE_EFFECT_WHILE_SENDING_BEAN, _language));
        getBeansStruct().setValue(51,bean(new ItemsBean(), AikiBeansItemsStd.TYPE_ITEMS_BEAN, _language));
        getBeansStruct().setValue(52,bean(new SellingItemBean(), AikiBeansItemsStd.TYPE_ITEM_BEAN, _language));
        getBeansStruct().setValue(53,bean(new BallBean(), AikiBeansItemsStd.TYPE_BALL_BEAN, _language));
        getBeansStruct().setValue(54,bean(new BerryBean(), AikiBeansItemsStd.TYPE_BERRY_BEAN, _language));
        getBeansStruct().setValue(55,bean(new BoostBean(), AikiBeansItemsStd.TYPE_BOOST_BEAN, _language));
        getBeansStruct().setValue(56,bean(new EvolvingItemBean(), AikiBeansItemsStd.TYPE_EVOLVING_ITEM_BEAN, _language));
        getBeansStruct().setValue(57,bean(new EvolvingStoneBean(), AikiBeansItemsStd.TYPE_EVOLVING_STONE_BEAN, _language));
        getBeansStruct().setValue(58,bean(new FossilBean(), AikiBeansItemsStd.TYPE_FOSSIL_BEAN, _language));
        getBeansStruct().setValue(59,bean(new HealingHpBean(), AikiBeansItemsStd.TYPE_HEALING_HP_BEAN, _language));
        getBeansStruct().setValue(60,bean(new HealingStatusBean(), AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN, _language));
        getBeansStruct().setValue(61,bean(new HealingItemBean(), AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN, _language));
        getBeansStruct().setValue(62,bean(new HealingPpBean(), AikiBeansItemsStd.TYPE_HEALING_PP_BEAN, _language));
        getBeansStruct().setValue(63,bean(new HealingStatusBean(), AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN, _language));
        getBeansStruct().setValue(64,bean(new ItemForBattleBean(), AikiBeansItemsStd.TYPE_ITEM_FOR_BATTLE_BEAN, _language));
        getBeansStruct().setValue(65,bean(new RepelBean(), AikiBeansItemsStd.TYPE_REPEL_BEAN, _language));
        getBeansStruct().setValue(66,bean(new SellingItemBean(), AikiBeansItemsStd.TYPE_SELLING_ITEM_BEAN, _language));
        getBeansStruct().setValue(67,bean(new AbilityBean(), AikiBeansAbilitiesStd.TYPE_ABILITY_BEAN, _language));
        getBeansStruct().setValue(68,bean(new AbilitiesBean(), AikiBeansAbilitiesStd.TYPE_ABILITIES_BEAN, _language));
        getBeansStruct().setValue(69,bean(new StatusBean(), AikiBeansStatusStd.TYPE_STATUS_BEAN, _language));
        getBeansStruct().setValue(70,bean(new StatusSetBean(), AikiBeansStatusStd.TYPE_STATUS_SET_BEAN, _language));
        getBeansStruct().setValue(71,bean(new EndRoundBean(), AikiBeansEndroundStd.TYPE_END_ROUND_BEAN, _language));
        getBeansStruct().setValue(72,bean(new EffectEndRoundBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN, _language));
        getBeansStruct().setValue(73,bean(new EffectEndRoundIndividualBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN, _language));
        getBeansStruct().setValue(74,bean(new EffectEndRoundStatusBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN, _language));
        getBeansStruct().setValue(75,bean(new EffectEndRoundStatusRelationBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN, _language));
        getBeansStruct().setValue(76,bean(new EffectEndRoundSingleRelationBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN, _language));
        getBeansStruct().setValue(77,bean(new EffectEndRoundGlobalBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_GLOBAL_BEAN, _language));
        getBeansStruct().setValue(78,bean(new EffectEndRoundFoeBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_FOE_BEAN, _language));
        getBeansStruct().setValue(79,bean(new EffectEndRoundTeamBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_TEAM_BEAN, _language));
        getBeansStruct().setValue(80,bean(new EffectEndRoundMultiRelationBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN, _language));
        getBeansStruct().setValue(81,bean(new EffectEndRoundPositionRelationBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN, _language));
        getBeansStruct().setValue(82,bean(new EffectEndRoundPositionTargetBean(), AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN, _language));
        getBeansStruct().setValue(83,bean(new EffectComboBean(), AikiBeansEffectsStd.TYPE_EFFECT_COMBO_BEAN, _language));
        getBeansStruct().setValue(84,bean(new CombosBean(), AikiBeansEffectsStd.TYPE_COMBOS_BEAN, _language));
        getBeansStruct().setValue(85,bean(new FightHelpBean(), AikiBeansHelpStd.TYPE_FIGHT_HELP_BEAN, _language));
        getBeansStruct().setValue(86,bean(new MapBean(), AikiBeansMapStd.TYPE_MAP_BEAN, _language));
        getBeansStruct().setValue(87,bean(new MapLevelBean(), AikiBeansMapStd.TYPE_MAP_LEVEL_BEAN, _language));
        getBeansStruct().setValue(88,bean(new PokemonTeamBean(), AikiBeansMapPokemonStd.TYPE_POKEMON_TEAM_BEAN, _language));
        getBeansStruct().setValue(89,bean(new TrainerBean(), AikiBeansMapCharactersStd.TYPE_TRAINER_BEAN, _language));
        getBeansStruct().setValue(90,bean(new AllyBean(), AikiBeansMapCharactersStd.TYPE_ALLY_BEAN, _language));
        getBeansStruct().setValue(91,bean(new DualFightBean(), AikiBeansMapCharactersStd.TYPE_DUAL_FIGHT_BEAN, _language));
        getBeansStruct().setValue(92,bean(new AreaBean(), AikiBeansMapElementsStd.TYPE_AREA_BEAN, _language));
        getBeansStruct().setValue(93,bean(new LegendaryPokemonBean(), AikiBeansMapElementsStd.TYPE_LEGENDARY_POKEMON_BEAN, _language));
        getBeansStruct().setValue(94,bean(new SellerBean(), AikiBeansMapCharactersStd.TYPE_SELLER_BEAN, _language));
        getBeansStruct().setValue(95,bean(new DealerBean(), AikiBeansMapCharactersStd.TYPE_DEALER_BEAN, _language));
        getBeansStruct().setValue(96,bean(new SolutionBean(), AikiBeansSolutionStd.TYPE_SOLUTION_BEAN, _language));
        getBeansStruct().setValue(97,bean(new SimulationBean(), AikiBeansSimulationStd.TYPE_SIMULATION_BEAN, _language));
        getBeansStruct().setValue(98,bean(new SimulationLevelBean(), AikiBeansSimulationStd.TYPE_SIMULATION_LEVEL_BEAN, _language));
        getBeansStruct().setValue(99,bean(new AddPokemonBean(), AikiBeansSimulationStd.TYPE_ADD_POKEMON_BEAN, _language));
        getBeansStruct().setValue(100,bean(new EditPokemonBean(), AikiBeansSimulationStd.TYPE_EDIT_POKEMON_BEAN, _language));
        getBeansStruct().setValue(101,bean(new EditTrainerPokemonBean(), AikiBeansSimulationStd.TYPE_EDIT_TRAINER_POKEMON_BEAN, _language));
        getBeansStruct().setValue(102,bean(new EditPokemonMovesBean(), AikiBeansSimulationStd.TYPE_EDIT_POKEMON_MOVES_BEAN, _language));
        getBeansStruct().setValue(103,bean(new SelectAbilityBean(), AikiBeansSimulationStd.TYPE_SELECT_ABILITY_BEAN, _language));
        getBeansStruct().setValue(104,bean(new SelectItemBean(), AikiBeansSimulationStd.TYPE_SELECT_ITEM_BEAN, _language));
        getBeansStruct().setValue(105,bean(new SelectPokemonBean(), AikiBeansSimulationStd.TYPE_SELECT_POKEMON_BEAN, _language));
        getBeansStruct().setValue(106,bean(new GeneralHelpBean(), AikiBeansHelpStd.TYPE_GENERAL_HELP_BEAN, _language));
        getBeansStruct().setValue(107,bean(new LangsBean(), AikiBeansHelpStd.TYPE_LANGS_BEAN, _language));
    }
}
