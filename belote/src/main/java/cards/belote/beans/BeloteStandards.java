package cards.belote.beans;

import code.expressionlanguage.ContextEl;
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

    @Override
    public void buildOther() {
        DefaultInitialization.basicStandards(this);
        buildBeans();
        StringMap<StandardField> fields_;
        fields_ = new StringMap<StandardField>();
        StandardClass std_;
        ObjectMap<MethodId, StandardMethod> methods_;
        CustList<StandardConstructor> constructors_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        StandardMethod method_;
        constructors_ = new CustList<StandardConstructor>();
        StringList params_;
        params_ = new StringList();
        method_ = new StandardMethod("beforeDisplaying", params_, getAliasVoid(), false, MethodModifier.NORMAL, "cards.belote.beans.BeloteBean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("playGame", params_, getAliasPrimBoolean(), false, MethodModifier.NORMAL, "cards.belote.beans.BeloteBean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getNicknames", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, "cards.belote.beans.BeloteBean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getScores", params_, getAliasSimpleIterableType(), false, MethodModifier.NORMAL, "cards.belote.beans.BeloteBean");
        methods_.put(method_.getId(), method_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass("cards.belote.beans.BeloteBean", fields_, constructors_, methods_, "code.bean.Bean", MethodModifier.ABSTRACT);
        getStandards().put("cards.belote.beans.BeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod("getDeclaring", params_, getCustList(), false, MethodModifier.NORMAL, "cards.belote.beans.BeloteBean");
        methods_.put(method_.getId(), method_);
        std_ = new StandardClass("cards.belote.beans.DetailsResultsBeloteBean", fields_, constructors_, methods_, "cards.belote.beans.BeloteBean", MethodModifier.NORMAL);
        getStandards().put("cards.belote.beans.DetailsResultsBeloteBean", std_);
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        params_ = new StringList();
        method_ = new StandardMethod("getDeclaring", params_, getCustList(), false, MethodModifier.NORMAL, "cards.belote.beans.SumDeclaringPlayer");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getSum", params_, getAliasPrimInteger(), false, MethodModifier.NORMAL, "cards.belote.beans.SumDeclaringPlayer");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getNickname", params_, getAliasString(), false, MethodModifier.NORMAL, "cards.belote.beans.SumDeclaringPlayer");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getStatut", params_, getAliasString(), false, MethodModifier.NORMAL, "cards.belote.beans.SumDeclaringPlayer");
        methods_.put(method_.getId(), method_);
        std_ = new StandardClass("cards.belote.beans.SumDeclaringPlayer", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("cards.belote.beans.SumDeclaringPlayer", std_);
        //getCustList()
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), "cards.belote.beans.DetailsResultsBeloteBean")) {
            res_.setResult(new StdStruct(new DetailsResultsBeloteBean(), "cards.belote.beans.DetailsResultsBeloteBean"));
            return res_;
        }
        return super.getOtherResult(_cont, _method, _args);
    }
    @Override
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof DetailsResultsBeloteBean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "getDeclaring")) {
                res_.setResult(new StdStruct(((DetailsResultsBeloteBean)_instance.getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof SumDeclaringPlayer) {
            if (StringList.quickEq(_method.getConstraints().getName(), "getDeclaring")) {
                res_.setResult(new StdStruct(((SumDeclaringPlayer)_instance.getInstance()).getDeclaring(), getCustList()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getSum")) {
                res_.setResult(new IntStruct(((SumDeclaringPlayer)_instance.getInstance()).getSum()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getNickname")) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)_instance.getInstance()).getNickname()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getStatut")) {
                res_.setResult(new StringStruct(((SumDeclaringPlayer)_instance.getInstance()).getStatut()));
                return res_;
            }
        }
        if (_instance.getInstance() instanceof DeclaringPlayerValue) {
            if (StringList.quickEq(_method.getConstraints().getName(), "getDeclaring")) {
                res_.setResult(new StringStruct(((DeclaringPlayerValue)_instance.getInstance()).getDeclaring()));
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "getValue")) {
                res_.setResult(new IntStruct(((DeclaringPlayerValue)_instance.getInstance()).getValue()));
                return res_;
            }
        }
        return super.getOtherResult(_cont, _instance, _method, _args);
    }
}
