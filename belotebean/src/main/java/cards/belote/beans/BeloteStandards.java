package cards.belote.beans;

import cards.belote.enumerations.BidBelote;
import cards.belote.enumerations.DeclaresBelote;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
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
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class BeloteStandards extends BeanLgNames {

    public BeloteStandards() {
        setSelectedBoolean("sb");
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
        std_ = new StandardClass("cards.belote.beans.BeloteBean", fields_, constructors_, methods_, "code.bean.Bean", MethodModifier.ABSTRACT);
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod("playGame", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getNicknames", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getScores", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        getStandards().put("cards.belote.beans.BeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.DetailsResultsBeloteBean", fields_, constructors_, methods_, "cards.belote.beans.BeloteBean", MethodModifier.NORMAL);
        fields_.put("declaring", new StandardField("declaring", getCustList(), false, false, std_));
        getStandards().put("cards.belote.beans.DetailsResultsBeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.SumDeclaringPlayer", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("declaring", new StandardField("declaring", getCustList(), false, false, std_));
        fields_.put("sum", new StandardField("sum", getAliasPrimInteger(), false, false, std_));
        fields_.put("nickname", new StandardField("nickname", getAliasString(), false, false, std_));
        fields_.put("statut", new StandardField("statut", getAliasString(), false, false, std_));
        getStandards().put("cards.belote.beans.SumDeclaringPlayer", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.DeclaringPlayerValue", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("declaring", new StandardField("declaring", getAliasString(), false, false, std_));
        fields_.put("value", new StandardField("value", getAliasPrimInteger(), false, false, std_));
        getStandards().put("cards.belote.beans.DeclaringPlayerValue", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.ResultsBeloteBean", fields_, constructors_, methods_, "cards.belote.beans.BeloteBean", MethodModifier.NORMAL);
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
        method_ = new StandardMethod("slam", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("absoluteDiff", params_, getAliasPrimInteger(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        fields_.put("pointsAttaqueSansPrime", new StandardField("pointsAttaqueSansPrime", getAliasPrimInteger(), false, false, std_));
        fields_.put("pointsAttaqueTemporaire", new StandardField("pointsAttaqueTemporaire", getAliasPrimInteger(), false, false, std_));
        fields_.put("pointsAttaqueDefinitif", new StandardField("pointsAttaqueDefinitif", getAliasPrimInteger(), false, false, std_));
        fields_.put("pointsDefenseSansPrime", new StandardField("pointsDefenseSansPrime", getAliasPrimInteger(), false, false, std_));
        fields_.put("pointsDefenseTemporaire", new StandardField("pointsDefenseTemporaire", getAliasPrimInteger(), false, false, std_));
        fields_.put("pointsDefenseDefinitif", new StandardField("pointsDefenseDefinitif", getAliasPrimInteger(), false, false, std_));
        fields_.put("differenceScoreTaker", new StandardField("differenceScoreTaker", getAliasPrimInteger(), false, false, std_));
        fields_.put("takerNickname", new StandardField("takerNickname", getAliasString(), false, false, std_));
        fields_.put("bidString", new StandardField("bidString", getAliasString(), false, false, std_));
        fields_.put("calledPlayersList", new StandardField("calledPlayersList", getCustList(), false, false, std_));
        fields_.put("linesDeal", new StandardField("linesDeal", getCustList(), false, false, std_));
        getStandards().put("cards.belote.beans.ResultsBeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.LineDeal", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        fields_.put("number", new StandardField("number", getAliasPrimInteger(), false, false, std_));
        fields_.put("scores", new StandardField("scores", getCustList(), false, false, std_));
        getStandards().put("cards.belote.beans.LineDeal", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("cards.belote.beans.RulesBeloteBean", fields_, constructors_, methods_, "cards.belote.beans.BeloteBean", MethodModifier.NORMAL);
        fields_.put("cartesBattues", new StandardField("cartesBattues", getAliasString(), false, false, std_));
        fields_.put("dealAll", new StandardField("dealAll", getAliasPrimBoolean(), false, false, std_));
        fields_.put("annoncesAutorisees", new StandardField("annoncesAutorisees", getCustList(), false, false, std_));
        fields_.put("encheresAutorisees", new StandardField("encheresAutorisees", getCustList(), false, false, std_));
        fields_.put("sousCoupeAdv", new StandardField("sousCoupeAdv", getAliasPrimBoolean(), false, false, std_));
        fields_.put("gestionCoupePartenaire", new StandardField("gestionCoupePartenaire", getAliasString(), false, false, std_));
        fields_.put("repartition", new StandardField("repartition", getAliasString(), false, false, std_));
        fields_.put("comptePointsClassique", new StandardField("comptePointsClassique", getAliasPrimBoolean(), false, false, std_));
        getStandards().put("cards.belote.beans.RulesBeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$BidBelote", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$BidBelote", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("$DeclaresBelote", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        std_.getDirectInterfaces().add(getAliasDisplayable());
        getStandards().put("$DeclaresBelote", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("ResultsBelote", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("ResultsBelote", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("RulesBelote", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("RulesBelote", std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), "cards.belote.beans.DetailsResultsBeloteBean")) {
            DetailsResultsBeloteBean details_ = new DetailsResultsBeloteBean();
            details_.setClassName("cards.belote.beans.DetailsResultsBeloteBean");
            res_.setResult(new StdStruct(details_, "cards.belote.beans.DetailsResultsBeloteBean"));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), "cards.belote.beans.ResultsBeloteBean")) {
            ResultsBeloteBean details_ = new ResultsBeloteBean();
            details_.setClassName("cards.belote.beans.ResultsBeloteBean");
            res_.setResult(new StdStruct(details_, "cards.belote.beans.ResultsBeloteBean"));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), "cards.belote.beans.RulesBeloteBean")) {
            RulesBeloteBean details_ = new RulesBeloteBean();
            details_.setClassName("cards.belote.beans.RulesBeloteBean");
            res_.setResult(new StdStruct(details_, "cards.belote.beans.RulesBeloteBean"));
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof DetailsResultsBeloteBean) {
            if (StringList.quickEq(_classField.getFieldName(), "declaring")) {
                res_.setResult(new StdStruct(((DetailsResultsBeloteBean)_instance.getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SumDeclaringPlayer) {
            if (StringList.quickEq(_classField.getFieldName(), "declaring")) {
                res_.setResult(new StdStruct(((SumDeclaringPlayer)_instance.getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "sum")) {
                res_.setResult(new IntStruct(((SumDeclaringPlayer)_instance.getInstance()).getSum()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nickname")) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)_instance.getInstance()).getNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "statut")) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)_instance.getInstance()).getStatut()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof DeclaringPlayerValue) {
            if (StringList.quickEq(_classField.getFieldName(), "declaring")) {
                res_.setResult(new StringStruct(((DeclaringPlayerValue)_instance.getInstance()).getDeclaring()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "value")) {
                res_.setResult(new IntStruct(((DeclaringPlayerValue)_instance.getInstance()).getValue()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "pointsAttaqueSansPrime")) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueSansPrime()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsAttaqueTemporaire")) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueTemporaire()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsAttaqueDefinitif")) {
                res_.setResult(new IntStruct(instance_.getPointsAttaqueDefinitif()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsDefenseSansPrime")) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseSansPrime()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsDefenseTemporaire")) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseTemporaire()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "pointsDefenseDefinitif")) {
                res_.setResult(new IntStruct(instance_.getPointsDefenseDefinitif()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "differenceScoreTaker")) {
                res_.setResult(new IntStruct(instance_.getDifferenceScoreTaker()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "bidString")) {
                res_.setResult(new StringStruct(instance_.getBidString()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "takerNickname")) {
                res_.setResult(new StringStruct(instance_.getTakerNickname()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "calledPlayersList")) {
                res_.setResult(new StdStruct(instance_.getCalledPlayersList(), getCustList()));
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
        if (_instance.getInstance() instanceof RulesBeloteBean) {
            RulesBeloteBean instance_ = (RulesBeloteBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "cartesBattues")) {
                res_.setResult(new StringStruct(instance_.getCartesBattues()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "dealAll")) {
                res_.setResult(new BooleanStruct(instance_.isDealAll()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "encheresAutorisees")) {
                res_.setResult(new StdStruct(instance_.getEncheresAutorisees(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "sousCoupeAdv")) {
                res_.setResult(new BooleanStruct(instance_.isSousCoupeAdv()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "annoncesAutorisees")) {
                res_.setResult(new StdStruct(instance_.getAnnoncesAutorisees(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "gestionCoupePartenaire")) {
                res_.setResult(new StringStruct(instance_.getGestionCoupePartenaire()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "repartition")) {
                res_.setResult(new StringStruct(instance_.getRepartition()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "comptePointsClassique")) {
                res_.setResult(new BooleanStruct(instance_.isComptePointsClassique()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _classField, _instance);
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof DeclaringPlayerValue) {
            return "cards.belote.beans.DeclaringPlayerValue";
        }
        if (_struct instanceof SumDeclaringPlayer) {
            return "cards.belote.beans.SumDeclaringPlayer";
        }
        if (_struct instanceof LineDeal) {
            return "cards.belote.beans.LineDeal";
        }
        if (_struct instanceof DeclaresBelote) {
            return "$DeclaresBelote";
        }
        if (_struct instanceof BidBelote) {
            return "$BidBelote";
        }
        return getAliasObject();
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof BeloteBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "playGame")) {
                res_.setResult(new BooleanStruct(((BeloteBean)_instance.getInstance()).playGame()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getNicknames")) {
                res_.setResult(new StdStruct(((BeloteBean)_instance.getInstance()).getNicknames(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getScores")) {
                res_.setResult(new StdStruct(((BeloteBean)_instance.getInstance()).getScores(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof ResultsBeloteBean) {
            ResultsBeloteBean instance_ = (ResultsBeloteBean) _instance.getInstance();
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
            if (StringList.quickEq(_method.getConstraints().getName(), "slam")) {
                res_.setResult(new BooleanStruct(instance_.slam()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "absoluteDiff")) {
                res_.setResult(new IntStruct(instance_.absoluteDiff()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _instance, _method, _args);
    }
}
