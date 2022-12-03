package aiki.beans;

import aiki.beans.abilities.*;
import aiki.beans.effects.*;
import aiki.beans.endround.*;
import aiki.beans.facade.dto.*;
import aiki.beans.facade.map.dto.*;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.solution.dto.*;
import aiki.beans.game.AikiBeansGameStd;
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
import code.bean.nat.analyze.NatConfigurationCore;
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
        AikiBeansGameStd.buildDifficultyCommonBean(this);
    }

    private static void buildTypeDamageBoost(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_BOOST,BeanNatCommonLgNames.TYPE_RATE, new TypeDamageBoostGetBoost()));
        _std.getStds().addEntry(TYPE_TYPE_DAMAGE_BOOST, type_);
    }
    private static void buildEfficiencyRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_EFF,BeanNatCommonLgNames.TYPE_RATE, new EfficiencyRateGetEff()));
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, new EfficiencyRateGetHpRate()));
        _std.getStds().addEntry(TYPE_EFFICIENCY_RATE, type_);
    }
    private static void buildBoostHpRate(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_HP_RATE,BeanNatCommonLgNames.TYPE_RATE, new BoostHpRateGetHpRate()));
        methods_.add( new SpecNatMethod(GET_BOOST, PRIM_INTEGER, new BoostHpRateGetBoost()));
        _std.getStds().addEntry(TYPE_BOOST_HP_RATE, type_);
    }
    private static void buildPkTrainer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, new PkTrainerGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, new PkTrainerGetItem()));
        methods_.add( new SpecNatMethod(GET_MOVES, TYPE_LIST, new PkTrainerGetMoves()));
        _std.getStds().addEntry(TYPE_PK_TRAINER, type_);
    }

    private static void buildAreaApparition(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_AVG_NB_STEPS, PRIM_INTEGER, new AreaApparitionGetAvgNbSteps()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON, TYPE_LIST, new AreaApparitionGetWildPokemon()));
        methods_.add( new SpecNatMethod(GET_WILD_POKEMON_FISHING, TYPE_LIST, new AreaApparitionGetWildPokemonFishing()));
        _std.getStds().addEntry(TYPE_AREA_APPARITION, type_);
    }
    private static void buildWildPk(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, new WildPkGetLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, new WildPkGetItem()));
        _std.getStds().addEntry(TYPE_WILD_PK, type_);
    }
    private static void buildPlace(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_NAME,BeanNatCommonLgNames.STRING, new PlaceGetName()));
        _std.getStds().addEntry(TYPE_PLACE, type_);
    }
    private static void buildTypesDuo(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_DAMAGE_TYPE,BeanNatCommonLgNames.STRING, new TypesDuoGetDamageType()));
        methods_.add( new SpecNatMethod(GET_POKEMON_TYPE,BeanNatCommonLgNames.STRING, new TypesDuoGetPokemonType()));
        _std.getStds().addEntry(TYPE_TYPES_DUO, type_);
    }
    private static void buildCategoryMult(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_CATEGORY,BeanNatCommonLgNames.STRING, new CategoryMultGetCategory()));
        methods_.add( new SpecNatMethod(GET_MULT, PRIM_INTEGER, new CategoryMultGetMult()));
        _std.getStds().addEntry(TYPE_CATEGORY_MULT, type_);
    }
    private static void buildLevelMove(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_LEVEL, PRIM_INTEGER, new LevelMoveGetLevel()));
        methods_.add( new SpecNatMethod(GET_MOVE,BeanNatCommonLgNames.STRING, new LevelMoveGetMove()));
        _std.getStds().addEntry(TYPE_LEVEL_MOVE, type_);
    }
    private static void buildPokemonPlayer(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, PokemonStandards.TYPE_POKEMON);
        methods_.add( new SpecNatMethod(GET_WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE, new PokemonPlayerGetWonExpSinceLastLevel()));
        methods_.add( new SpecNatMethod(GET_ITEM,BeanNatCommonLgNames.STRING, new PokemonPlayerGetItem()));
        methods_.add( new SpecNatMethod(GET_HAPPINESS, PRIM_INTEGER, new PokemonPlayerGetHappiness()));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER, type_);
    }
    private static void buildEffectPartnerStatus(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.OBJECT);
        methods_.add( new SpecNatMethod(GET_RESTORED_HP_RATE_LOVED_ALLY,BeanNatCommonLgNames.TYPE_RATE, new EffectPartnerStatusGetRestoredHpRateLovedAlly()));
        methods_.add( new SpecNatMethod(GET_WEDDING_ALLY,BeanNatCommonLgNames.PRIM_BOOLEAN, new EffectPartnerStatusGetWeddingAlly()));
        methods_.add( new SpecNatMethod(GET_MULT_DAMAGE_AGAINST_FOE,BeanNatCommonLgNames.TYPE_RATE, new EffectPartnerStatusGetMultDamageAgainstFoe()));
        _std.getStds().addEntry(TYPE_EFFECT_PARTNER_STATUS, type_);
    }

    @Override
    public void initBeans(NatConfigurationCore _conf, String _language) {
        getBeansStruct().setValue(0, beanWelcomeBean(_language));
        getBeansStruct().setValue(1, beanPokedexBean(_language));
        getBeansStruct().setValue(2, beanPokemonBean(_language));
        getBeansStruct().setValue(3, beanEvolutionHappinessBean(_language));
        getBeansStruct().setValue(4, beanEvolutionLevelBean(_language));
        getBeansStruct().setValue(5, beanEvolutionLevelGenderBean(_language));
        getBeansStruct().setValue(6, beanEvolutionMoveBean(_language));
        getBeansStruct().setValue(7, beanEvolutionItemBean(_language));
        getBeansStruct().setValue(8, beanEvolutionStoneBean(_language));
        getBeansStruct().setValue(9, beanEvolutionStoneGenderBean(_language));
        getBeansStruct().setValue(10, beanEvolutionTeamBean(_language));
        getBeansStruct().setValue(11, beanEvolutionMoveTypeBean(_language));
        getBeansStruct().setValue(12, beanMovesBean(_language));
        getBeansStruct().setValue(13, beanMovesBean(_language));
        getBeansStruct().setValue(14, beanMoveBean(_language));
        getBeansStruct().setValue(15, beanEffectBean(_language));
        getBeansStruct().setValue(16, beanEffectDamageBean(_language));
        getBeansStruct().setValue(17, beanEffectDamageRateBean(_language));
        getBeansStruct().setValue(18, beanEffectStatisticBean(_language));
        getBeansStruct().setValue(19, beanEffectStatusBean(_language));
        getBeansStruct().setValue(20, beanEffectTeamBean(_language));
        getBeansStruct().setValue(21, beanEffectGlobalBean(_language));
        getBeansStruct().setValue(22, beanEffectEndRoundMoveBean(_language));
        getBeansStruct().setValue(23, beanEffectTeamWhileSendFoeBean(_language));
        getBeansStruct().setValue(24, beanEffectCopyMoveBean(_language));
        getBeansStruct().setValue(25, beanEffectFullHpRateBean(_language));
        getBeansStruct().setValue(26, beanEffectInvokeBean(_language));
        getBeansStruct().setValue(27, beanEffectSwitchMoveTypesBean(_language));
        getBeansStruct().setValue(28, beanEffectCounterAttackBean(_language));
        getBeansStruct().setValue(29, beanEffectProtectionBean(_language));
        getBeansStruct().setValue(30, beanEffectBean(_language));
        getBeansStruct().setValue(31, beanEffectCopyFighterBean(_language));
        getBeansStruct().setValue(32, beanEffectProtectFromTypesBean(_language));
        getBeansStruct().setValue(33, beanEffectUnprotectFromTypesBean(_language));
        getBeansStruct().setValue(34, beanEffectAllyBean(_language));
        getBeansStruct().setValue(35, beanEffectBatonPassBean(_language));
        getBeansStruct().setValue(36, beanEffectCloneBean(_language));
        getBeansStruct().setValue(37, beanEffectCommonStatisticsBean(_language));
        getBeansStruct().setValue(38, beanEffectOrderBean(_language));
        getBeansStruct().setValue(39, beanEffectRestrictionBean(_language));
        getBeansStruct().setValue(40, beanEffectSwitchAbilitiesBean(_language));
        getBeansStruct().setValue(41, beanEffectSwitchItemsBean(_language));
        getBeansStruct().setValue(42, beanEffectSwitchTypesBean(_language));
        getBeansStruct().setValue(43, beanEffectSwitchPointViewBean(_language));
        getBeansStruct().setValue(44, beanEffectRemainedHpRateBean(_language));
        getBeansStruct().setValue(45, beanEffectMultMovePowerBean(_language));
        getBeansStruct().setValue(46, beanEffectMultMovePowerBean(_language));
        getBeansStruct().setValue(47, beanEffectBean(_language));
        getBeansStruct().setValue(48, beanEffectVarPPBean(_language));
        getBeansStruct().setValue(49, beanEffectWinMoneyBean(_language));
        getBeansStruct().setValue(50, beanEffectWhileSendingBean(_language));
        getBeansStruct().setValue(51, beanItemsBean(_language));
        getBeansStruct().setValue(52, beanSellingItemBean(_language));
        getBeansStruct().setValue(53, beanBallBean(_language));
        getBeansStruct().setValue(54, beanBerryBean(_language));
        getBeansStruct().setValue(55, beanBoostBean(_language));
        getBeansStruct().setValue(56, beanEvolvingItemBean(_language));
        getBeansStruct().setValue(57, beanEvolvingStoneBean(_language));
        getBeansStruct().setValue(58, beanFossilBean(_language));
        getBeansStruct().setValue(59, beanHealingHpBean(_language));
        getBeansStruct().setValue(60, beanHealingStatusBean(_language));
        getBeansStruct().setValue(61, beanHealingItemBean(_language));
        getBeansStruct().setValue(62, beanHealingPpBean(_language));
        getBeansStruct().setValue(63, beanHealingStatusBean(_language));
        getBeansStruct().setValue(64, beanItemForBattleBean(_language));
        getBeansStruct().setValue(65, beanRepelBean(_language));
        getBeansStruct().setValue(66, beanSellingItemBean(_language));
        getBeansStruct().setValue(67, beanAbilityBean(_language));
        getBeansStruct().setValue(68, beanAbilitiesBean(_language));
        getBeansStruct().setValue(69, beanStatusBean(_language));
        getBeansStruct().setValue(70, beanStatusSetBean(_language));
        getBeansStruct().setValue(71, beanEndRoundBean(_language));
        getBeansStruct().setValue(72, beanEffectEndRoundBean(_language));
        getBeansStruct().setValue(73, beanEffectEndRoundIndividualBean(_language));
        getBeansStruct().setValue(74, beanEffectEndRoundSingleStatusBean(_language));
        getBeansStruct().setValue(75, beanEffectEndRoundStatusRelationBean(_language));
        getBeansStruct().setValue(76, beanEffectEndRoundSingleRelationBean(_language));
        getBeansStruct().setValue(77, beanEffectEndRoundGlobalBean(_language));
        getBeansStruct().setValue(78, beanEffectEndRoundFoeBean(_language));
        getBeansStruct().setValue(79, beanEffectEndRoundTeamBean(_language));
        getBeansStruct().setValue(80, beanEffectEndRoundMultiRelationBean(_language));
        getBeansStruct().setValue(81, beanEffectEndRoundPositionRelationBean(_language));
        getBeansStruct().setValue(82, beanEffectEndRoundPositionTargetBean(_language));
        getBeansStruct().setValue(83, beanEffectComboBean(_language));
        getBeansStruct().setValue(84, beanCombosBean(_language));
        getBeansStruct().setValue(85, beanFightHelpBean(_language));
        getBeansStruct().setValue(86, beanMapBean(_language));
        getBeansStruct().setValue(87, beanMapLevelBean(_language));
        getBeansStruct().setValue(88, beanPokemonTeamBean(_language));
        getBeansStruct().setValue(89, beanTrainerBean(_language));
        getBeansStruct().setValue(90, beanAllyBean(_language));
        getBeansStruct().setValue(91, beanDualFightBean(_language));
        getBeansStruct().setValue(92, beanAreaBean(_language));
        getBeansStruct().setValue(93, beanLegendaryPokemonBean(_language));
        getBeansStruct().setValue(94, beanSellerBean(_language));
        getBeansStruct().setValue(95, beanDealerBean(_language));
        getBeansStruct().setValue(96, beanSolutionBean(_language));
        getBeansStruct().setValue(97, beanSimulationBean(_language));
        getBeansStruct().setValue(98, beanSimulationLevelBean(_language));
        getBeansStruct().setValue(99, beanAddPokemonBean(_language));
        getBeansStruct().setValue(100, beanEditPokemonBean(_language));
        getBeansStruct().setValue(101, beanEditTrainerPokemonBean(_language));
        getBeansStruct().setValue(102, beanEditPokemonMovesBean(_language));
        getBeansStruct().setValue(103, beanSelectAbilityBean(_language));
        getBeansStruct().setValue(104, beanSelectItemBean(_language));
        getBeansStruct().setValue(105, beanSelectPokemonBean(_language));
        getBeansStruct().setValue(106, beanGeneralHelpBean(_language));
        getBeansStruct().setValue(107, beanLangsBean(_language));
        getBeansStruct().setValue(108, beanDiffCommon(_language));
    }

    public PokemonBeanStruct beanWelcomeBean(String _language) {
        return bean(new WelcomeBean(), _language);
    }

    public PokemonBeanStruct beanPokedexBean(String _language) {
        return bean(new PokedexBean(), _language);
    }

    public PokemonBeanStruct beanPokemonBean(String _language) {
        return bean(new PokemonBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionHappinessBean(String _language) {
        return bean(new EvolutionHappinessBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionLevelBean(String _language) {
        return bean(new EvolutionLevelBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionLevelGenderBean(String _language) {
        return bean(new EvolutionLevelGenderBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionMoveBean(String _language) {
        return bean(new EvolutionMoveBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionItemBean(String _language) {
        return bean(new EvolutionItemBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionStoneBean(String _language) {
        return bean(new EvolutionStoneBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionStoneGenderBean(String _language) {
        return bean(new EvolutionStoneGenderBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionTeamBean(String _language) {
        return bean(new EvolutionTeamBean(), _language);
    }

    public PokemonBeanStruct beanEvolutionMoveTypeBean(String _language) {
        return bean(new EvolutionMoveTypeBean(), _language);
    }

    public PokemonBeanStruct beanMovesBean(String _language) {
        return bean(new MovesBean(), _language);
    }

//    public PokemonBeanStruct beanMoveLineBean(String _language) {
//        return bean(new MoveLineBean(), _language);
//    }

    public PokemonBeanStruct beanMoveBean(String _language) {
        return bean(new MoveBean(), _language);
    }

    public PokemonBeanStruct beanEffectDamageBean(String _language) {
        return bean(new EffectDamageBean(), _language);
    }

    public PokemonBeanStruct beanEffectDamageRateBean(String _language) {
        return bean(new EffectDamageRateBean(), _language);
    }

    public PokemonBeanStruct beanEffectStatisticBean(String _language) {
        return bean(new EffectStatisticBean(), _language);
    }

    public PokemonBeanStruct beanEffectStatusBean(String _language) {
        return bean(new EffectStatusBean(), _language);
    }

    public PokemonBeanStruct beanEffectTeamBean(String _language) {
        return bean(new EffectTeamBean(), _language);
    }

    public PokemonBeanStruct beanEffectGlobalBean(String _language) {
        return bean(new EffectGlobalBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundMoveBean(String _language) {
        return bean(new EffectEndRoundMoveBean(), _language);
    }

    public PokemonBeanStruct beanEffectTeamWhileSendFoeBean(String _language) {
        return bean(new EffectTeamWhileSendFoeBean(), _language);
    }

    public PokemonBeanStruct beanEffectCopyMoveBean(String _language) {
        return bean(new EffectCopyMoveBean(), _language);
    }

    public PokemonBeanStruct beanEffectFullHpRateBean(String _language) {
        return bean(new EffectFullHpRateBean(), _language);
    }

    public PokemonBeanStruct beanEffectInvokeBean(String _language) {
        return bean(new EffectInvokeBean(), _language);
    }

    public PokemonBeanStruct beanEffectSwitchMoveTypesBean(String _language) {
        return bean(new EffectSwitchMoveTypesBean(), _language);
    }

    public PokemonBeanStruct beanEffectCounterAttackBean(String _language) {
        return bean(new EffectCounterAttackBean(), _language);
    }

    public PokemonBeanStruct beanEffectProtectionBean(String _language) {
        return bean(new EffectProtectionBean(), _language);
    }

    public PokemonBeanStruct beanEffectCopyFighterBean(String _language) {
        return bean(new EffectCopyFighterBean(), _language);
    }

    public PokemonBeanStruct beanEffectProtectFromTypesBean(String _language) {
        return bean(new EffectProtectFromTypesBean(), _language);
    }

    public PokemonBeanStruct beanEffectUnprotectFromTypesBean(String _language) {
        return bean(new EffectUnprotectFromTypesBean(), _language);
    }

    public PokemonBeanStruct beanEffectAllyBean(String _language) {
        return bean(new EffectAllyBean(), _language);
    }

    public PokemonBeanStruct beanEffectBatonPassBean(String _language) {
        return bean(new EffectBatonPassBean(), _language);
    }

    public PokemonBeanStruct beanEffectCloneBean(String _language) {
        return bean(new EffectCloneBean(), _language);
    }

    public PokemonBeanStruct beanEffectCommonStatisticsBean(String _language) {
        return bean(new EffectCommonStatisticsBean(), _language);
    }

    public PokemonBeanStruct beanEffectOrderBean(String _language) {
        return bean(new EffectOrderBean(), _language);
    }

    public PokemonBeanStruct beanEffectRestrictionBean(String _language) {
        return bean(new EffectRestrictionBean(), _language);
    }

    public PokemonBeanStruct beanEffectSwitchAbilitiesBean(String _language) {
        return bean(new EffectSwitchAbilitiesBean(), _language);
    }

    public PokemonBeanStruct beanEffectSwitchItemsBean(String _language) {
        return bean(new EffectSwitchItemsBean(), _language);
    }

    public PokemonBeanStruct beanEffectSwitchTypesBean(String _language) {
        return bean(new EffectSwitchTypesBean(), _language);
    }

    public PokemonBeanStruct beanEffectSwitchPointViewBean(String _language) {
        return bean(new EffectSwitchPointViewBean(), _language);
    }

    public PokemonBeanStruct beanEffectRemainedHpRateBean(String _language) {
        return bean(new EffectRemainedHpRateBean(), _language);
    }

    public PokemonBeanStruct beanEffectMultMovePowerBean(String _language) {
        return bean(new EffectMultMovePowerBean(), _language);
    }

    public PokemonBeanStruct beanEffectBean(String _language) {
        return bean(new EffectBean(), _language);
    }

    public PokemonBeanStruct beanEffectVarPPBean(String _language) {
        return bean(new EffectVarPPBean(), _language);
    }

    public PokemonBeanStruct beanEffectWinMoneyBean(String _language) {
        return bean(new EffectWinMoneyBean(), _language);
    }

    public PokemonBeanStruct beanEffectWhileSendingBean(String _language) {
        return bean(new EffectWhileSendingBean(), _language);
    }

    public PokemonBeanStruct beanItemsBean(String _language) {
        return bean(new ItemsBean(), _language);
    }

    public PokemonBeanStruct beanBallBean(String _language) {
        return bean(new BallBean(), _language);
    }

    public PokemonBeanStruct beanBerryBean(String _language) {
        return bean(new BerryBean(), _language);
    }

    public PokemonBeanStruct beanBoostBean(String _language) {
        return bean(new BoostBean(), _language);
    }

    public PokemonBeanStruct beanEvolvingItemBean(String _language) {
        return bean(new EvolvingItemBean(), _language);
    }

    public PokemonBeanStruct beanEvolvingStoneBean(String _language) {
        return bean(new EvolvingStoneBean(), _language);
    }

    public PokemonBeanStruct beanFossilBean(String _language) {
        return bean(new FossilBean(), _language);
    }

    public PokemonBeanStruct beanHealingHpBean(String _language) {
        return bean(new HealingHpBean(), _language);
    }

    public PokemonBeanStruct beanHealingItemBean(String _language) {
        return bean(new HealingItemBean(), _language);
    }

    public PokemonBeanStruct beanHealingPpBean(String _language) {
        return bean(new HealingPpBean(), _language);
    }

    public PokemonBeanStruct beanHealingStatusBean(String _language) {
        return bean(new HealingStatusBean(), _language);
    }

    public PokemonBeanStruct beanItemForBattleBean(String _language) {
        return bean(new ItemForBattleBean(), _language);
    }

    public PokemonBeanStruct beanRepelBean(String _language) {
        return bean(new RepelBean(), _language);
    }

    public PokemonBeanStruct beanSellingItemBean(String _language) {
        return bean(new SellingItemBean(), _language);
    }

    public PokemonBeanStruct beanAbilityBean(String _language) {
        return bean(new AbilityBean(), _language);
    }

    public PokemonBeanStruct beanAbilitiesBean(String _language) {
        return bean(new AbilitiesBean(), _language);
    }

    public PokemonBeanStruct beanStatusBean(String _language) {
        return bean(new StatusBean(), _language);
    }

    public PokemonBeanStruct beanStatusSetBean(String _language) {
        return bean(new StatusSetBean(), _language);
    }

    public PokemonBeanStruct beanEndRoundBean(String _language) {
        return bean(new EndRoundBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundBean(String _language) {
        return bean(new EffectEndRoundBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundIndividualBean(String _language) {
        return bean(new EffectEndRoundIndividualBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundSingleStatusBean(String _language) {
        return bean(new EffectEndRoundSingleStatusBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundStatusRelationBean(String _language) {
        return bean(new EffectEndRoundStatusRelationBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundSingleRelationBean(String _language) {
        return bean(new EffectEndRoundSingleRelationBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundGlobalBean(String _language) {
        return bean(new EffectEndRoundGlobalBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundFoeBean(String _language) {
        return bean(new EffectEndRoundFoeBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundTeamBean(String _language) {
        return bean(new EffectEndRoundTeamBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundMultiRelationBean(String _language) {
        return bean(new EffectEndRoundMultiRelationBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundPositionRelationBean(String _language) {
        return bean(new EffectEndRoundPositionRelationBean(), _language);
    }

    public PokemonBeanStruct beanEffectEndRoundPositionTargetBean(String _language) {
        return bean(new EffectEndRoundPositionTargetBean(), _language);
    }

    public PokemonBeanStruct beanEffectComboBean(String _language) {
        return bean(new EffectComboBean(), _language);
    }

    public PokemonBeanStruct beanCombosBean(String _language) {
        return bean(new CombosBean(), _language);
    }

    public PokemonBeanStruct beanFightHelpBean(String _language) {
        return bean(new FightHelpBean(), _language);
    }

    public PokemonBeanStruct beanMapBean(String _language) {
        return bean(new MapBean(), _language);
    }

    public PokemonBeanStruct beanMapLevelBean(String _language) {
        return bean(new MapLevelBean(), _language);
    }

    public PokemonBeanStruct beanPokemonTeamBean(String _language) {
        return bean(new PokemonTeamBean(), _language);
    }

    public PokemonBeanStruct beanTrainerBean(String _language) {
        return bean(new TrainerBean(), _language);
    }

    public PokemonBeanStruct beanAllyBean(String _language) {
        return bean(new AllyBean(), _language);
    }

    public PokemonBeanStruct beanDualFightBean(String _language) {
        return bean(new DualFightBean(), _language);
    }

    public PokemonBeanStruct beanAreaBean(String _language) {
        return bean(new AreaBean(), _language);
    }

    public PokemonBeanStruct beanLegendaryPokemonBean(String _language) {
        return bean(new LegendaryPokemonBean(), _language);
    }

    public PokemonBeanStruct beanSellerBean(String _language) {
        return bean(new SellerBean(), _language);
    }

    public PokemonBeanStruct beanDealerBean(String _language) {
        return bean(new DealerBean(), _language);
    }

    public PokemonBeanStruct beanSolutionBean(String _language) {
        return bean(new SolutionBean(), _language);
    }

    public PokemonBeanStruct beanSimulationBean(String _language) {
        return bean(new SimulationBean(), _language);
    }

    public PokemonBeanStruct beanSimulationLevelBean(String _language) {
        return bean(new SimulationLevelBean(), _language);
    }

    public PokemonBeanStruct beanAddPokemonBean(String _language) {
        return bean(new AddPokemonBean(), _language);
    }

    public PokemonBeanStruct beanEditPokemonBean(String _language) {
        return bean(new EditPokemonBean(), _language);
    }

    public PokemonBeanStruct beanEditTrainerPokemonBean(String _language) {
        return bean(new EditTrainerPokemonBean(), _language);
    }

    public PokemonBeanStruct beanEditPokemonMovesBean(String _language) {
        return bean(new EditPokemonMovesBean(), _language);
    }

    public PokemonBeanStruct beanSelectAbilityBean(String _language) {
        return bean(new SelectAbilityBean(), _language);
    }

    public PokemonBeanStruct beanSelectItemBean(String _language) {
        return bean(new SelectItemBean(), _language);
    }

    public PokemonBeanStruct beanSelectPokemonBean(String _language) {
        return bean(new SelectPokemonBean(), _language);
    }

    public PokemonBeanStruct beanGeneralHelpBean(String _language) {
        return bean(new GeneralHelpBean(), _language);
    }

    public PokemonBeanStruct beanLangsBean(String _language) {
        return bean(new LangsBean(), _language);
    }

}
