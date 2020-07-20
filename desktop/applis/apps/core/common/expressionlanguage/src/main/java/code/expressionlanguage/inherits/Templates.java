package code.expressionlanguage.inherits;

import code.expressionlanguage.*;
import code.expressionlanguage.common.AnaGeneType;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;

import code.expressionlanguage.stds.*;
import code.util.*;

public final class Templates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';
    public static final char SEP_CLASS_CHAR = '.';
    public static final String PREFIX_VAR_TYPE = "#";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';
    private Templates() {
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
        //
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
                    for (String p: _an.getAnalyzing().getPackagesFound()) {
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
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
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
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        GeneType classBody_ = _context.getClassBody(idArg_);
        if (classBody_ == null) {
            return "";
        }
        String geneSubType_ = classBody_.getGenericString();
        String generic_ = getSuperGeneric(_context, 0, idSuperType_, geneSubType_);
        return quickFormat(_subType, generic_, _context);
    }

    public static String getOverridingFullTypeByBases(AnaGeneType _subType, String _superType, ContextEl _context) {
        String geneSubType_ = _subType.getGenericString();
        return getInternOverriding(_superType, _context, geneSubType_);
    }

    private static String getInternOverriding(String _superType, ContextEl _context, String _geneSubType) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_geneSubType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _geneSubType;
        }
        String generic_ = getSuperGeneric(_context, 0, idSuperType_, _geneSubType);
        return quickFormat(_geneSubType, generic_, _context);
    }


    public static String getSuperGeneric(ContextEl _context, int _dim, String _classParam, String _geneSubType) {
        String generic_ = "";
        StringList curClasses_ = new StringList(_geneSubType);
        StringList visitedClasses_ = new StringList(_geneSubType);
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c : curClasses_) {
                String baseClass_ = StringExpUtil.getIdFromAllTypes(c);
                baseClass_ = StringExpUtil.getQuickComponentBaseType(baseClass_).getComponent();
                for (String s : getSuperInterfaceNames(baseClass_, _context)) {
                    String id_ = StringExpUtil.getIdFromAllTypes(s);
                    String geneSuperInterface_;
                    geneSuperInterface_ = quickFormat(c, s, _context);
                    if (StringList.quickEq(id_, _classParam)) {
                        generic_ = StringExpUtil.getPrettyArrayType(geneSuperInterface_, _dim);
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
        return StringExpUtil.getFormattedType(_second, varTypes_);
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
        return StringExpUtil.getQuickFormattedType(_second, varTypes_);
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
        StringList types_ = StringExpUtil.getAllTypes(_className);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
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

    public static void addTypeIdNotYet(StringList _visitedClasses, StringList _nextClasses, String _geneSuperInterface) {
        if (!StringList.contains(_visitedClasses, _geneSuperInterface)) {
            _nextClasses.add(_geneSuperInterface);
            _visitedClasses.add(_geneSuperInterface);
        }
    }

    public static StringList getSuperInterfaceNames(String _className, ContextEl _context) {
        GeneType r_ = _context.getClassBody(_className);
        if (r_ instanceof ExecRootBlock) {
            return ((ExecRootBlock)r_).getImportedDirectSuperTypes();
        }
        if (r_ instanceof StandardType) {
            return ((StandardType)r_).getDirectSuperTypes();
        }
        return new StringList();
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
        String idCl_ = StringExpUtil.getIdFromAllTypes(_genericClass);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        GeneType info_ = _context.getClassBody(compo_);
        if (info_ == null) {
            if (PrimitiveTypeUtil.isPrimitive(compo_,_context)) {
                return true;
            }
            return StringList.quickEq(compo_, _context.getStandards().getAliasVoid());
        }
        String fct_ = _context.getStandards().getAliasFct();
        Ints rep_ = info_.getTypeVarCounts();
        StringList inners_ = StringExpUtil.getAllInnerTypes(_genericClass);
        int len_ = inners_.size();
        if (!StringList.quickEq(compo_, fct_)) {
            for (int i = 0; i < len_; i++) {
                String i_ = inners_.get(i);
                int req_ = rep_.get(i);
                StringList params_ = StringExpUtil.getAllTypes(i_);
                int nbParams_ = params_.size() - 1;
                if (req_ != nbParams_) {
                    return false;
                }
            }
        }
        return true;
    }
}
