package code.expressionlanguage.inherits;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.ErrorType;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.exec.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.*;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

public final class Templates {

    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';
    public static final char SEP_CLASS_CHAR = '.';
    public static final String PREFIX_VAR_TYPE = "#";
    public static final char SUB_TYPE_CHAR = '?';
    public static final char SUP_TYPE_CHAR = '!';
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';
    private Templates() {
    }

    public static String getIdFromAllTypes(String _type) {
        return getAllTypes(_type).first();
    }

    public static StringList getAllSepCommaTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == Templates.LT) {
                    count_++;
                }
                if (curChar_ == Templates.GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                out_.append(curChar_);
                if (out_.toString().trim().charAt(0) != Templates.LT) {
                    count_++;
                }
                i_++;
                continue;
            }
            if (curChar_ == Templates.COMMA) {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }
    public static StringList getAllInnerTypesSingleDotted(String _type, Analyzable _an) {
        StringList types_ = new StringList();
        int len_ = _type.length();
        boolean inner_ = false;
        StringBuilder builtId_ = new StringBuilder();
        int i_ = 0;
        int count_ = 0;
        while (i_ < len_) {
            char cur_ = _type.charAt(i_);
            if (count_ > 0) {
                builtId_.append(cur_);
                if (cur_ == LT) {
                    count_++;
                }
                if (cur_ == GT) {
                    count_--;
                    if (count_ == 0) {
                        inner_ = true;
                    }
                }
                i_++;
                continue;
            }
            if (cur_ == LT) {
                builtId_.append(cur_);
                count_++;
                i_++;
                continue;
            }
            if (cur_ == SEP_CLASS_CHAR) {
                //if builtId_.toString() is a type => inner_ is true
                String foundId_ = builtId_.toString();
                if (!inner_) {
                    boolean foundPkg_ = false;
                    for (String p: _an.getClasses().getPackagesFound()) {
                        if (StringList.quickEq(p, ContextEl.removeDottedSpaces(foundId_))) {
                            foundPkg_ = true;
                            break;
                        }
                    }
                    if (!foundPkg_) {
                        inner_ = true;
                    }
                }
                if (!inner_) {
                    builtId_.append(cur_);
                } else {
                    types_.add(builtId_.toString());
                    builtId_.delete(0, builtId_.length());
                }
            } else {
                builtId_.append(cur_);
            }
            i_++;
        }
        types_.add(builtId_.toString());
        return types_;
    }
    public static StringList getAllInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == Templates.LT) {
                    count_++;
                }
                if (curChar_ == Templates.GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                out_.append(curChar_);
                count_++;
                i_++;
                continue;
            }
            if (_type.substring(i_).startsWith(INNER_TYPE)) {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
                i_++;
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }
    public static StringList getAllTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StringBuilder id_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == Templates.LT) {
                    count_++;
                }
                if (curChar_ == Templates.GT) {
                    count_--;
                }
                if (count_ == 1 && curChar_ == Templates.COMMA) {
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                if (count_ == 0 && curChar_ == Templates.GT) {
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                count_++;
                i_++;
                continue;
            }
            id_.append(curChar_);
            i_++;
        }
        types_.add(0, id_.toString());
        return types_;
    }

    /**Calls Templates.isCorrect*/
    public static String correctClassPartsDynamic(String _className, ExecutableCode _context, boolean _exact, boolean _wildCard) {
        String className_ = PartTypeUtil.processExec(_className, _context);
        if (className_.isEmpty()) {
            return "";
        }
        StringMap<StringList> mapping_ = new StringMap<StringList>();
        if (_wildCard) {
            if (className_.startsWith(SUB_TYPE)) {
                return "";
            }
            if (className_.startsWith(SUP_TYPE)) {
                return "";
            }
            StringList allArgTypes_ = getAllTypes(className_).mid(1);
            for (String m: allArgTypes_) {
                if (m.startsWith(SUB_TYPE)) {
                    return "";
                }
                if (m.startsWith(SUP_TYPE)) {
                    return "";
                }
            }
        }
        if (isCorrectTemplateAll(className_, mapping_, _context, _exact)) {
            return className_;
        }
        return "";
    }

    public static String getFullTypeByBases(String _subType, String _superType, Analyzable _context) {
        String baseSubType_ = getIdFromAllTypes(_subType);
        String baseSuperType_ = getIdFromAllTypes(_superType);
        if (!PrimitiveTypeUtil.canBeUseAsArgument(baseSuperType_, baseSubType_, _context)) {
            return null;
        }
        DimComp dc_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseSubType_);
        String compo_ = dc_.getComponent();
        if (PrimitiveTypeUtil.isPrimitive(compo_,_context)) {
            return _superType;
        }
        GeneType inf_ = _context.getClassBody(compo_);
        String geneSubType_ = inf_.getGenericString();
        String subType_ = PrimitiveTypeUtil.getPrettyArrayType(geneSubType_, dc_.getDim());
        StringList curClasses_ = new StringList(subType_);
        String generic_ = null;
        if (StringList.quickEq(_subType, _superType)) {
            generic_ = _subType;
        } else if (StringList.quickEq(baseSubType_, baseSuperType_)) {
            generic_ = _subType;
        }
        if (generic_ == null) {
            generic_ = requestGenericSuperType(_subType, curClasses_, _superType, _context);
        }
        return generic_;
    }

    static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, Analyzable _context) {
        return isCorrectTemplateAll(_className, _inherit, _context, true);
    }
    public static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _exact) {
        String type_ = getIdFromAllTypes(_className);
        String fct_ = _context.getStandards().getAliasFct();
        boolean v_ = StringList.quickEq(type_, fct_);
        if (!isCorrectTemplate(_className, _inherit, _context, v_, _exact)) {
            return false;
        }
        StringList current_ = new StringList(_className);
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList types_ = getAllTypes(c);
                for (String n: types_.mid(1)) {
                    if (!isCorrectTemplate(n, _inherit, _context, v_)) {
                        return false;
                    }
                    next_.add(n);
                }
            }
            if (next_.isEmpty()) {
                return true;
            }
            current_ = next_;
        }
    }
    static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Analyzable _context) {
        return isCorrectTemplate(_className, _inherit, _context, false, true);
    }
    static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _voidOk) {
        return isCorrectTemplate(_className, _inherit, _context, _voidOk, true);
    }
    static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _voidOk, boolean _exact) {
        StringList types_ = getAllTypes(_className);
        String className_ = types_.first();
        if (StringList.quickEq(className_, SUB_TYPE)) {
            return true;
        }
        int wc_ = 0;
        if (className_.startsWith(SUB_TYPE)) {
            className_ = className_.substring(SUB_TYPE.length());
            wc_ = 1;
        } else if (className_.startsWith(SUP_TYPE)) {
            className_ = className_.substring(SUP_TYPE.length());
            wc_ = 1;
        }
        String void_ = _context.getStandards().getAliasVoid();
        if (_voidOk && StringList.quickEq(className_, void_)) {
            return true;
        }
        className_ = PrimitiveTypeUtil.getQuickComponentBaseType(className_).getComponent();
        if (PrimitiveTypeUtil.isPrimitive(className_, _context)) {
            return true;
        }
        if (className_.startsWith(PREFIX_VAR_TYPE)) {
            return _inherit.contains(className_.substring(1));
        }
        if (_exact) {
            if (!correctNbParameters(_className.substring(wc_), _context)) {
                return false;
            }
        } else {
            if (types_.size() > 1 && !correctNbParameters(_className.substring(wc_), _context)) {
                return false;
            }
            if (types_.size() == 1) {
                return true;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        EqList<StringList> boundsAll_;
        GeneType r_ = _context.getClassBody(className_);
        boundsAll_ = new EqList<StringList>();
        for (TypeVar t:r_.getParamTypesMapValues()) {
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
        }
        for (StringList t: boundsAll_) {
            i_++;
            String arg_ = types_.get(i_);
            if (StringList.quickEq(arg_, SUB_TYPE)) {
                continue;
            }
            String comp_ = arg_;
            if (comp_.startsWith(SUB_TYPE)) {
                comp_ = comp_.substring(SUB_TYPE.length());
            } else if (comp_.startsWith(SUP_TYPE)) {
                comp_ = comp_.substring(SUP_TYPE.length());
            }
            DimComp dimCompArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(comp_);
            comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(PREFIX_VAR_TYPE);
            StringList bounds_ = new StringList();
            if (lookInInherit_) {
                bounds_.addAllElts(_inherit.getVal(comp_.substring(1)));
            } else {
                bounds_.add(comp_);
            }
            for (String e: t) {
                Mapping m_ = new Mapping();
                String param_ = format(_className.substring(wc_), e, _context);
                if (param_ == null) {
                    return false;
                }
                m_.setParam(param_);
                boolean ok_ = false;
                for (String v: bounds_) {
                    m_.setArg(v);
                    m_.setMapping(_inherit);
                    if (isCorrectOrNumbers(m_, _context)) {
                        ok_ = true;
                        break;
                    }
                }
                if (!ok_) {
                    return false;
                }
            }
        }
        return true;
    }
    public static String wildCardFormatReturn(boolean _staticMember, String _first, String _second, Analyzable _classes) {
        if (_staticMember) {
            return _second;
        }
        DimComp dc_ = PrimitiveTypeUtil.getQuickComponentBaseType(_second);
        StringList types_ = getAllTypes(_first);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
        String objType_ = _classes.getStandards().getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (TypeVar t: typeVar_) {
                index_++;
                if (StringList.quickEq(t.getName(), name_)) {
                    String formatted_ = types_.get(index_+1);
                    //return type, field getting
                    if (StringList.quickEq(formatted_, SUB_TYPE)) {
                        return PrimitiveTypeUtil.getPrettyArrayType(objType_,arr_);
                    }
                    if (formatted_.startsWith(SUB_TYPE)) {
                        return PrimitiveTypeUtil.getPrettyArrayType(formatted_.substring(SUB_TYPE.length()),arr_);
                    }
                    if (formatted_.startsWith(SUP_TYPE)) {
                        return PrimitiveTypeUtil.getPrettyArrayType(objType_,arr_);
                    }
                    return PrimitiveTypeUtil.getPrettyArrayType(formatted_,arr_);
                }
            }
            return objType_;
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        if (typeVar_.size() == types_.size() - 1){
            int i_ = CustList.FIRST_INDEX;
            for (TypeVar t: typeVar_) {
                i_++;
                String arg_ = types_.get(i_);
                varTypes_.put(t.getName(), arg_);
            }
        }
        return getWildCardFormattedTypeReturn(_second, varTypes_);
    }
    public static String wildCardFormatParam(boolean _staticMember, String _first, String _second, Analyzable _classes) {
        if (_staticMember) {
            return _second;
        }
        DimComp dc_ = PrimitiveTypeUtil.getQuickComponentBaseType(_second);
        StringList types_ = getAllTypes(_first);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
        String objType_ = _classes.getStandards().getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (TypeVar t: typeVar_) {
                index_++;
                if (StringList.quickEq(t.getName(), name_)) {
                    String formatted_ = types_.get(index_+1);
                    //parameters, field affectation
                    if (formatted_.startsWith(SUB_TYPE)) {
                        return null;
                    }
                    if (formatted_.startsWith(SUP_TYPE)) {
                        return PrimitiveTypeUtil.getPrettyArrayType(formatted_.substring(SUP_TYPE.length()),arr_);
                    }
                    return PrimitiveTypeUtil.getPrettyArrayType(formatted_,arr_);
                }
            }
            return null;
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        if (typeVar_.size() == types_.size() - 1){
            int i_ = CustList.FIRST_INDEX;
            for (TypeVar t: typeVar_) {
                i_++;
                String arg_ = types_.get(i_);
                varTypes_.put(t.getName(), arg_);
            }
        }
        return getWildCardFormattedTypeParam(objType_,_second, varTypes_);
    }
    public static String reflectFormat(String _first, String _second, Analyzable _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getReflectFormattedType(_second, varTypes_);
    }
    public static String format(String _first, String _second, Analyzable _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getFormattedType(_second, varTypes_);
    }
    public static String quickFormat(String _first, String _second, Analyzable _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getQuickFormattedType(_second, varTypes_);
    }
    public static String getGenericString(String _className, Analyzable _classes) {
        String types_ = getIdFromAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        return root_.getGenericString();
    }
    public static String getMadeVarTypes(String _className, StringList _classNames,ExecutableCode _context) {
        String type_ = getIdFromAllTypes(_className);
        String fct_ = _context.getStandards().getAliasFct();
        if (StringList.quickEq(type_, fct_)) {
            if (_classNames.isEmpty()) {
                return null;
            }
            StringList parts_ = new StringList();
            for (String s: _classNames) {
                if (StringList.quickEq(s, Templates.SUB_TYPE)) {
                    parts_.add(_context.getStandards().getAliasObject());
                    continue;
                }
                if (s.startsWith(Templates.SUB_TYPE)) {
                    parts_.add(s.substring(Templates.SUB_TYPE.length()));
                    continue;
                }
                if (s.startsWith(Templates.SUP_TYPE)) {
                    parts_.add(s.substring(Templates.SUP_TYPE.length()));
                    continue;
                }
                parts_.add(s);
            }
            StringBuilder str_ = new StringBuilder(fct_);
            str_.append(Templates.TEMPLATE_BEGIN);
            str_.append(parts_.join(Templates.TEMPLATE_SEP));
            str_.append(Templates.TEMPLATE_END);
            return str_.toString();
        }
        GeneType root_ = _context.getClassBody(type_);
        String pref_ = root_.getGenericString();
        StringMap<String> varTypes_ = new StringMap<String>();
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
        if (typeVar_.size() != _classNames.size()) {
            return null;
        }
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            String arg_ = _classNames.get(i_);
            if (arg_.contains(PREFIX_VAR_TYPE)) {
                return null;
            }
            varTypes_.put(t.getName(), arg_);
            i_++;
        }
        String formatted_ = getQuickFormattedType(pref_, varTypes_);
        if (!isCorrectTemplateAll(formatted_, new StringMap<StringList>(), _context, true)) {
            return null;
        }
        return formatted_;
    }
    static StringMap<String> getVarTypes(String _className, Analyzable _context) {
        StringList types_ = getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        StringMap<String> varTypes_ = new StringMap<String>();
        if (root_ == null) {
            return varTypes_;
        }
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return varTypes_;
    }

    static String getQuickFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            quickReplaceType(str_,_type,_varTypes,diese_,i);
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            quickReplaceType(str_,_type,_varTypes,diese_,len_);
        }
        return str_.toString();
    }
    private static void quickReplaceType(StringBuilder _str, String _type, StringMap<String> _varTypes, int _diese, int _max) {
        String sub_ = _type.substring(_diese+1, _max);
        if (_varTypes.contains(sub_)) {
            String value_ = _varTypes.getVal(sub_);
            _str.append(value_);
        } else {
            sub_ = _type.substring(_diese, _max);
            _str.append(sub_);
        }
    }
    static String getFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                if (!tryReplaceType(_varTypes, str_, sub_)) {
                    return null;
                }
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            if (_varTypes.contains(sub_)) {
                if (!tryReplaceType(_varTypes, str_, sub_)) {
                    return null;
                }
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    private static boolean tryReplaceType(StringMap<String> _varTypes, StringBuilder _str, String _sub) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        String value_ = _varTypes.getVal(_sub);
        if (isSubOrSubChar(_str,j_)) {
            return false;
        }
        if (value_.startsWith(SUB_TYPE)) {
            _str.insert(j_ +1, SUB_TYPE);
            _str.append(value_.substring(SUB_TYPE.length()));
        } else if (value_.startsWith(SUP_TYPE)) {
            _str.insert(j_ +1, SUP_TYPE);
            _str.append(value_.substring(SUP_TYPE.length()));
        } else {
            _str.append(value_);
        }
        return true;
    }
    static String getWildCardFormattedTypeReturn(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringList.quickEq(value_, SUB_TYPE)) {
                    if (isSubOrSubChar(str_, j_)) {
                        j_--;
                    }
                    str_.delete(j_+1, max_+1);
                    str_.append(SUB_TYPE);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUB_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUB_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(SUB_TYPE);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isNotChar(str_, j_, SUP_TYPE_CHAR)) {
                        str_.insert(j_ +1, SUP_TYPE);
                    }
                    str_.append(bound_);
                    str_.append(_type.charAt(i));
                    var_ = false;
                    continue;

                }
                str_.append(value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        return str_.toString();
    }
    static String getWildCardFormattedTypeParam(String _objType, String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                String value_ = _varTypes.getVal(sub_);
                int max_ = str_.length() -1;
                int j_ = getMaxIndex(str_, max_);
                if (StringList.quickEq(value_, SUB_TYPE)) {
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return null;
                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return null;
                }
                if (value_.startsWith(SUP_TYPE)) {
                    String bound_= value_.substring(SUP_TYPE.length());
                    if (isSubChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    if (isSupChar(str_, j_)) {
                        str_.delete(j_, max_+1);
                        str_.append(_objType);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return null;
                }
                str_.append(value_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        return str_.toString();
    }

    static int getMaxIndex(StringBuilder str_, int max_) {
        int j_ = max_;
        while (j_ >= 0) {
            if (str_.charAt(j_) != ARR_BEG) {
                break;
            }
            j_--;
        }
        return j_;
    }

    static boolean isNotChar(StringBuilder str_, int j_, char subTypeChar) {
        return j_ >= 0 && str_.charAt(j_) != subTypeChar;
    }

    static boolean isSubOrSubChar(StringBuilder str_, int j_) {
        return isSubChar(str_,j_) || isSupChar(str_, j_);
    }

    static boolean isSubChar(StringBuilder str_, int j_) {
        return j_ >= 0 && str_.charAt(j_) == SUB_TYPE_CHAR;
    }

    static boolean isSupChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUP_TYPE_CHAR;
    }

    static String getReflectFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isDollarWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                replaceReflectedType(_varTypes, str_, sub_);
            } else {
                sub_ = _type.substring(diese_, i);
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            if (_varTypes.contains(sub_)) {
                replaceReflectedType(_varTypes, str_, sub_);
            } else {
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }

    static void replaceReflectedType(StringMap<String> _varTypes, StringBuilder str_, String sub_) {
        int j_ = getMaxIndex(str_, str_.length() - 1);
        String value_ = _varTypes.getVal(sub_);
        if (value_.startsWith(SUB_TYPE)) {
            if (j_ >= 0 && str_.charAt(j_) != SUB_TYPE_CHAR && str_.charAt(j_) != SUP_TYPE_CHAR) {
                str_.insert(j_ +1, SUB_TYPE);
            }
            str_.append(value_.substring(SUB_TYPE.length()));
        } else if (value_.startsWith(SUP_TYPE)) {
            if (j_ >= 0 && str_.charAt(j_) != SUB_TYPE_CHAR && str_.charAt(j_) != SUP_TYPE_CHAR) {
                str_.insert(j_ +1, SUP_TYPE);
            }
            str_.append(value_.substring(SUP_TYPE.length()));
        } else {
            str_.append(value_);
        }
    }

    public static boolean checkObject(String _param, Argument _arg, ExecutableCode _context) {
    	ErrorType err_ = safeObject(_param, _arg, _context);
    	LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.CAST) {
    		String cast_ = stds_.getAliasCast();
            _context.setException(new ErrorStruct(_context,cast_));
            return false;
    	}
        if (err_ == ErrorType.NPE) {
        	String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return false;
        }
        return true;
    }
    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ExecutableCode _context) {
        int len_ = _args.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            ExecInvokingOperation.setElement(_arr, ind_, chArg_.getStruct(), _context);
            if (_context.getContextEl().hasExceptionOrFailInit()) {
                return;
            }
        }
    }
    public static void setElements(CustList<Argument> _args, ArrayStruct _arr) {
        int len_ = _args.size();
        Struct[] arr_ = _arr.getInstance();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            arr_[i] = _args.get(i).getStruct();
        }
    }
    public static boolean okArgs(Identifiable _id, String _classNameFound,CustList<Argument> _firstArgs, int _possibleOffset,ExecutableCode _conf) {
        StringList params_ = new StringList();
        if (!_id.isStaticMethod()) {
            int i_ = 0;
            for (String c: _id.getParametersTypes()) {
                String c_ = c;
                c_ = Templates.quickFormat(_classNameFound, c_, _conf);
                if (i_ + 1 == _id.getParametersTypes().size() && _id.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        } else {
            int i_ = 0;
            for (String c: _id.getParametersTypes()) {
                String c_ = c;
                if (i_ + 1 == _id.getParametersTypes().size() && _id.isVararg()) {
                    c_ = PrimitiveTypeUtil.getPrettyArrayType(c_);
                }
                params_.add(c_);
                i_++;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            if (_possibleOffset > -1) {
                _conf.setOffset(_possibleOffset);
            }
            String param_ = params_.get(i_);
            if (!Templates.checkObject(param_, a, _conf)) {
                return false;
            }
            i_++;
        }
        if (_id.isVararg()) {
            Struct str_ = _firstArgs.last().getStruct();
            if (str_ instanceof ArrayStruct) {
                ArrayStruct arr_ = (ArrayStruct) str_;
                for (Struct s: arr_.getInstance()) {
                    ErrorType state_ = checkElement(arr_, s, _conf);
                    if (state_ != ErrorType.NOTHING) {
                        LgNames stds_ = _conf.getStandards();
                        if (state_ == ErrorType.NPE) {
                            String npe_ = stds_.getAliasNullPe();
                            _conf.setException(new ErrorStruct(_conf,npe_));
                        } else {
                            String arrType_ = arr_.getClassName();
                            String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
                            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
                            Struct conv_ = PrimitiveTypeUtil.convertObject(cl_, s, stds_);
                            String arg_ = stds_.getStructClassName(conv_, _conf.getContextEl());
                            String cast_ = stds_.getAliasStore();
                            StringBuilder mess_ = new StringBuilder();
                            mess_.append(arg_);
                            mess_.append("!=");
                            mess_.append(param_);
                            _conf.setException(new ErrorStruct(_conf,mess_.toString(),cast_));
                        }
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public static ErrorType safeObject(String _param, Argument _arg, Analyzable _context) {
        Struct str_ = _arg.getStruct();
        LgNames stds_ = _context.getStandards();
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_param);
        _arg.setStruct(PrimitiveTypeUtil.convertObject(cl_, str_, stds_));
        str_ = _arg.getStruct();
        if (str_ != NullStruct.NULL_VALUE) {
            String a_ = stds_.getStructClassName(str_, _context.getContextEl());
            String param_ = PrimitiveTypeUtil.toWrapper(_param, stds_);
            if (!Templates.isCorrectExecute(a_, param_, _context)) {
                return ErrorType.CAST;
            }
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(_param, str_, _context)) {
            return ErrorType.NPE;
        }
        return ErrorType.NOTHING;
    }
    public static void gearErrorWhenContain(Struct _array, Struct _index, Struct _value, ExecutableCode _context) {
        ErrorType err_ = Templates.getErrorWhenContain(_array, _index, _value, _context);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            Number nb_ = ((NumberStruct)_index).getInstance();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = nb_.intValue();
            String arrType_ = arr_.getClassName();
            String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertObject(cl_, _value, stds_);
            if (_context.getContextEl().isContainedSensibleFields(arr_)) {
                _context.getContextEl().failInitEnums();
                return;
            }
            inst_[index_] = conv_;
            return;
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getAliasBadIndex();
            Number nb_ = ((NumberStruct)_index).getInstance();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = nb_.intValue();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(inst_.length);
            }
            _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
            return;
        }
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getAliasCast();
            String type_ = _array.getClassName(_context);
            _context.setException(new ErrorStruct(_context,type_,cast_));
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        String arrType_ = arr_.getClassName();
        String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
        ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
        Struct conv_ = PrimitiveTypeUtil.convertObject(cl_, _value, stds_);
        String arg_ = stds_.getStructClassName(conv_, _context.getContextEl());
        String cast_ = stds_.getAliasStore();
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
    }
    public static ErrorType getErrorWhenContain(Struct _array, Struct _index, Struct _value, Analyzable _context) {
        if (_array == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_array instanceof ArrayStruct)) {
            return ErrorType.CAST;
        }
        if (_index == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_index instanceof NumberStruct)) {
            return ErrorType.CAST;
        }
        Number nb_ = ((NumberStruct)_index).getInstance();
        ArrayStruct arr_ = (ArrayStruct) _array;
        Struct[] inst_ = arr_.getInstance();
        int index_ = nb_.intValue();
        if (index_ < 0 || index_ >= inst_.length) {
            return ErrorType.BAD_INDEX;
        }
        String arrType_ = arr_.getClassName();
        String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (PrimitiveTypeUtil.primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertObject(cl_, _value, stds_);
            String arg_ = stds_.getStructClassName(conv_, _context.getContextEl());
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!Templates.isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static ErrorType checkElement(ArrayStruct _arr, Struct _value, Analyzable _context) {
        String arrType_ = _arr.getClassName();
        String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (PrimitiveTypeUtil.primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertObject(cl_, _value, stds_);
            String arg_ = stds_.getStructClassName(conv_, _context.getContextEl());
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!Templates.isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static Struct gearErrorWhenIndex(Struct _array, Struct _index, ExecutableCode _context) {
        ErrorType err_ = Templates.getErrorWhenIndex(_array, _index);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            Number nb_ = ((NumberStruct)_index).getInstance();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = nb_.intValue();
            return inst_[index_];
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return NullStruct.NULL_VALUE;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getAliasBadIndex();
            Number nb_ = ((NumberStruct)_index).getInstance();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = nb_.intValue();
            StringBuilder mess_ = new StringBuilder();
            if (index_ < 0) {
                mess_.append(index_);
                mess_.append("<0");
            } else {
                mess_.append(index_);
                mess_.append(">=");
                mess_.append(inst_.length);
            }
            _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
            return NullStruct.NULL_VALUE;
        }
        String cast_ = stds_.getAliasCast();
        String type_ = _array.getClassName(_context);
        _context.setException(new ErrorStruct(_context,type_,cast_));
        return NullStruct.NULL_VALUE;
    }
    public static ErrorType getErrorWhenIndex(Struct _array, Struct _index) {
        if (_array == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_array instanceof ArrayStruct)) {
            return ErrorType.CAST;
        }
        if (_index == NullStruct.NULL_VALUE) {
            return ErrorType.NPE;
        }
        if (!(_index instanceof NumberStruct)) {
            return ErrorType.CAST;
        }
        Number nb_ = ((NumberStruct)_index).getInstance();
        ArrayStruct arr_ = (ArrayStruct) _array;
        Struct[] inst_ = arr_.getInstance();
        int index_ = nb_.intValue();
        if (index_ < 0 || index_ >= inst_.length) {
            return ErrorType.BAD_INDEX;
        }
        return ErrorType.NOTHING;
    }
    public static boolean isReturnCorrect(String _p, String _a, StringMap<StringList> _mapping,Analyzable _context) {
        if (PrimitiveTypeUtil.isPrimitive(_p, _context)) {
            if (!PrimitiveTypeUtil.isPrimitive(_a, _context)) {
                return false;
            }
        }
        String void_ = _context.getStandards().getAliasVoid();
        if (StringList.quickEq(_p, void_)) {
            return StringList.quickEq(_a, void_);
        }
        if (StringList.quickEq(_a, void_)) {
            return false;
        }
        Mapping map_ = new Mapping();
        map_.setArg(_a);
        map_.setParam(_p);
        map_.setMapping(_mapping);
        return isCorrectOrNumbers(map_, _context);
    }
    public static boolean isCorrectOrNumbers(Mapping _m, Analyzable _context) {
        ClassArgumentMatching a_ = _m.getArg();
        ClassArgumentMatching p_ = _m.getParam();
        if (PrimitiveTypeUtil.isPrimitive(p_, _context)) {
            StringList bounds_ = new StringList();
            LgNames stds_ = _context.getStandards();
            if (PrimitiveTypeUtil.isPrimitive(a_, _context)) {
                bounds_.add(a_.getName());
            } else if (!a_.isArray()){
                String aliasObj_ = stds_.getAliasObject();
                for (String n: a_.getNames()) {
                    for (String u: _m.getAllUpperBounds(n, aliasObj_)) {
                        bounds_.add(u);
                    }
                }
            }
            StringMap<PrimitiveType> prims_ = stds_.getPrimitiveTypes();
            for (String b: bounds_) {
                if (PrimitiveTypeUtil.isPrimitive(b, _context)) {
                    String pName_ = p_.getName();
                    return prims_.getVal(b).getAllPrimSuperType(_context).containsStr(pName_);
                }
                if (PrimitiveTypeUtil.isWrapper(b, _context)) {
                    String aPrim_ = PrimitiveTypeUtil.toPrimitive(b, stds_);
                    String pName_ = p_.getName();
                    return prims_.getVal(aPrim_).getAllSuperType(_context).containsStr(pName_);
                }
            }
            return false;
        }
        if (PrimitiveTypeUtil.isPrimitive(a_, _context)) {
            StringMap<PrimitiveType> prims_ = _context.getStandards().getPrimitiveTypes();
            String aName_ = a_.getName();
            ClassArgumentMatching pPrim_  = PrimitiveTypeUtil.toPrimitive(p_, _context);
            String pName_ = pPrim_.getName();
            return prims_.getVal(aName_).getAllPrimSuperType(_context).containsStr(pName_);
        }
        if (_m.getArg().isVariable()) {
            return !PrimitiveTypeUtil.isPrimitive(_m.getParam(), _context);
        }
        return isCorrect(_m, _context);
    }

    static boolean isCorrect(Mapping _m, Analyzable _context) {
        ClassArgumentMatching arg_ = _m.getArg();
        ClassArgumentMatching param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        for (String p: param_.getNames()) {
            boolean ok_ = false;
            StringList names_ = arg_.getNames();
            for (String a: names_) {
                CustList<Matching> matchs_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setArg(a);
                match_.setParam(p);
                matchs_.add(match_);
                boolean okTree_ = true;
                while (true) {
                    CustList<Matching> new_ = new CustList<Matching>();
                    for (Matching m: matchs_) {
                        String a_ = m.getArg();
                        String p_ = m.getParam();
                        MappingPairs m_ = getSimpleMapping(a_,p_,generalMapping_, _context);
                        if (m_ == null) {
                            okTree_ = false;
                            break;
                        }
                        boolean locOk_ = true;
                        for (Matching n: m_.getPairsArgParam()) {
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                if (!StringList.quickEq(n.getParam(), n.getArg())) {
                                    locOk_ = false;
                                    break;
                                }
                                continue;
                            }
                            if (StringList.quickEq(n.getParam(), n.getArg())) {
                                continue;
                            }
                            Matching n_ = new Matching();
                            if (n.getMatchEq() == MatchingEnum.SUB) {
                                n_.setArg(n.getArg());
                                n_.setParam(n.getParam());
                            } else {
                                n_.setArg(n.getParam());
                                n_.setParam(n.getArg());
                            }
                            new_.add(n_);
                        }
                        if (!locOk_) {
                            okTree_ = false;
                            break;
                        }
                    }
                    if (new_.isEmpty()) {
                        break;
                    }
                    matchs_ = new_;
                    if (!okTree_) {
                        break;
                    }
                }
                if (!okTree_) {
                    continue;
                }
                ok_ = true;
                break;
            }
            if (!ok_) {
                return false;
            }
        }
        return true;
    }
    public static boolean isCorrectExecute(String _a, String _p, Analyzable _context) {
        CustList<Matching> matchs_ = new CustList<Matching>();
        Matching match_ = new Matching();
        match_.setArg(_a);
        match_.setParam(_p);
        matchs_.add(match_);
        boolean okTree_ = true;
        while (true) {
            CustList<Matching> new_ = new CustList<Matching>();
            for (Matching m: matchs_) {
                String a_ = m.getArg();
                String p_ = m.getParam();
                MappingPairs m_ = getExecutingCorrect(a_,p_, _context);
                if (m_ == null) {
                    okTree_ = false;
                    break;
                }
                boolean locOk_ = true;
                for (Matching n: m_.getPairsArgParam()) {
                    if (n.getMatchEq() == MatchingEnum.EQ) {
                        if (!StringList.quickEq(n.getParam(), n.getArg())) {
                            locOk_ = false;
                            break;
                        }
                        continue;
                    }
                    if (StringList.quickEq(n.getParam(), n.getArg())) {
                        continue;
                    }
                    Matching n_ = new Matching();
                    if (n.getMatchEq() == MatchingEnum.SUB) {
                        n_.setArg(n.getArg());
                        n_.setParam(n.getParam());
                    } else {
                        n_.setArg(n.getParam());
                        n_.setParam(n.getArg());
                    }
                    new_.add(n_);
                }
                if (!locOk_) {
                    okTree_ = false;
                    break;
                }
            }
            if (new_.isEmpty()) {
                break;
            }
            matchs_ = new_;
            if (!okTree_) {
                break;
            }
        }
        return okTree_;
    }
    private static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, Analyzable _context) {
        StringList typesArg_ = getAllTypes(_arg);
        StringList typesParam_ = getAllTypes(_param);
        String baseArg_ = typesArg_.first();
        String baseParam_ = typesParam_.first();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            if (!baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
                return null;
            }
            if (dArg_.getDim() != dParam_.getDim()) {
                return null;
            }
            if (map_.inheritArgParam(baseArrayParam_.substring(1), baseArrayArg_.substring(1))) {
                return new MappingPairs();
            }
            return null;
        }
        StringList bounds_ = new StringList();
        String objType_ = _context.getStandards().getAliasObject();
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            for (String a: map_.getAllUpperBounds(baseArrayArg_, objType_)) {
                bounds_.add(PrimitiveTypeUtil.getPrettyArrayType(a, dArg_.getDim()));
            }
        } else {
            bounds_.add(baseArg_);
        }
        if (typesParam_.size() == 1) {
            boolean inh_ = false;
            for (String a: bounds_) {
                String base_ = getIdFromAllTypes(a);
                if (PrimitiveTypeUtil.canBeUseAsArgument(baseParam_, base_, _context)) {
                    inh_ = true;
                    break;
                }
            }
            if (!inh_) {
                return null;
            }
            return new MappingPairs();
        }
        if (PrimitiveTypeUtil.isPrimitive(baseArrayArg_, _context)) {
            return null;
        }
        for (String a: bounds_) {
            DimComp dLoc_ = PrimitiveTypeUtil.getQuickComponentBaseType(a);
            int dim_ = dLoc_.getDim();
            if (dim_ != dParam_.getDim()) {
                return null;
            }
        }
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
        if (StringList.quickEq(baseArg_, baseParam_)) {
            int len_ = typesParam_.size();
            if (typesArg_.size() != len_) {
                return null;
            }
            if (StringList.quickEq(baseArg_, fct_)) {
                return newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return newMappingPairs(_arg, typesParam_);
        }
        if (StringList.quickEq(baseArg_, fct_)) {
            return null;
        }
        if (StringList.quickEq(baseParam_, fct_)) {
            return null;
        }
        String generic_ = null;
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            String cmp_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param).getComponent();
            for (String c: map_.getAllUpperBounds(baseArrayArg_.substring(PREFIX_VAR_TYPE.length()), objType_)) {
                if (StringList.quickEq(c, cmp_)) {
                    generic_ = c;
                    break;
                }
                String idArg_ = Templates.getIdFromAllTypes(c);
                String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
                StringList curClasses_ = new StringList(geneSubType_);
                generic_ = requestGenericSuperType(c, curClasses_, _param, _context);
                if (generic_ != null) {
                    break;
                }
            }
        } else {
            String idArg_ = Templates.getIdFromAllTypes(baseArrayArg_);
            String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
            StringList curClasses_ = new StringList(geneSubType_);
            generic_ = requestGenericSuperType(baseArrayArg_, curClasses_, _param, _context);
        }
        if (generic_ == null) {
            return null;
        }
        return newMappingPairs(generic_, typesParam_);
    }
    private static MappingPairs getExecutingCorrect(String _arg, String _param, Analyzable _context) {
        StringList typesArg_ = getAllTypes(_arg);
        StringList typesParam_ = getAllTypes(_param);
        String baseArg_ = typesArg_.first();
        String baseParam_ = typesParam_.first();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        if (typesParam_.size() == 1) {
            String base_ = getIdFromAllTypes(baseArg_);
            if (PrimitiveTypeUtil.canBeUseAsArgument(baseParam_, base_, _context)) {
                return new MappingPairs();
            }
            return null;
        }
        if (PrimitiveTypeUtil.isPrimitive(baseArrayArg_, _context)) {
            return null;
        }
        DimComp dLoc_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseArg_);
        int dim_ = dLoc_.getDim();
        if (dim_ != dParam_.getDim()) {
            return null;
        }
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
        if (StringList.quickEq(baseArg_, baseParam_)) {
            int len_ = typesParam_.size();
            if (typesArg_.size() != len_) {
                return null;
            }
            if (StringList.quickEq(baseArg_, fct_)) {
                return newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return newMappingPairs(_arg, typesParam_);
        }
        if (StringList.quickEq(baseArg_, fct_)) {
            return null;
        }
        if (StringList.quickEq(baseParam_, fct_)) {
            return null;
        }
        String generic_;
        String idArg_ = Templates.getIdFromAllTypes(baseArrayArg_);
        String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
        StringList curClasses_ = new StringList(geneSubType_);
        generic_ = requestGenericSuperType(baseArrayArg_, curClasses_, _param, _context);
        if (generic_ == null) {
            return null;
        }
        return newMappingPairs(generic_, typesParam_);
    }
    private static MappingPairs newMappingPairsFct(StringList _args, StringList _params, String _objectType) {
        EqList<Matching> pairsArgParam_ = new EqList<Matching>();
        int len_ = _params.size();
        int argCall_ = len_ - 1;
        for (int i = CustList.SECOND_INDEX; i < argCall_; i++) {
            String arg_ = _args.get(i);
            String param_ = _params.get(i);
            if (StringList.quickEq(arg_, SUB_TYPE)) {
                if (StringList.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (StringList.quickEq(param_, SUB_TYPE)) {
                continue;
            }
            Matching match_ = new Matching();
            match_.setArg(arg_);
            match_.setParam(param_);
            match_.setMatchEq(MatchingEnum.SUP);
            pairsArgParam_.add(match_);
        }
        String a_ = _args.last();
        String p_ = _params.last();
        boolean add_ = true;
        if (StringList.quickEq(a_, SUB_TYPE)) {
            if (!StringList.quickEq(p_, SUB_TYPE)) {
                return null;
            }
            add_ = false;
        }
        if (StringList.quickEq(p_, SUB_TYPE)) {
            add_ = false;
        }
        if (!StringList.quickEq(p_, _objectType) && add_) {
            Matching match_ = new Matching();
            match_.setArg(a_);
            match_.setParam(p_);
            match_.setMatchEq(MatchingEnum.SUB);
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }
    private static MappingPairs newMappingPairs(String _generic, StringList _params) {
        int len_;
        StringList foundSuperClass_ = getAllTypes(_generic);
        EqList<Matching> pairsArgParam_ = new EqList<Matching>();
        len_ = foundSuperClass_.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Matching match_ = new Matching();
            String arg_ = foundSuperClass_.get(i);
            String param_ = _params.get(i);
            if (StringList.quickEq(arg_, SUB_TYPE)) {
                if (StringList.quickEq(param_, SUB_TYPE)) {
                    continue;
                }
                return null;
            }
            if (StringList.quickEq(param_, SUB_TYPE)) {
                continue;
            }
            if (arg_.startsWith(SUP_TYPE)) {
                if (param_.startsWith(SUB_TYPE)) {
                    return null;
                }
            }
            if (arg_.startsWith(SUB_TYPE)) {
                if (param_.startsWith(SUP_TYPE)) {
                    return null;
                }
            }
            boolean eqParam_ = !param_.startsWith(SUP_TYPE) && !param_.startsWith(SUB_TYPE);
            boolean eqArg_ = !arg_.startsWith(SUP_TYPE) && !arg_.startsWith(SUB_TYPE);
            if (eqParam_) {
                if (arg_.startsWith(SUB_TYPE)) {
                    return null;
                }
                if (arg_.startsWith(SUP_TYPE)) {
                    return null;
                }
                match_.setArg(arg_);
                match_.setParam(param_);
                pairsArgParam_.add(match_);
                continue;
            }
            if (eqArg_) {
                if (param_.startsWith(SUP_TYPE)) {
                    match_.setArg(arg_);
                    match_.setParam(param_.substring(SUP_TYPE.length()));
                    match_.setMatchEq(MatchingEnum.SUP);
                    pairsArgParam_.add(match_);
                    continue;
                }
                match_.setArg(arg_);
                match_.setParam(param_.substring(SUB_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUB);
                pairsArgParam_.add(match_);
                continue;
            }
            if (arg_.startsWith(SUP_TYPE)) {
                match_.setArg(arg_.substring(SUP_TYPE.length()));
                match_.setParam(param_.substring(SUP_TYPE.length()));
                match_.setMatchEq(MatchingEnum.SUP);
                pairsArgParam_.add(match_);
                continue;
            }
            match_.setArg(arg_.substring(SUB_TYPE.length()));
            match_.setParam(param_.substring(SUB_TYPE.length()));
            match_.setMatchEq(MatchingEnum.SUB);
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    private static String requestGenericSuperType(String _baseArg, StringList _subTypes, String _erasedSuperType, Analyzable _context) {
        String generic_ = null;
        String idSuperType_ = getIdFromAllTypes(_erasedSuperType);
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        StringList curClasses_ = new StringList(_subTypes);
        StringList visitedClasses_ = new StringList(_subTypes);
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c: curClasses_) {
                String baseClass_ = getIdFromAllTypes(c);
                baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                String superClass_ = getGenericSuperClassName(baseClass_, _context);
                if (superClass_ != null) {
                    String id_ = getIdFromAllTypes(superClass_);
                    String geneSuperClass_ = superClass_;
                    geneSuperClass_ = quickFormat(c, geneSuperClass_, _context);
                    if (StringList.quickEq(id_, classParam_)) {
                        generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperClass_, dim_);
                        break;
                    }
                    addTypeIdNotYet(visitedClasses_, nextClasses_, geneSuperClass_);
                }
                for (String s: getSuperInterfaceNames(baseClass_, _context)) {
                    String id_ = getIdFromAllTypes(s);
                    String geneSuperInterface_;
                    geneSuperInterface_ = quickFormat(c, s, _context);
                    if (StringList.quickEq(id_, classParam_)) {
                        generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                        break;
                    }
                    addTypeIdNotYet(visitedClasses_, nextClasses_, geneSuperInterface_);
                }
                if (generic_ != null) {
                    break;
                }
            }
            if (generic_ != null) {
                break;
            }
            if (nextClasses_.isEmpty()) {
                break;
            }
            curClasses_ = nextClasses_;
        }
        if (generic_ == null) {
            return null;
        }
        generic_ = format(_baseArg, generic_, _context);
        return generic_;
    }

    private static void addTypeIdNotYet(StringList visitedClasses_, StringList nextClasses_, String geneSuperInterface_) {
        if (!visitedClasses_.containsStr(geneSuperInterface_)) {
            nextClasses_.add(geneSuperInterface_);
            visitedClasses_.add(geneSuperInterface_);
        }
    }

    private static String getGenericSuperClassName(String _genericClassName, Analyzable _context) {
        String baseClass_ = getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(baseClass_);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getImportedDirectGenericSuperClass();
        }
        if (r_ != null) {
            return null;
        }
        LgNames stds_ = _context.getStandards();
        if (StringList.quickEq(baseClass_, stds_.getAliasObject())) {
            return null;
        }
        StandardType type_ = _context.getStandards().getStandards().getVal(baseClass_);
        if (type_ instanceof StandardClass) {
            return ((StandardClass)type_).getSuperClass();
        }
        return null;
    }

    private static StringList getSuperInterfaceNames(String _className, Analyzable _context) {
        GeneType r_ = _context.getClassBody(_className);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getImportedDirectGenericSuperInterfaces();
        }
        if (r_ instanceof InterfaceBlock) {
            return ((InterfaceBlock)r_).getImportedDirectSuperInterfaces();
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getDirectInterfaces();
        }
        return ((StandardInterface)r_).getDirectSuperClasses();
    }

    public static boolean correctNbParameters(String _genericClass, Analyzable _context) {
        StringBuilder id_ = new StringBuilder();
        //From analyze
        StringList inners_ = getAllInnerTypes(_genericClass);
        String fct_ = _context.getStandards().getAliasFct();
        int len_ = inners_.size();
        for (int i = 0; i < len_; i++) {
            String i_ = inners_.get(i);
            StringList params_ = getAllTypes(i_);
            String base_;
            if (id_.length() > 0) {
                base_ = StringList.concat(id_.toString(),"..",params_.first());
                id_.delete(0, id_.length());
            } else {
                base_ = params_.first();
            }
            int nbParams_ = params_.size() - 1;
            String baseArr_ = PrimitiveTypeUtil.getQuickComponentBaseType(base_).getComponent();
            GeneType current_ = _context.getClassBody(baseArr_);
            if (current_.isStaticType()) {
                if (i + 1 < len_) {
                    String idNext_ = getIdFromAllTypes(inners_.get(i+1));
                    String baseArrNext_ = PrimitiveTypeUtil.getQuickComponentBaseType(idNext_).getComponent();
                    String n_ = StringList.concat(baseArr_,"..",baseArrNext_);
                    GeneType next_ = _context.getClassBody(n_);
                    if (next_.isStaticType()) {
                        if (nbParams_ != 0) {
                            return false;
                        }
                        id_.append(baseArr_);
                        continue;
                    }
                }
            }
            if (StringList.quickEq(baseArr_, fct_)) {
                id_.append(baseArr_);
                continue;
            }
            boolean ex_ = current_.getParamTypes().size() == nbParams_;
            if (!ex_) {
                return false;
            }
            id_.append(baseArr_);
        }
        return true;
    }
}
