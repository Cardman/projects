package aiki.beans.fight;

import aiki.beans.*;
import aiki.beans.facade.fight.*;
import aiki.beans.game.ImgPkPlayer;
import aiki.comparators.DictionaryComparator;
import aiki.db.DataBase;
import aiki.db.MessagesDataBaseConstants;
import aiki.facade.FacadeGame;
import aiki.facade.enums.SelectedBoolean;
import aiki.fight.enums.Statistic;
import aiki.fight.items.Ball;
import aiki.fight.moves.DamagingMoveData;
import aiki.fight.moves.StatusMoveData;
import aiki.fight.moves.effects.*;
import aiki.fight.moves.effects.enums.MoveChoiceRestrictionType;
import aiki.fight.moves.enums.TargetChoice;
import aiki.fight.pokemon.NameLevel;
import aiki.fight.pokemon.PokemonData;
import aiki.fight.pokemon.enums.ExpType;
import aiki.fight.status.StatusSimple;
import aiki.fight.status.StatusType;
import aiki.fight.util.LevelMove;
import aiki.fight.util.ListEffectCombo;
import aiki.fight.util.ListEffectCombos;
import aiki.fight.util.TypesDuo;
import aiki.game.Game;
import aiki.game.UsesOfMove;
import aiki.game.fight.*;
import aiki.game.fight.util.AffectedMove;
import aiki.game.fight.util.CopiedMove;
import aiki.game.fight.util.MoveTarget;
import aiki.game.params.Difficulty;
import aiki.game.params.enums.DifficultyModelLaw;
import aiki.game.params.enums.DifficultyWinPointsFight;
import aiki.game.player.Player;
import aiki.instances.Instances;
import aiki.map.DataMap;
import aiki.map.characters.DualFight;
import aiki.map.characters.TrainerLeague;
import aiki.map.levels.enums.EnvironmentType;
import aiki.map.pokemon.PkTrainer;
import aiki.map.pokemon.PokemonPlayer;
import aiki.map.pokemon.WildPk;
import aiki.map.pokemon.enums.Gender;
import aiki.util.CoordsLists;
import aiki.util.LawNumber;
import aiki.util.TeamPositionList;
//import code.formathtml.Configuration;
import code.maths.LgInt;
import code.maths.Rate;
import code.maths.litteral.MbOperationNode;
import code.maths.montecarlo.MonteCarloNumber;
import code.scripts.pages.aiki.MessagesPkBean;
import code.sml.util.Translations;
import code.sml.util.TranslationsLg;
import code.util.*;
import code.util.core.BoolVal;

public abstract class InitDbFight extends InitDbBean {
    protected static final String SPEC = "SPEC";
    protected static final String ACC_TR = "agda";
    protected static final String ATT_TR = "aie";
    protected static final String SPE_DEF_TR = "iche";
    protected static final String SPE_ATT_TR = "io";
    protected static final String DEF_TR = "je";
    protected static final String EVA_TR = "ri";
    protected static final String HP_TR = "tit";
    protected static final String CRIT_TR = "uit";

    protected static final String NO_TEAM = "no_team";
    protected static final String NO_FIGHTER = "no_fighter";
    protected static final String NO_DISPLAY = "__";
    protected static final String SAMPLE_TYPE = "SAMPLE_TYPE";
    protected static final String SAMPLE_TYPE_TR = "sample_type";

    protected static final String M_ROQUE = "M_ROQUE";
    protected static final String M_ROQUE_TR = "m_roque";
    protected static final String M_TEAM_TR = "team_move";
    protected static final String M_CST_CHOICE_TR = "cst_choice";
    protected static final String M_USE_ACTION_TR = "mv_use_action";
    protected static final String M_TRACK_TR = "mv_track";
    protected static final String M_ACC_TR = "mv_acc";
    protected static final String M_COPY_TR = "m_copy";
    protected static final String M_ALLY_TR = "m_ally";
    protected static final String M_NB_FIGHTER_TR = "m_nb_fighter";
    protected static final String M_COUNTER_TR = "count_move";
    protected static final String M_SWITCH_TR = "sw_move";
    protected static final String M_PROT_TR = "protect_move";
    protected static final String M_UNPROT_TR = "unprotect_move";
    protected static final String M_END_TR = "move_end";
    protected static final String M_TEAM_SEND_TR = "team_move_send";
    protected static final String M_USE_TR = "use_move";
    protected static final String M_STACK_TR = "stack_move";
    protected static final String M_ANT_TR = "ant_move";
    protected static final String S_SIMPLE_TR = "st_simple";
    protected static final String S_RELATION_TR = "st_relation";
    protected static final String SPEC_TR = "spectre";
    protected static final String I_SAMPLE_TR = "item_cust";

    protected static final String M_TEAM = "M_TEAM";
    protected static final String M_CST_CHOICE = "M_CST_CHOICE";
    protected static final String M_USE_ACTION = "M_USE_ACTION";
    protected static final String M_TRACK = "M_TRACK";
    protected static final String M_ACC = "M_ACC";
    protected static final String M_COPY = "M_COPY";
    protected static final String M_ALLY = "M_ALLY";
    protected static final String M_NB_FIGHTER = "M_NB_FIGHTER";
    protected static final String M_COUNTER = "M_COUNTER";
    protected static final String M_SWITCH = "M_SWITCH";
    protected static final String M_PROT = "M_PROT";
    protected static final String M_UNPROT = "M_UNPROT";
    protected static final String M_END = "M_END";
    protected static final String M_TEAM_SEND = "M_TEAM_SEND";
    protected static final String M_USE = "M_USE";
    protected static final String M_STACK = "M_STACK";
    protected static final String M_ANT = "M_ANT";
    protected static final String S_SIMPLE = "S_SIMPLE";
    protected static final String S_RELATION = "S_RELATION";
    protected static final String I_SAMPLE = "I_SAMPLE";
    protected static final String PIKA_2 = "PIKACHU2";
    protected static final String PIKA_2_TR = "PIKACHU_TR2";
    protected static final String NICK_NA = "NICK_NA";
    protected static final int IMG_MAX_RAI = 143;
//    protected static final String IMG_MAX_RAI2 = "AAABAACQ";
    static final String NICKNAME = "CARDTEAM";

//    private static final String FIGHT="fight";
//    private static final String TEAM="team";
//    private static final String M_CLICK_FOE="clickFoe";
//    private static final String M_CLICK_PLAYER="clickPlayer";
//    private static final String M_CLICK_FIGHTER_1="clickFighter()";
    //private static final String M_CLICK_FIGHTER_2="clickFighter(,)";

    public static FightBean beanFight(String _language, FacadeGame _dataBase) {
        FightBean b_ = new FightBean();
        b_.setDataBase(_dataBase);
        b_.setLanguage(_language);
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT, MessagesPkBean.enFight());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT, MessagesPkBean.frFight());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_dataBase);
        b_.setBuilder(bu_);
        return b_;
    }

    public static String callFightCalculationBeanGetFighterWildFight(FightCalculationBean _str, int... _args) {
        return _str.getFighterWildFight(_args[0],_args[1]);
    }
    public static String callFightCalculationBeanGetFighter(FightCalculationBean _str, int... _args) {
        return _str.getFighter(_args[0]);
    }
    public static CustList<ImgMovesListTeamPositionsList> callFightCalculationBeanSortedFightersWildFightGet(FightCalculationBean _str, int... _args) {
        return _str.getSortedFightersWildFight();
    }
    public static ImgMovesListTeamPositionsList eltImg(CustList<ImgMovesListTeamPositionsList> _ls, int _v) {
        return _ls.get(_v);
    }
    public static ImgPkPlayer eltFighterImg(CustList<ImgPkPlayer> _ls, int _v) {
        return _ls.get(_v);
    }
    public static CustList<ImgPkPlayer> firstImg(ImgMovesListTeamPositionsList _e) {
        return _e.getKeyPks();
    }
    public static TeamPositionList secondImg(ImgMovesListTeamPositionsList _e) {
        return _e.getTeamPositions();
    }
    public static String callFighterNamePkNameMvNameMvGet(ImgPkPlayer _str, int... _args) {
        return _str.getKey().getTranslation();
    }

    public static int[][] callFighterNamePkNameMvNamePkGet(ImgPkPlayer _str, int... _args) {
        return _str.getImage();
    }

    public static StringList callFightCalculationBeanSortedFightersGet(FightCalculationBean _str, int... _args) {
        return _str.getSortedFighters();
    }
    public static String callFightCalculationBeanGetTargetNameAllyChoice(FightCalculationBean _str, int... _args) {
        return _str.getTargetNameAllyChoice(_args[0]);
    }

    public static String callFightCalculationBeanGetTargetNameAllyChoiceCondition(FightCalculationBean _str, int... _args) {
        return _str.getTargetNameAllyChoiceCondition(_args[0]);
    }
    public static boolean callFightCalculationBeanIsFoeTargetChoiceTeam(FightCalculationBean _str, int... _args) {
        return _str.isFoeTargetChoiceTeam(_args[0]);
    }

    public static boolean callFightCalculationBeanIsFoeTargetTeam(FightCalculationBean _str, int... _args) {
        return _str.isFoeTargetTeam(_args[0]);
    }

    public static boolean callFightCalculationBeanIsBackTargetChoiceTeam(FightCalculationBean _str, int... _args) {
        return _str.isBackTargetChoiceTeam(_args[0]);
    }

    public static boolean callFightCalculationBeanIsBackTargetTeam(FightCalculationBean _str, int... _args) {
        return _str.isBackTargetTeam(_args[0]);
    }

    public static DictionaryComparator<TrPkMoveTarget,TrPkMoveTarget> callFightCalculationBeanAllyChoiceGet(FightCalculationBean _str, int... _args) {
        return _str.getAllyChoice();
    }
    public static EntryCust<TrPkMoveTarget,TrPkMoveTarget> eltTarget(DictionaryComparator<TrPkMoveTarget,TrPkMoveTarget> _ls, int _v) {
        return _ls.getEntry(_v);
    }
    public static TrPkMoveTarget firstTarget(EntryCust<TrPkMoveTarget,TrPkMoveTarget> _e) {
        return _e.getKey();
    }
    public static int firstTargetIndex(EntryCust<TrPkMoveTarget,TrPkMoveTarget> _e) {
        return _e.getKey().getIndex();
    }
    public static TrPkMoveTarget secondTarget(EntryCust<TrPkMoveTarget,TrPkMoveTarget> _e) {
        return _e.getValue();
    }
    public static String callFightCalculationBeanGetTargetNameFoeChoice(FightCalculationBean _str, int... _args) {
        return _str.getTargetNameFoeChoice(_args[0]);
    }

    public static String callFightCalculationBeanGetFoeFighterName(FightCalculationBean _str, int... _args) {
        return _str.getFoeFighterName(_args[0]);
    }

    public static boolean callFightCalculationBeanIsChosenTarget(FightCalculationBean _str, int... _args) {
        return _str.isChosenTarget(_args[0]);
    }

    public static boolean callFightCalculationBeanIsFoeTargetChTeam(FightCalculationBean _str, int... _args) {
        return _str.isFoeTargetChTeam(_args[0]);
    }
    public static long callMoveTargetGetTarget(TrPkMoveTarget _str, int... _args) {
        return _str.getMoveTarget().getTarget().getPosition();
    }

    public static String callMoveTargetGetMove(TrPkMoveTarget _str, int... _args) {
        return _str.getMoveTarget().getMove();
    }

    public static DictionaryComparator<TrPkMoveTarget,TrPkMoveTarget> callFightCalculationBeanFoeChoicesGet(FightCalculationBean _str, int... _args) {
        return _str.getFoeChoices();
    }
    public static boolean callKeyHypothesisIsBelongsToUser(KeyHypothesis _str, int... _args) {
        return _str.isBelongsToUser();
    }

    public static String callKeyHypothesisGetMove(KeyHypothesis _str, int... _args) {
        return _str.getMove();
    }

    public static Rate callKeyHypothesisGetDamage(KeyHypothesis _str, int... _args) {
        return _str.getDamage();
    }

    public static Rate callKeyHypothesisGetDamageSecond(KeyHypothesis _str, int... _args) {
        return _str.getDamageSecond();
    }

    public static String callKeyHypothesisGetPlayerPokemon(KeyHypothesis _str, int... _args) {
        return _str.getPlayerPokemon();
    }

    public static String callKeyHypothesisGetTargetPokemon(KeyHypothesis _str, int... _args) {
        return _str.getTargetPokemon();
    }

    public static long callKeyHypothesisGetNumberPlayer(KeyHypothesis _str, int... _args) {
        return _str.getNumberPlayer();
    }

    public static long callKeyHypothesisGetNumberTarget(KeyHypothesis _str, int... _args) {
        return _str.getNumberTarget();
    }

    public static CustList<KeyHypothesis> callFightCalculationBeanDamageGet(FightCalculationBean _str, int... _args) {
        return _str.getDamage();
    }
    public static KeyHypothesis eltKeyHypothesis(CustList<KeyHypothesis> _ls, int _v) {
        return _ls.get(_v);
    }
    public FightCalculationBean beanFightCalculation(FacadeGame _fac) {
        MockBeanBuilderHelper bu_ = new MockBeanBuilderHelper();
        Translations tr_ = new Translations();
        TranslationsLg en_ = new TranslationsLg();
        en_.getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT, MessagesPkBean.enFight());
        tr_.getMapping().addEntry(EN, en_);
        TranslationsLg fr_ = new TranslationsLg();
        fr_.getMapping().addEntry(MessagesPkBean.APP_BEAN_FIGHT, MessagesPkBean.frFight());
        tr_.getMapping().addEntry(FR, fr_);
        bu_.setTranslations(tr_);
        bu_.setFacade(_fac);
        FightBean fightBean_ = new FightBean();
        fightBean_.setBuilder(bu_);
        fightBean_.setDataBase(_fac);
        fightBean_.setLanguage(EN);
        FightCalculationBean b_ = new FightCalculationBean();
        b_.setDataBase(_fac);
        b_.setLanguage(EN);
        b_.setBuilder(bu_);
        bu_.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHT_HTML,fightBean_);
        bu_.getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHTDETAIL_HTML,b_);
        fightBean_.build(_fac);
        IntBeanAction anc_ = bu_.getAnchors().get(1);
        bu_.build(anc_);
        return b_;
    }

    public static TeamBean beanTeam(String _language, FacadeGame _dataBase, FightBean _f) {
        TeamBean b_ = new TeamBean();
        b_.setDataBase(_dataBase);
        b_.setLanguage(_language);
        b_.setBuilder(_f.getBuilder());
        return b_;
    }

    protected TeamBean beanTeam(int _caller) {
        FacadeGame facade_ = facadeBigTeams(dbTeam());
        return beanTeam(_caller, facade_);
    }

    protected TeamBean beanTeam(int _caller, FacadeGame _facade) {
        FightBean bFigtht_ = beanFight(EN, _facade);
        TeamBean bTeam_ = beanTeam(EN, _facade,bFigtht_);
        bFigtht_.getBuilder().getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_FIGHT_HTML,bFigtht_);
        bFigtht_.getBuilder().getRenders().addEntry(CommonBean.WEB_FIGHT_HTML_TEAM_HTML,bTeam_);
        bFigtht_.build(_facade);
        IntBeanAction anc_ = bFigtht_.getBuilder().getAnchors().get(2 + _caller);
        bFigtht_.getBuilder().build(anc_);
        return bTeam_;
    }

    public static String navigateFightPlayer(FightBean _str, int... _args) {
        return navigateFight(Fight.CST_PLAYER, "",_str,_args);
    }

    public static String navigateFightFoe(FightBean _str, int... _args) {
        return navigateFight(Fight.CST_FOE, "",_str,_args);
    }

    public static String navigateTeamFighter(TeamBean _str, int... _args) {
        return TeamBean.clickFighter(_args[0], _str.getForms());
    }

    public static String navigateFight(int _caller, String _concat, FightBean _str, int... _args) {
//        PkScriptPagesInit.initConfFight(new NatConfigurationCore());
        return _str.click(_caller);
    }

    public static String callFighterBeanGetStatusRelatTeam(FighterBean _str, int... _args) {
        return _str.getStatusRelatTeam(_args[0]);
    }
    public static String callFighterBeanGetIncrTrappingMovesTeam(FighterBean _str, int... _args) {
        return _str.getIncrTrappingMovesTeam(_args[0]);
    }

    public static String callFighterBeanGetIncrTrackingMovesTeam(FighterBean _str, int... _args) {
        return _str.getIncrTrackingMovesTeam(_args[0]);
    }

    public static String callFighterBeanGetIncrPrivateMovesTeam(FighterBean _str, int... _args) {
        return _str.getIncrPrivateMovesTeam(_args[0]);
    }

    public static String callFighterBeanGetIncrUserAccuracyTeam(FighterBean _str, int... _args) {
        return _str.getIncrUserAccuracyTeam(_args[0]);
    }

    public static boolean callFighterBeanIsFoeTrappingMovesTeam(FighterBean _str, int... _args) {
        return _str.isFoeTrappingMovesTeam(_args[0]);
    }

    public static boolean callFighterBeanIsFoeTrackingMovesTeam(FighterBean _str, int... _args) {
        return _str.isFoeTrackingMovesTeam(_args[0]);
    }

    public static boolean callFighterBeanIsFoeStatusRelatTeam(FighterBean _str, int... _args) {
        return _str.isFoeStatusRelatTeam(_args[0]);
    }

    public static boolean callFighterBeanIsFoePrivateMovesTeam(FighterBean _str, int... _args) {
        return _str.isFoePrivateMovesTeam(_args[0]);
    }

    public static boolean callFighterBeanIsFoeIncrUserAccuracyTeam(FighterBean _str, int... _args) {
        return _str.isFoeIncrUserAccuracyTeam(_args[0]);
    }
    public static long callStatisticInfoGetEv(StatisticInfo _str, int... _args) {
        return _str.getEv();
    }

    public static long callStatisticInfoGetIv(StatisticInfo _str, int... _args) {
        return _str.getIv();
    }

    public static String callStatisticInfoGetDisplayStatistic(StatisticInfo _str, int... _args) {
        return _str.getDisplayStatistic();
    }

    public static long callStatisticInfoGetStatisBoost(StatisticInfo _str, int... _args) {
        return _str.getStatisBoost();
    }

    public static Rate callStatisticInfoGetStatisBase(StatisticInfo _str, int... _args) {
        return _str.getStatisBase();
    }

    public static boolean callStatisticInfoIsBase(StatisticInfo _str, int... _args) {
        return CommonBean.toBool(_str.base());
    }

    public static boolean callStatisticInfoIsBoost(StatisticInfo _str, int... _args) {
        return CommonBean.toBool(_str.boost());
    }
    public static StatisticInfo eltStat(CustList<StatisticInfo> _ls, int _i) {
        return _ls.get(_i);
    }
    public static CustList<StatisticInfo> callFighterBeanStatisticsGet(FighterBean _str, int... _args) {
        return _str.getStatistics();
    }
    public static String callCopiedMoveGetMove(CopiedMove _str, int... _args) {
        return _str.getMove();
    }

    public static long callCopiedMoveGetPp(CopiedMove _str, int... _args) {
        return _str.getPp();
    }

    public static Rate callMultPowerMovesGetMultSuffering(MultPowerMoves _str, int... _args) {
        return _str.getMultSuffering();
    }

    public static Rate callMultPowerMovesGetMultInflicted(MultPowerMoves _str, int... _args) {
        return _str.getMultInflicted();
    }

    public static Rate callSufferedDamageCategoryGetUsing(SufferedDamageCategory _str, int... _args) {
        return _str.getUsing();
    }

    public static Rate callSufferedDamageCategoryGetRound(SufferedDamageCategory _str, int... _args) {
        return _str.getRound();
    }

    public static NatStringTreeMap<MultPowerMoves> callFighterBeanDamageRateByTypeGet(FighterBean _str, int... _args) {
        return _str.getDamageRateByType();
    }
    public static EntryCust<String,MultPowerMoves> eltMultPowerMoves(NatStringTreeMap<MultPowerMoves> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstMultPowerMoves(EntryCust<String,MultPowerMoves> _e) {
        return _e.getKey();
    }
    public static MultPowerMoves secondMultPowerMoves(EntryCust<String,MultPowerMoves> _e) {
        return _e.getValue();
    }
    public static NatStringTreeMap<CopiedMove> callFighterBeanCopiedMovesGet(FighterBean _str, int... _args) {
        return _str.getCopiedMoves();
    }
    public static EntryCust<String,CopiedMove> eltCopiedMove(NatStringTreeMap<CopiedMove> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstCopiedMove(EntryCust<String,CopiedMove> _e) {
        return _e.getKey();
    }
    public static CopiedMove secondCopiedMove(EntryCust<String,CopiedMove> _e) {
        return _e.getValue();
    }
    public static NatStringTreeMap<SufferedDamageCategory> callFighterBeanDamageSufferedCategGet(FighterBean _str, int... _args) {
        return _str.getDamageSufferedCateg();
    }
    public static EntryCust<String,SufferedDamageCategory> eltSufferedDamageCategory(NatStringTreeMap<SufferedDamageCategory> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstSufferedDamageCategory(EntryCust<String,SufferedDamageCategory> _e) {
        return _e.getKey();
    }
    public static SufferedDamageCategory secondSufferedDamageCategory(EntryCust<String,SufferedDamageCategory> _e) {
        return _e.getValue();
    }
    public static ActivityOfMove callAffectedMoveGetActivity(AffectedMove _str, int... _args) {
        return _str.getActivity();
    }

    public static String callAffectedMoveGetMove(AffectedMove _str, int... _args) {
        return _str.getMove();
    }
    public static DictionaryComparator<MoveTeamPositionFighterName,AffectedMove> callFighterBeanTrckingMovesGet(FighterBean _str, int... _args) {
        return _str.getTrackingMoves();
    }
    public static EntryCust<MoveTeamPositionFighterName,AffectedMove> eltAffectedMove(DictionaryComparator<MoveTeamPositionFighterName,AffectedMove> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static MoveTeamPosition firstAffectedMove(EntryCust<MoveTeamPositionFighterName,AffectedMove> _e) {
        return _e.getKey().getMoveTeamPosition();
    }
    public static MoveTeamPosition firstActivityString(EntryCust<MoveTeamPositionFighterName,String> _e) {
        return _e.getKey().getMoveTeamPosition();
    }
    public static String secondActivityString(EntryCust<MoveTeamPositionFighterName,String> _e) {
        return _e.getValue();
    }

    public static MoveTeamPosition firstActivityOfMoveStill(EntryCust<MoveTeamPositionFighterName,ActivityOfMoveStill> _e) {
        return _e.getKey().getMoveTeamPosition();
    }
    public static AffectedMove secondAffectedMove(EntryCust<MoveTeamPositionFighterName,AffectedMove> _e) {
        return _e.getValue();
    }
    public static DictionaryComparator<MoveTeamPositionFighterName,ActivityOfMoveStill> callFighterBeanTrappingMovesGet(FighterBean _str, int... _args) {
        return _str.getTrappingMoves();
    }
    public static EntryCust<MoveTeamPositionFighterName,ActivityOfMoveStill> eltActivityOfMoveStill(DictionaryComparator<MoveTeamPositionFighterName,ActivityOfMoveStill> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static EntryCust<String,ActivityOfMoveStill> eltActivityOfMove(NatStringTreeMap<ActivityOfMoveStill> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstActivityOfMove(EntryCust<String,ActivityOfMoveStill> _e) {
        return _e.getKey();
    }
    public static ActivityOfMove secondActivityOfMove(EntryCust<String,ActivityOfMoveStill> _e) {
        return _e.getValue().getActivity();
    }
    public static EntryCust<StringList,ActivityOfMove> eltActivityOfMove2(DictionaryComparator<StringList, ActivityOfMove> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static StringList firstActivityOfMove2(EntryCust<StringList,ActivityOfMove> _e) {
        return _e.getKey();
    }
    public static ActivityOfMove secondActivityOfMove2(EntryCust<StringList,ActivityOfMove> _e) {
        return _e.getValue();
    }
    public static DictionaryComparator<MoveTeamPositionFighterName,String> callFighterBeanPrivateMovesGet(FighterBean _str, int... _args) {
        return _str.getPrivateMoves();
    }
    public static EntryCust<MoveTeamPositionFighterName,String> eltString(DictionaryComparator<MoveTeamPositionFighterName,String> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static DictionaryComparator<MoveTeamPositionFighterName,Long> callFighterBeanStatusRelatGet(FighterBean _str, int... _args) {
        return _str.getStatusRelat();
    }
    public static EntryCust<MoveTeamPositionFighterName,Long> eltLong(DictionaryComparator<MoveTeamPositionFighterName,Long> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static MoveTeamPosition firstActivityLong(EntryCust<MoveTeamPositionFighterName,Long> _e) {
        return _e.getKey().getMoveTeamPosition();
    }
    public static Long secondActivityLong(EntryCust<MoveTeamPositionFighterName,Long> _e) {
        return _e.getValue();
    }
    public static NatStringTreeMap<ActivityOfMoveStill> callFighterBeanEnabledMovesGet(FighterBean _str, int... _args) {
        return _str.getEnabledMoves();
    }
    public static NatStringTreeMap<Long> callFighterBeanStatusGet(FighterBean _str, int... _args) {
        return _str.getStatus();
    }
    public static NatStringTreeMap<Long> callFighterBeanNbUsesMovesGet(FighterBean _str, int... _args) {
        return _str.getNbUsesMoves();
    }
    public static EntryCust<String,Long> eltStrNb(NatStringTreeMap<Long> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstStrNb(EntryCust<String,Long> _e) {
        return _e.getKey();
    }
    public static Long secondStrNb(EntryCust<String,Long> _e) {
        return _e.getValue();
    }
    public static NatStringTreeMap<UsesOfMove> callFighterBeanMovesGet(FighterBean _str, int... _args) {
        return _str.getMoves();
    }

    public static NatStringTreeMap<UsesOfMove> callFighterBeanCurrentMovesGet(FighterBean _str, int... _args) {
        return _str.getCurrentMoves();
    }
    public static EntryCust<String,UsesOfMove> eltUsesOfMove(NatStringTreeMap<UsesOfMove> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstUsesOfMove(EntryCust<String,UsesOfMove> _e) {
        return _e.getKey();
    }
    public static UsesOfMove secondUsesOfMove(EntryCust<String,UsesOfMove> _e) {
        return _e.getValue();
    }
    public static StringList callFighterBeanLastSufferedMoveTypesGet(FighterBean _str, int... _args) {
        return _str.getLastSufferedMoveTypes();
    }

    public static StringList callFighterBeanProtectedAgainstMoveTypesGet(FighterBean _str, int... _args) {
        return _str.getProtectedAgainstMoveTypes();
    }

    public static StringList callFighterBeanTypesGet(FighterBean _str, int... _args) {
        return _str.getTypes();
    }

    public static StringList callFighterBeanAlreadyInvokedMovesRoundGet(FighterBean _str, int... _args) {
        return _str.getAlreadyInvokedMovesRound();
    }
    public static Rate callFighterBeanWonExpSinceLastLevelGet(FighterBean _str, int... _args) {
        return _str.getWonExpSinceLastLevel();
    }

    public static Rate callFighterBeanNecessaryPointsNextLevelGet(FighterBean _str, int... _args) {
        return _str.getNecessaryPointsNextLevel();
    }
    public static long callFighterFighterBeanHappinessGet(FighterBean _str, int... _args) {
        return _str.getHappiness();
    }

    public static String callFighterBeanCloneStrGet(FighterBean _str, int... _args) {
        return _str.getCloneStr();
    }

    public static Rate callFighterBeanCloneGet(FighterBean _str, int... _args) {
        return _str.getClone();
    }

    public static String callFighterBeanHeightStrGet(FighterBean _str, int... _args) {
        return _str.getHeightStr();
    }

    public static Rate callFighterBeanHeightGet(FighterBean _str, int... _args) {
        return _str.getHeight();
    }

    public static String callFighterBeanWeightStrGet(FighterBean _str, int... _args) {
        return _str.getWeightStr();
    }

    public static Rate callFighterBeanWeightGet(FighterBean _str, int... _args) {
        return _str.getWeight();
    }

    public static String callFighterBeanRemainingHpStrGet(FighterBean _str, int... _args) {
        return _str.getRemainingHpStr();
    }

    public static Rate callFighterBeanRemainingHpGet(FighterBean _str, int... _args) {
        return _str.getRemainingHp();
    }

    public static Rate callFighterBeanFullHpGet(FighterBean _str, int... _args) {
        return _str.getMaxHp();
    }

    public static String callFighterBeanRemainingHpStrPerCentGet(FighterBean _str, int... _args) {
        return _str.getRemainingHpStrPerCent();
    }
    public static long callFighterBeanLevelGet(FighterBean _str, int... _args) {
        return _str.getLevel();
    }

    public static long callFighterBeanNbPrepaRoundGet(FighterBean _str, int... _args) {
        return _str.getNbPrepaRound();
    }

    public static LgInt callFighterBeanNbRoundsGet(FighterBean _str, int... _args) {
        return _str.getNbRounds();
    }


    public static LgInt callFighterBeanNbRepeatingSuccessfulMovesGet(FighterBean _str, int... _args) {
        return _str.getNbRepeatingSuccessfulMoves();
    }

    public static String callFighterBeanLastUsedMoveGet(FighterBean _str, int... _args) {
        return _str.getLastUsedMove();
    }

    public static String callFighterBeanUsedMoveLastRoundGet(FighterBean _str, int... _args) {
        return _str.getUsedMoveLastRound();
    }

    public static String callFighterBeanLastSufferedMoveGet(FighterBean _str, int... _args) {
        return _str.getLastSufferedMove();
    }
    public static String callFighterBeanLastSuccessfulMoveGet(FighterBean _str, int... _args) {
        return _str.getLastSuccessfulMove();
    }
    public static String callFighterBeanLastUsedItemGet(FighterBean _str, int... _args) {
        return _str.getLastUsedItem();
    }

    public static String callFighterBeanExpItemGet(FighterBean _str, int... _args) {
        return _str.getExpItem();
    }

    public static String callFighterBeanItemGet(FighterBean _str, int... _args) {
        return _str.getItem();
    }

    public static String callFighterBeanAbilityGet(FighterBean _str, int... _args) {
        return _str.getAbility();
    }

    public static String callFighterBeanCurrentAbilityGet(FighterBean _str, int... _args) {
        return _str.getCurrentAbility();
    }

    public static String callFighterBeanCurrentNameGet(FighterBean _str, int... _args) {
        return _str.getCurrentName();
    }

    public static String callFighterBeanCurrentGenderGet(FighterBean _str, int... _args) {
        return _str.getCurrentGender();
    }

    public static String callFighterBeanGenderGet(FighterBean _str, int... _args) {
        return _str.getGender();
    }

    public static long callFighterBeanGroundPlaceSubstGet(FighterBean _str, int... _args) {
        return _str.getGroundPlaceSubst();
    }

    public static long callFighterBeanGroundPlaceGet(FighterBean _str, int... _args) {
        return _str.getGroundPlace();
    }

    public static String callFighterBeanUsedBallCatchingGet(FighterBean _str, int... _args) {
        return _str.getUsedBallCatching();
    }

    public static String callFighterBeanNicknameGet(FighterBean _str, int... _args) {
        return _str.getNickname();
    }
    public static String callMoveTeamPositionGetMove(MoveTeamPosition _str, int... _args) {
        return _str.getMove();
    }
    public static DictionaryComparator<MoveTeamPositionFighterName, Integer> callFighterBeanIncrUserAccuracyGet(FighterBean _str, int... _args) {
        return _str.getIncrUserAccuracy();
    }
    public static EntryCust<MoveTeamPositionFighterName,Integer> eltIntAcc(DictionaryComparator<MoveTeamPositionFighterName,Integer> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static MoveTeamPosition firstActivityInt(EntryCust<MoveTeamPositionFighterName,Integer> _e) {
        return _e.getKey().getMoveTeamPosition();
    }
    public static boolean secondActivityInt(EntryCust<MoveTeamPositionFighterName,Integer> _e) {
        return CommonBean.toBool(_e.getValue());
    }
    public static EntryCust<String,Integer> eltStringInt(NatStringTreeMap<Integer> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstStringInt(EntryCust<String,Integer> _e) {
        return _e.getKey();
    }
    public static boolean secondStringInt(EntryCust<String,Integer> _e) {
        return CommonBean.toBool(_e.getValue());
    }
    public static NatStringTreeMap<Integer> callFighterBeanEnabledMovesForAllyGet(FighterBean _str, int... _args) {
        return _str.getEnabledMovesForAlly();
    }
    public static boolean callFighterBeanBelongingToPlayerGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getBelongingToPlayer());
    }
    public static boolean callFighterBeanIsBackSubst(FighterBean _str, int... _args) {
        return _str.isBackSubst();
    }
    public static boolean callFighterBeanIsBack(FighterBean _str, int... _args) {
        return _str.isBack();
    }
    public static boolean callFighterBeanIsEnabled(FighterBean _str, int... _args) {
        return _str.isEnabled(_args[0]);
    }

    public static boolean callFighterBeanChangedGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getChanged());
    }

    public static boolean callFighterBeanActedGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getActed());
    }

    public static boolean callFighterBeanSuccessfulMoveGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getSuccessfulMove());
    }

    public static boolean callFighterBeanUsingItemGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getUsingItem());
    }

    public static boolean callFighterBeanDisappearedGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getDisappeared());
    }

    public static boolean callFighterBeanNeedingToRechargeGet(FighterBean _str, int... _args) {
        return CommonBean.toBool(_str.getNeedingToRecharge());
    }
    public static String callFighterBeanNameGet(FighterBean _str, int... _args) {
        return _str.getName();
    }
    public static IntTreeMap<FighterAgainstFoes> callTeamBeanPlayerFightersAgainstFoeGet(TeamBean _str, int... _args) {
        return _str.getPlayerFightersAgainstFoe();
    }
    public static EntryCust<Integer,FighterAgainstFoes> eltFighterAgainstFoes(IntTreeMap<FighterAgainstFoes> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static Integer firstFighterAgainstFoes(EntryCust<Integer,FighterAgainstFoes> _ls) {
        return _ls.getKey();
    }
    public static IntMap<String> secondFighterAgainstFoes(EntryCust<Integer,FighterAgainstFoes> _ls) {
        return _ls.getValue().getFoes();
    }
    public static int eltIntFighterAgainstFoes(IntMap<String> _ls, int _i) {
        return _ls.getKey(_i);
    }
    public static String callTeamBeanGetPlayerFigtherAgainstFoe(TeamBean _str, int... _args) {
        return _str.getPlayerFigtherAgainstFoe(_args[0]);
    }

    public static String callTeamBeanGetFoeFigtherAgainstFoe(TeamBean _str, int... _args) {
        return _str.getFoeFigtherAgainstFoe(_args[0],_args[1]);
    }

    public static NatStringTreeMap<ActivityOfMoveStill> callTeamBeanEnabledMovesGet(TeamBean _str, int... _args) {
        return _str.getEnabledMoves();
    }

    public static DictionaryComparator<StringList, ActivityOfMove> callTeamBeanEnabledMovesByGroupGet(TeamBean _str, int... _args) {
        return _str.getEnabledMovesByGroup();
    }

    public static String callTeamBeanGetKey(TeamBean _str, int... _args) {
        return _str.getKey(_args[0]);
    }

    public static long callStacksOfUsesGetNbRounds(StacksOfUses _str, int... _args) {
        return _str.getNbRounds();
    }

    public static boolean callStacksOfUsesIsFirstStacked(StacksOfUses _str, int... _args) {
        return _str.isFirstStacked();
    }

    public static boolean callStacksOfUsesIsLastStacked(StacksOfUses _str, int... _args) {
        return _str.isLastStacked();
    }

    public static long callAnticipationGetTargetPositionValue(Anticipation _str, int... _args) {
        return _str.getTargetPosition().getPosition();
    }

    public static Rate callAnticipationGetDamage(Anticipation _str, int... _args) {
        return _str.getDamage();
    }

    public static long callAnticipationGetNbRounds(Anticipation _str, int... _args) {
        return _str.getNbRounds();
    }

    public static boolean callAnticipationIsIncrementing(Anticipation _str, int... _args) {
        return _str.isIncrementing();
    }

    public static NatStringTreeMap<IntTreeMap<Anticipation>> callTeamBeanMovesAnticipationGet(TeamBean _str, int... _args) {
        return _str.getMovesAnticipation();
    }
    public static EntryCust<String,IntTreeMap<Anticipation>> eltAnt(NatStringTreeMap<IntTreeMap<Anticipation>> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstAnt(EntryCust<String,IntTreeMap<Anticipation>> _ls) {
        return _ls.getKey();
    }
    public static IntTreeMap<Anticipation> secondAnt(EntryCust<String,IntTreeMap<Anticipation>> _ls) {
        return _ls.getValue();
    }
    public static EntryCust<Integer, Anticipation> eltAnt2(IntTreeMap<Anticipation> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static Integer firstAnt2(EntryCust<Integer,Anticipation> _ls) {
        return _ls.getKey();
    }
    public static Anticipation secondAnt2(EntryCust<Integer,Anticipation> _ls) {
        return _ls.getValue();
    }
    public static boolean callTeamBeanIsBackMovesAnticipationTeam(TeamBean _str, int... _args) {
        return _str.isBackMovesAnticipationTeam(_args[0],_args[1]);
    }

    public static boolean callTeamBeanIsFoeMovesAnticipationTeam(TeamBean _str, int... _args) {
        return _str.isFoeMovesAnticipationTeam(_args[0],_args[1]);
    }

    public static boolean callTeamBeanIsPlayerMovesAnticipationTeam(TeamBean _str, int... _args) {
        return _str.isPlayerMovesAnticipationTeam(_args[0],_args[1]);
    }
    public static NatStringTreeMap<IntTreeMap<StacksOfUses>> callTeamBeanTeamBeanHealAfterGet(TeamBean _str, int... _args) {
        return _str.getHealAfter();
    }
    public static EntryCust<String,IntTreeMap<StacksOfUses>> eltHeal(NatStringTreeMap<IntTreeMap<StacksOfUses>> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstHeal(EntryCust<String,IntTreeMap<StacksOfUses>> _ls) {
        return _ls.getKey();
    }
    public static IntTreeMap<StacksOfUses> secondHeal(EntryCust<String,IntTreeMap<StacksOfUses>> _ls) {
        return _ls.getValue();
    }
    public static EntryCust<Integer, StacksOfUses> eltHeal2(IntTreeMap<StacksOfUses> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static Integer firstHeal2(EntryCust<Integer,StacksOfUses> _ls) {
        return _ls.getKey();
    }
    public static StacksOfUses secondHeal2(EntryCust<Integer,StacksOfUses> _ls) {
        return _ls.getValue();
    }
    public static NatStringTreeMap<LgInt> callTeamBeanEnabledMovesWhileSendingFoeUsesGet(TeamBean _str, int... _args) {
        return _str.getEnabledMovesWhileSendingFoeUses();
    }
    public static EntryCust<String, LgInt> eltLgInt(NatStringTreeMap<LgInt> _ls, int _i) {
        return _ls.getEntry(_i);
    }
    public static String firstLgInt(EntryCust<String,LgInt> _ls) {
        return _ls.getKey();
    }
    public static LgInt secondLgInt(EntryCust<String,LgInt> _ls) {
        return _ls.getValue();
    }
    public static NatStringTreeMap<Long> callTeamBeanNbUsesMovesGet(TeamBean _str, int... _args) {
        return _str.getNbUsesMoves();
    }
    public static String callTeamBeanGetTrPokemonLink(TeamBean _str, int... _args) {
        return _str.getTrPokemonLink(_args[0]);
    }

    public static CustList<Integer> callTeamBeanGetMembers(TeamBean _str, int... _args) {
        return _str.getMembers().getKeys();
    }
    public static boolean callTeamBeanFoeTeamGet(TeamBean _str, int... _args) {
        return _str.getFoeTeam();
    }

    public static long callFightBeanMultGet(FightBean _str, int... _args) {
        return _str.getMult();
    }

    public static NatStringTreeMap<ActivityOfMoveStill> callFightBeanEnabledMovesGet(FightBean _str, int... _args) {
        return _str.getEnabledMoves();
    }

    public static boolean callFightBeanIsStillEnabled(FightBean _str, int... _args) {
        return _str.isStillEnabled(_args[0]);
    }

    public static Rate callFightBeanWinningMoneyGet(FightBean _str, int... _args) {
        return _str.getWinningMoney();
    }

    public static LgInt callFightBeanNbRoundsGet(FightBean _str, int... _args) {
        return _str.getNbRounds();
    }

    public static long callFightBeanNbFleeAttemptGet(FightBean _str, int... _args) {
        return _str.getNbFleeAttempt();
    }

    public static long callActivityOfMoveGetNbTurn(ActivityOfMove _str, int... _args) {
        return _str.getNbTurn();
    }

    public static boolean callActivityOfMoveIsEnabled(ActivityOfMove _str, int... _args) {
        return _str.isEnabled();
    }

    public static boolean callActivityOfMoveIsIncrementCount(ActivityOfMove _str, int... _args) {
        return _str.isIncrementCount();
    }

    public static long callUsesOfMoveGetCurrent(UsesOfMove _str, int... _args) {
        return _str.getCurrent();
    }

    public static long callUsesOfMoveGetMax(UsesOfMove _str, int... _args) {
        return _str.getMax();
    }

    protected void update(FacadeGame _facade, CommonBean _bean) {
        _bean.setDataBase(_facade);
        _bean.setLanguage(EN);
    }

    protected DataBase dbFighter() {
        DataBase data_ = dbBase();
        secondPk(data_);
        DamagingMoveData damage_ = Instances.newDamagingMoveData();
        damage_.setCategory(SPEC);
        EffectDamage effDam_ = Instances.newEffectDamage();
        effDam_.setPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_LANCEUR_NB_UTILISATION +DataBase.SEP_BETWEEN_KEYS+ M_NB_FIGHTER);
        damage_.getEffects().add(effDam_);
        data_.completeMembers(M_NB_FIGHTER, damage_);
        StatusMoveData mrestrdef_ = Instances.newStatusMoveData();
        mrestrdef_.getEffects().add(Instances.newEffectRestriction());
        data_.completeMembers(M_TEAM, mrestrdef_);
        StatusMoveData mrestrcst_ = Instances.newStatusMoveData();
        EffectRestriction rcst_ = Instances.newEffectRestriction();
        rcst_.setChoiceRestriction(MoveChoiceRestrictionType.LANCEUR_ATTAQUES);
        mrestrcst_.getEffects().add(rcst_);
        mrestrcst_.setConstUserChoice(true);
        mrestrcst_.getRepeatRoundLaw().addQuickEvent(Rate.one(), LgInt.one());
        data_.completeMembers(M_CST_CHOICE, mrestrcst_);
        StatusMoveData mrestract_ = Instances.newStatusMoveData();
        EffectRestriction ract_ = Instances.newEffectRestriction();
        ract_.setChoiceRestriction(MoveChoiceRestrictionType.FORCE);
        mrestract_.getEffects().add(ract_);
        data_.completeMembers(M_USE_ACTION, mrestract_);
        StatusMoveData mcounter_ = Instances.newStatusMoveData();
        mcounter_.getEffects().add(Instances.newEffectCounterAttack());
        data_.completeMembers(M_COUNTER, mcounter_);
        StatusMoveData msw_ = Instances.newStatusMoveData();
        msw_.getEffects().add(Instances.newEffectSwitchMoveTypes());
        data_.completeMembers(M_SWITCH, msw_);
        StatusMoveData mun_ = Instances.newStatusMoveData();
        mun_.getEffects().add(Instances.newEffectUnprotectFromTypes());
        data_.completeMembers(M_UNPROT, mun_);
        StatusMoveData mpr_ = Instances.newStatusMoveData();
        mpr_.getEffects().add(Instances.newEffectProtectFromTypes());
        data_.completeMembers(M_PROT, mpr_);
        StatusMoveData mi_ = Instances.newStatusMoveData();
        mi_.getEffects().add(Instances.newEffectEndRoundIndividual());
        data_.completeMembers(M_END, mi_);
        StatusMoveData mtrack_ = Instances.newStatusMoveData();
        mtrack_.getEffects().add(Instances.newEffectEndRoundSingleRelation());
        data_.completeMembers(M_TRACK, mtrack_);
        StatusMoveData mac_ = Instances.newStatusMoveData();
        mac_.getEffects().add(Instances.newEffectAccuracy());
        data_.completeMembers(M_ACC, mac_);
        StatusMoveData mcopy_ = Instances.newStatusMoveData();
        EffectCopyMove efcp_ = Instances.newEffectCopyMove();
        efcp_.setCopyingMoveForUser( 1);
        mcopy_.getEffects().add(efcp_);
        data_.completeMembers(M_COPY, mcopy_);
        StatusMoveData mally_ = Instances.newStatusMoveData();
        mally_.getEffects().add(Instances.newEffectAlly());
        data_.completeMembers(M_ALLY, mally_);
        StatusSimple simple_ = Instances.newStatusSimple();
        simple_.setStatusType(StatusType.INDIVIDUEL);
        data_.completeMembers(S_SIMPLE,simple_);
        StatusSimple relation_ = Instances.newStatusSimple();
        relation_.setStatusType(StatusType.RELATION_UNIQUE);
        data_.completeMembers(S_RELATION,relation_);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_NB_FIGHTER, M_NB_FIGHTER_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM, M_TEAM_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_CST_CHOICE, M_CST_CHOICE_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_USE_ACTION, M_USE_ACTION_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_COUNTER, M_COUNTER_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_SWITCH, M_SWITCH_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_UNPROT, M_UNPROT_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_PROT, M_PROT_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_END, M_END_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TRACK, M_TRACK_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ACC, M_ACC_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_COPY, M_COPY_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ALLY, M_ALLY_TR);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(NULL_REF, NO_DISPLAY);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(SAMPLE_TYPE, SAMPLE_TYPE_TR);
        data_.getTranslatedTypes().getVal(LANGUAGE).addEntry(ELECTRICK, ELECTRICK_TR);
        data_.getTranslatedItems().getVal(LANGUAGE).addEntry(I_SAMPLE, I_SAMPLE_TR);
        data_.getTranslatedStatus().getVal(LANGUAGE).addEntry(S_SIMPLE, S_SIMPLE_TR);
        data_.getTranslatedStatus().getVal(LANGUAGE).addEntry(S_RELATION, S_RELATION_TR);
        data_.getTranslatedCategories().getVal(LANGUAGE).clear();
        data_.getTranslatedCategories().getVal(LANGUAGE).addEntry(SPEC, SPEC_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.ATTACK, ATT_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.DEFENSE, DEF_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.SPECIAL_ATTACK, SPE_ATT_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.SPECIAL_DEFENSE, SPE_DEF_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.EVASINESS, EVA_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.ACCURACY, ACC_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.HP, HP_TR);
        data_.getTranslatedStatistics().getVal(LANGUAGE).set(Statistic.CRITICAL_HIT, CRIT_TR);
        data_.getTypes().add(NULL_REF);
        data_.getTypes().add(SAMPLE_TYPE);
        data_.getTypes().add(ELECTRICK);
        data_.completeVariables();
        return data_;
    }
    protected DataBase dbTeam() {
        DataBase data_ = dbBase();
        secondPk(data_);
        StatusMoveData mteam_ = Instances.newStatusMoveData();
        mteam_.getEffects().add(Instances.newEffectTeam());
        data_.completeMembers(M_TEAM, mteam_);
        StatusMoveData send_ = Instances.newStatusMoveData();
        send_.getEffects().add(Instances.newEffectTeamWhileSendFoe());
        data_.completeMembers(M_TEAM_SEND, send_);
        DamagingMoveData used_ = Instances.newDamagingMoveData();
        used_.setCategory(SPEC);
        EffectDamage dam_ = Instances.newEffectDamage();
        dam_.setPower(VAR_PREFIX+ MessagesDataBaseConstants.DEF_EQUIPE_NB_UTILISATION +DataBase.SEP_BETWEEN_KEYS+M_USE);
        used_.getEffects().add(dam_);
        data_.completeMembers(M_USE, used_);
        StatusMoveData heal_ = Instances.newStatusMoveData();
        heal_.getEffects().add(Instances.newEffectEndRoundPositionRelation());
        data_.completeMembers(M_STACK,heal_);
        StatusMoveData ant_ = Instances.newStatusMoveData();
        ant_.getEffects().add(Instances.newEffectEndRoundPositionTargetRelation());
        data_.completeMembers(M_ANT,ant_);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM, M_TEAM_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_TEAM_SEND, M_TEAM_SEND_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_USE, M_USE_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_STACK, M_STACK_TR);
        data_.getTranslatedMoves().getVal(LANGUAGE).addEntry(M_ANT, M_ANT_TR);
        data_.getCombos().getEffects().add(new ListEffectCombo(new StringList(M_TEAM,M_TEAM_SEND),Instances.newEffectCombo()));
        data_.completeVariables();
        return data_;
    }

    private void secondPk(DataBase _data) {
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKA_2);
        pkData_.setEggGroups(new StringList(_data.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove(1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate( 1);
        pkData_.setHappiness( 1);
        pkData_.setHappinessHatch( 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        _data.completeMembers(PIKA_2,pkData_);
        StringMap<String> trPks_ = new StringMap<String>();
        trPks_.addEntry(PIKACHU,PIKACHU_TR);
        trPks_.addEntry(PIKA_2, PIKA_2_TR);
        _data.getTranslatedPokemon().clear();
        _data.getTranslatedPokemon().addEntry(LANGUAGE,trPks_);
    }

    protected DataBase db() {
        DataBase data_ = dbBase();
        data_.completeVariables();
        return data_;
    }

    protected DataBase dbBaseCalcRoque() {
        DataBase data_ = dbBaseCalcCom();
        StatusMoveData al_ = Instances.newStatusMoveData();
        al_.setPp( 20);
        EffectSwitchPosition e_ = Instances.newEffectSwitchPosition();
        e_.setTargetChoice(TargetChoice.ALLIE);
        al_.setTargetChoice(TargetChoice.ALLIE);
        al_.getEffects().add(e_);
        data_.completeMembers(M_ROQUE, al_);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_ROQUE, M_ROQUE_TR);
        data_.completeVariables();
        return data_;
    }
    protected DataBase dbBaseCalc() {
        DataBase data_ = dbBaseCalcCom();
        data_.completeVariables();
        return data_;
    }

    private DataBase dbBaseCalcCom() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        data_.completeMembers(ECLAIR, damMoveAcc(TargetChoice.ANY_FOE, "40"));
        data_.completeMembers(CHARGE, damMoveAcc(TargetChoice.TOUS_ADV, "20"));
        data_.completeMembers(M_TEAM, damMoveAcc(TargetChoice.ADJ_ADV, "15"));
        data_.completeMembers(CHARGE2, damMoveAcc(TargetChoice.TOUS_ADV, "25"));
        data_.getTypes().add(ELECTRICK);
        data_.completeMembers(PIKACHU,pkData(data_, PIKACHU));
        data_.completeMembers(PIKA_2,pkData(data_, PIKA_2));
        data_.completeMembers(PARATONNERRE,Instances.newAbilityData());
        data_.getTableTypes().addEntry(new TypesDuo(ELECTRICK,ELECTRICK),Rate.one());
        data_.addConstNumTest(DataBase.PP_MAX,Rate.newRate("256"));
        data_.addConstNumTest(DataBase.DEF_MAX_ATT,Rate.newRate("2"));
        data_.getTranslatedPokemon().addEntry(EN,new StringMap<String>());
        data_.getTranslatedPokemon().getVal(EN).addEntry(PIKACHU,PIKACHU_TR);
        data_.getTranslatedPokemon().getVal(EN).addEntry(PIKA_2, PIKA_2_TR);
        data_.getTranslatedMoves().addEntry(EN,new StringMap<String>());
        data_.getTranslatedMoves().getVal(EN).addEntry(ECLAIR,ECLAIR_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(CHARGE,CHARGE_TR);
        data_.getTranslatedMoves().getVal(EN).addEntry(CHARGE2,CHARGE_TR2);
        data_.getTranslatedMoves().getVal(EN).addEntry(M_TEAM,M_TEAM_TR);
        data_.getCombos().setEffects(new ListEffectCombos());
        data_.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MIN,new LawNumber(law(),1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.CROISSANT,new LawNumber(law(),1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.UNIFORME,new LawNumber(law(),1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.DECROISSANT,new LawNumber(law(),1));
        data_.getLawsDamageRate().put(DifficultyModelLaw.CONSTANT_MAX,new LawNumber(law(),5));
        return data_;
    }

    private MonteCarloNumber law() {
        MonteCarloNumber monteCarloNumber_ = new MonteCarloNumber();
        monteCarloNumber_.addQuickEvent(new Rate("1"),new LgInt("1"));
        return monteCarloNumber_;
    }

    private PokemonData pkData(DataBase _data, String _base) {
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(_base);
        pkData_.setEggGroups(new StringList(_data.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove(1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate( 1);
        pkData_.setHappiness( 1);
        pkData_.setHappinessHatch( 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        return pkData_;
    }

    private DamagingMoveData damMoveAcc(TargetChoice _target, String _power) {
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp( 20);
        move_.setCategory(SPEC);
        move_.setAccuracy("1");
        move_.setTypes(new StringList(ELECTRICK));
        move_.setTargetChoice(_target);
        EffectDamage one_ = Instances.newEffectDamage();
        one_.setPower(_power);
        one_.setTargetChoice(_target);
        move_.getEffects().add(one_);
        return move_;
    }

    private DataBase dbBase() {
        DataBase data_ = newData();
        updateLg(data_);
        data_.initializeMembers();
        DamagingMoveData move_ = Instances.newDamagingMoveData();
        move_.setPp( 20);
        move_.setCategory(SPEC);
        data_.completeMembers(ECLAIR, move_);
        StatusMoveData sta_ = Instances.newStatusMoveData();
        EffectGlobal egl_ = Instances.newEffectGlobal();
        egl_.setWeather(true);
        sta_.getEffects().add(egl_);
        data_.completeMembers(CHARGE, sta_);
        StatusMoveData staTmp_ = Instances.newStatusMoveData();
        staTmp_.getEffects().add(Instances.newEffectGlobal());
        data_.completeMembers(CHARGE2, staTmp_);
        PokemonData pkData_ = Instances.newPokemonData();
        pkData_.setBaseEvo(PIKACHU);
        pkData_.setEggGroups(new StringList(data_.getDefaultEggGroup()));
        pkData_.setTypes(new StringList(ELECTRICK));
        statBase(pkData_);
        pkData_.getLevMoves().add(new LevelMove(1,ECLAIR));
        pkData_.getLevMoves().add(new LevelMove(1,CHARGE));
        pkData_.setExpRate(1);
        pkData_.setHeight(Rate.one());
        pkData_.setWeight(Rate.one());
        pkData_.setCatchingRate( 1);
        pkData_.setHappiness( 1);
        pkData_.setHappinessHatch( 1);
        pkData_.setAbilities(new StringList(PARATONNERRE));
        data_.completeMembers(PIKACHU,pkData_);
        Ball ball_ = Instances.newBall();
        data_.completeMembers(POKE_BALL,ball_);
        data_.getMiniPk().addEntry(PIKACHU,instance(new int[1][1]));
        data_.getMaxiPkBack().addEntry(PIKACHU,instance(new int[1][1]));
        data_.getMaxiPkFront().addEntry(PIKACHU,instance(new int[1][1]));
        IdMap<Gender, String> gdrs_ = new IdMap<Gender, String>();
        gdrs_.addEntry(Gender.NO_GENDER, NO_G);
        gdrs_.addEntry(Gender.FEMALE,"FE");
        gdrs_.addEntry(Gender.MALE,"MA");
        data_.getTranslatedGenders().addEntry(LANGUAGE, gdrs_);
        IdMap<SelectedBoolean, String> bools_ = new IdMap<SelectedBoolean, String>();
        bools_.addEntry(SelectedBoolean.NO,"NO");
        bools_.addEntry(SelectedBoolean.YES,"YES");
        bools_.addEntry(SelectedBoolean.YES_AND_NO,"YES_AND_NO");
        data_.getTranslatedBooleans().addEntry(LANGUAGE, bools_);
        IdMap<DifficultyWinPointsFight, String> wfn_ = new IdMap<DifficultyWinPointsFight, String>();
        wfn_.addEntry(DifficultyWinPointsFight.TRES_DIFFICILE,"TRES_DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.DIFFICILE,"DIFFICILE");
        wfn_.addEntry(DifficultyWinPointsFight.FACILE,"FACILE");
        wfn_.addEntry(DifficultyWinPointsFight.TRES_FACILE,"TRES_FACILE");
        data_.getTranslatedDiffWinPts().addEntry(LANGUAGE, wfn_);
        IdMap<DifficultyModelLaw, String> ml_ = new IdMap<DifficultyModelLaw, String>();
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MIN,"CONSTANT_MIN");
        ml_.addEntry(DifficultyModelLaw.CROISSANT,"CROISSANT");
        ml_.addEntry(DifficultyModelLaw.UNIFORME,"UNIFORME");
        ml_.addEntry(DifficultyModelLaw.DECROISSANT,"DECROISSANT");
        ml_.addEntry(DifficultyModelLaw.CONSTANT_MAX,"CONSTANT_MAX");
        data_.getTranslatedDiffModelLaw().addEntry(LANGUAGE, ml_);
        IdMap<EnvironmentType, String> et_ = new IdMap<EnvironmentType, String>();
        et_.addEntry(EnvironmentType.ROAD,"ROAD");
        et_.addEntry(EnvironmentType.ROCK,"ROCK");
        et_.addEntry(EnvironmentType.NOTHING,"NOTHING");
        et_.addEntry(EnvironmentType.BUILDING,"BUILDING");
        et_.addEntry(EnvironmentType.GRASS,"GRASS");
        et_.addEntry(EnvironmentType.DESERT,"DESERT");
        et_.addEntry(EnvironmentType.ICE,"ICE");
        et_.addEntry(EnvironmentType.SNOW,"SNOW");
        et_.addEntry(EnvironmentType.WATER,"WATER");
        data_.getTranslatedEnvironment().addEntry(LANGUAGE, et_);
        IdMap<Statistic, String> stats_ = new IdMap<Statistic, String>();
        stats_.addEntry(Statistic.ATTACK,"ATTACK");
        stats_.addEntry(Statistic.SPECIAL_ATTACK,"SPECIAL_ATTACK");
        stats_.addEntry(Statistic.DEFENSE,"DEFENSE");
        stats_.addEntry(Statistic.SPECIAL_DEFENSE,"SPECIAL_DEFENSE");
        stats_.addEntry(Statistic.SPEED, SPEED_TR);
        stats_.addEntry(Statistic.ACCURACY,"ACCURACY");
        stats_.addEntry(Statistic.EVASINESS,"EVASINESS");
        stats_.addEntry(Statistic.HP,"HP");
        stats_.addEntry(Statistic.PV_RESTANTS,"PV_RESTANTS");
        stats_.addEntry(Statistic.CRITICAL_HIT,"CRITICAL_HIT");
        data_.getTranslatedStatistics().addEntry(LANGUAGE, stats_);
        IdMap<TargetChoice, String> tar_ = new IdMap<TargetChoice, String>();
        tar_.addEntry(TargetChoice.ADJ_ADV,"ADJ_ADV");
        tar_.addEntry(TargetChoice.ADJ_MULT,"ADJ_MULT");
        tar_.addEntry(TargetChoice.ANY_FOE,"ANY_FOE");
        tar_.addEntry(TargetChoice.ALLIE,"ALLIE");
        tar_.addEntry(TargetChoice.ALLIES,"ALLIES");
        tar_.addEntry(TargetChoice.AUTRE_UNIQ,"AUTRE_UNIQ");
        tar_.addEntry(TargetChoice.ADJ_UNIQ,"ADJ_UNIQ");
        tar_.addEntry(TargetChoice.GLOBALE,"GLOBALE");
        tar_.addEntry(TargetChoice.LANCEUR,"LANCEUR");
        tar_.addEntry(TargetChoice.NOTHING,"NOTHING");
        tar_.addEntry(TargetChoice.TOUS_ADV,"TOUS_ADV");
        tar_.addEntry(TargetChoice.PSEUDO_GLOBALE,"PSEUDO_GLOBALE");
        tar_.addEntry(TargetChoice.UNIQUE_IMPORTE,"UNIQUE_IMPORTE");
        data_.getTranslatedTargets().addEntry(LANGUAGE, tar_);
        data_.getTranslatedTypes().addEntry(LANGUAGE,tr(ELECTRICK,ELECTRICK_TR));
        data_.getTranslatedPokemon().addEntry(LANGUAGE,tr(PIKACHU,PIKACHU_TR));
        data_.getTranslatedItems().addEntry(LANGUAGE,tr(POKE_BALL,POKE_BALL_TR));
        data_.getTranslatedAbilities().addEntry(LANGUAGE,tr(PARATONNERRE,PARATONNERRE_TR));
        StringMap<String> trMov_ = tr(LUTTE, LUTTE);
        trMov_.addAllEntries(tr(ECLAIR,ECLAIR_TR));
        trMov_.addAllEntries(tr(CHARGE,CHARGE_TR));
        trMov_.addAllEntries(tr(CHARGE2,CHARGE_TR2));
        data_.getTranslatedMoves().addEntry(LANGUAGE, trMov_);
        data_.getTranslatedStatus().addEntry(LANGUAGE,new StringMap<String>());
        data_.getTranslatedCategories().addEntry(LANGUAGE,tr(SPEC, SPEC));
        DataMap map_ = data_.getMap();
        map_.setAccessCondition(new CoordsLists());
        WildPk pkm_ = new WildPk();
        pkm_.setName(PIKACHU);
        pkm_.setAbility(PARATONNERRE);
        pkm_.setGender(Gender.NO_GENDER);
        pkm_.setItem(NULL_REF);
        pkm_.setLevel( 7);
        map_.setFirstPokemon(pkm_);
        map_.setBegin(newCoords(0, 0, 0, 1));
        data_.getCombos().setEffects(new ListEffectCombos());
        pts(data_);
        return data_;
    }

    protected FacadeGame facadeEnMoves(DataBase _data) {
        FacadeGame f_ = facade(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove ever_ = new ActivityOfMove(false);
        ever_.setEnabled(true);
        fight_.getEnabledMoves().set(CHARGE2, ever_);
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(false);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }

    protected FacadeGame facadeEnMovesAct(DataBase _data) {
        FacadeGame f_ = facadeEnMoves(_data);
        Fight fight_ = f_.getFight();
        ActivityOfMove some_ = new ActivityOfMove(true);
        some_.setEnabled(true);
        some_.setNbTurn( 1);
        fight_.getEnabledMoves().set(CHARGE, some_);
        return f_;
    }
    protected FacadeGame facadeCalculation(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation2(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation3(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        DualFight trainer_ = Instances.newDualFight();
        trainer_.getFoeTrainer().setTeam(foeTeam_);
        trainer_.getFoeTrainer().setReward( 200);
        trainer_.getAlly().setTeam(allyTeam_);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation4(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> allyTeam_ = new CustList<PkTrainer>();
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        allyTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        DualFight trainer_ = Instances.newDualFight();
        trainer_.getFoeTrainer().setTeam(foeTeam_);
        trainer_.getFoeTrainer().setReward( 200);
        trainer_.getAlly().setTeam(allyTeam_);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        fight_.getAllyChoice().clear();
        fight_.getAllyChoice().addEntry(MoveTarget.def(),MoveTarget.def());
        return fac_;
    }
    protected FacadeGame facadeCalculation5(DataBase _data) {
        //AAABAACP
        _data.getMaxiPkFront().addEntry(PIKA_2, instance(IMG_MAX_RAI));
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, toWildPk(new NameLevel(PIKA_2,3)), _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation6(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(M_ROQUE)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(M_ROQUE)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeCalculation7(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,3),new StringList(M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(1);
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        FightFacade.initTypeEnv(fight_,EnvironmentType.ROAD,diff_,_data);
        return fac_;
    }
    protected FacadeGame facadeBigTeams(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        updateMoves(fight_);
        return fac_;
    }

    protected FacadeGame facadeFighters(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,false,_data);
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,3),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,4),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,5),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,6),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,7),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,8),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKACHU,9),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        player_.getTeam().add(pkPlayer(new NameLevel(PIKA_2,10),move(move(new StringMap<Long>(),CHARGE,8),CHARGE2,5),diff_,_data));
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,4),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,5),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,6),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,7),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,8),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,9),new StringList(ECLAIR,M_TEAM)));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKA_2,10),new StringList(ECLAIR,M_TEAM)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(4);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        fight_.getUserTeam().getMembers().getValue(0).getTrackingMoves().getValue(0).getActivity().setEnabled(true);
        fight_.getUserTeam().getMembers().getValue(0).getTrackingMoves().getValue(0).setMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getPrivateMoves().setValue(0,new StringList(M_TEAM));
        fight_.getUserTeam().getMembers().getValue(0).getCopiedMoves().getValue(0).setMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getCopiedMoves().getValue(0).setPp( 3);
        fight_.getUserTeam().getMembers().getValue(0).getMoves().getVal(CHARGE).setCurrent(5);
        fight_.getUserTeam().getMembers().getValue(0).getMoves().getVal(CHARGE).setMax(6);
        fight_.getUserTeam().getMembers().getValue(0).getMoves().getVal(CHARGE2).setCurrent(7);
        fight_.getUserTeam().getMembers().getValue(0).getMoves().getVal(CHARGE2).setMax(8);
        fight_.getUserTeam().getMembers().getValue(0).getIncrUserAccuracy().setValue(0,BoolVal.TRUE);
        fight_.getUserTeam().getMembers().getValue(0).getEnabledMovesForAlly().setValue(0, BoolVal.TRUE);
        fight_.getUserTeam().getMembers().getValue(0).getAlreadyInvokedMovesRound().add(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastSufferedMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastUsedMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setLastSuccessfulMove(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).setUsedMoveLastRound(M_TEAM);
        fight_.getUserTeam().getMembers().getValue(0).getLastSufferedMoveTypes().add(ELECTRICK);
        fight_.getUserTeam().getMembers().getValue(0).getProtectedAgainstMoveTypes().add(ELECTRICK);
        fight_.getUserTeam().getMembers().getValue(0).setCurrentAbility(NULL_REF);
        fight_.getUserTeam().getMembers().getValue(0).setItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setExpItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setLastUsedItem(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).setUsedBallCatching(I_SAMPLE);
        fight_.getUserTeam().getMembers().getValue(0).getStatusRelat().setValue(0, 1L);
        fight_.getUserTeam().getMembers().getValue(0).setChanged(true);
        fight_.getUserTeam().getMembers().getValue(0).setActed(true);
        fight_.getUserTeam().getMembers().getValue(0).setUsingItem(true);
        fight_.getUserTeam().getMembers().getValue(0).setSuccessfulMove(true);
        fight_.getUserTeam().getMembers().getValue(0).setDisappeared(true);
        fight_.getUserTeam().getMembers().getValue(0).setNeedingToRecharge(true);
        fight_.getUserTeam().getMembers().getValue(0).setNickname(NICK_NA);
        return fac_;
    }
    private void updateMoves(Fight _fight) {
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setNbRounds(1);
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setLastStacked(true);
        _fight.getUserTeam().getHealAfter().getVal(M_STACK).getValue(0).setFirstStacked(true);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setNbRounds(0);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setLastStacked(false);
        _fight.getFoeTeam().getHealAfter().getVal(M_STACK).getValue(0).setFirstStacked(false);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setIncrementing(true);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setNbRounds(1);
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setDamage(Rate.one());
        _fight.getUserTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setTargetPosition(new TargetCoords( -1,Fighter.BACK));
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setIncrementing(false);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setNbRounds(0);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setDamage(Rate.zero());
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(0).setTargetPosition(TargetCoords.toFoeTarget( 0));
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setIncrementing(false);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setNbRounds(0);
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setDamage(Rate.zero());
        _fight.getFoeTeam().getMovesAnticipation().getVal(M_ANT).getValue(1).setTargetPosition(TargetCoords.toUserTarget( 0));
    }
    protected FacadeGame facade(DataBase _data) {
        FacadeGame fac_ = initFacade(_data);
        Game g_ = new Game();
        Difficulty diff_= new Difficulty();
        Player player_ = Player.build(diff_,true,_data);
        CustList<PkTrainer> foeTeam_ = new CustList<PkTrainer>();
//        PkTrainer foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel( 3);
//        foePokemon_.setMoves(new StringList(ECLAIR));
//        foeTeam_.add(foePokemon_);
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,3),new StringList(ECLAIR)));
//        foePokemon_ = Instances.newPkTrainer();
//        foePokemon_.setName(PIKACHU);
////        foePokemon_.setItem(MAGNET);
//        foePokemon_.setAbility(PARATONNERRE);
//        foePokemon_.setGender(Gender.NO_GENDER);
//        foePokemon_.setLevel( 4);
//        foePokemon_.setMoves(new StringList(ECLAIR));
        foeTeam_.add(toPkTrainer(new NameLevel(PIKACHU,4),new StringList(ECLAIR)));
        TrainerLeague trainer_ = Instances.newTrainerLeague();
        trainer_.setTeam(foeTeam_);
        trainer_.setReward( 200);
        trainer_.setMultiplicityFight(2);
        g_.setPlayer(player_);
        fac_.setGame(g_);
        Fight fight_ = g_.getFight();
        FightFacade.initFight(fight_, player_, diff_, trainer_, _data);
        return fac_;
    }

    private FacadeGame initFacade(DataBase _data) {
        FacadeGame fac_ = new FacadeGame();
        fac_.setLanguages(indexes());
        StringMap<String> displayLanguages_ = new StringMap<String>();
        displayLanguages_.addEntry(EN,EN);
        fac_.setDisplayLanguages(displayLanguages_);
        fac_.setData(_data);
        _data.setMessages(fac_.getData());
        fac_.setLoadedData(true);
        fac_.setZipName("");
        fac_.setData(_data);
        fac_.setLanguage(EN);
        return fac_;
    }

    private static void pts(DataBase _data) {
        _data.getExpGrowth().put(ExpType.E,"2*"+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().put(ExpType.L,"5/4*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().put(ExpType.M,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getExpGrowth().put(ExpType.P,MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",2)");
        _data.getExpGrowth().put(ExpType.F,VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU);
        _data.getExpGrowth().put(ExpType.R,"4/5*"+MbOperationNode.PUIS+"("+VAR_PREFIX+ MessagesDataBaseConstants.DEF_NIVEAU+",3)");
        _data.getRates().put(DifficultyWinPointsFight.TRES_FACILE, "4");
        _data.getRates().put(DifficultyWinPointsFight.FACILE, "2");
        _data.getRates().put(DifficultyWinPointsFight.DIFFICILE, "1");
        _data.getRates().put(DifficultyWinPointsFight.TRES_DIFFICILE, "1/2");
    }
    private static StringMap<Long> move(StringMap<Long> _moves, String _move, long _pp) {
        _moves.addEntry(_move,  _pp);
        return _moves;
    }
    private static PokemonPlayer pkPlayer(NameLevel _nameLevel, StringMap<Long> _moves, Difficulty _diff, DataBase _data) {
        WildPk w_ = Instances.newWildPk();
        w_.setName(_nameLevel.getName());
        w_.setLevel(_nameLevel.getLevel());
        w_.setAbility(PARATONNERRE);
        w_.setItem(NULL_REF);
        w_.setGender(Gender.NO_GENDER);
        PokemonPlayer lasPk_ = new PokemonPlayer(w_, _data, _moves);
        lasPk_.initIv(_diff);
        lasPk_.initPvRestants(_data);
        return lasPk_;
    }

    private static PkTrainer toPkTrainer(NameLevel _nameLevel, StringList _moves) {
        PkTrainer pk_ = Instances.newPkTrainer();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setMoves(_moves);
        pk_.setGender(Gender.NO_GENDER);
        return pk_;
    }

    private static WildPk toWildPk(NameLevel _nameLevel) {
        WildPk pk_ = Instances.newWildPk();
        pk_.setName(_nameLevel.getName());
        pk_.setLevel(_nameLevel.getLevel());
        pk_.setAbility(PARATONNERRE);
        pk_.setItem(NULL_REF);
        pk_.setGender(Gender.NO_GENDER);
        return pk_;
    }
}
