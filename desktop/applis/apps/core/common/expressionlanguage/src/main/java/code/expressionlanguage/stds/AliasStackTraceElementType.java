package code.expressionlanguage.stds;

import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.FctStackTraceCurrent;
import code.expressionlanguage.fcts.FctStackTraceCurrentFull;
import code.expressionlanguage.fcts.FctStackTraceToStr;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;
import code.util.StringList;

public final class AliasStackTraceElementType {

    private String aliasStackTraceElement;
    private String aliasStackTraceElementToString;

    private String aliasCurrentStack;
    private String aliasCurrentFullStack;

    public void build(LgNames _stds) {
        CustList<CstFieldInfo> fields_;
        StringList params_;
        StandardMethod method_;
        CustList<StandardConstructor> constructors_;
        CustList<StandardMethod> methods_;
        StandardClass stdcl_;
        methods_ = new CustList<StandardMethod>();
        constructors_ = new CustList<StandardConstructor>();
        fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        stdcl_ = new StandardClass(aliasStackTraceElement, fields_, constructors_, methods_, aliasObject_ , StdClassModifier.ABSTRACT);
        String out_ = aliasStackTraceElement;
        out_ = StringExpUtil.getPrettyArrayType(out_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentStack, params_, out_, false, MethodModifier.STATIC,new FctStackTraceCurrent());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentFullStack, params_, out_, false, MethodModifier.STATIC,new FctStackTraceCurrentFull());
        methods_.add( method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasStackTraceElementToString, params_, aliasString_, false, MethodModifier.NORMAL,new FctStackTraceToStr());
        methods_.add( method_);
        _stds.getStandards().put(aliasStackTraceElement, stdcl_);
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
