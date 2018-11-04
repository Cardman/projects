package aiki.beans.game;
import aiki.beans.PokemonStandards;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.util.BeanLgNames;
import code.maths.Rate;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AikiBeansGameStd {
    public static final String TYPE_DIFFICULTY_BEAN = "aiki.beans.game.DifficultyBean";
    public static final String TYPE_GAME_PROGRESSION_BEAN = "aiki.beans.game.GameProgressionBean";
    public static final String TYPE_POKEMON_PLAYER_BEAN = "aiki.beans.game.PokemonPlayerBean";

    private static final String CHANGE = "change";
    private static final String GET_REMAINING_OTHER_TRAINERS_PLACE_NAME = "getRemainingOtherTrainersPlaceName";
    private static final String GET_IMAGE_POKEMON_FULL = "getImagePokemonFull";
    private static final String GET_TR_POKEMON_FULL = "getTrPokemonFull";
    private static final String GET_IMAGE_POKEMON_NOT_ALL = "getImagePokemonNotAll";
    private static final String GET_TR_POKEMON_NOT_ALL = "getTrPokemonNotAll";
    private static final String GET_IMAGE_POKEMON_PARTIAL_NOT = "getImagePokemonPartialNot";
    private static final String GET_TR_POKEMON_PARTIAL_NOT = "getTrPokemonPartialNot";
    private static final String GET_KEY_POKEMON = "getKeyPokemon";
    private static final String GET_IMAGE_POKEMON_PARTIAL = "getImagePokemonPartial";
    private static final String GET_TR_POKEMON_PARTIAL = "getTrPokemonPartial";
    private static final String GET_EVO = "getEvo";
    private static final String WIN_POINTS_FIGHT = "winPointsFight";
    private static final String DIFF_WINNING_EXP_PTS_FIGHT = "diffWinningExpPtsFight";
    private static final String ALLOW_CATCHING_KO = "allowCatchingKo";
    private static final String ALLOWED_SWITCH_PLACES_END_ROUND = "allowedSwitchPlacesEndRound";
    private static final String WIN_TRAINER_EXP = "winTrainerExp";
    private static final String RATE_WINNING_EXP_PTS_FIGHT = "rateWinningExpPtsFight";
    private static final String END_FIGHT_IF_ONE_TEAM_KO = "endFightIfOneTeamKo";
    private static final String IV_PLAYER = "ivPlayer";
    private static final String IV_FOE = "ivFoe";
    private static final String RATE_WIN_MONEY_BASE = "rateWinMoneyBase";
    private static final String RATE_LOOSE_MONEY_WIN = "rateLooseMoneyWin";
    private static final String RESTORED_MOVES_END_FIGHT = "restoredMovesEndFight";
    private static final String ENABLED_CLOSING = "enabledClosing";
    private static final String RANDOM_WILD_FIGHT = "randomWildFight";
    private static final String STILL_POSSIBLE_FLEE = "stillPossibleFlee";
    private static final String SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "skipLearningMovesWhileNotGrowingLevel";
    private static final String DAMAGE_RATES = "damageRates";
    private static final String DAMAGE_RATE_PLAYER = "damageRatePlayer";
    private static final String DAMAGE_RATE_PLAYER_TABLE = "damageRatePlayerTable";
    private static final String DAMAGE_RATE_LAW_FOE = "damageRateLawFoe";
    private static final String DAMAGE_RATE_FOE_TABLE = "damageRateFoeTable";
    private static final String FINISHED_GAME = "finishedGame";
    private static final String HERO_IMAGE = "heroImage";
    private static final String HERO_IMAGE_OPPOSITE_SEX = "heroImageOppositeSex";
    private static final String END_GAME_IMAGE = "endGameImage";
    private static final String NICKNAME = "nickname";
    private static final String UN_BEATEN_IMPORTANT_TRAINERS = "unBeatenImportantTrainers";
    private static final String BEATEN_IMPORTANT_TRAINERS = "beatenImportantTrainers";
    private static final String REMAINING_OTHER_TRAINER_PLACES = "remainingOtherTrainerPlaces";
    private static final String UN_VISITED_PLACES = "unVisitedPlaces";
    private static final String VISITED_PLACES = "visitedPlaces";
    private static final String NB_REMAINING_NOT_MAX_LEVEL = "nbRemainingNotMaxLevel";
    private static final String NB_REMAINING_NOT_MAX_HAPPINESS = "nbRemainingNotMaxHappiness";
    private static final String NB_REMAINING_EGGS = "nbRemainingEggs";
    private static final String REMAIN_STEPS_REPEL = "remainStepsRepel";
    private static final String MONEY = "money";
    private static final String FULL_FAMILIES_BASE = "fullFamiliesBase";
    private static final String NOT_AT_ALL_FAMILIES_BASE = "notAtAllFamiliesBase";
    private static final String PARTIAL_FAMILIES_BASE_NOT_CAUGHT = "partialFamiliesBaseNotCaught";
    private static final String NAME = "name";
    private static final String IMAGE = "image";
    private static final String EVOLUTIONS = "evolutions";
    private static final String LEVEL = "level";
    private static final String GENDER = "gender";
    private static final String ABILITY = "ability";
    private static final String USED_BALL_CATCHING = "usedBallCatching";
    private static final String ITEM = "item";
    private static final String REMAINING_HP = "remainingHp";
    private static final String REMAINING_HP_PER_CENT = "remainingHpPerCent";
    private static final String FULL_HP = "fullHp";
    private static final String WON_EXP_SINCE_LAST_LEVEL = "wonExpSinceLastLevel";
    private static final String NECESSARY_POINTS_NEXT_LEVEL = "necessaryPointsNextLevel";
    private static final String HAPPINESS = "happiness";
    private static final String NB_STEPS_TEAM_LEAD = "nbStepsTeamLead";
    private static final String TYPES = "types";
    private static final String STATUS = "status";
    private static final String MOVES = "moves";
    private static final String STATISTICS = "statistics";

    public static void build(BeanLgNames _std) {
        buildDifficultyBean(_std);
        buildGameProgressionBean(_std);
        buildPokemonPlayerBean(_std);
    }
    private static void buildDifficultyBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_DIFFICULTY_BEAN, fields_, constructors_, methods_, _std.getBean(), MethodModifier.NORMAL);
        fields_.put(WIN_POINTS_FIGHT,new StandardField(WIN_POINTS_FIGHT,_std.getCustMap(),false,false,type_));
        fields_.put(DIFF_WINNING_EXP_PTS_FIGHT,new StandardField(DIFF_WINNING_EXP_PTS_FIGHT,PokemonStandards.TYPE_DIFFICULTY_WIN_POINTS_FIGHT,false,false,type_));
        fields_.put(ALLOW_CATCHING_KO,new StandardField(ALLOW_CATCHING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ALLOWED_SWITCH_PLACES_END_ROUND,new StandardField(ALLOWED_SWITCH_PLACES_END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(WIN_TRAINER_EXP,new StandardField(WIN_TRAINER_EXP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RATE_WINNING_EXP_PTS_FIGHT,new StandardField(RATE_WINNING_EXP_PTS_FIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(END_FIGHT_IF_ONE_TEAM_KO,new StandardField(END_FIGHT_IF_ONE_TEAM_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(IV_PLAYER,new StandardField(IV_PLAYER,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(IV_FOE,new StandardField(IV_FOE,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(RATE_WIN_MONEY_BASE,new StandardField(RATE_WIN_MONEY_BASE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RATE_LOOSE_MONEY_WIN,new StandardField(RATE_LOOSE_MONEY_WIN,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(RESTORED_MOVES_END_FIGHT,new StandardField(RESTORED_MOVES_END_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(ENABLED_CLOSING,new StandardField(ENABLED_CLOSING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(RANDOM_WILD_FIGHT,new StandardField(RANDOM_WILD_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(STILL_POSSIBLE_FLEE,new StandardField(STILL_POSSIBLE_FLEE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,new StandardField(SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(DAMAGE_RATES,new StandardField(DAMAGE_RATES,_std.getCustMap(),false,false,type_));
        fields_.put(DAMAGE_RATE_PLAYER,new StandardField(DAMAGE_RATE_PLAYER,PokemonStandards.TYPE_DIFFICULTY_MODEL_LAW,false,false,type_));
        fields_.put(DAMAGE_RATE_PLAYER_TABLE,new StandardField(DAMAGE_RATE_PLAYER_TABLE,_std.getCustMap(),false,false,type_));
        fields_.put(DAMAGE_RATE_LAW_FOE,new StandardField(DAMAGE_RATE_LAW_FOE,PokemonStandards.TYPE_DIFFICULTY_MODEL_LAW,false,false,type_));
        fields_.put(DAMAGE_RATE_FOE_TABLE,new StandardField(DAMAGE_RATE_FOE_TABLE,_std.getCustMap(),false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CHANGE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_DIFFICULTY_BEAN, type_);
    }
    private static void buildGameProgressionBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_GAME_PROGRESSION_BEAN, fields_, constructors_, methods_, _std.getBean(), MethodModifier.NORMAL);
        fields_.put(FINISHED_GAME,new StandardField(FINISHED_GAME,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.put(HERO_IMAGE,new StandardField(HERO_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(HERO_IMAGE_OPPOSITE_SEX,new StandardField(HERO_IMAGE_OPPOSITE_SEX,_std.getAliasString(),false,false,type_));
        fields_.put(END_GAME_IMAGE,new StandardField(END_GAME_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(NICKNAME,new StandardField(NICKNAME,_std.getAliasString(),false,false,type_));
        fields_.put(UN_BEATEN_IMPORTANT_TRAINERS,new StandardField(UN_BEATEN_IMPORTANT_TRAINERS,_std.getCustList(),false,false,type_));
        fields_.put(BEATEN_IMPORTANT_TRAINERS,new StandardField(BEATEN_IMPORTANT_TRAINERS,_std.getCustList(),false,false,type_));
        fields_.put(REMAINING_OTHER_TRAINER_PLACES,new StandardField(REMAINING_OTHER_TRAINER_PLACES,_std.getCustMap(),false,false,type_));
        fields_.put(UN_VISITED_PLACES,new StandardField(UN_VISITED_PLACES,_std.getCustList(),false,false,type_));
        fields_.put(VISITED_PLACES,new StandardField(VISITED_PLACES,_std.getCustList(),false,false,type_));
        fields_.put(NB_REMAINING_NOT_MAX_LEVEL,new StandardField(NB_REMAINING_NOT_MAX_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(NB_REMAINING_NOT_MAX_HAPPINESS,new StandardField(NB_REMAINING_NOT_MAX_HAPPINESS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(NB_REMAINING_EGGS,new StandardField(NB_REMAINING_EGGS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(REMAIN_STEPS_REPEL,new StandardField(REMAIN_STEPS_REPEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.put(MONEY,new StandardField(MONEY,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.put(FULL_FAMILIES_BASE,new StandardField(FULL_FAMILIES_BASE,_std.getCustMap(),false,false,type_));
        fields_.put(NOT_AT_ALL_FAMILIES_BASE,new StandardField(NOT_AT_ALL_FAMILIES_BASE,_std.getCustMap(),false,false,type_));
        fields_.put(PARTIAL_FAMILIES_BASE_NOT_CAUGHT,new StandardField(PARTIAL_FAMILIES_BASE_NOT_CAUGHT,_std.getCustMap(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_REMAINING_OTHER_TRAINERS_PLACE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_FULL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_FULL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_NOT_ALL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_NOT_ALL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_PARTIAL_NOT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_PARTIAL_NOT,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_KEY_POKEMON,params_,_std.getCustList(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_PARTIAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList(_std.getAliasLong(),_std.getAliasLong(),_std.getAliasLong());
        method_ = new StandardMethod(GET_TR_POKEMON_PARTIAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_GAME_PROGRESSION_BEAN, type_);
    }
    private static void buildPokemonPlayerBean(BeanLgNames _std) {
        StandardClass type_;
        StringMap<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_PLAYER_BEAN, fields_, constructors_, methods_, _std.getBean(), MethodModifier.NORMAL);
        fields_.put(NAME,new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.put(IMAGE,new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.put(EVOLUTIONS,new StandardField(EVOLUTIONS,_std.getCustMap(),false,false,type_));
        fields_.put(LEVEL,new StandardField(LEVEL,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(GENDER,new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        fields_.put(ABILITY,new StandardField(ABILITY,_std.getAliasString(),false,false,type_));
        fields_.put(USED_BALL_CATCHING,new StandardField(USED_BALL_CATCHING,_std.getAliasString(),false,false,type_));
        fields_.put(ITEM,new StandardField(ITEM,_std.getAliasString(),false,false,type_));
        fields_.put(REMAINING_HP,new StandardField(REMAINING_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(REMAINING_HP_PER_CENT,new StandardField(REMAINING_HP_PER_CENT,_std.getAliasString(),false,false,type_));
        fields_.put(FULL_HP,new StandardField(FULL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(NICKNAME,new StandardField(NICKNAME,_std.getAliasString(),false,false,type_));
        fields_.put(WON_EXP_SINCE_LAST_LEVEL,new StandardField(WON_EXP_SINCE_LAST_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(NECESSARY_POINTS_NEXT_LEVEL,new StandardField(NECESSARY_POINTS_NEXT_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.put(HAPPINESS,new StandardField(HAPPINESS,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(NB_STEPS_TEAM_LEAD,new StandardField(NB_STEPS_TEAM_LEAD,_std.getAliasPrimShort(),false,false,type_));
        fields_.put(TYPES,new StandardField(TYPES,_std.getCustList(),false,false,type_));
        fields_.put(STATUS,new StandardField(STATUS,_std.getCustList(),false,false,type_));
        fields_.put(MOVES,new StandardField(MOVES,_std.getCustMap(),false,false,type_));
        fields_.put(STATISTICS,new StandardField(STATISTICS,_std.getCustList(),false,false,type_));
        params_ = new StringList(_std.getAliasLong());
        method_ = new StandardMethod(GET_EVO,params_,_std.getAliasString(), false, MethodModifier.NORMAL,type_);
        methods_.put(method_.getId(), method_);
        _std.getStandards().put(TYPE_POKEMON_PLAYER_BEAN, type_);
    }
    public static ResultErrorStd getResultDifficultyBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        DifficultyBean instance_ = (DifficultyBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,WIN_POINTS_FIGHT)) {
            res_.setResult(new StdStruct(instance_.getWinPointsFight(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(StdStruct.newInstance(instance_.getDiffWinningExpPtsFight(),PokemonStandards.TYPE_DIFFICULTY_WIN_POINTS_FIGHT));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            res_.setResult(new BooleanStruct(instance_.getAllowCatchingKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            res_.setResult(new BooleanStruct(instance_.getAllowedSwitchPlacesEndRound()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            res_.setResult(new StdStruct(instance_.getWinTrainerExp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(new StdStruct(instance_.getRateWinningExpPtsFight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            res_.setResult(new BooleanStruct(instance_.getEndFightIfOneTeamKo()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_PLAYER)) {
            res_.setResult(new ShortStruct(instance_.getIvPlayer()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_FOE)) {
            res_.setResult(new ShortStruct(instance_.getIvFoe()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            res_.setResult(new StdStruct(instance_.getRateWinMoneyBase(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            res_.setResult(new StdStruct(instance_.getRateLooseMoneyWin(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            res_.setResult(new BooleanStruct(instance_.getRestoredMovesEndFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_CLOSING)) {
            res_.setResult(new BooleanStruct(instance_.getEnabledClosing()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            res_.setResult(new BooleanStruct(instance_.getRandomWildFight()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            res_.setResult(new BooleanStruct(instance_.getStillPossibleFlee()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            res_.setResult(new BooleanStruct(instance_.getSkipLearningMovesWhileNotGrowingLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATES)) {
            res_.setResult(new StdStruct(instance_.getDamageRates(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            res_.setResult(StdStruct.newInstance(instance_.getDamageRatePlayer(),PokemonStandards.TYPE_DIFFICULTY_MODEL_LAW));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER_TABLE)) {
            res_.setResult(new StdStruct(instance_.getDamageRatePlayerTable(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            res_.setResult(StdStruct.newInstance(instance_.getDamageRateLawFoe(),PokemonStandards.TYPE_DIFFICULTY_MODEL_LAW));
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_FOE_TABLE)) {
            res_.setResult(new StdStruct(instance_.getDamageRateFoeTable(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultGameProgressionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        GameProgressionBean instance_ = (GameProgressionBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,FINISHED_GAME)) {
            res_.setResult(new BooleanStruct(instance_.getFinishedGame()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HERO_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getHeroImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HERO_IMAGE_OPPOSITE_SEX)) {
            res_.setResult(new StringStruct(instance_.getHeroImageOppositeSex()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_GAME_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getEndGameImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UN_BEATEN_IMPORTANT_TRAINERS)) {
            res_.setResult(new StdStruct(instance_.getUnBeatenImportantTrainers(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,BEATEN_IMPORTANT_TRAINERS)) {
            res_.setResult(new StdStruct(instance_.getBeatenImportantTrainers(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_OTHER_TRAINER_PLACES)) {
            res_.setResult(new StdStruct(instance_.getRemainingOtherTrainerPlaces(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,UN_VISITED_PLACES)) {
            res_.setResult(new StdStruct(instance_.getUnVisitedPlaces(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,VISITED_PLACES)) {
            res_.setResult(new StdStruct(instance_.getVisitedPlaces(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_REMAINING_NOT_MAX_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingNotMaxLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_REMAINING_NOT_MAX_HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingNotMaxHappiness()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_REMAINING_EGGS)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingEggs()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAIN_STEPS_REPEL)) {
            res_.setResult(new IntStruct(instance_.getRemainStepsRepel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MONEY)) {
            res_.setResult(new StdStruct(instance_.getMoney(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FULL_FAMILIES_BASE)) {
            res_.setResult(new StdStruct(instance_.getFullFamiliesBase(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NOT_AT_ALL_FAMILIES_BASE)) {
            res_.setResult(new StdStruct(instance_.getNotAtAllFamiliesBase(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,PARTIAL_FAMILIES_BASE_NOT_CAUGHT)) {
            res_.setResult(new StdStruct(instance_.getPartialFamiliesBaseNotCaught(),std_.getCustMap()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonPlayerBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonPlayerBean instance_ = (PokemonPlayerBean) _instance.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,EVOLUTIONS)) {
            res_.setResult(new StdStruct(instance_.getEvolutions(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new ShortStruct(instance_.getLevel()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,USED_BALL_CATCHING)) {
            res_.setResult(new StringStruct(instance_.getUsedBallCatching()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP)) {
            res_.setResult(new StdStruct(instance_.getRemainingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,REMAINING_HP_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpPerCent()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,FULL_HP)) {
            res_.setResult(new StdStruct(instance_.getFullHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,WON_EXP_SINCE_LAST_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getWonExpSinceLastLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NECESSARY_POINTS_NEXT_LEVEL)) {
            res_.setResult(new StdStruct(instance_.getNecessaryPointsNextLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringList.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new ShortStruct(instance_.getHappiness()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,NB_STEPS_TEAM_LEAD)) {
            res_.setResult(new ShortStruct(instance_.getNbStepsTeamLead()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,TYPES)) {
            res_.setResult(new StdStruct(instance_.getTypes(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATUS)) {
            res_.setResult(new StdStruct(instance_.getStatus(),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,MOVES)) {
            res_.setResult(new StdStruct(instance_.getMoves(),std_.getCustMap()));
            return res_;
        }
        if (StringList.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(new StdStruct(instance_.getStatistics(),std_.getCustList()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultDifficultyBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        DifficultyBean instance_ = (DifficultyBean) _instance.getInstance();
        Object value_ = _value.getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringList.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            instance_.setDiffWinningExpPtsFight((DifficultyWinPointsFight) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            instance_.setAllowCatchingKo((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            instance_.setAllowedSwitchPlacesEndRound((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            instance_.setWinTrainerExp((Rate) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            instance_.setRateWinningExpPtsFight((Rate) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            instance_.setEndFightIfOneTeamKo((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_PLAYER)) {
            instance_.setIvPlayer((Short) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,IV_FOE)) {
            instance_.setIvFoe((Short) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            instance_.setRateWinMoneyBase((Rate) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            instance_.setRateLooseMoneyWin((Rate) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            instance_.setRestoredMovesEndFight((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,ENABLED_CLOSING)) {
            instance_.setEnabledClosing((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            instance_.setRandomWildFight((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            instance_.setStillPossibleFlee((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            instance_.setSkipLearningMovesWhileNotGrowingLevel((Boolean) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            instance_.setDamageRatePlayer((DifficultyModelLaw) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringList.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            instance_.setDamageRateLawFoe((DifficultyModelLaw) value_);
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodDifficultyBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        DifficultyBean instance_ = (DifficultyBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,CHANGE)) {
            instance_.change();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodGameProgressionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        BeanLgNames std_ = (BeanLgNames) _cont.getStandards();
        GameProgressionBean instance_ = (GameProgressionBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_REMAINING_OTHER_TRAINERS_PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getRemainingOtherTrainersPlaceName((Long)_args[0])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_POKEMON_FULL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonFull((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_FULL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonFull((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_POKEMON_NOT_ALL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonNotAll((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_NOT_ALL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonNotAll((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_POKEMON_PARTIAL_NOT)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonPartialNot((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_PARTIAL_NOT)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonPartialNot((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_KEY_POKEMON)) {
            res_.setResult(new StdStruct(instance_.getKeyPokemon((Long)_args[0],(Long)_args[1]),std_.getCustList()));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_IMAGE_POKEMON_PARTIAL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonPartial((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        if (StringList.quickEq(methodName_,GET_TR_POKEMON_PARTIAL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonPartial((Long)_args[0],(Long)_args[1],(Long)_args[2])));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokemonPlayerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Object... _args) {
        PokemonPlayerBean instance_ = (PokemonPlayerBean) _instance.getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(methodName_,GET_EVO)) {
            res_.setResult(new StringStruct(instance_.getEvo((Long)_args[0])));
            return res_;
        }
        return res_;
    }
}
