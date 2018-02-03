package cards.tarot.beans;

import cards.tarot.enumerations.BidTarot;
import cards.tarot.enumerations.Handfuls;
import cards.tarot.enumerations.Miseres;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.StringStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.formathtml.DefaultInitialization;
import code.formathtml.util.BeanLgNames;
import code.maths.Rate;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class TarotStandards extends BeanLgNames {
    public TarotStandards() {
        DefaultInitialization.basicStandards(this);
    }
    @Override
    public void buildOther() {
        buildBeans();
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass("cards.tarot.beans.TarotBean", fields_, constructors_, methods_, "code.bean.Bean", MethodModifier.ABSTRACT);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod("playClassicGame", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("playVariantModeGame", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getNicknames", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getScores", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        getStandards().put("cards.tarot.beans.TarotBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("ls", fields_, constructors_, methods_, getCustList(), MethodModifier.NORMAL);
        getStandards().put("ls", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$Rate", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$Rate", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$BidTarot", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$BidTarot", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$Handfuls", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$Handfuls", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$Miseres", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$Miseres", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.DetailsResultsTarotBean", fields_, constructors_, methods_, "cards.tarot.beans.TarotBean", MethodModifier.NORMAL);
        fields_.put("rate", new StandardField("rate", getAliasPrimInteger(), false, false, std_));
        fields_.put("multipliedTmp", new StandardField("multipliedTmp", getAliasPrimInteger(), false, false, std_));
        fields_.put("sumPlayers", new StandardField("sumPlayers", getAliasPrimInteger(), false, false, std_));
        fields_.put("additionnalBonusesAttack", new StandardField("additionnalBonusesAttack", getAliasPrimInteger(), false, false, std_));
        fields_.put("additionnalBonusesDefense", new StandardField("additionnalBonusesDefense", getAliasPrimInteger(), false, false, std_));
        fields_.put("diffAttackDefenseBonuses", new StandardField("diffAttackDefenseBonuses", getAliasPrimInteger(), false, false, std_));
        fields_.put("basePoints", new StandardField("basePoints", getAliasPrimShort(), false, false, std_));
        fields_.put("differenceScoreTaker", new StandardField("differenceScoreTaker", getAliasPrimShort(), false, false, std_));
        fields_.put("playerSmall", new StandardField("playerSmall", getAliasString(), false, false, std_));
        fields_.put("small", new StandardField("small", getAliasString(), false, false, std_));
        fields_.put("linesDeclaring", new StandardField("linesDeclaring", getCustList(), false, false, std_));
        fields_.put("playersScores", new StandardField("playersScores", getCustList(), false, false, std_));
        fields_.put("orderedPlayers", new StandardField("orderedPlayers", getCustList(), false, false, std_));
        fields_.put("pointsPlayers", new StandardField("pointsPlayers", getCustList(), false, false, std_));
        fields_.put("bonuses", new StandardField("bonuses", getCustList(), false, false, std_));
        getStandards().put("cards.tarot.beans.DetailsResultsTarotBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.SumDeclaringPlayer", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("status", new StandardField("status", getAliasString(), false, false, std_));
        fields_.put("handfuls", new StandardField("handfuls", getCustMap(), false, false, std_));
        fields_.put("miseres", new StandardField("miseres", getCustMap(), false, false, std_));
        fields_.put("sum", new StandardField("sum", getAliasPrimInteger(), false, false, std_));
        getStandards().put("cards.tarot.beans.SumDeclaringPlayer", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.ScoresPlayers", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("rate", new StandardField("rate", "$Rate", false, false, std_));
        fields_.put("score", new StandardField("score", getAliasPrimShort(), false, false, std_));
        getStandards().put("cards.tarot.beans.ScoresPlayers", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.RankingPlayerVariantGame", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("positionDiff", new StandardField("positionDiff", getAliasPrimShort(), false, false, std_));
        fields_.put("positionOudlers", new StandardField("positionOudlers", getAliasPrimShort(), false, false, std_));
        fields_.put("positionCharacters", new StandardField("positionCharacters", getAliasPrimShort(), false, false, std_));
        fields_.put("positionStrengthCharacters", new StandardField("positionStrengthCharacters", getAliasPrimShort(), false, false, std_));
        fields_.put("finalPosition", new StandardField("finalPosition", getAliasPrimShort(), false, false, std_));
        getStandards().put("cards.tarot.beans.RankingPlayerVariantGame", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.PointsPlayerVariantGame", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("rate", new StandardField("rate", getAliasPrimShort(), false, false, std_));
        fields_.put("minimumPoints", new StandardField("minimumPoints", getAliasPrimShort(), false, false, std_));
        fields_.put("score", new StandardField("score", getAliasPrimShort(), false, false, std_));
        fields_.put("differenceScore", new StandardField("differenceScore", "$Rate", false, false, std_));
        fields_.put("pointsTricks", new StandardField("pointsTricks", "$Rate", false, false, std_));
        getStandards().put("cards.tarot.beans.PointsPlayerVariantGame", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.BonusesPlayers", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("bonus", new StandardField("bonus", getAliasPrimShort(), false, false, std_));
        getStandards().put("cards.tarot.beans.BonusesPlayers", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.ResultsTarotBean", fields_, constructors_, methods_, "cards.tarot.beans.TarotBean", MethodModifier.NORMAL);
        params_ = new StringList();
        method_ = new StandardMethod("win", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("equality", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("loose", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("successfulBid", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("midBid", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("failedBid", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("successfulDeclaredSlamAttack", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("successfulNoDeclaredSlamAttack", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("failedSlamAttack", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("noSlamAttack", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("slamDefense", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("noSlamDefense", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("absoluteDiff", params_, getAliasPrimInteger(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("bidString", params_, getAliasString(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        fields_.put("numberOudlersTaker", new StandardField("numberOudlersTaker", getAliasPrimByte(), false, false, std_));
        fields_.put("needlyScoresTaker", new StandardField("needlyScoresTaker", getAliasPrimShort(), false, false, std_));
        fields_.put("scoreTaker", new StandardField("scoreTaker", getAliasPrimShort(), false, false, std_));
        fields_.put("differenceScoreTaker", new StandardField("differenceScoreTaker", getAliasPrimShort(), false, false, std_));
        fields_.put("additionnalBonusesAttack", new StandardField("additionnalBonusesAttack", getAliasPrimShort(), false, false, std_));
        fields_.put("additionnalBonusesDefense", new StandardField("additionnalBonusesDefense", getAliasPrimShort(), false, false, std_));
        fields_.put("scoreTakerWithoutDeclaring", new StandardField("scoreTakerWithoutDeclaring", getAliasPrimShort(), false, false, std_));
        fields_.put("maxDoubledDifference", new StandardField("maxDoubledDifference", getAliasPrimShort(), false, false, std_));
        fields_.put("maxDifference", new StandardField("maxDifference", getAliasPrimShort(), false, false, std_));
        fields_.put("initialUserPosition", new StandardField("initialUserPosition", getAliasPrimShort(), false, false, std_));
        fields_.put("finalUserPosition", new StandardField("finalUserPosition", getAliasPrimShort(), false, false, std_));
        fields_.put("taker", new StandardField("taker", getAliasString(), false, false, std_));
        fields_.put("aloneTrumpAcePlayer", new StandardField("aloneTrumpAcePlayer", getAliasString(), false, false, std_));
        fields_.put("calledPlayers", new StandardField("calledPlayers", getCustList(), false, false, std_));
        fields_.put("nicknames", new StandardField("nicknames", getCustList(), false, false, std_));
        fields_.put("calledCardsList", new StandardField("calledCardsList", getCustList(), false, false, std_));
        fields_.put("linesDeal", new StandardField("linesDeal", getCustList(), false, false, std_));
        getStandards().put("cards.tarot.beans.ResultsTarotBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.LineDeal", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("number", new StandardField("number", getAliasPrimInteger(), false, false, std_));
        fields_.put("scores", new StandardField("scores", getCustList(), false, false, std_));
        getStandards().put("cards.tarot.beans.LineDeal", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.tarot.beans.RulesTarotBean", fields_, constructors_, methods_, "cards.tarot.beans.TarotBean", MethodModifier.NORMAL);
        fields_.put("cartesBattues", new StandardField("cartesBattues", getAliasString(), false, false, std_));
        fields_.put("repartition", new StandardField("repartition", getAliasString(), false, false, std_));
        fields_.put("mode", new StandardField("mode", getAliasString(), false, false, std_));
        fields_.put("finPartieTarot", new StandardField("finPartieTarot", getAliasString(), false, false, std_));
        fields_.put("miseres", new StandardField("miseres", getCustList(), false, false, std_));
        fields_.put("contrats", new StandardField("contrats", getCustList(), false, false, std_));
        fields_.put("poigneesAutorisees", new StandardField("poigneesAutorisees", getCustMap(), false, false, std_));
        fields_.put("discardAfterCall", new StandardField("discardAfterCall", getAliasPrimBoolean(), false, false, std_));
        getStandards().put("cards.tarot.beans.RulesTarotBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("ResultsTarot", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("ResultsTarot", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("RulesTarot", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("RulesTarot", std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), "cards.tarot.beans.DetailsResultsTarotBean")) {
            DetailsResultsTarotBean details_ = new DetailsResultsTarotBean();
            details_.setClassName("cards.tarot.beans.DetailsResultsTarotBean");
            res_.setResult(new StdStruct(details_, "cards.tarot.beans.DetailsResultsTarotBean"));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), "cards.tarot.beans.ResultsTarotBean")) {
            ResultsTarotBean details_ = new ResultsTarotBean();
            details_.setClassName("cards.tarot.beans.ResultsTarotBean");
            res_.setResult(new StdStruct(details_, "cards.tarot.beans.ResultsTarotBean"));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), "cards.tarot.beans.RulesTarotBean")) {
            RulesTarotBean details_ = new RulesTarotBean();
            details_.setClassName("cards.tarot.beans.RulesTarotBean");
            res_.setResult(new StdStruct(details_, "cards.tarot.beans.RulesTarotBean"));
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof DetailsResultsTarotBean) {
            DetailsResultsTarotBean instance_ = (DetailsResultsTarotBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "rate")) {
                res_.setResult(new IntStruct(instance_.getRate()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "multipliedTmp")) {
                res_.setResult(new IntStruct(instance_.getMultipliedTmp()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "sumPlayers")) {
                res_.setResult(new IntStruct(instance_.getSumPlayers()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "additionnalBonusesAttack")) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "additionnalBonusesDefense")) {
                res_.setResult(new IntStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "diffAttackDefenseBonuses")) {
                res_.setResult(new IntStruct(instance_.getDiffAttackDefenseBonuses()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "basePoints")) {
                res_.setResult(new ShortStruct(instance_.getBasePoints()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "differenceScoreTaker")) {
                res_.setResult(new ShortStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "playerSmall")) {
                res_.setResult(new StringStruct(instance_.getPlayerSmall()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "playerSmall")) {
                res_.setResult(new StringStruct(instance_.getSmall()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "linesDeclaring")) {
                res_.setResult(new StdStruct(instance_.getLinesDeclaring(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "playersScores")) {
                res_.setResult(new StdStruct(instance_.getPlayersScores(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "orderedPlayers")) {
                res_.setResult(new StdStruct(instance_.getOrderedPlayers(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsPlayers")) {
                res_.setResult(new StdStruct(instance_.getPointsPlayers(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "bonuses")) {
                res_.setResult(new StdStruct(instance_.getBonuses(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SumDeclaringPlayer) {
            SumDeclaringPlayer instance_ = (SumDeclaringPlayer) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "sum")) {
                res_.setResult(new IntStruct(instance_.getSum()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "status")) {
                res_.setResult(new StringStruct(instance_.getStatus()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "handfuls")) {
                res_.setResult(new StdStruct(instance_.getHandfuls(),getCustMap()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "miseres")) {
                res_.setResult(new StdStruct(instance_.getMiseres(),getCustMap()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ScoresPlayers) {
            ScoresPlayers instance_ = (ScoresPlayers) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "sum")) {
                res_.setResult(new StdStruct(instance_.getRate(),"$Rate"));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "score")) {
                res_.setResult(new ShortStruct(instance_.getScore()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof RankingPlayerVariantGame) {
            RankingPlayerVariantGame instance_ = (RankingPlayerVariantGame) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "positionDiff")) {
                res_.setResult(new ShortStruct(instance_.getPositionDiff()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "positionOudlers")) {
                res_.setResult(new ShortStruct(instance_.getPositionOudlers()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "positionCharacters")) {
                res_.setResult(new ShortStruct(instance_.getPositionCharacters()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "positionStrengthCharacters")) {
                res_.setResult(new ShortStruct(instance_.getPositionStrengthCharacters()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "finalPosition")) {
                res_.setResult(new ShortStruct(instance_.getFinalPosition()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof PointsPlayerVariantGame) {
            PointsPlayerVariantGame instance_ = (PointsPlayerVariantGame) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "rate")) {
                res_.setResult(new ShortStruct(instance_.getRate()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "score")) {
                res_.setResult(new ShortStruct(instance_.getScore()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "minimumPoints")) {
                res_.setResult(new ShortStruct(instance_.getMinimumPoints()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "differenceScore")) {
                res_.setResult(new StdStruct(instance_.getDifferenceScore(), "$Rate"));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsTricks")) {
                res_.setResult(new StdStruct(instance_.getPointsTricks(), "$Rate"));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof BonusesPlayers) {
            BonusesPlayers instance_ = (BonusesPlayers) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(instance_.getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "bonus")) {
                res_.setResult(new ShortStruct(instance_.getBonus()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "numberOudlersTaker")) {
                res_.setResult(new ByteStruct(instance_.getNumberOudlersTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "needlyScoresTaker")) {
                res_.setResult(new ShortStruct(instance_.getNeedlyScoresTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "scoreTaker")) {
                res_.setResult(new ShortStruct(instance_.getScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "differenceScoreTaker")) {
                res_.setResult(new ShortStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "additionnalBonusesAttack")) {
                res_.setResult(new ShortStruct(instance_.getAdditionnalBonusesAttack()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "additionnalBonusesDefense")) {
                res_.setResult(new ShortStruct(instance_.getAdditionnalBonusesDefense()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "scoreTakerWithoutDeclaring")) {
                res_.setResult(new ShortStruct(instance_.getScoreTakerWithoutDeclaring()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "maxDoubledDifference")) {
                res_.setResult(new ShortStruct(instance_.getMaxDoubledDifference()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "maxDifference")) {
                res_.setResult(new ShortStruct(instance_.getMaxDifference()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "initialUserPosition")) {
                res_.setResult(new ShortStruct(instance_.getInitialUserPosition()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "finalUserPosition")) {
                res_.setResult(new ShortStruct(instance_.getFinalUserPosition()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "taker")) {
                res_.setResult(new StringStruct(instance_.getTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "aloneTrumpAcePlayer")) {
                res_.setResult(new StringStruct(instance_.getAloneTrumpAcePlayer()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "calledPlayers")) {
                res_.setResult(new StdStruct(instance_.getCalledPlayers(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "calledCardsList")) {
                res_.setResult(new StdStruct(instance_.getCalledCardsList(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "linesDeal")) {
                res_.setResult(new StdStruct(instance_.getLinesDeal(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof LineDeal) {
            LineDeal instance_ = (LineDeal) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "number")) {
                res_.setResult(new IntStruct(instance_.getNumber()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "scores")) {
                res_.setResult(new StdStruct(instance_.getScores(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof RulesTarotBean) {
            RulesTarotBean instance_ = (RulesTarotBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "cartesBattues")) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "repartition")) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "mode")) {
                res_.setResult(new StringStruct(instance_.getMode()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "finPartieTarot")) {
                res_.setResult(new StringStruct(instance_.getFinPartieTarot()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "discardAfterCall")) {
                res_.setResult(new BooleanStruct(instance_.isDiscardAfterCall()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "contrats")) {
                res_.setResult(new StdStruct(instance_.getContrats(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "poigneesAutorisees")) {
                res_.setResult(new StdStruct(instance_.getPoigneesAutorisees(), getCustMap()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "miseres")) {
                res_.setResult(new StdStruct(instance_.getMiseres(), getCustList()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _classField, _instance);
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof TarotBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "playClassicGame")) {
                res_.setResult(new BooleanStruct(((TarotBean)_instance.getInstance()).playClassicGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "playVariantModeGame")) {
                res_.setResult(new BooleanStruct(((TarotBean)_instance.getInstance()).playVariantModeGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getNicknames")) {
                res_.setResult(new StdStruct(((TarotBean)_instance.getInstance()).getNicknames(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getScores")) {
                res_.setResult(new StdStruct(((TarotBean)_instance.getInstance()).getScores(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ResultsTarotBean) {
            ResultsTarotBean instance_ = (ResultsTarotBean) _instance.getInstance();
            if (StringList.quickEq(_method.getConstraints().getName(), "win")) {
                res_.setResult(new BooleanStruct(instance_.win()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "equality")) {
                res_.setResult(new BooleanStruct(instance_.equality()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "loose")) {
                res_.setResult(new BooleanStruct(instance_.loose()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "successfulBid")) {
                res_.setResult(new BooleanStruct(instance_.successfulBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "midBid")) {
                res_.setResult(new BooleanStruct(instance_.midBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "failedBid")) {
                res_.setResult(new BooleanStruct(instance_.failedBid()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "successfulDeclaredSlamAttack")) {
                res_.setResult(new BooleanStruct(instance_.successfulDeclaredSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "successfulNoDeclaredSlamAttack")) {
                res_.setResult(new BooleanStruct(instance_.successfulNoDeclaredSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "failedSlamAttack")) {
                res_.setResult(new BooleanStruct(instance_.failedSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "noSlamAttack")) {
                res_.setResult(new BooleanStruct(instance_.noSlamAttack()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "slamDefense")) {
                res_.setResult(new BooleanStruct(instance_.slamDefense()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "noSlamDefense")) {
                res_.setResult(new BooleanStruct(instance_.noSlamDefense()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "absoluteDiff")) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "bidString")) {
                res_.setResult(new StringStruct(instance_.bidString()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _instance, _method, _args);
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof Rate) {
            return "$Rate";
        }
        if (_struct instanceof Handfuls) {
            return "$Handfuls";
        }
        if (_struct instanceof Miseres) {
            return "$Miseres";
        }
        if (_struct instanceof ScoresPlayers) {
            return "cards.tarot.beans.ScoresPlayers";
        }
        if (_struct instanceof BonusesPlayers) {
            return "cards.tarot.beans.BonusesPlayers";
        }
        if (_struct instanceof PointsPlayerVariantGame) {
            return "cards.tarot.beans.PointsPlayerVariantGame";
        }
        if (_struct instanceof RankingPlayerVariantGame) {
            return "cards.tarot.beans.RankingPlayerVariantGame";
        }
        if (_struct instanceof SumDeclaringPlayer) {
            return "cards.tarot.beans.SumDeclaringPlayer";
        }
        if (_struct instanceof LineDeal) {
            return "cards.tarot.beans.LineDeal";
        }
        if (_struct instanceof BidTarot) {
            return "$BidTarot";
        }
        return getAliasObject();
    }
}
