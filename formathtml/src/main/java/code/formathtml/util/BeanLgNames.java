package code.formathtml.util;

import code.bean.Bean;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.ResultErrorStd;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardConstructor;
import code.expressionlanguage.stds.StandardField;
import code.expressionlanguage.stds.StandardMethod;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public class BeanLgNames extends LgNames {

    private final String aliasStringMapObject = "code.util.StringMapObject";
    private final String custList = "$custlist";
    private String aliasRate;
    private String aliasDataBase;

    public void buildBeans() {
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
        method_ = new StandardMethod("beforeDisplaying", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getDataBase", params_, getAliasObject(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasObject());
        method_ = new StandardMethod("setDataBase", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getScope", params_, getAliasString(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setScope", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getLanguage", params_, getAliasString(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(getAliasString());
        method_ = new StandardMethod("setLanguage", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod("getForms", params_, aliasStringMapObject, false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        params_ = new StringList(aliasStringMapObject);
        method_ = new StandardMethod("setForms", params_, getAliasVoid(), false, MethodModifier.NORMAL, "code.bean.Bean");
        methods_.put(method_.getId(), method_);
        std_ = new StandardClass("code.bean.Bean", fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put("code.bean.Bean", std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        std_ = new StandardClass(aliasStringMapObject, fields_, constructors_, methods_, getAliasObject(), MethodModifier.NORMAL);
        getStandards().put(aliasStringMapObject, std_);
        fields_ = new StringMap<StandardField>();
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        StandardClass cl_;
        cl_ = new StandardClass(custList, fields_, constructors_, methods_, getAliasObject(), MethodModifier.ABSTRACT);
        cl_.getDirectInterfaces().add(getAliasCountable());
        cl_.getDirectInterfaces().add(getAliasSimpleIterableType());
        getStandards().put(custList, cl_);
    }
    @Override
    public ResultErrorStd getOtherResult(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        ResultErrorStd res_ = new ResultErrorStd();
        if (_instance.getInstance() instanceof Bean) {
            if (StringList.quickEq(_method.getConstraints().getName(), "beforeDisplaying")) {
                ((Bean)_instance.getInstance()).beforeDisplaying();
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
            if (StringList.quickEq(_method.getConstraints().getName(), "setDataBase")) {
                ((Bean)_instance.getInstance()).setDataBase(_args[0]);
                res_.setResult(NullStruct.NULL_VALUE);
                return res_;
            }
        }
        return getOtherResultBean(_cont, _instance, _method, _args);
    }
    public ResultErrorStd getOtherResultBean(ContextEl _cont, Struct _instance,
            ClassMethodId _method, Object... _args) {
        return new ResultErrorStd();
    }
    public String getAliasRate() {
        return aliasRate;
    }

    public void setAliasRate(String _aliasRate) {
        aliasRate = _aliasRate;
    }

    public String getAliasDataBase() {
        return aliasDataBase;
    }

    public void setAliasDataBase(String _aliasDataBase) {
        aliasDataBase = _aliasDataBase;
    }

    public String getAliasStringMapObject() {
        return aliasStringMapObject;
    }
    public String getCustList() {
        return custList;
    }
}
