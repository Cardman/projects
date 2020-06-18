package code.expressionlanguage.inherits;

import code.expressionlanguage.*;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.common.GeneCustMethod;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.blocks.ExecBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecPartTypeUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.exec.opers.ExecInvokingOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.Identifiable;
import code.expressionlanguage.opers.util.MethodId;
import code.expressionlanguage.stds.*;
import code.expressionlanguage.structs.*;
import code.util.*;

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
    /** Returns the id of a type<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "Pair&lt;int,long&gt;" => ["Pair"]<br/>
     Sample 3: "Solo&lt;Pair&lt;int,long&gt;&gt;" => ["Solo"]<br/>
     */
    public static String getIdFromAllTypes(String _type) {
        return getAllTypes(_type).first();
    }

    /** Splits by comma the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "int,long" => ["int","long"]<br/>
     Sample 3: "Pair&lt;int,long&gt;" => ["Pair&lt;int,long&gt;"]<br/>
     Sample 4: "Solo&lt;int&gt;,&lt;,List&lt;long&gt;" => ["Solo&lt;int&gt;","&lt;","List&lt;long&gt;"]<br/>
      */
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
    /** Splits by single dot the input string into parts regarding packages<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass{}</pre>
     <pre>public class my.pkg.MySecondClass{</pre>
     <pre>     public class Inner{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass{</pre>
     <pre>     public class Inner{</pre>
     <pre>         public class SecInner{}</pre>
     <pre>     }</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "my.pkg.MyClass" => ["my.pkg.MyClass"]<br/>
     Sample 3: "my.pkg.MySecondClass.Inner" => ["my.pkg.MySecondClass","Inner"]<br/>
     Sample 4: "my.pkg.MyThirdClass.Inner.SecInner" => ["my.pkg.MySecondClass","Inner","SecInner"]<br/>
     Sample 5: "List&lt;my.pkg.MyThirdClass.Inner.SecInner&gt;" => ["List&lt;my.pkg.MyThirdClass.Inner.SecInner&gt;"]<br/>
     */
    static StringList getAllInnerTypesSingleDotted(String _type, ContextEl _an) {
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
                        if (StringList.quickEq(p, StringExpUtil.removeDottedSpaces(foundId_))) {
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
    /** Splits by single dot the input string into parts regarding packages<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass{}</pre>
     <pre>public class my.pkg.MySecondClass{</pre>
     <pre>     public class Inner{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass{</pre>
     <pre>     public class Inner{</pre>
     <pre>         public class SecInner{}</pre>
     <pre>     }</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "my.pkg.MyClass" => ["my.pkg.MyClass"]<br/>
     Sample 3: "my.pkg.MySecondClass.Inner" => ["my.pkg.MySecondClass","Inner"]<br/>
     Sample 4: "my.pkg.MyThirdClass.Inner.SecInner" => ["my.pkg.MySecondClass","Inner","SecInner"]<br/>
     */
    public static StringList getAllInnerTypes(String _type, ContextEl _an) {
        return getAllInnerTypes(_type,_an.getClasses().getPackagesFound());
    }
    public static StringList getAllInnerTypes(String _type, StringList _pkg) {
        StringList types_ = new StringList();
        int len_ = _type.length();
        StringBuilder builtId_ = new StringBuilder();
        int i_ = 0;
        while (i_ < len_) {
            char cur_ = _type.charAt(i_);
            if (cur_ == SEP_CLASS_CHAR) {
                //if builtId_.toString() is a type => inner_ is true
                String foundId_ = builtId_.toString();
                String tr_ = StringExpUtil.removeDottedSpaces(foundId_);
                if (StringExpUtil.nextCharIs(_type,i_+1,len_,SEP_CLASS_CHAR)||!StringList.contains(_pkg,tr_)) {
                    break;
                }
            }
            builtId_.append(cur_);
            i_++;
        }
        while (i_ < len_) {
            char cur_ = _type.charAt(i_);
            if (cur_ == SEP_CLASS_CHAR) {
                //if builtId_.toString() is a type => inner_ is true
                types_.add(builtId_.toString());
                builtId_.delete(0, builtId_.length());
                if (StringExpUtil.nextCharIs(_type,i_+1,len_,SEP_CLASS_CHAR)) {
                    i_++;
                    types_.add("..");
                } else {
                    types_.add(".");
                }
            } else {
                builtId_.append(cur_);
            }
            i_++;
        }
        types_.add(builtId_.toString());
        return types_;
    }
    public static StringList getAllPartInnerTypes(String _type) {
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
            if (curChar_ == '-') {
                types_.add(out_.toString());
                types_.add("-");
                out_.delete(0, out_.length());
                i_++;
                continue;
            }
            if (_type.startsWith(INNER_TYPE,i_)) {
                types_.add(out_.toString());
                types_.add(INNER_TYPE);
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
    /** Splits by double dots the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "int..long" => ["int","long"]<br/>
     Sample 3: "Pair&lt;int..long&gt;" => ["Pair&lt;int..long&gt;"]<br/>
     */
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
            if (curChar_ == '-') {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
                i_++;
                continue;
            }
            if (_type.startsWith(INNER_TYPE,i_)) {
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
    /** Splits by comma the input string into parts<br/>
     Sample 1: "int" => ["int"]<br/>
     Sample 2: "Pair&lt;int,long&gt;" => ["Pair","int","long"]<br/>
     Sample 3: "Solo&lt;Pair&lt;int,long&gt;&gt;" => ["Solo","Pair&lt;int,long&gt;"]<br/>
     */
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
                if (count_ == 0) {
                    //curChar_ == Templates.GT
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
    public static String correctClassPartsDynamic(String _className, ContextEl _context, boolean _wildCard) {
        String className_ = ExecPartTypeUtil.processExec(_className, _context);
        if (className_.isEmpty()) {
            return "";
        }
        if (_wildCard) {
            CustList<String> allArgTypes_ = getAllTypes(className_).mid(1);
            for (String m: allArgTypes_) {
                if (m.startsWith(SUB_TYPE)) {
                    return "";
                }
                if (m.startsWith(SUP_TYPE)) {
                    return "";
                }
            }
        }
        if (isCorrectTemplateAllExec(className_, _context)) {
            return className_;
        }
        return "";
    }
    /** Return if possible the inferred form<br/>
     Sample 1: "int" => null<br/>
     Sample 2: "Pair&gt;" => null<br/>
     Sample 3: "Pair&lt;int&gt;" => null<br/>
     Sample 4: "Pa,ir&lt;&gt;" => null<br/>
     Sample 5: "Pair&lt;&gt;" => Pair<br/>
     Sample 6: "Pa..ir&lt;&gt;" => Pa..ir<br/>
     */
    public static String getInferForm(String _type) {
        String tr_ = _type.trim();
        if (!tr_.endsWith(TEMPLATE_END)) {
            return null;
        }
        tr_ = tr_.substring(0, tr_.length() - 1).trim();
        if (!tr_.endsWith(TEMPLATE_BEGIN)) {
            return null;
        }
        tr_ = tr_.substring(0, tr_.length() - 1);
        for (String p: StringList.splitChars(tr_,'.')) {
            if (p.isEmpty()) {
                continue;
            }
            if (StringList.isDollarWord(p.trim())) {
                continue;
            }
            return null;
        }
        return StringExpUtil.removeDottedSpaces(tr_);
    }
    public static String tryInfer(String _erased, StringMap<String> _vars, String _declaring, ContextEl _context) {
        GeneType g_ = _context.getClassBody(_erased);
        String idParam_ = getIdFromAllTypes(_declaring);
        String gene_ = g_.getGenericString();
        String type_ = "";
        if (!StringList.quickEq(idParam_,_erased)) {
            for (String s: g_.getAllGenericSuperTypes()) {
                String idSuper_ = getIdFromAllTypes(s);
                if (StringList.quickEq(idSuper_,idParam_)) {
                    type_ = s;
                }
            }
        } else {
            if (StringList.quickEq(_erased,_context.getStandards().getAliasFct())) {
                return _declaring;
            }
            type_ = gene_;
        }
        if (type_.isEmpty()) {
            return null;
        }
        CustList<InferenceConstraints> ics_ = new CustList<InferenceConstraints>();
        CustList<InferenceConstraints> found_ = new CustList<InferenceConstraints>();
        StringList argTypes_ = new StringList();
        for (String p: getAllTypes(type_).mid(1)) {
            argTypes_.add(p);
        }
        StringList paramTypes_ = new StringList();
        for (String p: getAllTypes(_declaring).mid(1)) {
            paramTypes_.add(p);
        }
        int len_ = argTypes_.size();
        for (int i = 0; i < len_; i++) {
            InferenceConstraints i_ = new InferenceConstraints();
            i_.setArg(argTypes_.get(i));
            i_.setParam(paramTypes_.get(i));
            ics_.add(i_);
        }
        while (true) {
            CustList<InferenceConstraints> next_ = new CustList<InferenceConstraints>();
            for (InferenceConstraints i: ics_) {
                String argLoc_ = i.getArg();
                String paramLoc_ = i.getParam();
                if (argLoc_.startsWith(PREFIX_VAR_TYPE)) {
                    found_.add(i);
                    continue;
                }
                if (argLoc_.startsWith(ARR_BEG_STRING)) {
                    if (paramLoc_.startsWith(ARR_BEG_STRING)) {
                        InferenceConstraints n_ = new InferenceConstraints();
                        n_.setArg(argLoc_.substring(ARR_BEG_STRING.length()));
                        n_.setParam(paramLoc_.substring(ARR_BEG_STRING.length()));
                        next_.add(n_);
                    }
                    continue;
                }
                if (StringList.quickEq(argLoc_,SUB_TYPE)) {
                    continue;
                }
                if (argLoc_.startsWith(SUB_TYPE)) {
                    if (paramLoc_.startsWith(SUB_TYPE)) {
                        InferenceConstraints n_ = new InferenceConstraints();
                        n_.setArg(argLoc_.substring(SUB_TYPE.length()));
                        n_.setParam(paramLoc_.substring(SUB_TYPE.length()));
                        next_.add(n_);
                    }
                    continue;
                }
                if (argLoc_.startsWith(SUP_TYPE)) {
                    if (paramLoc_.startsWith(SUP_TYPE)) {
                        InferenceConstraints n_ = new InferenceConstraints();
                        n_.setArg(argLoc_.substring(SUP_TYPE.length()));
                        n_.setParam(paramLoc_.substring(SUP_TYPE.length()));
                        next_.add(n_);
                    }
                    continue;
                }
                StringList nArgTypes_ = getAllTypes(argLoc_);
                StringList nParamTypes_ = getAllTypes(paramLoc_);
                if (!StringList.quickEq(nArgTypes_.first(), nParamTypes_.first())) {
                    continue;
                }
                int lenLoc_ = nArgTypes_.size();
                for (int j = 1; j < lenLoc_; j++) {
                    InferenceConstraints i_ = new InferenceConstraints();
                    i_.setArg(nArgTypes_.get(j));
                    i_.setParam(nParamTypes_.get(j));
                    next_.add(i_);
                }
            }
            if (next_.isEmpty()) {
                break;
            }
            ics_ = next_;
        }
        StringMap<StringList> multi_ = new StringMap<StringList>();
        for (String p: getAllTypes(gene_).mid(1)) {
            multi_.put(p, new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            multi_.put(StringList.concat(PREFIX_VAR_TYPE,e.getKey()), new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            multi_.getVal(StringList.concat(PREFIX_VAR_TYPE,e.getKey())).add(e.getValue());
        }
        for (InferenceConstraints i: found_) {
            String argLoc_ = i.getArg();
            String paramLoc_ = i.getParam();
            multi_.getVal(argLoc_).add(paramLoc_);
        }
        StringMap<String> vars_ = new StringMap<String>();
        for (EntryCust<String,StringList> e: multi_.entryList()) {
            if (!e.getValue().onlyOneElt()) {
                return null;
            }
            vars_.put(e.getKey().substring(1), e.getValue().first());
        }
        return getQuickFormattedType(gene_,vars_);
    }
    /**parameriterized sub type (possibly array) - super type<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;A,B&gt;:MySecondClass&lt;A,B&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;C,D&gt;{}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "my.pkg.MySecondClass" => "my.pkg.MySecondClass&lt;long,int&gt;"<br/>
     Sample 2: "my.pkg.MyClass" - "my.pkg.MySecondClass" => null<br/>
     Sample 3: "my.pkg.MySecondClass&lt;long,int&gt;" - "my.pkg.MyClass" => null<br/>
     Sample 4: "my.pkg.MyClass&lt;long,int&gt;" - "my.pkg.MySecondClass[]" => "my.pkg.MySecondClass&lt;long,int&gt;[]"<br/>
     */
    public static String getFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        if (!correctNbParameters(_subType,_context)) {
            return "";
        }
        String idArg_ = getIdFromAllTypes(_subType);
        String idSuperType_ = getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        DimComp dBaseArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        if (StringList.quickEq(classParam_, _context.getStandards().getAliasObject())) {
            if (dBaseArg_.getDim() < dim_) {
                return "";
            }
            return _superType;
        }
        if (dBaseArg_.getDim() != dim_) {
            return "";
        }
        if (PrimitiveTypeUtil.isPrimitive(baseArr_,_context)) {
            PrimitiveType pr_ = _context.getStandards().getPrimitiveTypes().getVal(baseArr_);
            if (StringList.contains(pr_.getAllSuperType(_context), classParam_)) {
                return _superType;
            }
            return "";
        }
        if (StringList.quickEq(_subType, _context.getStandards().getAliasVoid())) {
            return "";
        }
        if (StringList.quickEq(_superType, _context.getStandards().getAliasVoid())) {
            return "";
        }
        String geneSubType_ = _context.getClassBody(baseArr_).getGenericString();
        String generic_ = getSuperGeneric(_context, dim_, classParam_, geneSubType_);
        return format(_subType, generic_, _context);
    }
    public static String getOverridingFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idArg_ = getIdFromAllTypes(_subType);
        String idSuperType_ = getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
        String generic_ = getSuperGeneric(_context, 0, idSuperType_, geneSubType_);
        return quickFormat(_subType, generic_, _context);
    }

    public static String getQuickFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(idSuperType_);
        String classParam_ = dBaseParam_.getComponent();
        int dim_ = dBaseParam_.getDim();
        String idArg_ = getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(idArg_);
        if (StringList.quickEq(classParam_, _context.getStandards().getAliasObject())) {
            if (dBaseArg_.getDim() < dim_) {
                return "";
            }
            return _superType;
        }
        if (dBaseArg_.getDim() != dim_) {
            return "";
        }
        return getFullObject(_subType, _superType,_context);
    }

    public static String getFullObject(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        String idArg_ = getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        if (!correctNbParameters(_subType,_context)) {
            return "";
        }
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        String geneSubType_ = _context.getClassBody(baseArr_).getGenericString();
        String generic_ = getSuperGeneric(_context, dim_, classParam_, geneSubType_);
        return quickFormat(_subType, generic_, _context);
    }

    private static String getSuperGeneric(ContextEl _context, int _dim, String _classParam, String _geneSubType) {
        String generic_ = "";
        StringList curClasses_ = new StringList(_geneSubType);
        StringList visitedClasses_ = new StringList(_geneSubType);
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c : curClasses_) {
                String baseClass_ = getIdFromAllTypes(c);
                baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                for (String s : getSuperInterfaceNames(baseClass_, _context)) {
                    String id_ = getIdFromAllTypes(s);
                    String geneSuperInterface_;
                    geneSuperInterface_ = quickFormat(c, s, _context);
                    if (StringList.quickEq(id_, _classParam)) {
                        generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, _dim);
                        break;
                    }
                    addTypeIdNotYet(visitedClasses_, nextClasses_, geneSuperInterface_);
                }
                if (!generic_.isEmpty()) {
                    break;
                }
            }
            if (!generic_.isEmpty()) {
                break;
            }
            if (nextClasses_.isEmpty()) {
                break;
            }
            curClasses_ = nextClasses_;
        }
        return generic_;
    }

    public static String getSuperGeneric(String _arg, String _classParam, ContextEl _context) {
        if (!correctNbParameters(_arg,_context)) {
            return "";
        }
        String generic_ = "";
        String idArg_ = getIdFromAllTypes(_arg);
        String idSuperType_ = getIdFromAllTypes(_classParam);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _arg;
        }
        String geneSubType_ = _context.getClassBody(idArg_).getGenericString();
        StringList curClasses_ = new StringList(geneSubType_);
        StringList visitedClasses_ = new StringList(geneSubType_);
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c : curClasses_) {
                String baseClass_ = getIdFromAllTypes(c);
                for (String s : getSuperInterfaceNames(baseClass_, _context)) {
                    String id_ = getIdFromAllTypes(s);
                    String geneSuperInterface_;
                    geneSuperInterface_ = quickFormat(c, s, _context);
                    if (StringList.quickEq(id_, idSuperType_)) {
                        generic_ = geneSuperInterface_;
                        break;
                    }
                    addTypeIdNotYet(visitedClasses_, nextClasses_, geneSuperInterface_);
                }
                if (!generic_.isEmpty()) {
                    break;
                }
            }
            if (!generic_.isEmpty()) {
                break;
            }
            if (nextClasses_.isEmpty()) {
                break;
            }
            curClasses_ = nextClasses_;
        }
        return quickFormat(_arg, generic_, _context);
    }
    public static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, ContextEl _context) {
        return isCorrectTemplateAll(_className, _inherit, _context, true);
    }
    public static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, ContextEl _context, boolean _exact) {
        return AnaPartTypeUtil.processAnalyzeConstraints(_className,_inherit,_context,_exact);
    }
    public static boolean isCorrectTemplateAllExec(String _className, ContextEl _context) {
        return ExecPartTypeUtil.processAnalyzeConstraintsExec(_className,_context);
    }

    public static String wildCardFormatReturn(String _first, String _second, ContextEl _classes) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
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
        if (typeVar_.size() != types_.size() - 1){
            return objType_;
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return getWildCardFormattedTypeReturn(_second, varTypes_);
    }
    public static String wildCardFormatParam(String _first, String _second, ContextEl _classes) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
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
                        return "";
                    }
                    if (formatted_.startsWith(SUP_TYPE)) {
                        return PrimitiveTypeUtil.getPrettyArrayType(formatted_.substring(SUP_TYPE.length()),arr_);
                    }
                    return PrimitiveTypeUtil.getPrettyArrayType(formatted_,arr_);
                }
            }
            return "";
        }
        if (typeVar_.size() != types_.size() - 1){
            return "";
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return getWildCardFormattedTypeParam(objType_,_second, varTypes_);
    }
    public static String reflectFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getReflectFormattedType(_second, varTypes_);
    }
    /**Returns a formatted string (variables present in second type are defined in the scope of the first type id)<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "?#K" => null<br/>
     Sample 2: "my.pkg.MyClass&lt;?long,int&gt;" - "#K" => "long"<br/>
     Sample 3: "my.pkg.MyClass&lt;?long,int&gt;" - "?#K" => null<br/>
     */
    public static String format(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getFormattedType(_second, varTypes_);
    }
    /**Returns a formatted string (variables present in second type are defined in the scope of the first type id)<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" - "#K" => "long"<br/>
     Sample 2: "my.pkg.MyClass&lt;long,int&gt;" - "#V" => "int"<br/>
     Sample 3: "my.pkg.MySecondClass&lt;long&gt;..Inner&lt;int&gt;" - "#K" => "long"<br/>
     Sample 4: "my.pkg.MyThirdClass..Inner&lt;int&gt;" - "#V" => "int"<br/>
     */
    public static String quickFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return getQuickFormattedType(_second, varTypes_);
    }

    public static String getMadeVarTypes(String _className, StringList _classNames,ContextEl _context) {
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
            str_.append(StringList.join(parts_, Templates.TEMPLATE_SEP));
            str_.append(Templates.TEMPLATE_END);
            return str_.toString();
        }
        GeneType root_ = _context.getClassBody(type_);
        if (root_ == null) {
            return null;
        }
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
        if (!isCorrectTemplateAllExec(formatted_, _context)) {
            return null;
        }
        return formatted_;
    }
    /**Returns the map of variables of a paramethized type<br/>
      Let this code:<br/>
           <code><pre>public class my.pkg.MyClass&lt;K,V&gt;{}</pre>
           <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
           <pre>     public class Inner&lt;V&gt;{}</pre>
           <pre>}</pre></code><br/>
           <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
           <pre>     public static class Inner&lt;V&gt;{}</pre></code>
           <pre>}</pre></code><br/>
     Sample 1: "my.pkg.MyClass&lt;long,int&gt;" => ["K"-"long","V"-"int"]<br/>
     Sample 2: "my.pkg.MySecondClass&lt;long&gt;..Inner&lt;int&gt;" => ["K"-"long","V"-"int"]<br/>
     Sample 3: "my.pkg.MyThirdClass..Inner&lt;int&gt;" => ["V"-"int"]<br/>
     Sample 4: "my.pkg.MyClass&lt;long,int&gt;[]" => ["K"-"long","V"-"int"]<br/>
     */
    public static StringMap<String> getVarTypes(String _className, ContextEl _context) {
        StringList types_ = getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        StringMap<String> varTypes_ = new StringMap<String>();
        if (root_ == null) {
            return varTypes_;
        }
        int i_ = CustList.FIRST_INDEX;
        for (String t: root_.getParamTypesValues()) {
            i_++;
            if (!types_.isValidIndex(i_)) {
                return new StringMap<String>();
            }
            String arg_ = types_.get(i_);
            varTypes_.put(t, arg_);
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
        String value_ = _varTypes.getVal(sub_);
        if (value_ == null) {
            _str.append(sub_);
            return;
        }
        _str.append(value_);
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
                    return "";
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
                    return "";
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
                    return "";
                }
                if (value_.startsWith(SUB_TYPE)) {
                    String bound_= value_.substring(SUB_TYPE.length());
                    if (isSupChar(str_, j_)) {
                        str_.append(bound_);
                        str_.append(_type.charAt(i));
                        var_ = false;
                        continue;
                    }
                    return "";
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
                    return "";
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

    static int getMaxIndex(StringBuilder _str, int _max) {
        int j_ = _max;
        while (j_ >= 0) {
            if (_str.charAt(j_) != ARR_BEG) {
                break;
            }
            j_--;
        }
        return j_;
    }

    static boolean isNotChar(StringBuilder _str, int _j, char _subTypeChar) {
        return _j >= 0 && _str.charAt(_j) != _subTypeChar;
    }

    static boolean isSubOrSubChar(StringBuilder _str, int _j) {
        return isSubChar(_str,_j) || isSupChar(_str, _j);
    }

    static boolean isSubChar(StringBuilder _str, int _j) {
        return _j >= 0 && _str.charAt(_j) == SUB_TYPE_CHAR;
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

    static void replaceReflectedType(StringMap<String> _varTypes, StringBuilder _str, String _sub) {
        int j_ = getMaxIndex(_str, _str.length() - 1);
        String value_ = _varTypes.getVal(_sub);
        if (StringList.quickEq(value_,SUB_TYPE)) {
            _str.delete(j_+1,_str.length());
            _str.append(value_);
        } else if (value_.startsWith(SUB_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUB_TYPE);
            }
            _str.append(value_.substring(SUB_TYPE.length()));
        } else if (value_.startsWith(SUP_TYPE)) {
            if (isNotChar(_str,j_,SUB_TYPE_CHAR) && isNotChar(_str,j_,SUP_TYPE_CHAR)) {
                _str.insert(j_ +1, SUP_TYPE);
            }
            _str.append(value_.substring(SUP_TYPE.length()));
        } else {
            _str.append(value_);
        }
    }

    public static boolean checkObject(String _param, Argument _arg, ContextEl _context) {
        Struct str_ = _arg.getStruct();
        LgNames stds_ = _context.getStandards();
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_param);
        _arg.setStruct(PrimitiveTypeUtil.convertObject(cl_, str_, stds_));
        return checkQuick(_param, _arg, _context);
    }

    public static boolean checkQuick(String _param, Argument _arg, ContextEl _context) {
        Struct ex_ = checkObjectEx(_param,_arg,_context);
        if (ex_ != null) {
            _context.setException(ex_);
            return false;
    	}
        return true;
    }

    public static Struct checkObjectEx(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        ErrorType err_ = safeObject(_param, _arg, _context);
        if (err_ == ErrorType.CAST) {
            String cast_ = stds_.getAliasCastType();
            return new ErrorStruct(_context,cast_);
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            return new ErrorStruct(_context,npe_);
        }
        return null;
    }
    public static void setCheckedElements(CustList<Argument> _args, Struct _arr, ContextEl _context) {
        int len_ = _args.size();
        for (int i = CustList.FIRST_INDEX; i < len_; i++) {
            Argument chArg_ = _args.get(i);
            IntStruct ind_ = new IntStruct(i);
            ExecInvokingOperation.setElement(_arr, ind_, chArg_.getStruct(), _context);
            if (_context.callsOrException()) {
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
    public static Struct okArgsEx(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        StringList params_ = new StringList();
        boolean hasFormat_;
        if (_id instanceof MethodId) {
            hasFormat_ = ((MethodId)_id).canAccessParamTypes() || _format;
        } else {
            hasFormat_ = true;
        }
        if (hasFormat_ && !correctNbParameters(_classNameFound,_conf)) {
            LgNames stds_ = _conf.getStandards();
            String npe_ = stds_.getAliasIllegalArg();
            return new ErrorStruct(_conf,npe_);
        }
        if (hasFormat_) {
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
        if (_firstArgs.size() != params_.size()) {
            LgNames stds_ = _conf.getStandards();
            String cast_ = stds_.getAliasBadIndex();
            StringBuilder mess_ = new StringBuilder();
            mess_.append(_firstArgs.size());
            mess_.append(">=");
            mess_.append(params_.size());
            return new ErrorStruct(_conf,mess_.toString(),cast_);
        }
        int i_ = CustList.FIRST_INDEX;
        for (Argument a: _firstArgs) {
            String param_ = params_.get(i_);
            Struct ex_ = Templates.checkObjectEx(param_, a, _conf);
            if (ex_ != null) {
                return ex_;
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
                            return new ErrorStruct(_conf,npe_);
                        } else {
                            String arrType_ = arr_.getClassName();
                            String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
                            String arg_ = stds_.getStructClassName(s, _conf);
                            String cast_ = stds_.getAliasStore();
                            StringBuilder mess_ = new StringBuilder();
                            mess_.append(arg_);
                            mess_.append("!=");
                            mess_.append(param_);
                            return new ErrorStruct(_conf,mess_.toString(),cast_);
                        }
                    }
                }
            }
        }
        if (_id instanceof MethodId) {
            String name_ = _id.getName();
            if (StringList.quickEq("[]=", name_)) {
                String id_ = getIdFromAllTypes(_classNameFound);
                for (GeneCustMethod g: ExecBlock.getMethodExecBlocks(_conf.getClasses().getClassBody(id_))) {
                    if (!StringList.quickEq("[]",g.getId().getName())) {
                        continue;
                    }
                    if(!g.getId().eqPartial((MethodId) _id)) {
                        continue;
                    }
                    String type_ = g.getImportedReturnType();
                    type_ = Templates.quickFormat(_classNameFound, type_, _conf);
                    Struct ex_ = Templates.checkObjectEx(type_, _right, _conf);
                    if (ex_ != null) {
                        return ex_;
                    }
                }
            }
        }
        return null;
    }
    public static boolean okArgs(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        Struct ex_ = okArgsSet(_id, _format, _classNameFound, _firstArgs, _conf, _right);
        return ex_ == null;
    }
    public static Struct okArgsSet(Identifiable _id, boolean _format, String _classNameFound, CustList<Argument> _firstArgs, ContextEl _conf, Argument _right) {
        Struct ex_ = okArgsEx(_id, _format, _classNameFound, _firstArgs, _conf, _right);
        if (ex_ != null) {
            _conf.setException(ex_);
        }
        return ex_;
    }
    public static ErrorType safeObject(String _param, Argument _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        Struct str_ = _arg.getStruct();
        if (str_ != NullStruct.NULL_VALUE) {
            String a_ = stds_.getStructClassName(str_, _context);
            String param_ = PrimitiveTypeUtil.toWrapper(_param, stds_);
            if (!Templates.isCorrectExecute(a_, param_, _context)) {
                return ErrorType.CAST;
            }
        }
        if (_param.isEmpty()) {
            return ErrorType.CAST;
        }
        if (PrimitiveTypeUtil.primitiveTypeNullObject(_param, str_, _context)) {
            return ErrorType.NPE;
        }
        return ErrorType.NOTHING;
    }
    public static void gearErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
        ErrorType err_ = Templates.getErrorWhenContain(_array, _index, _value, _context);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            String arrType_ = arr_.getClassName();
            String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
            ClassArgumentMatching cl_ = new ClassArgumentMatching(param_);
            Struct conv_ = PrimitiveTypeUtil.convertStrictObject(cl_, _value, stds_);
            if (_context.getInitializingTypeInfos().isContainedSensibleFields(arr_)) {
                _context.getInitializingTypeInfos().failInitEnums();
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
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
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
            String cast_ = stds_.getAliasCastType();
            String type_ = _array.getClassName(_context);
            _context.setException(new ErrorStruct(_context,type_,cast_));
            return;
        }
        ArrayStruct arr_ = (ArrayStruct) _array;
        String arrType_ = arr_.getClassName();
        String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
        String arg_ = stds_.getStructClassName(_value, _context);
        String cast_ = stds_.getAliasStore();
        StringBuilder mess_ = new StringBuilder();
        mess_.append(arg_);
        mess_.append("!=");
        mess_.append(param_);
        _context.setException(new ErrorStruct(_context,mess_.toString(),cast_));
    }
    public static ErrorType getErrorWhenContain(Struct _array, Struct _index, Struct _value, ContextEl _context) {
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
        ArrayStruct arr_ = (ArrayStruct) _array;
        Struct[] inst_ = arr_.getInstance();
        int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
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
            Struct conv_ = PrimitiveTypeUtil.convertStrictObject(cl_, _value, stds_);
            String arg_ = stds_.getStructClassName(conv_, _context);
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!Templates.isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static ErrorType checkElement(ArrayStruct _arr, Struct _value, ContextEl _context) {
        String arrType_ = _arr.getClassName();
        String param_ = PrimitiveTypeUtil.getQuickComponentType(arrType_);
        LgNames stds_ = _context.getStandards();
        if (PrimitiveTypeUtil.primitiveTypeNullObject(param_, _value, stds_)) {
            return ErrorType.NPE;
        }
        if (_value != NullStruct.NULL_VALUE) {
            String arg_ = stds_.getStructClassName(_value, _context);
            param_ = PrimitiveTypeUtil.toWrapper(param_, stds_);
            if (!Templates.isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.STORE;
            }
        }
        return ErrorType.NOTHING;
    }
    public static Struct gearErrorWhenIndex(Struct _array, Struct _index, ContextEl _context) {
        ErrorType err_ = Templates.getErrorWhenIndex(_array, _index);
        LgNames stds_ = _context.getStandards();
        if (err_ == ErrorType.NOTHING) {
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
            return inst_[index_];
        }
        if (err_ == ErrorType.NPE) {
            String npe_ = stds_.getAliasNullPe();
            _context.setException(new ErrorStruct(_context,npe_));
            return NullStruct.NULL_VALUE;
        }
        if (err_ == ErrorType.BAD_INDEX) {
            String cast_ = stds_.getAliasBadIndex();
            ArrayStruct arr_ = (ArrayStruct) _array;
            Struct[] inst_ = arr_.getInstance();
            int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
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
        String cast_ = stds_.getAliasCastType();
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
        ArrayStruct arr_ = (ArrayStruct) _array;
        Struct[] inst_ = arr_.getInstance();
        int index_ = ClassArgumentMatching.convertToNumber(_index).intStruct();
        if (index_ < 0 || index_ >= inst_.length) {
            return ErrorType.BAD_INDEX;
        }
        return ErrorType.NOTHING;
    }
    public static boolean isReturnCorrect(String _p, String _a, StringMap<StringList> _mapping,ContextEl _context) {
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
    public static boolean isCorrectOrNumbers(Mapping _m, ContextEl _context) {
        ClassArgumentMatching a_ = _m.getArg();
        ClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !PrimitiveTypeUtil.isPrimitive(p_, _context);
        }
        if (PrimitiveTypeUtil.isPrimitive(p_, _context)) {
            LgNames stds_ = _context.getStandards();
            Mapping m_ = new Mapping();
            m_.setArg(PrimitiveTypeUtil.toPrimitive(a_, stds_));
            m_.setParam(p_);
            m_.setMapping(_m.getMapping());
            return isCorrect(m_, _context);
        }
        return isCorrect(_m, _context);
    }

    public static boolean isCorrect(Mapping _m, ContextEl _context) {
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
                        for (Matching n: m_.getPairsArgParam()) {
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                if (!StringList.quickEq(n.getParam(), n.getArg())) {
                                    okTree_ = false;
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
                        if (!okTree_) {
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
    public static boolean isCorrectExecute(String _a, String _p, ContextEl _context) {
        if (_p.isEmpty()) {
            return false;
        }
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
                for (Matching n: m_.getPairsArgParam()) {
                    if (n.getMatchEq() == MatchingEnum.EQ) {
                        if (!StringList.quickEq(n.getParam(), n.getArg())) {
                            okTree_ = false;
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
                if (!okTree_) {
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
    private static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, ContextEl _context) {
        StringList typesArg_ = getAllTypes(_arg);
        StringList typesParam_ = getAllTypes(_param);
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
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
        String idBaseArrayArg_ = getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = getIdFromAllTypes(baseArrayParam_);
        if (StringList.quickEq(idBaseArrayArg_, fct_)) {
            if (StringList.quickEq(idBaseArrayParam_, fct_)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringList.quickEq(baseArrayParam_, fct_)) {
                    return new MappingPairs();
                }
                int len_ = typesParam_.size();
                if (typesArg_.size() != len_) {
                    return null;
                }
                return newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return getMappingFctPairs(_context, dArg_, dParam_, baseArrayParam_);
        }
        if (StringList.quickEq(idBaseArrayParam_, fct_)) {
            return null;
        }
        String generic_ = getGeneric(_arg, _param, _context, map_);
        if (generic_.isEmpty()) {
            return null;
        }
        return newMappingPairs(generic_, typesParam_);
    }

    public static String getGeneric(String _arg, String _param, ContextEl _context, Mapping map_) {
        String objType_ = _context.getStandards().getAliasObject();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String baseArrayArg_ = dArg_.getComponent();
        String generic_ = "";
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            StringMap<StringList> mapping_ = map_.getMapping();
            for (String c: Mapping.getAllUpperBounds(mapping_,baseArrayArg_.substring(PREFIX_VAR_TYPE.length()), objType_)) {
                String arr_ = PrimitiveTypeUtil.getPrettyArrayType(c,dArg_.getDim());
                generic_ = getFullTypeByBases(arr_, _param, _context);
                if (!generic_.isEmpty()) {
                    break;
                }
            }
        } else {
            generic_ = getFullTypeByBases(_arg, _param, _context);
        }
        return generic_;
    }

    private static MappingPairs getExecutingCorrect(String _arg, String _param, ContextEl _context) {
        StringList typesArg_ = getAllTypes(_arg);
        StringList typesParam_ = getAllTypes(_param);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        String baseArrayParam_ = dParam_.getComponent();
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
        String idBaseArrayArg_ = getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = getIdFromAllTypes(baseArrayParam_);
        if (StringList.quickEq(idBaseArrayArg_, fct_)) {
            if (StringList.quickEq(idBaseArrayParam_, fct_)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringList.quickEq(baseArrayParam_, fct_)) {
                    return new MappingPairs();
                }
                int len_ = typesParam_.size();
                if (typesArg_.size() != len_) {
                    return null;
                }
                return newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return getMappingFctPairs(_context, dArg_, dParam_, baseArrayParam_);
        }
        if (StringList.quickEq(idBaseArrayParam_, fct_)) {
            return null;
        }
        String generic_;
        generic_ = getFullTypeByBases(_arg, _param, _context);
        if (generic_.isEmpty()) {
            return null;
        }
        return newMappingPairs(generic_, typesParam_);
    }

    private static MappingPairs getMappingFctPairs(ContextEl _context, DimComp _dArg, DimComp _dParam, String _baseArrayParam) {
        if (StringList.quickEq(_baseArrayParam, _context.getStandards().getAliasObject())) {
            int dim_ = _dArg.getDim();
            if (dim_ >= _dParam.getDim()) {
                return new MappingPairs();
            }
        }
        return null;
    }

    /**arg - param*/
    private static MappingPairs newMappingPairsFct(StringList _args, StringList _params, String _objectType) {
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
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
    /**arg - param*/
    private static MappingPairs newMappingPairs(String _generic, StringList _params) {
        int len_;
        StringList foundSuperClass_ = getAllTypes(_generic);
        CustList<Matching> pairsArgParam_ = new CustList<Matching>();
        len_ = foundSuperClass_.size();
        if (_params.size() != len_) {
            return null;
        }
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

    private static void addTypeIdNotYet(StringList _visitedClasses, StringList _nextClasses, String _geneSuperInterface) {
        if (!StringList.contains(_visitedClasses, _geneSuperInterface)) {
            _nextClasses.add(_geneSuperInterface);
            _visitedClasses.add(_geneSuperInterface);
        }
    }

    private static StringList getSuperInterfaceNames(String _className, ContextEl _context) {
        GeneType r_ = _context.getClassBody(_className);
        if (r_ instanceof ExecRootBlock) {
            return ((ExecRootBlock)r_).getImportedDirectSuperTypes();
        }
        return ((StandardType)r_).getDirectSuperTypes();
    }

    /**Checks nb parameters of the most wrapping type<br/>
     Let this code:<br/>
     <code><pre>public class my.pkg.MySimpleClass{}</pre>
     <pre>public class my.pkg.MyClass&lt;K&gt;{}</pre>
     <pre>public class my.pkg.MySecondClass&lt;K&gt;{</pre>
     <pre>     public class Inner&lt;V&gt;{}</pre>
     <pre>}</pre></code><br/>
     <pre>public class my.pkg.MyThirdClass&lt;K&gt;{</pre>
     <pre>     public static class Inner&lt;V&gt;{}</pre></code>
     <pre>}</pre></code><br/>
    Sample 1: int => true<br/>
    Sample 2: my.pkg.MySimpleClass => true<br/>
    Sample 3: my.pkg.MyClass => false<br/>
    Sample 4: my.pkg.MyClass&lt;Integer&gt; => true<br/>
    Sample 5: my.pkg.MyClass&lt;my.pkg.MyClass&gt; => true<br/>
    Sample 6: my.pkg.MyClass&lt;Integer,Integer&gt; => false<br/>
    Sample 7: my.pkg.MySecondClass..Inner&lt;Integer&gt; => false<br/>
    Sample 8: my.pkg.MySecondClass&lt;Integer&gt;..Inner&lt;Integer&gt; => true<br/>
    Sample 9: my.pkg.MySecondClass..Inner&lt;Integer,Integer&gt; => false<br/>
    Sample 10: my.pkg.MyThirdClass..Inner&lt;Integer,Integer&gt; => true<br/>
    Sample 11: my.pkg.MyThirdClass&lt;Integer&gt;..Inner&lt;Integer&gt; => false<br/>
    Sample 12: my.pkg.MyThirdClass..Inner&lt;Integer,Integer&gt; => false<br/>
    */
    public static boolean correctNbParameters(String _genericClass, ContextEl _context) {
        //From analyze
        String idCl_ = getIdFromAllTypes(_genericClass);
        String compo_ = PrimitiveTypeUtil.getQuickComponentBaseType(idCl_).getComponent();
        GeneType info_ = _context.getClassBody(compo_);
        if (info_ == null) {
            if (PrimitiveTypeUtil.isPrimitive(compo_,_context)) {
                return true;
            }
            return StringList.quickEq(compo_, _context.getStandards().getAliasVoid());
        }
        String fct_ = _context.getStandards().getAliasFct();
        Ints rep_ = info_.getTypeVarCounts();
        StringList inners_ = getAllInnerTypes(_genericClass);
        int len_ = inners_.size();
        if (!StringList.quickEq(compo_, fct_)) {
            for (int i = 0; i < len_; i++) {
                String i_ = inners_.get(i);
                int req_ = rep_.get(i);
                StringList params_ = getAllTypes(i_);
                int nbParams_ = params_.size() - 1;
                if (req_ != nbParams_) {
                    return false;
                }
            }
        }
        return true;
    }
}
