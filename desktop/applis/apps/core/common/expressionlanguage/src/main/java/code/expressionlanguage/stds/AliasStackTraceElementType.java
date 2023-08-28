package code.expressionlanguage.stds;

import code.expressionlanguage.analyze.errors.KeyValueMemberName;
import code.expressionlanguage.common.CstFieldInfo;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.fcts.FctStackTraceCurrent;
import code.expressionlanguage.fcts.FctStackTraceCurrentFull;
import code.expressionlanguage.fcts.FctStackTraceToStr;
import code.expressionlanguage.functionid.MethodModifier;
import code.expressionlanguage.functionid.StdClassModifier;
import code.sml.util.TranslationsFile;
import code.util.CustList;
import code.util.StringList;
import code.util.StringMap;

public final class AliasStackTraceElementType {
    private static final String STACK_TRACE_ELEMENT="_________1877";
    private static final String CURRENT_STACK="_________1878";
    private static final String CURRENT_FULL_STACK="_________1879";
    private static final String STACK_TRACE_ELEMENT_TO_STRING="_________1880";
    private String aliasStackTraceElement;
    private String aliasStackTraceElementToString;

    private String aliasCurrentStack;
    private String aliasCurrentFullStack;

    public void build(StringMap<String> _util, StringMap<String> _cust, StringMap<String> _mapping) {
        setAliasCurrentFullStack(LgNamesContent.get(_util,_cust,_mapping.getVal(CURRENT_FULL_STACK)));
        setAliasStackTraceElementToString(LgNamesContent.get(_util,_cust,_mapping.getVal(STACK_TRACE_ELEMENT_TO_STRING)));
        setAliasStackTraceElement(LgNamesContent.get(_util,_cust,_mapping.getVal(STACK_TRACE_ELEMENT)));
        setAliasCurrentStack(LgNamesContent.get(_util,_cust,_mapping.getVal(CURRENT_STACK)));
    }
    public static void en(TranslationsFile _en){
        _en.add(STACK_TRACE_ELEMENT,"StackTraceElement=$core.Stack");
        _en.add(CURRENT_STACK,"CurrentStack=current");
        _en.add(CURRENT_FULL_STACK,"CurrentFullStack=currentFull");
        _en.add(STACK_TRACE_ELEMENT_TO_STRING,"StackTraceElementToString=toString");
    }
    public static void fr(TranslationsFile _fr){
        _fr.add(STACK_TRACE_ELEMENT,"StackTraceElement=$coeur.Pile");
        _fr.add(CURRENT_STACK,"CurrentStack=courante");
        _fr.add(CURRENT_FULL_STACK,"CurrentFullStack=couranteComplete");
        _fr.add(STACK_TRACE_ELEMENT_TO_STRING,"StackTraceElementToString=chaine");
    }
    public static void mapping(StringMap<String> _m){
        _m.addEntry(STACK_TRACE_ELEMENT,"StackTraceElement");
        _m.addEntry(CURRENT_STACK,"CurrentStack");
        _m.addEntry(CURRENT_FULL_STACK,"CurrentFullStack");
        _m.addEntry(STACK_TRACE_ELEMENT_TO_STRING,"StackTraceElementToString");
    }
    public StringMap<String> allRefTypes(StringMap<String> _mapping) {
        StringMap<String> list_ = new StringMap<String>();
        list_.addEntry(_mapping.getVal(STACK_TRACE_ELEMENT), getAliasStackTraceElement());
        return list_;
    }
    public StringMap<CustList<KeyValueMemberName>> allTableTypeMethodNames(StringMap<String> _mapping) {
        StringMap<CustList<KeyValueMemberName>> map_ = new StringMap<CustList<KeyValueMemberName>>();
        map_.addEntry(getAliasStackTraceElement(), new CustList<KeyValueMemberName>(
                new KeyValueMemberName(_mapping.getVal(CURRENT_STACK), getAliasCurrentStack()),
                new KeyValueMemberName(_mapping.getVal(CURRENT_FULL_STACK),getAliasCurrentFullStack()),
                new KeyValueMemberName(_mapping.getVal(STACK_TRACE_ELEMENT_TO_STRING), getAliasStackTraceElementToString())));
        return map_;
    }
    public void build(LgNames _stds) {
        CustList<StandardMethod> methods_ = new CustList<StandardMethod>();
        CustList<StandardConstructor> constructors_ = new CustList<StandardConstructor>();
        CustList<CstFieldInfo> fields_ = new CustList<CstFieldInfo>();
        String aliasObject_ = _stds.getContent().getCoreNames().getAliasObject();
        String aliasString_ = _stds.getContent().getCharSeq().getAliasString();
        StandardClass stdcl_ = new StandardClass(aliasStackTraceElement, fields_, constructors_, methods_, aliasObject_, StdClassModifier.ABSTRACT);
        stdcl_.addSuperStdTypes(_stds.getCoreNames().getObjType());
        String out_ = aliasStackTraceElement;
        out_ = StringExpUtil.getPrettyArrayType(out_);
        StringList params_ = new StringList();
        StandardMethod method_ = new StandardMethod(aliasCurrentStack, params_, out_, false, MethodModifier.STATIC, new FctStackTraceCurrent());
        StandardNamedFunction.addFct(methods_,method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasCurrentFullStack, params_, out_, false, MethodModifier.STATIC,new FctStackTraceCurrentFull());
        StandardNamedFunction.addFct(methods_,method_);
        params_ = new StringList();
        method_ = new StandardMethod(aliasStackTraceElementToString, params_, aliasString_, false, MethodModifier.NORMAL,new FctStackTraceToStr());
        StandardNamedFunction.addFct(methods_,method_);
        StandardType.addType(_stds.getStandards(),aliasStackTraceElement, stdcl_);
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
