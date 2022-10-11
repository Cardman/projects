package aiki.beans.game;

import aiki.beans.PokemonStandards;
import code.bean.nat.BeanNatCommonLgNames;
import code.bean.nat.SpecNatMethod;
import code.bean.nat.SpecialNatClass;
import code.bean.nat.StandardField;
import code.expressionlanguage.functionid.MethodModifier;
import code.util.CustList;
public final class AikiBeansGameStd{
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
    private static final String D_WIN_POINTS_FIGHT = "winPointsFight";
    private static final String D_DIFF_WINNING_EXP_PTS_FIGHT = "diffWinningExpPtsFight";
    private static final String D_ALLOW_CATCHING_KO = "allowCatchingKo";
    private static final String D_ALLOWED_SWITCH_PLACES_END_ROUND = "allowedSwitchPlacesEndRound";
    private static final String D_WIN_TRAINER_EXP = "winTrainerExp";
    private static final String D_RATE_WINNING_EXP_PTS_FIGHT = "rateWinningExpPtsFight";
    private static final String D_END_FIGHT_IF_ONE_TEAM_KO = "endFightIfOneTeamKo";
    private static final String D_IV_PLAYER = "ivPlayer";
    private static final String D_IV_FOE = "ivFoe";
    private static final String D_RATE_WIN_MONEY_BASE = "rateWinMoneyBase";
    private static final String D_RATE_LOOSE_MONEY_WIN = "rateLooseMoneyWin";
    private static final String D_RESTORED_MOVES_END_FIGHT = "restoredMovesEndFight";
    private static final String D_ENABLED_CLOSING = "enabledClosing";
    private static final String D_RANDOM_WILD_FIGHT = "randomWildFight";
    private static final String D_STILL_POSSIBLE_FLEE = "stillPossibleFlee";
    private static final String D_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL = "skipLearningMovesWhileNotGrowingLevel";
    private static final String D_DAMAGE_RATES = "damageRates";
    private static final String D_DAMAGE_RATE_PLAYER = "damageRatePlayer";
    private static final String D_DAMAGE_RATE_PLAYER_TABLE = "damageRatePlayerTable";
    private static final String D_DAMAGE_RATE_LAW_FOE = "damageRateLawFoe";
    private static final String D_DAMAGE_RATE_FOE_TABLE = "damageRateFoeTable";
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
    private AikiBeansGameStd(){}

    public static void buildDifficultyBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_DIFFICULTY_BEAN, fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(D_WIN_POINTS_FIGHT, BeanNatCommonLgNames.TYPE_MAP,false,false,new DifficultyBeanWinPointsFightGet(),null));
        fields_.add(new StandardField(D_DIFF_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.STRING,false,false,new DifficultyBeanDiffWinningExpPtsFightGet(),new DifficultyBeanDiffWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_ALLOW_CATCHING_KO,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanAllowCatchingKoGet(),new DifficultyBeanAllowCatchingKoSet()));
        fields_.add(new StandardField(D_ALLOWED_SWITCH_PLACES_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanAllowedSwitchPlacesEndRoundGet(),new DifficultyBeanAllowedSwitchPlacesEndRoundSet()));
        fields_.add(new StandardField(D_WIN_TRAINER_EXP,BeanNatCommonLgNames.TYPE_RATE,false,false,new DifficultyBeanWinTrainerExpGet(),new DifficultyBeanWinTrainerExpSet()));
        fields_.add(new StandardField(D_RATE_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.TYPE_RATE,false,false,new DifficultyBeanRateWinningExpPtsFightGet(),new DifficultyBeanRateWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_END_FIGHT_IF_ONE_TEAM_KO,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanEndFightIfOneTeamKoGet(),new DifficultyBeanEndFightIfOneTeamKoSet()));
        fields_.add(new StandardField(D_IV_PLAYER, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new DifficultyBeanIvPlayerGet(),new DifficultyBeanIvPlayerSet()));
        fields_.add(new StandardField(D_IV_FOE, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new DifficultyBeanIvFoeGet(),new DifficultyBeanIvFoeSet()));
        fields_.add(new StandardField(D_RATE_WIN_MONEY_BASE,BeanNatCommonLgNames.TYPE_RATE,false,false,new DifficultyBeanRateWinMoneyBaseGet(),new DifficultyBeanRateWinMoneyBaseSet()));
        fields_.add(new StandardField(D_RATE_LOOSE_MONEY_WIN,BeanNatCommonLgNames.TYPE_RATE,false,false,new DifficultyBeanRateLooseMoneyWinGet(),new DifficultyBeanRateLooseMoneyWinSet()));
        fields_.add(new StandardField(D_RESTORED_MOVES_END_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanRestoredMovesEndFightGet(),new DifficultyBeanRestoredMovesEndFightSet()));
        fields_.add(new StandardField(D_ENABLED_CLOSING,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanEnabledClosingGet(),new DifficultyBeanEnabledClosingSet()));
        fields_.add(new StandardField(D_RANDOM_WILD_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanRandomWildFightGet(),new DifficultyBeanRandomWildFightSet()));
        fields_.add(new StandardField(D_STILL_POSSIBLE_FLEE,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanStillPossibleFleeGet(),new DifficultyBeanStillPossibleFleeSet()));
        fields_.add(new StandardField(D_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new DifficultyBeanSkipLearningMovesWhileNotGrowingLevelGet(),new DifficultyBeanSkipLearningMovesWhileNotGrowingLevelSet()));
        fields_.add(new StandardField(D_DAMAGE_RATES, BeanNatCommonLgNames.TYPE_MAP,false,false,new DifficultyBeanDamageRatesGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER,BeanNatCommonLgNames.STRING,false,false,new DifficultyBeanDamageRatePlayerGet(),new DifficultyBeanDamageRatePlayerSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER_TABLE, BeanNatCommonLgNames.TYPE_MAP,false,false,new DifficultyBeanDamageRatePlayerTableGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_LAW_FOE,BeanNatCommonLgNames.STRING,false,false,new DifficultyBeanDamageRateLawFoeGet(),new DifficultyBeanDamageRateLawFoeSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_FOE_TABLE, BeanNatCommonLgNames.TYPE_MAP,false,false,new DifficultyBeanDamageRateFoeTableGet(),null));
        methods_.add( new SpecNatMethod(CHANGE, BeanNatCommonLgNames.VOID, false, MethodModifier.NORMAL,new DifficultyBeanChange()));
        _std.getStds().addEntry(TYPE_DIFFICULTY_BEAN, type_);
    }
    public static void buildGameProgressionBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_GAME_PROGRESSION_BEAN, fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(FINISHED_GAME,BeanNatCommonLgNames.PRIM_BOOLEAN,false,false,new GameProgressionBeanFinishedGameGet(),null));
        fields_.add(new StandardField(HERO_IMAGE,BeanNatCommonLgNames.STRING,false,false,new GameProgressionBeanHeroImageGet(),null));
        fields_.add(new StandardField(HERO_IMAGE_OPPOSITE_SEX,BeanNatCommonLgNames.STRING,false,false,new GameProgressionBeanHeroImageOppositeSexGet(),null));
        fields_.add(new StandardField(END_GAME_IMAGE,BeanNatCommonLgNames.STRING,false,false,new GameProgressionBeanEndGameImageGet(),null));
        fields_.add(new StandardField(NICKNAME,BeanNatCommonLgNames.STRING,false,false,new GameProgressionBeanNicknameGet(),null));
        fields_.add(new StandardField(UN_BEATEN_IMPORTANT_TRAINERS, BeanNatCommonLgNames.TYPE_LIST,false,false,new GameProgressionBeanUnBeatenImportantTrainersGet(),null));
        fields_.add(new StandardField(BEATEN_IMPORTANT_TRAINERS, BeanNatCommonLgNames.TYPE_LIST,false,false,new GameProgressionBeanBeatenImportantTrainersGet(),null));
        fields_.add(new StandardField(REMAINING_OTHER_TRAINER_PLACES, BeanNatCommonLgNames.TYPE_MAP,false,false,new GameProgressionBeanRemainingOtherTrainerPlacesGet(),null));
        fields_.add(new StandardField(UN_VISITED_PLACES, BeanNatCommonLgNames.TYPE_LIST,false,false,new GameProgressionBeanUnVisitedPlacesGet(),null));
        fields_.add(new StandardField(VISITED_PLACES, BeanNatCommonLgNames.TYPE_LIST,false,false,new GameProgressionBeanVisitedPlacesGet(),null));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_LEVEL, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new GameProgressionBeanNbRemainingNotMaxLevelGet(),null));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_HAPPINESS, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new GameProgressionBeanNbRemainingNotMaxHappinessGet(),null));
        fields_.add(new StandardField(NB_REMAINING_EGGS, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new GameProgressionBeanNbRemainingEggsGet(),null));
        fields_.add(new StandardField(REMAIN_STEPS_REPEL, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new GameProgressionBeanRemainStepsRepelGet(),null));
        fields_.add(new StandardField(MONEY,BeanNatCommonLgNames.TYPE_LG_INT,false,false,new GameProgressionBeanMoneyGet(),null));
        fields_.add(new StandardField(FULL_FAMILIES_BASE, BeanNatCommonLgNames.TYPE_MAP,false,false,new GameProgressionBeanFullFamiliesBaseGet(),null));
        fields_.add(new StandardField(NOT_AT_ALL_FAMILIES_BASE, BeanNatCommonLgNames.TYPE_MAP,false,false,new GameProgressionBeanNotAtAllFamiliesBaseGet(),null));
        fields_.add(new StandardField(PARTIAL_FAMILIES_BASE_NOT_CAUGHT, BeanNatCommonLgNames.TYPE_MAP,false,false,new GameProgressionBeanPartialFamiliesBaseNotCaughtGet(),null));
        methods_.add( new SpecNatMethod(GET_REMAINING_OTHER_TRAINERS_PLACE_NAME,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetRemainingOtherTrainersPlaceName()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_FULL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetImagePokemonFull()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_FULL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetTrPokemonFull()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_NOT_ALL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetImagePokemonNotAll()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_NOT_ALL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetTrPokemonNotAll()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_PARTIAL_NOT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetImagePokemonPartialNot()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_PARTIAL_NOT,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetTrPokemonPartialNot()));
        methods_.add( new SpecNatMethod(GET_KEY_POKEMON, BeanNatCommonLgNames.TYPE_LIST, false, MethodModifier.NORMAL,new GameProgressionBeanGetKeyPokemon()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_PARTIAL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetImagePokemonPartial()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_PARTIAL,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new GameProgressionBeanGetTrPokemonPartial()));
        _std.getStds().addEntry(TYPE_GAME_PROGRESSION_BEAN, type_);
    }
    public static void buildPokemonPlayerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(TYPE_POKEMON_PLAYER_BEAN, fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanNameGet(),null));
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanImageGet(),null));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.TYPE_MAP,false,false,new PokemonPlayerBeanEvolutionsGet(),null));
        fields_.add(new StandardField(LEVEL, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonPlayerBeanLevelGet(),null));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanGenderGet(),null));
        fields_.add(new StandardField(ABILITY,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanAbilityGet(),null));
        fields_.add(new StandardField(USED_BALL_CATCHING,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanUsedBallCatchingGet(),null));
        fields_.add(new StandardField(ITEM,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanItemGet(),null));
        fields_.add(new StandardField(REMAINING_HP,BeanNatCommonLgNames.TYPE_RATE,false,false,new PokemonPlayerBeanRemainingHpGet(),null));
        fields_.add(new StandardField(REMAINING_HP_PER_CENT,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanRemainingHpPerCentGet(),null));
        fields_.add(new StandardField(FULL_HP,BeanNatCommonLgNames.TYPE_RATE,false,false,new PokemonPlayerBeanFullHpGet(),null));
        fields_.add(new StandardField(NICKNAME,BeanNatCommonLgNames.STRING,false,false,new PokemonPlayerBeanNicknameGet(),null));
        fields_.add(new StandardField(WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE,false,false,new PokemonPlayerBeanWonExpSinceLastLevelGet(),null));
        fields_.add(new StandardField(NECESSARY_POINTS_NEXT_LEVEL,BeanNatCommonLgNames.TYPE_RATE,false,false,new PokemonPlayerBeanNecessaryPointsNextLevelGet(),null));
        fields_.add(new StandardField(HAPPINESS, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonPlayerBeanHappinessGet(),null));
        fields_.add(new StandardField(NB_STEPS_TEAM_LEAD, BeanNatCommonLgNames.PRIM_INTEGER,false,false,new PokemonPlayerBeanNbStepsTeamLeadGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST,false,false,new PokemonPlayerBeanTypesGet(),null));
        fields_.add(new StandardField(STATUS, BeanNatCommonLgNames.TYPE_LIST,false,false,new PokemonPlayerBeanStatusGet(),null));
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_MAP,false,false,new PokemonPlayerBeanMovesGet(),null));
        fields_.add(new StandardField(STATISTICS, BeanNatCommonLgNames.TYPE_LIST,false,false,new PokemonPlayerBeanStatisticsGet(),null));
        methods_.add( new SpecNatMethod(GET_EVO,BeanNatCommonLgNames.STRING, false, MethodModifier.NORMAL,new PokemonPlayerBeanGetEvo()));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER_BEAN, type_);
    }
}
