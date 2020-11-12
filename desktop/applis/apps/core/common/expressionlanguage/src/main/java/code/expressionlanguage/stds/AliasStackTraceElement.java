package code.expressionlanguage.stds;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.NumParsers;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ExecutingUtil;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.expressionlanguage.structs.Struct;
import code.util.CustList;
import code.util.StringList;
import code.util.core.StringUtil;

public final class AliasStackTraceElement {

    private String aliasStackTraceElement;
    private String aliasStackTraceElementToString;

    private String aliasCurrentStack;
    private String aliasCurrentFullStack;

    public void build(LgNames _stds) {
        CustList<StandardField> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<StandardField>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        stdcl_ = new StandardClass(aliasStackTraceElement, fields_, constructors_, methods_, aliasObject_ , StdClassModifier.ABSTRACT);
        String out_ = aliasStackTraceElement;
        out_ = StringExpUtil.getPrettyArrayType(out_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentStack, params_, out_, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentFullStack, params_, out_, false, MethodModifier.STATIC);
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasStackTraceElementToString, params_, aliasString_, false, MethodModifier.NORMAL);
        methods_.add( method_);
        _stds.getStandards().put(aliasStackTraceElement, stdcl_);
    }

    public static ResultErrorStd invokeMethod(ContextEl _cont, ClassMethodId _method, Struct _struct) {
        ResultErrorStd result_ = new ResultErrorStd();
        String name_ = _method.getConstraints().getName();
        LgNames lgNames_ = _cont.getStandards();
        AliasStackTraceElement ref_ = lgNames_.getStackElt();
        if (StringUtil.quickEq(name_, ref_.aliasCurrentStack)) {
            result_.setResult(ExecutingUtil.newStackTraceElementArray(_cont));
            return result_;
        }
        if (StringUtil.quickEq(name_, ref_.aliasCurrentFullStack)) {
            result_.setResult(ExecutingUtil.newStackTraceElementArrayFull(_cont));
            return result_;
        }
        result_.setResult(NumParsers.getStack(_struct).getDisplayedString(_cont));
        return result_;
    }
    public String getAliasStackTraceElement() {
        return aliasStackTraceElement;
    }

    public void setAliasStackTraceElement(String _aliasStackTraceElement) {
        aliasStackTraceElement = _aliasStackTraceElement;
    }

    public String getAliasStackTraceElementToString() {
        return aliasStackTraceElementToString;
    }

    public void setAliasStackTraceElementToString(String _aliasStackTraceElementToString) {
        aliasStackTraceElementToString = _aliasStackTraceElementToString;
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
