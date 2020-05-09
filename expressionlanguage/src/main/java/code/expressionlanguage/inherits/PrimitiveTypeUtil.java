package code.expressionlanguage.inherits;

import code.expressionlanguage.Analyzable;
import code.expressionlanguage.Argument;
import code.expressionlanguage.ExecutableCode;
import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.InheritedType;
import code.expressionlanguage.opers.exec.ExecArrayFieldOperation;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.opers.util.IndexesComparator;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.PrimitiveType;
import code.expressionlanguage.structs.*;
import code.util.*;

public final class PrimitiveTypeUtil {
    public static final String ARR_CLASS = "[";
    private static final String NO_SUB_CLASS = "";
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
        if (first_.isPrimitive(_conf) && second_.isWrapper(_conf) && StringList.equalsSet(new StringList(toWrapper(first_.getSingleNameOrEmpty(), stds_)), second_.getNames())) {
            return new ResultTernary(_first, false, true);
        }
        if (second_.isPrimitive(_conf) && first_.isWrapper(_conf) && StringList.equalsSet(new StringList(toWrapper(second_.getSingleNameOrEmpty(), stds_)), first_.getNames())) {
            return new ResultTernary(_second, true, false);
        }
        if (StringList.contains(_first, NO_SUB_CLASS) && !second_.isPrimitive(_conf)) {
            return new ResultTernary(_second, false, false);
        }
        if (StringList.contains(_first, NO_SUB_CLASS)) {
            String w_ = toWrapper(second_.getSingleNameOrEmpty(), stds_);
            return new ResultTernary(new StringList(w_), false, false);
        }
        if (StringList.contains(_second, NO_SUB_CLASS) && !first_.isPrimitive(_conf)) {
            return new ResultTernary(_first, false, false);
        }
        if (StringList.contains(_second, NO_SUB_CLASS)) {
            String w_ = toWrapper(first_.getSingleNameOrEmpty(), stds_);
            return new ResultTernary(new StringList(w_), false, false);
        }
        if (isPrimitiveOrWrapper(first_, _conf) && isPrimitiveOrWrapper(second_, _conf)) {
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
                prOne_.add(toPrimitive(c, stds_));
            }
            for (String c: _second) {
                prTwo_.add(toPrimitive(c, stds_));
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
                if (Templates.isCorrectOrNumbers(map_, _conf)) {
                    superTypesSecondAdj_.add(f);
                }
                map_ = new Mapping();
                map_.setArg(f);
                map_.setParam(s);
                map_.setMapping(_vars);
                if (Templates.isCorrectOrNumbers(map_, _conf)) {
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
        boolean exit_ = false;
        for (String c: _first) {
            DimComp dc_ = getQuickComponentBaseType(c);
            String base_ = dc_.getComponent();
            String id_ = Templates.getIdFromAllTypes(base_);
            if (!StringList.quickEq(id_, _conf.getStandards().getAliasVoid())) {
                continue;
            }
            exit_ = true;
            superTypes_.add(obj_);
        }
        if (exit_) {
            superTypes_.removeDuplicates();
            return superTypes_;
        }
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
                    for (String u: j_.getAllGenericSuperTypes()) {
                        superTypes_.add(getPrettyArrayType(u, d_ + dLoc_));
                    }
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (StringList.quickEq(base_, bool_)) {
                String w_ = PrimitiveTypeUtil.toWrapper(base_, stds_);
                GeneType g_ = _conf.getClassBody(w_);
                superTypes_.add(getPrettyArrayType(w_, d_));
                for (String t: g_.getAllGenericSuperTypes()) {
                    superTypes_.add(getPrettyArrayType(t, d_));
                }
                for (int d = 1; d <= d_; d++) {
                    superTypes_.add(getPrettyArrayType(obj_, d));
                }
                continue;
            }
            if (PrimitiveTypeUtil.isPrimitive(base_, _conf)) {
                ClassArgumentMatching c_ = new ClassArgumentMatching(base_);
                for (ClassArgumentMatching s: PrimitiveTypeUtil.getAllSuperTypes(c_, _conf)) {
                    for (String p: s.getNames()) {
                        superTypes_.add(getPrettyArrayType(p, d_));
                        String w_ = PrimitiveTypeUtil.toWrapper(p, stds_);
                        GeneType g_ = _conf.getClassBody(w_);
                        superTypes_.add(getPrettyArrayType(w_, d_));
                        for (String t: g_.getAllGenericSuperTypes()) {
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
            for (String t: g_.getAllGenericSuperTypes()) {
                String f_ = Templates.format(base_, t, _conf);
                if (f_.isEmpty()) {
                    continue;
                }
                superTypes_.add(getPrettyArrayType(f_, d_));
            }
            for (int d = 1; d <= d_; d++) {
                superTypes_.add(getPrettyArrayType(obj_, d));
            }
        }
        superTypes_.add(obj_);
        superTypes_.removeDuplicates();
        return superTypes_;
    }
    /** nb calls of getParent - super type - arg object
    use class parent of object
    */
    public static Struct getParent(int _nbAncestors,String _required, Struct _current, ExecutableCode _an) {
        String id_ = Templates.getIdFromAllTypes(_required);
        LgNames lgNames_ = _an.getStandards();
        Argument arg_ = new Argument();
        String cast_ = lgNames_.getAliasCastType();
        if (_current != NullStruct.NULL_VALUE) {
            String className_ = lgNames_.getStructClassName(_current, _an.getContextEl());
            String cl_ = Templates.getIdFromAllTypes(className_);
            DimComp dimReq_ = getQuickComponentBaseType(id_);
            DimComp dimCurrent_ = getQuickComponentBaseType(cl_);
            if (StringList.quickEq(dimReq_.getComponent(),_an.getStandards().getAliasObject())) {
                if (dimReq_.getDim() > dimCurrent_.getDim()) {
                    _an.setException(new ErrorStruct(_an,cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            if (dimReq_.getDim() != dimCurrent_.getDim()) {
                _an.setException(new ErrorStruct(_an,cast_));
                return NullStruct.NULL_VALUE;
            }
            PrimitiveType pr_ = _an.getStandards().getPrimitiveTypes().getVal(dimCurrent_.getComponent());
            GeneType g_ = _an.getClassBody(dimCurrent_.getComponent());
            InheritedType in_ = null;
            if (pr_ != null) {
                in_ = pr_;
            } else {
                if (cl_.startsWith(Templates.ARR_BEG_STRING) || g_.withoutInstance()) {
                    in_ = g_;
                }
            }
            if (in_ != null) {
                if (!in_.isSubTypeOf(dimReq_.getComponent(),_an)) {
                    _an.setException(new ErrorStruct(_an,cast_));
                    return NullStruct.NULL_VALUE;
                }
                return _current;
            }
            arg_.setStruct(_current);
            for (int i = 0; i < _nbAncestors; i++) {
                Struct enc_ = arg_.getStruct();
                Struct par_ = enc_.getParent();
                _an.getContextEl().getInitializingTypeInfos().addSensibleField(enc_, par_);
                arg_.setStruct(par_);
            }
        }
        String npe_ = lgNames_.getAliasNullPe();
        if (arg_.isNull()) {
            _an.setException(new ErrorStruct(_an,npe_));
            return arg_.getStruct();
        }
        Struct current_ = arg_.getStruct();
        String className_ = lgNames_.getStructClassName(current_, _an.getContextEl());
        String cl_ = Templates.getIdFromAllTypes(className_);
        StringList list_ = new StringList();
        GeneType g_ = _an.getClassBody(cl_);
        while (!g_.isSubTypeOf(id_,_an)) {
            if (StringList.contains(list_, cl_)) {
                _an.setException(new ErrorStruct(_an,cast_));
                break;
            }
            list_.add(cl_);
            if (!(current_ instanceof WithParentStruct)) {
                _an.setException(new ErrorStruct(_an,cast_));
                break;
            }
            Struct par_ = current_.getParent();
            _an.getContextEl().getInitializingTypeInfos().addSensibleField(current_, par_);
            className_ = ((WithParentStruct)current_).getParentClassName();
            current_ = par_;
            cl_ = Templates.getIdFromAllTypes(className_);
            g_ = _an.getClassBody(cl_);
        }
        return current_;
    }
    public static boolean primitiveTypeNullObject(String _className, Struct _instance, Analyzable _context) {
        return primitiveTypeNullObject(_className, _instance, _context.getStandards());
    }

    public static boolean primitiveTypeNullObject(String _className, Struct _instance, LgNames _stds) {
        if (!isPrimitive(_className, _stds)) {
            return false;
        }
        return _instance == NullStruct.NULL_VALUE;
    }

    public static boolean isPrimitive(String _className, Analyzable _context) {
        return isPrimitive(_className, _context.getStandards());
    }
    public static boolean isWrapper(String _className, Analyzable _context) {
        return isWrapper(_className, _context.getStandards());
    }
    private static boolean isWrapper(String _className, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            String wrap_ = e.getValue().getWrapper();
            if (StringList.quickEq(wrap_, _className)) {
                return true;
            }
        }
        return false;
    }
    public static boolean isPrimitive(String _className, LgNames _stds) {
        return _stds.getPrimitiveTypes().contains(_className);
    }

    public static ArrayStruct newCustomArray(String _className, Ints _dims, Analyzable _cont) {
        TreeMap<Ints,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Ints,Struct>(new IndexesComparator());
        Struct[] instanceGl_ = new Struct[_dims.first()];
        ArrayStruct output_ = new ArrayStruct(instanceGl_, PrimitiveTypeUtil.getPrettyArrayType(_className, _dims.size()));
        Ints dims_ = new Ints();
        indexesArray_.put(new Ints(), output_);
        int glDim_ = _dims.size();
        int i_ = CustList.FIRST_INDEX;
        Struct defClass_ = defaultClass(_className, _cont);
        for (int i : _dims) {
            dims_.add(i);
            glDim_--;
            if (glDim_ == 0) {
                for (Ints k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, defClass_);
                }
                continue;
            }
            String formattedClass_ = PrimitiveTypeUtil.getPrettyArrayType(_className, glDim_);
            for (Ints k: dims_.getAllIndexes()) {
                Struct[] instance_ = new Struct[_dims.get(i_ + 1)];
                Struct value_ = new ArrayStruct(instance_, formattedClass_);
                indexesArray_.put(k, value_);
            }
            i_++;
        }
        for (EntryCust<Ints,Struct> e: indexesArray_.entryList()) {
            Ints key_ = e.getKey();
            Struct value_ = e.getValue();
            if (key_.isEmpty()) {
                continue;
            }
            Ints ind_ = new Ints(key_);
            ind_.removeLast();
            int lastIndex_ = key_.last();
            Struct str_ = indexesArray_.getVal(ind_);
            Struct[] array_ = ExecArrayFieldOperation.getArray(str_,_cont.getContextEl()).getInstance();
            array_[lastIndex_] = value_;
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
                GeneType subType_ = _context.getClassBody(baseSub_);
                if (subType_.isSubTypeOf(baseSup_,_context)) {
                    sub_ = false;
                    break;
                }
            }
            if (!sub_) {
                continue;
            }
            types_.add(i);
        }
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
                DimComp baseArrSup_ = getQuickComponentBaseType(baseSup_);
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
                if (baseArrSup_.getComponent().startsWith(Templates.PREFIX_VAR_TYPE)) {
                    continue;
                }
                if (isPrimitive(baseArrSub_.getComponent(), _context)) {
                    if (StringList.contains(_context.getStandards().getPrimitiveTypes().getVal(baseArrSub_.getComponent()).getAllPrimSuperType(_context),
                            baseArrSup_.getComponent())){
                        sub_ = false;
                        break;
                    }
                    continue;
                }
                if (_context.getClassBody(baseArrSub_.getComponent()).isSubTypeOf(baseArrSup_.getComponent(),_context)) {
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
        String comp_ = getQuickComponentType(_className);
        if (comp_ == null) {
            return new DimComp(d_, _className);
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

    public static NumberStruct convertToNumber(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj,_context);
        }
        return convertObject(_match,new ByteStruct((byte)0),_context);
    }

    public static Struct convertObject(ClassArgumentMatching _match, Struct _obj, LgNames _context) {
        if (_obj instanceof NumberStruct) {
            return convertObject(_match, (NumberStruct)_obj, _context);
        }
        return _obj;
    }
    private static NumberStruct convertObject(ClassArgumentMatching _match, NumberStruct _obj, LgNames _stds) {
        if (_match.matchClass(_stds.getAliasPrimDouble()) || _match.matchClass(_stds.getAliasDouble())) {
            return new DoubleStruct(_obj.doubleStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimFloat()) || _match.matchClass(_stds.getAliasFloat())) {
            return new FloatStruct(_obj.floatStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimLong()) || _match.matchClass(_stds.getAliasLong())) {
            return new LongStruct(_obj.longStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimInteger()) || _match.matchClass(_stds.getAliasInteger())) {
            return new IntStruct(_obj.intStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimShort()) || _match.matchClass(_stds.getAliasShort())) {
            return new ShortStruct(_obj.shortStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimByte()) || _match.matchClass(_stds.getAliasByte())) {
            return new ByteStruct(_obj.byteStruct());
        }
        if (_match.matchClass(_stds.getAliasPrimChar()) || _match.matchClass(_stds.getAliasCharacter())) {
            return new CharStruct((char)_obj.intStruct());
        }
        return _obj;
    }
    public static Struct unwrapObject(String _match, Struct _obj, LgNames _stds) {
        return convertObject(new ClassArgumentMatching(_match), _obj, _stds);
    }

    public static int cmpTypes(String _one, String _two, Analyzable _context) {
        GeneType one_ = _context.getClassBody(_one);
        GeneType two_ = _context.getClassBody(_two);
        if (two_.isSubTypeOf(_one,_context)) {
            return CustList.SWAP_SORT;
        }
        if (one_.isSubTypeOf(_two,_context)) {
            return CustList.NO_SWAP_SORT;
        }
        return CustList.EQ_CMP;
    }

    private static CustList<ClassArgumentMatching> getAllSuperTypes(ClassArgumentMatching _class, Analyzable _context) {
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

    public static int getOrderClass(String _class, Analyzable _context) {
        return getOrderClass(_class, _context.getStandards());
    }
    private static int getOrderClass(String _class, LgNames _stds) {
        return getOrderClass(new ClassArgumentMatching(_class), _stds);
    }
    public static int getOrderClass(ClassArgumentMatching _class, Analyzable _context) {
        return getOrderClass(_class, _context.getStandards());
    }
    private static int getOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, _stds);
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
    private static int getIntOrderClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching class_ = toPrimitive(_class, _stds);
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
    private static boolean isPrimitiveOrWrapper(String _className, LgNames _stds) {
        if (isPrimitive(_className, _stds)) {
            return true;
        }
        return isWrapper(_className, _stds);
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class, Analyzable _context) {
        return isPureNumberClass(_class, _context.getStandards());
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class, LgNames _stds) {
        ClassArgumentMatching out_ = toPrimitive(_class, _stds);
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
        return out_.matchClass(_stds.getAliasPrimByte());
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, Analyzable _context) {
        return toPrimitive(_class, _context.getStandards());
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, LgNames _stds) {
        for (String w: _class.getNames()) {
            for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
                if (StringList.quickEq(e.getValue().getWrapper(), w)) {
                    return new ClassArgumentMatching(e.getKey());
                }
            }
        }
        return _class;
    }
    public static String toPrimitive(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getValue().getWrapper(), _class)) {
                return e.getKey();
            }
        }
        return _class;
    }
    static String toWrapper(String _class, LgNames _stds) {
        for (EntryCust<String, PrimitiveType> e: _stds.getPrimitiveTypes().entryList()) {
            if (StringList.quickEq(e.getKey(), _class)) {
                return e.getValue().getWrapper();
            }
        }
        return _class;
    }
    public static Struct defaultClass(String _element, Analyzable _context) {
        if (isPrimitive(_element, _context)) {
            return defaultValue(_element, _context);
        }
        return NullStruct.NULL_VALUE;
    }

    public static Struct defaultValue(String _class, Analyzable _context) {
        return defaultValue(_class, _context.getStandards());
    }

    private static Struct defaultValue(String _class, LgNames _stds) {
        if (isPrimitive(_class, _stds)) {
            if (StringList.quickEq(_class, _stds.getAliasPrimBoolean())) {
                return BooleanStruct.of(false);
            }
            return convert(_class, _stds);
        }
        return NullStruct.NULL_VALUE;
    }
    private static Struct convert(String _toClass, LgNames _stds) {
        ClassArgumentMatching class_ = new ClassArgumentMatching(_toClass);
        ClassArgumentMatching prim_ = toPrimitive(class_, _stds);
        if (prim_.matchClass(_stds.getAliasPrimDouble())) {
            return new DoubleStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimFloat())) {
            return new FloatStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            return new LongStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            return new IntStruct(0);
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            return new CharStruct((char)0);
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            return new ShortStruct((short)0);
        }
        return new ByteStruct((byte)0);
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
