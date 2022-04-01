package cards.tarot.beans;

import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.bean.nat.*;
import code.bean.nat.exec.NatImportingPage;
import code.bean.nat.exec.NatRendStackCall;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.StringUtil;
public final class TarotStandards extends BeanNatLgNames {
    static final String TYPE_RATE = "$Rate";
    private static final String DISCARD_AFTER_CALL = "discardAfterCall";
    private static final String POIGNEES_AUTORISEES = "poigneesAutorisees";
    private static final String CONTRATS = "contrats";
    private static final String FIN_PARTIE_TAROT = "finPartieTarot";
    private static final String MODE = "mode";
    private static final String REPARTITION = "repartition";
    private static final String CARTES_BATTUES = "cartesBattues";
    private static final String SCORES = "scores";
    private static final String NUMBER = "number";
    private static final String LINES_DEAL = "linesDeal";
    private static final String CALLED_CARDS_LIST = "calledCardsList";
    private static final String NICKNAMES = "nicknames";
    private static final String CALLED_PLAYERS = "calledPlayers";
    private static final String ALONE_TRUMP_ACE_PLAYER = "aloneTrumpAcePlayer";
    private static final String TAKER = "taker";
    private static final String FINAL_USER_POSITION = "finalUserPosition";
    private static final String INITIAL_USER_POSITION = "initialUserPosition";
    private static final String MAX_DIFFERENCE = "maxDifference";
    private static final String MAX_DOUBLED_DIFFERENCE = "maxDoubledDifference";
    private static final String SCORE_TAKER_WITHOUT_DECLARING = "scoreTakerWithoutDeclaring";
    private static final String SCORE_TAKER = "scoreTaker";
    private static final String NEEDLY_SCORES_TAKER = "needlyScoresTaker";
    private static final String NUMBER_OUDLERS_TAKER = "numberOudlersTaker";
    private static final String BID_STRING = "bidString";
    private static final String ABSOLUTE_DIFF = "absoluteDiff";
    private static final String NO_SLAM_DEFENSE = "noSlamDefense";
    private static final String SLAM_DEFENSE = "slamDefense";
    private static final String NO_SLAM_ATTACK = "noSlamAttack";
    private static final String FAILED_SLAM_ATTACK = "failedSlamAttack";
    private static final String SUCCESSFUL_NO_DECLARED_SLAM_ATTACK = "successfulNoDeclaredSlamAttack";
    private static final String SUCCESSFUL_DECLARED_SLAM_ATTACK = "successfulDeclaredSlamAttack";
    private static final String FAILED_BID = "failedBid";
    private static final String MID_BID = "midBid";
    private static final String SUCCESSFUL_BID = "successfulBid";
    private static final String LOOSE = "loose";
    private static final String EQUALITY = "equality";
    private static final String WIN = "win";
    private static final String BONUS = "bonus";
    private static final String POINTS_TRICKS = "pointsTricks";
    private static final String DIFFERENCE_SCORE = "differenceScore";
    private static final String MINIMUM_POINTS = "minimumPoints";
    private static final String FINAL_POSITION = "finalPosition";
    private static final String POSITION_STRENGTH_CHARACTERS = "positionStrengthCharacters";
    private static final String POSITION_CHARACTERS = "positionCharacters";
    private static final String POSITION_OUDLERS = "positionOudlers";
    private static final String POSITION_DIFF = "positionDiff";
    private static final String SCORE = "score";
    private static final String SUM = "sum";
    private static final String MISERES = "miseres";
    private static final String HANDFULS = "handfuls";
    private static final String STATUS = "status";
    private static final String NICKNAME = "nickname";
    private static final String BONUSES = "bonuses";
    private static final String POINTS_PLAYERS = "pointsPlayers";
    private static final String ORDERED_PLAYERS = "orderedPlayers";
    private static final String PLAYERS_SCORES = "playersScores";
    private static final String LINES_DECLARING = "linesDeclaring";
    private static final String SMALL = "small";
    private static final String PLAYER_SMALL = "playerSmall";
    private static final String DIFFERENCE_SCORE_TAKER = "differenceScoreTaker";
    private static final String BASE_POINTS = "basePoints";
    private static final String DIFF_ATTACK_DEFENSE_BONUSES = "diffAttackDefenseBonuses";
    private static final String ADDITIONNAL_BONUSES_DEFENSE = "additionnalBonusesDefense";
    private static final String ADDITIONNAL_BONUSES_ATTACK = "additionnalBonusesAttack";
    private static final String SUM_PLAYERS = "sumPlayers";
    private static final String MULTIPLIED_TMP = "multipliedTmp";
    private static final String RATE = "rate";
    private static final String GET_SCORES = "getScores";
    private static final String GET_NICKNAMES = "getNicknames";
    private static final String PLAY_VARIANT_MODE_GAME = "playVariantModeGame";
    private static final String PLAY_CLASSIC_GAME = "playClassicGame";
    private static final String TYPE_RULES_TAROT = "RulesTarot";
    private static final String TYPE_RESULTS_TAROT = "ResultsTarot";
    private static final String TYPE_RULES_TAROT_BEAN = "cards.tarot.beans.RulesTarotBean";
    private static final String TYPE_LINE_DEAL = "cards.tarot.beans.LineDeal";
    private static final String TYPE_RESULTS_TAROT_BEAN = "cards.tarot.beans.ResultsTarotBean";
    private static final String TYPE_BONUSES_PLAYERS = "cards.tarot.beans.BonusesPlayers";
    private static final String TYPE_POINTS_PLAYER_VARIANT_GAME = "cards.tarot.beans.PointsPlayerVariantGame";
    private static final String TYPE_RANKING_PLAYER_VARIANT_GAME = "cards.tarot.beans.RankingPlayerVariantGame";
    private static final String TYPE_SCORES_PLAYERS = "cards.tarot.beans.ScoresPlayers";
    private static final String TYPE_SUM_DECLARING_PLAYER = "cards.tarot.beans.SumDeclaringPlayer";
    private static final String TYPE_DETAILS_RESULTS_TAROT_BEAN = "cards.tarot.beans.DetailsResultsTarotBean";
    private static final String TYPE_BID_TAROT = "$BidTarot";
    private static final String TYPE_TAROT_BEAN = "cards.tarot.beans.TarotBean";
    private ResultsTarot dataBase;
    private RulesTarot dataBaseRules;
    public TarotStandards(){
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        buildTarotBean();
        buildResultsTarotBean();
        buildSumDeclaringPlayer();
        buildScoresPlayers();
        buildRankingPlayerVariantGame();
        buildPointsPlayerVariantGame();
        buildBonusesPlayers();
        buildLineDeal();
        buildDetailsResultsTarotBean();
        buildRulesTarotBean();
    }
    private void buildTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_TAROT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, false, false,new TarotBeanNicknames(),null));
        methods_.add( new SpecNatMethod(PLAY_CLASSIC_GAME, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new TarotBeanPlayClassicGame()));
        methods_.add( new SpecNatMethod(PLAY_VARIANT_MODE_GAME, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new TarotBeanPlayVariantModeGame()));
        methods_.add( new SpecNatMethod(GET_NICKNAMES, TYPE_LIST, false, MethodModifier.NORMAL,new TarotBeanGetNicknames()));
        methods_.add( new SpecNatMethod(GET_SCORES, TYPE_LIST, false, MethodModifier.NORMAL,new TarotBeanGetScores()));
        getStds().addEntry(TYPE_TAROT_BEAN, std_);
    }
    private void buildResultsTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RESULTS_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(NUMBER_OUDLERS_TAKER, PRIM_INTEGER, false, false,new ResultsTarotBeanNumberOudlersTaker(),null));
        fields_.add( new StandardField(NEEDLY_SCORES_TAKER, PRIM_INTEGER, false, false,new ResultsTarotBeanNeedlyScoresTaker(),null));
        fields_.add( new StandardField(SCORE_TAKER, PRIM_INTEGER, false, false,new ResultsTarotBeanScoreTaker(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, false, false,new ResultsTarotBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, PRIM_INTEGER, false, false,new ResultsTarotBeanAdditionnalBonusesAttack(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, PRIM_INTEGER, false, false,new ResultsTarotBeanAdditionnalBonusesDefense(),null));
        fields_.add( new StandardField(SCORE_TAKER_WITHOUT_DECLARING, PRIM_INTEGER, false, false,new ResultsTarotBeanScoreTakerWithoutDeclaring(),null));
        fields_.add( new StandardField(MAX_DOUBLED_DIFFERENCE, PRIM_INTEGER, false, false,new ResultsTarotBeanMaxDoubledDifference(),null));
        fields_.add( new StandardField(MAX_DIFFERENCE, PRIM_INTEGER, false, false,new ResultsTarotBeanMaxDifference(),null));
        fields_.add( new StandardField(INITIAL_USER_POSITION, PRIM_INTEGER, false, false,new ResultsTarotBeanInitialUserPosition(),null));
        fields_.add( new StandardField(FINAL_USER_POSITION, PRIM_INTEGER, false, false,new ResultsTarotBeanFinalUserPosition(),null));
        fields_.add( new StandardField(TAKER, STRING, false, false,new ResultsTarotBeanTaker(),null));
        fields_.add( new StandardField(ALONE_TRUMP_ACE_PLAYER, STRING, false, false,new ResultsTarotBeanAloneTrumpAcePlayer(),null));
        fields_.add( new StandardField(CALLED_PLAYERS, TYPE_LIST, false, false,new ResultsTarotBeanCalledPlayers(),null));
        fields_.add( new StandardField(CALLED_CARDS_LIST, TYPE_LIST, false, false,new ResultsTarotBeanCalledCardsList(),null));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false,new ResultsTarotBeanLinesDeal(),null));
        methods_.add( new SpecNatMethod(WIN, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanWin()));
        methods_.add( new SpecNatMethod(EQUALITY, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanEquality()));
        methods_.add( new SpecNatMethod(LOOSE, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanLoose()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanSuccessfulBid()));
        methods_.add( new SpecNatMethod(MID_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanMidBid()));
        methods_.add( new SpecNatMethod(FAILED_BID, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanFailedBid()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_DECLARED_SLAM_ATTACK, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanSuccessfulDeclaredSlamAttack()));
        methods_.add( new SpecNatMethod(SUCCESSFUL_NO_DECLARED_SLAM_ATTACK, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanSuccessfulNoDeclaredSlamAttack()));
        methods_.add( new SpecNatMethod(FAILED_SLAM_ATTACK, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanFailedSlamAttack()));
        methods_.add( new SpecNatMethod(NO_SLAM_ATTACK, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanNoSlamAttack()));
        methods_.add( new SpecNatMethod(SLAM_DEFENSE, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanSlamDefense()));
        methods_.add( new SpecNatMethod(NO_SLAM_DEFENSE, PRIM_BOOLEAN, false, MethodModifier.NORMAL,new ResultsTarotBeanNoSlamDefense()));
        methods_.add( new SpecNatMethod(ABSOLUTE_DIFF, PRIM_INTEGER, false, MethodModifier.NORMAL,new ResultsTarotBeanAbsoluteDiff()));
        methods_.add( new SpecNatMethod(BID_STRING, STRING, false, MethodModifier.NORMAL,new ResultsTarotBeanBidString()));
        getStds().addEntry(TYPE_RESULTS_TAROT_BEAN, std_);
    }
    private void buildSumDeclaringPlayer(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_SUM_DECLARING_PLAYER, fields_, methods_, OBJECT);
        fields_.add( new StandardField(SUM, PRIM_INTEGER, false, false,new TarotSumDeclaringPlayerSum(),null));
        fields_.add( new StandardField(STATUS, STRING, false, false,new TarotSumDeclaringPlayerStatus(),null));
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new TarotSumDeclaringPlayerNickname(),null));
        fields_.add( new StandardField(HANDFULS, TYPE_MAP, false, false,new TarotSumDeclaringPlayerHandfuls(),null));
        fields_.add( new StandardField(MISERES, TYPE_MAP, false, false,new TarotSumDeclaringPlayerMiseres(),null));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
    }
    private void buildScoresPlayers(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_SCORES_PLAYERS, fields_, methods_, OBJECT);
        fields_.add( new StandardField(SUM, PRIM_INTEGER, false, false,new ScoresPlayersSum(),null));
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new ScoresPlayersNickname(),null));
        fields_.add( new StandardField(SCORE, PRIM_INTEGER, false, false,new ScoresPlayersScore(),null));
        fields_.add( new StandardField(RATE, TYPE_RATE, false, false,new ScoresPlayersRate(),null));
        getStds().addEntry(TYPE_SCORES_PLAYERS, std_);
    }
    private void buildRankingPlayerVariantGame(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RANKING_PLAYER_VARIANT_GAME, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new RankingPlayerVariantGameNickname(),null));
        fields_.add( new StandardField(POSITION_DIFF, PRIM_INTEGER, false, false,new RankingPlayerVariantGamePositionDiff(),null));
        fields_.add( new StandardField(POSITION_OUDLERS, PRIM_INTEGER, false, false,new RankingPlayerVariantGamePositionOudlers(),null));
        fields_.add( new StandardField(POSITION_CHARACTERS, PRIM_INTEGER, false, false,new RankingPlayerVariantGamePositionCharacters(),null));
        fields_.add( new StandardField(POSITION_STRENGTH_CHARACTERS, PRIM_INTEGER, false, false,new RankingPlayerVariantGamePositionStrengthCharacters(),null));
        fields_.add( new StandardField(FINAL_POSITION, PRIM_INTEGER, false, false,new RankingPlayerVariantGameFinalPosition(),null));
        getStds().addEntry(TYPE_RANKING_PLAYER_VARIANT_GAME, std_);
    }
    private void buildPointsPlayerVariantGame(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_POINTS_PLAYER_VARIANT_GAME, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new PointsPlayerVariantGameNickname(),null));
        fields_.add( new StandardField(RATE, PRIM_INTEGER, false, false,new PointsPlayerVariantGameRate(),null));
        fields_.add( new StandardField(SCORE, PRIM_INTEGER, false, false,new PointsPlayerVariantGameScore(),null));
        fields_.add( new StandardField(MINIMUM_POINTS, PRIM_INTEGER, false, false,new PointsPlayerVariantGameMinimumPoints(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE, TYPE_RATE, false, false,new PointsPlayerVariantGameDifferenceScore(),null));
        fields_.add( new StandardField(POINTS_TRICKS, TYPE_RATE, false, false,new PointsPlayerVariantGamePointsTricks(),null));
        getStds().addEntry(TYPE_POINTS_PLAYER_VARIANT_GAME, std_);
    }
    private void buildBonusesPlayers(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_BONUSES_PLAYERS, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false,new BonusesPlayersNickname(),null));
        fields_.add( new StandardField(BONUS, PRIM_INTEGER, false, false,new BonusesPlayersBonus(),null));
        getStds().addEntry(TYPE_BONUSES_PLAYERS, std_);
    }
    private void buildLineDeal(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NUMBER, PRIM_INTEGER, false, false,new TarotLineDealNumber(),null));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false,new TarotLineDealScores(),null));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
    }
    private void buildDetailsResultsTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_DETAILS_RESULTS_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(RATE, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanRate(),null));
        fields_.add( new StandardField(MULTIPLIED_TMP, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanMultipliedTmp(),null));
        fields_.add( new StandardField(SUM_PLAYERS, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanSumPlayers(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanAdditionnalBonusesAttack(),null));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanAdditionnalBonusesDefense(),null));
        fields_.add( new StandardField(DIFF_ATTACK_DEFENSE_BONUSES, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanDiffAttackDefenseBonuses(),null));
        fields_.add( new StandardField(BASE_POINTS, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanBasePoints(),null));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, PRIM_INTEGER, false, false,new DetailsResultsTarotBeanDifferenceScoreTaker(),null));
        fields_.add( new StandardField(PLAYER_SMALL, STRING, false, false,new DetailsResultsTarotBeanPlayerSmall(),null));
        fields_.add( new StandardField(SMALL, STRING, false, false,new DetailsResultsTarotBeanSmall(),null));
        fields_.add( new StandardField(LINES_DECLARING, TYPE_LIST, false, false,new DetailsResultsTarotBeanLinesDeclaring(),null));
        fields_.add( new StandardField(PLAYERS_SCORES, TYPE_LIST, false, false,new DetailsResultsTarotBeanPlayersScores(),null));
        fields_.add( new StandardField(ORDERED_PLAYERS, TYPE_LIST, false, false,new DetailsResultsTarotBeanOrderedPlayers(),null));
        fields_.add( new StandardField(POINTS_PLAYERS, TYPE_LIST, false, false,new DetailsResultsTarotBeanPointsPlayers(),null));
        fields_.add( new StandardField(BONUSES, TYPE_LIST, false, false,new DetailsResultsTarotBeanBonuses(),null));
        getStds().addEntry(TYPE_DETAILS_RESULTS_TAROT_BEAN, std_);
    }
    private void buildRulesTarotBean(){
        CustList<StandardField> fields_=new CustList<StandardField>();
        CustList<SpecNatMethod> methods_=new CustList<SpecNatMethod>();
        SpecialNatClass std_ = new SpecialNatClass(TYPE_RULES_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false,new RulesTarotBeanCartesBattues(),null));
        fields_.add( new StandardField(REPARTITION, STRING, false, false,new RulesTarotBeanRepartition(),null));
        fields_.add( new StandardField(MODE, STRING, false, false,new RulesTarotBeanMode(),null));
        fields_.add( new StandardField(FIN_PARTIE_TAROT, STRING, false, false,new RulesTarotBeanFinPartieTarot(),null));
        fields_.add( new StandardField(DISCARD_AFTER_CALL, PRIM_BOOLEAN, false, false,new RulesTarotBeanDiscardAfterCall(),null));
        fields_.add( new StandardField(CONTRATS, TYPE_LIST, false, false,new RulesTarotBeanContrats(),null));
        fields_.add( new StandardField(POIGNEES_AUTORISEES, TYPE_MAP, false, false,new RulesTarotBeanPoigneesAutorisees(),null));
        fields_.add( new StandardField(MISERES, TYPE_LIST, false, false,new RulesTarotBeanMiseres(),null));
        getStds().addEntry(TYPE_RULES_TAROT_BEAN, std_);
    }
@Override
    public void beforeDisplaying(Struct _arg) {
        ((TarotBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _language, NatRendStackCall _rendStack) {
        NatImportingPage ip_ = new NatImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = getRenders().getVal(_dest);
        _rendStack.clearPages();
        return getRes(rendDocumentBlock_,_conf, _rendStack);
    }

    public Struct getOtherResultBean(ConstructorId _method, Struct... _args) {
        if (StringUtil.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_TAROT_BEAN)) {
            DetailsResultsTarotBean details_ = new DetailsResultsTarotBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_TAROT_BEAN);
            return(new TarotBeanStruct(details_));
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RESULTS_TAROT_BEAN)) {
            ResultsTarotBean details_ = new ResultsTarotBean();
            details_.setClassName(TYPE_RESULTS_TAROT_BEAN);
            return(new TarotBeanStruct(details_));
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_TAROT_BEAN)) {
            RulesTarotBean details_ = new RulesTarotBean();
            details_.setClassName(TYPE_RULES_TAROT_BEAN);
            return(new TarotBeanStruct(details_));
        }
        return NullStruct.NULL_VALUE;
    }


    public static ArrayStruct getSumDeclaringPlayerArray(CustList<TarotSumDeclaringPlayer> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_SUM_DECLARING_PLAYER));
        int j_ = 0;
        for (TarotSumDeclaringPlayer s:_ls) {
            arr_.set(j_,new TarotSumDeclaringPlayerStruct(s, TYPE_SUM_DECLARING_PLAYER));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getScoresPlayersArray(CustList<ScoresPlayers> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_SCORES_PLAYERS));
        int j_ = 0;
        for (ScoresPlayers s:_ls) {
            arr_.set(j_,new ScoresPlayersStruct(s, TYPE_SCORES_PLAYERS));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getRankingPlayerVariantGameArray(CustList<RankingPlayerVariantGame> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_RANKING_PLAYER_VARIANT_GAME));
        int j_ = 0;
        for (RankingPlayerVariantGame s:_ls) {
            arr_.set(j_,new RankingPlayerVariantGameStruct(s, TYPE_RANKING_PLAYER_VARIANT_GAME));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getPointsPlayerVariantGameArray(CustList<PointsPlayerVariantGame> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_POINTS_PLAYER_VARIANT_GAME));
        int j_ = 0;
        for (PointsPlayerVariantGame s:_ls) {
            arr_.set(j_,new PointsPlayerVariantGameStruct(s, TYPE_POINTS_PLAYER_VARIANT_GAME));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getBonusesPlayersArray(CustList<BonusesPlayers> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_BONUSES_PLAYERS));
        int j_ = 0;
        for (BonusesPlayers s:_ls) {
            arr_.set(j_,new BonusesPlayersStruct(s, TYPE_BONUSES_PLAYERS));
            j_++;
        }
        return arr_;
    }

    public static ArrayStruct getLineDealArray(CustList<TarotLineDeal> _ls) {
        ArrayStruct arr_ = new ArrayStruct(_ls.size(), StringExpUtil.getPrettyArrayType(TYPE_LINE_DEAL));
        int j_ = 0;
        for (TarotLineDeal s:_ls) {
            arr_.set(j_,new TarotLineDealStruct(s, TYPE_LINE_DEAL));
            j_++;
        }
        return arr_;
    }
    public static ArrayStruct getStringShortTree(StringMap<Short> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(),StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String, Short> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new ShortStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }
    public static ArrayStruct getStringIntegerTree(StringMap<Integer> _map) {
        ArrayStruct arr_ = new ArrayStruct(_map.size(),StringExpUtil.getPrettyArrayType(OBJECT));
        int i_ = 0;
        for (EntryCust<String, Integer> e: _map.entryList()){
            PairStruct p_ = new PairStruct(OBJECT,new StringStruct(StringUtil.nullToEmpty(e.getKey())),new IntStruct(e.getValue()));
            arr_.set(i_,p_);
            i_++;
        }
        return arr_;
    }

    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        Struct strBean_ = getOtherResultBean(id_, args_);
        return update(_language, (TarotBeanStruct) strBean_);
    }

    private TarotBeanStruct update(String _language, TarotBeanStruct _str) {
        TarotBean bean_ = _str.getInstance();
        bean_.setDataBase(dataBase,dataBaseRules);
        bean_.setLanguage(_language);
        return _str;
    }

    public void setDataBase(ResultsTarot _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesTarot _dataBase){
        dataBaseRules = _dataBase;
    }
}
