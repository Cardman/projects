package cards.tarot.beans;

import cards.tarot.ResultsTarot;
import cards.tarot.RulesTarot;
import code.bean.nat.*;
import code.bean.nat.exec.opers.NatStdFctOperation;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.ClassField;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.ConstructorId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.formathtml.Configuration;
import code.formathtml.ImportingPage;
import code.formathtml.exec.RendStackCall;
import code.formathtml.exec.blocks.RendDocumentBlock;
import code.formathtml.structs.BeanInfo;
import code.util.*;
import code.util.core.StringUtil;

public final class TarotStandards extends BeanNatLgNames {
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
    private static final String TYPE_RATE = "$Rate";
    private static final String TYPE_TAROT_BEAN = "cards.tarot.beans.TarotBean";
    private ResultsTarot dataBase;
    private RulesTarot dataBaseRules;
    public TarotStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        CustList<StandardField> fields_;
        SpecialNatClass std_;
        CustList<SpecNatMethod> methods_;
        methods_ = new CustList<SpecNatMethod>();
        SpecNatMethod method_;
        fields_ = new CustList<StandardField>();
        std_ = new SpecialNatClass(TYPE_TAROT_BEAN, fields_, methods_, TYPE_BEAN);
        fields_.add( new StandardField(NICKNAMES, TYPE_LIST, false, false));
        StringList params_;
        params_ = new StringList();
        method_ = new SpecNatMethod(PLAY_CLASSIC_GAME, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(PLAY_VARIANT_MODE_GAME, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_NICKNAMES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(GET_SCORES, params_, TYPE_LIST, false, MethodModifier.NORMAL);
        methods_.add( method_);
        getStds().addEntry(TYPE_TAROT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RATE, fields_, methods_, OBJECT);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_RATE, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_BID_TAROT, fields_, methods_, OBJECT);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStds().addEntry(TYPE_BID_TAROT, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_DETAILS_RESULTS_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(RATE, getPrimInt(), false, false));
        fields_.add( new StandardField(MULTIPLIED_TMP, getPrimInt(), false, false));
        fields_.add( new StandardField(SUM_PLAYERS, getPrimInt(), false, false));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, getPrimInt(), false, false));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, getPrimInt(), false, false));
        fields_.add( new StandardField(DIFF_ATTACK_DEFENSE_BONUSES, getPrimInt(), false, false));
        fields_.add( new StandardField(BASE_POINTS, getPrimInt(), false, false));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(PLAYER_SMALL, STRING, false, false));
        fields_.add( new StandardField(SMALL, STRING, false, false));
        fields_.add( new StandardField(LINES_DECLARING, TYPE_LIST, false, false));
        fields_.add( new StandardField(PLAYERS_SCORES, TYPE_LIST, false, false));
        fields_.add( new StandardField(ORDERED_PLAYERS, TYPE_LIST, false, false));
        fields_.add( new StandardField(POINTS_PLAYERS, TYPE_LIST, false, false));
        fields_.add( new StandardField(BONUSES, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_DETAILS_RESULTS_TAROT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_SUM_DECLARING_PLAYER, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false));
        fields_.add( new StandardField(STATUS, STRING, false, false));
        fields_.add( new StandardField(HANDFULS, TYPE_MAP, false, false));
        fields_.add( new StandardField(MISERES, TYPE_MAP, false, false));
        fields_.add( new StandardField(SUM, getPrimInt(), false, false));
        getStds().addEntry(TYPE_SUM_DECLARING_PLAYER, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_SCORES_PLAYERS, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false));
        fields_.add( new StandardField(RATE, TYPE_RATE, false, false));
        fields_.add( new StandardField(SCORE, getPrimInt(), false, false));
        getStds().addEntry(TYPE_SCORES_PLAYERS, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RANKING_PLAYER_VARIANT_GAME, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false));
        fields_.add( new StandardField(POSITION_DIFF, getPrimInt(), false, false));
        fields_.add( new StandardField(POSITION_OUDLERS, getPrimInt(), false, false));
        fields_.add( new StandardField(POSITION_CHARACTERS, getPrimInt(), false, false));
        fields_.add( new StandardField(POSITION_STRENGTH_CHARACTERS, getPrimInt(), false, false));
        fields_.add( new StandardField(FINAL_POSITION, getPrimInt(), false, false));
        getStds().addEntry(TYPE_RANKING_PLAYER_VARIANT_GAME, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_POINTS_PLAYER_VARIANT_GAME, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false));
        fields_.add( new StandardField(RATE, getPrimInt(), false, false));
        fields_.add( new StandardField(MINIMUM_POINTS, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORE, getPrimInt(), false, false));
        fields_.add( new StandardField(DIFFERENCE_SCORE, TYPE_RATE, false, false));
        fields_.add( new StandardField(POINTS_TRICKS, TYPE_RATE, false, false));
        getStds().addEntry(TYPE_POINTS_PLAYER_VARIANT_GAME, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_BONUSES_PLAYERS, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NICKNAME, STRING, false, false));
        fields_.add( new StandardField(BONUS, getPrimInt(), false, false));
        getStds().addEntry(TYPE_BONUSES_PLAYERS, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RESULTS_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        params_ = new StringList();
        method_ = new SpecNatMethod(WIN, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(EQUALITY, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(LOOSE, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SUCCESSFUL_BID, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(MID_BID, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(FAILED_BID, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SUCCESSFUL_DECLARED_SLAM_ATTACK, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SUCCESSFUL_NO_DECLARED_SLAM_ATTACK, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(FAILED_SLAM_ATTACK, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(NO_SLAM_ATTACK, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(SLAM_DEFENSE, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(NO_SLAM_DEFENSE, params_, PRIM_BOOLEAN, false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(ABSOLUTE_DIFF, params_, getPrimInt(), false, MethodModifier.NORMAL);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new SpecNatMethod(BID_STRING, params_, STRING, false, MethodModifier.NORMAL);
        methods_.add( method_);
        fields_.add( new StandardField(NUMBER_OUDLERS_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(NEEDLY_SCORES_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORE_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(DIFFERENCE_SCORE_TAKER, getPrimInt(), false, false));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_ATTACK, getPrimInt(), false, false));
        fields_.add( new StandardField(ADDITIONNAL_BONUSES_DEFENSE, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORE_TAKER_WITHOUT_DECLARING, getPrimInt(), false, false));
        fields_.add( new StandardField(MAX_DOUBLED_DIFFERENCE, getPrimInt(), false, false));
        fields_.add( new StandardField(MAX_DIFFERENCE, getPrimInt(), false, false));
        fields_.add( new StandardField(INITIAL_USER_POSITION, getPrimInt(), false, false));
        fields_.add( new StandardField(FINAL_USER_POSITION, getPrimInt(), false, false));
        fields_.add( new StandardField(TAKER, STRING, false, false));
        fields_.add( new StandardField(ALONE_TRUMP_ACE_PLAYER, STRING, false, false));
        fields_.add( new StandardField(CALLED_PLAYERS, TYPE_LIST, false, false));
        fields_.add( new StandardField(CALLED_CARDS_LIST, TYPE_LIST, false, false));
        fields_.add( new StandardField(LINES_DEAL, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_RESULTS_TAROT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_LINE_DEAL, fields_, methods_, OBJECT);
        fields_.add( new StandardField(NUMBER, getPrimInt(), false, false));
        fields_.add( new StandardField(SCORES, TYPE_LIST, false, false));
        getStds().addEntry(TYPE_LINE_DEAL, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_TAROT_BEAN, fields_, methods_, TYPE_TAROT_BEAN);
        fields_.add( new StandardField(CARTES_BATTUES, STRING, false, false));
        fields_.add( new StandardField(REPARTITION, STRING, false, false));
        fields_.add( new StandardField(MODE, STRING, false, false));
        fields_.add( new StandardField(FIN_PARTIE_TAROT, STRING, false, false));
        fields_.add( new StandardField(MISERES, TYPE_LIST, false, false));
        fields_.add( new StandardField(CONTRATS, TYPE_LIST, false, false));
        fields_.add( new StandardField(POIGNEES_AUTORISEES, TYPE_MAP, false, false));
        fields_.add( new StandardField(DISCARD_AFTER_CALL, PRIM_BOOLEAN, false, false));
        getStds().addEntry(TYPE_RULES_TAROT_BEAN, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RESULTS_TAROT, fields_, methods_, OBJECT);
        getStds().addEntry(TYPE_RESULTS_TAROT, std_);
        fields_ = new CustList<StandardField>();
        methods_ = new CustList<SpecNatMethod>();
        std_ = new SpecialNatClass(TYPE_RULES_TAROT, fields_, methods_, OBJECT);
        getStds().addEntry(TYPE_RULES_TAROT, std_);
    }

    @Override
    public void beforeDisplaying(Struct _arg) {
        ((TarotBeanStruct)_arg).getBean().beforeDisplaying();
    }

    public String processAfterInvoke(Configuration _conf, String _dest, String _beanName, Struct _bean, String _currentUrl, String _language, ContextEl _ctx, RendStackCall _rendStack) {
        ImportingPage ip_ = new ImportingPage();
        _rendStack.addPage(ip_);
        RendDocumentBlock rendDocumentBlock_ = _conf.getRenders().getVal(_dest);
        _rendStack.clearPages();
        return BeanNatCommonLgNames.getRes(rendDocumentBlock_,_conf, this, _ctx, _rendStack);
    }

    @Override
    public ResultErrorStd getOtherResultBean(ConstructorId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringUtil.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_TAROT_BEAN)) {
            DetailsResultsTarotBean details_ = new DetailsResultsTarotBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_TAROT_BEAN);
            res_.setResult(new TarotBeanStruct(details_));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RESULTS_TAROT_BEAN)) {
            ResultsTarotBean details_ = new ResultsTarotBean();
            details_.setClassName(TYPE_RESULTS_TAROT_BEAN);
            res_.setResult(new TarotBeanStruct(details_));
            return res_;
        }
        if (StringUtil.quickEq(_method.getName(), TYPE_RULES_TAROT_BEAN)) {
            RulesTarotBean details_ = new RulesTarotBean();
            details_.setClassName(TYPE_RULES_TAROT_BEAN);
            res_.setResult(new TarotBeanStruct(details_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (_instance instanceof TarotSumDeclaringPlayerStruct) {
            TarotSumDeclaringPlayer instance_ = ((TarotSumDeclaringPlayerStruct) _instance).getSumDeclaringPlayer();
            if (StringUtil.quickEq(fieldName_, SUM)) {
                res_.setResult(new IntStruct(instance_.getSum()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, STATUS)) {
                res_.setResult(new StringStruct(instance_.getStatus()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, HANDFULS)) {
                res_.setResult(getStringShortTree(instance_.getHandfuls()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MISERES)) {
                res_.setResult(getStringShortTree(instance_.getMiseres()));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof ScoresPlayersStruct) {
            ScoresPlayers instance_ = ((ScoresPlayersStruct)_instance).getScoresPlayers();
            if (StringUtil.quickEq(fieldName_, SUM)) {
                res_.setResult(new RateStruct(instance_.getRate(),TYPE_RATE));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORE)) {
                res_.setResult(new IntStruct(instance_.getScore()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, RATE)) {
                res_.setResult(new RateStruct(instance_.getRate(),TYPE_RATE));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof RankingPlayerVariantGameStruct) {
            RankingPlayerVariantGame instance_ = ((RankingPlayerVariantGameStruct)_instance).getRankingPlayerVariantGame();
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POSITION_DIFF)) {
                res_.setResult(new IntStruct(instance_.getPositionDiff()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POSITION_OUDLERS)) {
                res_.setResult(new IntStruct(instance_.getPositionOudlers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POSITION_CHARACTERS)) {
                res_.setResult(new IntStruct(instance_.getPositionCharacters()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POSITION_STRENGTH_CHARACTERS)) {
                res_.setResult(new IntStruct(instance_.getPositionStrengthCharacters()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, FINAL_POSITION)) {
                res_.setResult(new IntStruct(instance_.getFinalPosition()));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof PointsPlayerVariantGameStruct) {
            PointsPlayerVariantGame instance_ = ((PointsPlayerVariantGameStruct)_instance).getPointsPlayerVariantGame();
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, RATE)) {
                res_.setResult(new IntStruct(instance_.getRate()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORE)) {
                res_.setResult(new IntStruct(instance_.getScore()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MINIMUM_POINTS)) {
                res_.setResult(new IntStruct(instance_.getMinimumPoints()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DIFFERENCE_SCORE)) {
                res_.setResult(new RateStruct(instance_.getDifferenceScore(), TYPE_RATE));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_TRICKS)) {
                res_.setResult(new RateStruct(instance_.getPointsTricks(), TYPE_RATE));
                return res_;
            }
            return res_;
        }
        if (_instance instanceof BonusesPlayersStruct) {
            BonusesPlayers instance_ = ((BonusesPlayersStruct)_instance).getBonusesPlayers();
            if (StringUtil.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, BONUS)) {
                res_.setResult(new IntStruct(instance_.getBonus()));
                return res_;
            }
            return res_;
        }

        if (_instance instanceof TarotLineDealStruct) {
            TarotLineDeal instance_ = ((TarotLineDealStruct)_instance).getLineDeal();
            if (StringUtil.quickEq(fieldName_, NUMBER)) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORES)) {
                res_.setResult(getLongArray(instance_.getScores()));
                return res_;
            }
            return res_;
        }
        //
        if (((TarotBeanStruct)_instance).getInstance() instanceof TarotBean) {
//            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_CLASSIC_GAME)) {
//                res_.setResult(BooleanStruct.of(((TarotBean)((TarotBeanStruct)_instance).getInstance()).playClassicGame()));
//                return res_;
//            }
//            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_VARIANT_MODE_GAME)) {
//                res_.setResult(BooleanStruct.of(((TarotBean)((TarotBeanStruct)_instance).getInstance()).playVariantModeGame()));
//                return res_;
//            }
            if (StringUtil.quickEq(fieldName_, NICKNAMES)) {
                res_.setResult(getStringArray(((TarotBeanStruct)_instance).getInstance().getNicknames()));
                return res_;
            }
//            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
//                res_.setResult(new DefaultStruct(((TarotBean)((TarotBeanStruct)_instance).getInstance()).getScores(), getCustList()));
//                return res_;
//            }
        }
        if (((TarotBeanStruct)_instance).getInstance() instanceof DetailsResultsTarotBean) {
            DetailsResultsTarotBean instance_ = (DetailsResultsTarotBean) ((TarotBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, RATE)) {
                res_.setResult(new IntStruct(instance_.getRate()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MULTIPLIED_TMP)) {
                res_.setResult(new IntStruct(instance_.getMultipliedTmp()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SUM_PLAYERS)) {
                res_.setResult(new IntStruct(instance_.getSumPlayers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ADDITIONNAL_BONUSES_ATTACK)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ADDITIONNAL_BONUSES_DEFENSE)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DIFF_ATTACK_DEFENSE_BONUSES)) {
                res_.setResult(new IntStruct(instance_.getDiffAttackDefenseBonuses()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, BASE_POINTS)) {
                res_.setResult(new IntStruct(instance_.getBasePoints()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new IntStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, PLAYER_SMALL)) {
                res_.setResult(new StringStruct(instance_.getPlayerSmall()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SMALL)) {
                res_.setResult(new StringStruct(instance_.getSmall()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LINES_DECLARING)) {
                res_.setResult(getSumDeclaringPlayerArray(instance_.getLinesDeclaring()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, PLAYERS_SCORES)) {
                res_.setResult(getScoresPlayersArray(instance_.getPlayersScores()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ORDERED_PLAYERS)) {
                res_.setResult(getRankingPlayerVariantGameArray(instance_.getOrderedPlayers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POINTS_PLAYERS)) {
                res_.setResult(getPointsPlayerVariantGameArray(instance_.getPointsPlayers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, BONUSES)) {
                res_.setResult(getBonusesPlayersArray(instance_.getBonuses()));
                return res_;
            }
        }

        if (((TarotBeanStruct)_instance).getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, NUMBER_OUDLERS_TAKER)) {
                res_.setResult(new IntStruct(instance_.getNumberOudlersTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, NEEDLY_SCORES_TAKER)) {
                res_.setResult(new IntStruct(instance_.getNeedlyScoresTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORE_TAKER)) {
                res_.setResult(new IntStruct(instance_.getScoreTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new IntStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ADDITIONNAL_BONUSES_ATTACK)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ADDITIONNAL_BONUSES_DEFENSE)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, SCORE_TAKER_WITHOUT_DECLARING)) {
                res_.setResult(new IntStruct(instance_.getScoreTakerWithoutDeclaring()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MAX_DOUBLED_DIFFERENCE)) {
                res_.setResult(new IntStruct(instance_.getMaxDoubledDifference()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MAX_DIFFERENCE)) {
                res_.setResult(new IntStruct(instance_.getMaxDifference()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, INITIAL_USER_POSITION)) {
                res_.setResult(new IntStruct(instance_.getInitialUserPosition()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, FINAL_USER_POSITION)) {
                res_.setResult(new IntStruct(instance_.getFinalUserPosition()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, TAKER)) {
                res_.setResult(new StringStruct(instance_.getTaker()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, ALONE_TRUMP_ACE_PLAYER)) {
                res_.setResult(new StringStruct(""));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, CALLED_PLAYERS)) {
                res_.setResult(getStringArray(instance_.getCalledPlayers()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, CALLED_CARDS_LIST)) {
                res_.setResult(getStringArray(instance_.getCalledCardsList()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(getLineDealArray(instance_.getLinesDeal()));
                return res_;
            }
        }
        if (((TarotBeanStruct)_instance).getInstance() instanceof RulesTarotBean) {
            RulesTarotBean instance_ = (RulesTarotBean) ((TarotBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(fieldName_, CARTES_BATTUES)) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, REPARTITION)) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MODE)) {
                res_.setResult(new StringStruct(instance_.getMode()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, FIN_PARTIE_TAROT)) {
                res_.setResult(new StringStruct(instance_.getFinPartieTarot()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, DISCARD_AFTER_CALL)) {
                res_.setResult(BooleanStruct.of(instance_.isDiscardAfterCall()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, CONTRATS)) {
                res_.setResult(getStringArray(instance_.getContrats()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, POIGNEES_AUTORISEES)) {
                res_.setResult(getStringIntegerTree(instance_.getPoigneesAutorisees()));
                return res_;
            }
            if (StringUtil.quickEq(fieldName_, MISERES)) {
                res_.setResult(getStringArray(instance_.getMiseres()));
                return res_;
            }
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResultBean(Struct _instance,
                                             ClassMethodId _method, Struct... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((TarotBeanStruct)_instance).getInstance() instanceof TarotBean) {
            if (StringUtil.quickEq(_method.getConstraints().getName(), PLAY_CLASSIC_GAME)) {
                res_.setResult(BooleanStruct.of(((TarotBeanStruct)_instance).getInstance().playClassicGame()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), PLAY_VARIANT_MODE_GAME)) {
                res_.setResult(BooleanStruct.of(((TarotBeanStruct)_instance).getInstance().playVariantModeGame()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), GET_NICKNAMES)) {
                res_.setResult(getStringArray(((TarotBeanStruct)_instance).getInstance().getNicknames()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
                res_.setResult(getLongsArray(((TarotBeanStruct)_instance).getInstance().getScores()));
                return res_;
            }
        }
        if (((TarotBeanStruct)_instance).getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) ((TarotBeanStruct)_instance).getInstance();
            if (StringUtil.quickEq(_method.getConstraints().getName(), WIN)) {
                res_.setResult(BooleanStruct.of(instance_.win()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), EQUALITY)) {
                res_.setResult(BooleanStruct.of(instance_.equality()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), LOOSE)) {
                res_.setResult(BooleanStruct.of(instance_.loose()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SUCCESSFUL_BID)) {
                res_.setResult(BooleanStruct.of(instance_.successfulBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), MID_BID)) {
                res_.setResult(BooleanStruct.of(instance_.midBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), FAILED_BID)) {
                res_.setResult(BooleanStruct.of(instance_.failedBid()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SUCCESSFUL_DECLARED_SLAM_ATTACK)) {
                res_.setResult(BooleanStruct.of(instance_.successfulDeclaredSlamAttack()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SUCCESSFUL_NO_DECLARED_SLAM_ATTACK)) {
                res_.setResult(BooleanStruct.of(instance_.successfulNoDeclaredSlamAttack()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), FAILED_SLAM_ATTACK)) {
                res_.setResult(BooleanStruct.of(instance_.failedSlamAttack()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), NO_SLAM_ATTACK)) {
                res_.setResult(BooleanStruct.of(instance_.noSlamAttack()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), SLAM_DEFENSE)) {
                res_.setResult(BooleanStruct.of(instance_.slamDefense()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), NO_SLAM_DEFENSE)) {
                res_.setResult(BooleanStruct.of(instance_.noSlamDefense()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), ABSOLUTE_DIFF)) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
            if (StringUtil.quickEq(_method.getConstraints().getName(), BID_STRING)) {
                res_.setResult(new StringStruct(instance_.bidString()));
                return res_;
            }
        }
        return res_;
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

    public ResultErrorStd getOtherName(Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ClassField _classField, Struct _instance, Struct _val) {
        return new ResultErrorStd();
    }

    public String getPrimInt() {
        return getContent().getPrimTypes().getAliasPrimInteger();
    }
    protected Struct newSimpleBean(String _language, BeanInfo _bean) {
        ConstructorId id_ = new ConstructorId(_bean.getResolvedClassName(), new StringList(), false);
        Struct[] args_ = NatStdFctOperation.getObjects(Argument.toArgArray(new CustList<Argument>()));
        ResultErrorStd res_ = getOtherResultBean(id_, args_);
        Struct strBean_ = res_.getResult();
        TarotBeanStruct str_ = (TarotBeanStruct) strBean_;
        TarotBean bean_ = str_.getBean();
        bean_.setDataBase(dataBase,dataBaseRules);
        bean_.setLanguage(_language);
        return strBean_;
    }
    public void setDataBase(ResultsTarot _dataBase){
        dataBase = _dataBase;
    }

    public void setDataBaseRules(RulesTarot _dataBase){
        dataBaseRules = _dataBase;
    }
}
