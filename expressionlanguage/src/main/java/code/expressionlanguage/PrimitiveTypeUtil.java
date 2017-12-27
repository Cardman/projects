package code.expressionlanguage;
import code.expressionlanguage.methods.Block;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.ConstructorBlock;
import code.expressionlanguage.methods.MethodBlock;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.opers.util.ArrayStruct;
import code.expressionlanguage.opers.util.AssignableFrom;
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
import code.expressionlanguage.opers.util.ShortStruct;
import code.expressionlanguage.opers.util.StdStruct;
import code.expressionlanguage.opers.util.Struct;
import code.expressionlanguage.stds.LgNames;
import code.serialize.ConstClasses;
import code.util.CustList;
import code.util.EntryCust;
import code.util.Numbers;
import code.util.StringList;
import code.util.StringMap;
import code.util.TreeMap;

public final class PrimitiveTypeUtil {
    public static final String NO_SUB_CLASS = "";
    public static final String PRIM  = "$";
    public static final String PRIM_BOOLEAN = "$boolean";
    public static final String PRIM_CHAR = "$char";
    public static final String PRIM_BYTE = "$byte";
    public static final String PRIM_SHORT = "$short";
    public static final String PRIM_INT = "$int";
    public static final String PRIM_LONG = "$long";
    public static final String PRIM_FLOAT = "$float";
    public static final String PRIM_DOUBLE = "$double";
    private static final String ARR_CLASS = "[";
    private static final String BOOLEAN = "Z";
    private static final String CHAR = "C";
    private static final String BYTE = "B";
    private static final String SHORT = "S";
    private static final String INTEGER = "I";
    private static final String LONG = "J";
    private static final String FLOAT = "F";
    private static final String DOUBLE = "D";
    private static final String OBJECT = "L";
    private static final String SUFFIX_INSTANCE = ";";
    private static final byte DOUBLE_CASTING = 7;
    private static final byte FLOAT_CASTING = 6;
    private static final byte LONG_CASTING = 5;
    private static final byte INT_CASTING = 4;
    private static final byte CHAR_CASTING = 3;
    private static final byte SHORT_CASTING = 2;
    private static final byte BYTE_CASTING = 1;
    private PrimitiveTypeUtil() {
    }

    public static Class<?> getSingleNativeClass(String _className) {
        String base_ = StringList.getAllTypes(_className).first();
        return ConstClasses.classForNameNotInit(getArrayClass(base_));
    }

    public static boolean primitiveTypeNullObject(String _className, Struct _instance, ContextEl _context) {
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
    public static Class<?> getPrimitiveClass(String _className) {
        if (!isPrimitive(_className)) {
            return null;
        }
        return ConstClasses.getPrimitiveClass(_className.substring(PRIM.length()));
    }

    public static boolean isExistentPrimitive(String _className, ContextEl _context) {
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
    public static boolean isPrimitive(String _className, ContextEl _context) {
        return isPrimitive(_className, _context.getStandards());
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
    public static boolean isPrimitive(String _className) {
        if (_className.startsWith(PredefinedClasses.ITERABLE)) {
            return false;
        }
        if (_className.startsWith(PredefinedClasses.ITERATOR)) {
            return false;
        }
        if (_className.startsWith(PredefinedClasses.ENUM)) {
            return false;
        }
        if (_className.startsWith(PredefinedClasses.ENUM_PARAM)) {
            return false;
        }
        return toWrapper(new ClassArgumentMatching(_className), false) != null;
    }

    public static Struct newCustomArray(String _className, Numbers<Integer> _dims, ContextEl _cont) {
        TreeMap<Numbers<Integer>,Struct> indexesArray_;
        indexesArray_ = new TreeMap<Numbers<Integer>,Struct>(new IndexesComparator());
        Struct[] instanceGl_ = new Struct[_dims.first()];
        Struct output_ = new ArrayStruct(instanceGl_, PrimitiveTypeUtil.getPrettyArrayType(_className, _dims.size()));
        Numbers<Integer> dims_ = new Numbers<Integer>();
        indexesArray_.put(new Numbers<Integer>(), output_);
        int glDim_ = _dims.size();
        int i_ = CustList.FIRST_INDEX;
        for (int i : _dims) {
            dims_.add(i);
            glDim_--;
            if (glDim_ == 0) {
                for (Numbers<Integer> k: dims_.getAllIndexes()) {
                    indexesArray_.put(k, StdStruct.defaultClass(_className, _cont));
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
    public static StringList getSubclasses(StringList _classNames, ContextEl _context) {
        StringList types_ = new StringList();
        LgNames stds_ = _context.getStandards();
        String voidType_ = stds_.getAliasVoid();
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
                for (String j: _classNames) {
                    String baseSup_ = StringList.getAllTypes(i).first();
                    String baseSub_ = StringList.getAllTypes(j).first();
                    if (StringList.quickEq(baseSup_, baseSub_)) {
                        continue;
                    }
                    if (canBeUseAsArgument(baseSup_, baseSub_, _context)) {
                        sub_ = false;
                        break;
                    }
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
    public static String getSubslass(StringList _classNames, StringMap<StringList> _vars, ContextEl _classes) {
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
    private static String getArrayType(String _className, int _nb) {
        String cl_ = _className;
        for (int i = CustList.FIRST_INDEX; i < _nb; i++) {
            cl_ = getArrayType(cl_);
        }
        return cl_;
    }
    private static String getArrayType(String _className) {
        if (StringList.quickEq(_className, PRIM_BOOLEAN)) {
            return ARR_CLASS+BOOLEAN;
        }
        if (StringList.quickEq(_className, PRIM_BYTE)) {
            return ARR_CLASS+BYTE;
        }
        if (StringList.quickEq(_className, PRIM_SHORT)) {
            return ARR_CLASS+SHORT;
        }
        if (StringList.quickEq(_className, PRIM_CHAR)) {
            return ARR_CLASS+CHAR;
        }
        if (StringList.quickEq(_className, PRIM_INT)) {
            return ARR_CLASS+INTEGER;
        }
        if (StringList.quickEq(_className, PRIM_LONG)) {
            return ARR_CLASS+LONG;
        }
        if (StringList.quickEq(_className, PRIM_FLOAT)) {
            return ARR_CLASS+FLOAT;
        }
        if (StringList.quickEq(_className, PRIM_DOUBLE)) {
            return ARR_CLASS+DOUBLE;
        }
        if (_className.startsWith(ARR_CLASS)) {
            return ARR_CLASS+_className;
        }
        return ARR_CLASS+OBJECT+_className+SUFFIX_INSTANCE;
    }

    public static String getPrettyArrayType(String _className) {
        return ARR_CLASS+_className;
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

    /**Native classes*/
    private static DimComp getComponentBaseType(String _className) {
        int d_ = 0;
        String className_ = _className;
        String comp_ = getComponentType(className_);
        if (comp_ == null) {
            return new DimComp(d_, className_);
        }
        d_++;
        while (true) {
            String res_ = getComponentType(comp_);
            if (res_ == null) {
                return new DimComp(d_, comp_);
            }
            d_++;
            comp_ = res_;
        }
    }

    static boolean isArrayAssignable(String _arrArg, String _arrParam, ContextEl _classes) {
        Classes classes_ = _classes.getClasses();
        LgNames stds_ = _classes.getStandards();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrArg);
        String a_ = dArg_.getComponent();
        DimComp dPar_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arrParam);
        String className_ = dPar_.getComponent();
        if (StringList.quickEq(className_, stds_.getAliasObject())) {
            if (dPar_.getDim() > dArg_.getDim()) {
                return false;
            }
            return true;
        }
        if (dPar_.getDim() != dArg_.getDim()) {
            return false;
        }
        RootBlock clArgBl_ = classes_.getClassBody(a_);
        if (clArgBl_.getAllSuperTypes().containsObj(className_)) {
            return true;
        }
        if (StringList.quickEq(className_, a_)) {
            return true;
        }
        return false;
    }

    public static String getQuickComponentType(String _className) {
        if (!_className.startsWith(ARR_CLASS)) {
            return null;
        }
        return _className.substring(CustList.SECOND_INDEX);
    }

    private static String getComponentType(String _className) {
        if (!_className.startsWith(ARR_CLASS)) {
            return null;
        }
        String compon_ = _className.substring(CustList.SECOND_INDEX);
        if (StringList.quickEq(compon_, BOOLEAN)) {
            return PRIM_BOOLEAN;
        }
        if (StringList.quickEq(compon_, BYTE)) {
            return PRIM_BYTE;
        }
        if (StringList.quickEq(compon_, SHORT)) {
            return PRIM_SHORT;
        }
        if (StringList.quickEq(compon_, CHAR)) {
            return PRIM_CHAR;
        }
        if (StringList.quickEq(compon_, INTEGER)) {
            return PRIM_INT;
        }
        if (StringList.quickEq(compon_, LONG)) {
            return PRIM_LONG;
        }
        if (StringList.quickEq(compon_, FLOAT)) {
            return PRIM_FLOAT;
        }
        if (StringList.quickEq(compon_, DOUBLE)) {
            return PRIM_DOUBLE;
        }
        if (!compon_.startsWith(ARR_CLASS)) {
            compon_ = compon_.substring(CustList.SECOND_INDEX, compon_.length() - 1);
        }
        return compon_;
    }
    public static Struct convertObject(ClassArgumentMatching _match, Struct _obj, ContextEl _context) {
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

    public static String getAliasArrayClass(Class<?> _class) {
        String className_ = _class.getName();
        if (_class.isPrimitive()) {
            className_ = PRIM + className_;
        }
        DimComp d_ = getComponentBaseType(className_);
        String compo_ = d_.getComponent();
        return getPrettyArrayType(compo_, d_.getDim());
    }

    private static String getArrayClass(String _class) {
        DimComp d_ = getQuickComponentBaseType(_class);
        String compo_ = d_.getComponent();
        return getArrayType(compo_, d_.getDim());
    }
    public static boolean canBeUseAsArgument(String _param, String _arg, ContextEl _context) {
        LgNames stds_ = _context.getStandards();
        if (StringList.quickEq(_param, stds_.getAliasVoid())) {
            return false;
        }
        ClassArgumentMatching param_ = new ClassArgumentMatching(_param);
        if (_arg == null) {
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
        if (a_ == AssignableFrom.NO) {
            return false;
        }
        ClassArgumentMatching arg_ = new ClassArgumentMatching(_arg);
        DimComp paramComp_ = getQuickComponentBaseType(param_.getName());
        DimComp argComp_ = getQuickComponentBaseType(_arg);
        Class<?> clParam_ = param_.getClazz();
        Class<?> clArg_ = arg_.getClazz();
        boolean array_ = false;
        if (paramComp_.getDim() == argComp_.getDim()) {
            param_ = new ClassArgumentMatching(paramComp_.getComponent());
            arg_ = new ClassArgumentMatching(argComp_.getComponent());
            clArg_ = new ClassArgumentMatching(argComp_.getComponent()).getClazz();
            clParam_ = param_.getClazz();
            array_ = paramComp_.getDim() > 0 || argComp_.getDim() > 0;
        }
        if (clParam_.isAssignableFrom(clArg_)) {
            return true;
        }
        if (!array_) {
            String typeNameArg_ = PrimitiveTypeUtil.toPrimitive(arg_, true).getName();
            if (StringList.quickEq(typeNameArg_, PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                String typeNameParam_ = PrimitiveTypeUtil.toPrimitive(param_, true).getName();
                if (!StringList.quickEq(typeNameParam_, PrimitiveTypeUtil.PRIM_BOOLEAN)) {
                    return false;
                }
                return true;
            }
            ClassArgumentMatching clMatch_ = PrimitiveTypeUtil.toPrimitive(arg_, true);
            if (clMatch_.isPrimitive(_context)) {
                if (arg_.isPrimitive(_context)) {
                    CustList<ClassArgumentMatching> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_);
                    if (isPureNumberClass(clMatch_) && StringList.quickEq(_param, Number.class.getName())) {
                        return true;
                    }
                    ClassArgumentMatching prim_ = PrimitiveTypeUtil.toPrimitive(param_, true);
                    boolean contained_ = false;
                    for (ClassArgumentMatching c: gt_) {
                        if (StringList.quickEq(c.getName(), prim_.getName())) {
                            contained_ = true;
                            break;
                        }
                    }
                    if (!contained_) {
                        return false;
                    }
                    return true;
                }
                if (!param_.isPrimitive(_context)) {
                    return false;
                }
                CustList<ClassArgumentMatching> gt_ = PrimitiveTypeUtil.getOrdersGreaterEqThan(clMatch_);
                ClassArgumentMatching prim_ = param_;
                boolean contained_ = false;
                for (ClassArgumentMatching c: gt_) {
                    if (StringList.quickEq(c.getName(), prim_.getName())) {
                        contained_ = true;
                        break;
                    }
                }
                if (!contained_) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    static AssignableFrom isAssignableFromCust(String _param,String _arg, ContextEl _classes) {
        Classes classes_ = _classes.getClasses();
        LgNames stds_ = _classes.getStandards();
        if (classes_ != null) {
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
                    return AssignableFrom.NO;
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
                String className_ = dPar_.getComponent();
                if (StringList.quickEq(className_, a_)) {
                    return AssignableFrom.YES;
                }
                if (clArgBl_.getAllSuperTypes().containsObj(className_)) {
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
        if (StringList.quickEq(_param, stds_.getAliasObject())) {
            return AssignableFrom.YES;
        }
        return AssignableFrom.MAYBE;
    }

    public static CustList<ClassArgumentMatching> getOrdersGreaterEqThan(ClassArgumentMatching _class, ContextEl _context) {
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

    static CustList<ClassArgumentMatching> getOrdersGreaterEqThan(ClassArgumentMatching _class) {
        CustList<ClassArgumentMatching> primitives_ = new CustList<ClassArgumentMatching>();
        primitives_.add(new ClassArgumentMatching(PRIM_DOUBLE));
        primitives_.add(new ClassArgumentMatching(PRIM_FLOAT));
        primitives_.add(new ClassArgumentMatching(PRIM_LONG));
        primitives_.add(new ClassArgumentMatching(PRIM_INT));
        primitives_.add(new ClassArgumentMatching(PRIM_CHAR));
        primitives_.add(new ClassArgumentMatching(PRIM_SHORT));
        primitives_.add(new ClassArgumentMatching(PRIM_BYTE));
        CustList<ClassArgumentMatching> gt_ = new CustList<ClassArgumentMatching>();
        for (ClassArgumentMatching p: primitives_) {
            if (getOrderClass(p) >= getOrderClass(_class)) {
                gt_.add(p);
            }
        }
        return gt_;
    }
    public static int getOrderClass(String _class, ContextEl _context) {
        return getOrderClass(_class, _context.getStandards());
    }
    public static int getOrderClass(String _class, LgNames _stds) {
        return getOrderClass(new ClassArgumentMatching(_class), _stds);
    }
    public static int getOrderClass(ClassArgumentMatching _class, ContextEl _context) {
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
    public static int getOrderClass(ClassArgumentMatching _class) {
        ClassArgumentMatching class_ = toPrimitive(_class, true);
        if (class_.matchClass(PRIM_DOUBLE)) {
            return DOUBLE_CASTING;
        }
        if (class_.matchClass(PRIM_FLOAT)) {
            return FLOAT_CASTING;
        }
        if (class_.matchClass(PRIM_LONG)) {
            return LONG_CASTING;
        }
        if (class_.matchClass(PRIM_INT)) {
            return INT_CASTING;
        }
        if (class_.matchClass(PRIM_CHAR)) {
            return CHAR_CASTING;
        }
        if (class_.matchClass(PRIM_SHORT)) {
            return SHORT_CASTING;
        }
        if (class_.matchClass(PRIM_BYTE)) {
            return BYTE_CASTING;
        }
        return 0;
    }
    public static boolean isPrimitiveOrWrapper(String _className, ContextEl _context) {
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
    public static boolean isPureNumberClass(ClassArgumentMatching _class, ContextEl _context) {
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
        if (out_.matchClass(_stds.getAliasPrimShort())) {
            return true;
        }
        if (out_.matchClass(_stds.getAliasPrimByte())) {
            return true;
        }
        return false;
    }
    public static boolean isPureNumberClass(ClassArgumentMatching _class) {
        ClassArgumentMatching out_ = toPrimitive(_class, true);
        if (out_.matchClass(PRIM_DOUBLE)) {
            return true;
        }
        if (out_.matchClass(PRIM_FLOAT)) {
            return true;
        }
        if (out_.matchClass(PRIM_LONG)) {
            return true;
        }
        if (out_.matchClass(PRIM_INT)) {
            return true;
        }
        if (out_.matchClass(PRIM_SHORT)) {
            return true;
        }
        if (out_.matchClass(PRIM_BYTE)) {
            return true;
        }
        return false;
    }
    public static ClassMatching toPrimitive(ClassMatching _class, LgNames _stds) {
        ClassArgumentMatching cl_ = new ClassArgumentMatching(_class.getClassName());
        return new ClassMatching(toPrimitive(cl_, true, _stds).getName()); 
    }
    public static ClassMatching toPrimitive(ClassMatching _class, ContextEl _context) {
        return toPrimitive(_class, _context.getStandards());
    }
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, boolean _id, ContextEl _context) {
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
    public static ClassArgumentMatching toPrimitive(ClassArgumentMatching _class, boolean _id) {
        if (_class.matchClass(Boolean.class)) {
            return new ClassArgumentMatching(PRIM_BOOLEAN);
        }
        if (_class.matchClass(Double.class)) {
            return new ClassArgumentMatching(PRIM_DOUBLE);
        }
        if (_class.matchClass(Float.class)) {
            return new ClassArgumentMatching(PRIM_FLOAT);
        }
        if (_class.matchClass(Long.class)) {
            return new ClassArgumentMatching(PRIM_LONG);
        }
        if (_class.matchClass(Integer.class)) {
            return new ClassArgumentMatching(PRIM_INT);
        }
        if (_class.matchClass(Short.class)) {
            return new ClassArgumentMatching(PRIM_SHORT);
        }
        if (_class.matchClass(Byte.class)) {
            return new ClassArgumentMatching(PRIM_BYTE);
        }
        if (_class.matchClass(Character.class)) {
            return new ClassArgumentMatching(PRIM_CHAR);
        }
        if (_id) {
            return _class;
        }
        return null;
    }
    static ClassArgumentMatching toWrapper(ClassArgumentMatching _class, boolean _id, ContextEl _context) {
        return toWrapper(_class, _id, _context.getStandards());
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
    static ClassArgumentMatching toWrapper(ClassArgumentMatching _class, boolean _id) {
        if (_class.matchClass(PRIM_BOOLEAN)) {
            return new ClassArgumentMatching(Boolean.class.getName());
        }
        if (_class.matchClass(PRIM_DOUBLE)) {
            return new ClassArgumentMatching(Double.class.getName());
        }
        if (_class.matchClass(PRIM_FLOAT)) {
            return new ClassArgumentMatching(Float.class.getName());
        }
        if (_class.matchClass(PRIM_LONG)) {
            return new ClassArgumentMatching(Long.class.getName());
        }
        if (_class.matchClass(PRIM_INT)) {
            return new ClassArgumentMatching(Integer.class.getName());
        }
        if (_class.matchClass(PRIM_CHAR)) {
            return new ClassArgumentMatching(Character.class.getName());
        }
        if (_class.matchClass(PRIM_SHORT)) {
            return new ClassArgumentMatching(Short.class.getName());
        }
        if (_class.matchClass(PRIM_BYTE)) {
            return new ClassArgumentMatching(Byte.class.getName());
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
            LgNames stds_ = _context.getStandards();
            a_.setStruct(StdStruct.defaultClass(m_.getReturnType(stds_), _context));
            return a_;
        }
        if (_block instanceof ConstructorBlock) {
            return _global;
        }
        return Argument.createVoid();
    }

    public static Object defaultValue(String _class, ContextEl _context) {
        return defaultValue(_class, _context.getStandards());
    }

    public static Object defaultValue(String _class, LgNames _stds) {
        if (isPrimitive(_class, _stds)) {
            if (StringList.quickEq(_class, _stds.getAliasPrimBoolean())) {
                return false;
            }
            return convert(_class, 0, _stds);
        }
        return null;
    }
    public static Object convert(String _toClass, Object _arg, ContextEl _context) {
        return convert(_toClass, _arg, _context.getStandards());
    }
    public static Object convert(String _toClass, Object _arg, LgNames _stds) {
        ClassArgumentMatching class_ = new ClassArgumentMatching(_toClass);
        ClassArgumentMatching prim_ = toPrimitive(class_, true, _stds);
        if (prim_.matchClass(_stds.getAliasPrimDouble())) {
            if (_arg instanceof Character) {
                return (double)((Character)_arg).charValue();
            }
            return ((Number)_arg).doubleValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimFloat())) {
            if (_arg instanceof Character) {
                return (float)((Character)_arg).charValue();
            }
            return ((Number)_arg).floatValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimLong())) {
            if (_arg instanceof Character) {
                return (long)((Character)_arg).charValue();
            }
            return ((Number)_arg).longValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimInteger())) {
            if (_arg instanceof Character) {
                return (int)((Character)_arg).charValue();
            }
            return ((Number)_arg).intValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimChar())) {
            if (_arg instanceof Character) {
                return _arg;
            }
            return (char)((Number)_arg).longValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimShort())) {
            if (_arg instanceof Character) {
                return (short)((Character)_arg).charValue();
            }
            return ((Number)_arg).shortValue();
        }
        if (prim_.matchClass(_stds.getAliasPrimByte())) {
            if (_arg instanceof Character) {
                return (byte)((Character)_arg).charValue();
            }
            return ((Number)_arg).byteValue();
        }
        return null;
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
}
