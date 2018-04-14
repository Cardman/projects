package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.PredefinedClasses;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

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

    public static boolean correctClassParts(String _className, StringMap<StringList> _mapping, Analyzable _context) {
        if (!existClassParts(_className, _mapping, _context)) {
            return false;
        }
        return isCorrectTemplateAll(_className, _mapping, _context);
    }

    public static boolean existClassParts(String _className, StringMap<StringList> _mapping, Analyzable _context) {
        if (!isCorrectWrite(_className, _context)) {
            return false;
        }
        StringList variables_ = _mapping.getKeys();
        if (!existAllClassParts(_className, variables_, _context)) {
            return false;
        }
        return true;
    }

    public static boolean existAllClassParts(String _className, StringList _variables, Analyzable _context) {
        Classes classes_ = _context.getClasses();
        LgNames stds_ = _context.getStandards();
        String void_ = stds_.getAliasVoid();
        for (String s: StringList.splitStrings(_className, TEMPLATE_BEGIN,TEMPLATE_SEP,TEMPLATE_END)) {
            if (s.isEmpty()) {
                continue;
            }
            String baseName_ = PrimitiveTypeUtil.getQuickComponentBaseType(s).getComponent();
            if (StringList.quickEq(baseName_, void_)) {
                return false;
            }
            if (baseName_.startsWith(PREFIX_VAR_TYPE)) {
                if (!_variables.containsStr(baseName_.substring(1))) {
                    return false;
                }
                continue;
            }
            boolean custClass_ = false;
            custClass_ = classes_.isCustomType(baseName_) || _context.getStandards().getStandards().contains(baseName_);
            if (!custClass_) {
                if (!PrimitiveTypeUtil.isPrimitive(baseName_, _context)) {
                    return false;
                }
            }
        }
        return true;
    }
    public static StringList getAllGenericSuperTypes(String _className, ContextEl _context) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = types_.first();
        GeneType root_ = _context.getClassBody(className_);
        return TypeUtil.getAllGenericSuperTypes(root_, _context);
    }
    public static String getFullTypeByStds(String _subType, ContextEl _context) {
        String baseSubType_ = _subType;
        LgNames lgNames_ = _context.getStandards();
        StandardType std_ = lgNames_.getStandards().getVal(baseSubType_);
        if (std_ == null) {
            return null;
        }
        if (std_.getIterative().isEmpty()) {
            return null;
        }
        return StringList.concat(lgNames_.getAliasIterable(),TEMPLATE_BEGIN,std_.getIterative(),TEMPLATE_END);
    }
    public static String getFullTypeByBases(String _subType, String _superType, Analyzable _context) {
        String baseSubType_ = StringList.getAllTypes(_subType).first();
        String baseSuperType_ = StringList.getAllTypes(_superType).first();
        if (!PrimitiveTypeUtil.canBeUseAsArgument(baseSuperType_, baseSubType_, _context)) {
            return null;
        }
        StringList curClasses_ = new StringList(_subType);
        StringList visitedClasses_ = new StringList(_subType);
        String generic_ = null;
        if (StringList.quickEq(_subType, _superType)) {
            generic_ = _subType;
        } else if (StringList.quickEq(baseSubType_, baseSuperType_)) {
            generic_ = _subType;
        }
        if (generic_ == null) {
            for (String c: curClasses_) {
                if (!correctNbParameters(c, _context)) {
                    return null;
                }
            }
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
                    String superClass_ = getSuperClassName(baseClass_, _context);
                    if (superClass_ != null) {
                        String geneSuperClass_ = getGenericSuperClassName(c, _context);
                        geneSuperClass_ = format(c, geneSuperClass_, _context);
                        if (StringList.quickEq(superClass_, baseSuperType_)) {
                            generic_ = geneSuperClass_;
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperClass_)) {
                            nextClasses_.add(geneSuperClass_);
                            visitedClasses_.add(geneSuperClass_);
                        }
                    }
                    int i_ = CustList.INDEX_NOT_FOUND_ELT;
                    for (String s: getSuperInterfaceNames(baseClass_, _context)) {
                        i_++;
                        String geneSuperInterface_ = getGenericSuperInterfaceName(c, i_, _context);
                        geneSuperInterface_ = format(c, geneSuperInterface_, _context);
                        if (StringList.quickEq(s, baseSuperType_)) {
                            generic_ = geneSuperInterface_;
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperInterface_)) {
                            nextClasses_.add(geneSuperInterface_);
                            visitedClasses_.add(geneSuperInterface_);
                        }
                    }
                    if (generic_ != null) {
                        break;
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
    public static boolean isCorrectWrite(String _className, Analyzable _context) {
        StringList current_ = new StringList(_className);
        boolean already_ = false;
        StringMap<StandardType> stds_ = _context.getStandards().getStandards();
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
                if (PrimitiveTypeUtil.isPrimitive(base_, _context) && already_) {
                    return false;
                }
                if (!PrimitiveTypeUtil.isPrimitive(compo_, _context)) {
                    if (!compo_.startsWith(PREFIX_VAR_TYPE)) {
                        if (!stds_.contains(compo_)) {
                            if (!PredefinedClasses.isPredefined(compo_, _context)) {
                                for (String p: StringList.splitStrings(compo_, SEP_CLASS)) {
                                    if (!StringList.isWord(p)) {
                                        return false;
                                    }
                                }
                            }
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
    static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, Analyzable _context) {
        if (!isCorrectTemplate(_className, _inherit, _context)) {
            return false;
        }
        StringList current_ = new StringList(_className);
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList types_ = StringList.getAllTypes(c);
                for (String n: types_.mid(1)) {
                    if (!isCorrectTemplate(n, _inherit, _context)) {
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
    static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Analyzable _context) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = types_.first();
        className_ = PrimitiveTypeUtil.getQuickComponentBaseType(className_).getComponent();
        if (PrimitiveTypeUtil.isPrimitive(className_, _context)) {
            return true;
        }
        if (className_.startsWith(PREFIX_VAR_TYPE)) {
            return _inherit.contains(className_.substring(1));
        }
        if (!correctNbParameters(_className, _context)) {
            return false;
        }
        int i_ = CustList.FIRST_INDEX;
        EqList<StringList> boundsAll_ = null;
        GeneType r_ = _context.getClassBody(className_);
        boundsAll_ = new EqList<StringList>();
        for (TypeVar t:r_.getParamTypes()) {
            StringList localBound_ = new StringList();
            for (String b: t.getConstraints()) {
                localBound_.add(b);
            }
            boundsAll_.add(localBound_);
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
                String param_ = format(_className, ext_, _context);
                m_.setParam(param_);
                boolean ok_ = false;
                for (String v: bounds_) {
                    m_.setArg(v);
                    m_.setMapping(_inherit);
                    if (isCorrect(m_, _context)) {
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
    public static String generalFormat(String _first, String _second, Analyzable _classes) {
        StringMap<String> varTypes_ = getVarTypes(_first, false, _classes);
        return getFormattedType(_second, varTypes_);
    }
    public static String format(String _first, String _second, Analyzable _context) {
        StringMap<String> varTypes_ = getVarTypes(_first, true, _context);
        return getFormattedType(_second, varTypes_);
    }
    public static String getGenericString(String _className, Analyzable _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        return root_.getGenericString();
    }
    public static CustList<TypeVar> getConstraints(String _className, Analyzable _context) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        return root_.getParamTypes();
    }
    static StringMap<String> getVarTypes(String _className, boolean _checkExact,Analyzable _context) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();

        String objType_ = _context.getStandards().getAliasObject();
        GeneType root_ = _context.getClassBody(className_);
        StringMap<String> varTypes_ = new StringMap<String>();
        CustList<TypeVar> typeVar_ = root_.getParamTypes();
        if (typeVar_.size() != types_.size() - 1 && !_checkExact) {
            Mapping map_ = new Mapping();
            for (TypeVar t: typeVar_) {
                map_.getMapping().put(t.getName(), t.getConstraints());
            }
            for (TypeVar t: typeVar_) {
                StringList bounds_ = map_.getAllUpperBounds(t.getName(), objType_);
                if (bounds_.size() == 1) {
                    varTypes_.put(t.getName(), bounds_.first());
                } else {
                    varTypes_.put(t.getName(), objType_);
                }
            }
            return varTypes_;
        }
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            i_++;
            String arg_ = types_.get(i_);
            varTypes_.put(t.getName(), arg_);
        }
        return varTypes_;
    }

    static String getFormattedType(String _type, StringMap<String> _varTypes) {
        if (_varTypes.isEmpty()) {
            return _type;
        }
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
                sub_ = _type.substring(diese_, i);
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
                sub_ = _type.substring(diese_);
                str_.append(sub_);
            }
        }
        return str_.toString();
    }
    public static boolean isGenericCorrect(Mapping _m, Analyzable _context) {
        if (_m.getArg().isEmpty()) {
            return !PrimitiveTypeUtil.isPrimitive(_m.getParam(), _context);
        }
        return isCorrect(_m, _context);
    }
    public static boolean isCorrect(Mapping _m, Analyzable _context) {
        String arg_ = _m.getArg();
        String param_ = _m.getParam();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        Mapping map_ = new Mapping();
        map_.setParam(param_);
        map_.setArg(arg_);
        map_.setMapping(generalMapping_);
        MappingPairs m_ = getSimpleMapping(map_, _context);
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
    private static MappingPairs getSimpleMapping(Mapping _m, Analyzable _context) {
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
        String objType_ = _context.getStandards().getAliasObject();
        if (baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            for (String a: _m.getAllUpperBounds(baseArrayArg_, objType_)) {
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
                if (PrimitiveTypeUtil.canBeUseAsArgument(baseParam_, base_, _context)) {
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
        if (PrimitiveTypeUtil.isPrimitive(baseArrayArg_, _context)) {
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
            curClasses_ = _m.getAllUpperBounds(baseArg_.substring(1), objType_);
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
                if (!correctNbParameters(c, _context)) {
                    return null;
                }
            }
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
                    baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                    String superClass_ = getSuperClassName(baseClass_, _context);
                    if (superClass_ != null) {
                        String geneSuperClass_ = getGenericSuperClassName(c, _context);
                        geneSuperClass_ = format(c, geneSuperClass_, _context);
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
                    for (String s: getSuperInterfaceNames(baseClass_, _context)) {
                        i_++;
                        String geneSuperInterface_ = getGenericSuperInterfaceName(c, i_, _context);
                        geneSuperInterface_ = format(c, geneSuperInterface_, _context);
                        if (StringList.quickEq(s, classParam_)) {
                            generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                            break;
                        }
                        if (!visitedClasses_.containsStr(geneSuperInterface_)) {
                            nextClasses_.add(geneSuperInterface_);
                            visitedClasses_.add(geneSuperInterface_);
                        }
                    }
                    if (generic_ != null) {
                        break;
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

    private static String getSuperClassName(String _className, Analyzable _context) {
        GeneType r_ = _context.getClassBody(_className);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getSuperClass(_context);
        }
        if (r_ instanceof InterfaceBlock) {
            return null;
        }
        LgNames stds_ = _context.getStandards();
        if (StringList.quickEq(_className, stds_.getAliasObject())) {
            return null;
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getSuperClass();
        }
        return null;
    }

    private static String getGenericSuperClassName(String _genericClassName, Analyzable _context) {
        StringList allTypes_ = StringList.getAllTypes(_genericClassName);
        String baseClass_ = allTypes_.first();
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(baseClass_);
        if (r_ != null) {
            return ((UniqueRootedBlock)r_).getGenericSuperClass(_context);
        }
        LgNames stds_ = _context.getStandards();
        if (StringList.quickEq(baseClass_, stds_.getAliasObject())) {
            return null;
        }
        StandardType type_ = _context.getStandards().getStandards().getVal(baseClass_);
        if (type_ instanceof StandardClass) {
            return ((StandardClass)type_).getSuperClass();
        }
        return null;
    }

    private static StringList getSuperInterfaceNames(String _className, Analyzable _context) {
        GeneType r_ = _context.getClassBody(_className);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getDirectInterfaces(_context);
        }
        if (r_ instanceof InterfaceBlock) {
            return ((InterfaceBlock)r_).getDirectSuperClasses(_context);
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getDirectInterfaces();
        }
        return ((StandardInterface)r_).getDirectSuperClasses();
    }

    private static String getGenericSuperInterfaceName(String _genericClassName, int _index, Analyzable _context) {
        StringList allTypes_ = StringList.getAllTypes(_genericClassName);
        String baseClass_ = allTypes_.first();
        GeneType r_ = _context.getClassBody(baseClass_);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getDirectGenericInterfaces(_context).get(_index);
        }
        if (r_ instanceof InterfaceBlock) {
            return ((InterfaceBlock)r_).getDirectGenericSuperClasses(_context).get(_index);
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getDirectInterfaces().get(_index);
        }
        return ((StandardInterface)r_).getDirectSuperClasses().get(_index);
    }

    public static boolean correctNbParameters(String _genericClass, Analyzable _context) {
        StringList params_ = StringList.getAllTypes(_genericClass);
        String base_ = params_.first();
        int nbParams_ = params_.size() - 1;
        String baseArr_ = PrimitiveTypeUtil.getQuickComponentBaseType(base_).getComponent();
        return _context.getClassBody(baseArr_).getParamTypes().size() == nbParams_;
    }
}
