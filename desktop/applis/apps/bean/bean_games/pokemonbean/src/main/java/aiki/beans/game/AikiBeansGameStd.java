package aiki.beans.game;

import aiki.beans.PokemonStandards;
import aiki.beans.facade.game.dto.StatisticInfoPkPlayer;
import aiki.fight.pokemon.TrainerPlaceNames;
import code.bean.nat.*;
import code.scripts.confs.PkScriptPages;
import code.util.CustList;
public final class AikiBeansGameStd{
    public static final String TYPE_DIFFICULTY_BEAN = "aiki.beans.game.DifficultyBean";
    public static final String TYPE_DIFFICULTY_COMMON_BEAN = "aiki.beans.DiCo";
    public static final String BEAN_DIFFICULTY_COMMON = "difficulty_common";
    public static final String TYPE_GAME_PROGRESSION_BEAN = "aiki.beans.game.GameProgressionBean";
    public static final String TYPE_POKEMON_PLAYER_BEAN = "aiki.beans.game.PokemonPlayerBean";
    public static final String TYPE_POKEMON_PLAYER_BEAN_2 = "_";
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
    private static final String GET_IMG = "getImg";
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
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField("d", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.DIFF_COMMON_HTML),null));
        fields_.add(new StandardField("c", TYPE_DIFFICULTY_COMMON_BEAN, new DifficultyBeanComGet(),null));
        methods_.add( new SpecNatMethod(CHANGE, BeanNatCommonLgNames.VOID, new DifficultyBeanChange()));
        _std.getStds().addEntry(TYPE_DIFFICULTY_BEAN, type_);
    }

    public static void buildDifficultyCommonBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(D_WIN_POINTS_FIGHT, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanWinPointsFightGet(),null));
        fields_.add(new StandardField(D_DIFF_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDiffWinningExpPtsFightGet(),new DifficultyCommonBeanDiffWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_ALLOW_CATCHING_KO,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanAllowCatchingKoGet(),new DifficultyCommonBeanAllowCatchingKoSet()));
        fields_.add(new StandardField(D_ALLOWED_SWITCH_PLACES_END_ROUND,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanAllowedSwitchPlacesEndRoundGet(),new DifficultyCommonBeanAllowedSwitchPlacesEndRoundSet()));
        fields_.add(new StandardField(D_WIN_TRAINER_EXP,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanWinTrainerExpGet(),new DifficultyCommonBeanWinTrainerExpSet()));
        fields_.add(new StandardField(D_RATE_WINNING_EXP_PTS_FIGHT,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateWinningExpPtsFightGet(),new DifficultyCommonBeanRateWinningExpPtsFightSet()));
        fields_.add(new StandardField(D_END_FIGHT_IF_ONE_TEAM_KO,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanEndFightIfOneTeamKoGet(),new DifficultyCommonBeanEndFightIfOneTeamKoSet()));
        fields_.add(new StandardField(D_IV_PLAYER, BeanNatCommonLgNames.PRIM_INTEGER, new DifficultyCommonBeanIvPlayerGet(),new DifficultyCommonBeanIvPlayerSet()));
        fields_.add(new StandardField(D_IV_FOE, BeanNatCommonLgNames.PRIM_INTEGER, new DifficultyCommonBeanIvFoeGet(),new DifficultyCommonBeanIvFoeSet()));
        fields_.add(new StandardField(D_RATE_WIN_MONEY_BASE,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateWinMoneyBaseGet(),new DifficultyCommonBeanRateWinMoneyBaseSet()));
        fields_.add(new StandardField(D_RATE_LOOSE_MONEY_WIN,BeanNatCommonLgNames.TYPE_RATE, new DifficultyCommonBeanRateLooseMoneyWinGet(),new DifficultyCommonBeanRateLooseMoneyWinSet()));
        fields_.add(new StandardField(D_RESTORED_MOVES_END_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanRestoredMovesEndFightGet(),new DifficultyCommonBeanRestoredMovesEndFightSet()));
        fields_.add(new StandardField(D_ENABLED_CLOSING,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanEnabledClosingGet(),new DifficultyCommonBeanEnabledClosingSet()));
        fields_.add(new StandardField(D_RANDOM_WILD_FIGHT,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanRandomWildFightGet(),new DifficultyCommonBeanRandomWildFightSet()));
        fields_.add(new StandardField(D_STILL_POSSIBLE_FLEE,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanStillPossibleFleeGet(),new DifficultyCommonBeanStillPossibleFleeSet()));
        fields_.add(new StandardField(D_SKIP_LEARNING_MOVES_WHILE_NOT_GROWING_LEVEL,BeanNatCommonLgNames.PRIM_BOOLEAN, new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelGet(),new DifficultyCommonBeanSkipLearningMovesWhileNotGrowingLevelSet()));
        fields_.add(new StandardField(D_DAMAGE_RATES, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRatesGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDamageRatePlayerGet(),new DifficultyCommonBeanDamageRatePlayerSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_PLAYER_TABLE, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRatePlayerTableGet(),null));
        fields_.add(new StandardField(D_DAMAGE_RATE_LAW_FOE,BeanNatCommonLgNames.STRING, new DifficultyCommonBeanDamageRateLawFoeGet(),new DifficultyCommonBeanDamageRateLawFoeSet()));
        fields_.add(new StandardField(D_DAMAGE_RATE_FOE_TABLE, BeanNatCommonLgNames.TYPE_MAP, new DifficultyCommonBeanDamageRateFoeTableGet(),null));
        fields_.add(new StandardField("c", TYPE_DIFFICULTY_COMMON_BEAN, null,new DifficultyBeanComSet()));
        _std.getStds().addEntry(TYPE_DIFFICULTY_COMMON_BEAN, type_);
    }
    public static void buildGameProgressionBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, methods_, BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(FINISHED_GAME,BeanNatCommonLgNames.PRIM_BOOLEAN, new GameProgressionBeanFinishedGameGet(),null));
        fields_.add(new StandardField(HERO_IMAGE,BeanNatCommonLgNames.STRING, new GameProgressionBeanHeroImageGet(),null));
        fields_.add(new StandardField(HERO_IMAGE_OPPOSITE_SEX,BeanNatCommonLgNames.STRING, new GameProgressionBeanHeroImageOppositeSexGet(),null));
        fields_.add(new StandardField(END_GAME_IMAGE,BeanNatCommonLgNames.STRING, new GameProgressionBeanEndGameImageGet(),null));
        fields_.add(new StandardField(NICKNAME,BeanNatCommonLgNames.STRING, new GameProgressionBeanNicknameGet(),null));
        fields_.add(new StandardField(UN_BEATEN_IMPORTANT_TRAINERS, BeanNatCommonLgNames.TYPE_LIST, new GameProgressionBeanUnBeatenImportantTrainersGet(),null));
        fields_.add(new StandardField(BEATEN_IMPORTANT_TRAINERS, BeanNatCommonLgNames.TYPE_LIST, new GameProgressionBeanBeatenImportantTrainersGet(),null));
        fields_.add(new StandardField(REMAINING_OTHER_TRAINER_PLACES, BeanNatCommonLgNames.TYPE_MAP, new GameProgressionBeanRemainingOtherTrainerPlacesGet(),null));
        fields_.add(new StandardField(UN_VISITED_PLACES, BeanNatCommonLgNames.TYPE_LIST, new GameProgressionBeanUnVisitedPlacesGet(),null));
        fields_.add(new StandardField(VISITED_PLACES, BeanNatCommonLgNames.TYPE_LIST, new GameProgressionBeanVisitedPlacesGet(),null));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_LEVEL, BeanNatCommonLgNames.PRIM_INTEGER, new GameProgressionBeanNbRemainingNotMaxLevelGet(),null));
        fields_.add(new StandardField(NB_REMAINING_NOT_MAX_HAPPINESS, BeanNatCommonLgNames.PRIM_INTEGER, new GameProgressionBeanNbRemainingNotMaxHappinessGet(),null));
        fields_.add(new StandardField(NB_REMAINING_EGGS, BeanNatCommonLgNames.PRIM_INTEGER, new GameProgressionBeanNbRemainingEggsGet(),null));
        fields_.add(new StandardField(REMAIN_STEPS_REPEL, BeanNatCommonLgNames.PRIM_INTEGER, new GameProgressionBeanRemainStepsRepelGet(),null));
        fields_.add(new StandardField(MONEY,BeanNatCommonLgNames.TYPE_LG_INT, new GameProgressionBeanMoneyGet(),null));
        fields_.add(new StandardField(FULL_FAMILIES_BASE, BeanNatCommonLgNames.TYPE_MAP, new GameProgressionBeanFullFamiliesBaseGet(),null));
        fields_.add(new StandardField(NOT_AT_ALL_FAMILIES_BASE, BeanNatCommonLgNames.TYPE_MAP, new GameProgressionBeanNotAtAllFamiliesBaseGet(),null));
        fields_.add(new StandardField(PARTIAL_FAMILIES_BASE_NOT_CAUGHT, BeanNatCommonLgNames.TYPE_MAP, new GameProgressionBeanPartialFamiliesBaseNotCaughtGet(),null));
        methods_.add( new SpecNatMethod(GET_REMAINING_OTHER_TRAINERS_PLACE_NAME,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetRemainingOtherTrainersPlaceName()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_FULL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetImagePokemonFull()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_FULL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetTrPokemonFull()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_NOT_ALL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetImagePokemonNotAll()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_NOT_ALL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetTrPokemonNotAll()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_PARTIAL_NOT,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetImagePokemonPartialNot()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_PARTIAL_NOT,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetTrPokemonPartialNot()));
        methods_.add( new SpecNatMethod(GET_KEY_POKEMON, BeanNatCommonLgNames.TYPE_LIST, new GameProgressionBeanGetKeyPokemon()));
        methods_.add( new SpecNatMethod(GET_IMAGE_POKEMON_PARTIAL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetImagePokemonPartial()));
        methods_.add( new SpecNatMethod(GET_TR_POKEMON_PARTIAL,BeanNatCommonLgNames.STRING, new GameProgressionBeanGetTrPokemonPartial()));
        methods_.add( new SpecNatMethod("n", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.REN_ADD_WEB_PROG_HTML_GAMEPROGNOTATALL_HTML)));
        methods_.add( new SpecNatMethod("p", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.REN_ADD_WEB_PROG_HTML_GAMEPROGPART_HTML)));
        methods_.add( new SpecNatMethod("a", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.REN_ADD_WEB_PROG_HTML_GAMEPROGALL_HTML)));
        methods_.add( new SpecNatMethod("g", BeanNatCommonLgNames.STRING, new CstNatCaller(PkScriptPages.REN_ADD_WEB_PROG_HTML_GAMEPROG_HTML)));
        _std.getStds().addEntry(TYPE_GAME_PROGRESSION_BEAN, type_);
    }
    public static void buildPokemonPlayerBean(PokemonStandards _std){
        CustList<StandardField> fields_=new CustList<StandardField>();
        SpecialNatClass type_ = new SpecialNatClass(fields_, new CustList<SpecNatMethod>(), BeanNatCommonLgNames.TYPE_BEAN);
        fields_.add(new StandardField(NAME,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanNameGet(),null));
        fields_.add(new StandardField(IMAGE,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanImageGet(),null));
        fields_.add(new StandardField(EVOLUTIONS, BeanNatCommonLgNames.TYPE_MAP, new PokemonPlayerBeanEvolutionsGet(),null));
        fields_.add(new StandardField(LEVEL, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonPlayerBeanLevelGet(),null));
        fields_.add(new StandardField(GENDER,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanGenderGet(),null));
        fields_.add(new StandardField(ABILITY,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanAbilityGet(),null));
        fields_.add(new StandardField(USED_BALL_CATCHING,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanUsedBallCatchingGet(),null));
        fields_.add(new StandardField(ITEM,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanItemGet(),null));
        fields_.add(new StandardField(REMAINING_HP,BeanNatCommonLgNames.TYPE_RATE, new PokemonPlayerBeanRemainingHpGet(),null));
        fields_.add(new StandardField(REMAINING_HP_PER_CENT,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanRemainingHpPerCentGet(),null));
        fields_.add(new StandardField(FULL_HP,BeanNatCommonLgNames.TYPE_RATE, new PokemonPlayerBeanFullHpGet(),null));
        fields_.add(new StandardField(NICKNAME,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanNicknameGet(),null));
        fields_.add(new StandardField(WON_EXP_SINCE_LAST_LEVEL,BeanNatCommonLgNames.TYPE_RATE, new PokemonPlayerBeanWonExpSinceLastLevelGet(),null));
        fields_.add(new StandardField(NECESSARY_POINTS_NEXT_LEVEL,BeanNatCommonLgNames.TYPE_RATE, new PokemonPlayerBeanNecessaryPointsNextLevelGet(),null));
        fields_.add(new StandardField(HAPPINESS, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonPlayerBeanHappinessGet(),null));
        fields_.add(new StandardField(NB_STEPS_TEAM_LEAD, BeanNatCommonLgNames.PRIM_INTEGER, new PokemonPlayerBeanNbStepsTeamLeadGet(),null));
        fields_.add(new StandardField(TYPES, BeanNatCommonLgNames.TYPE_LIST, new PokemonPlayerBeanTypesGet(),null));
        fields_.add(new StandardField(STATUS, BeanNatCommonLgNames.TYPE_LIST, new PokemonPlayerBeanStatusGet(),null));
        fields_.add(new StandardField(MOVES, BeanNatCommonLgNames.TYPE_MAP, new PokemonPlayerBeanMovesGet(),null));
        fields_.add(new StandardField(STATISTICS, BeanNatCommonLgNames.TYPE_LIST, new PokemonPlayerBeanStatisticsGet(),null));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER_BEAN, type_);
        CustList<StandardField> fieldsCompo_=new CustList<StandardField>();
        SpecialNatClass typeCompo_ = new SpecialNatClass(fieldsCompo_, new CustList<SpecNatMethod>(), BeanNatCommonLgNames.TYPE_BEAN);
        fieldsCompo_.add( new StandardField(GET_EVO,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanGetEvo(),null));
        fieldsCompo_.add( new StandardField(GET_IMG,BeanNatCommonLgNames.STRING, new PokemonPlayerBeanGetEvoImg(),null));
        _std.getStds().addEntry(TYPE_POKEMON_PLAYER_BEAN_2, typeCompo_);
    }

    public static NatArrayStruct getStPkPl(CustList<StatisticInfoPkPlayer> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (StatisticInfoPkPlayer s:_ls) {
            arr_.set(j_,new StatisticInfoPkPlayerStruct(s));
            j_++;
        }
        return arr_;
    }

    public static NatArrayStruct getTrPlNa(CustList<TrainerPlaceNames> _ls) {
        NatArrayStruct arr_ = new NatArrayStruct(_ls.size());
        int j_ = 0;
        for (TrainerPlaceNames s:_ls) {
            arr_.set(j_,new TrainerPlaceNamesStruct(s));
            j_++;
        }
        return arr_;
    }
}
