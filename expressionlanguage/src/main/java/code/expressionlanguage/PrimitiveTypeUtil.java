package code.expressionlanguage;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.AnnotationBlock;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignableFrom;
import code.expressionlanguage.opers.util.BooleanStruct;
import code.expressionlanguage.opers.util.ByteStruct;
import code.expressionlanguage.opers.util.CharStruct;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.ClassMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.DoubleStruct;
import code.expressionlanguage.opers.util.FloatStruct;
import code.expressionlanguage.opers.util.IndexesComparator;
import code.expressionlanguage.opers.util.IntStruct;
import code.expressionlanguage.opers.util.LongStruct;
import code.expressionlanguage.opers.util.NullStruct;
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public final class PrimitiveTypeUtil {
    public static final String NO_SUB_CLASS = "";
    public static final String ARR_CLASS = "[";
    private static final byte DOUBLE_CASTING = 7;
    private static final byte FLOAT_CASTING = 6;
    private static final byte LONG_CASTING = 5;
    private static final byte INT_CASTING = 4;
    private static final byte CHAR_CASTING = 3;
    private static final byte SHORT_CASTING = 2;
    private static final byte BYTE_CASTING = 1;
    private PrimitiveTypeUtil() {
    }

    public static ResultTernary getResultTernary(StringList _first, Argument _firstArg,
            StringList _second, Argument _secondArg,
            StringMap<StringList> _vars,
            Analyzable _conf) {
        if (StringList.equalsSet(_first, _second)) {
            return new ResultTernary(_first, false, false);
        }
        LgNames stds_ = _conf.getStandards();
        ClassArgumentMatching first_ = new ClassArgumentMatching(_first);
        ClassArgumentMatching second_ = new ClassArgumentMatching(_second);
        if (first_.isPrimitive(_conf) && second_.isWrapper(_conf) && StringList.equalsSet(toWrapper(first_, true, stds_).getNames(), second_.getNames())) {
            return new ResultTernary(_first, false, true);
        }
        if (second_.isPrimitive(_conf) && first_.isWrapper(_conf) && StringList.equalsSet(toWrapper(second_, true, stds_).getNames(), first_.getNames())) {
            return new ResultTernary(_second, true, false);
        }
        if (_first.containsStr(NO_SUB_CLASS) && !second_.isPrimitive(_conf)) {
            return new ResultTernary(_second, false, false);
        }
        if (_first.containsStr(NO_SUB_CLASS)) {
            StringList w_ = toWrapper(second_, true, stds_).getNames();
            return new ResultTernary(w_, false, false);
        }
        if (_second.containsStr(NO_SUB_CLASS) && !first_.isPrimitive(_conf)) {
            return new ResultTernary(_first, false, false);
        }
        if (_second.containsStr(NO_SUB_CLASS)) {
            StringList w_ = toWrapper(first_, true, stds_).getNames();
            return new ResultTernary(w_, false, false);
        }
        if (isPrimitiveOrWrapper(first_, _conf) && isPrimitiveOrWrapper(second_, _conf)) {
            String primShort_ = stds_.getAliasPrimShort();
            String primChar_ = stds_.getAliasPrimChar();
            String primByte_ = stds_.getAliasPrimByte();
            String short_ = stds_.getAliasShort();
            String char_ = stds_.getAliasCharacter();
            String byte_ = stds_.getAliasByte();
            if (_first.containsStr(primByte_) || _first.containsStr(byte_)) {
                if (_second.containsStr(primShort_) || _second.containsStr(short_)) {
                    return new ResultTernary(new StringList(primShort_), _first.containsStr(byte_), _second.containsStr(short_));
                }
            }
            if (_second.containsStr(primByte_) || _second.containsStr(byte_)) {
                if (_first.containsStr(primShort_) || _first.containsStr(short_)) {
                    return new ResultTernary(new StringList(primShort_), _first.containsStr(short_), _second.containsStr(byte_));
                }
            }
            if (_secondArg != null && _secondArg.getObject() instanceof Integer) {
                int value_ = (Integer) _secondArg.getObject();
                if (_first.containsStr(primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), false, true);
                }
                if (_first.containsStr(primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), false, true);
                }
                if (_first.containsStr(primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), false, true);
                }
                if (_first.containsStr(byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, true);
                }
                if (_first.containsStr(char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, true);
                }
                if (_first.containsStr(short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, true);
                }
            }
            if (_firstArg != null && _firstArg.getObject() instanceof Integer) {
                int value_ = (Integer) _firstArg.getObject();
                if (_second.containsStr(primByte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, false);
                }
                if (_second.containsStr(primChar_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, false);
                }
                if (_second.containsStr(primShort_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, false);
                }
                if (_second.containsStr(byte_) && value_ >= Byte.MIN_VALUE && value_ <= Byte.MAX_VALUE) {
                    return new ResultTernary(new StringList(primByte_), true, true);
                }
                if (_second.containsStr(char_) && value_ >= Character.MIN_VALUE && value_ <= Character.MAX_VALUE) {
                    return new ResultTernary(new StringList(primChar_), true, true);
                }
                if (_second.containsStr(short_) && value_ >= Short.MIN_VALUE && value_ <= Short.MAX_VALUE) {
                    return new ResultTernary(new StringList(primShort_), true, true);
                }
            }
            StringList prOne_ = new StringList();
            StringList prTwo_ = new StringList();
            for (String c: _first) {
                prOne_.add(toPrimitive(c, true, stds_));
            }
            for (String c: _second) {
                prTwo_.add(toPrimitive(c, true, stds_));
            }
            StringList superTypesFirst_ = getSuperTypesSet(prOne_, _vars, _conf);
            StringList superTypesSecond_ = getSuperTypesSet(prTwo_, _vars, _conf);
            StringList ints_ = superTypesFirst_.intersect(superTypesSecond_);
            StringList bases_;
            bases_ = getTernarySubclasses(ints_, _vars, _conf);
            return new ResultTernary(bases_, true, true);
        }
        StringList superTypesFirst_ = getSuperTypesSet(_first, _vars, _conf);
        StringList superTypesSecond_ = getSuperTypesSet(_second, _vars, _conf);
        StringList superTypesFirstAdj_ = new StringList(superTypesFirst_);
        StringList superTypesSecondAdj_ = new StringList(superTypesSecond_);
        for (String f: superTypesFirstAdj_) {
            for (String s: superTypesSecondAdj_) {
                Mapping map_ = new Mapping();
                map_.setArg(s);
                map_.setParam(f);
                map_.setMapping(_vars);
                if (Templates.isCorrect(map_, _conf)) {
                    superTypesSecondAdj_.add(f);
                }
                map_ = new Mapping();
                map_.setArg(f);
                map_.setParam(s);
                map_.setMapping(_vars);
                if (Templates.isCorrect(map_, _conf)) {
                    superTypesFirstAdj_.add(s);
                }
            }
        }
        superTypesSecondAdj_.removeDuplicates();
        superTypesFirstAdj_.removeDuplicates();
        StringList ints_ = superTypesFirstAdj_.intersect(superTypesSecondAdj_);
        StringMap<String> basesGene_ = new StringMap<String>();
        StringList bases_ = new StringList();
        for (String l: ints_) {
            String id_ = Templates.getIdFromAllTypes(l);
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
    static StringList getSuperTypesSet(StringList _first, StringMap<StringList> _mapping, Analyzable _conf) {
        StringList superTypes_ = new StringList();
        LgNames stds_ = _conf.getStandards();
        String obj_ = stds_.getAliasObject();
        String bool_ = stds_.getAliasPrimBoolean();
        for (String c: _first) {
            DimComp dc_ = getQuickComponentBaseType(c);
            String base_ = dc_.getComponent();
            int d_ = dc_.getDim();
            superTypes_.add(c);
            if (base_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                String ex_ = base_.substring(Templates.PREFIX_VAR_TYPE.length());
                for (String t: Mapping.getAllBounds(_mapping, ex_, obj_)) {
                    superTypes_.add(getPrettyArrayType(t, d_));
                    if (t.startsWith(Templates.PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    DimComp dci_ = getQuickComponentBaseType(t);
                    String i_ = dci_.getComponent();
                    int dLoc_ = dci_.getDim();
                    i_ = Templates.getIdFromAllTypes(i_);
                    GeneType j_ = _conf.getClassBody(i_);
                    for (String u: TypeUtil.getAllGenericSuperTypes(j_, _conf)) {
                        superTypes_.add(getPrettyArrayType(u, d_ + dLoc_));
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (StringList.quickEq(base_, bool_)) {
                String w_ = PrimitiveTypeUtil.toWrapper(base_, true, stds_);
                GeneType g_ = _conf.getClassBody(w_);
                superTypes_.add(getPrettyArrayType(w_, d_));
                for (String t: TypeUtil.getAllGenericSuperTypes(g_, _conf)) {
                    superTypes_.add(getPrettyArrayType(t, d_));
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(base_, _conf)) {
                ClassArgumentMatching c_ = new ClassArgumentMatching(base_);
                for (ClassArgumentMatching s: PrimitiveTypeUtil.getOrdersGreaterEqThan(c_, _conf)) {
                    for (String p: s.getNames()) {
                        superTypes_.add(getPrettyArrayType(p, d_));
                        String w_ = PrimitiveTypeUtil.toWrapper(p, true, stds_);
                        GeneType g_ = _conf.getClassBody(w_);
                        superTypes_.add(getPrettyArrayType(w_, d_));
                        for (String t: TypeUtil.getAllGenericSuperTypes(g_, _conf)) {
                            superTypes_.add(getPrettyArrayType(t, d_));
                        }
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(getPrettyArrayType(obj_, d));
                }
                continue;
            }
            String id_ = Templates.getIdFromAllTypes(base_);
            GeneType g_ = _conf.getClassBody(id_);
            for (String t: TypeUtil.getAllGenericSuperTypes(g_, _conf)) {
                superTypes_.add(getPrettyArrayType(t, d_));
            }
            for (int d = 1; d <= d_; d++) {
                superTypes_.add(getPrettyArrayType(obj_, d));
            }
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
    }
    public static Struct getParent(int _nbAncestors,String _required, Struct _current, ExecutableCode _an) {
        String id_ = Templates.getIdFromAllTypes(_required);
        Argument arg_ = new Argument(_current);
        for (int i = 0; i < _nbAncestors; i++) {
            arg_.setStruct(arg_.getStruct().getParent());
        }
        LgNames lgNames_ = _an.getStandards();
        String npe_ = lgNames_.getAliasNullPe();
        String cast_ = lgNames_.getAliasCast();
        if (arg_.isNull()) {
            _an.setException(new StdStruct(new CustomError(_an.joinPages()),npe_));
            return arg_.getStruct();
        }
        Struct current_ = arg_.getStruct();
        String className_ = current_.getClassName(_an);
        String cl_ = Templates.getIdFromAllTypes(className_);
        while (!canBeUseAsArgument(id_, cl_, _an)) {
            Struct par_ = current_.getParent();
            if (par_.isNull()) {
                _an.setException(new StdStruct(new CustomError(_an.joinPages()),cast_));
                break;
            }
            current_ = par_;
            className_ = current_.getClassName(_an);
            cl_ = Templates.getIdFromAllTypes(className_);
        }
        return current_;
    }
    public static String toNumberString(Number _nb) {
        if (_nb instanceof Double || _nb instanceof Float) {
            return Double.toString(_nb.doubleValue());
        }
        return Long.toString(_nb.longValue());
    }
    public static boolean primitiveTypeNullObject(String _className, Struct _instance, ExecutableCode _context) {
        return primitiveTypeNullObject(_className, _instance, _context.getStandards());
    }

    public static boolean primitiveTypeNullObject(String _className, Struct _instance, LgNames _stds) {
        if (!isPrimitive(_className, _stds)) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasIterable())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasIteratorType())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnum())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnumParam())) {
            return false;
        }
        return _instance.isNull();
    }

    public static boolean isExistentPrimitive(String _className, Analyzable _context) {
        return isExistentPrimitive(_className, _context.getStandards());
    }
    public static boolean isExistentPrimitive(String _className, LgNames _stds) {
        ClassArgumentMatching prim_ = toPrimitive(new ClassArgumentMatching(_className), true, _stds);
        if (prim_.matchClass(_stds.getAliasPrimBoolean())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimDouble())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimFloat())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimByte())) {
            return true;
        }
        return false;
    }
    public static boolean isPrimitive(String _className, Analyzable _context) {
        return isPrimitive(_className, _context.getStandards());
    }
    public static boolean isWrapper(String _className, Analyzable _context) {
        return isWrapper(_className, _context.getStandards());
    }
    public static boolean isWrapper(String _className, LgNames _stds) {
        if (_className.startsWith(_stds.getAliasIterable())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasIteratorType())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnum())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnumParam())) {
            return false;
        }
        return toPrimitive(new ClassArgumentMatching(_className), false, _stds) != null;
    }
    public static boolean isPrimitive(String _className, LgNames _stds) {
        if (_className.startsWith(_stds.getAliasIterable())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasIteratorType())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnum())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnumParam())) {
            return false;
        }
        return toWrapper(new ClassArgumentMatching(_className), false, _stds) != null;
    }

    public static ArrayStruct newCustomArray(String _className, Numbers<Integer> _dims, Analyzable _cont) {
        TreeMap<Numbers<Integer>,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Numbers<Integer>,Struct>(new IndexesComparator());
        Struct[] instanceGl_ = new Struct[_dims.first()];
        ArrayStruct output_ = new ArrayStruct(instanceGl_, PrimitiveTypeUtil.getPrettyArrayType(_className, _dims.size()));
        Numbers<Integer> dims_ = new Numbers<Integer>();
        indexesArray_.put(new Numbers<Integer>(), output_);
        int glDim_ = _dims.size();
        int i_ = CustList.FIRST_INDEX;
        Struct defClass_ = StdStruct.defaultClass(_className, _cont);
        for (int i : _dims) {
            dims_.add(i);
            glDim_--;
            if (glDim_ == 0) {
                for (Numbers<Integer> k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, defClass_);
                }
                continue;
            }
            String formattedClass_ = PrimitiveTypeUtil.getPrettyArrayType(_className, glDim_);
            for (Numbers<Integer> k: dims_.getAllIndexes()) {
                Struct[] instance_ = new Struct[_dims.get(i_ + 1)];
                Struct value_ = new ArrayStruct(instance_, formattedClass_);
                indexesArray_.put(k, value_);
            }
            i_++;
        }
        for (EntryCust<Numbers<Integer>,Struct> e: indexesArray_.entryList()) {
            Numbers<Integer> key_ = e.getKey();
            Struct value_ = e.getValue();
            if (key_.isEmpty()) {
                continue;
            }
            Numbers<Integer> ind_ = new Numbers<Integer>(key_);
            ind_.removeLast();
            int lastIndex_ = key_.last();
            Struct str_ = indexesArray_.getVal(ind_);
            if (str_ instanceof ArrayStruct) {
                Struct[] array_ = ((ArrayStruct)str_).getInstance();
                array_[lastIndex_] = value_;
            }
        }
        return output_;
    }
    /** Only "object" classes are used as arguments */
    public static StringList getSubclasses(StringList _classNames, Analyzable _context) {
        StringList types_ = new StringList();
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = Templates.getIdFromAllTypes(i);
                String baseSub_ = Templates.getIdFromAllTypes(j);
                if (StringList.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                if (canBeUseAsArgument(baseSup_, baseSub_, _context)) {
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

    static StringList getTernarySubclasses(StringList _classNames, StringMap<StringList> _map, Analyzable _context) {
        StringList types_ = new StringList();
        LgNames stds_ = _context.getStandards();
        String obj_ = stds_.getAliasObject();
        Mapping m_ = new Mapping();
        m_.setMapping(_map);
        for (String i: _classNames) {
            boolean sub_ = true;
            for (String j: _classNames) {
                String baseSup_ = Templates.getIdFromAllTypes(i);
                String baseSub_ = Templates.getIdFromAllTypes(j);
                DimComp baseArrSub_ = getQuickComponentBaseType(baseSub_);
                if (StringList.quickEq(baseSup_, baseSub_)) {
                    continue;
                }
                if (isPrimitive(baseSup_, _context) && !isPrimitive(baseSub_, _context)) {
                    continue;
                }
                int dimSup_ = baseArrSub_.getDim();
                if (baseArrSub_.getComponent().startsWith(Templates.PREFIX_VAR_TYPE)) {
                    boolean inh_ = false;
                    String b_ = baseArrSub_.getComponent().substring(Templates.PREFIX_VAR_TYPE.length());
                    for (String u: Mapping.getAllBounds(_map, b_, obj_)) {
                        String a_ = getPrettyArrayType(u, dimSup_);
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
                if (baseSup_.startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (canBeUseAsArgument(baseSup_, baseSub_, _context)) {
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
    public static String getSubslass(StringList _classNames, StringMap<StringList> _vars, Analyzable _classes) {
        boolean hasPrim_ = false;
        boolean hasObj_ = false;
        LgNames stds_ = _classes.getStandards();
        String voidType_ = stds_.getAliasVoid();
        for (String i: _classNames) {
            if (isPrimitive(i, _classes)) {
                hasPrim_ = true;
            } else {
                hasObj_ = true;
            }
        }
        if (hasPrim_ && hasObj_) {
            return NO_SUB_CLASS;
        }
        Mapping mapping_ = new Mapping();
        mapping_.getMapping().putAllMap(_vars);
        for (String i: _classNames) {
            boolean sub_ = true;
            if (StringList.quickEq(i, voidType_)) {
                for (String j: _classNames) {
                    if (!StringList.quickEq(i, j)) {
                        sub_ = false;
                        break;
                    }
                }
            } else {
                mapping_.setParam(i);
                for (String j: _classNames) {
                    mapping_.setArg(j);
                    if (!Templates.isCorrect(mapping_, _classes)) {
                        sub_ = false;
                        break;
                    }
                }
            }
            if (sub_) {
                return i;
            }
        }
        return NO_SUB_CLASS;
    }
    public static String getPrettyArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = CustList.FIRST_INDEX; i < _nb; i++) {
            cl_ = getPrettyArrayType(cl_);
        }
        return cl_;
    }
    public static String getPrettyArrayType(String _className) {
        return StringList.concat(ARR_CLASS,_className);
    }
    /**Custom classes*/
    public static DimComp getQuickComponentBaseType(String _className) {
        int d_ = 0;
        String className_ = _className;
        String comp_ = getQuickComponentType(className_);
        if (comp_ == null) {
            return new DimComp(d_, className_);
        }
        d_++;
        while (true) {
            String res_ = getQuickComponentType(comp_);
            if (res_ == null) {
                return new DimComp(d_, comp_);
            }
            d_++;
            comp_ = res_;
        }
    }

    static boolean isArrayAssignable(String _arrArg, String _arrParam, Analyzable _classes) {
        Classes classes_ = _classes.getClasses();
        LgNames stds_ = _classes.getStandards();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrParam);
        String p_ = dPar_.getComponent();
        if (StringList.quickEq(p_, stds_.getAliasObject())) {
            if (dPar_.getDim() > dArg_.getDim()) {
                return false;
            }
            return true;
        }
        if (dPar_.getDim() != dArg_.getDim()) {
            return false;
        }
        RootBlock clArgBl_ = classes_.getClassBody(a_);
        if (clArgBl_ instanceof AnnotationBlock) {
            if (StringList.quickEq(p_, stds_.getAliasAnnotation())) {
                return true;
            }
        }
        if (clArgBl_.getAllSuperTypes().containsObj(p_)) {
            return true;
        }
        if (StringList.quickEq(p_, a_)) {
            return true;
        }
        return false;
    }

    public static ClassArgumentMatching getPrettyArrayType(ClassArgumentMatching _className) {
        StringList cl_ = new StringList();
        for (String c: _className.getNames()) {
            cl_.add(PrimitiveTypeUtil.getPrettyArrayType(c));
        }
        return new ClassArgumentMatching(cl_);
    }

    public static ClassArgumentMatching getQuickComponentType(ClassArgumentMatching _className) {
        StringList cl_ = new StringList();
        for (String c: _className.getNames()) {
            String res_ = PrimitiveTypeUtil.getQuickComponentType(c);
            if (res_ == null) {
                continue;
            }
            cl_.add(res_);
        }
        return new ClassArgumentMatching(cl_);
    }
    public static String getQuickComponentType(String _className) {
        return getQuickComponentType(_className,1);
    }
    public static String getQuickComponentType(String _className, int _nb) {
        String className_ = _className;
        for (int i = 0; i < _nb; i++) {
            if (!className_.startsWith(ARR_CLASS)) {
                return null;
            }
            className_ = className_.substring(1);
        }
        return className_;
    }

    public static Struct convertObject(ClassArgumentMatching _match, Struct _obj, Analyzable _context) {
        return convertObject(_match, _obj, _context.getStandards());
    }
    public static Struct convertObject(ClassArgumentMatching _match, Struct _obj, LgNames _stds) {
        Object obj_ = _obj.getInstance();
        if (_match.matchClass(_stds.getAliasPrimDouble()) || _match.matchClass(_stds.getAliasDouble())) {
            return new DoubleStruct(((Number)obj_).doubleValue());
        }
        if (_match.matchClass(_stds.getAliasPrimFloat()) || _match.matchClass(_stds.getAliasFloat())) {
            return new FloatStruct(((Number)obj_).floatValue());
        }
        if (_match.matchClass(_stds.getAliasPrimLong()) || _match.matchClass(_stds.getAliasLong())) {
            return new LongStruct(((Number)obj_).longValue());
        }
        if (_match.matchClass(_stds.getAliasPrimInteger()) || _match.matchClass(_stds.getAliasInteger())) {
            return new IntStruct(((Number)obj_).intValue());
        }
        if (_match.matchClass(_stds.getAliasPrimShort()) || _match.matchClass(_stds.getAliasShort())) {
            return new ShortStruct(((Number)obj_).shortValue());
        }
        if (_match.matchClass(_stds.getAliasPrimByte()) || _match.matchClass(_stds.getAliasByte())) {
            return new ByteStruct(((Number)obj_).byteValue());
        }
        if (_match.matchClass(_stds.getAliasPrimChar()) || _match.matchClass(_stds.getAliasCharacter())) {
            return new CharStruct(((Character)obj_).charValue());
        }
        return _obj;
    }
    public static Struct unwrapObject(String _match, Struct _obj, LgNames _stds) {
        Object obj_ = _obj.getInstance();
        if (StringList.quickEq(_match,_stds.getAliasPrimDouble())) {
            if (obj_ instanceof Character) {
                return new DoubleStruct(((Character)obj_).charValue());
            }
            return new DoubleStruct(((Number)obj_).doubleValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimFloat())) {
            if (obj_ instanceof Character) {
                return new FloatStruct(((Character)obj_).charValue());
            }
            return new FloatStruct(((Number)obj_).floatValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimLong())) {
            if (obj_ instanceof Character) {
                return new LongStruct(((Character)obj_).charValue());
            }
            return new LongStruct(((Number)obj_).longValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimInteger())) {
            if (obj_ instanceof Character) {
                return new IntStruct(((Character)obj_).charValue());
            }
            return new IntStruct(((Number)obj_).intValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimShort())) {
            if (obj_ instanceof Character) {
                return new ShortStruct((short) ((Character)obj_).charValue());
            }
            return new ShortStruct(((Number)obj_).shortValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimByte())) {
            if (obj_ instanceof Character) {
                return new ByteStruct((byte) ((Character)obj_).charValue());
            }
            return new ByteStruct(((Number)obj_).byteValue());
        }
        if (StringList.quickEq(_match,_stds.getAliasPrimChar())) {
            if (obj_ instanceof Character) {
                return new CharStruct(((Character)obj_).charValue());
            }
            return new CharStruct((char) ((Number)obj_).longValue());
        }
        return _obj;
    }

    public static boolean canBeUseAsArgument(ClassArgumentMatching _param, ClassArgumentMatching _arg, Analyzable _context) {
        for (String p: _param.getNames()) {
            boolean ok_ = false;
            for (String a: _arg.getNames()) {
                if (canBeUseAsArgument(p,a,_context)) {
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
    public static boolean canBeUseAsArgument(String _param, String _arg, Analyzable _context) {
        LgNames stds_ = _context.getStandards();
        if (StringList.quickEq(_param, stds_.getAliasVoid())) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null || _arg.isEmpty()) {
            if (param_.isPrimitive(_context)) {
                return false;
            }
            return true;
        }
        if (StringList.quickEq(_arg, stds_.getAliasVoid())) {
            return false;
        }
        AssignableFrom a_ = isAssignableFromCust(_param, _arg, _context);
        if (a_ == AssignableFrom.YES) {
            return true;
        }
        return false;
    }

    static AssignableFrom isAssignableFromCust(String _param,String _arg, Analyzable _classes) {
        Classes classes_ = _classes.getClasses();
        LgNames stds_ = _classes.getStandards();
        if (StringList.quickEq(_param, stds_.getAliasObject())) {
            return AssignableFrom.YES;
        }
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String p_ = dPar_.getComponent();
        RootBlock clParBl_ = classes_.getClassBody(p_);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        String a_ = dArg_.getComponent();
        RootBlock clArgBl_ = classes_.getClassBody(a_);
        if (clArgBl_ != null) {
            if (clParBl_ == null && !StringList.quickEq(p_, stds_.getAliasObject())) {
                if (clArgBl_ instanceof AnnotationBlock) {
                    if (!StringList.quickEq(p_, stds_.getAliasAnnotation())) {
                        return AssignableFrom.NO;
                    }
                } else {
                    return AssignableFrom.NO;
                }
            }
            if (dArg_.getDim() > 0 && dPar_.getDim() > 0) {
                if (isArrayAssignable(_arg, _param, _classes)) {
                    return AssignableFrom.YES;
                }
                return AssignableFrom.NO;
            }
            if (dArg_.getDim() != dPar_.getDim()) {
                return AssignableFrom.NO;
            }
            if (clArgBl_ instanceof AnnotationBlock) {
                if (StringList.quickEq(p_, stds_.getAliasAnnotation())) {
                    return AssignableFrom.YES;
                }
            }
            if (StringList.quickEq(p_, a_)) {
                return AssignableFrom.YES;
            }
            if (clArgBl_.getAllSuperTypes().containsObj(p_)) {
                return AssignableFrom.YES;
            }
            return AssignableFrom.NO;
        }
        if (clParBl_ != null) {
            return AssignableFrom.NO;
        }
        if (LgNames.canBeUseAsArgument(_param, _arg, _classes.getStandards())) {
            return AssignableFrom.YES;
        }
        return AssignableFrom.NO;
    }

    public static CustList<ClassArgumentMatching> getOrdersGreaterEqThan(ClassArgumentMatching _class, Analyzable _context) {
        return getOrdersGreaterEqThan(_class, _context.getStandards());
    }
    public static CustList<ClassArgumentMatching> getOrdersGreaterEqThan(ClassArgumentMatching _class, LgNames _stds) {
        CustList<ClassArgumentMatching> primitives_ = new CustList<ClassArgumentMatching>();
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimDouble()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimFloat()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimLong()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimInteger()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimChar()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimShort()));
        primitives_.add(new ClassArgumentMatching(_stds.getAliasPrimByte()));
        CustList<ClassArgumentMatching> gt_ = new CustList<ClassArgumentMatching>();
        for (ClassArgumentMatching p: primitives_) {
            if (getOrderClass(p, _stds) >= getOrderClass(_class, _stds)) {
                gt_.add(p);
            }
        }
        return gt_;
    }

    public static int getOrderClass(String _class, Analyzable _context) {
        return getOrderClass(_class, _context.getStandards());
    }
    public static int getOrderClass(String _class, LgNames _stds) {
        return getOrderClass(new ClassArgumentMatching(_class), _stds);
    }
    public static int getOrderClass(ClassArgumentMatching _class, Analyzable _context) {
        return getOrderClass(_class, _context.getStandards());
    }
    public static int getOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, true, _stds);
        if (class_.matchClass(_stds.getAliasPrimDouble())) {
            return DOUBLE_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimFloat())) {
            return FLOAT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimLong())) {
            return LONG_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimInteger())) {
            return INT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimChar())) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimShort())) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimByte())) {
            return BYTE_CASTING;
        }
        return 0;
    }
    public static int getIntOrderClass(ClassArgumentMatching _class, Analyzable _context) {
        return getIntOrderClass(_class, _context.getStandards());
    }
    public static int getIntOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, true, _stds);
        if (class_.matchClass(_stds.getAliasPrimLong())) {
            return LONG_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimInteger())) {
            return INT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimChar())) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimShort())) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(_stds.getAliasPrimByte())) {
            return BYTE_CASTING;
        }
        return 0;
    }
    public static boolean isPrimitiveOrWrapper(ClassArgumentMatching _className, Analyzable _context) {
        for (String c: _className.getNames()) {
            if (isPrimitiveOrWrapper(c, _context.getStandards())) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPrimitiveOrWrapper(String _className, Analyzable _context) {
        return isPrimitiveOrWrapper(_className, _context.getStandards());
    }
    public static boolean isPrimitiveOrWrapper(String _className, LgNames _stds) {
        if (_className.startsWith(_stds.getAliasIterable())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasIteratorType())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnum())) {
            return false;
        }
        if (_className.startsWith(_stds.getAliasEnumParam())) {
            return false;
        }
        if (isPrimitive(_className, _stds)) {
            return true;
        }
        return toPrimitive(new ClassArgumentMatching(_className), false, _stds) != null;
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class, Analyzable _context) {
        return isPureNumberClass(_class, _context.getStandards());
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching out_ = toPrimitive(_class, true, _stds);
        if (out_.matchClass(_stds.getAliasPrimDouble())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimFloat())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimLong())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimInteger())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimChar())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimByte())) {
            return true;
        }
        return false;
    }
    public static ClassMatching toPrimitive(ClassMatching _class, LgNames _stds) {
        String cl_ = _class.getClassName();
        return new ClassMatching(toPrimitive(cl_, true, _stds)); 
    }
    public static ClassMatching toPrimitive(ClassMatching _class, Analyzable _context) {
        return toPrimitive(_class, _context.getStandards());
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, boolean _id, Analyzable _context) {
        return toPrimitive(_class, _id, _context.getStandards());
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, boolean _id, LgNames _stds) {
        if (_class.matchClass(_stds.getAliasBoolean())) {
            return new ClassArgumentMatching(_stds.getAliasPrimBoolean());
        }
        if (_class.matchClass(_stds.getAliasDouble())) {
            return new ClassArgumentMatching(_stds.getAliasPrimDouble());
        }
        if (_class.matchClass(_stds.getAliasFloat())) {
            return new ClassArgumentMatching(_stds.getAliasPrimFloat());
        }
        if (_class.matchClass(_stds.getAliasLong())) {
            return new ClassArgumentMatching(_stds.getAliasPrimLong());
        }
        if (_class.matchClass(_stds.getAliasInteger())) {
            return new ClassArgumentMatching(_stds.getAliasPrimInteger());
        }
        if (_class.matchClass(_stds.getAliasShort())) {
            return new ClassArgumentMatching(_stds.getAliasPrimShort());
        }
        if (_class.matchClass(_stds.getAliasByte())) {
            return new ClassArgumentMatching(_stds.getAliasPrimByte());
        }
        if (_class.matchClass(_stds.getAliasCharacter())) {
            return new ClassArgumentMatching(_stds.getAliasPrimChar());
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static String toPrimitive(String _class, boolean _id, LgNames _stds) {
        if (StringList.quickEq(_class,_stds.getAliasBoolean())) {
            return _stds.getAliasPrimBoolean();
        }
        if (StringList.quickEq(_class,_stds.getAliasDouble())) {
            return _stds.getAliasPrimDouble();
        }
        if (StringList.quickEq(_class,_stds.getAliasFloat())) {
            return _stds.getAliasPrimFloat();
        }
        if (StringList.quickEq(_class,_stds.getAliasLong())) {
            return _stds.getAliasPrimLong();
        }
        if (StringList.quickEq(_class,_stds.getAliasInteger())) {
            return _stds.getAliasPrimInteger();
        }
        if (StringList.quickEq(_class,_stds.getAliasShort())) {
            return _stds.getAliasPrimShort();
        }
        if (StringList.quickEq(_class,_stds.getAliasByte())) {
            return _stds.getAliasPrimByte();
        }
        if (StringList.quickEq(_class,_stds.getAliasCharacter())) {
            return _stds.getAliasPrimChar();
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    static ClassArgumentMatching toWrapper(ClassArgumentMatching _class, boolean _id, LgNames _stds) {
        if (_class.matchClass(_stds.getAliasPrimBoolean())) {
            return new ClassArgumentMatching(_stds.getAliasBoolean());
        }
        if (_class.matchClass(_stds.getAliasPrimDouble())) {
            return new ClassArgumentMatching(_stds.getAliasDouble());
        }
        if (_class.matchClass(_stds.getAliasPrimFloat())) {
            return new ClassArgumentMatching(_stds.getAliasFloat());
        }
        if (_class.matchClass(_stds.getAliasPrimLong())) {
            return new ClassArgumentMatching(_stds.getAliasLong());
        }
        if (_class.matchClass(_stds.getAliasPrimInteger())) {
            return new ClassArgumentMatching(_stds.getAliasInteger());
        }
        if (_class.matchClass(_stds.getAliasPrimChar())) {
            return new ClassArgumentMatching(_stds.getAliasCharacter());
        }
        if (_class.matchClass(_stds.getAliasPrimShort())) {
            return new ClassArgumentMatching(_stds.getAliasShort());
        }
        if (_class.matchClass(_stds.getAliasPrimByte())) {
            return new ClassArgumentMatching(_stds.getAliasByte());
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static String toWrapper(String _class, boolean _id, LgNames _stds) {
        if (StringList.quickEq(_class,_stds.getAliasPrimBoolean())) {
            return _stds.getAliasBoolean();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimDouble())) {
            return _stds.getAliasDouble();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimFloat())) {
            return _stds.getAliasFloat();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimLong())) {
            return _stds.getAliasLong();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimInteger())) {
            return _stds.getAliasInteger();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimShort())) {
            return _stds.getAliasShort();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimByte())) {
            return _stds.getAliasByte();
        }
        if (StringList.quickEq(_class,_stds.getAliasPrimChar())) {
            return _stds.getAliasCharacter();
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    public static Argument defaultValue(Block _block, Argument _global, ContextEl _context) {
        if (_block instanceof MethodBlock) {
            MethodBlock m_ = (MethodBlock) _block;
            Argument a_ = new Argument();
            a_.setStruct(StdStruct.defaultClass(m_.getImportedReturnType(), _context));
            return a_;
        }
        if (_block instanceof ConstructorBlock) {
            return _global;
        }
        return Argument.createVoid();
    }

    public static Struct defaultValue(String _class, Analyzable _context) {
        return defaultValue(_class, _context.getStandards());
    }

    public static Struct defaultValue(String _class, LgNames _stds) {
        if (isPrimitive(_class, _stds)) {
            if (StringList.quickEq(_class, _stds.getAliasPrimBoolean())) {
                return new BooleanStruct(false);
            }
            return convert(_class, 0, _stds);
        }
        return NullStruct.NULL_VALUE;
    }
    public static Struct convert(String _toClass, Object _arg, ContextEl _context) {
        return convert(_toClass, _arg, _context.getStandards());
    }
    public static Struct convert(String _toClass, Object _arg, LgNames _stds) {
        ClassArgumentMatching class_ = new ClassArgumentMatching(_toClass);
        ClassArgumentMatching prim_ = toPrimitive(class_, true, _stds);
        if (prim_.matchClass(_stds.getAliasPrimDouble())) {
            if (_arg instanceof Character) {
                return new DoubleStruct(((Character)_arg).charValue());
            }
            return new DoubleStruct(((Number)_arg).doubleValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimFloat())) {
            if (_arg instanceof Character) {
                return new FloatStruct(((Character)_arg).charValue());
            }
            return new FloatStruct(((Number)_arg).floatValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            if (_arg instanceof Character) {
                return new LongStruct(((Character)_arg).charValue());
            }
            return new LongStruct(((Number)_arg).longValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            if (_arg instanceof Character) {
                return new IntStruct(((Character)_arg).charValue());
            }
            return new IntStruct(((Number)_arg).intValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            if (_arg instanceof Character) {
                return new CharStruct((Character)_arg);
            }
            return new CharStruct((char)((Number)_arg).longValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            if (_arg instanceof Character) {
                return new ShortStruct((short)((Character)_arg).charValue());
            }
            return new ShortStruct(((Number)_arg).shortValue());
        }
        if (prim_.matchClass(_stds.getAliasPrimByte())) {
            if (_arg instanceof Character) {
                return new ByteStruct((byte)((Character)_arg).charValue());
            }
            return new ByteStruct(((Number)_arg).byteValue());
        }
        return NullStruct.NULL_VALUE;
    }
    public static boolean isIntegerType(ClassArgumentMatching _class, ContextEl _context) {
        return isIntegerType(_class, _context.getStandards());
    }
    public static boolean isIntegerType(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching prim_ = toPrimitive(_class, true, _stds);
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        if (prim_.matchClass(_stds.getAliasPrimByte())) {
            return true;
        }
        return false;
    }

    public static boolean isPrimitive(ClassArgumentMatching _clMatchLeft,
            Analyzable _conf) {
        for (String n: _clMatchLeft.getNames()) {
            if (isPrimitive(n, _conf)) {
                return true;
            }
        }
        return false;
    }
}
