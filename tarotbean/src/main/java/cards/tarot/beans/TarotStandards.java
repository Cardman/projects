package cards.tarot.beans;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.structs.*;
import code.formathtml.structs.BeanStruct;
import code.formathtml.structs.RealInstanceStruct;
import code.formathtml.structs.StdStruct;
import code.formathtml.util.*;
import code.formathtml.DefaultInitialization;
import code.maths.Rate;
import code.util.*;
import code.util.ints.SimpleEntries;
import code.util.ints.SimpleEntry;
import code.util.ints.SimpleList;

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
    private static final String TYPE_MISERES = "$Miseres";
    private static final String TYPE_HANDFULS = "$Handfuls";
    private static final String TYPE_BID_TAROT = "$BidTarot";
    private static final String TYPE_RATE = "$Rate";
    private static final String TYPE_TAROT_BEAN = "cards.tarot.beans.TarotBean";
    public TarotStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        StringMap<StandardField> fields_;
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(TYPE_TAROT_BEAN, fields_, constructors_, methods_, TYPE_BEAN, MethodModifier.ABSTRACT);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod(PLAY_CLASSIC_GAME, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(PLAY_VARIANT_MODE_GAME, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_NICKNAMES, params_, TYPE_LIST, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(GET_SCORES, params_, TYPE_LIST, false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        getStandards().put(TYPE_TAROT_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RATE, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().put(TYPE_RATE, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_BID_TAROT, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().put(TYPE_BID_TAROT, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_HANDFULS, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().put(TYPE_HANDFULS, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_MISERES, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(TYPE_DISPLAYABLE);
        getStandards().put(TYPE_MISERES, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_DETAILS_RESULTS_TAROT_BEAN, fields_, constructors_, methods_, TYPE_TAROT_BEAN, MethodModifier.NORMAL);
        fields_.put(RATE, new StandardField(RATE, getAliasPrimInteger(), false, false, std_));
        fields_.put(MULTIPLIED_TMP, new StandardField(MULTIPLIED_TMP, getAliasPrimInteger(), false, false, std_));
        fields_.put(SUM_PLAYERS, new StandardField(SUM_PLAYERS, getAliasPrimInteger(), false, false, std_));
        fields_.put(ADDITIONNAL_BONUSES_ATTACK, new StandardField(ADDITIONNAL_BONUSES_ATTACK, getAliasPrimInteger(), false, false, std_));
        fields_.put(ADDITIONNAL_BONUSES_DEFENSE, new StandardField(ADDITIONNAL_BONUSES_DEFENSE, getAliasPrimInteger(), false, false, std_));
        fields_.put(DIFF_ATTACK_DEFENSE_BONUSES, new StandardField(DIFF_ATTACK_DEFENSE_BONUSES, getAliasPrimInteger(), false, false, std_));
        fields_.put(BASE_POINTS, new StandardField(BASE_POINTS, getAliasPrimShort(), false, false, std_));
        fields_.put(DIFFERENCE_SCORE_TAKER, new StandardField(DIFFERENCE_SCORE_TAKER, getAliasPrimShort(), false, false, std_));
        fields_.put(PLAYER_SMALL, new StandardField(PLAYER_SMALL, getAliasString(), false, false, std_));
        fields_.put(SMALL, new StandardField(SMALL, getAliasString(), false, false, std_));
        fields_.put(LINES_DECLARING, new StandardField(LINES_DECLARING, TYPE_LIST, false, false, std_));
        fields_.put(PLAYERS_SCORES, new StandardField(PLAYERS_SCORES, TYPE_LIST, false, false, std_));
        fields_.put(ORDERED_PLAYERS, new StandardField(ORDERED_PLAYERS, TYPE_LIST, false, false, std_));
        fields_.put(POINTS_PLAYERS, new StandardField(POINTS_PLAYERS, TYPE_LIST, false, false, std_));
        fields_.put(BONUSES, new StandardField(BONUSES, TYPE_LIST, false, false, std_));
        getStandards().put(TYPE_DETAILS_RESULTS_TAROT_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_SUM_DECLARING_PLAYER, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(STATUS, new StandardField(STATUS, getAliasString(), false, false, std_));
        fields_.put(HANDFULS, new StandardField(HANDFULS, TYPE_MAP, false, false, std_));
        fields_.put(MISERES, new StandardField(MISERES, TYPE_MAP, false, false, std_));
        fields_.put(SUM, new StandardField(SUM, getAliasPrimInteger(), false, false, std_));
        getStandards().put(TYPE_SUM_DECLARING_PLAYER, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_SCORES_PLAYERS, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(RATE, new StandardField(RATE, TYPE_RATE, false, false, std_));
        fields_.put(SCORE, new StandardField(SCORE, getAliasPrimShort(), false, false, std_));
        getStandards().put(TYPE_SCORES_PLAYERS, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RANKING_PLAYER_VARIANT_GAME, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(POSITION_DIFF, new StandardField(POSITION_DIFF, getAliasPrimShort(), false, false, std_));
        fields_.put(POSITION_OUDLERS, new StandardField(POSITION_OUDLERS, getAliasPrimShort(), false, false, std_));
        fields_.put(POSITION_CHARACTERS, new StandardField(POSITION_CHARACTERS, getAliasPrimShort(), false, false, std_));
        fields_.put(POSITION_STRENGTH_CHARACTERS, new StandardField(POSITION_STRENGTH_CHARACTERS, getAliasPrimShort(), false, false, std_));
        fields_.put(FINAL_POSITION, new StandardField(FINAL_POSITION, getAliasPrimShort(), false, false, std_));
        getStandards().put(TYPE_RANKING_PLAYER_VARIANT_GAME, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_POINTS_PLAYER_VARIANT_GAME, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(RATE, new StandardField(RATE, getAliasPrimShort(), false, false, std_));
        fields_.put(MINIMUM_POINTS, new StandardField(MINIMUM_POINTS, getAliasPrimShort(), false, false, std_));
        fields_.put(SCORE, new StandardField(SCORE, getAliasPrimShort(), false, false, std_));
        fields_.put(DIFFERENCE_SCORE, new StandardField(DIFFERENCE_SCORE, TYPE_RATE, false, false, std_));
        fields_.put(POINTS_TRICKS, new StandardField(POINTS_TRICKS, TYPE_RATE, false, false, std_));
        getStandards().put(TYPE_POINTS_PLAYER_VARIANT_GAME, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_BONUSES_PLAYERS, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NICKNAME, new StandardField(NICKNAME, getAliasString(), false, false, std_));
        fields_.put(BONUS, new StandardField(BONUS, getAliasPrimShort(), false, false, std_));
        getStandards().put(TYPE_BONUSES_PLAYERS, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_TAROT_BEAN, fields_, constructors_, methods_, TYPE_TAROT_BEAN, MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod(WIN, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(EQUALITY, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(LOOSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SUCCESSFUL_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(MID_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FAILED_BID, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SUCCESSFUL_DECLARED_SLAM_ATTACK, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SUCCESSFUL_NO_DECLARED_SLAM_ATTACK, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(FAILED_SLAM_ATTACK, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(NO_SLAM_ATTACK, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(SLAM_DEFENSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(NO_SLAM_DEFENSE, params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(ABSOLUTE_DIFF, params_, getAliasPrimInteger(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(BID_STRING, params_, getAliasString(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        fields_.put(NUMBER_OUDLERS_TAKER, new StandardField(NUMBER_OUDLERS_TAKER, getAliasPrimByte(), false, false, std_));
        fields_.put(NEEDLY_SCORES_TAKER, new StandardField(NEEDLY_SCORES_TAKER, getAliasPrimShort(), false, false, std_));
        fields_.put(SCORE_TAKER, new StandardField(SCORE_TAKER, getAliasPrimShort(), false, false, std_));
        fields_.put(DIFFERENCE_SCORE_TAKER, new StandardField(DIFFERENCE_SCORE_TAKER, getAliasPrimShort(), false, false, std_));
        fields_.put(ADDITIONNAL_BONUSES_ATTACK, new StandardField(ADDITIONNAL_BONUSES_ATTACK, getAliasPrimShort(), false, false, std_));
        fields_.put(ADDITIONNAL_BONUSES_DEFENSE, new StandardField(ADDITIONNAL_BONUSES_DEFENSE, getAliasPrimShort(), false, false, std_));
        fields_.put(SCORE_TAKER_WITHOUT_DECLARING, new StandardField(SCORE_TAKER_WITHOUT_DECLARING, getAliasPrimShort(), false, false, std_));
        fields_.put(MAX_DOUBLED_DIFFERENCE, new StandardField(MAX_DOUBLED_DIFFERENCE, getAliasPrimShort(), false, false, std_));
        fields_.put(MAX_DIFFERENCE, new StandardField(MAX_DIFFERENCE, getAliasPrimShort(), false, false, std_));
        fields_.put(INITIAL_USER_POSITION, new StandardField(INITIAL_USER_POSITION, getAliasPrimShort(), false, false, std_));
        fields_.put(FINAL_USER_POSITION, new StandardField(FINAL_USER_POSITION, getAliasPrimShort(), false, false, std_));
        fields_.put(TAKER, new StandardField(TAKER, getAliasString(), false, false, std_));
        fields_.put(ALONE_TRUMP_ACE_PLAYER, new StandardField(ALONE_TRUMP_ACE_PLAYER, getAliasString(), false, false, std_));
        fields_.put(CALLED_PLAYERS, new StandardField(CALLED_PLAYERS, TYPE_LIST, false, false, std_));
        fields_.put(NICKNAMES, new StandardField(NICKNAMES, TYPE_LIST, false, false, std_));
        fields_.put(CALLED_CARDS_LIST, new StandardField(CALLED_CARDS_LIST, TYPE_LIST, false, false, std_));
        fields_.put(LINES_DEAL, new StandardField(LINES_DEAL, TYPE_LIST, false, false, std_));
        getStandards().put(TYPE_RESULTS_TAROT_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_LINE_DEAL, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put(NUMBER, new StandardField(NUMBER, getAliasPrimInteger(), false, false, std_));
        fields_.put(SCORES, new StandardField(SCORES, TYPE_LIST, false, false, std_));
        getStandards().put(TYPE_LINE_DEAL, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_TAROT_BEAN, fields_, constructors_, methods_, TYPE_TAROT_BEAN, MethodModifier.NORMAL);
        fields_.put(CARTES_BATTUES, new StandardField(CARTES_BATTUES, getAliasString(), false, false, std_));
        fields_.put(REPARTITION, new StandardField(REPARTITION, getAliasString(), false, false, std_));
        fields_.put(MODE, new StandardField(MODE, getAliasString(), false, false, std_));
        fields_.put(FIN_PARTIE_TAROT, new StandardField(FIN_PARTIE_TAROT, getAliasString(), false, false, std_));
        fields_.put(MISERES, new StandardField(MISERES, TYPE_LIST, false, false, std_));
        fields_.put(CONTRATS, new StandardField(CONTRATS, TYPE_LIST, false, false, std_));
        fields_.put(POIGNEES_AUTORISEES, new StandardField(POIGNEES_AUTORISEES, TYPE_MAP, false, false, std_));
        fields_.put(DISCARD_AFTER_CALL, new StandardField(DISCARD_AFTER_CALL, getAliasPrimBoolean(), false, false, std_));
        getStandards().put(TYPE_RULES_TAROT_BEAN, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RESULTS_TAROT, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(TYPE_RESULTS_TAROT, std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass(TYPE_RULES_TAROT, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(TYPE_RULES_TAROT, std_);
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont,
                                             ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), TYPE_DETAILS_RESULTS_TAROT_BEAN)) {
            DetailsResultsTarotBean details_ = new DetailsResultsTarotBean();
            details_.setClassName(TYPE_DETAILS_RESULTS_TAROT_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), TYPE_RESULTS_TAROT_BEAN)) {
            ResultsTarotBean details_ = new ResultsTarotBean();
            details_.setClassName(TYPE_RESULTS_TAROT_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), TYPE_RULES_TAROT_BEAN)) {
            RulesTarotBean details_ = new RulesTarotBean();
            details_.setClassName(TYPE_RULES_TAROT_BEAN);
            res_.setResult(new BeanStruct(details_));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        String fieldName_ = _classField.getFieldName();
        if (((RealInstanceStruct)_instance).getInstance() instanceof TarotBean) {
//            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_CLASSIC_GAME)) {
//                res_.setResult(new BooleanStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).playClassicGame()));
//                return res_;
//            }
//            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_VARIANT_MODE_GAME)) {
//                res_.setResult(new BooleanStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).playVariantModeGame()));
//                return res_;
//            }
            if (StringList.quickEq(fieldName_, NICKNAMES)) {
                res_.setResult(new StdStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).getNicknames(), TYPE_LIST));
                return res_;
            }
//            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
//                res_.setResult(new StdStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).getScores(), getCustList()));
//                return res_;
//            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof DetailsResultsTarotBean) {
            DetailsResultsTarotBean instance_ = (DetailsResultsTarotBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, RATE)) {
                res_.setResult(new IntStruct(instance_.getRate()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MULTIPLIED_TMP)) {
                res_.setResult(new IntStruct(instance_.getMultipliedTmp()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SUM_PLAYERS)) {
                res_.setResult(new IntStruct(instance_.getSumPlayers()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ADDITIONNAL_BONUSES_ATTACK)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ADDITIONNAL_BONUSES_DEFENSE)) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DIFF_ATTACK_DEFENSE_BONUSES)) {
                res_.setResult(new IntStruct(instance_.getDiffAttackDefenseBonuses()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, BASE_POINTS)) {
                res_.setResult(new ShortStruct(instance_.getBasePoints()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new ShortStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, PLAYER_SMALL)) {
                res_.setResult(new StringStruct(instance_.getPlayerSmall()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SMALL)) {
                res_.setResult(new StringStruct(instance_.getSmall()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, LINES_DECLARING)) {
                res_.setResult(new StdStruct(instance_.getLinesDeclaring(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, PLAYERS_SCORES)) {
                res_.setResult(new StdStruct(instance_.getPlayersScores(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ORDERED_PLAYERS)) {
                res_.setResult(new StdStruct(instance_.getOrderedPlayers(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_PLAYERS)) {
                res_.setResult(new StdStruct(instance_.getPointsPlayers(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, BONUSES)) {
                res_.setResult(new StdStruct(instance_.getBonuses(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof SumDeclaringPlayer) {
            SumDeclaringPlayer instance_ = (SumDeclaringPlayer) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, SUM)) {
                res_.setResult(new IntStruct(instance_.getSum()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, STATUS)) {
                res_.setResult(new StringStruct(instance_.getStatus()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, HANDFULS)) {
                res_.setResult(new StdStruct(instance_.getHandfuls(), TYPE_MAP));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MISERES)) {
                res_.setResult(new StdStruct(instance_.getMiseres(), TYPE_MAP));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ScoresPlayers) {
            ScoresPlayers instance_ = (ScoresPlayers) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, SUM)) {
                res_.setResult(new StdStruct(instance_.getRate(),TYPE_RATE));
                return res_;
            }
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORE)) {
                res_.setResult(new ShortStruct(instance_.getScore()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, RATE)) {
                res_.setResult(wrapStd(instance_.getRate(),_cont));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof RankingPlayerVariantGame) {
            RankingPlayerVariantGame instance_ = (RankingPlayerVariantGame) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POSITION_DIFF)) {
                res_.setResult(new ShortStruct(instance_.getPositionDiff()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POSITION_OUDLERS)) {
                res_.setResult(new ShortStruct(instance_.getPositionOudlers()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POSITION_CHARACTERS)) {
                res_.setResult(new ShortStruct(instance_.getPositionCharacters()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POSITION_STRENGTH_CHARACTERS)) {
                res_.setResult(new ShortStruct(instance_.getPositionStrengthCharacters()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, FINAL_POSITION)) {
                res_.setResult(new ShortStruct(instance_.getFinalPosition()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof PointsPlayerVariantGame) {
            PointsPlayerVariantGame instance_ = (PointsPlayerVariantGame) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, RATE)) {
                res_.setResult(new ShortStruct(instance_.getRate()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORE)) {
                res_.setResult(new ShortStruct(instance_.getScore()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MINIMUM_POINTS)) {
                res_.setResult(new ShortStruct(instance_.getMinimumPoints()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DIFFERENCE_SCORE)) {
                res_.setResult(new StdStruct(instance_.getDifferenceScore(), TYPE_RATE));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POINTS_TRICKS)) {
                res_.setResult(new StdStruct(instance_.getPointsTricks(), TYPE_RATE));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof BonusesPlayers) {
            BonusesPlayers instance_ = (BonusesPlayers) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NICKNAME)) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, BONUS)) {
                res_.setResult(new ShortStruct(instance_.getBonus()));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NUMBER_OUDLERS_TAKER)) {
                res_.setResult(new ByteStruct(instance_.getNumberOudlersTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, NEEDLY_SCORES_TAKER)) {
                res_.setResult(new ShortStruct(instance_.getNeedlyScoresTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORE_TAKER)) {
                res_.setResult(new ShortStruct(instance_.getScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DIFFERENCE_SCORE_TAKER)) {
                res_.setResult(new ShortStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ADDITIONNAL_BONUSES_ATTACK)) {
                res_.setResult(new ShortStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ADDITIONNAL_BONUSES_DEFENSE)) {
                res_.setResult(new ShortStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORE_TAKER_WITHOUT_DECLARING)) {
                res_.setResult(new ShortStruct(instance_.getScoreTakerWithoutDeclaring()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MAX_DOUBLED_DIFFERENCE)) {
                res_.setResult(new ShortStruct(instance_.getMaxDoubledDifference()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MAX_DIFFERENCE)) {
                res_.setResult(new ShortStruct(instance_.getMaxDifference()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, INITIAL_USER_POSITION)) {
                res_.setResult(new ShortStruct(instance_.getInitialUserPosition()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, FINAL_USER_POSITION)) {
                res_.setResult(new ShortStruct(instance_.getFinalUserPosition()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, TAKER)) {
                res_.setResult(new StringStruct(instance_.getTaker()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, ALONE_TRUMP_ACE_PLAYER)) {
                res_.setResult(new StringStruct(""));
                return res_;
            }
            if (StringList.quickEq(fieldName_, CALLED_PLAYERS)) {
                res_.setResult(new StdStruct(instance_.getCalledPlayers(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, CALLED_CARDS_LIST)) {
                res_.setResult(new StdStruct(instance_.getCalledCardsList(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, LINES_DEAL)) {
                res_.setResult(new StdStruct(instance_.getLinesDeal(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof LineDeal) {
            LineDeal instance_ = (LineDeal) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, NUMBER)) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, SCORES)) {
                res_.setResult(StdStruct.newListLong(instance_.getScores(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof RulesTarotBean) {
            RulesTarotBean instance_ = (RulesTarotBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(fieldName_, CARTES_BATTUES)) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, REPARTITION)) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MODE)) {
                res_.setResult(new StringStruct(instance_.getMode()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, FIN_PARTIE_TAROT)) {
                res_.setResult(new StringStruct(instance_.getFinPartieTarot()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, DISCARD_AFTER_CALL)) {
                res_.setResult(new BooleanStruct(instance_.isDiscardAfterCall()));
                return res_;
            }
            if (StringList.quickEq(fieldName_, CONTRATS)) {
                res_.setResult(new StdStruct(instance_.getContrats(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(fieldName_, POIGNEES_AUTORISEES)) {
                res_.setResult(new StdStruct(instance_.getPoigneesAutorisees(), TYPE_MAP));
                return res_;
            }
            if (StringList.quickEq(fieldName_, MISERES)) {
                res_.setResult(new StdStruct(instance_.getMiseres(), TYPE_LIST));
                return res_;
            }
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (((RealInstanceStruct)_instance).getInstance() instanceof TarotBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_CLASSIC_GAME)) {
                res_.setResult(new BooleanStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).playClassicGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), PLAY_VARIANT_MODE_GAME)) {
                res_.setResult(new BooleanStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).playVariantModeGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_NICKNAMES)) {
                res_.setResult(new StdStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).getNicknames(), TYPE_LIST));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), GET_SCORES)) {
                res_.setResult(new StdStruct(((TarotBean)((RealInstanceStruct)_instance).getInstance()).getScores(), TYPE_LIST));
                return res_;
            }
        }
        if (((RealInstanceStruct)_instance).getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) ((RealInstanceStruct)_instance).getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), WIN)) {
                res_.setResult(new BooleanStruct(instance_.win()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), EQUALITY)) {
                res_.setResult(new BooleanStruct(instance_.equality()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), LOOSE)) {
                res_.setResult(new BooleanStruct(instance_.loose()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SUCCESSFUL_BID)) {
                res_.setResult(new BooleanStruct(instance_.successfulBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), MID_BID)) {
                res_.setResult(new BooleanStruct(instance_.midBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), FAILED_BID)) {
                res_.setResult(new BooleanStruct(instance_.failedBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SUCCESSFUL_DECLARED_SLAM_ATTACK)) {
                res_.setResult(new BooleanStruct(instance_.successfulDeclaredSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SUCCESSFUL_NO_DECLARED_SLAM_ATTACK)) {
                res_.setResult(new BooleanStruct(instance_.successfulNoDeclaredSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), FAILED_SLAM_ATTACK)) {
                res_.setResult(new BooleanStruct(instance_.failedSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), NO_SLAM_ATTACK)) {
                res_.setResult(new BooleanStruct(instance_.noSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), SLAM_DEFENSE)) {
                res_.setResult(new BooleanStruct(instance_.slamDefense()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), NO_SLAM_DEFENSE)) {
                res_.setResult(new BooleanStruct(instance_.noSlamDefense()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), ABSOLUTE_DIFF)) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), BID_STRING)) {
                res_.setResult(new StringStruct(instance_.bidString()));
                return res_;
            }
        }
        return res_;
    }

    private String getOtherBeanStructClassName(Object _struct) {
        if (_struct instanceof Rate) {
            return TYPE_RATE;
        }
        if (_struct instanceof Handfuls) {
            return TYPE_HANDFULS;
        }
        if (_struct instanceof Miseres) {
            return TYPE_MISERES;
        }
        if (_struct instanceof ScoresPlayers) {
            return TYPE_SCORES_PLAYERS;
        }
        if (_struct instanceof BonusesPlayers) {
            return TYPE_BONUSES_PLAYERS;
        }
        if (_struct instanceof PointsPlayerVariantGame) {
            return TYPE_POINTS_PLAYER_VARIANT_GAME;
        }
        if (_struct instanceof RankingPlayerVariantGame) {
            return TYPE_RANKING_PLAYER_VARIANT_GAME;
        }
        if (_struct instanceof SumDeclaringPlayer) {
            return TYPE_SUM_DECLARING_PLAYER;
        }
        if (_struct instanceof LineDeal) {
            return TYPE_LINE_DEAL;
        }
        if (_struct instanceof BidTarot) {
            return TYPE_BID_TAROT;
        }
        if (_struct instanceof Bean) {
            return ((Bean)_struct).getClassName();
        }
        if (_struct instanceof SimpleList) {
            return TYPE_LIST;
        }
        if (_struct instanceof SimpleEntries) {
            return TYPE_MAP;
        }
        if (_struct instanceof SimpleEntry) {
            return TYPE_ENTRY;
        }
        if (_struct instanceof SimpleItr) {
            return TYPE_ITERATOR;
        }
        return getAliasObject();
    }

    @Override
    protected Struct wrapStd(Object _element, ExecutableCode _ex) {
        if (_element == null) {
            return NullStruct.NULL_VALUE;
        }
        if (_element instanceof Byte) {
            return new ByteStruct((Byte) _element);
        }
        if (_element instanceof Short) {
            return new ShortStruct((Short) _element);
        }
        if (_element instanceof Character) {
            return new CharStruct((Character) _element);
        }
        if (_element instanceof Integer) {
            return new IntStruct((Integer) _element);
        }
        if (_element instanceof Long) {
            return new LongStruct((Long) _element);
        }
        if (_element instanceof Float) {
            return new FloatStruct((Float) _element);
        }
        if (_element instanceof Double) {
            return new DoubleStruct((Double) _element);
        }
        if (_element instanceof Boolean) {
            return new BooleanStruct((Boolean) _element);
        }
        if (_element instanceof String) {
            return new StringStruct((String) _element);
        }
        if (_element instanceof StringBuilder) {
            return new StringBuilderStruct((StringBuilder) _element);
        }
        String aliasObject_ = getAliasObject();
        String className_ = getOtherBeanStructClassName(_element);
        if (StringList.quickEq(className_, getAliasObject())) {
            return StdStruct.newInstance(_element, aliasObject_);
        }
        return StdStruct.newInstance(_element, className_);
    }
    public ResultErrorStd getOtherName(ContextEl _cont, Struct _instance) {
        return new ResultErrorStd();
    }
    public ResultErrorStd setOtherResult(ContextEl _cont, ClassField _classField, Struct _instance, Object _value) {
        return new ResultErrorStd();
    }
    public ResultErrorStd getOtherStructToBeValidated(StringList _values, String _className, ContextEl _context) {
        return new ResultErrorStd();
    }

}
