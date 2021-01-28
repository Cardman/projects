package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnnotationBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.opers.OperationNode;
import code.expressionlanguage.analyze.opers.util.ConstrustorIdVarArg;
import code.expressionlanguage.analyze.opers.util.VarsComparer;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.*;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.*;

import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.structs.IntStruct;
import code.util.*;
import code.util.core.IndexConstants;
import code.util.core.NumberUtil;
import code.util.core.StringUtil;

public final class AnaTemplates {

    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char SEP_CLASS_CHAR = '.';
    public static final String PREFIX_VAR_TYPE = "#";
    public static final String SUB_TYPE = "?";
    public static final String SUP_TYPE = "!";

    public static final char LT = '<';

    public static final char GT = '>';

    private static final String NO_SUB_CLASS = "";

    private AnaTemplates() {
    }

    public static CustList<ConstrustorIdVarArg> reduceCtors(CustList<ConstrustorIdVarArg> _ls) {
        CustList<ConstrustorIdVarArg> ls_ = new CustList<ConstrustorIdVarArg>();
        for (ConstrustorIdVarArg c: _ls) {
            if (!containsResCtor(ls_,c)) {
                ls_.add(c);
            }
        }
        return ls_;
    }
    public static boolean containsResCtor(CustList<ConstrustorIdVarArg> _ls, ConstrustorIdVarArg _res) {
        for (ConstrustorIdVarArg c: _ls) {
            if (!StringUtil.quickEq(c.getConstId().getName(),_res.getConstId().getName())) {
                continue;
            }
            if (!c.getRealId().eq(_res.getRealId())) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static CustList<ClassMethodIdReturn> reduceMethods(CustList<ClassMethodIdReturn> _ls) {
        CustList<ClassMethodIdReturn> ls_ = new CustList<ClassMethodIdReturn>();
        for (ClassMethodIdReturn c: _ls) {
            if (!containsResMethod(ls_,c)) {
                ls_.add(c);
            }
        }
        return ls_;
    }
    public static boolean containsResMethod(CustList<ClassMethodIdReturn> _ls, ClassMethodIdReturn _res) {
        ClassMethodId input_ = new ClassMethodId(_res.getRealClass(),_res.getRealId());
        for (ClassMethodIdReturn c: _ls) {
            if (c.getAncestor() != _res.getAncestor()) {
                continue;
            }
            ClassMethodId cur_ = new ClassMethodId(c.getRealClass(),c.getRealId());
            if (!cur_.eq(input_)) {
                continue;
            }
            return true;
        }
        return false;
    }
    public static ResultTernary getResultTernary(StringList _first, Argument _firstArg,
                                                 StringList _second, Argument _secondArg,
                                                 StringMap<StringList> _vars,
                                                 AnalyzedPageEl _page) {
        if (StringUtil.equalsSet(_first, _second)) {
            return ResultTernary.noUnwrap(_first);
        }
        AnaClassArgumentMatching first_ = new AnaClassArgumentMatching(_first);
        AnaClassArgumentMatching second_ = new AnaClassArgumentMatching(_second);
        if (_page.matchPrimWrap(_first,_second)) {
            return ResultTernary.unwrapRight(_first, first_.getPrimitiveCast(_page));
        }
        if (_page.matchPrimWrap(_second,_first)) {
            return ResultTernary.unwrapLeft(_second, second_.getPrimitiveCast(_page));
        }
        if (StringUtil.contains(_first, NO_SUB_CLASS)) {
            return ResultTernary.noUnwrap(_page.getTernary(_second));
        }
        if (StringUtil.contains(_second, NO_SUB_CLASS)) {
            return ResultTernary.noUnwrap(_page.getTernary(_first));
        }
        if (AnaTypeUtil.isPrimitiveOrWrapper(first_, _page) && AnaTypeUtil.isPrimitiveOrWrapper(second_, _page)) {
            String primShort_ = _page.getAliasPrimShort();
            String primChar_ = _page.getAliasPrimChar();
            String primByte_ = _page.getAliasPrimByte();
            String short_ = _page.getAliasShort();
            String char_ = _page.getAliasCharacter();
            String byte_ = _page.getAliasByte();
            if (_secondArg != null && _secondArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_secondArg.getStruct()).intStruct();
                if (StringUtil.contains(_first, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringUtil.contains(_first, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringUtil.contains(_first, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
                if (StringUtil.contains(_first, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringUtil.contains(_first, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringUtil.contains(_first, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
            }
            if (_firstArg != null && _firstArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_firstArg.getStruct()).intStruct();
                if (StringUtil.contains(_second, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringUtil.contains(_second, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringUtil.contains(_second, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
                if (StringUtil.contains(_second, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringUtil.contains(_second, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringUtil.contains(_second, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
            }
            StringList prOne_ = new StringList();
            StringList prTwo_ = new StringList();
            for (String c: _first) {
                prOne_.add(AnaTypeUtil.toPrimitive(c, _page.getPrimitiveTypes()));
            }
            for (String c: _second) {
                prTwo_.add(AnaTypeUtil.toPrimitive(c, _page.getPrimitiveTypes()));
            }
            StringList superTypesFirst_ = getSuperTypesSet(prOne_, _vars, _page);
            StringList superTypesSecond_ = getSuperTypesSet(prTwo_, _vars, _page);
            StringList ints_ = StringUtil.intersect(superTypesFirst_,superTypesSecond_);
            StringList bases_;
            bases_ = getTernarySubclasses(ints_, _vars, _page);
            return ResultTernary.unwrapBoth(bases_, AnaClassArgumentMatching.getPrimitiveCast(bases_, _page));
        }
        StringList superTypesFirst_ = getSuperTypesSet(_first, _vars, _page);
        StringList superTypesSecond_ = getSuperTypesSet(_second, _vars, _page);
        StringList superTypesFirstAdj_ = new StringList(superTypesFirst_);
        StringList superTypesSecondAdj_ = new StringList(superTypesSecond_);
        for (String f: superTypesFirst_) {
            for (String s: superTypesSecond_) {
                Mapping map_ = new Mapping();
                map_.setArg(s);
                map_.setParam(f);
                map_.setMapping(_vars);
                if (isCorrectOrNumbers(map_, _page)) {
                    superTypesSecondAdj_.add(f);
                }
                map_ = new Mapping();
                map_.setArg(f);
                map_.setParam(s);
                map_.setMapping(_vars);
                if (isCorrectOrNumbers(map_, _page)) {
                    superTypesFirstAdj_.add(s);
                }
            }
        }
        superTypesSecondAdj_.removeDuplicates();
        superTypesFirstAdj_.removeDuplicates();
        StringList ints_ = StringUtil.intersect(superTypesFirstAdj_,superTypesSecondAdj_);
        StringMap<String> basesGene_ = new StringMap<String>();
        StringList bases_ = new StringList();
        for (String l: ints_) {
            String id_ = StringExpUtil.getIdFromAllTypes(l);
            basesGene_.put(id_, l);
            bases_.add(id_);
        }
        bases_ = getTernarySubclasses(bases_, _vars, _page);
        StringList out_ = new StringList();
        for (String l: bases_) {
            out_.add(basesGene_.getVal(l));
        }
        out_.removeDuplicates();
        return ResultTernary.noUnwrap(out_);
    }

    private static CustList<AnaClassArgumentMatching> getAllSuperTypes(AnaClassArgumentMatching _class, AnalyzedPageEl _page) {
        CustList<AnaClassArgumentMatching> gt_ = new CustList<AnaClassArgumentMatching>();
        String name_ = _class.getName();
        StringMap<PrimitiveType> prs_ = _page.getPrimitiveTypes();
        PrimitiveType pr_ = prs_.getVal(name_);
        gt_.add(_class);
        for (String s: pr_.getAllSuperType(_page)) {
            if (!prs_.contains(s)) {
                continue;
            }
            gt_.add(new AnaClassArgumentMatching(s));
        }
        return gt_;
    }
    static StringList getTernarySubclasses(StringList _classNames, StringMap<StringList> _map, AnalyzedPageEl _page) {
        StringList types_ = new StringList();
        String obj_ = _page.getAliasObject();
        Mapping m_ = new Mapping();
        m_.setMapping(_map);
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                DimComp baseArrSup_ = StringExpUtil.getQuickComponentBaseType(baseSup_);
                DimComp baseArrSub_ = StringExpUtil.getQuickComponentBaseType(baseSub_);
                if (StringUtil.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                if (AnaTypeUtil.isPrimitive(baseSup_, _page) && !AnaTypeUtil.isPrimitive(baseSub_, _page)) {
                    continue;
                }
                int dimSup_ = baseArrSub_.getDim();
                if (baseArrSub_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
                    boolean inh_ = false;
                    String b_ = baseArrSub_.getComponent().substring(PREFIX_VAR_TYPE.length());
                    for (String u: Mapping.getAllBounds(_map, b_, obj_)) {
                        String a_ = StringExpUtil.getPrettyArrayType(u, dimSup_);
                        if (StringUtil.quickEq(a_, baseSup_)) {
                            inh_ = true;
                            break;
                        }
                    }
                    if (inh_) {
                        sub_ = false;
                        break;
                    }
                    continue;
                }
                if (StringUtil.quickEq(baseArrSup_.getComponent(), _page.getAliasObject())) {
                    if (baseArrSub_.getDim() >= baseArrSup_.getDim()) {
                        sub_ = false;
                        break;
                    }
                    continue;
                }
                if (baseArrSub_.getDim() != baseArrSup_.getDim()) {
                    continue;
                }
                if (baseArrSup_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
                    continue;
                }
                AnaInheritedType pr_ = _page.getPrimitiveTypes().getVal(baseArrSub_.getComponent());
                AnaInheritedType g_ = _page.getAnaGeneType(baseArrSub_.getComponent());
                AnaInheritedType in_;
                if (pr_ != null) {
                    in_ = pr_;
                } else {
                    in_ = g_;
                }
                if (in_.isSubTypeOf(baseArrSup_.getComponent(), _page)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
        types_.removeDuplicates();
        return types_;
    }
    static StringList getSuperTypesSet(StringList _first, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        StringList superTypes_ = new StringList();
        String obj_ = _page.getAliasObject();
        String bool_ = _page.getAliasPrimBoolean();
        for (String c: _first) {
            DimComp dc_ = StringExpUtil.getQuickComponentBaseType(c);
            String base_ = dc_.getComponent();
            int d_ = dc_.getDim();
            superTypes_.add(c);
            if (base_.startsWith(PREFIX_VAR_TYPE)) {
                String ex_ = base_.substring(PREFIX_VAR_TYPE.length());
                for (String t: Mapping.getAllBounds(_mapping, ex_, obj_)) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
                    if (t.startsWith(PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    DimComp dci_ = StringExpUtil.getQuickComponentBaseType(t);
                    String component_ = dci_.getComponent();
                    int dLoc_ = dci_.getDim();
                    String i_ = StringExpUtil.getIdFromAllTypes(component_);
                    AnaGeneType j_ = _page.getAnaGeneType(i_);
                    addTypes(superTypes_, component_, d_+ dLoc_, j_);
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (StringUtil.quickEq(base_, bool_)) {
                superTypes_.add(StringExpUtil.getPrettyArrayType(base_, d_));
                superTypes_.addAllElts(_page.getAllGenericSuperTypesWrapper(base_,d_));
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (AnaTypeUtil.isPrimitive(base_, _page)) {
                AnaClassArgumentMatching c_ = new AnaClassArgumentMatching(base_);
                for (AnaClassArgumentMatching s: getAllSuperTypes(c_, _page)) {
                    for (String p: s.getNames()) {
                        superTypes_.add(StringExpUtil.getPrettyArrayType(p, d_));
                        superTypes_.addAllElts(_page.getAllGenericSuperTypesWrapper(p,d_));
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            AnaGeneType g_ = _page.getAnaGeneType(id_);
            addTypes(superTypes_, base_, d_, g_);
            for (int d = 1; d <= d_; d++) {
                superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
            }
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
    }

    private static void addTypes(StringList _superTypes, String _base, int _d, AnaGeneType _g) {
        if (_g instanceof RootBlock) {
            addWildCard(_superTypes, _d, (RootBlock)_g);
            for (AnaFormattedRootBlock m: ((RootBlock) _g).getAllGenericSuperTypesInfo()) {
                RootBlock rootBlock_ = m.getRootBlock();
                String formatted_ = m.getFormatted();
                String f_ = format(_g, _base, formatted_);
                addWildCard(_superTypes, _d, rootBlock_);
                _superTypes.add(StringExpUtil.getPrettyArrayType(f_, _d));
            }
        }
        if (_g instanceof StandardType) {
            for (String t: _g.getAllGenericSuperTypes()) {
                String f_ = format(_g, _base, t);
                _superTypes.add(StringExpUtil.getPrettyArrayType(f_, _d));
            }
        }
    }

    private static void addWildCard(StringList _superTypes, int _d, RootBlock _g) {
        _superTypes.add(StringExpUtil.getPrettyArrayType(_g.getWildCardString(), _d));
    }

    public static String wildCardFormatReturn(String _first, String _second, AnalyzedPageEl _page) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        StringList types_ = StringExpUtil.getAllTypes(_first);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        AnaGeneType root_ = _page.getAnaGeneType(className_);
        CustList<TypeVar> typeVar_ = ContextUtil.getParamTypesMapValues(root_);
        String objType_ = _page.getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (TypeVar t: typeVar_) {
                index_++;
                if (StringUtil.quickEq(t.getName(), name_)) {
                    String formatted_ = types_.get(index_+1);
                    //return type, field getting
                    if (StringUtil.quickEq(formatted_, SUB_TYPE)) {
                        return StringExpUtil.getPrettyArrayType(objType_,arr_);
                    }
                    if (formatted_.startsWith(SUB_TYPE)) {
                        return StringExpUtil.getPrettyArrayType(formatted_.substring(SUB_TYPE.length()),arr_);
                    }
                    if (formatted_.startsWith(SUP_TYPE)) {
                        return StringExpUtil.getPrettyArrayType(objType_,arr_);
                    }
                    return StringExpUtil.getPrettyArrayType(formatted_,arr_);
                }
            }
            return objType_;
        }
        if (typeVar_.size() != types_.size() - 1){
            return objType_;
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
    public static String wildCardFormatParam(String _first, String _second, AnalyzedPageEl _page) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        StringList types_ = StringExpUtil.getAllTypes(_first);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        AnaGeneType root_ = _page.getAnaGeneType(className_);
        CustList<TypeVar> typeVar_ = ContextUtil.getParamTypesMapValues(root_);
        String objType_ = _page.getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (TypeVar t: typeVar_) {
                index_++;
                if (StringUtil.quickEq(t.getName(), name_)) {
                    String formatted_ = types_.get(index_+1);
                    //parameters, field affectation
                    if (formatted_.startsWith(SUB_TYPE)) {
                        return "";
                    }
                    if (formatted_.startsWith(SUP_TYPE)) {
                        return StringExpUtil.getPrettyArrayType(formatted_.substring(SUP_TYPE.length()),arr_);
                    }
                    return StringExpUtil.getPrettyArrayType(formatted_,arr_);
                }
            }
            return "";
        }
        if (typeVar_.size() != types_.size() - 1){
            return "";
        }
        StringMap<String> varTypes_ = new StringMap<String>();
        int i_ = IndexConstants.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return StringExpUtil.getWildCardFormattedTypeParam(objType_,_second, varTypes_);
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
                if (StringUtil.isWhitespace(locChar_)) {
                    j_--;
                    continue;
                }
                if (locChar_ != ']') {
                    arr_ = false;
                }
                break;
            }
            if (arr_) {
                j_--;
                while (j_ >= 0) {
                    char locChar_ = _type.charAt(j_);
                    if (StringUtil.isWhitespace(locChar_)) {
                        j_--;
                        continue;
                    }
                    break;
                }
            }
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
        for (String p: StringUtil.splitChars(tr_,'.')) {
            if (p.isEmpty()) {
                continue;
            }
            if (StringExpUtil.isDollarWord(p.trim())) {
                continue;
            }
            return null;
        }
        return tr_;
    }

    public static String check(StringList _errs, String _className, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String res_ = tryGetAllInners(_className, _parts, _inherit, _page);
        if (res_.isEmpty()) {
            int rc_ = _page.getLocalizer().getCurrentLocationIndex();
            FoundErrorInterpret un_ = new FoundErrorInterpret();
            un_.setFileName(_page.getLocalizer().getCurrentFileName());
            un_.setIndexFile(rc_);
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

    public static String tryGetAllInners(String _className, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String realClassName_ = getRealClassName(_className, _parts);
        return getCorrectTemplateAll(realClassName_, _parts, _inherit, _page);
    }

    public static String getRealClassName(String _className, StringList _parts) {
        String realClassName_;
        if (_parts.isEmpty()) {
            realClassName_ = _className;
        } else {
            realClassName_ = StringUtil.concat(_className,"<", StringUtil.join(_parts, ","),">");
        }
        return realClassName_;
    }

    public static String getCorrectTemplateAll(String _realClassName, StringList _parts, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        String id_ = StringExpUtil.getIdFromAllTypes(_realClassName);
        RootBlock g_ = _page.getAnaClassBody(id_);
        if (g_ == null) {
            return "";
        }
        CustList<StringList> bounds_ = g_.getBoundAll();
        return check(_realClassName, _parts, _inherit, _page, g_, bounds_);
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
    public static String tryInferMethod(int _varargOnly, String _owner,
                                        MethodId _candidate, String _staticCall, StringMap<StringList> _vars,
                                        CustList<AnaClassArgumentMatching> _args,
                                        String _returnCandidate, String _returnType,
                                        AnalyzedPageEl _page) {
        AnaGeneType sub_ = _page.getAnaGeneType(_staticCall);
        if (sub_ == null) {
            return "";
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(sub_);
        int sizeVar_ = varTypes_.size();
        StringMap<StringList> inh_ = inhNb(sub_);
        String genericString_ = getGeneNb(sub_);
        String idOwner_ = StringExpUtil.getIdFromAllTypes(_owner);
        AnaGeneType cType_ = _page.getAnaGeneType(idOwner_);
        if (cType_ == null) {
            return "";
        }
        String candidate_ = getFullTypeByBases(sub_, genericString_, idOwner_, _page);
        int allArgsLen_ = _args.size();
        int startOpt_ = allArgsLen_;
        boolean checkOnlyDem_ = true;
        int paramLen_ = _candidate.getParametersTypesLength();
        boolean vararg_ = _candidate.isVararg();
        if (!vararg_) {
            if (paramLen_ != startOpt_) {
                return "";
            }
        } else {
            if (paramLen_ > startOpt_ + 1) {
                return "";
            }
            if (_varargOnly != 0) {
                checkOnlyDem_ = false;
                startOpt_ = paramLen_ - 1;
            }
            if (_varargOnly > 0) {
                if (startOpt_ != _varargOnly - 1) {
                    return "";
                }
            } else if (_varargOnly == 0) {
                if (paramLen_ -1 != startOpt_) {
                    return "";
                }
            }
        }
        CustList<Matching> all_ = new CustList<Matching>();
        String resRet_ = _returnCandidate;
        if (_returnCandidate.isEmpty()) {
            resRet_ = _page.getAliasVoid();
        }
        if (!StringUtil.quickEq(_page.getAliasVoid(),resRet_)&&!_returnType.isEmpty()) {
            Mapping mapRet_ = new Mapping();
            mapRet_.getMapping().putAllMap(inh_);
            mapRet_.getMapping().putAllMap(_vars);
            String arg_ = quickFormat(cType_, candidate_, _returnCandidate);
            mapRet_.setArg(arg_);
            mapRet_.setParam(_returnType);
            if (_candidate.isRetRef()) {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.EQ,mapRet_.getMapping(), _page));
            } else {
                all_.addAllElts(inferOrImplicit(new AnaClassArgumentMatching(arg_),_returnType,MatchingEnum.SUB,mapRet_.getMapping(), _page));
            }
        }
        for (int i = IndexConstants.FIRST_INDEX; i < startOpt_; i++) {
            String wc_ = _candidate.getParametersType(i);
            wc_ = quickFormat(cType_,candidate_,wc_);
            AnaClassArgumentMatching resArg_ = _args.get(i);
            if (_candidate.getParametersRef(i)) {
                Mapping map_ = new Mapping();
                map_.setArg(resArg_);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                map_.setParam(wc_);
                CustList<Matching> cts_ = inferOrImplicit(resArg_,wc_,MatchingEnum.EQ,map_.getMapping(), _page);
                //compare base of arg - param by eq and return eq constraints
                all_.addAllElts(cts_);
                continue;
            }
            if (resArg_.isVariable()) {
                continue;
            }
            Mapping map_ = new Mapping();
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_vars);
            map_.setParam(wc_);
            CustList<Matching> cts_ = inferOrImplicit(resArg_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            all_.addAllElts(cts_);
        }
        if (checkOnlyDem_) {
            return processConstraints(genericString_,all_, sizeVar_,_vars,_page);
        }
        int last_ = paramLen_ - 1;
        if (paramLen_ == allArgsLen_) {
            Mapping map_ = new Mapping();
            String wc_ = _candidate.getParametersType(last_);
            wc_ = quickFormat(cType_,candidate_,wc_);
            AnaClassArgumentMatching resArg_ = _args.last();
            if (_candidate.getParametersRef(last_)) {
                map_.setArg(resArg_);
                map_.getMapping().putAllMap(inh_);
                map_.getMapping().putAllMap(_vars);
                map_.setParam(StringExpUtil.getPrettyArrayType(wc_));
                CustList<Matching> cts_ = inferOrImplicit(resArg_,StringExpUtil.getPrettyArrayType(wc_),MatchingEnum.EQ,map_.getMapping(), _page);
                all_.addAllElts(cts_);
                return processConstraints(genericString_,all_, sizeVar_,_vars,_page);
            }
            map_.setArg(resArg_);
            map_.getMapping().putAllMap(inh_);
            map_.getMapping().putAllMap(_vars);
            String arr_ = StringExpUtil.getPrettyArrayType(wc_);
            map_.setParam(arr_);
            CustList<Matching> cts_ = inferOrImplicit(resArg_,arr_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            CustList<Matching> attempt_ = new CustList<Matching>(all_);
            attempt_.addAllElts(cts_);
            //try infer here
            String tried_ = processConstraints(genericString_, attempt_, sizeVar_,_vars, _page);
            if (!tried_.isEmpty()) {
                return tried_;
            }
            map_.setParam(wc_);
            CustList<Matching> cts2_ = inferOrImplicit(resArg_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //else compare base of arg - param by sub and return eq constraints and roll back previous
            all_.addAllElts(cts2_);
            return processConstraints(genericString_, all_, sizeVar_,_vars, _page);
        }
        Mapping map_ = new Mapping();
        map_.getMapping().putAllMap(inh_);
        map_.getMapping().putAllMap(_vars);
        String wc_ = _candidate.getParametersType(last_);
        wc_ = quickFormat(cType_,candidate_,wc_);
        map_.setParam(wc_);
        for (int i = startOpt_; i < allArgsLen_; i++) {
            AnaClassArgumentMatching a_ = _args.get(i);
            map_.setArg(a_);
            CustList<Matching> cts_ = inferOrImplicit(a_,wc_,MatchingEnum.SUB,map_.getMapping(), _page);
            //compare base of arg - param by sub and return eq constraints
            all_.addAllElts(cts_);
        }
        //try infer here
        return processConstraints(genericString_, all_, sizeVar_,_vars, _page);
    }
    public static String tryGetDeclaredImplicitCast(String _classes, StringMap<String> _varsOwner, String _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        AnaGeneType anaGeneType_ = _page.getAnaGeneType(_arg);
        StringMap<StringList> map_ = inhNb(anaGeneType_);
        map_.addAllEntries(_vars);
        String geneNb_ = getGeneNb(anaGeneType_);
        ClassMethodIdReturn res_ = tryGetDeclaredImplicitCast(_classes, new AnaClassArgumentMatching(geneNb_), _page, map_);
        if (!res_.isFoundMethod()) {
            return null;
        }
        String formPar_ = res_.getId().getConstraints().shiftFirst().first();
        if (isVar(formPar_)) {
            AnaGeneType typePar_ = _page.getAnaGeneType(StringExpUtil.getIdFromAllTypes(formPar_));
            StringMap<StringList> mapTwo_ = inhNb(typePar_);
            mapTwo_.addAllEntries(_vars);
            String geneNbTwo_ = getGeneNb(typePar_);
            CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(typePar_);
            int sizeVar_ = varTypes_.size();
            CustList<Matching> cts_ = inf(res_.getReturnType(),_classes,mapTwo_, _page);
            cts_.addAllElts(inf(geneNb_,formPar_,mapTwo_, _page));
            String formParam_ = processConstraints(geneNbTwo_, cts_, sizeVar_, mapTwo_, _page);
            return tryInfer(_arg,_varsOwner,formParam_,_page);
        }
        return tryInfer(_arg,_varsOwner,formPar_,_page);
    }
    private static CustList<Matching> inf(String _arg, String _par,StringMap<StringList> _inh, AnalyzedPageEl _page) {
        Mapping mapping_ = new Mapping();
        mapping_.setArg(_arg);
        mapping_.setParam(_par);
        mapping_.setMapping(_inh);
        return infer(mapping_,MatchingEnum.SUB,_page);
    }
    public static ClassMethodIdReturn tryGetDeclaredImplicitCast(String _classes, AnaClassArgumentMatching _arg, AnalyzedPageEl _page, StringMap<StringList> _vars) {
        return OperationNode.tryGetDeclaredImplicitCast(_classes, _arg, _page, _vars, new VarsComparer());
    }


    public static StringMap<StringList> inhNb(AnaGeneType _sub) {
        if (_sub == null) {
            return new StringMap<StringList>();
        }
        CustList<StringList> bounds_ = ContextUtil.getBoundAllAll(_sub);
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(_sub);
        int sizeVar_ = varTypes_.size();
        StringList args_ = new StringList();
        for (int i = 0; i < sizeVar_; i++) {
            args_.add("#"+ i);
        }
        StringMap<StringList> inh_ = new StringMap<StringList>();
        String gene_ = _sub.getFullName() +"<"+StringUtil.join(args_,",")+">";
        for (int i = 0; i < sizeVar_; i++) {
            String key_ = Integer.toString(i);
            StringList vs_ = new StringList();
            for (String v: bounds_.get(i)) {
                vs_.add(quickFormat(_sub,gene_,v));
            }
            inh_.addEntry(key_,vs_);
        }
        return inh_;
    }
    public static String getGeneNb(AnaGeneType _sub) {
        if (_sub == null) {
            return "";
        }
        CustList<TypeVar> varTypes_ = ContextUtil.getParamTypesMapValues(_sub);
        int sizeVar_ = varTypes_.size();
        StringList args_ = new StringList();
        for (int i = 0; i < sizeVar_; i++) {
            args_.add("#"+ i);
        }
        if (args_.isEmpty()) {
            return _sub.getFullName();
        }
        return _sub.getFullName() +"<"+StringUtil.join(args_,",")+">";
    }

    public static String tryInfer(String _erased, StringMap<String> _vars, String _declaring, AnalyzedPageEl _page) {
        AnaGeneType g_ = _page.getAnaGeneType(_erased);
        if (g_ == null) {
            return null;
        }
        String idParam_ = StringExpUtil.getIdFromAllTypes(_declaring);
        String gene_ = g_.getGenericString();
        String type_ = "";
        if (!StringUtil.quickEq(idParam_,_erased)) {
            for (String s: g_.getAllGenericSuperTypes()) {
                String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                if (StringUtil.quickEq(idSuper_,idParam_)) {
                    type_ = s;
                }
            }
        } else {
            if (StringUtil.quickEq(_erased, _page.getAliasFct())) {
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
        for (String p: StringExpUtil.getAllTypes(type_).mid(1)) {
            argTypes_.add(p);
        }
        StringList paramTypes_ = new StringList();
        for (String p: StringExpUtil.getAllTypes(_declaring).mid(1)) {
            paramTypes_.add(p);
        }
        if (argTypes_.size() != paramTypes_.size()) {
            return null;
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
                if (StringUtil.quickEq(argLoc_,SUB_TYPE)) {
                    continue;
                }
                boolean seen_ = false;
                for (String s: new StringList(SUB_TYPE,SUP_TYPE,"~")) {
                    if (argLoc_.startsWith(s)) {
                        if (paramLoc_.startsWith(s)) {
                            InferenceConstraints n_ = new InferenceConstraints();
                            n_.setArg(argLoc_.substring(s.length()));
                            n_.setParam(paramLoc_.substring(s.length()));
                            next_.add(n_);
                        }
                        seen_ = true;
                    }
                }
                if (seen_) {
                    continue;
                }
                StringList nArgTypes_ = StringExpUtil.getAllTypes(argLoc_);
                StringList nParamTypes_ = StringExpUtil.getAllTypes(paramLoc_);
                if (nArgTypes_.size() != nParamTypes_.size()) {
                    continue;
                }
                if (!StringUtil.quickEq(nArgTypes_.first(), nParamTypes_.first())) {
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
        for (String p: StringExpUtil.getAllTypes(gene_).mid(1)) {
            multi_.put(p, new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            multi_.put(StringUtil.concat(PREFIX_VAR_TYPE,e.getKey()), new StringList());
        }
        for (EntryCust<String,String> e: _vars.entryList()) {
            feed(multi_,StringUtil.concat(PREFIX_VAR_TYPE,e.getKey()),e.getValue());
        }
        for (InferenceConstraints i: found_) {
            String argLoc_ = i.getArg();
            String paramLoc_ = i.getParam();
            feed(multi_,argLoc_,paramLoc_);
        }
        return tryBuild(_page, gene_, multi_, _page.getCurrentConstraints().getCurrentConstraints());
    }

    private static String tryBuild(AnalyzedPageEl _page, String _gene, StringMap<StringList> _multi, StringMap<StringList> _cts) {
        StringMap<String> vars_ = new StringMap<String>();
        StringList parts_ = new StringList();
        for (EntryCust<String,StringList> e: _multi.entryList()) {
            if (!e.getValue().onlyOneElt()) {
                return null;
            }
            String value_ = e.getValue().first();
            parts_.add(value_);
            vars_.put(e.getKey().substring(1), value_);
        }
        String formattedType_ = StringExpUtil.getQuickFormattedType(_gene, vars_);
        String correct_ = getCorrectTemplateAllAll(formattedType_, parts_, _cts, _page);
        if (correct_.isEmpty()) {
            return null;
        }
        return formattedType_;
    }

    private static void feed(StringMap<StringList> _multi, String _key, String _value) {
        for (EntryCust<String,StringList> e: _multi.entryList()) {
            if (StringUtil.quickEq(e.getKey(),_key)) {
                e.getValue().add(_value);
            }
        }
    }
    public static boolean isReturnCorrect(String _p, String _a, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isPrimitive(_p, _page)) {
            if (!AnaTypeUtil.isPrimitive(_a, _page)) {
                return false;
            }
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
        AnaClassArgumentMatching a_ = _m.getArg();
        AnaClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !AnaTypeUtil.isPrimitive(p_, _page);
        }
        if (AnaTypeUtil.isPrimitive(p_, _page)) {
            Mapping m_ = new Mapping();
            m_.setArg(AnaTypeUtil.toPrimitive(a_, _page.getPrimitiveTypes()));
            m_.setParam(p_);
            m_.setMapping(_m.getMapping());
            return isCorrect(m_, _page);
        }
        return isCorrect(_m, _page);
    }

    public static boolean isCorrect(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
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
                        MappingPairs m_ = getSimpleMapping(a_,p_,generalMapping_, _page);
                        if (m_ == null) {
                            okTree_ = false;
                            break;
                        }
                        for (Matching n: m_.getPairsArgParam()) {
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                if (!StringUtil.quickEq(n.getParam(), n.getArg())) {
                                    okTree_ = false;
                                    break;
                                }
                                continue;
                            }
                            if (StringUtil.quickEq(n.getParam(), n.getArg())) {
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

    public static boolean isCorrectOrNumbersVars(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching a_ = _m.getArg();
        AnaClassArgumentMatching p_ = _m.getParam();
        if (_m.getParam().isVariable()) {
            return false;
        }
        if (_m.getArg().isVariable()) {
            return !AnaTypeUtil.isPrimitive(p_, _page);
        }
        if (AnaTypeUtil.isPrimitive(p_, _page)) {
            Mapping m_ = new Mapping();
            m_.setArg(AnaTypeUtil.toPrimitive(a_, _page.getPrimitiveTypes()));
            m_.setParam(p_);
            m_.setMapping(_m.getMapping());
            return isCorrectVars(m_, _page);
        }
        return isCorrectVars(_m, _page);
    }

    public static boolean isCorrectVars(Mapping _m, AnalyzedPageEl _page) {
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
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
                        MappingPairs m_ = getSimpleMappingVars(a_,p_,generalMapping_, _page);
                        if (m_ == null) {
                            okTree_ = false;
                            break;
                        }
                        for (Matching n: m_.getPairsArgParam()) {
                            String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(n.getParam());
                            String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(n.getArg());
                            if (StringUtil.quickEq(n.getParam(), n.getArg())) {
                                continue;
                            }
                            if (tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                                continue;
                            }
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                StringList foundArgTypes_ = StringExpUtil.getAllTypes(n.getArg());
                                StringList foundParamTypes_ = StringExpUtil.getAllTypes(n.getParam());
                                int len_ = foundArgTypes_.size();
                                if (foundParamTypes_.size() != len_) {
                                    okTree_ = false;
                                    break;
                                }
                                if (len_ > 1) {
                                    if (!StringUtil.quickEq(foundArgTypes_.first(), foundParamTypes_.first())) {
                                        okTree_ = false;
                                        break;
                                    }
                                }
                                if (isVar(n.getParam()) || isVar(n.getArg())) {
                                    new_.add(n);
                                    continue;
                                }
                                okTree_ = false;
                                break;
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

    public static boolean isVar(String _var) {
        int len_ = _var.length();
        int i_ = 0;
        while (i_ < len_) {
            char ch_ = _var.charAt(i_);
            if (ch_ == '#') {
                if (i_ + 1 < len_ && StringExpUtil.isDigit(_var.charAt(i_+1))) {
                    return true;
                }
            }
            i_++;
        }
        return false;
    }
    public static String processConstraints(String _gene,CustList<Matching> _infer, int _max, StringMap<StringList> _vars,AnalyzedPageEl _page) {
        StringMap<StringList> multi_ = processConstraints(_infer, _max);
        if (multi_ == null) {
            return "";
        }
        reduceConstraints(multi_,_vars,_page);
        return StringUtil.nullToEmpty(tryBuild(_page,_gene,multi_, _vars));
    }
    private static void reduceConstraints(StringMap<StringList> _cts, StringMap<StringList> _vars,AnalyzedPageEl _page) {
        for (EntryCust<String,StringList> e: _cts.entryList()) {
            e.setValue(AnaTypeUtil.getSubTypes(e.getValue(),_vars,_page));
        }
    }
    public static StringMap<StringList> processConstraints(CustList<Matching> _infer, int _max) {
        CustList<Matching> list_ = resolveEq(mergeEq(resolveEq(removeDup(_infer), _max)), _max);
        reverseEq(list_);
        return checkIfPresComplete(removeDup(list_),_max);
    }
    private static StringMap<StringList> checkIfPresComplete(CustList<Matching> _infer, int _max) {
        if (_infer == null) {
            return null;
        }
        StringMap<StringList> multi_ = new StringMap<StringList>();
        StringMap<StringList> addon_ = new StringMap<StringList>();
        for (int i = 0; i < _max; i++) {
            multi_.addEntry("#"+i,new StringList());
            addon_.addEntry("#"+i,new StringList());
        }
        CustList<Matching> eqs_ = new CustList<Matching>();
        for (Matching m: _infer) {
            if (m.getMatchEq() != MatchingEnum.EQ) {
                continue;
            }
            eqs_.add(m);
        }
        for (int i = 0; i < _max; i++) {
            StringList cts_ = multi_.getValue(i);
            feedCts(eqs_, i, cts_);
        }
        for (int i = 0; i < _max; i++) {
            if (!multi_.getValue(i).isEmpty()) {
                continue;
            }
            StringList ls_ = addon_.getValue(i);
            feedCts(_infer, i, ls_);
        }
        for (int i = 0; i < _max; i++) {
            StringList cts_ = multi_.getValue(i);
            cts_.addAllElts(addon_.getValue(i));
        }
        return multi_;
    }

    private static void feedCts(CustList<Matching> _list, int _i, StringList _dest) {
        for (Matching m : _list) {
            String resVar_ = m.getParam();
            String value_ = m.getArg();
            String solvedValue_ = "";
            int m_ = -1;
            int nbResVar_ = tryGetUnknownVar(resVar_);
            int nbValue_ = tryGetUnknownVar(value_);
            if (nbResVar_ >= 0) {
                solvedValue_ = value_;
                m_ =nbResVar_;
            } else if (nbValue_ >= 0) {
                solvedValue_ = resVar_;
                m_ = nbValue_;
            }
            if (m_ == _i) {
                _dest.add(solvedValue_);
                break;
            }
        }
    }

    public static CustList<Matching> mergeEq(CustList<Matching> _infer) {
        if (_infer == null) {
            return null;
        }
        int len_ = _infer.size();
        CustList<Matching> l_ = new CustList<Matching>();
        for (int i = 0; i < len_; i++) {
            Matching matching_ = _infer.get(i);
            if (matching_.getMatchEq() == MatchingEnum.EQ) {
                l_.add(matching_);
                continue;
            }
            if (matching_.getMatchEq() == null) {
                continue;
            }
            for (int j = 0; j < len_; j++) {
                if (i == j) {
                    continue;
                }
                Matching other_ = _infer.get(j);
                if (other_.getMatchEq() == MatchingEnum.EQ) {
                    continue;
                }
                if (areCompl(matching_, other_)) {
                    if (areTypePairs(matching_,other_)) {
                        matching_.setMatchEq(MatchingEnum.EQ);
                        other_.setMatchEq(null);
                        break;
                    }
                }
                if (matching_.getMatchEq() == other_.getMatchEq()) {
                    if (areTypeCross(matching_,other_)) {
                        matching_.setMatchEq(MatchingEnum.EQ);
                        other_.setMatchEq(null);
                        break;
                    }
                }
            }
            l_.add(matching_);
        }
        return l_;
    }
    public static CustList<Matching> resolveEq(CustList<Matching> _infer, int _max) {
        if (_infer == null) {
            return null;
        }
        int len_ = _infer.size();
        for (int k = 0; k < _max; k++) {
            for (int i = 0; i < len_; i++) {
                Matching matching_ = _infer.get(i);
                if (matching_.getMatchEq() != MatchingEnum.EQ) {
                    continue;
                }
                String resVar_ = matching_.getParam();
                String value_ = matching_.getArg();
                int nbResVar_ = tryGetUnknownVar(resVar_);
                int nbResValue_ = tryGetUnknownVar(value_);
                if (nbResVar_ == k&& nbResValue_ <0) {
                    for (int j = 0; j < len_; j++) {
                        if (i == j) {
                            continue;
                        }
                        Matching other_ = _infer.get(j);
                        if (other_.getMatchEq() != MatchingEnum.EQ) {
                            continue;
                        }
                        StringMap<String> varTypes_ = new StringMap<String>();
                        varTypes_.addEntry(Integer.toString(k),value_);
                        other_.setParam(StringExpUtil.getQuickFormattedTypeKeep(other_.getParam(), varTypes_));
                        other_.setArg(StringExpUtil.getQuickFormattedTypeKeep(other_.getArg(), varTypes_));
                    }
                    continue;
                }
                if (nbResValue_ == k&& nbResVar_ <0) {
                    for (int j = 0; j < len_; j++) {
                        if (i == j) {
                            continue;
                        }
                        Matching other_ = _infer.get(j);
                        if (other_.getMatchEq() != MatchingEnum.EQ) {
                            continue;
                        }
                        StringMap<String> varTypes_ = new StringMap<String>();
                        varTypes_.addEntry(Integer.toString(k),resVar_);
                        other_.setParam(StringExpUtil.getQuickFormattedTypeKeep(other_.getParam(), varTypes_));
                        other_.setArg(StringExpUtil.getQuickFormattedTypeKeep(other_.getArg(), varTypes_));
                    }
                }
            }
        }
        return _infer;
    }

    public static CustList<Matching> removeDup(CustList<Matching> _infer) {
        if (_infer == null) {
            return null;
        }
        CustList<Matching> l_ = new CustList<Matching>();
        for (Matching m: _infer) {
            boolean add_ = true;
            for (Matching n: l_) {
                if (m.getMatchEq() == MatchingEnum.EQ
                 &&n.getMatchEq() == MatchingEnum.EQ) {
                    if (areTypePairs(m, n)
                    || areTypeCross(m, n)) {
                        add_ = false;
                        break;
                    }
                    continue;
                }
                if (areCompl(m, n)) {
                    if (areTypeCross(m, n)) {
                        add_ = false;
                        break;
                    }
                    continue;
                }
                if (m.getMatchEq() == n.getMatchEq()) {
                    if (areTypePairs(m, n)) {
                        add_ = false;
                        break;
                    }
                }
            }
            if (add_) {
                l_.add(m);
            }
        }
        return l_;
    }

    private static boolean areCompl(Matching _m, Matching _n) {
        return _m.getMatchEq() == MatchingEnum.SUB
                && _n.getMatchEq() == MatchingEnum.SUP
                || _n.getMatchEq() == MatchingEnum.SUB
                && _m.getMatchEq() == MatchingEnum.SUP;
    }

    public static void reverseEq(CustList<Matching> _infer) {
        if (_infer == null) {
            return;
        }
        for (Matching m: _infer) {
            if (m.getMatchEq() == MatchingEnum.EQ) {
                String arg_ = m.getArg();
                String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(arg_);
                if (tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                    m.setArg(m.getParam());
                    m.setParam(arg_);
                }
                continue;
            }
            if (m.getMatchEq() == MatchingEnum.SUP) {
                String arg_ = m.getArg();
                m.setArg(m.getParam());
                m.setParam(arg_);
                m.setMatchEq(MatchingEnum.SUB);
            }
        }
    }
    private static boolean areTypeCross(Matching _m, Matching _n) {
        return StringUtil.quickEq(_m.getArg(), _n.getParam())
                &&StringUtil.quickEq(_m.getParam(), _n.getArg());
    }

    private static boolean areTypePairs(Matching _m, Matching _n) {
        return StringUtil.quickEq(_m.getArg(), _n.getArg())
                &&StringUtil.quickEq(_m.getParam(), _n.getParam());
    }

    public static CustList<Matching> inferOrImplicit(AnaClassArgumentMatching _arg,String _param, MatchingEnum _base, StringMap<StringList> _vars, AnalyzedPageEl _page) {
        String refTypeArgId_ = StringExpUtil.getIdFromAllTypes(_arg.getSingleNameOrEmpty());
        String refTypeParamId_ = StringExpUtil.getIdFromAllTypes(_param);
        AnaGeneType argType_ = _page.getAnaGeneType(refTypeArgId_);
        Mapping map_ = new Mapping();
        map_.setArg(_arg);
        map_.getMapping().putAllMap(_vars);
        map_.setParam(_param);
        if (argType_ != null && !argType_.isSubTypeOf(refTypeParamId_,_page)) {
            ClassMethodIdReturn result_ = tryGetDeclaredImplicitCast(_param, _arg, _page, _vars);
            if (result_.isFoundMethod()) {
                map_.setArg(result_.getReturnType());
            }
        }
        return infer(map_,_base,_page);
    }
    public static CustList<Matching> infer(Mapping _m, MatchingEnum _base, AnalyzedPageEl _page) {
        CustList<Matching> constraints_ = new CustList<Matching>();
        AnaClassArgumentMatching arg_ = _m.getArg();
        AnaClassArgumentMatching param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        //param is for example Tmp<#0>; Tmp<#0,#1> ...
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        for (String p: param_.getNames()) {
            StringList names_ = arg_.getNames();
            for (String a: names_) {
                CustList<Matching> matchs_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setArg(a);
                match_.setParam(p);
                match_.setMatchEq(_base);
                matchs_.add(match_);
                while (true) {
                    CustList<Matching> new_ = new CustList<Matching>();
                    for (Matching m: matchs_) {
                        String a_ = m.getArg();
                        String p_ = m.getParam();
                        MappingPairs m_ = getSimpleInferredMapping(a_,p_,generalMapping_, _page, m.getMatchEq());
                        if (m_ == null) {
                            continue;
                        }
                        for (Matching n: m_.getPairsArgParam()) {
                            if (StringUtil.quickEq(n.getParam(), n.getArg())) {
                                continue;
                            }
                            String baseArrayParamNext_ = StringExpUtil.getQuickComponentBase(n.getParam());
                            String baseArrayArgNext_ = StringExpUtil.getQuickComponentBase(n.getArg());
                            if (tryGetUnknownVar(baseArrayParamNext_) >= 0 || tryGetUnknownVar(baseArrayArgNext_) >= 0) {
                                constraints_.add(n);
                                continue;
                            }
                            if (n.getMatchEq() == MatchingEnum.EQ) {
                                StringList foundArgTypes_ = StringExpUtil.getAllTypes(n.getArg());
                                StringList foundParamTypes_ = StringExpUtil.getAllTypes(n.getParam());
                                int len_ = foundArgTypes_.size();
                                if (foundParamTypes_.size() != len_) {
                                    continue;
                                }
                                if (!StringUtil.quickEq(foundArgTypes_.first(),foundParamTypes_.first())) {
                                    continue;
                                }
                                new_.add(n);
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
                            n_.setMatchEq(_base);
                            new_.add(n_);
                        }
                    }
                    if (new_.isEmpty()) {
                        break;
                    }
                    matchs_ = new_;
                }
            }
        }
        //replace other variable by solved one
        //try replace numbered variables in param type, if incompat => false
        return constraints_;
    }

    private static int tryGetUnknownVar(String _baseArrayArg) {
        int var_ = -1;
        String af_ = "";
        if (_baseArrayArg.startsWith(PREFIX_VAR_TYPE)) {
            af_ = _baseArrayArg.substring(1);
        }
        if (!af_.isEmpty()){
            if (StringExpUtil.isDigit(af_.charAt(0))) {
                var_ = NumberUtil.parseInt(af_);
            }
        }
        return var_;
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
    private static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
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
    private static MappingPairs getSimpleMappingVars(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
            if (tryGetUnknownVar(baseArrayParam_)>=0) {
                MappingPairs pairs_ = new MappingPairs();
                CustList<Matching> pairsArgParam_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setMatchEq(MatchingEnum.SUB);
                match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
                match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
                pairsArgParam_.add(match_);
                pairs_.setPairsArgParam(pairsArgParam_);
                return pairs_;
            }
            return commonTypeVar(dArg_, dParam_, map_);
        }
        return commonTypePair(_arg, _param, _inherit, _page, MatchingEnum.SUB);
    }

    private static MappingPairs commonTypePair(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page, MatchingEnum _sub) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayArg_ = dArg_.getComponent();
        if (tryGetUnknownVar(baseArrayArg_) >= 0) {
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
            MappingPairs pairs_ = new MappingPairs();
            CustList<Matching> pairsArgParam_ = new CustList<Matching>();
            Matching match_ = new Matching();
            match_.setMatchEq(_sub);
            match_.setArg(StringExpUtil.getQuickComponentType(_arg, min_));
            match_.setParam(StringExpUtil.getQuickComponentType(_param, min_));
            pairsArgParam_.add(match_);
            pairs_.setPairsArgParam(pairsArgParam_);
            return pairs_;
        }
        return getCommentMappingPairs(_arg, _param, _inherit, _page);
    }

    private static MappingPairs commonTypeVar(DimComp _dArg, DimComp _dParam, Mapping _map) {
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

    private static MappingPairs getSimpleInferredMapping(String _arg, String _param, StringMap<StringList> _inherit, AnalyzedPageEl _page, MatchingEnum _ct) {
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        if (_ct == MatchingEnum.EQ) {
            if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
                if (_arg.isEmpty()) {
                    return new MappingPairs();
                }
                int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
                if (tryGetUnknownVar(baseArrayParam_)>=0) {
                    MappingPairs pairs_ = new MappingPairs();
                    CustList<Matching> pairsArgParam_ = new CustList<Matching>();
                    Matching match_ = new Matching();
                    match_.setMatchEq(_ct);
                    match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
                    match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
                    pairsArgParam_.add(match_);
                    pairs_.setPairsArgParam(pairsArgParam_);
                    return pairs_;
                }
                return new MappingPairs();
            }
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
            if (tryGetUnknownVar(baseArrayArg_)>=0) {
                MappingPairs pairs_ = new MappingPairs();
                CustList<Matching> pairsArgParam_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setMatchEq(_ct);
                match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
                match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
                pairsArgParam_.add(match_);
                pairs_.setPairsArgParam(pairsArgParam_);
                return pairs_;
            }
            if (dArg_.getDim() != dParam_.getDim()) {
                return null;
            }
            StringList foundArgTypes_ = StringExpUtil.getAllTypes(baseArrayArg_);
            StringList foundParamTypes_ = StringExpUtil.getAllTypes(baseArrayParam_);
            int len_ = foundArgTypes_.size();
            if (foundParamTypes_.size() != len_) {
                return null;
            }
            if (!StringUtil.quickEq(foundArgTypes_.first(),foundParamTypes_.first())) {
                return null;
            }
            MappingPairs pairs_ = new MappingPairs();
            CustList<Matching> pairsArgParam_ = new CustList<Matching>();
            for (int i = 1; i < len_; i++) {
                String cArg_ = foundArgTypes_.get(i);
                String cPar_ = foundParamTypes_.get(i);
                String prArg_ = getPrefix(cArg_);
                String prPar_ = getPrefix(cPar_);
                if (!StringUtil.quickEq(
                        prArg_,
                        prPar_)) {
                    return null;
                }
                Matching match_ = new Matching();
                match_.setMatchEq(_ct);
                match_.setArg(cArg_.substring(prArg_.length()));
                match_.setParam(cPar_.substring(prPar_.length()));
                pairsArgParam_.add(match_);
            }
            pairs_.setPairsArgParam(pairsArgParam_);
            return pairs_;
        }
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                return new MappingPairs();
            }
            int min_ = Math.min(dArg_.getDim(), dParam_.getDim());
            if (tryGetUnknownVar(baseArrayParam_)>=0) {
                MappingPairs pairs_ = new MappingPairs();
                CustList<Matching> pairsArgParam_ = new CustList<Matching>();
                Matching match_ = new Matching();
                match_.setMatchEq(_ct);
                match_.setArg(StringExpUtil.getQuickComponentType(_arg,min_));
                match_.setParam(StringExpUtil.getQuickComponentType(_param,min_));
                pairsArgParam_.add(match_);
                pairs_.setPairsArgParam(pairsArgParam_);
                return pairs_;
            }
            return new MappingPairs();
        }
        return commonTypePair(_arg, _param, _inherit, _page, _ct);
    }
    private static String getPrefix(String _str) {
        for (String s: new StringList(SUB_TYPE,SUP_TYPE,"~")) {
            if (_str.startsWith(s)) {
                return s;
            }
        }
        return "";
    }

    private static MappingPairs getCommentMappingPairs(String _arg, String _param,StringMap<StringList> _inherit, AnalyzedPageEl _page) {
        StringList typesArg_ = StringExpUtil.getAllTypes(_arg);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        Mapping map_ = new Mapping();
        map_.setMapping(_inherit);
        String fct_ = _page.getAliasFct();
        String obj_ = _page.getAliasObject();
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
        String generic_ = getGeneric(_arg, _param, map_, _page);
        if (generic_.isEmpty()) {
            return null;
        }
        return StringExpUtil.newMappingPairs(generic_, typesParam_);
    }

    public static String getFullTypeByBases(AnaGeneType _typeSub, String _subType, String _superType, AnalyzedPageEl _page) {
        if (!correctNbParameters(_typeSub,_subType, _page)) {
            return "";
        }
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

    public static String getOverridingFullTypeByBases(AnaGeneType _typSub, String _subType, String _superType, AnalyzedPageEl _page) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        if (_typSub == null) {
            return "";
        }
        String generic_ = getSuperGeneric(_typSub, 0, idSuperType_, _page);
        return quickFormat(_typSub,_subType, generic_);
    }

    public static String getOverridingFullTypeByBases(AnaGeneType _subType, String _superType, AnalyzedPageEl _page) {
        String geneSubType_ = _subType.getGenericString();
        return getInternOverriding(_subType,_superType, geneSubType_, _page);
    }

    private static String getInternOverriding(AnaGeneType _subType, String _superType, String _geneSubType, AnalyzedPageEl _page) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_geneSubType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringUtil.quickEq(idArg_,idSuperType_)) {
            return _geneSubType;
        }
        String generic_ = getSuperGeneric(_subType, 0, idSuperType_, _page);
        return quickFormat(_subType,_geneSubType, generic_);
    }

    public static String getSuperGeneric(AnaGeneType _subType, int _dim, String _classParam, AnalyzedPageEl _page) {
        String generic_ = "";
        String param_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (_subType instanceof AnnotationBlock) {
            if (StringUtil.quickEq(param_, _page.getAliasAnnotationType())) {
                return StringExpUtil.getPrettyArrayType(param_,_dim);
            }
        }
        for (String g: _subType.getAllGenericSuperTypes()) {
            if (StringUtil.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
                generic_ = g;
                break;
            }
        }
        if (generic_.isEmpty()) {
            return "";
        }
        return StringExpUtil.getPrettyArrayType(generic_,_dim);
    }

    public static String format(AnaGeneType _root, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root,_first);
        return StringExpUtil.getFormattedType(_second, varTypes_);
    }

    public static String quickFormat(AnaGeneType _root, String _first, String _second) {
        StringMap<String> varTypes_ = getVarTypes(_root,_first);
        return StringExpUtil.getQuickFormattedType(_second, varTypes_);
    }

    public static StringMap<String> getVarTypes(AnaGeneType _root, String _className) {
        StringList types_ = StringExpUtil.getAllTypes(_className);
        StringMap<String> varTypes_ = new StringMap<String>();
        if (_root == null) {
            return varTypes_;
        }
        int i_ = IndexConstants.FIRST_INDEX;
        for (String t: _root.getParamTypesValues()) {
            i_++;
            if (!types_.isValidIndex(i_)) {
                return varTypes_;
            }
            String arg_ = types_.get(i_);
            varTypes_.put(t, arg_);
        }
        return varTypes_;
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
        return StringExpUtil.commonCorrectType(_genericClass, compo_, fct_, rep_);
    }

}
