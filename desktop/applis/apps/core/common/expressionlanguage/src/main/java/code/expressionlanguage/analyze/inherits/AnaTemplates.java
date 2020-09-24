package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.analyze.AnalyzedPageEl;
import code.expressionlanguage.analyze.blocks.AnnotationBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaClassArgumentMatching;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.util.ContextUtil;
import code.expressionlanguage.analyze.util.TypeVar;
import code.expressionlanguage.common.*;
import code.expressionlanguage.analyze.errors.custom.FoundErrorInterpret;
import code.expressionlanguage.inherits.*;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.stds.PrimitiveTypes;
import code.expressionlanguage.structs.IntStruct;
import code.util.*;

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

    public static ResultTernary getResultTernary(StringList _first, Argument _firstArg,
                                                 StringList _second, Argument _secondArg,
                                                 StringMap<StringList> _vars,
                                                 AnalyzedPageEl _page) {
        if (StringList.equalsSet(_first, _second)) {
            return ResultTernary.noUnwrap(_first);
        }
        LgNames stds_ = _page.getStandards();
        AnaClassArgumentMatching first_ = new AnaClassArgumentMatching(_first);
        AnaClassArgumentMatching second_ = new AnaClassArgumentMatching(_second);
        if (_page.matchPrimWrap(_first,_second)) {
            return ResultTernary.unwrapRight(_first, first_.getPrimitiveCast(_page));
        }
        if (_page.matchPrimWrap(_second,_first)) {
            return ResultTernary.unwrapLeft(_second, second_.getPrimitiveCast(_page));
        }
        if (StringList.contains(_first, NO_SUB_CLASS)) {
            return ResultTernary.noUnwrap(_page.getTernary(_second));
        }
        if (StringList.contains(_second, NO_SUB_CLASS)) {
            return ResultTernary.noUnwrap(_page.getTernary(_first));
        }
        if (AnaTypeUtil.isPrimitiveOrWrapper(first_, _page) && AnaTypeUtil.isPrimitiveOrWrapper(second_, _page)) {
            String primShort_ = stds_.getAliasPrimShort();
            String primChar_ = stds_.getAliasPrimChar();
            String primByte_ = stds_.getAliasPrimByte();
            String short_ = stds_.getAliasShort();
            String char_ = stds_.getAliasCharacter();
            String byte_ = stds_.getAliasByte();
            if (_secondArg != null && _secondArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_secondArg.getStruct()).intStruct();
                if (StringList.contains(_first, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringList.contains(_first, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringList.contains(_first, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapRight(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
                if (StringList.contains(_first, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringList.contains(_first, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringList.contains(_first, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
            }
            if (_firstArg != null && _firstArg.getStruct() instanceof IntStruct) {
                int value_ = NumParsers.convertToNumber(_firstArg.getStruct()).intStruct();
                if (StringList.contains(_second, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringList.contains(_second, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringList.contains(_second, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapLeft(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
                if (StringList.contains(_second, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primByte_), PrimitiveTypes.BYTE_WRAP);
                }
                if (StringList.contains(_second, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primChar_), PrimitiveTypes.CHAR_WRAP);
                }
                if (StringList.contains(_second, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return ResultTernary.unwrapBoth(new StringList(primShort_), PrimitiveTypes.SHORT_WRAP);
                }
            }
            StringList prOne_ = new StringList();
            StringList prTwo_ = new StringList();
            for (String c: _first) {
                prOne_.add(AnaTypeUtil.toPrimitive(c, stds_));
            }
            for (String c: _second) {
                prTwo_.add(AnaTypeUtil.toPrimitive(c, stds_));
            }
            StringList superTypesFirst_ = getSuperTypesSet(prOne_, _vars, _page);
            StringList superTypesSecond_ = getSuperTypesSet(prTwo_, _vars, _page);
            StringList ints_ = StringList.intersect(superTypesFirst_,superTypesSecond_);
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
        StringList ints_ = StringList.intersect(superTypesFirstAdj_,superTypesSecondAdj_);
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
        LgNames stds_ = _page.getStandards();
        CustList<AnaClassArgumentMatching> gt_ = new CustList<AnaClassArgumentMatching>();
        String name_ = _class.getName();
        StringMap<PrimitiveType> prs_ = stds_.getPrimitiveTypes();
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
        LgNames stds_ = _page.getStandards();
        String obj_ = stds_.getAliasObject();
        Mapping m_ = new Mapping();
        m_.setMapping(_map);
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = StringExpUtil.getIdFromAllTypes(i);
                String baseSub_ = StringExpUtil.getIdFromAllTypes(j);
                DimComp baseArrSup_ = StringExpUtil.getQuickComponentBaseType(baseSup_);
                DimComp baseArrSub_ = StringExpUtil.getQuickComponentBaseType(baseSub_);
                if (StringList.quickEq(baseSup_, baseSub_)) {
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
                        if (StringList.quickEq(a_, baseSup_)) {
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
                if (StringList.quickEq(baseArrSup_.getComponent(), _page.getStandards().getAliasObject())) {
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
                AnaInheritedType pr_ = _page.getStandards().getPrimitiveTypes().getVal(baseArrSub_.getComponent());
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
        LgNames stds_ = _page.getStandards();
        String obj_ = stds_.getAliasObject();
        String bool_ = stds_.getAliasPrimBoolean();
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
                    String i_ = dci_.getComponent();
                    int dLoc_ = dci_.getDim();
                    i_ = StringExpUtil.getIdFromAllTypes(i_);
                    AnaGeneType j_ = _page.getAnaGeneType(i_);
                    for (String u: j_.getAllGenericSuperTypes()) {
                        superTypes_.add(StringExpUtil.getPrettyArrayType(u, d_ + dLoc_));
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (StringList.quickEq(base_, bool_)) {
//                String w_ = PrimitiveTypeUtil.toWrapper(base_, stds_);
//                AnaGeneType g_ = _page.getAnaGeneType(w_);
//                superTypes_.add(StringExpUtil.getPrettyArrayType(w_, d_));
//                for (String t: g_.getAllGenericSuperTypes()) {
//                    superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
//                }
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
//                        String w_ = PrimitiveTypeUtil.toWrapper(p, stds_);
//                        AnaGeneType g_ = _page.getAnaGeneType(w_);
//                        superTypes_.add(StringExpUtil.getPrettyArrayType(w_, d_));
//                        for (String t: g_.getAllGenericSuperTypes()) {
//                            superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
//                        }
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            AnaGeneType g_ = _page.getAnaGeneType(id_);
            for (String t: g_.getAllGenericSuperTypes()) {
                String f_ = format(g_,base_, t);
                superTypes_.add(StringExpUtil.getPrettyArrayType(f_, d_));
            }
            for (int d = 1; d <= d_; d++) {
                superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
            }
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
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
        String objType_ = _page.getStandards().getAliasObject();
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
        int i_ = CustList.FIRST_INDEX;
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
        String objType_ = _page.getStandards().getAliasObject();
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
        int i_ = CustList.FIRST_INDEX;
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

    public static DimComp getComponentForm(String _type) {
        int j_ = _type.length()-1;
        boolean arr_ = true;
        int count_ = 0;
        while (arr_) {
            while (j_ >= 0) {
                char locChar_ = _type.charAt(j_);
                if (Character.isWhitespace(locChar_)) {
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
                    if (Character.isWhitespace(locChar_)) {
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
            res_ = _page.getStandards().getAliasObject();
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
            realClassName_ = StringList.concat(_className,"<", StringList.join(_parts, ","),">");
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
        int len_ = bounds_.size();
        if (len_ != _parts.size()) {
            return "";
        }
        for (int i = 0; i < len_; i++) {
            StringList b_ = bounds_.get(i);
            for (String b:b_) {
                Mapping mapp_ = new Mapping();
                mapp_.setArg(_parts.get(i));
                String param_ = format(g_,_realClassName, b);
                mapp_.setParam(param_);
                mapp_.setMapping(_inherit);
                if (!isCorrect(mapp_, _page)){
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
    public static String tryInfer(String _erased, StringMap<String> _vars, String _declaring, AnalyzedPageEl _page) {
        AnaGeneType g_ = _page.getAnaGeneType(_erased);
        if (g_ == null) {
            return null;
        }
        String idParam_ = StringExpUtil.getIdFromAllTypes(_declaring);
        String gene_ = g_.getGenericString();
        String type_ = "";
        if (!StringList.quickEq(idParam_,_erased)) {
            for (String s: g_.getAllGenericSuperTypes()) {
                String idSuper_ = StringExpUtil.getIdFromAllTypes(s);
                if (StringList.quickEq(idSuper_,idParam_)) {
                    type_ = s;
                }
            }
        } else {
            if (StringList.quickEq(_erased, _page.getStandards().getAliasFct())) {
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
                StringList nArgTypes_ = StringExpUtil.getAllTypes(argLoc_);
                StringList nParamTypes_ = StringExpUtil.getAllTypes(paramLoc_);
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
        for (String p: StringExpUtil.getAllTypes(gene_).mid(1)) {
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
        return StringExpUtil.getQuickFormattedType(gene_,vars_);
    }

    public static boolean isReturnCorrect(String _p, String _a, StringMap<StringList> _mapping, AnalyzedPageEl _page) {
        if (AnaTypeUtil.isPrimitive(_p, _page)) {
            if (!AnaTypeUtil.isPrimitive(_a, _page)) {
                return false;
            }
        }
        String void_ = _page.getStandards().getAliasVoid();
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
            LgNames stds_ = _page.getStandards();
            Mapping m_ = new Mapping();
            m_.setArg(AnaTypeUtil.toPrimitive(a_, stds_));
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

    public static String getGeneric(String _arg, String _param, Mapping map_, AnalyzedPageEl _page) {
        String objType_ = _page.getStandards().getAliasObject();
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        String baseArrayArg_ = dArg_.getComponent();
        String generic_ = "";
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            StringMap<StringList> mapping_ = map_.getMapping();
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
        StringList typesArg_ = StringExpUtil.getAllTypes(_arg);
        StringList typesParam_ = StringExpUtil.getAllTypes(_param);
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = StringExpUtil.getQuickComponentBaseType(_param);
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
        String fct_ = _page.getStandards().getAliasFct();
        String obj_ = _page.getStandards().getAliasObject();
        String idBaseArrayArg_ = StringExpUtil.getIdFromAllTypes(baseArrayArg_);
        String idBaseArrayParam_ = StringExpUtil.getIdFromAllTypes(baseArrayParam_);
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
                return StringExpUtil.newMappingPairsFct(typesArg_, typesParam_, obj_);
            }
            return StringExpUtil.getMappingFctPairs(dArg_, dParam_, baseArrayParam_, obj_);
        }
        if (StringList.quickEq(idBaseArrayParam_, fct_)) {
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
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _subType;
        }
        DimComp dBaseParam_ = StringExpUtil.getQuickComponentBaseType(idSuperType_);
        int dim_ = dBaseParam_.getDim();
        String classParam_ = dBaseParam_.getComponent();
        DimComp dBaseArg_ = StringExpUtil.getQuickComponentBaseType(idArg_);
        String baseArr_ = dBaseArg_.getComponent();
        AnalyzedPageEl page_ = _page;
        if (StringList.quickEq(classParam_, page_.getStandards().getAliasObject())) {
            if (dBaseArg_.getDim() < dim_) {
                return "";
            }
            return _superType;
        }
        if (dBaseArg_.getDim() != dim_) {
            return "";
        }
        if (AnaTypeUtil.isPrimitive(baseArr_,page_)) {
            PrimitiveType pr_ = page_.getStandards().getPrimitiveTypes().getVal(baseArr_);
            if (StringList.contains(pr_.getAllSuperType(page_), classParam_)) {
                return _superType;
            }
            return "";
        }
        if (StringList.quickEq(_subType, page_.getStandards().getAliasVoid())) {
            return "";
        }
        if (StringList.quickEq(_superType, page_.getStandards().getAliasVoid())) {
            return "";
        }
        String generic_ = getSuperGeneric(_typeSub, dim_, classParam_, _page);
        return format(_typeSub,_subType, generic_);
    }

    public static String getOverridingFullTypeByBases(AnaGeneType _typSub, String _subType, String _superType, AnalyzedPageEl _page) {
        String idArg_ = StringExpUtil.getIdFromAllTypes(_subType);
        String idSuperType_ = StringExpUtil.getIdFromAllTypes(_superType);
        if (StringList.quickEq(idArg_,idSuperType_)) {
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
        if (StringList.quickEq(idArg_,idSuperType_)) {
            return _geneSubType;
        }
        String generic_ = getSuperGeneric(_subType, 0, idSuperType_, _page);
        return quickFormat(_subType,_geneSubType, generic_);
    }

    public static String getSuperGeneric(AnaGeneType _subType, int _dim, String _classParam, AnalyzedPageEl _page) {
        String generic_ = "";
        String param_ = StringExpUtil.getIdFromAllTypes(_classParam);
        if (_subType instanceof AnnotationBlock) {
            if (StringList.quickEq(param_, _page.getStandards().getAliasAnnotationType())) {
                return StringExpUtil.getPrettyArrayType(param_,_dim);
            }
        }
        for (String g: _subType.getAllGenericSuperTypes()) {
            if (StringList.quickEq(StringExpUtil.getIdFromAllTypes(g),param_)) {
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
        int i_ = CustList.FIRST_INDEX;
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
            return StringList.quickEq(compo_, _page.getStandards().getAliasVoid());
        }
        String fct_ = _page.getStandards().getAliasFct();
        Ints rep_ = _info.getTypeVarCounts();
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
