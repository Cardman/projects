package cards.president.beans;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
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

public final class PresidentStandards extends BeanLgNames {

    public PresidentStandards() {
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
        StringList params_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass("cards.president.beans.PresidentBean", fields_, constructors_, methods_, "code.bean.Bean", MethodModifier.FINAL);
        fields_.put("nicknames", new StandardField("nicknames", getCustList(), false, false, std_));
        fields_.put("linesDeal", new StandardField("linesDeal", getCustList(), false, false, std_));
        getStandards().put("cards.president.beans.PresidentBean", std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass("cards.president.beans.LineDeal", fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        fields_.put("number", new StandardField("number", getAliasPrimInteger(), false, false, std_));
        fields_.put("scores", new StandardField("scores", getCustList(), false, false, std_));
        getStandards().put("cards.president.beans.LineDeal", std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass("cards.president.beans.RulesPresidentBean", fields_, constructors_, methods_, "code.bean.Bean", MethodModifier.FINAL);
        fields_.put("cartesBattues", new StandardField("cartesBattues", getAliasString(), false, false, std_));
        fields_.put("equalty", new StandardField("equalty", getAliasString(), false, false, std_));
        fields_.put("possibleReversing", new StandardField("possibleReversing", getAliasPrimBoolean(), false, false, std_));
        fields_.put("hasToPlay", new StandardField("hasToPlay", getAliasPrimBoolean(), false, false, std_));
        fields_.put("loosingIfFinishByBestCards", new StandardField("loosingIfFinishByBestCards", getAliasPrimBoolean(), false, false, std_));
        fields_.put("switchCards", new StandardField("switchCards", getAliasPrimBoolean(), false, false, std_));
        fields_.put("looserStartsFirst", new StandardField("looserStartsFirst", getAliasPrimBoolean(), false, false, std_));
        fields_.put("nbPlayers", new StandardField("nbPlayers", getAliasPrimInteger(), false, false, std_));
        fields_.put("nbStacks", new StandardField("nbStacks", getAliasPrimInteger(), false, false, std_));
        fields_.put("nbCardsPerPlayerMin", new StandardField("nbCardsPerPlayerMin", getAliasPrimByte(), false, false, std_));
        fields_.put("nbCardsPerPlayerMax", new StandardField("nbCardsPerPlayerMax", getAliasPrimByte(), false, false, std_));
        params_ = new StringList();
        method_ = new StandardMethod("sameAmount", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL,std_);
        methods_.put(method_.getId(), method_);
        getStandards().put("cards.president.beans.RulesPresidentBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("ResultsPresident", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("ResultsPresident", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        std_ = new StandardClass("RulesPresident", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("RulesPresident", std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), "cards.president.beans.PresidentBean")) {
            PresidentBean details_ = new PresidentBean();
            details_.setClassName("cards.president.beans.PresidentBean");
            res_.setResult(new StdStruct(details_, "cards.president.beans.PresidentBean"));
            return res_;
        }
        if (StringList.quickEq(_method.getName(), "cards.president.beans.RulesPresidentBean")) {
            RulesPresidentBean details_ = new RulesPresidentBean();
            details_.setClassName("cards.president.beans.RulesPresidentBean");
            res_.setResult(new StdStruct(details_, "cards.president.beans.RulesPresidentBean"));
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof PresidentBean) {
            if (StringList.quickEq(_classField.getFieldName(), "nicknames")) {
                res_.setResult(new StdStruct(((PresidentBean)_instance.getInstance()).getNicknames(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "linesDeal")) {
                res_.setResult(new StdStruct(((PresidentBean)_instance.getInstance()).getLinesDeal(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof LineDeal) {
            if (StringList.quickEq(_classField.getFieldName(), "scores")) {
                res_.setResult(new StdStruct(((LineDeal)_instance.getInstance()).getScores(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "number")) {
                res_.setResult(new IntStruct(((LineDeal)_instance.getInstance()).getNumber()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof RulesPresidentBean) {
            RulesPresidentBean rules_ = (RulesPresidentBean) _instance.getInstance();
            if (StringList.quickEq(_classField.getFieldName(), "nbPlayers")) {
                res_.setResult(new IntStruct(rules_.getNbPlayers()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nbStacks")) {
                res_.setResult(new IntStruct(rules_.getNbPlayers()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nbCardsPerPlayerMin")) {
                res_.setResult(new ByteStruct(rules_.getNbCardsPerPlayerMin()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "nbCardsPerPlayerMax")) {
                res_.setResult(new ByteStruct(rules_.getNbCardsPerPlayerMax()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "cartesBattues")) {
                res_.setResult(new StringStruct(rules_.getCartesBattues()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "equalty")) {
                res_.setResult(new StringStruct(rules_.getEqualty()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "possibleReversing")) {
                res_.setResult(new BooleanStruct(rules_.isPossibleReversing()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "hasToPlay")) {
                res_.setResult(new BooleanStruct(rules_.isHasToPlay()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "loosingIfFinishByBestCards")) {
                res_.setResult(new BooleanStruct(rules_.isLoosingIfFinishByBestCards()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "switchCards")) {
                res_.setResult(new BooleanStruct(rules_.isSwitchCards()));
                return res_;
            }
            if (StringList.quickEq(_classField.getFieldName(), "looserStartsFirst")) {
                res_.setResult(new BooleanStruct(rules_.isLooserStartsFirst()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _classField, _instance);
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof RulesPresidentBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "sameAmount")) {
                res_.setResult(new BooleanStruct(((RulesPresidentBean)_instance.getInstance()).sameAmount()));
                return res_;
            }
        }
        return res_;
    }
    @Override
    public String getOtherBeanStructClassName(Object _struct, ContextEl _context) {
        if (_struct instanceof LineDeal) {
            return "cards.president.beans.LineDeal";
        }
        return getAliasObject();
    }
}
