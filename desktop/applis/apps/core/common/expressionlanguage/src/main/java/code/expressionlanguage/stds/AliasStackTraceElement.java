package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.opers.util.ClassMethodId;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.opers.util.MethodModifier;
import code.expressionlanguage.structs.StackTraceElementStruct;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.ObjectMap;
import code.util.StringList;
import code.util.StringMap;

public final class AliasStackTraceElement {

    private String aliasStackTraceElement;

    private String aliasCurrentStack;
    private String aliasCurrentFullStack;

    public void build(LgNames _stds) {
        StringMap<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        ObjectMap<MethodId, StandardMethod> methods_;
        StandardClass stdcl_;
        methods_ = new ObjectMap<MethodId, StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new StringMap<StandardField>();
        String aliasObject_ = _stds.getAliasObject();
        String aliasString_ = _stds.getAliasString();
        String aliasToString_ = _stds.getAliasToStringMethod();
        stdcl_ = new StandardClass(aliasStackTraceElement, fields_, constructors_, methods_, aliasObject_ , MethodModifier.ABSTRACT);
        String out_ = aliasStackTraceElement;
        out_ = PrimitiveTypeUtil.getPrettyArrayType(out_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentStack, params_, out_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentFullStack, params_, out_, false, MethodModifier.STATIC, stdcl_);
        methods_.put(method_.getId(), method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasToString_, params_, aliasString_, false, MethodModifier.NORMAL, stdcl_);
        methods_.put(method_.getId(), method_);
        _stds.getStandards().put(aliasStackTraceElement, stdcl_);
    }

    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        AliasStackTraceElement ref_ = lgNames_.getStackElt();
        if (StringList.quickEq(name_, ref_.aliasCurrentStack)) {
            result_.setResult(_cont.newStackTraceElementArray());
            return result_;
        }
        if (StringList.quickEq(name_, ref_.aliasCurrentFullStack)) {
            result_.setResult(_cont.newStackTraceElementArrayFull());
            return result_;
        }
        result_.setResult(StackTraceElementStruct.getStack(_struct).getDisplayedString(_cont));
        return result_;
    }
    public String getAliasStackTraceElement() {
        return aliasStackTraceElement;
    }

    public void setAliasStackTraceElement(String _aliasStackTraceElement) {
        aliasStackTraceElement = _aliasStackTraceElement;
    }

    public String getAliasCurrentStack() {
        return aliasCurrentStack;
    }

    public void setAliasCurrentStack(String _aliasCurrentStack) {
        aliasCurrentStack = _aliasCurrentStack;
    }

    public String getAliasCurrentFullStack() {
        return aliasCurrentFullStack;
    }

    public void setAliasCurrentFullStack(String _aliasCurrentFullStack) {
        aliasCurrentFullStack = _aliasCurrentFullStack;
    }

}
