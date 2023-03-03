package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.FctStackTraceCurrent;
import code.expressionlanguage.fcts.FctStackTraceCurrentFull;
import code.expressionlanguage.fcts.FctStackTraceToStr;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasStackTraceElementType {
    private static final String CURRENT_FULL_STACK = "CurrentFullStack";
    private static final String STACK_TRACE_ELEMENT_TO_STRING = "StackTraceElementToString";
    private static final String STACK_TRACE_ELEMENT = "StackTraceElement";
    private static final String CURRENT_STACK = "CurrentStack";
    private String aliasStackTraceElement;
    private String aliasStackTraceElementToString;

    private String aliasCurrentStack;
    private String aliasCurrentFullStack;

    public void build(StringMap<String> _util, StringMap<String> _cust) {
        setAliasCurrentFullStack(LgNamesContent.get(_util,_cust, CURRENT_FULL_STACK));
        setAliasStackTraceElementToString(LgNamesContent.get(_util,_cust, STACK_TRACE_ELEMENT_TO_STRING));
        setAliasStackTraceElement(LgNamesContent.get(_util,_cust, STACK_TRACE_ELEMENT));
        setAliasCurrentStack(LgNamesContent.get(_util,_cust, CURRENT_STACK));
    }
    public StringMap<String> allRefTypes() {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(STACK_TRACE_ELEMENT, getAliasStackTraceElement());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames() {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasStackTraceElement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(CURRENT_STACK, getAliasCurrentStack()),
                new KeyValueMemberName(CURRENT_FULL_STACK,getAliasCurrentFullStack()),
                new KeyValueMemberName(STACK_TRACE_ELEMENT_TO_STRING, getAliasStackTraceElementToString())));
        return map_;
    }
    public void build(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        StandardClass stdcl_ = new StandardClass(aliasStackTraceElement, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        String out_ = aliasStackTraceElement;
        out_ = StringExpUtil.getPrettyArrayType(out_);
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasCurrentStack, params_, out_, false, MethodModifier.STATIC, new FctStackTraceCurrent());
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
