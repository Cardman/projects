package code.expressionlanguage;

import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import code.expressionlanguage.methods.Classes;
import code.expressionlanguage.opers.util.DimComp;
import code.util.CustList;
import code.util.EntryCust;
import code.util.EqList;
import code.util.StringList;
import code.util.StringMap;
import code.util.consts.ConstClasses;
import code.util.opers.CollectionsUtil;

public final class Templates {

    private static final String TEMPLATE_SEP = ",";
    private static final String TEMPLATE_END = ">";
    private static final String TEMPLATE_BEGIN = "<";
    private static final String WILD_CARD = "?";
    private static final String EXTENDS = "~";
    private static final char SEP_BOUNDS = '&';
    private static final String SEP_CLASS = ".";
    private static final String EMPTY_STRING = "";
    private static final String PREFIX_VAR_TYPE = "#";

    private Templates() {
    }
    public static boolean isCorrectTemplate(String _className, StringMap<StringList> _inherit, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        int i_ = CustList.FIRST_INDEX;
        String className_ = types_.first();
        className_ = PrimitiveTypeUtil.getArrayClass(className_);
        Class<?> cl_ = ConstClasses.classAliasForNameNotInit(className_);
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            i_++;
            String arg_ = types_.get(i_);
            if (StringList.quickEq(arg_, WILD_CARD)) {
                continue;
            }
            DimComp dimCompArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(arg_);
            int dimArg_ = dimCompArg_.getDim();
            String comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(PREFIX_VAR_TYPE);
            for (Type e: t.getBounds()) {
                Mapping m_ = new Mapping();
                String typeBound_ = getName(e);
                String ext_ = extractFromPrefix(typeBound_);
                String param_ = format(_className, ext_, _classes);
                m_.setParam(param_);
                if (lookInInherit_ && _inherit.contains(comp_.substring(1))) {
                    boolean ok_ = false;
                    for (String v: _inherit.getVal(comp_.substring(1))) {
                        m_.setArg(v);
                        String boundArr_ = PrimitiveTypeUtil.getPrettyArrayType(v, dimArg_);
                        DimComp dimCompBoundArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(boundArr_);
                        int dimBoundArg_ = dimCompBoundArg_.getDim();
                        if (dimBoundArg_ > 0) {
                            if (!PrimitiveTypeUtil.isArrayAssignable(boundArr_, param_)) {
                                continue;
                            }
                        }
                        m_.setMapping(_inherit);
                        if (isCorrect(m_, _classes)) {
                            ok_ = true;
                            break;
                        }
                    }
                    if (!ok_) {
                        return false;
                    }
                    continue;
                }
                
                DimComp dimCompParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(param_);
                int dimParam_ = dimCompParam_.getDim();
                if (dimArg_ > 0 && dimParam_ > 0) {
                    if (!PrimitiveTypeUtil.isArrayAssignable(arg_, param_)) {
                        continue;
                    }
                }
                m_.setArg(arg_);
                m_.setMapping(_inherit);
                if (arg_.startsWith(WILD_CARD)) {
                    arg_ = removeWildCard(arg_);
                    for (String c: split(arg_)) {
                        m_.setArg(c);
                        if (!isCorrect(m_, _classes)) {
                            return false;
                        }
                    }
                } else {
                    m_.setArg(arg_);
                    if (!isCorrect(m_, _classes)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    static String format(String _first, String _second, Classes _classes) {
        StringMap<String> varTypes_ = getVarTypes(_first, _classes);
        return getFormattedType(_second, varTypes_);
    }
    static StringMap<String> getVarTypes(String _className, Classes _classes) {
        StringList types_ = StringList.getAllTypes(_className);
        String className_ = extractFromPrefix(types_.first());
        className_ = PrimitiveTypeUtil.getArrayClass(className_);
        Class<?> cl_ = ConstClasses.classAliasForNameNotInit(className_);
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
                }
                newList_.set(i,_varTypes.getVal(type_));
            }
        }
        return newList_.join(EMPTY_STRING);
    }
    public static boolean isCorrect(Mapping _m, Classes _classes) {
        String arg_ = _m.getArg();
        String param_ = _m.getParam();
        MappingPairs current_ = new MappingPairs();
        MappingPairs visited_ = new MappingPairs();
        Matching match_ = new Matching();
        StringMap<StringList> generalMapping_ = _m.getMapping();
        match_.setArg(arg_);
        match_.setParam(param_);
        current_.getPairsArgParam().add(match_);
        current_.getParamArgs().put(param_, new StringList(arg_));
        visited_.getPairsArgParam().add(match_);
        while (true) {
            MappingPairs next_ = new MappingPairs();
            StringMap<StringList> paramArgs_;
            paramArgs_ = current_.getParamArgs();
            for (EntryCust<String, StringList> e: paramArgs_.entryList()) {
                Mapping map_ = new Mapping();
                map_.setParam(e.getKey());
                CustList<MappingPairs> maps_;
                maps_ = new CustList<MappingPairs>();
                for (String a: e.getValue()) {
                    map_.setArg(a);
                    map_.setMapping(generalMapping_);
                    MappingPairs m_ = getMapping(map_, _classes);
                    if (m_ != null) {
                        maps_.add(m_);
                    }
                }
                if (maps_.isEmpty()) {
                    return false;
                }
                for (MappingPairs p: maps_) {
                    for (Matching n: p.getPairsArgParam()) {
                        String nextParam_ = n.getParam();
                        if (!nextParam_.startsWith(WILD_CARD)) {
                            if (!StringList.quickEq(n.getParam(), n.getArg())) {
                                return false;
                            }
                            
                        } else {
                            String boundsParam_ = removeWildCard(nextParam_);
                            StringList typesParam_ = split(boundsParam_);
                            if (!n.getArg().startsWith(WILD_CARD)) {
                                for (String t: typesParam_) {
                                    String argLoc_ = n.getArg();
                                    if (!visited_.containsParamArg(t, argLoc_)) {
                                        visited_.addParamArg(t, argLoc_);
                                        next_.addParamArg(t, argLoc_);
                                    }
                                }
                            } else {
                                String boundsArg_ = removeWildCard(n.getArg());
                                StringList typesArg_ = split(boundsArg_);
                                for (String d: typesArg_) {
                                    for (String t: typesParam_) {
                                        if (!visited_.containsParamArg(t, d)) {
                                            visited_.addParamArg(t, d);
                                            next_.addParamArg(t, d);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (next_.getParamArgs().isEmpty()) {
                return true;
            }
            current_ = next_;
        }
    }
    private static EqList<StringList> getClassBounds(String _className, Classes _classes) {
        String baseClass_ = extractFromPrefix(_className);
        baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
        Class<?> cl_ = ConstClasses.classAliasForNameNotInit(baseClass_);
        EqList<StringList> bounds_ = new EqList<StringList>();
        StringMap<StringList> localBounds_ = new StringMap<StringList>();
        for (TypeVariable<?> t: cl_.getTypeParameters()) {
            StringList localBound_ = new StringList();
            for (Type b: t.getBounds()) {
                String typeString_ = getName(b);
                String ext_ = extractFromPrefix(typeString_);
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
                            classBounds_.add(n);
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
    private static String removeWildCard(String _wildCard) {
        String sub_ = _wildCard.substring(1);
        sub_ = sub_.substring(sub_.indexOf(EXTENDS)+1);
        return StringList.removeAllSpaces(sub_);
    }

    private static String extractFromPrefix(String _wildCard) {
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

    private static MappingPairs getMapping(Mapping _m, Classes _classes) {
        EqList<StringList> boundsArg_;
        EqList<StringList> boundsParam_;
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
        int dim_ = dArg_.getDim();
        if (dArg_.getDim() < dParam_.getDim()) {
            return null;
        }
        boolean return_ = false;
        if (dArg_.getDim() > dParam_.getDim()) {
            if (!StringList.quickEq(baseArrayParam_, Object.class.getName())) {
                return null;
            }
            return_ = true;
        }
        if (baseArrayParam_.startsWith(PREFIX_VAR_TYPE)) {
            if (return_) {
                return null;
            }
            if (_m.inheritArgParam(baseArrayParam_.substring(1), baseArrayArg_.substring(1))) {
                MappingPairs m_ = new MappingPairs();
                return m_;
            }
            return null;
        }
        if (!baseArrayArg_.startsWith(PREFIX_VAR_TYPE)) {
            if (!PrimitiveTypeUtil.canBeUseAsArgument(baseParam_, baseArg_, _classes)) {
                return null;
            }
        }
        if (return_) {
            MappingPairs m_ = new MappingPairs();
            return m_;
        }
        if (StringList.quickEq(baseArg_, baseParam_)) {
            int len_ = typesParam_.size();
            EqList<Matching> pairsArgParam_ = new EqList<Matching>();
            boundsArg_ = getClassBounds(typesArg_.first(), _classes);
            boundsParam_ = getClassBounds(typesParam_.first(), _classes);
            for (int i = CustList.SECOND_INDEX; i < len_; i++) {
                Matching match_ = new Matching();
                if (StringList.quickEq(typesArg_.get(i), WILD_CARD)) {
                    match_.setArg(WILD_CARD+EXTENDS+boundsArg_.get(i-1).join(SEP_BOUNDS));
                } else {
                    match_.setArg(typesArg_.get(i));
                }
                if (StringList.quickEq(typesParam_.get(i), WILD_CARD)) {
                    match_.setParam(WILD_CARD+EXTENDS+boundsParam_.get(i-1).join(SEP_BOUNDS));
                } else {
                    match_.setParam(typesParam_.get(i));
                }
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
        if (StringList.quickEq(baseParam_, Object.class.getName())) {
            generic_ = Object.class.getName();
        }
        if (generic_ == null) {
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
                    baseClass_ = extractFromPrefix(baseClass_);
                    baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
                    Class<?> cl_ = ConstClasses.classAliasForNameNotInit(baseClass_);
                    Class<?> superCl_ = cl_.getSuperclass();
                    if (superCl_ != null) {
                        String superClass_ = superCl_.getName();
                        String geneSuperClass_ = getName(cl_.getGenericSuperclass());
                        geneSuperClass_ = format(c, geneSuperClass_, _classes);
                        if (dParam_.getDim() > 0) {
                            if (StringList.quickEq(superClass_, PrimitiveTypeUtil.getQuickComponentType(typesParam_.first()))) {
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperClass_, dim_);
                                break;
                            }
                        } else {
                            if (StringList.quickEq(superClass_, typesParam_.first())) {
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperClass_, dim_);
                                break;
                            }
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
                        geneSuperInterface_ = format(c, geneSuperInterface_, _classes);
                        if (dParam_.getDim() > 0) {
                            if (StringList.quickEq(s.getName(), PrimitiveTypeUtil.getQuickComponentType(typesParam_.first()))) {
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                                break;
                            }
                        } else {
                            if (StringList.quickEq(s.getName(), typesParam_.first())) {
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                                break;
                            }
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
        boundsArg_ = getClassBounds(foundSuperClass_.first(), _classes);
        boundsParam_ = getClassBounds(typesParam_.first(), _classes);
        EqList<Matching> pairsArgParam_ = new EqList<Matching>();
        len_ = foundSuperClass_.size();
        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
            Matching match_ = new Matching();
            if (StringList.quickEq(foundSuperClass_.get(i), WILD_CARD)) {
                match_.setArg(WILD_CARD+EXTENDS+boundsArg_.get(i-1).join(SEP_BOUNDS));
            } else {
                match_.setArg(foundSuperClass_.get(i));
            }
            if (StringList.quickEq(typesParam_.get(i), WILD_CARD)) {
                match_.setParam(WILD_CARD+EXTENDS+boundsParam_.get(i-1).join(SEP_BOUNDS));
            } else {
                match_.setParam(typesParam_.get(i));
            }
            pairsArgParam_.add(match_);
        }
        MappingPairs m_ = new MappingPairs();
        m_.setPairsArgParam(pairsArgParam_);
        return m_;
    }

    private static StringList split(String _geneTypes) {
        int nbOpenedTemplates_ = 0;
        int len_ = _geneTypes.length();
        int i_ = CustList.FIRST_INDEX;
        StringList types_ = new StringList();
        int begin_ = CustList.FIRST_INDEX;
        while (i_ < len_) {
            String cur_ = _geneTypes.substring(i_, i_ + 1);
            if (StringList.quickEq(cur_, TEMPLATE_BEGIN)) {
                nbOpenedTemplates_++;
            }
            if (StringList.quickEq(cur_, TEMPLATE_END)) {
                nbOpenedTemplates_--;
            }
            if (nbOpenedTemplates_ == 0 && _geneTypes.charAt(i_) == SEP_BOUNDS) {
                types_.add(_geneTypes.substring(begin_, i_));
                begin_ = i_+1;
            }
            i_++;
        }
        types_.add(_geneTypes.substring(begin_));
        return types_;
    }

    private static String getName(Type _t) {
        if (_t instanceof Class<?>) {
            return ((Class<?>)_t).getName();
        }
        return _t.toString();
    }
}
