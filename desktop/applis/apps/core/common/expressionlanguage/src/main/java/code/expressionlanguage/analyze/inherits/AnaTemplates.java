package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.Argument;
import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.types.AnaPartTypeUtil;
import code.expressionlanguage.common.DimComp;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.InheritedType;
import code.expressionlanguage.common.StringExpUtil;
import code.expressionlanguage.exec.blocks.ExecRootBlock;
import code.expressionlanguage.exec.util.ExecTypeVar;
import code.expressionlanguage.inherits.InferenceConstraints;
import code.expressionlanguage.inherits.MappingPairs;
import code.expressionlanguage.inherits.Matching;
import code.expressionlanguage.inherits.MatchingEnum;
import code.expressionlanguage.inherits.PrimitiveTypeUtil;
import code.expressionlanguage.inherits.Templates;
import code.expressionlanguage.inherits.ClassArgumentMatching;

import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.IntStruct;
import code.util.CustList;
import code.util.EntryCust;
import code.util.StringList;
import code.util.StringMap;

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
                                                 ContextEl _conf) {
        if (StringList.equalsSet(_first, _second)) {
            return new ResultTernary(_first, false, false);
        }
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching first_ = new ClassArgumentMatching(_first);
        ClassArgumentMatching second_ = new ClassArgumentMatching(_second);
        if (first_.isPrimitive(_conf) && second_.isWrapper(_conf) && StringList.equalsSet(new StringList(PrimitiveTypeUtil.toWrapper(first_.getSingleNameOrEmpty(), stds_)), second_.getNames())) {
            return new ResultTernary(_first, false, true);
        }
        if (second_.isPrimitive(_conf) && first_.isWrapper(_conf) && StringList.equalsSet(new StringList(PrimitiveTypeUtil.toWrapper(second_.getSingleNameOrEmpty(), stds_)), first_.getNames())) {
            return new ResultTernary(_second, true, false);
        }
        if (StringList.contains(_first, NO_SUB_CLASS) && !second_.isPrimitive(_conf)) {
            return new ResultTernary(_second, false, false);
        }
        if (StringList.contains(_first, NO_SUB_CLASS)) {
            String w_ = PrimitiveTypeUtil.toWrapper(second_.getSingleNameOrEmpty(), stds_);
            return new ResultTernary(new StringList(w_), false, false);
        }
        if (StringList.contains(_second, NO_SUB_CLASS) && !first_.isPrimitive(_conf)) {
            return new ResultTernary(_first, false, false);
        }
        if (StringList.contains(_second, NO_SUB_CLASS)) {
            String w_ = PrimitiveTypeUtil.toWrapper(first_.getSingleNameOrEmpty(), stds_);
            return new ResultTernary(new StringList(w_), false, false);
        }
        if (PrimitiveTypeUtil.isPrimitiveOrWrapper(first_, _conf) && PrimitiveTypeUtil.isPrimitiveOrWrapper(second_, _conf)) {
            String primShort_ = stds_.getAliasPrimShort();
            String primChar_ = stds_.getAliasPrimChar();
            String primByte_ = stds_.getAliasPrimByte();
            String short_ = stds_.getAliasShort();
            String char_ = stds_.getAliasCharacter();
            String byte_ = stds_.getAliasByte();
            if (StringList.contains(_first, primByte_) || StringList.contains(_first, byte_)) {
                if (StringList.contains(_second, primShort_) || StringList.contains(_second, short_)) {
                    return new ResultTernary(new StringList(primShort_), StringList.contains(_first, byte_), StringList.contains(_second, short_));
                }
            }
            if (StringList.contains(_second, primByte_) || StringList.contains(_second, byte_)) {
                if (StringList.contains(_first, primShort_) || StringList.contains(_first, short_)) {
                    return new ResultTernary(new StringList(primShort_), StringList.contains(_first, short_), StringList.contains(_second, byte_));
                }
            }
            if (_secondArg != null && _secondArg.getStruct() instanceof IntStruct) {
                int value_ = ClassArgumentMatching.convertToNumber(_secondArg.getStruct()).intStruct();
                if (StringList.contains(_first, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), false, true);
                }
                if (StringList.contains(_first, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), false, true);
                }
                if (StringList.contains(_first, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), false, true);
                }
                if (StringList.contains(_first, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, true);
                }
                if (StringList.contains(_first, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, true);
                }
                if (StringList.contains(_first, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, true);
                }
            }
            if (_firstArg != null && _firstArg.getStruct() instanceof IntStruct) {
                int value_ = ClassArgumentMatching.convertToNumber(_firstArg.getStruct()).intStruct();
                if (StringList.contains(_second, primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, false);
                }
                if (StringList.contains(_second, primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, false);
                }
                if (StringList.contains(_second, primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, false);
                }
                if (StringList.contains(_second, byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, true);
                }
                if (StringList.contains(_second, char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, true);
                }
                if (StringList.contains(_second, short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, true);
                }
            }
            StringList prOne_ = new StringList();
            StringList prTwo_ = new StringList();
            for (String c: _first) {
                prOne_.add(PrimitiveTypeUtil.toPrimitive(c, stds_));
            }
            for (String c: _second) {
                prTwo_.add(PrimitiveTypeUtil.toPrimitive(c, stds_));
            }
            StringList superTypesFirst_ = getSuperTypesSet(prOne_, _vars, _conf);
            StringList superTypesSecond_ = getSuperTypesSet(prTwo_, _vars, _conf);
            StringList ints_ = StringList.intersect(superTypesFirst_,superTypesSecond_);
            StringList bases_;
            bases_ = getTernarySubclasses(ints_, _vars, _conf);
            return new ResultTernary(bases_, true, true);
        }
        StringList superTypesFirst_ = getSuperTypesSet(_first, _vars, _conf);
        StringList superTypesSecond_ = getSuperTypesSet(_second, _vars, _conf);
        StringList superTypesFirstAdj_ = new StringList(superTypesFirst_);
        StringList superTypesSecondAdj_ = new StringList(superTypesSecond_);
        for (String f: superTypesFirst_) {
            for (String s: superTypesSecond_) {
                Mapping map_ = new Mapping();
                map_.setArg(s);
                map_.setParam(f);
                map_.setMapping(_vars);
                if (isCorrectOrNumbers(map_, _conf)) {
                    superTypesSecondAdj_.add(f);
                }
                map_ = new Mapping();
                map_.setArg(f);
                map_.setParam(s);
                map_.setMapping(_vars);
                if (isCorrectOrNumbers(map_, _conf)) {
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
        bases_ = getTernarySubclasses(bases_, _vars, _conf);
        StringList out_ = new StringList();
        for (String l: bases_) {
            out_.add(basesGene_.getVal(l));
        }
        out_.removeDuplicates();
        return new ResultTernary(out_, false, false);
    }

    private static CustList<ClassArgumentMatching> getAllSuperTypes(ClassArgumentMatching _class, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        CustList<ClassArgumentMatching> gt_ = new CustList<ClassArgumentMatching>();
        String name_ = _class.getName();
        StringMap<PrimitiveType> prs_ = stds_.getPrimitiveTypes();
        PrimitiveType pr_ = prs_.getVal(name_);
        gt_.add(_class);
        for (String s: pr_.getAllSuperType(_context)) {
            if (!prs_.contains(s)) {
                continue;
            }
            gt_.add(new ClassArgumentMatching(s));
        }
        return gt_;
    }
    static StringList getTernarySubclasses(StringList _classNames, StringMap<StringList> _map, ContextEl _context) {
        StringList types_ = new StringList();
        LgNames stds_ = _context.getStandards();
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
                if (PrimitiveTypeUtil.isPrimitive(baseSup_, _context) && !PrimitiveTypeUtil.isPrimitive(baseSub_, _context)) {
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
                if (StringList.quickEq(baseArrSup_.getComponent(), _context.getStandards().getAliasObject())) {
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
                PrimitiveType pr_ = _context.getStandards().getPrimitiveTypes().getVal(baseArrSub_.getComponent());
                GeneType g_ = _context.getClassBody(baseArrSub_.getComponent());
                InheritedType in_;
                if (pr_ != null) {
                    in_ = pr_;
                } else {
                    in_ = g_;
                }
                if (in_.isSubTypeOf(baseArrSup_.getComponent(),_context)) {
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
    static StringList getSuperTypesSet(StringList _first, StringMap<StringList> _mapping, ContextEl _conf) {
        StringList superTypes_ = new StringList();
        LgNames stds_ = _conf.getStandards();
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
                    GeneType j_ = _conf.getClassBody(i_);
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
                String w_ = PrimitiveTypeUtil.toWrapper(base_, stds_);
                GeneType g_ = _conf.getClassBody(w_);
                superTypes_.add(StringExpUtil.getPrettyArrayType(w_, d_));
                for (String t: g_.getAllGenericSuperTypes()) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(base_, _conf)) {
                ClassArgumentMatching c_ = new ClassArgumentMatching(base_);
                for (ClassArgumentMatching s: getAllSuperTypes(c_, _conf)) {
                    for (String p: s.getNames()) {
                        superTypes_.add(StringExpUtil.getPrettyArrayType(p, d_));
                        String w_ = PrimitiveTypeUtil.toWrapper(p, stds_);
                        GeneType g_ = _conf.getClassBody(w_);
                        superTypes_.add(StringExpUtil.getPrettyArrayType(w_, d_));
                        for (String t: g_.getAllGenericSuperTypes()) {
                            superTypes_.add(StringExpUtil.getPrettyArrayType(t, d_));
                        }
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(StringExpUtil.getPrettyArrayType(obj_, d));
                }
                continue;
            }
            String id_ = StringExpUtil.getIdFromAllTypes(base_);
            GeneType g_ = _conf.getClassBody(id_);
            for (String t: g_.getAllGenericSuperTypes()) {
                String f_ = Templates.format(base_, t, _conf);
                if (f_.isEmpty()) {
                    continue;
                }
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
    public static String wildCardFormatReturn(String _first, String _second, ContextEl _classes) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        StringList types_ = StringExpUtil.getAllTypes(_first);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        CustList<ExecTypeVar> typeVar_ = root_.getParamTypesMapValues();
        String objType_ = _classes.getStandards().getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (ExecTypeVar t: typeVar_) {
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
        for (ExecTypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return StringExpUtil.getWildCardFormattedTypeReturn(_second, varTypes_);
    }
    public static String wildCardFormatParam(String _first, String _second, ContextEl _classes) {
        if (!_second.contains(PREFIX_VAR_TYPE)) {
            return _second;
        }
        DimComp dc_ = StringExpUtil.getQuickComponentBaseType(_second);
        StringList types_ = StringExpUtil.getAllTypes(_first);
        String className_ = StringExpUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        CustList<ExecTypeVar> typeVar_ = root_.getParamTypesMapValues();
        String objType_ = _classes.getStandards().getAliasObject();
        if (dc_.getComponent().startsWith(PREFIX_VAR_TYPE)) {
            int arr_ = dc_.getDim();
            String name_ = _second.substring(PREFIX_VAR_TYPE.length()+arr_);

            int index_ = -1;
            for (ExecTypeVar t: typeVar_) {
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
        for (ExecTypeVar t: typeVar_) {
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
    public static StringList getAllInnerTypes(String _type, ContextEl _an) {
        return getAllInnerTypes(_type,_an.getAnalyzing().getPackagesFound());
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

    public static String getCorrectTemplateAll(String _className, StringList _parts, StringMap<StringList> _inherit, ContextEl _context) {
        String id_ = StringExpUtil.getIdFromAllTypes(_className);
        ExecRootBlock g_ = _context.getClasses().getClassBody(id_);
        CustList<StringList> bounds_ = g_.getBoundAll();
        int len_ = bounds_.size();
        if (len_ != _parts.size()) {
            return "";
        }
        String realClassName_;
        if (_parts.isEmpty()) {
            realClassName_ = _className;
        } else {
            realClassName_ = StringList.concat(_className,"<", StringList.join(_parts, ","),">");
        }
        for (int i = 0; i < len_; i++) {
            StringList b_ = bounds_.get(i);
            for (String b:b_) {
                Mapping mapp_ = new Mapping();
                mapp_.setArg(_parts.get(i));
                String param_ = Templates.format(realClassName_, b, _context);
                mapp_.setParam(param_);
                mapp_.setMapping(_inherit);
                if (!isCorrect(mapp_,_context)){
                    return "";
                }
            }
        }
        return realClassName_;
    }

    public static String tryInfer(String _erased, StringMap<String> _vars, String _declaring, ContextEl _context) {
        GeneType g_ = _context.getClassBody(_erased);
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

    public static String getGeneric(String _arg, String _param, ContextEl _context, Mapping map_) {
        String objType_ = _context.getStandards().getAliasObject();
        DimComp dArg_ = StringExpUtil.getQuickComponentBaseType(_arg);
        String baseArrayArg_ = dArg_.getComponent();
        String generic_ = "";
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            StringMap<StringList> mapping_ = map_.getMapping();
            for (String c: Mapping.getAllUpperBounds(mapping_,baseArrayArg_.substring(PREFIX_VAR_TYPE.length()), objType_)) {
                String arr_ = StringExpUtil.getPrettyArrayType(c,dArg_.getDim());
                generic_ = Templates.getFullTypeByBases(arr_, _param, _context);
                if (!generic_.isEmpty()) {
                    break;
                }
            }
        } else {
            generic_ = Templates.getFullTypeByBases(_arg, _param, _context);
        }
        return generic_;
    }
    private static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, ContextEl _context) {
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
        String fct_ = _context.getStandards().getAliasFct();
        String obj_ = _context.getStandards().getAliasObject();
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
        String generic_ = getGeneric(_arg, _param, _context, map_);
        if (generic_.isEmpty()) {
            return null;
        }
        return StringExpUtil.newMappingPairs(generic_, typesParam_);
    }
}
