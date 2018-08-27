package code.expressionlanguage;

import code.expressionlanguage.common.GeneType;
import code.expressionlanguage.common.TypeUtil;
import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.methods.InterfaceBlock;
import code.expressionlanguage.methods.RootBlock;
import code.expressionlanguage.methods.UniqueRootedBlock;
import code.expressionlanguage.methods.util.TypeVar;
import code.expressionlanguage.opers.util.ClassArgumentMatching;
import code.expressionlanguage.opers.util.DimComp;
import code.expressionlanguage.stds.LgNames;
import code.expressionlanguage.stds.StandardClass;
import code.expressionlanguage.stds.StandardInterface;
import code.expressionlanguage.stds.StandardType;
import code.expressionlanguage.types.PartTypeUtil;
import code.util.CustList;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;

public final class Templates {

    public static final char ARR_BEG = '[';
    public static final String ARR_BEG_STRING = "[";
    public static final String TEMPLATE_SEP = ",";
    public static final String TEMPLATE_END = ">";
    public static final String TEMPLATE_BEGIN = "<";
    public static final char EXTENDS_DEF = ':';
    public static final char SEP_BOUNDS = '&';
    public static final String SEP_CLASS = ".";
    public static final char SEP_CLASS_CHAR = '.';
    public static final String PREFIX_VAR_TYPE = "#";
    public static final char PREFIX_VAR_TYPE_CHAR = '#';
    public static final String INNER_TYPE = "..";

    public static final char LT = '<';

    public static final char GT = '>';

    public static final char COMMA = ',';
    private Templates() {
    }

    static StringList getTypes(String _type) {
        StringList types_ = new StringList();
        int i_ = _type.indexOf(String.valueOf(LT));
        if (i_ == CustList.INDEX_NOT_FOUND_ELT) {
            return types_;
        }
        i_++;
        int nbGt_ = 0;
        int nbLt_ = 0;
        int first_ = i_;
        while (true) {
            if (i_ >= _type.length() - 1) {
                types_.add(_type.substring(first_, i_));
                break;
            }
            if (_type.charAt(i_) == COMMA) {
                if (nbGt_ == nbLt_) {
                    types_.add(_type.substring(first_, i_));
                    first_ = i_ + 1;
                }
            }
            if (_type.charAt(i_) == LT) {
                nbGt_++;
            }
            if (_type.charAt(i_) == GT) {
                nbLt_++;
            }
            i_++;
        }
        return types_;
    }

    public static String getIdFromAllTypes(String _type) {
        return getAllTypes(_type).first();
    }

    public static StringList getAllInnerTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == Templates.LT) {
                    count_++;
                }
                if (curChar_ == Templates.GT) {
                    count_--;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                out_.append(curChar_);
                count_++;
                i_++;
                continue;
            }
            if (_type.substring(i_).startsWith("..")) {
                types_.add(out_.toString());
                out_.delete(0, out_.length());
                i_++;
            } else {
                out_.append(curChar_);
            }
            i_++;
        }
        types_.add(out_.toString());
        return types_;
    }
    public static StringList getAllTypes(String _type) {
        StringList types_ = new StringList();
        StringBuilder out_ = new StringBuilder();
        StringBuilder id_ = new StringBuilder();
        int i_ = CustList.FIRST_INDEX;
        int count_ = 0;
        int len_ = _type.length();
        while (i_ < len_) {
            char curChar_ = _type.charAt(i_);
            if (count_ > 0) {
                if (curChar_ == Templates.LT) {
                    count_++;
                }
                if (curChar_ == Templates.GT) {
                    count_--;
                }
                if (count_ == 1 && curChar_ == Templates.COMMA) {
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                if (count_ == 0 && curChar_ == Templates.GT) {
                    types_.add(out_.toString());
                    out_.delete(0, out_.length());
                    i_++;
                    continue;
                }
                out_.append(curChar_);
                i_++;
                continue;
            }
            if (curChar_ == Templates.LT) {
                count_++;
                i_++;
                continue;
            }
            id_.append(curChar_);
            i_++;
        }
        types_.add(0, id_.toString());
        return types_;
    }

    /**Calls Templates.isCorrect*/
    public static boolean correctClassPartsDynamic(String _className, ExecutableCode _context, boolean _exact) {
        String className_ = PartTypeUtil.processExec(_className, _context);
        if (className_.isEmpty()) {
            return false;
        }
        StringMap<StringList> mapping_ = new StringMap<StringList>();
        return isCorrectTemplateAll(className_, mapping_, _context, _exact);
    }

    static StringList getAllGenericSuperTypes(String _className, ContextEl _context) {
        String className_ = getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(className_);
        return TypeUtil.getAllGenericSuperTypes(root_, _context);
    }
    public static String getIterableFullTypeByStds(String _subType, ContextEl _context) {
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
        String baseSubType_ = getIdFromAllTypes(_subType);
        String baseSuperType_ = getIdFromAllTypes(_superType);
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
                    String baseClass_ = getIdFromAllTypes(c);
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
    static boolean isCorrectWrite(String _className, Analyzable _context) {
        StringList current_ = new StringList(_className);
        boolean already_ = false;
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList elts_ = getAllTypes(c);
                if (elts_ == null) {
                    return false;
                }
                String base_ = elts_.first();
                String trimBase_ = base_.trim();
                if (trimBase_.isEmpty()) {
                    return false;
                }
                String compo_ = PrimitiveTypeUtil.getQuickComponentBaseType(trimBase_).getComponent();
                compo_ = compo_.trim();
                if (PrimitiveTypeUtil.isPrimitive(trimBase_, _context) && already_) {
                    return false;
                }
                if (!compo_.startsWith(PREFIX_VAR_TYPE)) {
                    for (String p: StringList.splitStrings(compo_, SEP_CLASS)) {
                        String trPart_ = p.trim();
                        if (trPart_.isEmpty()) {
                            return false;
                        }
                        for (char h: trPart_.toCharArray()) {
                            if (h == FileResolver.SUPPLEMENT_CHAR) {
                                continue;
                            }
                            if (StringList.isWordChar(h)) {
                                continue;
                            }
                            return false;
                        }
                    }
                } else {
                    String compoLoc_ = compo_.substring(PREFIX_VAR_TYPE.length());
                    if (!StringList.isWord(compoLoc_.trim())) {
                        return false;
                    }
                }
                int nbParams_ = elts_.size();
                for (int i = CustList.SECOND_INDEX; i < nbParams_; i++) {
                    String baseLoc_ = elts_.get(i).trim();
                    String compoLoc_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseLoc_).getComponent();
                    if (compoLoc_.startsWith(PREFIX_VAR_TYPE)) {
                        if (!StringList.isWord(compoLoc_.substring(PREFIX_VAR_TYPE.length()).trim())) {
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
        return isCorrectTemplateAll(_className, _inherit, _context, true);
    }
    public static boolean isCorrectTemplateAll(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _exact) {
        if (!isCorrectTemplate(_className, _inherit, _context, _exact)) {
            return false;
        }
        StringList current_ = new StringList(_className);
        while (true) {
            StringList next_ = new StringList();
            for (String c: current_) {
                StringList types_ = getAllTypes(c);
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
        return isCorrectTemplate(_className, _inherit, _context, true);
    }
    static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Analyzable _context, boolean _exact) {
        StringList types_ = getAllTypes(_className);
        String className_ = types_.first();
        className_ = PrimitiveTypeUtil.getQuickComponentBaseType(className_).getComponent();
        if (PrimitiveTypeUtil.isPrimitive(className_, _context)) {
            return true;
        }
        if (className_.startsWith(PREFIX_VAR_TYPE)) {
            return _inherit.contains(className_.substring(1));
        }
        if (_exact) {
            if (!correctNbParameters(_className, _context)) {
                return false;
            }
        } else {
            if (types_.size() > 1 && !correctNbParameters(_className, _context)) {
                return false;
            }
            if (types_.size() == 1) {
                return true;
            }
        }
        int i_ = CustList.FIRST_INDEX;
        EqList<StringList> boundsAll_ = null;
        GeneType r_ = _context.getClassBody(className_);
        boundsAll_ = new EqList<StringList>();
        for (TypeVar t:r_.getParamTypesMapValues()) {
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
        String types_ = getIdFromAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_).getComponent();
        GeneType root_ = _classes.getClassBody(className_);
        return root_.getGenericString();
    }
    public static CustList<TypeVar> getConstraints(String _className, Analyzable _context) {
        if (_className.isEmpty()) {
            return new CustList<TypeVar>();
        }
        String types_ = getIdFromAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_).getComponent();
        GeneType root_ = _context.getClassBody(className_);
        return root_.getParamTypesMapValues();
    }
    public static String getMadeVarTypes(String _className, StringList _classNames,ExecutableCode _context) {
        String type_ = getIdFromAllTypes(_className);
        GeneType root_ = _context.getClassBody(type_);
        String pref_ = getGenericString(type_, _context);
        StringMap<String> varTypes_ = new StringMap<String>();
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
        if (typeVar_.size() != _classNames.size()) {
            return null;
        }
        int i_ = CustList.FIRST_INDEX;
        for (TypeVar t: typeVar_) {
            String arg_ = _classNames.get(i_);
            varTypes_.put(t.getName(), arg_);
            i_++;
        }
        String formatted_ = getFormattedType(pref_, varTypes_);
        if (!correctClassPartsDynamic(formatted_, _context, true)) {
            return null;
        }
        return formatted_;
    }
    static StringMap<String> getVarTypes(String _className, boolean _checkExact,Analyzable _context) {
        StringList types_ = getAllTypes(_className);
        String className_ = PrimitiveTypeUtil.getQuickComponentBaseType(types_.first()).getComponent();

        String objType_ = _context.getStandards().getAliasObject();
        GeneType root_ = _context.getClassBody(className_);
        StringMap<String> varTypes_ = new StringMap<String>();
        CustList<TypeVar> typeVar_ = root_.getParamTypesMapValues();
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
        if (_m.getArg().isVariable()) {
            return !PrimitiveTypeUtil.isPrimitive(_m.getParam(), _context);
        }
        return isCorrect(_m, _context);
    }
    public static boolean isCorrect(Mapping _m, Analyzable _context) {
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
                MappingPairs m_ = getSimpleMapping(a,p,generalMapping_, _context);
                if (m_ == null) {
                    continue;
                }
                boolean locOk_ = true;
                for (Matching n: m_.getPairsArgParam()) {
                    if (!StringList.quickEq(n.getParam(), n.getArg())) {
                        locOk_ = false;
                        break;
                    }
                }
                if (!locOk_) {
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
    private static MappingPairs getSimpleMapping(String _arg, String _param, StringMap<StringList> _inherit, Analyzable _context) {
        StringList typesArg_ = getAllTypes(_arg);
        StringList typesParam_ = getAllTypes(_param);
        String baseArg_ = typesArg_.first();
        String baseParam_ = typesParam_.first();
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(_arg);
        DimComp dParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(_param);
        String baseArrayParam_ = dParam_.getComponent();
        String baseArrayArg_ = dArg_.getComponent();
        DimComp dBaseParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseParam_);
        String classParam_ = dBaseParam_.getComponent();
        Mapping _m = new Mapping();
        _m.setMapping(_inherit);
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (_arg.isEmpty()) {
                MappingPairs m_ = new MappingPairs();
                return m_;
            }
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
                String base_ = getIdFromAllTypes(a);
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
                if (StringList.quickEq(c, _param)) {
                    generic_ = c;
                    break;
                }
            }
        }
        if (generic_ == null) {
            for (String c: curClasses_) {
                String baseClass_ = getIdFromAllTypes(c);
                baseClass_ = PrimitiveTypeUtil.getQuickComponentBaseType(baseClass_).getComponent();
                if (!correctNbParameters(c, _context)) {
                    return null;
                }
            }
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    String baseClass_ = getIdFromAllTypes(c);
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
        StringList foundSuperClass_ = getAllTypes(generic_);
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
            String gene_ = ((UniqueRootedBlock)r_).getImportedDirectGenericSuperClass();
            return getIdFromAllTypes(gene_);
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
        String baseClass_ = getIdFromAllTypes(_genericClassName);
        Classes classes_ = _context.getClasses();
        RootBlock r_ = classes_.getClassBody(baseClass_);
        if (r_ != null) {
            return ((UniqueRootedBlock)r_).getImportedDirectGenericSuperClass();
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
            StringList bases_ = new StringList();
            for (String i: ((UniqueRootedBlock)r_).getImportedDirectGenericSuperInterfaces()) {
                bases_.add(getIdFromAllTypes(i));
            }
            return bases_;
        }
        if (r_ instanceof InterfaceBlock) {
            StringList bases_ = new StringList();
            for (String i: ((InterfaceBlock)r_).getImportedDirectSuperInterfaces()) {
                bases_.add(getIdFromAllTypes(i));
            }
            return bases_;
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getDirectInterfaces();
        }
        return ((StandardInterface)r_).getDirectSuperClasses();
    }

    private static String getGenericSuperInterfaceName(String _genericClassName, int _index, Analyzable _context) {
        String baseClass_ = getIdFromAllTypes(_genericClassName);
        GeneType r_ = _context.getClassBody(baseClass_);
        if (r_ instanceof UniqueRootedBlock) {
            return ((UniqueRootedBlock)r_).getImportedDirectGenericSuperInterfaces().get(_index);
        }
        if (r_ instanceof InterfaceBlock) {
            return ((InterfaceBlock)r_).getImportedDirectSuperInterfaces().get(_index);
        }
        if (r_ instanceof StandardClass) {
            return ((StandardClass)r_).getDirectInterfaces().get(_index);
        }
        return ((StandardInterface)r_).getDirectSuperClasses().get(_index);
    }

    public static boolean correctNbParameters(String _genericClass, Analyzable _context) {
        StringBuilder id_ = new StringBuilder();
        for (String i: getAllInnerTypes(_genericClass)) {
            if (i.isEmpty()) {
                continue;
            }
            StringList params_ = getAllTypes(i);
            String base_;
            if (id_.length() > 0) {
                base_ = StringList.concat(id_.toString(),"..",params_.first());
                id_.delete(0, id_.length());
            } else {
                base_ = params_.first();
            }
            int nbParams_ = params_.size() - 1;
            String baseArr_ = PrimitiveTypeUtil.getQuickComponentBaseType(base_).getComponent();
            boolean ex_ = _context.getClassBody(baseArr_).getParamTypes().size() == nbParams_;
            if (!ex_) {
                return false;
            }
            id_.append(baseArr_);
        }
        return true;
    }
}
