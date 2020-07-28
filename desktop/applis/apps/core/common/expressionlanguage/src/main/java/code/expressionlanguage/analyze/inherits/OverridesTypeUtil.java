package code.expressionlanguage.analyze.inherits;

import code.expressionlanguage.ContextEl;
import code.expressionlanguage.analyze.blocks.InterfaceBlock;
import code.expressionlanguage.analyze.blocks.OverridableBlock;
import code.expressionlanguage.analyze.blocks.RootBlock;
import code.expressionlanguage.analyze.types.AnaTypeUtil;
import code.expressionlanguage.analyze.types.GeneStringOverridable;
import code.expressionlanguage.analyze.types.OverridingMethodDto;
import code.expressionlanguage.analyze.util.AnaFormattedRootBlock;
import code.expressionlanguage.analyze.util.Members;
import code.expressionlanguage.common.*;
import code.expressionlanguage.functionid.ClassMethodId;
import code.expressionlanguage.functionid.FormattedMethodId;
import code.expressionlanguage.functionid.MethodId;
import code.expressionlanguage.inherits.ComparingByTypeList;
import code.expressionlanguage.inherits.Templates;
import code.util.*;

public final class OverridesTypeUtil {

    private OverridesTypeUtil() {
    }

    public static StringMap<ClassMethodId> getConcreteMethodsToCall(AnaGeneType _type, MethodId _realId, ContextEl _conf) {
        StringMap<ClassMethodId> eq_ = new StringMap<ClassMethodId>();
        String baseClassFound_ = _type.getFullName();
        for (EntryCust<RootBlock, Members> e: _conf.getAnalyzing().getMapMembers().entryList()) {
            RootBlock c = e.getKey();
            String name_ = c.getFullName();
            String baseCond_ = Templates.getOverridingFullTypeByBases(c, baseClassFound_, _conf);
            if (baseCond_.isEmpty()) {
                continue;
            }
            ClassMethodId f_ = tryGetUniqueId(baseClassFound_, c, _realId, _conf);
            if (f_ != null) {
                eq_.put(name_, f_);
                continue;
            }
            CustList<GeneStringOverridable> finalMethods_ = new CustList<GeneStringOverridable>();
            CustList<GeneStringOverridable> methods_ = new CustList<GeneStringOverridable>();
            StringList all_ = new StringList();
            all_.add(name_);
            all_.addAllElts(c.getAllSuperTypes());
            for (String s: all_) {
                RootBlock r_ = _conf.getAnalyzing().getAnaClassBody(s);
                if (!(r_ instanceof InterfaceBlock)) {
                    continue;
                }
                String v_ = Templates.getOverridingFullTypeByBases(r_, baseClassFound_, _conf);
                if (v_.isEmpty()) {
                    continue;
                }
                //r_, as super interface of c, is a sub type of type input
                FormattedMethodId l_ = _realId.quickOverrideFormat(v_, _conf);
                CustList<OverridingMethodDto> ov_ = r_.getAllOverridingMethods();
                //r_ inherit the formatted method
                CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
                boolean found_ = false;
                //if the overridden types contain the type input, then retrieve the sub types of the input type
                //(which are super types of r_)
                for (GeneStringOverridable t: getList(ov_,l_)) {
                    String t_ = t.getGeneString();
                    String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                    if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                        found_ = true;
                    }
                    if (!t.getType().isSubTypeOf(baseClassFound_,_conf)) {
                        continue;
                    }
                    foundSuperClasses_.add(t);
                }
                if (!found_) {
                    continue;
                }
                feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            }
            ClassMethodId id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
                continue;
            }
            finalMethods_ = new CustList<GeneStringOverridable>();
            methods_ = new CustList<GeneStringOverridable>();
            FormattedMethodId l_ = _realId.quickOverrideFormat(baseCond_, _conf);
            CustList<OverridingMethodDto> ov_ = c.getAllOverridingMethods();
            //r_ inherit the formatted method
            CustList<GeneStringOverridable> foundSuperClasses_ = new CustList<GeneStringOverridable>();
            boolean found_ = false;
            //if the overridden types contain the type input, then retrieve the sub types of the input type
            //(which are super types of r_)
            for (GeneStringOverridable t: getList(ov_,l_)) {
                String t_ = t.getGeneString();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, baseClassFound_)) {
                    found_ = true;
                }
                foundSuperClasses_.add(t);
            }
            if (!found_) {
                continue;
            }
            feedMehodsLists(finalMethods_, methods_, foundSuperClasses_);
            id_ = filterUniqId(_conf, finalMethods_, methods_);
            if (id_ != null) {
                eq_.put(name_, id_);
            }
        }
        return eq_;
    }

    private static void feedMehodsLists(CustList<GeneStringOverridable> finalMethods_, CustList<GeneStringOverridable> methods_, CustList<GeneStringOverridable> foundSuperClasses_) {
        for (GeneStringOverridable t: foundSuperClasses_) {
            OverridableBlock method_ = t.getBlock();
            if (method_.isAbstractMethod()) {
                continue;
            }
            if (method_.isFinalMethod()) {
                finalMethods_.add(t);
            }
            methods_.add(t);
        }
    }
    private static ClassMethodId filterUniqId(ContextEl _conf, CustList<GeneStringOverridable> finalMethods_, CustList<GeneStringOverridable> methods_) {
        StringMap<MethodId> defs_ = new StringMap<MethodId>();
        StringList list_ = new StringList();
        for (GeneStringOverridable v: finalMethods_) {
            defs_.put(v.getGeneString(), v.getBlock().getId());
            list_.add(v.getGeneString());
        }
        list_ = AnaTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return new ClassMethodId(class_, defs_.getVal(class_));
        }
        defs_ = new StringMap<MethodId>();
        list_ = new StringList();
        for (GeneStringOverridable v: methods_) {
            defs_.put(v.getGeneString(), v.getBlock().getId());
            list_.add(v.getGeneString());
        }
        list_ = AnaTypeUtil.getSubclasses(list_, _conf);
        if (list_.onlyOneElt()) {
            String class_ = list_.first();
            return new ClassMethodId(class_, defs_.getVal(class_));
        }
        return null;
    }

    private static ClassMethodId tryGetUniqueId(String _subTypeName, RootBlock _type, MethodId _realId, ContextEl _conf) {
        //c is a concrete sub type of type input
        for (AnaFormattedRootBlock s: _type.getAllGenericClassesInfo()) {
            RootBlock r_ = s.getRootBlock();
            String v_ = Templates.getOverridingFullTypeByBases(r_, _subTypeName, _conf);
            if (v_.isEmpty()) {
                continue;
            }
            //r_, as super class of c, is a sub type of type input
            FormattedMethodId l_ = _realId.quickOverrideFormat(v_, _conf);
            CustList<OverridingMethodDto> ov_ = r_.getAllOverridingMethods();
            //r_ inherit the formatted method
            boolean found_ = false;
            TreeMap<String,OverridableBlock> tree_ = new TreeMap<String,OverridableBlock>(new ComparingByTypeList(r_.getAllGenericClasses()));
            //if the overridden types contain the type input, then look for the "most sub typed" super class of r_
            for (GeneStringOverridable t: getList(ov_,l_)) {
                String t_ = t.getGeneString();
                String baseSuperType_ = StringExpUtil.getIdFromAllTypes(t_);
                if (StringList.quickEq(baseSuperType_, _subTypeName)) {
                    found_ = true;
                }
                if (t.getType() instanceof InterfaceBlock) {
                    continue;
                }
                tree_.put(t_,t.getBlock());
            }
            if (!found_) {
                continue;
            }
            String classNameFound_;
            MethodId realId_;
            if (tree_.isEmpty()) {
                continue;
            }
            classNameFound_ = tree_.firstKey();
            realId_ = tree_.firstValue().getId();
            if (tree_.firstValue().isAbstractMethod()) {
                continue;
            }
            return new ClassMethodId(classNameFound_, realId_);
        }
        return null;
    }
    private static CustList<GeneStringOverridable> getList(CustList<OverridingMethodDto> _list, FormattedMethodId _id) {
        CustList<GeneStringOverridable> list_ = getNullList(_list, _id);
        if (list_ == null) {
            list_ = new CustList<GeneStringOverridable>();
        }
        return list_;
    }

    private static CustList<GeneStringOverridable> getNullList(CustList<OverridingMethodDto> _list, FormattedMethodId _id) {
        for (OverridingMethodDto o: _list) {
            if (o.getFormattedMethodId().eq(_id)) {
                return o.getMethodIds();
            }
        }
        return null;
    }


}
