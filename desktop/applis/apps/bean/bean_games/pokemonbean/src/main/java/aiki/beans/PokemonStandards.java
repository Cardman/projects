package aiki.beans;

import aiki.beans.abilities.AbilitiesBean;
import aiki.beans.abilities.AbilityBean;
import aiki.beans.abilities.AikiBeansAbilitiesStd;
import aiki.beans.effects.AikiBeansEffectsStd;
import aiki.beans.effects.CombosBean;
import aiki.beans.effects.EffectComboBean;
import aiki.beans.effects.EffectWhileSendingBean;
import aiki.beans.endround.*;
import aiki.beans.facade.AikiBeansFacadeStd;
import aiki.beans.facade.comparators.AikiBeansFacadeComparatorsStd;
import aiki.beans.facade.dto.AikiBeansFacadeDtoStd;
import aiki.beans.facade.dto.ItemLine;
import aiki.beans.facade.dto.MoveLine;
import aiki.beans.facade.dto.PokemonLine;
import aiki.beans.facade.fight.*;
import aiki.beans.facade.game.dto.AikiBeansFacadeGameDtoStd;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.beans.facade.map.dto.AikiBeansFacadeMapDtoStd;
import aiki.beans.facade.map.dto.PlaceIndex;
import aiki.beans.facade.simulation.dto.*;
import aiki.beans.facade.simulation.enums.AikiBeansFacadeSimulationEnumsStd;
import aiki.beans.facade.solution.dto.AikiBeansFacadeSolutionDtoStd;
import aiki.beans.facade.solution.dto.PlaceTrainerDto;
import aiki.beans.facade.solution.dto.StepDto;
import aiki.beans.facade.solution.dto.WildPokemonDto;
import aiki.beans.fight.*;
import aiki.beans.game.AikiBeansGameStd;
import aiki.beans.game.DifficultyBean;
import aiki.beans.game.GameProgressionBean;
import aiki.beans.game.PokemonPlayerBean;
import aiki.beans.help.AikiBeansHelpStd;
import aiki.beans.help.FightHelpBean;
import aiki.beans.help.GeneralHelpBean;
import aiki.beans.help.LangsBean;
import aiki.beans.items.*;
import aiki.beans.items.effects.AikiBeansItemsEffectsStd;
import aiki.beans.items.effects.EffectEndRoundItemBean;
import aiki.beans.map.AikiBeansMapStd;
import aiki.beans.map.MapBean;
import aiki.beans.map.MapLevelBean;
import aiki.beans.map.characters.*;
import aiki.beans.map.elements.AikiBeansMapElementsStd;
import aiki.beans.map.elements.AreaBean;
import aiki.beans.map.elements.LegendaryPokemonBean;
import aiki.beans.map.pokemon.AikiBeansMapPokemonStd;
import aiki.beans.map.pokemon.PokemonTeamBean;
import aiki.beans.moves.AikiBeansMovesStd;
import aiki.beans.moves.MoveBean;
import aiki.beans.moves.MoveLineBean;
import aiki.beans.moves.MovesBean;
import aiki.beans.moves.effects.*;
import aiki.beans.pokemon.AikiBeansPokemonStd;
import aiki.beans.pokemon.PokedexBean;
import aiki.beans.pokemon.PokemonBean;
import aiki.beans.pokemon.evolutions.*;
import aiki.beans.simulation.*;
import aiki.beans.solution.AikiBeansSolutionStd;
import aiki.beans.solution.SolutionBean;
import aiki.beans.status.AikiBeansStatusStd;
import aiki.beans.status.StatusBean;
import aiki.beans.status.StatusSetBean;
import aiki.beans.validators.*;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.EndRoundMainElements;
import aiki.fight.enums.Statistic;
import aiki.fight.pokemon.TrainerPlaceNames;
import aiki.fight.status.effects.EffectPartnerStatus;
import aiki.fight.util.*;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.map.levels.AreaApparition;
import aiki.map.levels.Level;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.places.Place;
import aiki.map.pokemon.*;
import aiki.map.pokemon.enums.Gender;
import aiki.map.util.MiniMapCoords;
import aiki.map.util.PlaceLevel;
import aiki.util.Point;
import code.bean.Bean;
import code.bean.nat.LgIntStruct;
import code.bean.nat.*;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.maths.LgInt;
import code.maths.Rate;
import code.util.*;
import code.util.core.StringUtil;

public final class PokemonStandards extends BeanNatLgNames implements AbstractNatImpLgNames {
    public static final String TYPE_ACTIVITY_OF_MOVE = "aiki.game.fight.ActivityOfMove";
    public static final String TYPE_MOVE_TARGET = "aiki.game.fight.util.MoveTarget";
    public static final String TYPE_TARGET_COORDS = "aiki.game.fight.TargetCoords";
    public static final String TYPE_USES_OF_MOVE = "aiki.game.UsesOfMove";
    public static final String TYPE_COPIED_MOVE = "aiki.game.fight.util.CopiedMove";
    public static final String TYPE_MOVE_TEAM_POSITION = "aiki.game.fight.MoveTeamPosition";
    public static final String TYPE_AFFECTED_MOVE = "aiki.game.fight.util.AffectedMove";
    public static final String TYPE_STACKS_OF_USES = "aiki.game.fight.StacksOfUses";
    public static final String TYPE_ANTICIPATION = "aiki.game.fight.Anticipation";
    public static final String TYPE_TYPE_DAMAGE_BOOST = "aiki.fight.util.TypeDamageBoost";
    public static final String TYPE_EFFICIENCY_RATE = "aiki.fight.util.EfficiencyRate";
    public static final String TYPE_BOOST_HP_RATE = "aiki.fight.util.BoostHpRate";
    public static final String TYPE_PK_TRAINER = "aiki.map.pokemon.PkTrainer";
    public static final String TYPE_POKEMON = "aiki.map.pokemon.Pokemon";
    public static final String TYPE_AREA_APPARITION = "aiki.map.levels.AreaApparition";
    public static final String TYPE_WILD_PK = "aiki.map.pokemon.WildPk";
    public static final String TYPE_PLACE = "aiki.map.places.Place";
    public static final String TYPE_TYPES_DUO = "aiki.fight.util.TypesDuo";
    public static final String TYPE_CATEGORY_MULT = "aiki.fight.util.CategoryMult";
    public static final String TYPE_LEVEL_MOVE = "aiki.fight.util.LevelMove";
    public static final String TYPE_POKEMON_PLAYER = "aiki.map.pokemon.PokemonPlayer";
    public static final String TYPE_EFFECT_PARTNER_STATUS = "aiki.fight.status.effects.EffectPartnerStatus";
    public static final String TYPE_TRAINER_PLACE_NAMES = "aiki.fight.pokemon.TrainerPlaceNames";
    public static final String TYPE_EFFECT_WHILE_SENDING = "aiki.fight.effects.EffectWhileSending";
    public static final String TYPE_ALLY = "aiki.map.characters.Ally";
    public static final String TYPE_TEMP_TRAINER = "aiki.map.characters.TempTrainer";
    public static final String TYPE_TRAINER_ONE_FIGHT = "aiki.map.characters.TrainerOneFight";
    public static final String TYPE_TRAINER = "aiki.map.characters.Trainer";
    public static final String TYPE_PERSON = "aiki.map.characters.Person";

    public static final String TYPE_FULL_RATE_VALIDATOR = "aiki.beans.validators.RateValidator";
    public static final String TYPE_FULL_POSITIVE_RATE_VALIDATOR = "aiki.beans.validators.PositiveRateValidator";
    public static final String TYPE_FULL_SHORT_VALIDATOR = "aiki.beans.validators.ShortValidator";
    public static final String TYPE_FULL_UNSELECTED_RADIO = "aiki.beans.validators.UnselectedRadio";
    public static final String TYPE_RATE_VALIDATOR = "RateValidator";
    public static final String TYPE_POSITIVE_RATE_VALIDATOR = "PositiveRateValidator";
    public static final String TYPE_SHORT_VALIDATOR = "ShortValidator";
    public static final String TYPE_UNSELECTED_RADIO = "UnselectedRadio";

    private static final String IS_ENABLED = "isEnabled";
    private static final String IS_INCREMENT_COUNT = "isIncrementCount";
    private static final String GET_NB_TURN = "getNbTurn";
    private static final String GET_MOVE = "getMove";
    private static final String GET_TARGET = "getTarget";
    private static final String GET_POSITION = "getPosition";
    private static final String GET_CURRENT = "getCurrent";
    private static final String GET_MAX = "getMax";
    private static final String GET_PP = "getPp";
    private static final String GET_ACTIVITY = "getActivity";
    private static final String GET_NB_ROUNDS = "getNbRounds";
    private static final String IS_FIRST_STACKED = "isFirstStacked";
    private static final String IS_LAST_STACKED = "isLastStacked";
    private static final String GET_TARGET_POSITION = "getTargetPosition";
    private static final String GET_DAMAGE = "getDamage";
    private static final String IS_INCREMENTING = "isIncrementing";
    private static final String IS_ZERO = "isZero";
    private static final String GET_BOOST = "getBoost";
    private static final String IS_ZERO_OR_GT = "isZeroOrGt";
    private static final String ABS_NB = "absNb";
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
    private static final String GET_TRAINER = "getTrainer";
    private static final String GET_PLACE = "getPlace";
    private FacadeGame dataBase;
    public PokemonStandards() {
        PokemonStandards val_ = this;
        DefaultInitialization.basicStandards(val_);
        getValidators().addEntry("positive_rate_validator",new PositiveRateValidator());
        getValidators().addEntry("rate_validator",new RateValidator());
        getValidators().addEntry("short_validator",new ShortValidator());
        getValidators().addEntry("selected_radio",new UnselectedRadio());
    }

    @Override
    public void buildOther() {
        buildBeans();
        AikiBeansAbilitiesStd.build(this);
        AikiBeansStd.build(this);
        AikiBeansEffectsStd.build(this);
        AikiBeansEndroundStd.build(this);
        AikiBeansFacadeComparatorsStd.build(this);
        AikiBeansFacadeDtoStd.build(this);
        AikiBeansFacadeFightStd.build(this);
        AikiBeansFacadeStd.build(this);
        AikiBeansFacadeGameDtoStd.build(this);
        AikiBeansFacadeMapDtoStd.build(this);
        AikiBeansFacadeSimulationDtoStd.build(this);
        AikiBeansFacadeSimulationEnumsStd.build(this);
        AikiBeansFacadeSolutionDtoStd.build(this);
        AikiBeansFightStd.build(this);
        AikiBeansGameStd.build(this);
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
        AikiBeansValidatorsStd.build(this);
        buildActivityOfMove(this);
        buildMoveTarget(this);
        buildTargetCoords(this);
        buildUsesOfMove(this);
        buildCopiedMove(this);
        buildMoveTeamPosition(this);
        buildAffectedMove(this);
        buildStacksOfUses(this);
        buildAnticipation(this);
        buildRate(this);
        buildTypeDamageBoost(this);
        buildEfficiencyRate(this);
        buildBoostHpRate(this);
        buildPkTrainer(this);
        buildPokemon(this);
        buildAreaApparition(this);
        buildWildPk(this);
        buildPlace(this);
        buildTypesDuo(this);
        buildCategoryMult(this);
        buildLevelMove(this);
        buildPokemonPlayer(this);
        buildEffectPartnerStatus(this);
        buildTrainerPlaceNames(this);
        buildLgInt(this);
        buildEffectWhileSending(this);
        buildAlly(this);
        buildTempTrainer(this);
        buildTrainerOneFight(this);
        buildTrainer(this);
        buildPerson(this);
        buildRateValidator(this);
        buildPositiveRateValidator(this);
        buildShortValidator(this);
        buildUnselectedRadio(this);
    }
    private static void buildActivityOfMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ACTIVITY_OF_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_ENABLED,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_INCREMENT_COUNT,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NB_TURN,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_ACTIVITY_OF_MOVE, type_);
    }
    private static void buildMoveTarget(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_MOVE_TARGET, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TARGET,params_,PokemonStandards.TYPE_TARGET_COORDS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_MOVE_TARGET, type_);
    }
    private static void buildTargetCoords(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TARGET_COORDS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_POSITION,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TARGET_COORDS, type_);
    }
    private static void buildUsesOfMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_USES_OF_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_CURRENT,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MAX,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_USES_OF_MOVE, type_);
    }
    private static void buildCopiedMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_COPIED_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_PP,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_COPIED_MOVE, type_);
    }
    private static void buildMoveTeamPosition(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_MOVE_TEAM_POSITION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_MOVE_TEAM_POSITION, type_);
    }
    private static void buildAffectedMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_AFFECTED_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ACTIVITY,params_,PokemonStandards.TYPE_ACTIVITY_OF_MOVE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_AFFECTED_MOVE, type_);
    }
    private static void buildStacksOfUses(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_STACKS_OF_USES, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NB_ROUNDS,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_FIRST_STACKED,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_LAST_STACKED,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_STACKS_OF_USES, type_);
    }
    private static void buildAnticipation(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ANTICIPATION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TARGET_POSITION,params_,PokemonStandards.TYPE_TARGET_COORDS, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DAMAGE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_INCREMENTING,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NB_ROUNDS,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_ANTICIPATION, type_);
    }
    private static void buildRate(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_ZERO,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(IS_ZERO_OR_GT,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(ABS_NB,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_RATE, type_);
    }
    private static void buildTypeDamageBoost(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TYPE_DAMAGE_BOOST, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_BOOST,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TYPE_DAMAGE_BOOST, type_);
    }
    private static void buildEfficiencyRate(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFICIENCY_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_EFF,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_HP_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_EFFICIENCY_RATE, type_);
    }
    private static void buildBoostHpRate(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_BOOST_HP_RATE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_HP_RATE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_BOOST,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_BOOST_HP_RATE, type_);
    }
    private static void buildPkTrainer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_PK_TRAINER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_LEVEL,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVES,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_PK_TRAINER, type_);
    }
    private static void buildPokemon(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        /*params_ = new StringList();
        method_ = new StandardMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);*/
        _std.getStds().addEntry(TYPE_POKEMON, type_);
    }
    private static void buildAreaApparition(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_AREA_APPARITION, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_AVG_NB_STEPS,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_WILD_POKEMON,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_WILD_POKEMON_FISHING,params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_AREA_APPARITION, type_);
    }
    private static void buildWildPk(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_WILD_PK, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_LEVEL,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_WILD_PK, type_);
    }
    private static void buildPlace(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_PLACE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NAME,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_PLACE, type_);
    }
    private static void buildTypesDuo(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TYPES_DUO, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_DAMAGE_TYPE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_POKEMON_TYPE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TYPES_DUO, type_);
    }
    private static void buildCategoryMult(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_CATEGORY_MULT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_CATEGORY,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MULT,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_CATEGORY_MULT, type_);
    }
    private static void buildLevelMove(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_LEVEL_MOVE, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_LEVEL,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MOVE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_LEVEL_MOVE, type_);
    }
    private static void buildPokemonPlayer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER, fields_, methods_, PokemonStandards.TYPE_POKEMON);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_WON_EXP_SINCE_LAST_LEVEL,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_HAPPINESS,params_, PRIM_INTEGER, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_ITEM,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER, type_);
    }
    private static void buildEffectPartnerStatus(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFECT_PARTNER_STATUS, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_RESTORED_HP_RATE_LOVED_ALLY,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_WEDDING_ALLY,params_,BeanNatCommonLgNames.PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_MULT_DAMAGE_AGAINST_FOE,params_,PokemonStandards.TYPE_RATE, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_EFFECT_PARTNER_STATUS, type_);
    }
    private static void buildTrainerPlaceNames(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        SpecNatMethod method_;
        StringList params_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TRAINER_PLACE_NAMES, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_TRAINER,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_PLACE,params_,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStds().addEntry(TYPE_TRAINER_PLACE_NAMES, type_);
    }
    private static void buildLgInt(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_LG_INT, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        type_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        _std.getStds().addEntry(TYPE_LG_INT, type_);
    }
    private static void buildEffectWhileSending(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_EFFECT_WHILE_SENDING, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_EFFECT_WHILE_SENDING, type_);
    }
    private static void buildAlly(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_ALLY, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_ALLY, type_);
    }
    private static void buildTempTrainer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TEMP_TRAINER, fields_, methods_, PokemonStandards.TYPE_TRAINER_ONE_FIGHT);
        _std.getStds().addEntry(TYPE_TEMP_TRAINER, type_);
    }
    private static void buildTrainerOneFight(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TRAINER_ONE_FIGHT, fields_, methods_, PokemonStandards.TYPE_TRAINER);
        _std.getStds().addEntry(TYPE_TRAINER_ONE_FIGHT, type_);
    }
    private static void buildTrainer(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_TRAINER, fields_, methods_, PokemonStandards.TYPE_PERSON);
        _std.getStds().addEntry(TYPE_TRAINER, type_);
    }
    private static void buildPerson(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_PERSON, fields_, methods_, BeanNatCommonLgNames.OBJECT);
        _std.getStds().addEntry(TYPE_PERSON, type_);
    }

    private static void buildRateValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_RATE_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_RATE_VALIDATOR, type_);
    }
    private static void buildPositiveRateValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_POSITIVE_RATE_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_POSITIVE_RATE_VALIDATOR, type_);
    }
    private static void buildShortValidator(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_SHORT_VALIDATOR, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_SHORT_VALIDATOR, type_);
    }
    private static void buildUnselectedRadio(PokemonStandards _std) {
        SpecialNatClass type_;
        CustList<StandardField> fields_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        fields_ = new CustList<StandardField>();
        type_ = new SpecialNatClass(TYPE_UNSELECTED_RADIO, fields_, methods_, TYPE_VALIDATOR);
        _std.getStds().addEntry(TYPE_UNSELECTED_RADIO, type_);
    }

    @Override
    public void beforeDisplaying(Struct _arg) {
        ((PokemonBeanStruct) _arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        StringMapObject stringMapObject_ = new StringMapObject();
        if (_bean instanceof PokemonBeanStruct&&((PokemonBeanStruct) _bean).getBean() instanceof WithForms) {
            stringMapObject_ = ((WithForms)((PokemonBeanStruct) _bean).getBean()).getForms();
        }
        String currentBeanName_;
        RendDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        currentBeanName_ = rendDocumentBlock_.getBeanName();
        Struct bean_ = getBeanOrNull(currentBeanName_);
        if (bean_ instanceof PokemonBeanStruct&& ((PokemonBeanStruct) bean_).getBean() instanceof WithForms) {
            ((WithForms) ((PokemonBeanStruct) bean_).getBean()).setForms(stringMapObject_);
        }
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, _ctx, _rendStack);
    }

    @Override
    public void setBeanForms(Struct _mainBean, String _beanName) {
        beanForms(_mainBean, _beanName);
    }

    private void beanForms(Struct _mainBean,
                           String _beanName) {
        if (_mainBean == null) {
            return;
        }
        Struct bean_ = getBeansStruct().getVal(_beanName);
        if (bean_ == null) {
            return;
        }
        gearFw(_mainBean, bean_);
    }

    void gearFw(Struct _mainBean, Struct _bean) {
        StringMapObject forms_ = ((WithForms) ((PokemonBeanStruct)_bean).getBean()).getForms();
        StringMapObject formsMap_ = ((WithForms) ((PokemonBeanStruct)_mainBean).getBean()).getForms();
        forms_.putAllMap(formsMap_);
    }

    @Override
    public ResultErrorStd getOtherResult(ClassField _classField, Struct _instance) {
        if (_instance instanceof PkLineStruct) {
            PokemonLine instance_ = ((PkLineStruct) _instance).getWildPk();
            return AikiBeansFacadeDtoStd.getResultPokemonLine(_classField, instance_);
        }
        if (_instance instanceof ItLineStruct) {
            ItemLine instance_ = ((ItLineStruct) _instance).getWildPk();
            return AikiBeansFacadeDtoStd.getResultItemLine(_classField, instance_);
        }
        if (_instance instanceof MvLineStruct) {
            MoveLine instance_ = ((MvLineStruct) _instance).getWildPk();
            if (instance_ instanceof SelectLineMove) {
                return AikiBeansFacadeSimulationDtoStd.getResultSelectLineMove(_classField, (SelectLineMove) instance_);
            }
            if (instance_ instanceof RadioLineMove) {
                return AikiBeansFacadeSimulationDtoStd.getResultRadioLineMove(_classField, (RadioLineMove) instance_);
            }
            return AikiBeansFacadeDtoStd.getResultMoveLine(_classField, instance_);
        }
        if (_instance instanceof EvLineStruct) {
            return AikiBeansFacadeSimulationDtoStd.getResultEvLine(_classField, ((EvLineStruct) _instance).getEvLine());
        }
        if (_instance instanceof PlaceIndexStruct) {
            return AikiBeansFacadeMapDtoStd.getResultPlaceIndex(_classField, ((PlaceIndexStruct) _instance).getPlaceIndex());
        }
        if (_instance instanceof PlaceTrainerDtoStruct) {
            return AikiBeansFacadeSolutionDtoStd.getResultPlaceTrainerDto(_classField, ((PlaceTrainerDtoStruct) _instance).getInstance());
        }
        if (_instance instanceof PokemonPlayerDtoStruct) {
            return AikiBeansFacadeSimulationDtoStd.getResultPokemonPlayerDto(_classField, ((PokemonPlayerDtoStruct) _instance).getInstance());
        }
        if (_instance instanceof PokemonTrainerDtoStruct) {
            return AikiBeansFacadeSimulationDtoStd.getResultPokemonTrainerDto(_classField, ((PokemonTrainerDtoStruct) _instance).getInstance());
        }
        if (_instance instanceof WildPokemonDtoStruct) {
            return AikiBeansFacadeSolutionDtoStd.getResultWildPokemonDto(_classField, ((WildPokemonDtoStruct) _instance).getInstance());
        }
        Bean instance_ = ((PokemonBeanStruct)_instance).getBean();
        if (instance_ instanceof AbilitiesBean) {
            return AikiBeansAbilitiesStd.getResultAbilitiesBean(_classField, _instance);
        }
        if (instance_ instanceof AbilityBean) {
            return AikiBeansAbilitiesStd.getResultAbilityBean(_classField, _instance);
        }
        if (instance_ instanceof CombosBean) {
            return AikiBeansEffectsStd.getResultCombosBean(_classField, _instance);
        }
        if (instance_ instanceof EffectComboBean) {
            return AikiBeansEffectsStd.getResultEffectComboBean(_classField, _instance);
        }
        if (instance_ instanceof EffectWhileSendingBean) {
            return AikiBeansEffectsStd.getResultEffectWhileSendingBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundFoeBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundFoeBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundGlobalBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundGlobalBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundIndividualBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundIndividualBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundMultiRelationBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundMultiRelationBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundPositionRelationBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundPositionRelationBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundSingleRelationBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundSingleRelationBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundStatusRelationBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundStatusRelationBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundStatusBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundStatusBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundTeamBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundTeamBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundBean) {
            return AikiBeansEndroundStd.getResultEffectEndRoundBean(_classField, _instance);
        }
        if (instance_ instanceof FightHelpBean) {
            return AikiBeansHelpStd.getResultFightHelpBean(_classField, _instance);
        }
        if (instance_ instanceof FightBean) {
            return AikiBeansFightStd.getResultFightBean(_classField, _instance);
        }
        if (instance_ instanceof FightCalculationBean) {
            return AikiBeansFightStd.getResultFightCalculationBean(_classField, _instance);
        }
        if (instance_ instanceof FighterBean) {
            return AikiBeansFightStd.getResultFighterBean(_classField, _instance);
        }
        if (instance_ instanceof TeamBean) {
            return AikiBeansFightStd.getResultTeamBean(_classField, _instance);
        }
        if (instance_ instanceof DifficultyBean) {
            return AikiBeansGameStd.getResultDifficultyBean(_classField, _instance);
        }
        if (instance_ instanceof GameProgressionBean) {
            return AikiBeansGameStd.getResultGameProgressionBean(_classField, _instance);
        }
        if (instance_ instanceof PokemonPlayerBean) {
            return AikiBeansGameStd.getResultPokemonPlayerBean(_classField, _instance);
        }
        if (instance_ instanceof GeneralHelpBean) {
            return AikiBeansHelpStd.getResultGeneralHelpBean(_classField, _instance);
        }
        if (instance_ instanceof LangsBean) {
            return AikiBeansHelpStd.getResultLangsBean(_classField, _instance);
        }
        if (instance_ instanceof BallBean) {
            return AikiBeansItemsStd.getResultBallBean(_classField, _instance);
        }
        if (instance_ instanceof BerryBean) {
            return AikiBeansItemsStd.getResultBerryBean(_classField, _instance);
        }
        if (instance_ instanceof BoostBean) {
            return AikiBeansItemsStd.getResultBoostBean(_classField, _instance);
        }
        if (instance_ instanceof EvolvingItemBean) {
            return AikiBeansItemsStd.getResultEvolvingItemBean(_classField, _instance);
        }
        if (instance_ instanceof EvolvingStoneBean) {
            return AikiBeansItemsStd.getResultEvolvingStoneBean(_classField, _instance);
        }
        if (instance_ instanceof FossilBean) {
            return AikiBeansItemsStd.getResultFossilBean(_classField, _instance);
        }
        if (instance_ instanceof HealingHpBean) {
            return AikiBeansItemsStd.getResultHealingHpBean(_classField, _instance);
        }
        if (instance_ instanceof HealingPpBean) {
            return AikiBeansItemsStd.getResultHealingPpBean(_classField, _instance);
        }
        if (instance_ instanceof HealingStatusBean) {
            return AikiBeansItemsStd.getResultHealingStatusBean(_classField, _instance);
        }
        if (instance_ instanceof HealingItemBean) {
            return AikiBeansItemsStd.getResultHealingItemBean(_classField, _instance);
        }
        if (instance_ instanceof ItemForBattleBean) {
            return AikiBeansItemsStd.getResultItemForBattleBean(_classField, _instance);
        }
        if (instance_ instanceof RepelBean) {
            return AikiBeansItemsStd.getResultRepelBean(_classField, _instance);
        }
        if (instance_ instanceof ItemsBean) {
            return AikiBeansItemsStd.getResultItemsBean(_classField, _instance);
        }
        if (instance_ instanceof ItemBean) {
            return AikiBeansItemsStd.getResultItemBean(_classField, _instance);
        }
        if (instance_ instanceof AllyBean) {
            return AikiBeansMapCharactersStd.getResultAllyBean(_classField, _instance);
        }
        if (instance_ instanceof DualFightBean) {
            return AikiBeansMapCharactersStd.getResultDualFightBean(_classField, _instance);
        }
        if (instance_ instanceof TrainerBean) {
            return AikiBeansMapCharactersStd.getResultTrainerBean(_classField, _instance);
        }
        if (instance_ instanceof AreaBean) {
            return AikiBeansMapElementsStd.getResultAreaBean(_classField, _instance);
        }
        if (instance_ instanceof LegendaryPokemonBean) {
            return AikiBeansMapElementsStd.getResultLegendaryPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof MapBean) {
            return AikiBeansMapStd.getResultMapBean(_classField, _instance);
        }
        if (instance_ instanceof MapLevelBean) {
            return AikiBeansMapStd.getResultMapLevelBean(_classField, _instance);
        }
        if (instance_ instanceof PokemonTeamBean) {
            return AikiBeansMapPokemonStd.getResultPokemonTeamBean(_classField, _instance);
        }
        if (instance_ instanceof EffectAllyBean) {
            return AikiBeansMovesEffectsStd.getResultEffectAllyBean(_classField, _instance);
        }
        if (instance_ instanceof EffectBatonPassBean) {
            return AikiBeansMovesEffectsStd.getResultEffectBatonPassBean(_classField, _instance);
        }
        if (instance_ instanceof EffectCloneBean) {
            return AikiBeansMovesEffectsStd.getResultEffectCloneBean(_classField, _instance);
        }
        if (instance_ instanceof EffectCommonStatisticsBean) {
            return AikiBeansMovesEffectsStd.getResultEffectCommonStatisticsBean(_classField, _instance);
        }
        if (instance_ instanceof EffectCopyFighterBean) {
            return AikiBeansMovesEffectsStd.getResultEffectCopyFighterBean(_classField, _instance);
        }
        if (instance_ instanceof EffectCopyMoveBean) {
            return AikiBeansMovesEffectsStd.getResultEffectCopyMoveBean(_classField, _instance);
        }
        if (instance_ instanceof EffectCounterAttackBean) {
            return AikiBeansMovesEffectsStd.getResultEffectCounterAttackBean(_classField, _instance);
        }
        if (instance_ instanceof EffectDamageBean) {
            return AikiBeansMovesEffectsStd.getResultEffectDamageBean(_classField, _instance);
        }
        if (instance_ instanceof EffectDamageRateBean) {
            return AikiBeansMovesEffectsStd.getResultEffectDamageRateBean(_classField, _instance);
        }
        if (instance_ instanceof EffectEndRoundMoveBean) {
            return AikiBeansMovesEffectsStd.getResultEffectEndRoundMoveBean(_classField, _instance);
        }
        if (instance_ instanceof EffectFullHpRateBean) {
            return AikiBeansMovesEffectsStd.getResultEffectFullHpRateBean(_classField, _instance);
        }
        if (instance_ instanceof EffectGlobalBean) {
            return AikiBeansMovesEffectsStd.getResultEffectGlobalBean(_classField, _instance);
        }
        if (instance_ instanceof EffectInvokeBean) {
            return AikiBeansMovesEffectsStd.getResultEffectInvokeBean(_classField, _instance);
        }
        if (instance_ instanceof EffectMultSufferedMovePowerBean) {
            return AikiBeansMovesEffectsStd.getResultEffectMultSufferedMovePowerBean(_classField, _instance);
        }
        if (instance_ instanceof EffectMultUsedMovePowerBean) {
            return AikiBeansMovesEffectsStd.getResultEffectMultUsedMovePowerBean(_classField, _instance);
        }
        if (instance_ instanceof EffectOrderBean) {
            return AikiBeansMovesEffectsStd.getResultEffectOrderBean(_classField, _instance);
        }
        if (instance_ instanceof EffectProtectFromTypesBean) {
            return AikiBeansMovesEffectsStd.getResultEffectProtectFromTypesBean(_classField, _instance);
        }
        if (instance_ instanceof EffectProtectionBean) {
            return AikiBeansMovesEffectsStd.getResultEffectProtectionBean(_classField, _instance);
        }
        if (instance_ instanceof EffectRemainedHpRateBean) {
            return AikiBeansMovesEffectsStd.getResultEffectRemainedHpRateBean(_classField, _instance);
        }
        if (instance_ instanceof EffectRestrictionBean) {
            return AikiBeansMovesEffectsStd.getResultEffectRestrictionBean(_classField, _instance);
        }
        if (instance_ instanceof EffectStatisticBean) {
            return AikiBeansMovesEffectsStd.getResultEffectStatisticBean(_classField, _instance);
        }
        if (instance_ instanceof EffectStatusBean) {
            return AikiBeansMovesEffectsStd.getResultEffectStatusBean(_classField, _instance);
        }
        if (instance_ instanceof EffectSwitchMoveTypesBean) {
            return AikiBeansMovesEffectsStd.getResultEffectSwitchMoveTypesBean(_classField, _instance);
        }
        if (instance_ instanceof EffectSwitchTypesBean) {
            return AikiBeansMovesEffectsStd.getResultEffectSwitchTypesBean(_classField, _instance);
        }
        if (instance_ instanceof EffectTeamBean) {
            return AikiBeansMovesEffectsStd.getResultEffectTeamBean(_classField, _instance);
        }
        if (instance_ instanceof EffectTeamWhileSendFoeBean) {
            return AikiBeansMovesEffectsStd.getResultEffectTeamWhileSendFoeBean(_classField, _instance);
        }
        if (instance_ instanceof EffectUnprotectFromTypesBean) {
            return AikiBeansMovesEffectsStd.getResultEffectUnprotectFromTypesBean(_classField, _instance);
        }
        if (instance_ instanceof EffectVarPPBean) {
            return AikiBeansMovesEffectsStd.getResultEffectVarPPBean(_classField, _instance);
        }
        if (instance_ instanceof EffectWinMoneyBean) {
            return AikiBeansMovesEffectsStd.getResultEffectWinMoneyBean(_classField, _instance);
        }
        if (instance_ instanceof EffectBean) {
            return AikiBeansMovesEffectsStd.getResultEffectBean(_classField, _instance);
        }
        if (instance_ instanceof MoveBean) {
            return AikiBeansMovesStd.getResultMoveBean(_classField, _instance);
        }
        if (instance_ instanceof MoveLineBean) {
            return AikiBeansMovesStd.getResultMoveLineBean(_classField, _instance);
        }
        if (instance_ instanceof MovesBean) {
            return AikiBeansMovesStd.getResultMovesBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionHappinessBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionHappinessBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionItemBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionItemBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionLevelGenderBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionLevelGenderBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionLevelBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionLevelBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionMoveBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionMoveBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionMoveTypeBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionMoveTypeBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionStoneGenderBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionStoneGenderBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionStoneBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionStoneBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionTeamBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionTeamBean(_classField, _instance);
        }
        if (instance_ instanceof EvolutionBean) {
            return AikiBeansPokemonEvolutionsStd.getResultEvolutionBean(_classField, _instance);
        }
        if (instance_ instanceof PokedexBean) {
            return AikiBeansPokemonStd.getResultPokedexBean(_classField, _instance);
        }
        if (instance_ instanceof PokemonBean) {
            return AikiBeansPokemonStd.getResultPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof AddPokemonBean) {
            return AikiBeansSimulationStd.getResultAddPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof EditPokemonBean) {
            return AikiBeansSimulationStd.getResultEditPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof EditPokemonMovesBean) {
            return AikiBeansSimulationStd.getResultEditPokemonMovesBean(_classField, _instance);
        }
        if (instance_ instanceof EditTrainerPokemonBean) {
            return AikiBeansSimulationStd.getResultEditTrainerPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof SelectAbilityBean) {
            return AikiBeansSimulationStd.getResultSelectAbilityBean(_classField, _instance);
        }
        if (instance_ instanceof SelectItemBean) {
            return AikiBeansSimulationStd.getResultSelectItemBean(_classField, _instance);
        }
        if (instance_ instanceof SelectPokemonBean) {
            return AikiBeansSimulationStd.getResultSelectPokemonBean(_classField, _instance);
        }
        if (instance_ instanceof SimulationBean) {
            return AikiBeansSimulationStd.getResultSimulationBean(_classField, _instance);
        }
        if (instance_ instanceof SimulationLevelBean) {
            return AikiBeansSimulationStd.getResultSimulationLevelBean(_classField, _instance);
        }
        if (instance_ instanceof SolutionBean) {
            return AikiBeansSolutionStd.getResultSolutionBean(_classField, _instance);
        }
        if (instance_ instanceof StatusBean) {
            return AikiBeansStatusStd.getResultStatusBean(_classField, _instance);
        }
        if (instance_ instanceof StatusSetBean) {
            return AikiBeansStatusStd.getResultStatusSetBean(_classField, _instance);
        }
        return new ResultErrorStd();
    }

    @Override
    public ResultErrorStd setOtherResult(ClassField _classField, Struct _instance, Struct _val) {
        if (_instance instanceof MvLineStruct) {
            return AikiBeansFacadeSimulationDtoStd.setResultSelectLineMove(_classField, _val, (SelectLineMove) ((MvLineStruct) _instance).getWildPk());
        }
        if (_instance instanceof EvLineStruct) {
            return AikiBeansFacadeSimulationDtoStd.setResultEvLine(_classField, _val, ((EvLineStruct) _instance).getEvLine());
        }
        Bean instance_ = ((PokemonBeanStruct)_instance).getBean();
        if (instance_ instanceof AbilitiesBean) {
            return AikiBeansAbilitiesStd.setResultAbilitiesBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EffectComboBean) {
            return AikiBeansEffectsStd.setResultEffectComboBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EffectWhileSendingBean) {
            return AikiBeansEffectsStd.setResultEffectWhileSendingBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EffectEndRoundBean) {
            return AikiBeansEndroundStd.setResultEffectEndRoundBean(_classField, _instance, _val);
        }
        if (instance_ instanceof DifficultyBean) {
            return AikiBeansGameStd.setResultDifficultyBean(_classField, _instance, _val);
        }
        if (instance_ instanceof ItemsBean) {
            return AikiBeansItemsStd.setResultItemsBean(_classField, _instance, _val);
        }
        if (instance_ instanceof ItemBean) {
            return AikiBeansItemsStd.setResultItemBean(_classField, _instance, _val);
        }
        if (instance_ instanceof AllyBean) {
            return AikiBeansMapCharactersStd.setResultAllyBean(_classField, _instance, _val);
        }
        if (instance_ instanceof PokemonTeamBean) {
            return AikiBeansMapPokemonStd.setResultPokemonTeamBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EffectBean) {
            return AikiBeansMovesEffectsStd.setResultEffectBean(_classField, _instance, _val);
        }
        if (instance_ instanceof MoveLineBean) {
            return AikiBeansMovesStd.setResultMoveLineBean(_classField, _instance, _val);
        }
        if (instance_ instanceof MovesBean) {
            return AikiBeansMovesStd.setResultMovesBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EvolutionBean) {
            return AikiBeansPokemonEvolutionsStd.setResultEvolutionBean(_classField, _instance, _val);
        }
        if (instance_ instanceof PokedexBean) {
            return AikiBeansPokemonStd.setResultPokedexBean(_classField, _instance, _val);
        }
        if (instance_ instanceof AddPokemonBean) {
            return AikiBeansSimulationStd.setResultAddPokemonBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EditPokemonBean) {
            return AikiBeansSimulationStd.setResultEditPokemonBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EditPokemonMovesBean) {
            return AikiBeansSimulationStd.setResultEditPokemonMovesBean(_classField, _instance, _val);
        }
        if (instance_ instanceof EditTrainerPokemonBean) {
            return AikiBeansSimulationStd.setResultEditTrainerPokemonBean(_classField, _instance, _val);
        }
        if (instance_ instanceof SelectAbilityBean) {
            return AikiBeansSimulationStd.setResultSelectAbilityBean(_classField, _instance, _val);
        }
        if (instance_ instanceof SelectItemBean) {
            return AikiBeansSimulationStd.setResultSelectItemBean(_classField, _instance, _val);
        }
        if (instance_ instanceof SelectPokemonBean) {
            return AikiBeansSimulationStd.setResultSelectPokemonBean(_classField, _instance, _val);
        }
        if (instance_ instanceof SimulationBean) {
            return AikiBeansSimulationStd.setResultSimulationBean(_classField, _instance, _val);
        }
        if (instance_ instanceof StatusSetBean) {
            return AikiBeansStatusStd.setResultStatusSetBean(_classField, _instance, _val);
        }
        return new ResultErrorStd();
    }

    @Override
    public ResultErrorStd getOtherResultBean(Struct _instance, ClassMethodId _method, Struct... _args) {
        if (_instance instanceof PkStruct) {
            Pokemon instance_ = ((PkStruct)_instance).getWildPk();
            if (instance_ instanceof PkTrainer) {
                return PokemonStandards.invokeMethodPkTrainer(_method, (PkTrainer) instance_, _args);
            }
            if (instance_ instanceof WildPk) {
                return PokemonStandards.invokeMethodWildPk(_method, (WildPk) instance_, _args);
            }
            if (instance_ instanceof PokemonPlayer) {
                return PokemonStandards.invokeMethodPokemonPlayer(_method, (PokemonPlayer) instance_, _args);
            }
            return PokemonStandards.invokeMethodPokemon(_method, instance_, _args);
        }
        if (_instance instanceof EffectPartnerStatusStruct) {
            EffectPartnerStatus instance_ = ((EffectPartnerStatusStruct) _instance).getEffectPartnerStatus();
            return PokemonStandards.invokeMethodEffectPartnerStatus(_method, instance_, _args);
        }
        if (_instance instanceof RateStruct) {
            return PokemonStandards.invokeMethodRate(_method, ((RateStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof MvLineStruct) {
            return AikiBeansFacadeDtoStd.invokeMethodMoveLine(_method, ((MvLineStruct) _instance).getWildPk(), _args);
        }
        if (_instance instanceof PlaceStruct) {
            return PokemonStandards.invokeMethodPlace(_method, ((PlaceStruct) _instance).getWildPk(), _args);
        }
        if (_instance instanceof AreaApparitionStruct) {
            return PokemonStandards.invokeMethodAreaApparition(_method, ((AreaApparitionStruct) _instance).getWildPk(), _args);
        }
        if (_instance instanceof PlaceIndexStruct) {
            return AikiBeansFacadeMapDtoStd.invokeMethodPlaceIndex(_method, ((PlaceIndexStruct) _instance).getPlaceIndex(), _args);
        }
        if (_instance instanceof StatisticInfoPkPlayerStruct) {
            return AikiBeansFacadeGameDtoStd.invokeMethodStatisticInfoPkPlayer(_method, ((StatisticInfoPkPlayerStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof TrainerPlaceNamesStruct) {
            return PokemonStandards.invokeMethodTrainerPlaceNames(_method, ((TrainerPlaceNamesStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof TypesDuoStruct) {
            return PokemonStandards.invokeMethodTypesDuo(_method, ((TypesDuoStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof LevelMoveStruct) {
            return PokemonStandards.invokeMethodLevelMove(_method, ((LevelMoveStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof KeyHypothesisStruct) {
            return AikiBeansFacadeFightStd.invokeMethodKeyHypothesis(_method, ((KeyHypothesisStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof StatisticInfoStruct) {
            return AikiBeansFacadeFightStd.invokeMethodStatisticInfo(_method, ((StatisticInfoStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof StepDtoStruct) {
            return AikiBeansFacadeSolutionDtoStd.invokeMethodStepDto(_method, ((StepDtoStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof ActivityOfMoveStruct) {
            return PokemonStandards.invokeMethodActivityOfMove(_method, ((ActivityOfMoveStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof TargetCoordsStruct) {
            return PokemonStandards.invokeMethodTargetCoords(_method, ((TargetCoordsStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof TypeDamageBoostStruct) {
            return PokemonStandards.invokeMethodTypeDamageBoost(_method, ((TypeDamageBoostStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof MoveTargetStruct) {
            return PokemonStandards.invokeMethodMoveTarget(_method, ((MoveTargetStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof MultPowerMovesStruct) {
            return AikiBeansFacadeFightStd.invokeMethodMultPowerMoves(_method, ((MultPowerMovesStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof SufferedDamageCategoryStruct) {
            return AikiBeansFacadeFightStd.invokeMethodSufferedDamageCategory(_method, ((SufferedDamageCategoryStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof UsesOfMoveStruct) {
            return PokemonStandards.invokeMethodUsesOfMove(_method, ((UsesOfMoveStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof CopiedMoveStruct) {
            return PokemonStandards.invokeMethodCopiedMove(_method, ((CopiedMoveStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof MoveTeamPositionStruct) {
            return PokemonStandards.invokeMethodMoveTeamPosition(_method, ((MoveTeamPositionStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof AffectedMoveStruct) {
            return PokemonStandards.invokeMethodAffectedMove(_method, ((AffectedMoveStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof StacksOfUsesStruct) {
            return PokemonStandards.invokeMethodStacksOfUses(_method, ((StacksOfUsesStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof AnticipationStruct) {
            return PokemonStandards.invokeMethodAnticipation(_method, ((AnticipationStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof EfficiencyRateStruct) {
            return PokemonStandards.invokeMethodEfficiencyRate(_method, ((EfficiencyRateStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof BoostHpRateStruct) {
            return PokemonStandards.invokeMethodBoostHpRate(_method, ((BoostHpRateStruct) _instance).getInstance(), _args);
        }
        if (_instance instanceof CategoryMultStruct) {
            return PokemonStandards.invokeMethodCategoryMult(_method, ((CategoryMultStruct) _instance).getInstance(), _args);
        }
        Bean instance_ = ((PokemonBeanStruct)_instance).getBean();
        if (instance_ instanceof AbilitiesBean) {
            return AikiBeansAbilitiesStd.invokeMethodAbilitiesBean(_instance, _method, _args);
        }
        if (instance_ instanceof AbilityBean) {
            return AikiBeansAbilitiesStd.invokeMethodAbilityBean(_instance, _method, _args);
        }
        if (instance_ instanceof WelcomeBean) {
            return AikiBeansStd.invokeMethodWelcomeBean(_instance, _method, _args);
        }
        if (instance_ instanceof CombosBean) {
            return AikiBeansEffectsStd.invokeMethodCombosBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectComboBean) {
            return AikiBeansEffectsStd.invokeMethodEffectComboBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectWhileSendingBean) {
            return AikiBeansEffectsStd.invokeMethodEffectWhileSendingBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectEndRoundIndividualBean) {
            return AikiBeansEndroundStd.invokeMethodEffectEndRoundIndividualBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectEndRoundMultiRelationBean) {
            return AikiBeansEndroundStd.invokeMethodEffectEndRoundMultiRelationBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectEndRoundPositionTargetBean) {
            return AikiBeansEndroundStd.invokeMethodEffectEndRoundPositionTargetBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectEndRoundBean) {
            return AikiBeansEndroundStd.invokeMethodEffectEndRoundBean(_instance, _method, _args);
        }
        if (instance_ instanceof EndRoundBean) {
            return AikiBeansEndroundStd.invokeMethodEndRoundBean(_instance, _method, _args);
        }
        if (instance_ instanceof FightHelpBean) {
            return AikiBeansHelpStd.invokeMethodFightHelpBean(_instance, _method, _args);
        }
        if (instance_ instanceof FightBean) {
            return AikiBeansFightStd.invokeMethodFightBean(_instance, _method, _args);
        }
        if (instance_ instanceof FightCalculationBean) {
            return AikiBeansFightStd.invokeMethodFightCalculationBean(_instance, _method, _args);
        }
        if (instance_ instanceof FighterBean) {
            return AikiBeansFightStd.invokeMethodFighterBean(_instance, _method, _args);
        }
        if (instance_ instanceof TeamBean) {
            return AikiBeansFightStd.invokeMethodTeamBean(_instance, _method, _args);
        }
        if (instance_ instanceof DifficultyBean) {
            return AikiBeansGameStd.invokeMethodDifficultyBean(_instance, _method, _args);
        }
        if (instance_ instanceof GameProgressionBean) {
            return AikiBeansGameStd.invokeMethodGameProgressionBean(_instance, _method, _args);
        }
        if (instance_ instanceof PokemonPlayerBean) {
            return AikiBeansGameStd.invokeMethodPokemonPlayerBean(_instance, _method, _args);
        }
        if (instance_ instanceof GeneralHelpBean) {
            return AikiBeansHelpStd.invokeMethodGeneralHelpBean(_instance, _method, _args);
        }
        if (instance_ instanceof LangsBean) {
            return AikiBeansHelpStd.invokeMethodLangsBean(_instance, _method, _args);
        }
        if (instance_ instanceof BerryBean) {
            return AikiBeansItemsStd.invokeMethodBerryBean(_instance, _method, _args);
        }
        if (instance_ instanceof BoostBean) {
            return AikiBeansItemsStd.invokeMethodBoostBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolvingItemBean) {
            return AikiBeansItemsStd.invokeMethodEvolvingItemBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolvingStoneBean) {
            return AikiBeansItemsStd.invokeMethodEvolvingStoneBean(_instance, _method, _args);
        }
        if (instance_ instanceof FossilBean) {
            return AikiBeansItemsStd.invokeMethodFossilBean(_instance, _method, _args);
        }
        if (instance_ instanceof HealingPpBean) {
            return AikiBeansItemsStd.invokeMethodHealingPpBean(_instance, _method, _args);
        }
        if (instance_ instanceof HealingStatusBean) {
            return AikiBeansItemsStd.invokeMethodHealingStatusBean(_instance, _method, _args);
        }
        if (instance_ instanceof HealingItemBean) {
            return AikiBeansItemsStd.invokeMethodHealingItemBean(_instance, _method, _args);
        }
        if (instance_ instanceof ItemForBattleBean) {
            return AikiBeansItemsStd.invokeMethodItemForBattleBean(_instance, _method, _args);
        }
        if (instance_ instanceof ItemsBean) {
            return AikiBeansItemsStd.invokeMethodItemsBean(_instance, _method, _args);
        }
        if (instance_ instanceof ItemBean) {
            return AikiBeansItemsStd.invokeMethodItemBean(_instance, _method, _args);
        }
        if (instance_ instanceof AllyBean) {
            return AikiBeansMapCharactersStd.invokeMethodAllyBean(_instance, _method, _args);
        }
        if (instance_ instanceof DealerBean) {
            return AikiBeansMapCharactersStd.invokeMethodDealerBean(_instance, _method, _args);
        }
        if (instance_ instanceof SellerBean) {
            return AikiBeansMapCharactersStd.invokeMethodSellerBean(_instance, _method, _args);
        }
        if (instance_ instanceof TrainerBean) {
            return AikiBeansMapCharactersStd.invokeMethodTrainerBean(_instance, _method, _args);
        }
        if (instance_ instanceof AreaBean) {
            return AikiBeansMapElementsStd.invokeMethodAreaBean(_instance, _method, _args);
        }
        if (instance_ instanceof LegendaryPokemonBean) {
            return AikiBeansMapElementsStd.invokeMethodLegendaryPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof MapBean) {
            return AikiBeansMapStd.invokeMethodMapBean(_instance, _method, _args);
        }
        if (instance_ instanceof MapLevelBean) {
            return AikiBeansMapStd.invokeMethodMapLevelBean(_instance, _method, _args);
        }
        if (instance_ instanceof PokemonTeamBean) {
            return AikiBeansMapPokemonStd.invokeMethodPokemonTeamBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectBatonPassBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectBatonPassBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectCloneBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectCloneBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectCommonStatisticsBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectCommonStatisticsBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectCopyMoveBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectCopyMoveBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectCounterAttackBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectCounterAttackBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectDamageBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectDamageBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectGlobalBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectGlobalBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectInvokeBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectInvokeBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectMultSufferedMovePowerBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectMultSufferedMovePowerBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectMultUsedMovePowerBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectMultUsedMovePowerBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectProtectFromTypesBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectProtectFromTypesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectRestrictionBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectRestrictionBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectStatisticBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectStatisticBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectStatusBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectStatusBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectSwitchAbilitiesBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectSwitchAbilitiesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectSwitchItemsBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectSwitchItemsBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectSwitchMoveTypesBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectSwitchMoveTypesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectSwitchPointViewBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectSwitchPointViewBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectSwitchTypesBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectSwitchTypesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectTeamBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectTeamBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectTeamWhileSendFoeBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectTeamWhileSendFoeBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectUnprotectFromTypesBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectUnprotectFromTypesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EffectBean) {
            return AikiBeansMovesEffectsStd.invokeMethodEffectBean(_instance, _method, _args);
        }
        if (instance_ instanceof MoveBean) {
            return AikiBeansMovesStd.invokeMethodMoveBean(_instance, _method, _args);
        }
        if (instance_ instanceof MoveLineBean) {
            return AikiBeansMovesStd.invokeMethodMoveLineBean(_instance, _method, _args);
        }
        if (instance_ instanceof MovesBean) {
            return AikiBeansMovesStd.invokeMethodMovesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolutionItemBean) {
            return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionItemBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolutionMoveBean) {
            return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionMoveBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolutionStoneBean) {
            return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionStoneBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolutionTeamBean) {
            return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionTeamBean(_instance, _method, _args);
        }
        if (instance_ instanceof EvolutionBean) {
            return AikiBeansPokemonEvolutionsStd.invokeMethodEvolutionBean(_instance, _method, _args);
        }
        if (instance_ instanceof PokedexBean) {
            return AikiBeansPokemonStd.invokeMethodPokedexBean(_instance, _method, _args);
        }
        if (instance_ instanceof PokemonBean) {
            return AikiBeansPokemonStd.invokeMethodPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof AddPokemonBean) {
            return AikiBeansSimulationStd.invokeMethodAddPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof EditPokemonBean) {
            return AikiBeansSimulationStd.invokeMethodEditPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof EditPokemonMovesBean) {
            return AikiBeansSimulationStd.invokeMethodEditPokemonMovesBean(_instance, _method, _args);
        }
        if (instance_ instanceof EditTrainerPokemonBean) {
            return AikiBeansSimulationStd.invokeMethodEditTrainerPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof SelectAbilityBean) {
            return AikiBeansSimulationStd.invokeMethodSelectAbilityBean(_instance, _method, _args);
        }
        if (instance_ instanceof SelectItemBean) {
            return AikiBeansSimulationStd.invokeMethodSelectItemBean(_instance, _method, _args);
        }
        if (instance_ instanceof SelectPokemonBean) {
            return AikiBeansSimulationStd.invokeMethodSelectPokemonBean(_instance, _method, _args);
        }
        if (instance_ instanceof SimulationBean) {
            return AikiBeansSimulationStd.invokeMethodSimulationBean(_instance, _method, _args);
        }
        if (instance_ instanceof SimulationLevelBean) {
            return AikiBeansSimulationStd.invokeMethodSimulationLevelBean(_instance, _method, _args);
        }
        if (instance_ instanceof SolutionBean) {
            return AikiBeansSolutionStd.invokeMethodSolutionBean(_instance, _method, _args);
        }
        if (instance_ instanceof StatusBean) {
            return AikiBeansStatusStd.invokeMethodStatusBean(_instance, _method, _args);
        }
        if (instance_ instanceof StatusSetBean) {
            return AikiBeansStatusStd.invokeMethodStatusSetBean(_instance, _method, _args);
        }
        return new ResultErrorStd();
    }

    @Override
    public ResultErrorStd getOtherResultBean(ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        String name_ = _method.getName();
        if (StringUtil.quickEq(name_,AikiBeansAbilitiesStd.TYPE_ABILITIES_BEAN)) {
            AbilitiesBean bean_ = new AbilitiesBean();
            bean_.setClassName(AikiBeansAbilitiesStd.TYPE_ABILITIES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansAbilitiesStd.TYPE_ABILITY_BEAN)) {
            AbilityBean bean_ = new AbilityBean();
            bean_.setClassName(AikiBeansAbilitiesStd.TYPE_ABILITY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEffectsStd.TYPE_COMBOS_BEAN)) {
            CombosBean bean_ = new CombosBean();
            bean_.setClassName(AikiBeansEffectsStd.TYPE_COMBOS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEffectsStd.TYPE_EFFECT_COMBO_BEAN)) {
            EffectComboBean bean_ = new EffectComboBean();
            bean_.setClassName(AikiBeansEffectsStd.TYPE_EFFECT_COMBO_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEffectsStd.TYPE_EFFECT_WHILE_SENDING_BEAN)) {
            EffectWhileSendingBean bean_ = new EffectWhileSendingBean();
            bean_.setClassName(AikiBeansEffectsStd.TYPE_EFFECT_WHILE_SENDING_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN)) {
            EffectEndRoundBean bean_ = new EffectEndRoundBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_FOE_BEAN)) {
            EffectEndRoundFoeBean bean_ = new EffectEndRoundFoeBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_FOE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_GLOBAL_BEAN)) {
            EffectEndRoundGlobalBean bean_ = new EffectEndRoundGlobalBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_GLOBAL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN)) {
            EffectEndRoundIndividualBean bean_ = new EffectEndRoundIndividualBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_INDIVIDUAL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN)) {
            EffectEndRoundMultiRelationBean bean_ = new EffectEndRoundMultiRelationBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_MULTI_RELATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN)) {
            EffectEndRoundPositionRelationBean bean_ = new EffectEndRoundPositionRelationBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_RELATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN)) {
            EffectEndRoundPositionTargetBean bean_ = new EffectEndRoundPositionTargetBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_POSITION_TARGET_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN)) {
            EffectEndRoundSingleRelationBean bean_ = new EffectEndRoundSingleRelationBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_SINGLE_RELATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_SINGLE_STATUS_BEAN)) {
            EffectEndRoundSingleStatusBean bean_ = new EffectEndRoundSingleStatusBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_SINGLE_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN)) {
            EffectEndRoundStatusBean bean_ = new EffectEndRoundStatusBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN)) {
            EffectEndRoundStatusRelationBean bean_ = new EffectEndRoundStatusRelationBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_STATUS_RELATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_TEAM_BEAN)) {
            EffectEndRoundTeamBean bean_ = new EffectEndRoundTeamBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_EFFECT_END_ROUND_TEAM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansEndroundStd.TYPE_END_ROUND_BEAN)) {
            EndRoundBean bean_ = new EndRoundBean();
            bean_.setClassName(AikiBeansEndroundStd.TYPE_END_ROUND_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHT_BEAN)) {
            FightBean bean_ = new FightBean();
            bean_.setClassName(AikiBeansFightStd.TYPE_FIGHT_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHT_CALCULATION_BEAN)) {
            FightCalculationBean bean_ = new FightCalculationBean();
            bean_.setClassName(AikiBeansFightStd.TYPE_FIGHT_CALCULATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_FIGHTER_BEAN)) {
            FighterBean bean_ = new FighterBean();
            bean_.setClassName(AikiBeansFightStd.TYPE_FIGHTER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansFightStd.TYPE_TEAM_BEAN)) {
            TeamBean bean_ = new TeamBean();
            bean_.setClassName(AikiBeansFightStd.TYPE_TEAM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansGameStd.TYPE_DIFFICULTY_BEAN)) {
            DifficultyBean bean_ = new DifficultyBean();
            bean_.setClassName(AikiBeansGameStd.TYPE_DIFFICULTY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansGameStd.TYPE_GAME_PROGRESSION_BEAN)) {
            GameProgressionBean bean_ = new GameProgressionBean();
            bean_.setClassName(AikiBeansGameStd.TYPE_GAME_PROGRESSION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansGameStd.TYPE_POKEMON_PLAYER_BEAN)) {
            PokemonPlayerBean bean_ = new PokemonPlayerBean();
            bean_.setClassName(AikiBeansGameStd.TYPE_POKEMON_PLAYER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansHelpStd.TYPE_FIGHT_HELP_BEAN)) {
            FightHelpBean bean_ = new FightHelpBean();
            bean_.setClassName(AikiBeansHelpStd.TYPE_FIGHT_HELP_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansHelpStd.TYPE_GENERAL_HELP_BEAN)) {
            GeneralHelpBean bean_ = new GeneralHelpBean();
            bean_.setClassName(AikiBeansHelpStd.TYPE_GENERAL_HELP_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansHelpStd.TYPE_LANGS_BEAN)) {
            LangsBean bean_ = new LangsBean();
            bean_.setClassName(AikiBeansHelpStd.TYPE_LANGS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_BALL_BEAN)) {
            BallBean bean_ = new BallBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_BALL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_BERRY_BEAN)) {
            BerryBean bean_ = new BerryBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_BERRY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_BOOST_BEAN)) {
            BoostBean bean_ = new BoostBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_BOOST_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsEffectsStd.TYPE_EFFECT_END_ROUND_ITEM_BEAN)) {
            EffectEndRoundItemBean bean_ = new EffectEndRoundItemBean();
            bean_.setClassName(AikiBeansItemsEffectsStd.TYPE_EFFECT_END_ROUND_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_EVOLVING_ITEM_BEAN)) {
            EvolvingItemBean bean_ = new EvolvingItemBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_EVOLVING_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_EVOLVING_STONE_BEAN)) {
            EvolvingStoneBean bean_ = new EvolvingStoneBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_EVOLVING_STONE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_FOSSIL_BEAN)) {
            FossilBean bean_ = new FossilBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_FOSSIL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_HEALING_HP_BEAN)) {
            HealingHpBean bean_ = new HealingHpBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_HEALING_HP_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_HEALING_HP_STATUS_BEAN)) {
            HealingStatusBean bean_ = new HealingStatusBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN)) {
            HealingItemBean bean_ = new HealingItemBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_HEALING_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_HEALING_PP_BEAN)) {
            HealingPpBean bean_ = new HealingPpBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_HEALING_PP_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN)) {
            HealingStatusBean bean_ = new HealingStatusBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_HEALING_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_ITEM_BEAN)) {
            ItemBean bean_ = new SellingItemBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_ITEM_FOR_BATTLE_BEAN)) {
            ItemForBattleBean bean_ = new ItemForBattleBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_ITEM_FOR_BATTLE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_ITEMS_BEAN)) {
            ItemsBean bean_ = new ItemsBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_ITEMS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_REPEL_BEAN)) {
            RepelBean bean_ = new RepelBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_REPEL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansItemsStd.TYPE_SELLING_ITEM_BEAN)) {
            SellingItemBean bean_ = new SellingItemBean();
            bean_.setClassName(AikiBeansItemsStd.TYPE_SELLING_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_ALLY_BEAN)) {
            AllyBean bean_ = new AllyBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_ALLY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_DEALER_BEAN)) {
            DealerBean bean_ = new DealerBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_DEALER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_DUAL_FIGHT_BEAN)) {
            DualFightBean bean_ = new DualFightBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_DUAL_FIGHT_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_SELLER_BEAN)) {
            SellerBean bean_ = new SellerBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_SELLER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_TRAINER_BEAN)) {
            TrainerBean bean_ = new TrainerBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_TRAINER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapCharactersStd.TYPE_TRAINER_ONE_FIGHT_BEAN)) {
            TrainerOneFightBean bean_ = new TrainerOneFightBean();
            bean_.setClassName(AikiBeansMapCharactersStd.TYPE_TRAINER_ONE_FIGHT_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapElementsStd.TYPE_AREA_BEAN)) {
            AreaBean bean_ = new AreaBean();
            bean_.setClassName(AikiBeansMapElementsStd.TYPE_AREA_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapElementsStd.TYPE_LEGENDARY_POKEMON_BEAN)) {
            LegendaryPokemonBean bean_ = new LegendaryPokemonBean();
            bean_.setClassName(AikiBeansMapElementsStd.TYPE_LEGENDARY_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapStd.TYPE_MAP_BEAN)) {
            MapBean bean_ = new MapBean();
            bean_.setClassName(AikiBeansMapStd.TYPE_MAP_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapStd.TYPE_MAP_LEVEL_BEAN)) {
            MapLevelBean bean_ = new MapLevelBean();
            bean_.setClassName(AikiBeansMapStd.TYPE_MAP_LEVEL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMapPokemonStd.TYPE_POKEMON_TEAM_BEAN)) {
            PokemonTeamBean bean_ = new PokemonTeamBean();
            bean_.setClassName(AikiBeansMapPokemonStd.TYPE_POKEMON_TEAM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_ALLY_BEAN)) {
            EffectAllyBean bean_ = new EffectAllyBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_ALLY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_BATON_PASS_BEAN)) {
            EffectBatonPassBean bean_ = new EffectBatonPassBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_BATON_PASS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN)) {
            EffectBean bean_ = new EffectBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_CLONE_BEAN)) {
            EffectCloneBean bean_ = new EffectCloneBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_CLONE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_COMMON_STATISTICS_BEAN)) {
            EffectCommonStatisticsBean bean_ = new EffectCommonStatisticsBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_COMMON_STATISTICS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_FIGHTER_BEAN)) {
            EffectCopyFighterBean bean_ = new EffectCopyFighterBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_FIGHTER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_MOVE_BEAN)) {
            EffectCopyMoveBean bean_ = new EffectCopyMoveBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_COPY_MOVE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_COUNTER_ATTACK_BEAN)) {
            EffectCounterAttackBean bean_ = new EffectCounterAttackBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_COUNTER_ATTACK_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_BEAN)) {
            EffectDamageBean bean_ = new EffectDamageBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_RATE_BEAN)) {
            EffectDamageRateBean bean_ = new EffectDamageRateBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_DAMAGE_RATE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_END_ROUND_MOVE_BEAN)) {
            EffectEndRoundMoveBean bean_ = new EffectEndRoundMoveBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_END_ROUND_MOVE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_FULL_HP_RATE_BEAN)) {
            EffectFullHpRateBean bean_ = new EffectFullHpRateBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_FULL_HP_RATE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_GLOBAL_BEAN)) {
            EffectGlobalBean bean_ = new EffectGlobalBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_GLOBAL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_INVOKE_BEAN)) {
            EffectInvokeBean bean_ = new EffectInvokeBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_INVOKE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN)) {
            EffectMultSufferedMovePowerBean bean_ = new EffectMultSufferedMovePowerBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_SUFFERED_MOVE_POWER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN)) {
            EffectMultUsedMovePowerBean bean_ = new EffectMultUsedMovePowerBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_MULT_USED_MOVE_POWER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_ORDER_BEAN)) {
            EffectOrderBean bean_ = new EffectOrderBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_ORDER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN)) {
            EffectProtectFromTypesBean bean_ = new EffectProtectFromTypesBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECT_FROM_TYPES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECTION_BEAN)) {
            EffectProtectionBean bean_ = new EffectProtectionBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_PROTECTION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_REMAINED_HP_RATE_BEAN)) {
            EffectRemainedHpRateBean bean_ = new EffectRemainedHpRateBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_REMAINED_HP_RATE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_RESTRICTION_BEAN)) {
            EffectRestrictionBean bean_ = new EffectRestrictionBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_RESTRICTION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_STATISTIC_BEAN)) {
            EffectStatisticBean bean_ = new EffectStatisticBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_STATISTIC_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_STATUS_BEAN)) {
            EffectStatusBean bean_ = new EffectStatusBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ABILITIES_BEAN)) {
            EffectSwitchAbilitiesBean bean_ = new EffectSwitchAbilitiesBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ABILITIES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ITEMS_BEAN)) {
            EffectSwitchItemsBean bean_ = new EffectSwitchItemsBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_ITEMS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN)) {
            EffectSwitchMoveTypesBean bean_ = new EffectSwitchMoveTypesBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_MOVE_TYPES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN)) {
            EffectSwitchPointViewBean bean_ = new EffectSwitchPointViewBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_POINT_VIEW_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_TYPES_BEAN)) {
            EffectSwitchTypesBean bean_ = new EffectSwitchTypesBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_SWITCH_TYPES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_BEAN)) {
            EffectTeamBean bean_ = new EffectTeamBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN)) {
            EffectTeamWhileSendFoeBean bean_ = new EffectTeamWhileSendFoeBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_TEAM_WHILE_SEND_FOE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN)) {
            EffectUnprotectFromTypesBean bean_ = new EffectUnprotectFromTypesBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_UNPROTECT_FROM_TYPES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_VAR_P_P_BEAN)) {
            EffectVarPPBean bean_ = new EffectVarPPBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_VAR_P_P_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesEffectsStd.TYPE_EFFECT_WIN_MONEY_BEAN)) {
            EffectWinMoneyBean bean_ = new EffectWinMoneyBean();
            bean_.setClassName(AikiBeansMovesEffectsStd.TYPE_EFFECT_WIN_MONEY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesStd.TYPE_MOVE_BEAN)) {
            MoveBean bean_ = new MoveBean();
            bean_.setClassName(AikiBeansMovesStd.TYPE_MOVE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesStd.TYPE_MOVE_LINE_BEAN)) {
            MoveLineBean bean_ = new MoveLineBean();
            bean_.setClassName(AikiBeansMovesStd.TYPE_MOVE_LINE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansMovesStd.TYPE_MOVES_BEAN)) {
            MovesBean bean_ = new MovesBean();
            bean_.setClassName(AikiBeansMovesStd.TYPE_MOVES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN)) {
            EvolutionBean bean_ = new EvolutionBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_HAPPINESS_BEAN)) {
            EvolutionHappinessBean bean_ = new EvolutionHappinessBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_HAPPINESS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_ITEM_BEAN)) {
            EvolutionItemBean bean_ = new EvolutionItemBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_BEAN)) {
            EvolutionLevelBean bean_ = new EvolutionLevelBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_GENDER_BEAN)) {
            EvolutionLevelGenderBean bean_ = new EvolutionLevelGenderBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_LEVEL_GENDER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_BEAN)) {
            EvolutionMoveBean bean_ = new EvolutionMoveBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_TYPE_BEAN)) {
            EvolutionMoveTypeBean bean_ = new EvolutionMoveTypeBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_MOVE_TYPE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_BEAN)) {
            EvolutionStoneBean bean_ = new EvolutionStoneBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_GENDER_BEAN)) {
            EvolutionStoneGenderBean bean_ = new EvolutionStoneGenderBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_STONE_GENDER_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_TEAM_BEAN)) {
            EvolutionTeamBean bean_ = new EvolutionTeamBean();
            bean_.setClassName(AikiBeansPokemonEvolutionsStd.TYPE_EVOLUTION_TEAM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonStd.TYPE_POKEDEX_BEAN)) {
            PokedexBean bean_ = new PokedexBean();
            bean_.setClassName(AikiBeansPokemonStd.TYPE_POKEDEX_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansPokemonStd.TYPE_POKEMON_BEAN)) {
            PokemonBean bean_ = new PokemonBean();
            bean_.setClassName(AikiBeansPokemonStd.TYPE_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_ADD_POKEMON_BEAN)) {
            AddPokemonBean bean_ = new AddPokemonBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_ADD_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_EDIT_POKEMON_BEAN)) {
            EditPokemonBean bean_ = new EditPokemonBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_EDIT_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_EDIT_POKEMON_MOVES_BEAN)) {
            EditPokemonMovesBean bean_ = new EditPokemonMovesBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_EDIT_POKEMON_MOVES_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_EDIT_TRAINER_POKEMON_BEAN)) {
            EditTrainerPokemonBean bean_ = new EditTrainerPokemonBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_EDIT_TRAINER_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_SELECT_ABILITY_BEAN)) {
            SelectAbilityBean bean_ = new SelectAbilityBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_SELECT_ABILITY_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_SELECT_ITEM_BEAN)) {
            SelectItemBean bean_ = new SelectItemBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_SELECT_ITEM_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_SELECT_POKEMON_BEAN)) {
            SelectPokemonBean bean_ = new SelectPokemonBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_SELECT_POKEMON_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_SIMULATION_BEAN)) {
            SimulationBean bean_ = new SimulationBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_SIMULATION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSimulationStd.TYPE_SIMULATION_LEVEL_BEAN)) {
            SimulationLevelBean bean_ = new SimulationLevelBean();
            bean_.setClassName(AikiBeansSimulationStd.TYPE_SIMULATION_LEVEL_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansSolutionStd.TYPE_SOLUTION_BEAN)) {
            SolutionBean bean_ = new SolutionBean();
            bean_.setClassName(AikiBeansSolutionStd.TYPE_SOLUTION_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansStatusStd.TYPE_STATUS_BEAN)) {
            StatusBean bean_ = new StatusBean();
            bean_.setClassName(AikiBeansStatusStd.TYPE_STATUS_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansStatusStd.TYPE_STATUS_SET_BEAN)) {
            StatusSetBean bean_ = new StatusSetBean();
            bean_.setClassName(AikiBeansStatusStd.TYPE_STATUS_SET_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        if (StringUtil.quickEq(name_,AikiBeansStd.TYPE_WELCOME_BEAN)) {
            WelcomeBean bean_ = new WelcomeBean();
            bean_.setClassName(AikiBeansStd.TYPE_WELCOME_BEAN);
            res_.setResult(new PokemonBeanStruct(bean_));
            return res_;
        }
        return res_;
    }

    @Override
    public ResultErrorStd getOtherName(Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        Struct arg_ = new StringStruct(StringUtil.nullToEmpty(processString(new Argument(_instance))));
        res_.setResult(arg_);
        return res_;
    }

    public static ResultErrorStd invokeMethodActivityOfMove(ClassMethodId _method, ActivityOfMove _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,IS_ENABLED)) {
            res_.setResult(BooleanStruct.of(_inst.isEnabled()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_INCREMENT_COUNT)) {
            res_.setResult(BooleanStruct.of(_inst.isIncrementCount()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NB_TURN)) {
            res_.setResult(new IntStruct(_inst.getNbTurn()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveTarget(ClassMethodId _method, MoveTarget _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TARGET)) {
            res_.setResult(new TargetCoordsStruct(_inst.getTarget(),PokemonStandards.TYPE_TARGET_COORDS));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTargetCoords(ClassMethodId _method, TargetCoords _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_POSITION)) {
            res_.setResult(new IntStruct(_inst.getPosition()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodUsesOfMove(ClassMethodId _method, UsesOfMove _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_CURRENT)) {
            res_.setResult(new IntStruct(_inst.getCurrent()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MAX)) {
            res_.setResult(new IntStruct(_inst.getMax()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodCopiedMove(ClassMethodId _method, CopiedMove _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PP)) {
            res_.setResult(new IntStruct(_inst.getPp()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodMoveTeamPosition(ClassMethodId _method, MoveTeamPosition _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAffectedMove(ClassMethodId _method, AffectedMove _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ACTIVITY)) {
            res_.setResult(new ActivityOfMoveStruct(_inst.getActivity(),PokemonStandards.TYPE_ACTIVITY_OF_MOVE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodStacksOfUses(ClassMethodId _method, StacksOfUses _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_NB_ROUNDS)) {
            res_.setResult(new IntStruct(_inst.getNbRounds()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_FIRST_STACKED)) {
            res_.setResult(BooleanStruct.of(_inst.isFirstStacked()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_LAST_STACKED)) {
            res_.setResult(BooleanStruct.of(_inst.isLastStacked()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAnticipation(ClassMethodId _method, Anticipation _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_TARGET_POSITION)) {
            res_.setResult(new TargetCoordsStruct(_inst.getTargetPosition(),PokemonStandards.TYPE_TARGET_COORDS));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_DAMAGE)) {
            res_.setResult(new RateStruct(_inst.getDamage(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_INCREMENTING)) {
            res_.setResult(BooleanStruct.of(_inst.isIncrementing()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_NB_ROUNDS)) {
            res_.setResult(new IntStruct(_inst.getNbRounds()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodRate(ClassMethodId _method, Rate _rate, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,IS_ZERO)) {
            res_.setResult(BooleanStruct.of(_rate.isZero()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,IS_ZERO_OR_GT)) {
            res_.setResult(BooleanStruct.of(_rate.isZeroOrGt()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,ABS_NB)) {
            res_.setResult(new RateStruct(_rate.absNb(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTypeDamageBoost(ClassMethodId _method, TypeDamageBoost _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_BOOST)) {
            res_.setResult(new RateStruct(_inst.getBoost(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodEfficiencyRate(ClassMethodId _method, EfficiencyRate _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_EFF)) {
            res_.setResult(new RateStruct(_inst.getEff(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_HP_RATE)) {
            res_.setResult(new RateStruct(_inst.getHpRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodBoostHpRate(ClassMethodId _method, BoostHpRate _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_HP_RATE)) {
            res_.setResult(new RateStruct(_inst.getHpRate(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_BOOST)) {
            res_.setResult(new IntStruct(_inst.getBoost()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPkTrainer(ClassMethodId _method, PkTrainer _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new IntStruct(_inst.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(_inst.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVES)) {
            res_.setResult(getStringArray(_inst.getMoves()));
            return res_;
        }
        return PokemonStandards.invokeMethodPokemon(_method, _inst, _args);
    }
    public static ResultErrorStd invokeMethodPokemon(ClassMethodId _method, Pokemon _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(_inst.getItem()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodAreaApparition(ClassMethodId _method, AreaApparition _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_AVG_NB_STEPS)) {
            res_.setResult(new IntStruct(_inst.getAvgNbSteps()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_WILD_POKEMON)) {
            res_.setResult(getWildPkArray(_inst.getWildPokemon()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_WILD_POKEMON_FISHING)) {
            res_.setResult(getWildPkArray(_inst.getWildPokemonFishing()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodWildPk(ClassMethodId _method, WildPk _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new IntStruct(_inst.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(_inst.getItem()));
            return res_;
        }
        return PokemonStandards.invokeMethodPokemon(_method, _inst, _args);
    }
    public static ResultErrorStd invokeMethodPlace(ClassMethodId _method, Place _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_NAME)) {
            res_.setResult(new StringStruct(_inst.getName()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTypesDuo(ClassMethodId _method, TypesDuo _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_DAMAGE_TYPE)) {
            res_.setResult(new StringStruct(_inst.getDamageType()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_POKEMON_TYPE)) {
            res_.setResult(new StringStruct(_inst.getPokemonType()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodCategoryMult(ClassMethodId _method, CategoryMult _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_CATEGORY)) {
            res_.setResult(new StringStruct(_inst.getCategory()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MULT)) {
            res_.setResult(new IntStruct(_inst.getMult()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodLevelMove(ClassMethodId _method, LevelMove _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_LEVEL)) {
            res_.setResult(new IntStruct(_inst.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MOVE)) {
            res_.setResult(new StringStruct(_inst.getMove()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokemonPlayer(ClassMethodId _method, PokemonPlayer _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_WON_EXP_SINCE_LAST_LEVEL)) {
            res_.setResult(new RateStruct(_inst.getWonExpSinceLastLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_ITEM)) {
            res_.setResult(new StringStruct(_inst.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_HAPPINESS)) {
            res_.setResult(new IntStruct(_inst.getHappiness()));
            return res_;
        }
        return PokemonStandards.invokeMethodPokemon(_method, _inst, _args);
    }
    public static ResultErrorStd invokeMethodEffectPartnerStatus(ClassMethodId _method, EffectPartnerStatus _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_RESTORED_HP_RATE_LOVED_ALLY)) {
            res_.setResult(new RateStruct(_inst.getRestoredHpRateLovedAlly(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_WEDDING_ALLY)) {
            res_.setResult(BooleanStruct.of(_inst.getWeddingAlly()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_MULT_DAMAGE_AGAINST_FOE)) {
            res_.setResult(new RateStruct(_inst.getMultDamageAgainstFoe(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodTrainerPlaceNames(ClassMethodId _method, TrainerPlaceNames _inst, Struct... _args) {
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_TRAINER)) {
            res_.setResult(new StringStruct(_inst.getTrainer()));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_PLACE)) {
            res_.setResult(new StringStruct(_inst.getPlace()));
            return res_;
        }
        return res_;
    }
    public static ArrayStruct getBigByAn(AbsMap<String, ByteTreeMap<Anticipation>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ByteTreeMap<Anticipation>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getByAn(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigBySt(AbsMap<String, ByteTreeMap<StacksOfUses>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ByteTreeMap<StacksOfUses>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getBySt(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getByAn(AbsMap<Byte, Anticipation> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, Anticipation> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new AnticipationStruct(e.getValue(),TYPE_ANTICIPATION));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBySt(AbsMap<Byte, StacksOfUses> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, StacksOfUses> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new StacksOfUsesStruct(e.getValue(),TYPE_STACKS_OF_USES));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigNatMapLs(AbsMap<String, EqList<TeamPosition>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, EqList<TeamPosition>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getTeamPos(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getBigNatMap(AbsMap<String, AbsBasicTreeMap<Rate, Rate>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, AbsBasicTreeMap<Rate, Rate>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getRateRate(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getBigNatMapSta(AbsMap<String, TreeMap<Statistic, Byte>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, TreeMap<Statistic, Byte>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getStaByte(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrAct(AbsMap<StringList, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StringList, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,getStringArray(e.getKey()),new ActivityOfMoveStruct(e.getValue(),TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaBoost(AbsMap<Statistic, BoostHpRate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Statistic, BoostHpRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new BoostHpRateStruct(e.getValue(),TYPE_BOOST_HP_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTp(AbsMap<MoveTeamPosition, AffectedMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, AffectedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new AffectedMoveStruct(e.getValue(),TYPE_AFFECTED_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvAm(AbsMap<MoveTeamPosition, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new ActivityOfMoveStruct(e.getValue(),TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTpNb(AbsMap<MoveTeamPosition, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, Short> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new ShortStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcMvTpBool(AbsMap<MoveTeamPosition, Boolean> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, Boolean> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),BooleanStruct.of(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcStr(AbsMap<MiniMapCoords, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MiniMapCoords, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPtStr(AbsMap<Point, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Point, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(""),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTpStr(AbsMap<MoveTeamPosition, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTeamPosition, String> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTeamPositionStruct(e.getKey(),TYPE_MOVE_TEAM_POSITION),new StringStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getCpStr(AbsMap<String, CopiedMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, CopiedMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new CopiedMoveStruct(e.getValue(),TYPE_COPIED_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMultPowStr(AbsMap<String, MultPowerMoves> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, MultPowerMoves> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new MultPowerMovesStruct(e.getValue(),AikiBeansFacadeFightStd.TYPE_MULT_POWER_MOVES));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSuffCatStr(AbsMap<String, SufferedDamageCategory> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, SufferedDamageCategory> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new SufferedDamageCategoryStruct(e.getValue(),AikiBeansFacadeFightStd.TYPE_SUFFERED_DAMAGE_CATEGORY));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getEffRateStr(AbsMap<String, EfficiencyRate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, EfficiencyRate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new EfficiencyRateStruct(e.getValue(),TYPE_EFFICIENCY_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getUsesStr(AbsMap<String, UsesOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, UsesOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new UsesOfMoveStruct(e.getValue(),TYPE_USES_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcByteMap(AbsMap<StatisticPokemon, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticPokemon, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticTypeByteMap(AbsMap<StatisticType, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticType, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticCategoryByteMap(AbsMap<StatisticCategory, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticCategory, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticStatusByteMap(AbsMap<StatisticStatus, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticStatus, Byte> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StatisticStatusStruct(e.getKey(),OBJECT),new ByteStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getCatMultRateMap(AbsMap<CategoryMult, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<CategoryMult, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new CategoryMultStruct(e.getKey(),TYPE_CATEGORY_MULT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStatisticCategoryRateMap(AbsMap<StatisticCategory, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticCategory, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWcRateMap(AbsMap<StatisticType, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<StatisticType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWeatherTypeRateMap(AbsMap<WeatherType, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<WeatherType, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrListStaList(AbsMap<String, EnumList<Statistic>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, EnumList<Statistic>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getSta(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrListStrList(AbsMap<String, CustList<StringList>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, CustList<StringList>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getStrList(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getShStrList(AbsMap<Short, StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Short, StringList> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),getStringArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTpDuoRate(AbsMap<TypesDuo, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<TypesDuo, Rate> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new TypesDuoStruct(e.getKey(),TYPE_TYPES_DUO),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getIntIntMap(AbsMap<Integer, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Integer, Integer> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IntStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getByteBytes(AbsMap<Byte, Bytes> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, Bytes> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),getByteArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrInts(AbsMap<String, Ints> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, Ints> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),getIntArray(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTars(AbsMap<MoveTarget, MoveTarget> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<MoveTarget, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new MoveTargetStruct(e.getKey(),TYPE_MOVE_TARGET),new MoveTargetStruct(e.getValue(),TYPE_MOVE_TARGET));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvTar(AbsMap<Byte, MoveTarget> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<Byte, MoveTarget> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new ByteStruct(e.getKey()),new MoveTargetStruct(e.getValue(),TYPE_MOVE_TARGET));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrTpDam(AbsMap<String, TypeDamageBoost> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, TypeDamageBoost> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new TypeDamageBoostStruct(e.getValue(),TYPE_TYPE_DAMAGE_BOOST));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getActMove(AbsMap<String, ActivityOfMove> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<String, ActivityOfMove> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(e.getKey()),new ActivityOfMoveStruct(e.getValue(),PokemonStandards.TYPE_ACTIVITY_OF_MOVE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlLevWildPkDto(AbsMap<PlaceLevel, CustList<WildPokemonDto>> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (EntryCust<PlaceLevel, CustList<WildPokemonDto>> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new PlaceLevelStruct(e.getKey(),OBJECT),getWildPkDto(e.getValue()));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWildPkDto(CustList<WildPokemonDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_WILD_POKEMON_DTO));
        int j_ = 0;
        for (WildPokemonDto s:_ls) {
            arr_.set(j_,new WildPokemonDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_WILD_POKEMON_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSteDto(CustList<StepDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_STEP_DTO));
        int j_ = 0;
        for (StepDto s:_ls) {
            arr_.set(j_,new StepDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_STEP_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkPlDto(CustList<PokemonPlayerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_PLAYER_DTO));
        int j_ = 0;
        for (PokemonPlayerDto s:_ls) {
            arr_.set(j_,new PokemonPlayerDtoStruct(s, AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_PLAYER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTrDto(CustList<PokemonTrainerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_TRAINER_DTO));
        int j_ = 0;
        for (PokemonTrainerDto s:_ls) {
            arr_.set(j_,new PokemonTrainerDtoStruct(s, AikiBeansFacadeSimulationDtoStd.TYPE_POKEMON_TRAINER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaInf(CustList<StatisticInfo> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeFightStd.TYPE_STATISTIC_INFO));
        int j_ = 0;
        for (StatisticInfo s:_ls) {
            arr_.set(j_,new StatisticInfoStruct(s, AikiBeansFacadeFightStd.TYPE_STATISTIC_INFO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getKeyHyp(CustList<KeyHypothesis> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeFightStd.TYPE_KEY_HYPOTHESIS));
        int j_ = 0;
        for (KeyHypothesis s:_ls) {
            arr_.set(j_,new KeyHypothesisStruct(s, AikiBeansFacadeFightStd.TYPE_KEY_HYPOTHESIS));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStPkPl(CustList<StatisticInfoPkPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeGameDtoStd.TYPE_STATISTIC_INFO_PK_PLAYER));
        int j_ = 0;
        for (StatisticInfoPkPlayer s:_ls) {
            arr_.set(j_,new StatisticInfoPkPlayerStruct(s, AikiBeansFacadeGameDtoStd.TYPE_STATISTIC_INFO_PK_PLAYER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTrPlNa(CustList<TrainerPlaceNames> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_TRAINER_PLACE_NAMES));
        int j_ = 0;
        for (TrainerPlaceNames s:_ls) {
            arr_.set(j_,new TrainerPlaceNamesStruct(s, TYPE_TRAINER_PLACE_NAMES));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getLvMv(CustList<LevelMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LEVEL_MOVE));
        int j_ = 0;
        for (LevelMove s:_ls) {
            arr_.set(j_,new LevelMoveStruct(s, TYPE_LEVEL_MOVE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlTr(CustList<PlaceTrainerDto> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSolutionDtoStd.TYPE_PLACE_TRAINER_DTO));
        int j_ = 0;
        for (PlaceTrainerDto s:_ls) {
            arr_.set(j_,new PlaceTrainerDtoStruct(s, AikiBeansFacadeSolutionDtoStd.TYPE_PLACE_TRAINER_DTO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getTypesDuo(CustList<TypesDuo> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_TYPES_DUO));
        int j_ = 0;
        for (TypesDuo s:_ls) {
            arr_.set(j_,new TypesDuoStruct(s, TYPE_TYPES_DUO));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTeam(CustList<PokemonTeam> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (PokemonTeam s:_ls) {
            arr_.set(j_,new PokemonTeamStruct(s, OBJECT));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSiSa(CustList<StatisticStatus> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int j_ = 0;
        for (StatisticStatus s:_ls) {
            arr_.set(j_,new StatisticStatusStruct(s, OBJECT));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPlInd(CustList<PlaceIndex> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeMapDtoStd.TYPE_PLACE_INDEX));
        int j_ = 0;
        for (PlaceIndex s:_ls) {
            arr_.set(j_,new PlaceIndexStruct(s, AikiBeansFacadeMapDtoStd.TYPE_PLACE_INDEX));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getWildPkArray(CustList<WildPk> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_WILD_PK));
        int j_ = 0;
        for (WildPk s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_WILD_PK));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkPlayerArray(CustList<PokemonPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_POKEMON_PLAYER));
        int j_ = 0;
        for (PokemonPlayer s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_POKEMON_PLAYER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkTrainerArray(CustList<PkTrainer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_PK_TRAINER));
        int j_ = 0;
        for (PkTrainer s:_ls) {
            arr_.set(j_,new PkStruct(s, TYPE_PK_TRAINER));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getPkLine(CustList<PokemonLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_POKEMON_LINE));
        int j_ = 0;
        for (PokemonLine s:_ls) {
            arr_.set(j_,new PkLineStruct(s, AikiBeansFacadeDtoStd.TYPE_POKEMON_LINE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getItLine(CustList<ItemLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_ITEM_LINE));
        int j_ = 0;
        for (ItemLine s:_ls) {
            arr_.set(j_,new ItLineStruct(s, AikiBeansFacadeDtoStd.TYPE_ITEM_LINE));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getMvLine(CustList<MoveLine> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeDtoStd.TYPE_MOVE_LINE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getRdMvLine(CustList<RadioLineMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_RADIO_LINE_MOVE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getSelectLineMove(CustList<SelectLineMove> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_SELECT_LINE_MOVE));
        int j_ = 0;
        for (MoveLine s:_ls) {
            arr_.set(j_,newMoveLine(s));
            j_++;
        }
        return arr_;
    }
    public static Struct newMoveLine(MoveLine _s) {
        if (_s instanceof SelectLineMove) {
            return new MvLineStruct(_s, AikiBeansFacadeSimulationDtoStd.TYPE_SELECT_LINE_MOVE);
        }
        if (_s instanceof RadioLineMove) {
            return new MvLineStruct(_s, AikiBeansFacadeSimulationDtoStd.TYPE_RADIO_LINE_MOVE);
        }
        return new MvLineStruct(_s, AikiBeansFacadeDtoStd.TYPE_MOVE_LINE);
    }
    public static ArrayStruct getEffPartStat(CustList<EffectPartnerStatus> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_EFFECT_PARTNER_STATUS));
        int j_ = 0;
        for (EffectPartnerStatus s:_ls) {
            arr_.set(j_,new EffectPartnerStatusStruct(s, TYPE_EFFECT_PARTNER_STATUS));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getEvLine(AbsMap<Statistic, EvLine> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(AikiBeansFacadeSimulationDtoStd.TYPE_EV_LINE));
        int j_ = 0;
        for (EntryCust<Statistic, EvLine> e:_map.entryList()) {
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new EvLineStruct(e.getValue(),AikiBeansFacadeSimulationDtoStd.TYPE_EV_LINE));
            arr_.set(j_,p_);
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrStr(AbsMap<String, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrRate(AbsMap<String, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrLgInt(AbsMap<String, LgInt> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,LgInt> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new LgIntStruct(e.getValue(),TYPE_LG_INT));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrShort(AbsMap<String, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrInteger(AbsMap<String, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrByte(AbsMap<String, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaByte(AbsMap<Statistic, Byte> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Byte> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ByteStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaShort(AbsMap<Statistic, Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaStr(AbsMap<Statistic, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStaRate(AbsMap<Statistic, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Statistic,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IdStruct(OBJECT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getIntStr(AbsMap<Integer, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Integer,String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new IntStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLgIntRate(AbsMap<LgInt, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<LgInt,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new LgIntStruct(e.getKey(),TYPE_LG_INT),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLongRate(AbsMap<Long, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Long,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new LongStruct(e.getKey()),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getRateRate(AbsMap<Rate, Rate> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Rate,Rate> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new RateStruct(e.getKey(),TYPE_RATE),new RateStruct(e.getValue(),TYPE_RATE));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrBool(AbsMap<String, Boolean> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,Boolean> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),BooleanStruct.of(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getShortStr(AbsMap<Short, String> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Short, String> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),new StringStruct(StringUtil.nullToEmpty(e.getValue())));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getShortInt(AbsMap<Short, Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<Short, Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new ShortStruct(e.getKey()),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrStrList(AbsMap<String, StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String,StringList> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),getStringArray(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getLayers(CustList<Level> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (Level e: _map){
            arr_.set(i_,new IdStruct(OBJECT));
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getEnd(CustList<EndRoundMainElements> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EndRoundMainElements e: _map){
            arr_.set(i_,new IdStruct(OBJECT));
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getSta(CustList<Statistic> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (Statistic e: _map){
            arr_.set(i_,new IdStruct(OBJECT));
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getTeamPos(CustList<TeamPosition> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (TeamPosition e: _map){
            arr_.set(i_,new IdStruct(OBJECT));
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStrList(CustList<StringList> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(), StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (StringList e: _map){
            arr_.set(i_,getStringArray(e));
            i_++;
        }
        return arr_;
    }

    public static ArrayStruct getIntArray(Ints _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(PRIM_INTEGER));
        int j_ = 0;
        for (Integer s:_ls) {
            arr_.set(j_,new IntStruct(s));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getByteArray(Bytes _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(PRIM_BYTE));
        int j_ = 0;
        for (Byte s:_ls) {
            arr_.set(j_,new ByteStruct(s));
            j_++;
        }
        return arr_;
    }

    public static SelectedBoolean getBoolByName(String _env) {
        for (SelectedBoolean e : SelectedBoolean.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return SelectedBoolean.YES_AND_NO;
    }
    public static DifficultyModelLaw getModelByName(String _env) {
        for (DifficultyModelLaw e: DifficultyModelLaw.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return DifficultyModelLaw.UNIFORME;
    }
    public static DifficultyWinPointsFight getDiffWonPtsByName(String _env) {
        for (DifficultyWinPointsFight e: DifficultyWinPointsFight.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return DifficultyWinPointsFight.TRES_FACILE;
    }
    public static EnvironmentType getEnvByName(String _env) {
        for (EnvironmentType e: EnvironmentType.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return EnvironmentType.NOTHING;
    }
    public static Gender getGenderByName(String _env) {
        for (Gender e: Gender.values()) {
            if (StringUtil.quickEq(e.name(), _env)) {
                return e;
            }
        }
        return Gender.NO_GENDER;
    }

    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        ResultErrorStd res_ = getOtherResultBean(id_, args_);
        Struct strBean_ = res_.getResult();
        PokemonBeanStruct str_ = (PokemonBeanStruct) strBean_;
        Bean bean_ = str_.getBean();
        ((WithFacade)bean_).setDataBase(dataBase);
        if (bean_ instanceof WithForms) {
            ((WithForms)bean_).setForms(new StringMapObject());
        }
        bean_.setLanguage(_language);
        return strBean_;
    }

    public void setDataBase(FacadeGame _dataBase){
        dataBase = _dataBase;
    }
}
