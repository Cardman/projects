package code.expressionlanguage;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.DimComp;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.opers.CollectionsUtil;

public final class Templates {

    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';
    public static final String SEP_CLASS = ".";
    public static final String PREFIX_VAR_TYPE = "#";
    private static final String EMPTY_STRING = "";

    private Templates() {
    }
    /**This method works only on native classes*/
    public static StringList getTypesByBases(String _baseType, String _baseSuperType, Classes _classes) {
        if (!PrimitiveTypeUtil.canBeUseAsArgument(_baseSuperType, _baseType, _classes)) {
            return null;
        }
        Class<?> clSup_ = ConstClasses.classForNameNotInit(_baseSuperType);
        StringList curClasses_ = new StringList(_baseType);
        StringList visitedClasses_ = new StringList(_baseType);
        String generic_ = null;
        while (true) {
            StringList nextClasses_ = new StringList();
            for (String c: curClasses_) {
                StringList allTypes_ = StringList.getAllTypes(c);
                String baseClass_ = allTypes_.first();
                baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
                Class<?> cl_ = ConstClasses.classForNameNotInit(baseClass_);
                if (cl_.getTypeParameters().length != allTypes_.size() - 1) {
                    return null;
                }
                Class<?> superCl_ = cl_.getSuperclass();
                if (superCl_ != null) {
                    String superClass_ = superCl_.getName();
                    String geneSuperClass_ = getName(cl_.getGenericSuperclass());
                    geneSuperClass_ = insertPrefixVarType(geneSuperClass_);
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
                for (Class<?> s: cl_.getInterfaces()) {
                    i_++;
                    String geneSuperInterface_ = getName(cl_.getGenericInterfaces()[i_]);
                    geneSuperInterface_ = insertPrefixVarType(geneSuperInterface_);
                    geneSuperInterface_ = format(c, geneSuperInterface_, _classes);
                    if (StringList.quickEq(s.getName(), _baseSuperType)) {
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
        int len_ = clSup_.getTypeParameters().length;
        StringList foundSuperClass_ = StringList.getAllTypes(generic_);
        len_ = foundSuperClass_.size();
        StringList args_ = new StringList();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            args_.add(foundSuperClass_.get(i));
        }
        return args_;
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
                            ConstClasses.classForNameNotInit(base_);
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
            Class<?> cl_ = ConstClasses.classForNameNotInit(className_);
            for (TypeVariable<?> t: cl_.getTypeParameters()) {
                StringList localBound_ = new StringList();
                for (Type b: t.getBounds()) {
                    String typeString_ = getName(b);
                    String ext_;
                    if (!typeString_.contains(SEP_CLASS)) {
                        ext_ = PREFIX_VAR_TYPE + typeString_;
                    } else {
                        ext_ = insertPrefixVarType(typeString_);
                    }
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
        Class<?> cl_ = ConstClasses.classForNameNotInit(className_);
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
        StringList wordsAsTokens_ = StringList.getWordsSeparators(_type);
        StringList newList_ = new StringList();
        for (String t: wordsAsTokens_) {
            if (newList_.isEmpty()) {
                newList_.add(t);
                continue;
            }
            if (StringList.isWord(t)) {
                if (newList_.last().endsWith(SEP_CLASS)) {
                    newList_.setLast(newList_.last()+t);
                } else {
                    newList_.add(t);
                }
            } else if (StringList.quickEq(t,SEP_CLASS)) {
                newList_.setLast(newList_.last()+t);
            } else {
                newList_.add(t);
            }
        }
        int len_ = newList_.size();
        for (int i = CollectionsUtil.getFirstIndex(); i < len_; i++) {
            if (i % 2 == CollectionsUtil.getFirstIndex() % 2) {
                continue;
            }
            String type_ = newList_.get(i);
            if (_varTypes.contains(type_)) {
                String previous_ = newList_.getPrev(i);
                int indexDiese_ = previous_.lastIndexOf(PREFIX_VAR_TYPE);
                if (indexDiese_ != CustList.INDEX_NOT_FOUND_ELT) {
                    newList_.set(i - 1, previous_.substring(0, indexDiese_));
                    newList_.set(i,_varTypes.getVal(type_));
                }
            }
        }
        return newList_.join(EMPTY_STRING);
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
    static EqList<StringList> getClassBounds(String _className, Classes _classes) {
        StringList allTypes_ = StringList.getAllTypes(_className);
        String baseClass_ = PrimitiveTypeUtil.getArrayClass(allTypes_.first());
        Class<?> cl_ = ConstClasses.classForNameNotInit(baseClass_);
        if (cl_.getTypeParameters().length != allTypes_.size() - 1) {
            return null;
        }
        EqList<StringList> bounds_ = new EqList<StringList>();
        StringMap<StringList> localBounds_ = new StringMap<StringList>();
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            StringList localBound_ = new StringList();
            for (Type b: t.getBounds()) {
                String typeString_ = getName(b);
                String ext_ = insertPrefixVarType(typeString_);
                localBound_.add(ext_);
            }
            localBounds_.put(t.getName(), localBound_);
        }
        for (String t: localBounds_.getKeys()) {
            StringList current_ = new StringList(t);
            StringList classBounds_ = new StringList();
            while (true) {
                StringList next_ = new StringList();
                for (String c: current_) {
                    StringList bound_ = localBounds_.getVal(c);
                    for (String n: bound_) {
                        if (localBounds_.contains(n)) {
                            next_.add(n);
                        } else {
                            classBounds_.add(format(_className, n, _classes));
                        }
                    }
                }
                if (next_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            bounds_.add(classBounds_);
        }
        return bounds_;
    }
    public static StringList getClassLeftMostBounds(String _className, Classes _classes) {
        StringList bounds_ = new StringList();
        StringMap<StringList> localBounds_ = new StringMap<StringList>();
        boolean custom_ = false;
        if (_classes != null) {
            RootBlock cl_ = _classes.getClassBody(_className);
            if (cl_ != null) {
                custom_ = true;
                for (TypeVar t: cl_.getParamTypes()) {
                    StringList localBound_ = new StringList();
                    for (String c: t.getConstraints()) {
                        if (c.contains(TEMPLATE_BEGIN)) {
                            localBound_.add(c.substring(CustList.FIRST_INDEX, c.indexOf(TEMPLATE_BEGIN)));
                        } else {
                            localBound_.add(c);
                        }
                    }
                    localBounds_.put(PREFIX_VAR_TYPE+t.getName(), localBound_);
                }
            }
        }
        if (!custom_) {
            String baseClass_ = PrimitiveTypeUtil.getArrayClass(_className);
            Class<?> cl_ = ConstClasses.classForNameNotInit(baseClass_);
            for (TypeVariable<?> t: cl_.getTypeParameters()) {
                StringList localBound_ = new StringList();
                for (Type b: t.getBounds()) {
                    String typeString_ = getBaseName(b);
                    if (!typeString_.contains(SEP_CLASS)) {
                        localBound_.add(PREFIX_VAR_TYPE+typeString_);
                    } else {
                        localBound_.add(typeString_);
                    }
                }
                localBounds_.put(PREFIX_VAR_TYPE+t.getName(), localBound_);
            }
        }
        for (String t: localBounds_.getKeys()) {
            StringList current_ = new StringList(t);
            String classBound_ = EMPTY_STRING;
            while (true) {
                StringList next_ = new StringList();
                for (String c: current_) {
                    StringList bound_ = localBounds_.getVal(c);
                    for (String n: bound_) {
                        if (localBounds_.contains(n)) {
                            next_.add(n);
                        } else {
                            classBound_ = n;
                            break;
                        }
                    }
                }
                if (!classBound_.isEmpty()) {
                    break;
                }
                current_ = next_;
            }
            bounds_.add(classBound_);
        }
        return bounds_;
    }
    private static String insertPrefixVarType(String _wildCard) {
        String str_ = _wildCard;
        StringList allTypes_ = StringList.getAllTypes(str_);
        int nbTypes_ = allTypes_.size();
        if (nbTypes_ == 1) {
            return str_;
        }
        StringBuilder newType_ = new StringBuilder(allTypes_.first());
        newType_.append(TEMPLATE_BEGIN);
        for (int i = CustList.SECOND_INDEX; i < nbTypes_; i++) {
            if (!allTypes_.get(i).contains(SEP_CLASS)) {
                newType_.append(PREFIX_VAR_TYPE);
            }
            newType_.append(allTypes_.get(i));
            newType_.append(TEMPLATE_SEP);
        }
        newType_.deleteCharAt(newType_.length()-1);
        newType_.append(TEMPLATE_END);
        return newType_.toString();
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

    private static String getBaseName(Type _t) {
        if (_t instanceof Class<?>) {
            return ((Class<?>)_t).getName();
        }
        String str_ = _t.toString();
        if (_t instanceof ParameterizedType) {
            return str_.substring(0, str_.indexOf(TEMPLATE_BEGIN));
        }
        return str_;
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
        Class<?> cl_ = ConstClasses.classForNameNotInit(_className).getSuperclass();
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
        String genericSuper_ = getName(ConstClasses.classForNameNotInit(baseClass_).getGenericSuperclass());
        return insertPrefixVarType(genericSuper_);
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
        Class<?> cl_ = ConstClasses.classForNameNotInit(_className);
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
        String genericSuper_ = getName(ConstClasses.classForNameNotInit(baseClass_).getGenericInterfaces()[_index]);
        return insertPrefixVarType(genericSuper_);
    }

    private static String getName(Type _t) {
        if (_t instanceof Class<?>) {
            return ((Class<?>)_t).getName();
        }
        return _t.toString();
    }
}
