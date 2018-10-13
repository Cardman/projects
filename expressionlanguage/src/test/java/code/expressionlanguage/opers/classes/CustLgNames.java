package code.expressionlanguage.opers.classes;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassField;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.ConstructorId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class CustLgNames extends LgNames {

    private String aliasStringList = "code.util.StringList";
    @Override
    public void buildOther() {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        StandardConstructor ctor_;
        StandardType std_;
        StandardClass stdcl_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        params_ = new StringList(getAliasInteger());
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        fields_ = new StringMap<StandardField>();
        constructors_ = new CustList<StandardConstructor>();
        stdcl_ = new StandardClass(aliasStringList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.FINAL);
        ctor_ = new StandardConstructor(new StringList(getAliasString()), true, stdcl_);
        constructors_.add(ctor_);
        params_ = new StringList(getAliasPrimInteger());
        method_ = new StandardMethod("get", params_, getAliasString(), false, MethodModifier.FINAL, stdcl_);
        methods_.put(method_.getId(), method_);
        std_ = stdcl_;
        getStandards().put(aliasStringList, std_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ConstructorId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (StringList.quickEq(_method.getName(), aliasStringList)) {
            res_.setResult(new StdStruct(new StringList(), aliasStringList));
            return res_;
        }
        return res_;
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance) {
        ResultErrorStd res_ = new ResultErrorStd();
        return res_;
    }
    @Override
    public ResultErrorStd setOtherResult(ContextEl _cont,
            ClassField _classField, Struct _instance, Struct _value) {
        ResultErrorStd res_ = new ResultErrorStd();
        return res_;
    }
}
