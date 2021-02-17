package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.inherits.MappingPairs;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.NullStruct;
import code.expressionlanguage.structs.Struct;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class ExecInherits {
    private ExecInherits() {
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
                    String param_ = n.getParam();
                    String arg_ = n.getArg();
                    if (n.getMatchEq() == MatchingEnum.EQ) {
                        if (!StringUtil.quickEq(param_, arg_)) {
                            okTree_ = false;
                            break;
                        }
                        continue;
                    }
                    if (StringUtil.quickEq(param_, arg_)) {
                        continue;
                    }
                    Matching n_ = new Matching();
                    if (n.getMatchEq() == MatchingEnum.SUB) {
                        n_.setArg(arg_);
                        n_.setParam(param_);
                    } else {
                        n_.setArg(param_);
                        n_.setParam(arg_);
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

    private static MappingPairs getExecutingCorrect(String _arg, String _param, ContextEl _context) {
        StringList typesArg_ = StringExpUtil.getAllTypes(_arg);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        String baseArrayParam_ = dParam_.getComponent();
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        String obj_ = _context.getStandards().getContent().getCoreNames().getAliasObject();
        String idBaseArrayArg_ = StringExpUtil.getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = StringExpUtil.getIdFromAllTypes(baseArrayParam_);
        if (StringUtil.quickEq(idBaseArrayArg_, fct_)) {
            if (StringUtil.quickEq(idBaseArrayParam_, fct_)) {
                int dim_ = dArg_.getDim();
                if (dim_ != dParam_.getDim()) {
                    return null;
                }
                if (StringUtil.quickEq(baseArrayParam_, fct_)) {
                    return new MappingPairs();
                }
                return StringExpUtil.newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return StringExpUtil.getMappingFctPairs(dArg_, dParam_, baseArrayParam_, obj_);
        }
        if (StringUtil.quickEq(idBaseArrayParam_, fct_)) {
            return null;
        }
        String generic_ = getFullTypeByBases(_arg, _param, _context);
        if (generic_.isEmpty()) {
            return null;
        }
        return StringExpUtil.newMappingPairs(generic_, typesParam_);
    }

    public static ErrorType safeObject(ErrorType _errCast, String _param, Struct _arg, ContextEl _context) {
        if (_arg == null) {
            return ErrorType.NPE;
        }
        LgNames stds_ = _context.getStandards();
        String param_ = StringUtil.nullToEmpty(_param);
        if (_arg != NullStruct.NULL_VALUE) {
            String a_ = _arg.getClassName(_context);
            param_ = toWrapper(param_, stds_);
            if (!isCorrectExecute(a_, param_, _context)) {
                return _errCast;
            }
            return ErrorType.NOTHING;
        }
        if (param_.isEmpty()) {
            return ErrorType.CAST;
        }
        if (_context.getStandards().getPrimitiveTypes().contains(param_)) {
            return ErrorType.NPE;
        }
        return ErrorType.NOTHING;
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
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        int dimArg_ = dBaseArg_.getDim();
        if (StringUtil.quickEq(classParam_, _context.getStandards().getContent().getCoreNames().getAliasObject())) {
            if (dimArg_ < dim_) {
                return "";
            }
            return _superType;
        }
        if (dimArg_ != dim_) {
            return "";
        }
        if (ExecClassArgumentMatching.isPrimitive(baseArr_,_context)) {
            PrimitiveType pr_ = _context.getStandards().getPrimitiveTypes().getVal(baseArr_);
            if (StringUtil.contains(pr_.getAllSuperType(_context), classParam_)) {
                return _superType;
            }
            return "";
        }
        if (StringUtil.quickEq(_subType, _context.getStandards().getContent().getCoreNames().getAliasVoid())) {
            return "";
        }
        if (StringUtil.quickEq(_superType, _context.getStandards().getContent().getCoreNames().getAliasVoid())) {
            return "";
        }
        GeneType classBody_ = _context.getClassBody(baseArr_);
        String generic_ = getSuperGeneric(classBody_,_context, dim_, classParam_);
        return format(classBody_,_subType, generic_);
    }

    static String getSuperGeneric(GeneType _subType, ContextEl _context, int _dim, String _classParam) {
        String param_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (_subType instanceof ExecAnnotationBlock) {
            if (StringUtil.quickEq(param_, _context.getStandards().getContent().getReflect().getAliasAnnotationType())) {
                return StringExpUtil.getPrettyArrayType(param_,_dim);
            }
        }
        String generic_ = "";
        if (_subType instanceof ExecRootBlock) {
            for (ExecFormattedRootBlock e: ((ExecRootBlock)_subType).getAllGenericSuperTypes()) {
                String g = e.getFormatted();
                if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
                    generic_ = g;
                    break;
                }
            }
        }
        if (_subType instanceof StandardType) {
            for (String g: ((StandardType)_subType).getAllGenericSuperTypes()) {
                 if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
                    generic_ = g;
                    break;
                }
            }
        }
        if (generic_.isEmpty()) {
            return "";
        }
        return StringExpUtil.getPrettyArrayType(generic_,_dim);
    }

    public static String format(GeneType _type, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_first, _type);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }

    static StringMap<String> getVarTypes(String _className, GeneType _root) {
        StringList types_ = StringExpUtil.getAllTypes(_className);
        return getVarTypes(types_,_root);
    }

    static StringMap<String> getVarTypes(StringList _types, GeneType _root) {
        StringMap<String> varTypes_ = new StringMap<String>();
        if (_root == null) {
            return varTypes_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t: _root.getParamTypesValues()) {
            i_++;
            if (!_types.isValidIndex(i_)) {
                return varTypes_;
            }
            String arg_ = _types.get(i_);
            varTypes_.put(t, arg_);
        }
        return varTypes_;
    }

    public static String toWrapper(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringUtil.quickEq(e.getKey(), _class)) {
                return e.getValue().getWrapper();
            }
        }
        return _class;
    }

    public static String getQuickFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        String classParam_ = dBaseParam_.getComponent();
        int dim_ = dBaseParam_.getDim();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        int dimArg_ = dBaseArg_.getDim();
        if (StringUtil.quickEq(classParam_, _context.getStandards().getContent().getCoreNames().getAliasObject())) {
            if (dimArg_ < dim_) {
                return "";
            }
            return _superType;
        }
        if (dimArg_ != dim_) {
            return "";
        }
        return getFullObject(_subType, _superType,_context);
    }

    public static String getFullObject(String _subType, String _superType, ContextEl _context) {
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        GeneType classBody_ = _context.getClassBody(baseArr_);
        String generic_ = getSuperGeneric(classBody_,_context, dim_, classParam_);
        return quickFormat(classBody_,_subType, generic_);
    }

    public static String reflectFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return StringExpUtil.getReflectFormattedType(_second, varTypes_);
    }

    public static String getOverridingFullTypeByBases(String _subType, String _superType, ContextEl _context) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        GeneType classBody_ = _context.getClassBody(idArg_);
        String generic_ = getSuperGeneric(classBody_,_context, 0, idSuperType_);
        return quickFormat(classBody_,_subType, generic_);
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
        return quickFormat(_context.getClassBody(StringExpUtil.getIdFromAllTypes(_first)),_first,_second);
    }

    public static String quickFormat(GeneType _type, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_first,_type);
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
    private static StringMap<String> getVarTypes(String _className, ContextEl _context) {
        StringList types_ = StringExpUtil.getAllTypes(_className);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        return getVarTypes(types_, root_);
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
            if (ExecClassArgumentMatching.isPrimitive(compo_,_context)) {
                return true;
            }
            return StringUtil.quickEq(compo_, _context.getStandards().getContent().getCoreNames().getAliasVoid());
        }
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        Ints rep_ = info_.getTypeVarCounts();
        return StringExpUtil.commonCorrectType(_genericClass,compo_,fct_,rep_);
    }

    public static String getSuperGeneric(String _arg, String _classParam, ContextEl _context) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_arg);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _arg;
        }
        GeneType classBody_ = _context.getClassBody(idArg_);
        String generic_ = getSuperGeneric(classBody_, _context, 0, _classParam);
        return quickFormat(classBody_,_arg, generic_);
    }
}
