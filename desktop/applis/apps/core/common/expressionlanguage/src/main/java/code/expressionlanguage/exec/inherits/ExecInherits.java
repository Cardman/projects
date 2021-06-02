package code.expressionlanguage.exec.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.common.*;
import code.expressionlanguage.exec.ErrorType;
import code.expressionlanguage.exec.blocks.ExecAnnotationBlock;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.types.ExecClassArgumentMatching;
import code.expressionlanguage.exec.util.ExecFormattedRootBlock;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardType;
import code.util.*;
import code.util.core.StringUtil;

public final class ExecInherits {
    private ExecInherits() {
    }

    public static boolean isCorrectExecute(String _a, String _p, ContextEl _context) {
        if (_p.isEmpty()) {
            return false;
        }
        AbstractInheritProcess inh_ = new ExecInheritProcess(_context);
        return inh_.isCorrectExecute(_a, _p);
    }

    static MappingPairs getExecutingCorrect(String _arg, String _param, ContextEl _context) {
        String idBaseArrayArg_ = StringExpUtil.getId(_arg);
        String idBaseArrayParam_ = StringExpUtil.getId(_param);
        String fct_ = _context.getStandards().getContent().getReflect().getAliasFct();
        if (StringUtil.quickEq(idBaseArrayArg_, fct_) || StringUtil.quickEq(idBaseArrayParam_, fct_)) {
            String obj_ = _context.getStandards().getContent().getCoreNames().getAliasObject();
            return FeedMappingTypePair.getMappingFctPairs(_arg,_param,fct_,obj_);
        }
        String generic_ = getFullTypeByBases(_arg, _param, _context);
        if (generic_.isEmpty()) {
            return null;
        }
        return FeedMappingTypePair.newMappingPairs(generic_, _param);
    }

    public static ErrorType safeObject(String _param, String _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        String arg_ = StringUtil.nullToEmpty(_arg);
        String param_ = StringUtil.nullToEmpty(_param);
        if (!arg_.isEmpty()) {
            param_ = toWrapper(param_, stds_);
            if (!isCorrectExecute(arg_, param_, _context)) {
                return ErrorType.CAST;
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
        PrimitiveType pr_ = _context.getStandards().getPrimitiveTypes().getVal(baseArr_);
        if (pr_ != null) {
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
        String generic_ = getSuperGenericIn(baseArr_,dBaseParam_, _context);
        GeneType classBody_ = _context.getClassBody(baseArr_);
        return format(classBody_,_subType, generic_);
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
        return StringExpUtil.getVarTypes(_root.getParamTypesValues(),_types);
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
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        return getSuperGeneric(_subType,idSuperType_, _context);
    }

    public static String reflectFormat(ExecFormattedRootBlock _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_first.getFormatted(), _first.getRootBlock());
        return StringExpUtil.getReflectFormattedType(_second, varTypes_);
    }

    public static String reflectFormat(String _first, String _second, ContextEl _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, _context);
        return StringExpUtil.getReflectFormattedType(_second, varTypes_);
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

    public static String quickFormat(ExecFormattedRootBlock _type, String _second) {
        return quickFormat(_type.getRootBlock(),_type.getFormatted(),_second);
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
        return StringExpUtil.commonCorrectType(_genericClass, fct_,rep_);
    }

    private static String getSuperGeneric(String _subType, String _supType, ContextEl _context) {
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(_supType);
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        String generic_ = getSuperGenericIn(baseArr_,dBaseParam_, _context);
        GeneType classBody_ = _context.getClassBody(baseArr_);
        return quickFormat(classBody_, _subType, generic_);
    }

    private static String getSuperGenericIn(String _arg, DimComp _dimParam, ContextEl _context) {
        GeneType classBody_ = _context.getClassBody(_arg);
        int dim_ = _dimParam.getDim();
        String param_ = StringExpUtil.getIdFromAllTypes(_dimParam.getComponent());
        if (classBody_ instanceof ExecAnnotationBlock && StringUtil.quickEq(param_, _context.getStandards().getContent().getReflect().getAliasAnnotationType())) {
            return StringExpUtil.getPrettyArrayType(param_, dim_);
        }
        String generic_ = "";
        if (classBody_ instanceof ExecRootBlock) {
            generic_ = new ExecLookingSuperTypes(((ExecRootBlock) classBody_).getAllGenericSuperTypes()).find(param_);
        }
        if (classBody_ instanceof StandardType) {
            generic_ = new CommonLookingSuperTypes(((StandardType) classBody_).getAllGenericSuperTypes()).find(param_);
        }
        if (generic_.isEmpty()) {
            return "";
        }
        return StringExpUtil.getPrettyArrayType(generic_,dim_);
    }

}
