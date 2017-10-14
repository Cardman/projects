package code.expressionlanguage;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.types.NativeTypeUtil;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;

public final class Templates {

    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';
    public static final String SEP_CLASS = ".";
    public static final String PREFIX_VAR_TYPE = "#";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';

    private Templates() {
    }

    public static StringList getTypesByBases(String _baseType, String _baseSuperType, Classes _classes) {
        String generic_ = getGenericTypeByBases(_baseType, _baseSuperType, _classes);
        if (generic_ == null) {
            return null;
        }
        StringList foundSuperClass_ = StringList.getAllTypes(generic_);
        int len_ = foundSuperClass_.size();
        StringList args_ = new StringList();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            args_.add(foundSuperClass_.get(i));
        }
        return args_;
    }
    public static String getGenericTypeByBases(String _baseType, String _baseSuperType, Classes _classes) {
        if (!PrimitiveTypeUtil.canBeUseAsArgument(_baseSuperType, _baseType, _classes)) {
            return null;
        }
        StringList curClasses_ = new StringList(_baseType);
        StringList visitedClasses_ = new StringList(_baseType);
        String generic_ = null;
        if (StringList.quickEq(_baseSuperType, _baseType)) {
            generic_ = _baseType;
        }
        if (generic_ == null) {
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
//                    baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
                    if (!PrimitiveTypeUtil.correctNbParameters(c, _classes)) {
                        return null;
                    }
                    String superClass_ = getSuperClassName(baseClass_, _classes);
                    if (superClass_ != null) {
                        String geneSuperClass_ = getGenericSuperClassName(c, _classes);
                        geneSuperClass_ = format(c, geneSuperClass_, _classes);
                        if (StringList.quickEq(superClass_, _baseSuperType)) {
                            generic_ = geneSuperClass_;
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperClass_)) {
                            nextClasses_.add(geneSuperClass_);
                            visitedClasses_.add(geneSuperClass_);
                        }
                    }
                    int i_ = CustList.INDEX_NOT_FOUND_ELT;
                    for (String s: getSuperInterfaceNames(baseClass_, _classes)) {
                        i_++;
                        String geneSuperInterface_ = getGenericSuperInterfaceName(c, i_, _classes);
                        geneSuperInterface_ = format(c, geneSuperInterface_, _classes);
                        if (StringList.quickEq(s, _baseSuperType)) {
                            generic_ = geneSuperInterface_;
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperInterface_)) {
                            nextClasses_.add(geneSuperInterface_);
                            visitedClasses_.add(geneSuperInterface_);
                        }
                    }
                }
                if (generic_ != null) {
                    break;
                }
                curClasses_ = nextClasses_;
            }
        }
        return generic_;
    }
    public static boolean isCorrectWrite(String _className) {
        StringList current_ = new StringList(_className);
        boolean already_ = false;
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList elts_ = StringList.getAllTypes(c);
                if (elts_ == null) {
                    return false;
                }
                String base_ = elts_.first();
                if (base_.isEmpty()) {
                    return false;
                }
                String compo_ = PrimitiveTypeUtil.getQuickComponentBaseType(base_).getComponent();
                if (base_.startsWith(PrimitiveTypeUtil.PRIM) && already_) {
                    return false;
                }
                if (compo_.startsWith(PrimitiveTypeUtil.PRIM)) {
                    if (!StringList.isWord(compo_.substring(PrimitiveTypeUtil.PRIM.length()))) {
                        return false;
                    }
                } else if (!compo_.startsWith(PREFIX_VAR_TYPE)) {
                    for (String p: StringList.splitStrings(compo_, SEP_CLASS)) {
                        if (!StringList.isWord(p)) {
                            return false;
                        }
                    }
                }
                int nbParams_ = elts_.size();
                for (int i = CustList.SECOND_INDEX; i < nbParams_; i++) {
                    String baseLoc_ = elts_.get(i);
                    String compoLoc_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseLoc_).getComponent();
                    if (compoLoc_.startsWith(PREFIX_VAR_TYPE)) {
                        if (!StringList.isWord(compoLoc_.substring(PREFIX_VAR_TYPE.length()))) {
                            return false;
                        }
                        continue;
                    }
                    next_.add(baseLoc_);
                }
            }
            if (next_.isEmpty()) {
                return true;
            }
            already_ = true;
            current_ = next_;
        }
    }
    public static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, Classes _classes) {
        if (!isCorrectTemplate(_className, _inherit, _classes)) {
            return false;
        }
        StringList current_ = new StringList(_className);
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList types_ = StringList.getAllTypes(c);
                String base_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
                if (_classes != null) {
                    if (base_.startsWith(PREFIX_VAR_TYPE)) {
                        continue;
                    }
                    if (base_.startsWith(PrimitiveTypeUtil.PRIM)) {
                        continue;
                    }
                    if (_classes.getClassBody(base_) == null) {
                        try {
                            ConstClasses.classForObjectNameNotInit(base_);
                        } catch (Exception _0) {
                            return false;
                        }
                    }
                }
                for (String n: types_.mid(1)) {
                    if (!isCorrectTemplate(n, _inherit, _classes)) {
                        return false;
                    }
                    next_.add(n);
                }
            }
            if (next_.isEmpty()) {
                return true;
            }
            current_ = next_;
        }
    }
    public static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = types_.first();
        className_ = PrimitiveTypeUtil.getQuickComponentBaseType(className_).getComponent();
        if (className_.startsWith(PrimitiveTypeUtil.PRIM)) {
            return ConstClasses.getPrimitiveClass(className_.substring(1)) != null;
        }
        if (className_.startsWith(PREFIX_VAR_TYPE)) {
            return _inherit.contains(className_.substring(1));
        }
        if (!PrimitiveTypeUtil.correctNbParameters(_className, _classes)) {
            return false;
        }
        int i_ = CustList.FIRST_INDEX;
        EqList<StringList> boundsAll_ = null;
        if (_classes != null) {
            RootBlock r_ = _classes.getClassBody(className_);
            if (r_ != null) {
                boundsAll_ = new EqList<StringList>();
                for (TypeVar t:r_.getParamTypes()) {
                    StringList localBound_ = new StringList();
                    for (String b: t.getConstraints()) {
                        localBound_.add(b);
                    }
                    boundsAll_.add(localBound_);
                }
            }
        }
        if (boundsAll_ == null) {
            boundsAll_ = new EqList<StringList>();
            Class<?> cl_ = ConstClasses.classForObjectNameNotInit(className_);
            for (TypeVariable<?> t: cl_.getTypeParameters()) {
                StringList localBound_ = new StringList();
                for (Type b: t.getBounds()) {
                    String ext_ = NativeTypeUtil.getPrettyType(b);
                    localBound_.add(ext_);
                }
                boundsAll_.add(localBound_);
            }
        }
        for (StringList t: boundsAll_) {
            i_++;
            String arg_ = types_.get(i_);
            DimComp dimCompArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(arg_);
            String comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(PREFIX_VAR_TYPE);
            StringList bounds_ = new StringList();
            if (lookInInherit_) {
                if (!_inherit.contains(comp_.substring(1))) {
                    return false;
                }
                bounds_.addAllElts(_inherit.getVal(comp_.substring(1)));
            } else {
                bounds_.add(arg_);
            }
            for (String e: t) {
                Mapping m_ = new Mapping();
                String typeBound_ = e;
                String ext_ = typeBound_;
                String param_ = format(_className, ext_, _classes);
                m_.setParam(param_);
                boolean ok_ = false;
                for (String v: bounds_) {
                    m_.setArg(v);
                    m_.setMapping(_inherit);
                    if (isCorrect(m_, _classes)) {
                        ok_ = true;
                        break;
                    }
                }
                if (!ok_) {
                    return false;
                }
            }
        }
        return true;
    }
    public static String format(String _first, String _second, Classes _classes) {
        StringMap<String> varTypes_ = getVarTypes(_first, _classes);
        return getFormattedType(_second, varTypes_);
    }
    public static String getGenericString(String _className, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        if (_classes != null) {
            RootBlock root_ = _classes.getClassBody(className_);
            if (root_ != null) {
                return root_.getGenericString();
            }
        }
        Class<?> cl_ = ConstClasses.classForObjectNameNotInit(className_);
        if (cl_.getTypeParameters().length == 0) {
            return className_;
        }
        StringBuilder generic_ = new StringBuilder(className_);
        StringList vars_ = new StringList();
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            vars_.add(PREFIX_VAR_TYPE+t.getName());
        }
        generic_.append(TEMPLATE_BEGIN);
        generic_.append(vars_.join(TEMPLATE_SEP));
        generic_.append(TEMPLATE_END);
        return generic_.toString();
    }
    public static CustList<TypeVar> getConstraints(String _className, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        if (_classes != null) {
            RootBlock root_ = _classes.getClassBody(className_);
            if (root_ != null) {
                return root_.getParamTypes();
            }
        }
        Class<?> cl_ = ConstClasses.classForObjectNameNotInit(className_);
        if (cl_.getTypeParameters().length == 0) {
            return new CustList<TypeVar>();
        }
        CustList<TypeVar> vars_ = new CustList<TypeVar>();
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            TypeVar t_ = new TypeVar();
            t_.setName(t.getName());
            StringList list_ = new StringList();
            for (Type b: t.getBounds()) {
                list_.add(NativeTypeUtil.getPrettyType(b));
            }
            t_.setConstraints(list_);
            vars_.add(t_);
        }
        return vars_;
    }
    static StringMap<String> getVarTypes(String _className, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        if (_classes != null) {
            RootBlock root_ = _classes.getClassBody(className_);
            if (root_ != null) {
                StringMap<String> varTypes_ = new StringMap<String>();
                int i_ = CustList.FIRST_INDEX;
                for (TypeVar t: root_.getParamTypes()) {
                    i_++;
                    String arg_ = types_.get(i_);
                    varTypes_.put(t.getName(), arg_);
                }
                return varTypes_;
            }
        }
        Class<?> cl_ = ConstClasses.classForObjectNameNotInit(className_);
        int i_ = CustList.FIRST_INDEX;
        StringMap<String> varTypes_ = new StringMap<String>();
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return varTypes_;
    }

    static String getFormattedType(String _type, StringMap<String> _varTypes) {
        StringBuilder str_ = new StringBuilder();
        int len_ = _type.length();
        int diese_ = 0;
        boolean var_ = false;
        for (int i = 0; i < len_; i++) {
            if (_type.charAt(i) == PREFIX_VAR_TYPE_CHAR) {
                var_ = true;
                diese_ = i;
                continue;
            }
            if (!var_) {
                str_.append(_type.charAt(i));
                continue;
            }
            if (StringList.isWordChar(_type.charAt(i))) {
                continue;
            }
            String sub_ = _type.substring(diese_+1, i);
            if (_varTypes.contains(sub_)) {
                str_.append(_varTypes.getVal(sub_));
            } else {
                str_.append(sub_);
            }
            str_.append(_type.charAt(i));
            var_ = false;
        }
        if (var_) {
            String sub_ = _type.substring(diese_+1);
            if (_varTypes.contains(sub_)) {
                str_.append(_varTypes.getVal(sub_));
            } else {
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    public static boolean isCorrect(Mapping _m, Classes _classes) {
        String arg_ = _m.getArg();
        String param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        MappingPairs m_ = getSimpleMapping(map_, _classes);
        if (m_ == null) {
            return false;
        }
        for (Matching n: m_.getPairsArgParam()) {
            if (!StringList.quickEq(n.getParam(), n.getArg())) {
                return false;
            }
        }
        return true;
    }
    private static MappingPairs getSimpleMapping(Mapping _m, Classes _classes) {
        String arg_ = _m.getArg();
        String param_ = _m.getParam();
        StringList typesArg_ = StringList.getAllTypes(arg_);
        StringList typesParam_ = StringList.getAllTypes(param_);
        String baseArg_ = typesArg_.first();
        String baseParam_ = typesParam_.first();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(arg_);
        DimComp dParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(param_);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParam_);
        String classParam_ = dBaseParam_.getComponent();
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_m.inheritArgParam(baseArrayParam_.substring(1), baseArrayArg_.substring(1))) {
                MappingPairs m_ = new MappingPairs();
                return m_;
            }
            return null;
        }
        StringList bounds_ = new StringList();
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            for (String a: _m.getAllUpperBounds(baseArrayArg_)) {
                bounds_.add(PrimitiveTypeUtil.getPrettyArrayType(a, dArg_.getDim()));
            }
        } else {
            bounds_.add(baseArg_);
        }
        if (typesParam_.size() == 1) {
            boolean inh_ = false;
            for (String a: bounds_) {
                StringList allTypes_ = StringList.getAllTypes(a);
                String base_ = allTypes_.first();
                if (PrimitiveTypeUtil.canBeUseAsArgument(baseParam_, base_, _classes)) {
                    inh_ = true;
                    break;
                }
            }
            if (!inh_) {
                return null;
            }
            MappingPairs m_ = new MappingPairs();
            return m_;
        }
        if (baseArrayArg_.startsWith(PrimitiveTypeUtil.PRIM)) {
            return null;
        }
        for (String a: bounds_) {
            DimComp dLoc_ = PrimitiveTypeUtil.getQuickComponentBaseType(a);
            int dim_ = dLoc_.getDim();
            if (dim_ != dParam_.getDim()) {
                return null;
            }
        }
        int dim_ = dArg_.getDim();
        if (StringList.quickEq(baseArg_, baseParam_)) {
            int len_ = typesParam_.size();
            if (typesArg_.size() != len_) {
                return null;
            }
            EqList<Matching> pairsArgParam_ = new EqList<Matching>();
            for (int i = CustList.SECOND_INDEX; i < len_; i++) {
                Matching match_ = new Matching();
                match_.setArg(typesArg_.get(i));
                match_.setParam(typesParam_.get(i));
                pairsArgParam_.add(match_);
            }
            MappingPairs m_ = new MappingPairs();
            m_.setPairsArgParam(pairsArgParam_);
            return m_;
        }

        StringList curClasses_ = new StringList(dArg_.getComponent());
        StringList visitedClasses_ = new StringList(dArg_.getComponent());
        String generic_ = null;
        if (baseArg_.startsWith(PREFIX_VAR_TYPE)) {
            curClasses_ = _m.getAllUpperBounds(baseArg_.substring(1));
            visitedClasses_ = new StringList(curClasses_);
            for (String c: curClasses_) {
                if (StringList.quickEq(c, param_)) {
                    generic_ = c;
                    break;
                }
            }
        }
        if (generic_ == null) {
            for (String c: curClasses_) {
                StringList allTypes_ = StringList.getAllTypes(c);
                String baseClass_ = allTypes_.first();
                baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                if (!PrimitiveTypeUtil.correctNbParameters(c, _classes)) {
                    return null;
                }
            }
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
                    baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                    String superClass_ = getSuperClassName(baseClass_, _classes);
                    if (superClass_ != null) {
                        String geneSuperClass_ = getGenericSuperClassName(c, _classes);
                        geneSuperClass_ = format(c, geneSuperClass_, _classes);
                        if (StringList.quickEq(superClass_, classParam_)) {
                            generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperClass_, dim_);
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperClass_)) {
                            nextClasses_.add(geneSuperClass_);
                            visitedClasses_.add(geneSuperClass_);
                        }
                    }
                    int i_ = CustList.INDEX_NOT_FOUND_ELT;
                    for (String s: getSuperInterfaceNames(baseClass_, _classes)) {
                        i_++;
                        String geneSuperInterface_ = getGenericSuperInterfaceName(c, i_, _classes);
                        geneSuperInterface_ = format(c, geneSuperInterface_, _classes);
                        if (StringList.quickEq(s, classParam_)) {
                            generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperInterface_)) {
                            nextClasses_.add(geneSuperInterface_);
                            visitedClasses_.add(geneSuperInterface_);
                        }
                    }
                }
                if (generic_ != null) {
                    break;
                }
                if (nextClasses_.isEmpty()) {
                    break;
                }
                curClasses_ = nextClasses_;
            }
        }
        if (generic_ == null) {
            return null;
        }
        int len_ = typesParam_.size();
        StringList foundSuperClass_ = StringList.getAllTypes(generic_);
        EqList<Matching> pairsArgParam_ = new EqList<Matching>();
        len_ = foundSuperClass_.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Matching match_ = new Matching();
            match_.setArg(foundSuperClass_.get(i));
            match_.setParam(typesParam_.get(i));
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    private static String getSuperClassName(String _className, Classes _classes) {
        if (_classes != null) {
            RootBlock r_ = _classes.getClassBody(_className);
            if (r_ instanceof UniqueRootedBlock) {
                return ((UniqueRootedBlock)r_).getSuperClass();
            }
            if (r_ instanceof InterfaceBlock) {
                return null;
            }
        }
        String baseClass_ = PrimitiveTypeUtil.getArrayClass(_className);
        Class<?> cl_ = ConstClasses.classForObjectNameNotInit(baseClass_).getSuperclass();
        if (cl_ == null) {
            return null;
        }
        return cl_.getName();
    }

    private static String getGenericSuperClassName(String _genericClassName, Classes _classes) {
        StringList allTypes_ = StringList.getAllTypes(_genericClassName);
        String baseClass_ = allTypes_.first();
        if (_classes != null) {
            RootBlock r_ = _classes.getClassBody(baseClass_);
            if (r_ != null) {
                return ((UniqueRootedBlock)r_).getGenericSuperClass();
            }
        }
        baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
        return NativeTypeUtil.getPrettyType(ConstClasses.classForObjectNameNotInit(baseClass_).getGenericSuperclass());
    }

    private static StringList getSuperInterfaceNames(String _className, Classes _classes) {
        if (_classes != null) {
            RootBlock r_ = _classes.getClassBody(_className);
            if (r_ instanceof UniqueRootedBlock) {
                return ((UniqueRootedBlock)r_).getDirectInterfaces();
            }
            if (r_ instanceof InterfaceBlock) {
                return ((InterfaceBlock)r_).getDirectSuperClasses();
            }
        }
        String baseClass_ = PrimitiveTypeUtil.getArrayClass(_className);
        Class<?> cl_ = ConstClasses.classForObjectNameNotInit(baseClass_);
        StringList interfaces_ = new StringList();
        for (Class<?> i: cl_.getInterfaces()) {
            interfaces_.add(i.getName());
        }
        return interfaces_;
    }

    private static String getGenericSuperInterfaceName(String _genericClassName, int _index, Classes _classes) {
        StringList allTypes_ = StringList.getAllTypes(_genericClassName);
        String baseClass_ = allTypes_.first();
        if (_classes != null) {
            RootBlock r_ = _classes.getClassBody(baseClass_);
            if (r_ instanceof UniqueRootedBlock) {
                return ((UniqueRootedBlock)r_).getDirectGenericInterfaces().get(_index);
            }
            if (r_ instanceof InterfaceBlock) {
                return ((InterfaceBlock)r_).getDirectGenericSuperClasses().get(_index);
            }
        }
        baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
        return NativeTypeUtil.getPrettyType(ConstClasses.classForObjectNameNotInit(baseClass_).getGenericInterfaces()[_index]);
    }
}
