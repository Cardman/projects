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
    private static final String INTERFACE_PREFIX = "interface ";
    private static final String CLASS_PREFIX = "class ";
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
//            DimComp dimCompArg_ = PrimitiveTypeUtil.getComponentBaseType(arg_);
            DimComp dimCompArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(arg_);
            int dimArg_ = dimCompArg_.getDim();
            String comp_ = dimCompArg_.getComponent();
            boolean lookInInherit_ = comp_.startsWith(PREFIX_VAR_TYPE);
            for (Type e: t.getBounds()) {
                Mapping m_ = new Mapping();
                String ext_ = extractFromPrefix(e.toString());
                m_.setParam(format(_className, ext_, _classes));
                if (lookInInherit_ && _inherit.contains(comp_.substring(1))) {
                    boolean ok_ = false;
                    for (String v: _inherit.getVal(comp_.substring(1))) {
                        m_.setArg(v);
//                        String boundArr_ = PrimitiveTypeUtil.getArrayType(v, dimArg_);
                        String boundArr_ = PrimitiveTypeUtil.getPrettyArrayType(v, dimArg_);
//                        DimComp dimCompBoundArg_ = PrimitiveTypeUtil.getComponentBaseType(boundArr_);
                        DimComp dimCompBoundArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(boundArr_);
                        int dimBoundArg_ = dimCompBoundArg_.getDim();
                        if (dimBoundArg_ > 0) {
                            if (!PrimitiveTypeUtil.isArrayAssignable(boundArr_, m_.getParam())) {
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
                m_.setArg(arg_);
//                DimComp dimCompParam_ = PrimitiveTypeUtil.getComponentBaseType(m_.getParam());
                DimComp dimCompParam_ = PrimitiveTypeUtil.getQuickComponentBaseType(m_.getParam());
                int dimParam_ = dimCompParam_.getDim();
                if (dimArg_ > 0 && dimParam_ > 0) {
                    if (!PrimitiveTypeUtil.isArrayAssignable(arg_, m_.getParam())) {
                        continue;
                    }
                }
                m_.setMapping(_inherit);
                if (!isCorrect(m_, _classes)) {
                    return false;
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
        if (className_.startsWith(WILD_CARD)) {
            className_ = removeWildCard(className_);
        }
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
//        current_.setMapping(generalMapping_);
        current_.getPairsArgParam().add(match_);
        current_.getParamArgs().put(param_, new StringList(arg_));
        visited_.getPairsArgParam().add(match_);
        while (true) {
            MappingPairs next_ = new MappingPairs();
//            boolean allNull_ = true;
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
//                            if (nextParam_.startsWith(PREFIX_VAR_TYPE)) {
//                                boolean ok_ = _m.inheritArgParam(nextParam_.substring(1), n.getArg().substring(1));
////                                if (p.getMapping().contains(nextParam_.substring(1))) {
////                                    if (n.getArg().startsWith(PREFIX_VAR_TYPE)) {
////                                        if (!StringList.quickEq(nextParam_, n.getArg())) {
////                                            return false;
////                                        }
////                                        continue;
////                                    }
////                                    for (String v: p.getMapping().getVal(nextParam_.substring(1))) {
////                                        if (StringList.quickEq(v, n.getArg())) {
////                                            ok_ = true;
////                                            break;
////                                        }
////                                    }
////                                    if (!ok_) {
////                                        return false;
////                                    }
////                                    continue;
////                                }
//                                if (!ok_) {
//                                    return false;
//                                }
//                                continue;
//                            }
                            if (!StringList.quickEq(n.getParam(), n.getArg())) {
                                return false;
                            }
                            
                        } else {
                            String boundsParam_ = removeWildCard(nextParam_);
                            StringList typesParam_ = StringList.splitChars(boundsParam_, SEP_BOUNDS);
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
                                StringList typesArg_ = StringList.splitChars(boundsArg_, SEP_BOUNDS);
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
//            for (Matching m: current_.getPairsArgParam()) {
//                Mapping map_ = new Mapping();
//                map_.setArg(m.getArg());
//                map_.setParam(m.getParam());
//                map_.setMapping(current_.getMapping());
//                MappingPairs m_ = getMapping(map_, _classes);
//                if (m_ == null) {
//                    return false;
//                }
////                if (m_ == null) {
////                    continue;
////                }
////                allNull_ = false;
//                for (Matching n: m_.getPairsArgParam()) {
//                    String nextParam_ = n.getParam();
//                    if (!nextParam_.startsWith(WILD_CARD)) {
//                        String paramType_ = n.getParam();
//                        if (paramType_.startsWith(PREFIX_VAR_TYPE)) {
//                            boolean ok_ = false;
//                            if (m_.getMapping().contains(paramType_.substring(1))) {
//                                for (String v: m_.getMapping().getVal(paramType_.substring(1))) {
//                                    if (StringList.quickEq(v, n.getArg())) {
//                                        ok_ = true;
//                                        break;
//                                    }
//                                }
//                                if (!ok_) {
//                                    return false;
//                                }
//                                continue;
//                            }
//                        }
//                        if (!StringList.quickEq(n.getParam(), n.getArg())) {
//                            return false;
//                        }
//                        
//                    } else {
//                        String boundsParam_ = removeWildCard(nextParam_);
//                        StringList typesParam_ = StringList.splitChars(boundsParam_, SEP_BOUNDS);
//                        if (!n.getArg().startsWith(WILD_CARD)) {
//                            for (String e: typesParam_) {
//                                match_ = new Matching();
//                                match_.setArg(n.getArg());
//                                match_.setParam(e);
//                                if (!visited_.getPairsArgParam().containsObj(match_)) {
//                                    visited_.getPairsArgParam().add(match_);
//                                    next_.getPairsArgParam().add(match_);
//                                }
//                            }
//                        } else {
//                            String boundsArg_ = removeWildCard(n.getArg());
//                            StringList typesArg_ = StringList.splitChars(boundsArg_, SEP_BOUNDS);
//                            for (String d: typesArg_) {
//                                for (String e: typesParam_) {
//                                    match_ = new Matching();
//                                    match_.setArg(d);
//                                    match_.setParam(e);
//                                    if (!visited_.getPairsArgParam().containsObj(match_)) {
//                                        visited_.getPairsArgParam().add(match_);
//                                        next_.getPairsArgParam().add(match_);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            if (next_.getPairsArgParam().isEmpty()) {
//                return true;
//            }
//            if (allNull_) {
//                return false;
//            }
            current_ = next_;
        }
    }
    private static String removeWildCard(String _wildCard) {
        String sub_ = _wildCard.substring(1);
        sub_ = sub_.substring(sub_.indexOf(EXTENDS)+1);
        return StringList.removeAllSpaces(sub_);
    }

    private static String extractFromPrefix(String _wildCard) {
        String str_ = _wildCard;
        if (_wildCard.startsWith(CLASS_PREFIX)) {
            str_ = _wildCard.substring(CLASS_PREFIX.length());
        } else if (_wildCard.startsWith(INTERFACE_PREFIX)) {
            str_ = _wildCard.substring(INTERFACE_PREFIX.length());
        }
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
        String arg_ = _m.getArg();
        String param_ = _m.getParam();
        StringList typesArg_ = StringList.getAllTypes(arg_);
        StringList typesParam_ = StringList.getAllTypes(param_);
        String baseArg_ = typesArg_.first();
        if (baseArg_.startsWith(WILD_CARD)) {
            baseArg_ = removeWildCard(baseArg_);
        }
        String baseParam_ = typesParam_.first();
        if (baseParam_.startsWith(WILD_CARD)) {
            baseParam_ = removeWildCard(baseParam_);
        }
//        DimComp dArg_ = PrimitiveTypeUtil.getComponentBaseType(arg_);
        DimComp dArg_ = PrimitiveTypeUtil.getQuickComponentBaseType(arg_);
//        DimComp dParam_ = PrimitiveTypeUtil.getComponentBaseType(param_);
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
//            MappingPairs m_ = new MappingPairs();
//            return m_;
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
//            if (_m.getMapping().contains(baseParam_.substring(1))) {
//                boolean ok_ = false;
//                for (String v: _m.getMapping().getVal(baseParam_.substring(1))) {
//                    if (_m.getMapping().contains(v.substring(1))) {
//                        if (_m.containsParamArg(v.substring(1), baseArg_)) {
//                            ok_ = true;
//                            break;
//                        }
//                        continue;
//                    }
//                    if (PrimitiveTypeUtil.canBeUseAsArgument(v, baseArg_, _classes)) {
//                        ok_ = true;
//                        break;
//                    }
//                }
//                if (!ok_) {
//                    return null;
//                }
//            }
//            StringMap<StringList> map_ = buildMapping(arg_, param_);
//            MappingPairs m_ = new MappingPairs();
//            m_.setMapping(map_);
//            return m_;
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
//            StringMap<StringList> map_ = buildMapping(arg_, param_);
            int len_ = typesParam_.size();
            EqList<Matching> pairsArgParam_ = new EqList<Matching>();
            for (int i = CustList.SECOND_INDEX; i < len_; i++) {
                Matching match_ = new Matching();
                match_.setArg(typesArg_.get(i));
                match_.setParam(typesParam_.get(i));
                pairsArgParam_.add(match_);
            }
            MappingPairs m_ = new MappingPairs();
//            m_.setMapping(map_);
            m_.setPairsArgParam(pairsArgParam_);
            return m_;
        }
//        StringList curClasses_ = new StringList(arg_);
//        StringList visitedClasses_ = new StringList(arg_);

        //getArrayType
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
            while (true) {
                StringList nextClasses_ = new StringList();
                for (String c: curClasses_) {
                    StringList allTypes_ = StringList.getAllTypes(c);
                    String baseClass_ = allTypes_.first();
                    baseClass_ = extractFromPrefix(baseClass_);
                    if (baseClass_.startsWith(WILD_CARD)) {
                        baseClass_ = removeWildCard(baseClass_);
                    }
                    baseClass_ = PrimitiveTypeUtil.getArrayClass(baseClass_);
                    Class<?> cl_ = ConstClasses.classAliasForNameNotInit(baseClass_);
                    Class<?> superCl_ = cl_.getSuperclass();
                    if (superCl_ != null) {
                        String superClass_ = superCl_.getName();
                        String geneSuperClass_ = cl_.getGenericSuperclass().toString();
                        geneSuperClass_ = format(c, geneSuperClass_, _classes);
//                        geneSuperClass_ = PrimitiveTypeUtil.getComponentType(geneSuperClass_);
                        if (dParam_.getDim() > 0) {
//                            if (StringList.quickEq(superClass_, PrimitiveTypeUtil.getComponentType(typesParam_.first())))
                            if (StringList.quickEq(superClass_, PrimitiveTypeUtil.getQuickComponentType(typesParam_.first()))) {
//                                generic_ = PrimitiveTypeUtil.getArrayType(geneSuperClass_, dim_);
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperClass_, dim_);
                                break;
                            }
                        } else {
                            if (StringList.quickEq(superClass_, typesParam_.first())) {
//                                generic_ = PrimitiveTypeUtil.getArrayType(geneSuperClass_, dim_);
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
                        String geneSuperInterface_ = cl_.getGenericInterfaces()[i_].toString();
                        geneSuperInterface_ = format(c, geneSuperInterface_, _classes);
//                        geneSuperInterface_ = PrimitiveTypeUtil.getComponentType(geneSuperInterface_);
                        if (dParam_.getDim() > 0) {
//                            if (StringList.quickEq(s.getName(), PrimitiveTypeUtil.getComponentType(typesParam_.first())))
                            if (StringList.quickEq(s.getName(), PrimitiveTypeUtil.getQuickComponentType(typesParam_.first()))) {
//                                generic_ = PrimitiveTypeUtil.getArrayType(geneSuperInterface_, dim_);
                                generic_ = PrimitiveTypeUtil.getPrettyArrayType(geneSuperInterface_, dim_);
                                break;
                            }
                        } else {
                            if (StringList.quickEq(s.getName(), typesParam_.first())) {
//                                generic_ = PrimitiveTypeUtil.getArrayType(geneSuperInterface_, dim_);
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

//    private static StringMap<StringList> buildMapping(String _arg, String _param) {
//        StringList typesArg_ = StringList.getAllTypes(_arg);
//        StringList typesParam_ = StringList.getAllTypes(_param);
//        StringMap<StringList> mapping_ = new StringMap<StringList>();
//        int len_ = typesParam_.size();
//        for (int i = CustList.SECOND_INDEX; i < len_; i++) {
//            String bounds_ = typesArg_.get(i);
//            StringList boundList_ = new StringList();
//            String typeParam_ = typesParam_.get(i);
//            if (!typeParam_.startsWith(PREFIX_VAR_TYPE)) {
//                continue;
//            }
//            if (bounds_.indexOf(SEP_BOUNDS) == CustList.INDEX_NOT_FOUND_ELT) {
//                boundList_.add(bounds_);
//                mapping_.put(typeParam_.substring(1), boundList_);
//                continue;
//            }
//            for (String b: StringList.splitChars(bounds_, SEP_BOUNDS)) {
//                boundList_.add(b);
//            }
//            mapping_.put(typeParam_.substring(1), boundList_);
//        }
//        return mapping_;
//    }
}
