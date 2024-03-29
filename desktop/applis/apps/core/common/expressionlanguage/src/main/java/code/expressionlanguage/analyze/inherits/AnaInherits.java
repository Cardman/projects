package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.ManageTokens;
import code.expressionlanguage.analyze.TokenCheckerContext;
import code.expressionlanguage.analyze.blocks.AnnotationBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.Identifiable;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.StandardType;
import code.maths.litteralcom.MathExpUtil;
import code.util.CharList;
import code.util.CustList;
import code.util.Ints;
import code.util.StringList;
import code.util.StringMap;
import code.util.core.IndexConstants;
import code.util.core.StringUtil;

public final class AnaInherits {
    public static final String PREFIX_VAR_TYPE = "#";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";
    public static final char SEP_CLASS_CHAR = '.';

    private AnaInherits() {
    }

    public static String wildCardFormatReturn(String _first, String _second, AnalyzedPageEl _page) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        StringList types_ = StringExpUtil.getAllTypes(_first);
        CustList<TypeVar> typeVar_ = ContextUtil.getParamTypesMapValues(_page.getAnaGeneType(StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent()));
        if (typeVar_.size() != types_.size() - 1){
            return _page.getAliasObject();
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            return typeVarReturn(_second,_page, dc_.getDim(), types_, typeVar_);
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return StringExpUtil.getWildCardFormattedTypeReturn(_second, varTypes_);
    }

    private static String typeVarReturn(String _second, AnalyzedPageEl _page, int _d, StringList _types, CustList<TypeVar> _typeVar) {
        String objType_ = _page.getAliasObject();
        String name_ = _second.substring(PREFIX_VAR_TYPE.length()+ _d);

        int index_ = -1;
        for (TypeVar t: _typeVar) {
            index_++;
            if (StringUtil.quickEq(t.getName(), name_)) {
                String formatted_ = _types.get(index_+1);
                //return type, field getting
                if (StringUtil.quickEq(formatted_, SUB_TYPE)) {
                    return StringExpUtil.getPrettyArrayType(objType_, _d);
                }
                if (formatted_.startsWith(SUB_TYPE)) {
                    return StringExpUtil.getPrettyArrayType(formatted_.substring(SUB_TYPE.length()), _d);
                }
                if (formatted_.startsWith(SUP_TYPE)) {
                    return StringExpUtil.getPrettyArrayType(objType_, _d);
                }
                return StringExpUtil.getPrettyArrayType(formatted_, _d);
            }
        }
        return objType_;
    }

    public static StringList wildCardFormatParams(String _first, Identifiable _params, AnalyzedPageEl _page) {
        StringList params_ = new StringList();
        int nbParams_ = _params.getParametersTypesLength();
        for (int i = 0; i < nbParams_; i++) {
            params_.add(wildCardFormatParam(_first,_params.getParametersType(i),_page));
        }
        return params_;
    }
    public static String wildCardFormatParam(String _first, String _second, AnalyzedPageEl _page) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        StringList types_ = StringExpUtil.getAllTypes(_first);
        CustList<TypeVar> typeVar_ = ContextUtil.getParamTypesMapValues(_page.getAnaGeneType(StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent()));
        if (typeVar_.size() != types_.size() - 1){
            return "";
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            return typeVarParam(_second, dc_.getDim(), types_, typeVar_);
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        String objType_ = _page.getAliasObject();
        return StringExpUtil.getWildCardFormattedTypeParam(objType_,_second, varTypes_);
    }

    private static String typeVarParam(String _second, int _d, StringList _types, CustList<TypeVar> _typeVar) {
        String name_ = _second.substring(PREFIX_VAR_TYPE.length()+ _d);

        int index_ = -1;
        for (TypeVar t: _typeVar) {
            index_++;
            if (StringUtil.quickEq(t.getName(), name_)) {
                String formatted_ = _types.get(index_+1);
                //parameters, field affectation
                if (formatted_.startsWith(SUB_TYPE)) {
                    return "";
                }
                if (formatted_.startsWith(SUP_TYPE)) {
                    return StringExpUtil.getPrettyArrayType(formatted_.substring(SUP_TYPE.length()), _d);
                }
                return StringExpUtil.getPrettyArrayType(formatted_, _d);
            }
        }
        return "";
    }

    public static boolean isReturnCorrect(String _p, String _a, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isPrimitive(_p, _page) && !AnaTypeUtil.isPrimitive(_a, _page)) {
            return false;
        }
        String void_ = _page.getAliasVoid();
        if (StringUtil.quickEq(_p, void_)) {
            return StringUtil.quickEq(_a, void_);
        }
        if (StringUtil.quickEq(_a, void_)) {
            return false;
        }
        Mapping map_ = new Mapping();
        map_.setArg(_a);
        map_.setParam(_p);
        map_.setMapping(_mapping);
        return isCorrectOrNumbers(map_, _page);
    }

    public static boolean isCorrectOrNumbers(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !AnaTypeUtil.isPrimitive(p_, _page);
        }
        return isCorrect(transform(_m,_page), _page);
    }
    static Mapping transform(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching p_ = _m.getParam();
        if (AnaTypeUtil.isPrimitive(p_, _page)) {
            Mapping m_ = new Mapping();
            AnaClassArgumentMatching a_ = _m.getArg();
            m_.setArg(AnaTypeUtil.toPrimitive(a_, _page.getPrimitiveTypes()));
            m_.setParam(p_);
            m_.setMapping(_m.getMapping());
            return m_;
        }
        return _m;
    }

    public static boolean isCorrect(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        for (String p: param_.getNames()) {
            boolean ok_ = false;
            StringList names_ = arg_.getNames();
            for (String a: names_) {
                AbstractInheritProcess inh_ = new AnaInheritProcess(_page,generalMapping_);
                if (inh_.isCorrectExecute(a, p)) {
                    ok_ = true;
                    break;
                }
            }
            if (!ok_) {
                return false;
            }
        }
        return true;
    }

    public static String getGeneric(String _arg, String _param, Mapping _map, AnalyzedPageEl _page) {
        String objType_ = _page.getAliasObject();
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        String baseArrayArg_ = dArg_.getComponent();
        String generic_ = "";
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            StringMap<StringList> mapping_ = _map.getMapping();
            for (String c: Mapping.getAllUpperBounds(mapping_,baseArrayArg_.substring(PREFIX_VAR_TYPE.length()), objType_)) {
                String arr_ = StringExpUtil.getPrettyArrayType(c,dArg_.getDim());
                String idCl_ = StringExpUtil.getIdFromAllTypes(arr_);
                String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
                AnaGeneType info_ = _page.getAnaGeneType(compo_);
                generic_ = getFullTypeByBases(info_,arr_, _param, _page);
                if (!generic_.isEmpty()) {
                    break;
                }
            }
        } else {
            String idCl_ = StringExpUtil.getIdFromAllTypes(_arg);
            String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
            AnaGeneType info_ = _page.getAnaGeneType(compo_);
            generic_ = getFullTypeByBases(info_,_arg, _param, _page);
        }
        return generic_;
    }

    static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            return commonTypeVar(dArg_, dParam_, map_);
        }
        return getCommentMappingPairs(_arg, _param,_inherit, _page);
    }

    static MappingPairs commonTypeVar(DimComp _dArg, DimComp _dParam, Mapping _map) {
        String baseArrayParam_ = _dParam.getComponent();
        String baseArrayArg_ = _dArg.getComponent();
        if (!baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            return null;
        }
        if (_dArg.getDim() != _dParam.getDim()) {
            return null;
        }
        if (_map.inheritArgParam(baseArrayParam_.substring(1), baseArrayArg_.substring(1))) {
            return new MappingPairs();
        }
        return null;
    }

    static MappingPairs getCommentMappingPairs(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String idBaseArrayArg_ = StringExpUtil.getId(_arg);
        String idBaseArrayParam_ = StringExpUtil.getId(_param);
        String fct_ = _page.getAliasFct();
        if (StringUtil.quickEq(idBaseArrayArg_, fct_) || StringUtil.quickEq(idBaseArrayParam_, fct_)) {
            String obj_ = _page.getAliasObject();
            return FeedMappingTypePair.getMappingFctPairs(_arg,_param,fct_,obj_);
        }
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        String generic_ = getGeneric(_arg, _param, map_, _page);
        if (generic_.isEmpty()) {
            return null;
        }
        return FeedMappingTypePair.newMappingPairs(generic_, _param);
    }

    public static String getFullTypeByBases(AnaGeneType _typeSub, String _subType, String _superType, AnalyzedPageEl _page) {
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
        if (StringUtil.quickEq(classParam_, _page.getAliasObject())) {
            if (dBaseArg_.getDim() < dim_) {
                return "";
            }
            return _superType;
        }
        if (dBaseArg_.getDim() != dim_) {
            return "";
        }
        if (AnaTypeUtil.isPrimitive(baseArr_, _page)) {
            PrimitiveType pr_ = _page.getPrimitiveTypes().getVal(baseArr_);
            if (StringUtil.contains(pr_.getAllSuperType(_page), classParam_)) {
                return _superType;
            }
            return "";
        }
        if (StringUtil.quickEq(_subType, _page.getAliasVoid())) {
            return "";
        }
        if (StringUtil.quickEq(_superType, _page.getAliasVoid())) {
            return "";
        }
        String generic_ = getSuperGeneric(_typeSub, dim_, classParam_, _page);
        return format(_typeSub,_subType, generic_);
    }

    public static String getOverridingFullTypeByBases(String _subType, String _superType, AnalyzedPageEl _page) {
        AnaGeneType type_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(_subType));
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        String generic_ = getSuperGeneric(type_, 0, idSuperType_, _page);
        return quickFormat(type_,_subType, generic_);
    }

    public static AnaFormattedRootBlock getFormattedOverridingFullTypeByBases(AnaFormattedRootBlock _subType, AnaGeneType _superType) {
        AnaFormattedRootBlock res_ = getOverridingFullTypeByBases(_subType.getRootBlock(), _superType);
        if (res_ == null) {
            return null;
        }
        return AnaFormattedRootBlock.quickFormat(_subType,res_);
    }
    public static AnaFormattedRootBlock getOverridingFullTypeByBases(RootBlock _subType, AnaGeneType _superType) {
        if (_subType == null) {
            return null;
        }
        if (_subType == _superType) {
            return new AnaFormattedRootBlock(_subType);
        }
        for (AnaFormattedRootBlock g: _subType.getAllGenericSuperTypesInfo()) {
            if (g.getRootBlock() == _superType) {
                return g;
            }
        }
        return null;
    }

    public static String getSuperGeneric(AnaGeneType _subType, int _dim, String _classParam, AnalyzedPageEl _page) {
        if (_subType == null) {
            return "";
        }
        String param_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (_subType instanceof AnnotationBlock && StringUtil.quickEq(param_, _page.getAliasAnnotationType())) {
            return StringExpUtil.getPrettyArrayType(param_, _dim);
        }
        String generic_ = generic(_subType, param_);
        if (generic_.isEmpty()) {
            return "";
        }
        return StringExpUtil.getPrettyArrayType(generic_,_dim);
    }

    static String generic(AnaGeneType _subType, String _param) {
        String generic_ = "";
        if (_subType instanceof RootBlock) {
            generic_ = new AnaLookingSuperTypes(((RootBlock) _subType).getAllGenericSuperTypesInfo()).find(_param);
        }
        if (_subType instanceof StandardType) {
            generic_ = new CommonLookingSuperTypes(((StandardType) _subType).getAllGenericSuperTypes()).find(_param);
        }
        return generic_;
    }

    public static String format(AnaFormattedRootBlock _root, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }

    public static String format(AnaGeneType _root, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root,_first);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }

    public static String quickFormat(AnaFormattedRootBlock _root, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root);
        return StringExpUtil.getQuickFormattedType(_second, varTypes_);
    }
    public static String quickFormat(AnaGeneType _root, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root,_first);
        return StringExpUtil.getQuickFormattedType(_second, varTypes_);
    }

    public static StringMap<String> getVarTypes(AnaFormattedRootBlock _root) {
        return getVarTypes(_root.getRootBlock(),_root.getFormatted());
    }
    public static StringMap<String> getVarTypes(AnaGeneType _root, String _className) {
        StringMap<String> varTypes_ = new StringMap<String>();
        if (_root == null) {
            return varTypes_;
        }
        return StringExpUtil.getVarTypes(_root.getParamTypesValues(),_className);
    }

    public static boolean correctNbParameters(AnaGeneType _info, String _genericClass, AnalyzedPageEl _page) {
        //From analyze
        String idCl_ = StringExpUtil.getIdFromAllTypes(_genericClass);
        String compo_ = StringExpUtil.getQuickComponentBaseType(idCl_).getComponent();
        if (_info == null) {
            if (AnaTypeUtil.isPrimitive(compo_, _page)) {
                return true;
            }
            return StringUtil.quickEq(compo_, _page.getAliasVoid());
        }
        String fct_ = _page.getAliasFct();
        Ints rep_ = _info.getTypeVarCounts();
        return StringExpUtil.commonCorrectType(_genericClass, fct_, rep_);
    }

    public static boolean correctNbParameters(AnaGeneType _info, String _genericClass) {
        //From analyze
        Ints rep_ = _info.getTypeVarCounts();
        return StringExpUtil.normalCorrectType(_genericClass, rep_);
    }

    public static boolean isOkQualFields(String _string, TokenCheckerContext _page) {
        int last_ = _string.lastIndexOf('.');
        if (last_ < 0) {
            return ManageTokens.isValidToken(_string.trim(),_page);
        }
        String firstPart_ = _string.substring(0,last_);
        StringList inner_ = getAllInnerTypes(firstPart_, new StringList());
        int size_ = inner_.size();
        for (int i = 0; i < size_; i+=2) {
            String part_ = inner_.get(i).trim();
            int c_ = 0;
            for (char c: CharList.wrapCharArray('*','+')) {
                int incr_ = incr(part_, c, _page);
                if (incr_ < 0) {
                    return false;
                }
                c_ += incr_;
            }
            if (c_ == 0 && !ManageTokens.isValidToken(part_,_page)) {
                return false;
            }
        }
        String lastPart_ = _string.substring(last_+1);
        return ManageTokens.isValidToken(lastPart_.trim(),_page);
    }
    private static int incr(String _part, char _c, TokenCheckerContext _page) {
        int ind_ = _part.indexOf(_c);
        if (ind_ >= 0) {
            if (!MathExpUtil.isPositiveNumber(_part.substring(ind_+1).trim())){
                return -1;
            }
            String pre_ = _part.substring(0, ind_).trim();
            if (_c == '*' && StringUtil.quickEq(pre_, _page.getKeys().getKeyWordId())) {
                return 1;
            }
            if (!ManageTokens.isValidToken(pre_,_page)) {
                return -1;
            }
            return 1;
        }
        return 0;
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
    public static StringList getAllInnerTypes(String _type, AnalyzedPageEl _page) {
        return getAllInnerTypes(_type, _page.getPackagesFound());
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
                if (StringExpUtil.nextCharIs(_type,i_+1,len_,SEP_CLASS_CHAR)||!StringUtil.contains(_pkg,tr_)) {
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

    public static DimComp getComponentForm(String _type) {
        int j_ = _type.length()-1;
        boolean arr_ = true;
        int count_ = 0;
        while (arr_) {
            while (j_ >= 0) {
                char locChar_ = _type.charAt(j_);
                if (!StringUtil.isWhitespace(locChar_)) {
                    if (locChar_ != ']') {
                        arr_ = false;
                    }
                    break;
                }
                j_--;
            }
            j_ = pair(arr_,_type,j_);
            if (j_ < 0) {
                break;
            }
            if (arr_) {
                j_--;
                count_++;
            }
        }
        if (j_ >= 0) {
            return new DimComp(count_,_type.substring(0, j_+1));
        }
        return new DimComp(0,"");
    }
    private static int pair(boolean _arr, String _type, int _j) {
        if (!_arr) {
            return _j;
        }
        int j_ = _j;
        j_--;
        while (j_ >= 0) {
            char locChar_ = _type.charAt(j_);
            if (!StringUtil.isWhitespace(locChar_)) {
                break;
            }
            j_--;
        }
        return j_;
    }

    public static String check(StringList _errs, RootBlock _root, String _className, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String res_ = tryGetAllInners(_root,_className, _parts, _inherit, _page);
        if (res_.isEmpty()) {
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFile(_page.getCurrentFile());
            un_.setIndexFile(_page);
            //original type len
            String realClassName_ = getRealClassName(_className, _parts);
            un_.buildError(_page.getAnalysisMessages().getBadParamerizedType(),
                    realClassName_);
            _page.getLocalizer().addError(un_);
            _errs.add(un_.getBuiltError());
            res_ = _page.getAliasObject();
        }
        return res_;
    }

    public static String tryGetAllInners(RootBlock _root, String _className, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String realClassName_ = getRealClassName(_className, _parts);
        if (!correctNbParameters(_root,realClassName_,_page)) {
            realClassName_ = getRealClassName(StringExpUtil.getIdFromAllTypes(_className), _parts);
        }
        if (!correctNbParameters(_root,realClassName_,_page)) {
            return check("", new StringList(""),new StringMap<StringList>(),_page,_root,new CustList<StringList>());
        }
        return getCorrectTemplateAll(_root, realClassName_, _parts, _inherit, _page);
    }

    public static String getRealClassName(String _className, CustList<String> _parts) {
        String realClassName_;
        if (_parts.isEmpty()) {
            realClassName_ = _className;
        } else {
            realClassName_ = StringUtil.concat(_className,"<", StringUtil.join(_parts, ","),">");
        }
        return realClassName_;
    }

    public static String getCorrectTemplateAll(RootBlock _type, String _realClassName,StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        if (_type == null) {
            return "";
        }
        CustList<StringList> bounds_ = _type.getBoundAll();
        return check(_realClassName, _parts, _inherit, _page, _type, bounds_);
    }

    public static String getCorrectTemplateAllAll(String _realClassName, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        AnaGeneType g_ = _page.getAnaGeneType(id_);
        if (g_ == null) {
            return "";
        }
        CustList<StringList> bounds_ = ContextUtil.getBoundAllAll(g_);
        return check(_realClassName, _parts, _inherit, _page, g_, bounds_);
    }

    private static String check(String _realClassName, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page, AnaGeneType _g, CustList<StringList> _bounds) {
        int len_ = _bounds.size();
        if (len_ != _parts.size()) {
            return "";
        }
        for (int i = 0; i < len_; i++) {
            StringList b_ = _bounds.get(i);
            for (String b : b_) {
                Mapping mapp_ = new Mapping();
                mapp_.setArg(_parts.get(i));
                String param_ = format(_g, _realClassName, b);
                mapp_.setParam(param_);
                mapp_.setMapping(_inherit);
                if (!isCorrect(mapp_, _page)) {
                    return "";
                }
            }
        }
        return _realClassName;
    }

    public static CustList<StringList> getBoundAll(AnaGeneType _ana) {
        if (_ana == null) {
            return new CustList<StringList>();
        }
        return _ana.getBoundAll();
    }
}
