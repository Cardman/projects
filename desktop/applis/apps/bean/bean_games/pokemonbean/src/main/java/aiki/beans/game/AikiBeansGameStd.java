package aiki.beans.game;
import aiki.beans.LgIntStruct;
import aiki.beans.PokemonBeanStruct;
import aiki.beans.PokemonStandards;
import aiki.beans.RateStruct;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.BooleanStruct;
import code.expressionlanguage.structs.IntStruct;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.StringStruct;
import code.expressionlanguage.structs.Struct;
import code.formathtml.util.BeanLgNames;
import code.bean.nat.BeanNatLgNames;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

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

    public static void build(PokemonStandards _std) {
        buildDifficultyBean(_std);
        buildGameProgressionBean(_std);
        buildPokemonPlayerBean(_std);
    }
    private static void buildDifficultyBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_DIFFICULTY_BEAN, fields_, constructors_, methods_, BeanNatLgNames.TYPE_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(WIN_POINTS_FIGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(DIFF_WINNING_EXP_PTS_FIGHT,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ALLOW_CATCHING_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(ALLOWED_SWITCH_PLACES_END_ROUND,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(WIN_TRAINER_EXP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RATE_WINNING_EXP_PTS_FIGHT,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(END_FIGHT_IF_ONE_TEAM_KO,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(IV_PLAYER,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(IV_FOE,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(RATE_WIN_MONEY_BASE,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RATE_LOOSE_MONEY_WIN,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(RESTORED_MOVES_END_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(ENABLED_CLOSING,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(RANDOM_WILD_FIGHT,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(STILL_POSSIBLE_FLEE,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(DAMAGE_RATES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(DAMAGE_RATE_PLAYER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(DAMAGE_RATE_PLAYER_TABLE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(DAMAGE_RATE_LAW_FOE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(DAMAGE_RATE_FOE_TABLE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList();
        method_ = new StandardMethod(CHANGE,params_,_std.getAliasVoid(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_DIFFICULTY_BEAN, type_);
    }
    private static void buildGameProgressionBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_GAME_PROGRESSION_BEAN, fields_, constructors_, methods_, BeanNatLgNames.TYPE_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(FINISHED_GAME,_std.getAliasPrimBoolean(),false,false,type_));
        fields_.add(new StandardField(HERO_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(HERO_IMAGE_OPPOSITE_SEX,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(END_GAME_IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(NICKNAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(UN_BEATEN_IMPORTANT_TRAINERS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(BEATEN_IMPORTANT_TRAINERS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(REMAINING_OTHER_TRAINER_PLACES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(UN_VISITED_PLACES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(VISITED_PLACES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_HAPPINESS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_REMAINING_EGGS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(REMAIN_STEPS_REPEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(MONEY,PokemonStandards.TYPE_LG_INT,false,false,type_));
        fields_.add(new StandardField(FULL_FAMILIES_BASE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(NOT_AT_ALL_FAMILIES_BASE, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(PARTIAL_FAMILIES_BASE_NOT_CAUGHT, BeanNatLgNames.TYPE_MAP,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_REMAINING_OTHER_TRAINERS_PLACE_NAME,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_FULL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_FULL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_NOT_ALL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_NOT_ALL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_PARTIAL_NOT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_PARTIAL_NOT,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_KEY_POKEMON,params_, BeanNatLgNames.TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_IMAGE_POKEMON_PARTIAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList(_std.getAliasPrimInteger(),_std.getAliasPrimInteger(),_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_TR_POKEMON_PARTIAL,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_GAME_PROGRESSION_BEAN, type_);
    }
    private static void buildPokemonPlayerBean(PokemonStandards _std) {
        StandardClass type_;
        CustList<StandardField> fields_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardMethod method_;
        StringList params_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        type_ = new StandardClass(TYPE_POKEMON_PLAYER_BEAN, fields_, constructors_, methods_, BeanNatLgNames.TYPE_BEAN, MethodModifier.NORMAL);
        fields_.add(new StandardField(NAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(IMAGE,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(LEVEL,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(GENDER,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ABILITY,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(USED_BALL_CATCHING,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(ITEM,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(REMAINING_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(REMAINING_HP_PER_CENT,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(FULL_HP,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(NICKNAME,_std.getAliasString(),false,false,type_));
        fields_.add(new StandardField(WON_EXP_SINCE_LAST_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(NECESSARY_POINTS_NEXT_LEVEL,PokemonStandards.TYPE_RATE,false,false,type_));
        fields_.add(new StandardField(HAPPINESS,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(NB_STEPS_TEAM_LEAD,_std.getAliasPrimInteger(),false,false,type_));
        fields_.add(new StandardField(TYPES, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(STATUS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        fields_.add(new StandardField(MOVES, BeanNatLgNames.TYPE_MAP,false,false,type_));
        fields_.add(new StandardField(STATISTICS, BeanNatLgNames.TYPE_LIST,false,false,type_));
        params_ = new StringList(_std.getAliasPrimInteger());
        method_ = new StandardMethod(GET_EVO,params_,_std.getAliasString(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        _std.getStandards().addEntry(TYPE_POKEMON_PLAYER_BEAN, type_);
    }
    public static ResultErrorStd getResultDifficultyBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        DifficultyBean instance_ = (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,WIN_POINTS_FIGHT)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getWinPointsFight()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getDiffWinningExpPtsFight()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getAllowCatchingKo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            res_.setResult(BooleanStruct.of(instance_.getAllowedSwitchPlacesEndRound()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            res_.setResult(new RateStruct(instance_.getWinTrainerExp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            res_.setResult(new RateStruct(instance_.getRateWinningExpPtsFight(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            res_.setResult(BooleanStruct.of(instance_.getEndFightIfOneTeamKo()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IV_PLAYER)) {
            res_.setResult(new IntStruct(instance_.getIvPlayer()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IV_FOE)) {
            res_.setResult(new IntStruct(instance_.getIvFoe()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            res_.setResult(new RateStruct(instance_.getRateWinMoneyBase(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            res_.setResult(new RateStruct(instance_.getRateLooseMoneyWin(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            res_.setResult(BooleanStruct.of(instance_.getRestoredMovesEndFight()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_CLOSING)) {
            res_.setResult(BooleanStruct.of(instance_.getEnabledClosing()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            res_.setResult(BooleanStruct.of(instance_.getRandomWildFight()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            res_.setResult(BooleanStruct.of(instance_.getStillPossibleFlee()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            res_.setResult(BooleanStruct.of(instance_.getSkipLearningMovesWhileNotGrowingLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATES)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getDamageRates()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getDamageRatePlayer()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_PLAYER_TABLE)) {
            res_.setResult(PokemonStandards.getRateRate(_cont,instance_.getDamageRatePlayerTable()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            res_.setResult(BeanLgNames.wrapStd(instance_.getDamageRateLawFoe()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_FOE_TABLE)) {
            res_.setResult(PokemonStandards.getRateRate(_cont,instance_.getDamageRateFoeTable()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultGameProgressionBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        GameProgressionBean instance_ = (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,FINISHED_GAME)) {
            res_.setResult(BooleanStruct.of(instance_.getFinishedGame()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HERO_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getHeroImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HERO_IMAGE_OPPOSITE_SEX)) {
            res_.setResult(new StringStruct(instance_.getHeroImageOppositeSex()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_GAME_IMAGE)) {
            res_.setResult(new StringStruct(instance_.getEndGameImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,UN_BEATEN_IMPORTANT_TRAINERS)) {
            res_.setResult(PokemonStandards.getTrPlNa(instance_.getUnBeatenImportantTrainers()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,BEATEN_IMPORTANT_TRAINERS)) {
            res_.setResult(PokemonStandards.getTrPlNa(instance_.getBeatenImportantTrainers()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_OTHER_TRAINER_PLACES)) {
            res_.setResult(PokemonStandards.getShortInt(_cont,instance_.getRemainingOtherTrainerPlaces()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,UN_VISITED_PLACES)) {
            res_.setResult(std_.getStringArray(instance_.getUnVisitedPlaces()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,VISITED_PLACES)) {
            res_.setResult(std_.getStringArray(instance_.getVisitedPlaces()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_REMAINING_NOT_MAX_LEVEL)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingNotMaxLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_REMAINING_NOT_MAX_HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingNotMaxHappiness()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_REMAINING_EGGS)) {
            res_.setResult(new IntStruct(instance_.getNbRemainingEggs()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAIN_STEPS_REPEL)) {
            res_.setResult(new IntStruct(instance_.getRemainStepsRepel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MONEY)) {
            res_.setResult(new LgIntStruct(instance_.getMoney(),PokemonStandards.TYPE_LG_INT));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,FULL_FAMILIES_BASE)) {
            res_.setResult(PokemonStandards.getStrListStrList(_cont,instance_.getFullFamiliesBase()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NOT_AT_ALL_FAMILIES_BASE)) {
            res_.setResult(PokemonStandards.getStrListStrList(_cont,instance_.getNotAtAllFamiliesBase()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,PARTIAL_FAMILIES_BASE_NOT_CAUGHT)) {
            res_.setResult(PokemonStandards.getStrListStrList(_cont,instance_.getPartialFamiliesBaseNotCaught()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd getResultPokemonPlayerBean(ContextEl _cont, ClassField _classField, Struct _instance) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        ResultErrorStd res_ = new ResultErrorStd();
        PokemonPlayerBean instance_ = (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,NAME)) {
            res_.setResult(new StringStruct(instance_.getName()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IMAGE)) {
            res_.setResult(new StringStruct(instance_.getImage()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,EVOLUTIONS)) {
            res_.setResult(PokemonStandards.getStrStr(_cont,instance_.getEvolutions()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,LEVEL)) {
            res_.setResult(new IntStruct(instance_.getLevel()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,GENDER)) {
            res_.setResult(new StringStruct(instance_.getGender()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ABILITY)) {
            res_.setResult(new StringStruct(instance_.getAbility()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,USED_BALL_CATCHING)) {
            res_.setResult(new StringStruct(instance_.getUsedBallCatching()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ITEM)) {
            res_.setResult(new StringStruct(instance_.getItem()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_HP)) {
            res_.setResult(new RateStruct(instance_.getRemainingHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,REMAINING_HP_PER_CENT)) {
            res_.setResult(new StringStruct(instance_.getRemainingHpPerCent()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,FULL_HP)) {
            res_.setResult(new RateStruct(instance_.getFullHp(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NICKNAME)) {
            res_.setResult(new StringStruct(instance_.getNickname()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WON_EXP_SINCE_LAST_LEVEL)) {
            res_.setResult(new RateStruct(instance_.getWonExpSinceLastLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NECESSARY_POINTS_NEXT_LEVEL)) {
            res_.setResult(new RateStruct(instance_.getNecessaryPointsNextLevel(),PokemonStandards.TYPE_RATE));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,HAPPINESS)) {
            res_.setResult(new IntStruct(instance_.getHappiness()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,NB_STEPS_TEAM_LEAD)) {
            res_.setResult(new IntStruct(instance_.getNbStepsTeamLead()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,TYPES)) {
            res_.setResult(std_.getStringArray(instance_.getTypes()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATUS)) {
            res_.setResult(std_.getStringArray(instance_.getStatus()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,MOVES)) {
            res_.setResult(PokemonStandards.getUsesStr(_cont,instance_.getMoves()));
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STATISTICS)) {
            res_.setResult(PokemonStandards.getStPkPl(instance_.getStatistics()));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd setResultDifficultyBean(ContextEl _cont, ClassField _classField, Struct _instance, Struct _val) {
        ResultErrorStd res_ = new ResultErrorStd();
        DifficultyBean instance_ = (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance();
        String fieldName_ = _classField.getFieldName();
        if (StringUtil.quickEq(fieldName_,DIFF_WINNING_EXP_PTS_FIGHT)) {
            instance_.setDiffWinningExpPtsFight(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLOW_CATCHING_KO)) {
            instance_.setAllowCatchingKo(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ALLOWED_SWITCH_PLACES_END_ROUND)) {
            instance_.setAllowedSwitchPlacesEndRound(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,WIN_TRAINER_EXP)) {
            instance_.setWinTrainerExp(PokemonStandards.convertToRate(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_WINNING_EXP_PTS_FIGHT)) {
            instance_.setRateWinningExpPtsFight(PokemonStandards.convertToRate(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,END_FIGHT_IF_ONE_TEAM_KO)) {
            instance_.setEndFightIfOneTeamKo(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IV_PLAYER)) {
            instance_.setIvPlayer(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,IV_FOE)) {
            instance_.setIvFoe(NumParsers.convertToNumber(_val).intStruct());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_WIN_MONEY_BASE)) {
            instance_.setRateWinMoneyBase(PokemonStandards.convertToRate(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RATE_LOOSE_MONEY_WIN)) {
            instance_.setRateLooseMoneyWin(PokemonStandards.convertToRate(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RESTORED_MOVES_END_FIGHT)) {
            instance_.setRestoredMovesEndFight(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,ENABLED_CLOSING)) {
            instance_.setEnabledClosing(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,RANDOM_WILD_FIGHT)) {
            instance_.setRandomWildFight(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,STILL_POSSIBLE_FLEE)) {
            instance_.setStillPossibleFlee(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL)) {
            instance_.setSkipLearningMovesWhileNotGrowingLevel(BooleanStruct.isTrue(_val));
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_PLAYER)) {
            instance_.setDamageRatePlayer(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        if (StringUtil.quickEq(fieldName_,DAMAGE_RATE_LAW_FOE)) {
            instance_.setDamageRateLawFoe(NumParsers.getString(_val).getInstance());
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodDifficultyBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        DifficultyBean instance_ = (DifficultyBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,CHANGE)) {
            instance_.change();
            res_.setResult(NullStruct.NULL_VALUE);
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodGameProgressionBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        BeanNatLgNames std_ = (BeanNatLgNames) _cont.getStandards();
        GameProgressionBean instance_ = (GameProgressionBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_REMAINING_OTHER_TRAINERS_PLACE_NAME)) {
            res_.setResult(new StringStruct(instance_.getRemainingOtherTrainersPlaceName(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_POKEMON_FULL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonFull(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_FULL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonFull(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_POKEMON_NOT_ALL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonNotAll(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_NOT_ALL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonNotAll(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_POKEMON_PARTIAL_NOT)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonPartialNot(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_PARTIAL_NOT)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonPartialNot(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_KEY_POKEMON)) {
            res_.setResult(std_.getStringArray(instance_.getKeyPokemon(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_IMAGE_POKEMON_PARTIAL)) {
            res_.setResult(new StringStruct(instance_.getImagePokemonPartial(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        if (StringUtil.quickEq(methodName_,GET_TR_POKEMON_PARTIAL)) {
            res_.setResult(new StringStruct(instance_.getTrPokemonPartial(NumParsers.convertToNumber(_args[0]).intStruct(),NumParsers.convertToNumber(_args[1]).intStruct(),NumParsers.convertToNumber(_args[2]).intStruct())));
            return res_;
        }
        return res_;
    }
    public static ResultErrorStd invokeMethodPokemonPlayerBean(ContextEl _cont, Struct _instance, ClassMethodId _method, Struct... _args) {
        PokemonPlayerBean instance_ = (PokemonPlayerBean) ((PokemonBeanStruct)_instance).getInstance();
        String methodName_ = _method.getConstraints().getName();
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(methodName_,GET_EVO)) {
            res_.setResult(new StringStruct(instance_.getEvo(NumParsers.convertToNumber(_args[0]).intStruct())));
            return res_;
        }
        return res_;
    }
}
